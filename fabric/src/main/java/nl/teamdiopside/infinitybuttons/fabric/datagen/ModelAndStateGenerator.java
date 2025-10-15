package nl.teamdiopside.infinitybuttons.fabric.datagen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockSetType;

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

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModels) {}

    @Override
    public void generateItemModels(ItemModelGenerators itemModels) {}

    /**
     * Small helper method to simplify creating models
     */
    private static ResourceLocation defineModel(Function<String, ModelTemplate> model, String version, Block block,
                                         TextureMapping texMap, BiConsumer<ResourceLocation, ModelInstance> output) {
        return model.apply(version).createWithSuffix(block, version, texMap, output);
    }
}