package nl.teamdiopside.infinitybuttons.fabric.datagen;

import com.google.gson.JsonElement;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import nl.teamdiopside.infinitybuttons.block.normal.CopperButton;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import nl.teamdiopside.infinitybuttons.registry.RegistryUtils;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static nl.teamdiopside.infinitybuttons.InfinityButtonsUtil.getResource;

@Environment(EnvType.CLIENT)
public class ModelAndStateGenerator extends FabricModelProvider {
    public ModelAndStateGenerator(FabricDataOutput output) {
        super(output);
    }

    private ResourceLocation getDefaultTexture(BlockSetType type) {
        if (type == BlockSetType.POLISHED_BLACKSTONE || type == BlockSetType.STONE)
            return ResourceLocation.withDefaultNamespace("block/" + type.name());

        return ResourceLocation.withDefaultNamespace("block/" + type.name() + "_planks");
    }

    private static Function<String, ModelTemplate> template(Function<String, ResourceLocation> locationFactory) {
        return name -> new ModelTemplate(Optional.of(locationFactory.apply(name)), Optional.empty(), TextureSlot.TEXTURE);
    }

    public static final Function<String, ModelTemplate> TEMPLATE_SMALL =
            template(name -> ResourceLocation.withDefaultNamespace("block/button" + name));

    public static final Function<String, ModelTemplate> TEMPLATE_LARGE =
            template(name -> getResource("block/large_button" + name));

    public static final Function<String, ModelTemplate> TEMPLATE_INVENTORY_SMALL =
            template(name -> ResourceLocation.withDefaultNamespace("block/button" + name));

    public static final Function<String, ModelTemplate> TEMPLATE_INVENTORY_LARGE =
            template(name -> getResource("block/large_button" + name));


    public void generateSmallButton(BlockModelGenerators blockModels, Block block, TextureMapping texMap) {
        blockModels.blockStateOutput.accept(BlockModelGenerators.createButton(block,
                defineModel(TEMPLATE_SMALL, "", block, texMap, blockModels.modelOutput),
                defineModel(TEMPLATE_SMALL, "_pressed", block, texMap, blockModels.modelOutput)
        ));
        blockModels.modelOutput.accept(ModelLocationUtils.getModelLocation(block.asItem()), new DelegatedModel(
                defineModel(TEMPLATE_INVENTORY_SMALL, "_inventory", block, texMap, blockModels.modelOutput)
        ));
    }

    public void generateLargeButton(BlockModelGenerators blockModels, Block block, TextureMapping texMap) {
        blockModels.blockStateOutput.accept(BlockModelGenerators.createButton(block,
                defineModel(TEMPLATE_LARGE, "", block, texMap, blockModels.modelOutput),
                defineModel(TEMPLATE_LARGE, "_pressed", block, texMap, blockModels.modelOutput)
        ));
        blockModels.modelOutput.accept(ModelLocationUtils.getModelLocation(block.asItem()), new DelegatedModel(
                defineModel(TEMPLATE_INVENTORY_LARGE, "_inventory", block, texMap, blockModels.modelOutput)
        ));
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModels) {

        Set<String> CUSTOM_TEXTURE = Set.of("emerald", "gold", "iron");

        for (Map.Entry<String, RegistryUtils.LargeVariantSupplier<Block>> entry : IBBlocks.SMALL_LARGE_BUTTONS.entrySet()) {
            String type = entry.getKey();

            if (Objects.equals(type, "dripstone")) type = "dripstone_block"; // Dripstone wants to be special again

            RegistryUtils.LargeVariantSupplier<Block> variantSupplier = entry.getValue();

            Block small = variantSupplier.get(false);
            Block large = variantSupplier.get(true);

            final String finalType = type;
            Function<Boolean, TextureMapping> texMap = (isLarge) -> (
                    CUSTOM_TEXTURE.contains(finalType)
                            ? new TextureMapping().put(TextureSlot.TEXTURE, getResource("block/" + finalType + (isLarge ? "_large" : "") + "_button"))
                            : new TextureMapping().put(TextureSlot.TEXTURE, ResourceLocation.withDefaultNamespace("block/" + finalType))
            );

            System.out.println(finalType + " - " + CUSTOM_TEXTURE.contains(finalType));

            generateSmallButton(blockModels, small, texMap.apply(false));
            generateLargeButton(blockModels, large, texMap.apply(true));
        }

        for (Map.Entry<BlockSetType, RegistrySupplier<Block>> entry : IBBlocks.DEFAULT_LARGE_BUTTONS.entrySet()) {
            BlockSetType type = entry.getKey();
            Block block = entry.getValue().get();

            TextureMapping texMap = new TextureMapping()
                    .put(TextureSlot.TEXTURE, getDefaultTexture(type));

            generateLargeButton(blockModels, block, texMap);
        }

        for (RegistryUtils.LargeVariantSupplier<CopperButton> variantSupplier : IBBlocks.COPPER_BUTTONS.values()) {
            CopperButton small = variantSupplier.get(false);
            CopperButton large = variantSupplier.get(true);
            String state = small.getAge() == WeatheringCopper.WeatherState.UNAFFECTED ? "copper" : small.getAge().getSerializedName() + "_copper";

            TextureMapping texMap = new TextureMapping()
                    .put(TextureSlot.TEXTURE, getResource("block/" + state + "_button"));

            generateSmallButton(blockModels, small, texMap);
            generateLargeButton(blockModels, large, texMap);
        }

        // TODO: Emergency Buttons
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        System.out.println("Hello :)");
    }

    /**
     * Small helper method to simplify creating models
     */
    private static ResourceLocation defineModel(Function<String, ModelTemplate> model, String version, Block block,
                                                TextureMapping texMap, BiConsumer<ResourceLocation, Supplier<JsonElement>> output) {
        return model.apply(version).createWithSuffix(block, version, texMap, output);
    }
}