package nl.teamdiopside.infinitybuttons.fabric.datagen;

import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import nl.teamdiopside.infinitybuttons.InfinityButtons;
import nl.teamdiopside.infinitybuttons.block.normal.CopperButton;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import nl.teamdiopside.infinitybuttons.registry.RegistryUtils;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

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

    public static final Function<String, ModelTemplate> SMALL_BUTTONS = (String suffix) -> new ModelTemplate(
            Optional.of(ResourceLocation.withDefaultNamespace("block/button" + suffix)),
            Optional.empty(), // The suffix to apply to the end of any model that uses this template
            TextureSlot.TEXTURE
    );

    public static final Function<String, ModelTemplate> LARGE_BUTTONS = (String suffix) -> new ModelTemplate(
            Optional.of(getResource("block/large_button" + suffix)),
            Optional.empty(), // The suffix to apply to the end of any model that uses this template
            TextureSlot.TEXTURE
    );

    public void generateSmallButton(BlockModelGenerators blockModels, Block block, TextureMapping texMap) {
        blockModels.blockStateOutput.accept(BlockModelGenerators.createButton(block,
                BlockModelGenerators.plainVariant(
                        defineModel(SMALL_BUTTONS, "", block, texMap, blockModels.modelOutput)
                ),
                BlockModelGenerators.plainVariant(
                        defineModel(SMALL_BUTTONS, "_pressed", block, texMap, blockModels.modelOutput)
                )
        ));
    }

    public void generateLargeButton(BlockModelGenerators blockModels, Block block, TextureMapping texMap) {
        blockModels.blockStateOutput.accept(BlockModelGenerators.createButton(block,
                BlockModelGenerators.plainVariant(
                        defineModel(LARGE_BUTTONS, "", block, texMap, blockModels.modelOutput)
                ),
                BlockModelGenerators.plainVariant(
                        defineModel(LARGE_BUTTONS, "_pressed", block, texMap, blockModels.modelOutput)
                )
        ));
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModels) {

        for (Map.Entry<String, RegistryUtils.LargeVariantSupplier<Block>> entry : IBBlocks.STONE_BUTTONS.entrySet()) {
            String type = entry.getKey();
            if (Objects.equals(type, "dripstone")) type = "dripstone_block"; // Dripstone wants to be special again
            RegistryUtils.LargeVariantSupplier<Block> variantSupplier = entry.getValue();

            Block small = variantSupplier.get(false);
            Block large = variantSupplier.get(true);

            TextureMapping texMap = new TextureMapping()
                    .put(TextureSlot.TEXTURE, ResourceLocation.withDefaultNamespace("block/" + type));

            generateSmallButton(blockModels, small, texMap);
            generateLargeButton(blockModels, large, texMap);
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
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModels) {

        for (Map.Entry<String, RegistryUtils.LargeVariantSupplier<Block>> entry : IBBlocks.STONE_BUTTONS.entrySet()) {
            String type = entry.getKey();
            RegistryUtils.LargeVariantSupplier<Block> variantSupplier = entry.getValue();

            Block small = variantSupplier.get(false);
            Block large = variantSupplier.get(true);

            TextureMapping texMap = new TextureMapping()
                    .put(TextureSlot.TEXTURE, ResourceLocation.withDefaultNamespace("block/" + type));

            itemModels.itemModelOutput.accept(small.asItem(),
                    ItemModelUtils.plainModel(
                            defineModel(SMALL_BUTTONS, "_inventory", small, texMap, itemModels.modelOutput)
                    )
            );

            itemModels.itemModelOutput.accept(large.asItem(),
                    ItemModelUtils.plainModel(
                            defineModel(LARGE_BUTTONS, "_inventory", large, texMap, itemModels.modelOutput)
                    )
            );
        }

        // This overrides the default models that simply reference the base block model
        for (Map.Entry<BlockSetType, RegistrySupplier<Block>> entry : IBBlocks.DEFAULT_LARGE_BUTTONS.entrySet()) {
            BlockSetType type = entry.getKey();
            Block block = entry.getValue().get();

            TextureMapping texMap = new TextureMapping()
                    .put(TextureSlot.TEXTURE, getDefaultTexture(type));

            itemModels.itemModelOutput.accept(block.asItem(),
                    ItemModelUtils.plainModel(
                            defineModel(LARGE_BUTTONS, "_inventory", block, texMap, itemModels.modelOutput)
                    )
            );
        }

        for (RegistryUtils.LargeVariantSupplier<CopperButton> variantSupplier : IBBlocks.COPPER_BUTTONS.values()) {
            CopperButton small = variantSupplier.get(false);
            CopperButton large = variantSupplier.get(true);
            String state = small.getAge() == WeatheringCopper.WeatherState.UNAFFECTED ? "copper" : small.getAge().getSerializedName() + "_copper";

            TextureMapping texMap = new TextureMapping()
                    .put(TextureSlot.TEXTURE, getResource("block/" + state + "_button"));


            itemModels.itemModelOutput.accept(small.asItem(),
                    ItemModelUtils.plainModel(
                            defineModel(SMALL_BUTTONS, "_inventory", small, texMap, itemModels.modelOutput)
                    )
            );

            itemModels.itemModelOutput.accept(large.asItem(),
                    ItemModelUtils.plainModel(
                            defineModel(LARGE_BUTTONS, "_inventory", large, texMap, itemModels.modelOutput)
                    )
            );
        }

        for (Map.Entry<String, RegistrySupplier<Block>> entry : IBBlocks.SECRET_BUTTONS.entrySet()) {
            String name = entry.getKey();
            Block block = entry.getValue().get();

            itemModels.itemModelOutput.accept(block.asItem(),
                    ItemModelUtils.plainModel(
                            ResourceLocation.fromNamespaceAndPath(InfinityButtons.MOD_ID, "block/secret_buttons/" + name)
                    )
            );
        }
    }

    /**
     * Small helper method to simplify creating models
     */
    private static ResourceLocation defineModel(Function<String, ModelTemplate> model, String version, Block block,
                                                TextureMapping texMap, BiConsumer<ResourceLocation, ModelInstance> output) {
        return model.apply(version).createWithSuffix(block, version, texMap, output);
    }
}