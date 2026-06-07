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
import nl.teamdiopside.infinitybuttons.block.emergency.SafeEmergencyButton;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButton;
import nl.teamdiopside.infinitybuttons.block.faced6.normal.CopperButton;
import nl.teamdiopside.infinitybuttons.block.faced6.normal.NormalButton;
import nl.teamdiopside.infinitybuttons.fabric.datagen.simplifier.SimpleReferenceModel;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.MOD_ID;
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

        Set<String> CUSTOM_TEXTURE = Set.of("emerald", "gold", "iron", "prismarine_brick", "diamond", "netherite");

        for (Map.Entry<String, IBRegistryUtils.LargeVariantSupplier<? extends Block>> entry : IBBlocks.SMALL_LARGE_BUTTONS.entrySet()) {
            String type = entry.getKey();

            if (Objects.equals(type, "dripstone")) type = "dripstone_block"; // Dripstone wants to be special again

            IBRegistryUtils.LargeVariantSupplier<? extends Block> variantSupplier = entry.getValue();

            Block small = variantSupplier.get(false);
            Block large = variantSupplier.get(true);

            final String finalType = type;
            Function<Boolean, TextureMapping> texMap = (isLarge) -> (
                    CUSTOM_TEXTURE.contains(finalType)
                            ? new TextureMapping().put(TextureSlot.TEXTURE, getResource("block/" + finalType + (isLarge ? "_large" : "") + "_button"))
                            : new TextureMapping().put(TextureSlot.TEXTURE, ResourceLocation.withDefaultNamespace("block/" + finalType))
            );

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

        for (IBRegistryUtils.LargeVariantSupplier<CopperButton> variantSupplier : IBBlocks.COPPER_BUTTONS.values()) {
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
            Block emergencyButton = IBRegistryUtils.getBlockByID(MOD_ID, color + "_emergency_button");
            Block safetyButton = IBRegistryUtils.getBlockByID(MOD_ID, color + "_safe_emergency_button");

            // Parenting item models
            new SimpleReferenceModel("item/" + color + "_emergency_button", "block/" + color + "_emergency_button")
                    .build(blockModels.modelOutput);
            new SimpleReferenceModel("item/" + color + "_safe_emergency_button", "block/" + color + "_safe_emergency_button_closed")
                    .build(blockModels.modelOutput);

            // Generate safe variant states/models dynamically
            generateSafeEmergencyButton(blockModels, emergencyButton, safetyButton, color);
        }

        for (RegistrySupplier<SecretButton> secretButton : IBBlocks.SECRET_BUTTONS.values()) {
            generateSecretButton(blockModels, secretButton.get());
        }
    }

    /**
     * <h1>Emergency Buttons & Safety Buttons</h1>
     */

    private SimpleReferenceModel emergencyButtonRefModel(String variant, String color, boolean safe) {
        String safeSuffix = safe ? "safe_" : "";
        return new SimpleReferenceModel("block/" + color + "_" + safeSuffix + "emergency_button" + variant,
                                        "block/" + safeSuffix + "emergency_button" + variant)
                .withTexture(ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/emergency_buttons/" + color));
    }

    @SuppressWarnings("all")
    public void generateSafeEmergencyButton(BlockModelGenerators blockModels, Block emergencyBlock, Block safetyBlock, String color) {
        ResourceLocation emergencyNormal = emergencyButtonRefModel("", color, false).build(blockModels.modelOutput);

        ResourceLocation emergencyPressed = emergencyButtonRefModel("_pressed", color, false).build(blockModels.modelOutput);
        ResourceLocation safetyPressed    = emergencyButtonRefModel("_pressed", color, true).build(blockModels.modelOutput);

        ResourceLocation safetyOpen       = emergencyButtonRefModel("_open", color, true).build(blockModels.modelOutput);
        ResourceLocation safetyClosed     = emergencyButtonRefModel("_closed", color, true).build(blockModels.modelOutput);

        var emergencyButtonBuilder = PropertyDispatch.properties(BlockStateProperties.ATTACH_FACE, BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.POWERED);
        var safetyButtonBuilder = PropertyDispatch.properties(SafeEmergencyButton.CLOSED, BlockStateProperties.ATTACH_FACE, BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.POWERED);

        for (boolean closed : Set.of(true, false))
            for (boolean powered : Set.of(true, false))
                for (AttachFace face : AttachFace.values())
                    for (Direction facing : Direction.Plane.HORIZONTAL) {

                        ResourceLocation emergencyModel = powered ? emergencyPressed : emergencyNormal;
                        ResourceLocation safetyModel    = closed ? safetyClosed : (powered ? safetyPressed : safetyOpen);

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

    /**
     * <h1>Secret Buttons</h1>
     */

    private static void generateSecretButton(BlockModelGenerators blockModels, SecretButton secretButton) {
        var camouflage = IBRegistryUtils.BlockInfo.from(secretButton.getCamouflage());
        var button = IBRegistryUtils.BlockInfo.from(secretButton);

        var camouflageLocation = ResourceLocation.fromNamespaceAndPath(camouflage.namespace(), "block/" + camouflage.id());
        var buttonLocation = ResourceLocation.fromNamespaceAndPath(button.namespace(), "block/" + button.id());

        ResourceLocation parentLocation = ResourceLocation.fromNamespaceAndPath(MOD_ID,
                "block/secret_buttons/" + secretButton.type.getSerializedName() + "_secret_button");

        new SimpleReferenceModel(buttonLocation, parentLocation)
                .withTexture(camouflageLocation) // Model location == Texture location
                .build(blockModels.modelOutput);


        MultiVariantGenerator generator = MultiVariantGenerator.multiVariant(secretButton);

        PropertyDispatch.C2<Boolean, Direction> poweredFacingDispatch = PropertyDispatch.properties(
                BlockStateProperties.POWERED,
                BlockStateProperties.HORIZONTAL_FACING
        );

        Direction[] directions = { Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST };
        int[] rotations = { 0, 90, 180, 270 };

        for (int i = 0; i < directions.length; i++) {
            poweredFacingDispatch.select(false, directions[i], Variant.variant().with(VariantProperties.MODEL, camouflageLocation));

            Variant activeVariant = Variant.variant()
                    .with(VariantProperties.MODEL, buttonLocation)
                    .with(VariantProperties.UV_LOCK, true);

            if (rotations[i] != 0) {
                activeVariant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.values()[rotations[i] / 90]);
            }

            poweredFacingDispatch.select(true, directions[i], activeVariant);
        }

        generator.with(poweredFacingDispatch);

        blockModels.blockStateOutput.accept(generator);
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