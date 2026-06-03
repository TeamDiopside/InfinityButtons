package nl.teamdiopside.infinitybuttons.fabric.datagen;

import com.google.gson.JsonElement;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.core.Direction;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import nl.teamdiopside.infinitybuttons.InfinityButtons;
import nl.teamdiopside.infinitybuttons.block.emergency.SafeEmergencyButton;
import nl.teamdiopside.infinitybuttons.block.faced6.normal.CopperButton;
import nl.teamdiopside.infinitybuttons.block.faced6.normal.NormalButton;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import nl.teamdiopside.infinitybuttons.registry.RegistryUtils;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.getResource;

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

    public static final Function<String, ModelTemplate> TEMPLATE_EMERGENCY =
            template(name -> getResource("block/emergency_button" + name));
    public static final Function<String, ModelTemplate> TEMPLATE_SAFETY =
            template(name -> getResource("block/safe_emergency_button" + name));

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

        Set<String> CUSTOM_TEXTURE = Set.of("emerald", "gold", "iron", "prismarine_brick", "diamond");

        for (Map.Entry<String, RegistryUtils.LargeVariantSupplier<? extends Block>> entry : IBBlocks.SMALL_LARGE_BUTTONS.entrySet()) {
            String type = entry.getKey();

            if (Objects.equals(type, "dripstone")) type = "dripstone_block"; // Dripstone wants to be special again

            RegistryUtils.LargeVariantSupplier<? extends Block> variantSupplier = entry.getValue();

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

        for (Map.Entry<BlockSetType, RegistrySupplier<NormalButton>> entry : IBBlocks.DEFAULT_LARGE_BUTTONS.entrySet()) {
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

        List<String> colors = new ArrayList<>(Arrays.stream(DyeColor.values()).map(DyeColor::getName).toList());
        colors.add("fancy");

        for (String color : colors) {

            TextureMapping colorMap = new TextureMapping()
                    .put(TextureSlot.TEXTURE, getResource("block/emergency_buttons/" + color));

            Block emergencyButton = RegistryUtils.getBlockByID(InfinityButtons.MOD_ID, color + "_emergency_button");
            Block safetyButton = RegistryUtils.getBlockByID(InfinityButtons.MOD_ID, color + "_safe_emergency_button");

            blockModels.modelOutput.accept(ModelLocationUtils.getModelLocation(emergencyButton.asItem()), new DelegatedModel(
                    ResourceLocation.fromNamespaceAndPath(InfinityButtons.MOD_ID, "block/" + color + "_emergency_button")
            ));
            blockModels.modelOutput.accept(ModelLocationUtils.getModelLocation(safetyButton.asItem()), new DelegatedModel(
                    ResourceLocation.fromNamespaceAndPath(InfinityButtons.MOD_ID, "block/" + color + "_safe_emergency_button_open")
            ));

            // Generate safe variant states/models dynamically
            generateSafeEmergencyButton(blockModels, emergencyButton, safetyButton, colorMap);
        }
    }

    @SuppressWarnings("all")
    public void generateSafeEmergencyButton(BlockModelGenerators blockModels, Block emergencyBlock, Block safetyBlock, TextureMapping colorMap) {
        ResourceLocation emergencyNormal  = defineModel(TEMPLATE_EMERGENCY, "", emergencyBlock, colorMap, blockModels.modelOutput);

        ResourceLocation emergencyPressed = defineModel(TEMPLATE_EMERGENCY, "_pressed", emergencyBlock, colorMap, blockModels.modelOutput);
        ResourceLocation safetyPressed    = defineModel(TEMPLATE_SAFETY, "_pressed", safetyBlock, colorMap, blockModels.modelOutput);

        ResourceLocation safetyOpen       = defineModel(TEMPLATE_SAFETY, "_open", safetyBlock, colorMap, blockModels.modelOutput);
        ResourceLocation safetyClosed     = defineModel(TEMPLATE_SAFETY, "_closed", safetyBlock, colorMap, blockModels.modelOutput);

        var emergencyButtonBuilder = PropertyDispatch.properties(BlockStateProperties.ATTACH_FACE, BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.POWERED);
        var safetyButtonBuilder = PropertyDispatch.properties(SafeEmergencyButton.CLOSED, BlockStateProperties.ATTACH_FACE, BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.POWERED);

        for (boolean closed : Set.of(true, false))
            for (boolean powered : Set.of(true, false))
                for (AttachFace face : AttachFace.values())
                    for (Direction facing : Direction.Plane.HORIZONTAL) {

                        ResourceLocation emergencyModel = powered ? emergencyPressed : emergencyNormal;
                        ResourceLocation safetyModel    = powered ? safetyPressed : (closed ? safetyClosed : safetyOpen);

                        Variant emergencyVariant = Variant.variant().with(VariantProperties.MODEL, emergencyModel);
                        Variant safetyVariant = Variant.variant().with(VariantProperties.MODEL, safetyModel);

                        for (Variant variant : new Variant[]{emergencyVariant, safetyVariant}) {
                            if (face == AttachFace.FLOOR) {
                                if (facing == Direction.EAST) variant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90);
                                else if (facing == Direction.SOUTH) variant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180);
                                else if (facing == Direction.WEST) variant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270);
                            } else {
                                if (face == AttachFace.CEILING) variant.with(VariantProperties.X_ROT, VariantProperties.Rotation.R180);
                                else variant.with(VariantProperties.X_ROT, VariantProperties.Rotation.R270);

                                if (facing == Direction.NORTH) variant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180);
                                else if (facing == Direction.EAST) variant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270);
                                else if (facing == Direction.WEST) variant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90);
                            }
                        }

                        safetyButtonBuilder.select(closed, face, facing, powered, safetyVariant);

                        if (closed) {
                            emergencyButtonBuilder.select(face, facing, powered, emergencyVariant);
                        }
                    }
        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(emergencyBlock).with(emergencyButtonBuilder));
        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(safetyBlock).with(safetyButtonBuilder));
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