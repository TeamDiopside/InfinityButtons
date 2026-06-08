package nl.teamdiopside.infinitybuttons.datagen;

import com.google.gson.JsonElement;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import nl.teamdiopside.infinitybuttons.block.emergency.SafeEmergencyButton;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButton;
import nl.teamdiopside.infinitybuttons.block.faced6.normal.CopperButton;
import nl.teamdiopside.infinitybuttons.block.faced6.normal.NormalButton;
import nl.teamdiopside.infinitybuttons.compat.IBModdedBlocks;
import nl.teamdiopside.infinitybuttons.datagen.simplifier.ButtonVariant;
import nl.teamdiopside.infinitybuttons.datagen.simplifier.OutFolder;
import nl.teamdiopside.infinitybuttons.datagen.simplifier.SimpleReferenceModel;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.MOD_ID;

public class ModelAndStateProvider implements DataProvider {
    private final PackOutput.PathProvider blockStatePathProvider;
    private final PackOutput.PathProvider modelPathProvider;

    public ModelAndStateProvider(PackOutput output) {
        this.blockStatePathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "blockstates");
        this.modelPathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        Map<Block, BlockStateGenerator> blockStateOutputs = new HashMap<>();
        Map<ResourceLocation, Supplier<JsonElement>> modelOutputs = new HashMap<>();

        ModelAndStateGen generator = new ModelAndStateGen(
                state -> blockStateOutputs.put(state.getBlock(), state),
                modelOutputs::put,
                item -> {
                }
        );

        generator.run();

        CompletableFuture<?>[] futures = new CompletableFuture<?>[blockStateOutputs.size() + modelOutputs.size()];
        int index = 0;

        for (Map.Entry<Block, BlockStateGenerator> entry : blockStateOutputs.entrySet()) {
            ResourceLocation id = BuiltInRegistries.BLOCK.getKey(entry.getKey());
            futures[index++] = DataProvider.saveStable(cache, entry.getValue().get(), this.blockStatePathProvider.json(id));
        }

        for (Map.Entry<ResourceLocation, Supplier<JsonElement>> entry : modelOutputs.entrySet()) {
            futures[index++] = DataProvider.saveStable(cache, entry.getValue().get(), this.modelPathProvider.json(entry.getKey()));
        }

        return CompletableFuture.allOf(futures);
    }

    public static class ModelAndStateGen extends BlockModelGenerators {

        public ModelAndStateGen(Consumer<BlockStateGenerator> consumer, BiConsumer<ResourceLocation, Supplier<JsonElement>> biConsumer, Consumer<Item> consumer2) {
            super(consumer, biConsumer, consumer2);
        }

        private ResourceLocation getDefaultTexture(BlockSetType type) {
            if (type == BlockSetType.POLISHED_BLACKSTONE || type == BlockSetType.STONE)
                return ResourceLocation.withDefaultNamespace("block/" + type.name());

            return ResourceLocation.withDefaultNamespace("block/" + type.name() + "_planks");
        }

        private static Function<String, ModelTemplate> template(Function<String, ResourceLocation> locationFactory) {
            return name -> new ModelTemplate(Optional.of(locationFactory.apply(name)), Optional.empty(), TextureSlot.TEXTURE);
        }

        public void generateButton(String button_name, ResourceLocation textureLocation, boolean isLarge) {
            Block block = IBRegistryUtils.getBlockByID(MOD_ID, button_name);

            this.blockStateOutput.accept(BlockModelGenerators.createButton(block,
                    SimpleReferenceModel.makeButton(OutFolder.BLOCK, button_name, textureLocation, isLarge, ButtonVariant.BASE).build(this.modelOutput),
                    SimpleReferenceModel.makeButton(OutFolder.BLOCK, button_name, textureLocation, isLarge, ButtonVariant.PRESSED).build(this.modelOutput)
            ));

            SimpleReferenceModel.makeButton(OutFolder.ITEM, button_name, textureLocation, isLarge, ButtonVariant.INVENTORY).build(this.modelOutput);
        }

        @Override
        public void run() {
            Set<String> CUSTOM_TEXTURE = Set.of("emerald", "gold", "iron", "prismarine_brick", "diamond", "netherite");

            for (Map.Entry<ResourceLocation, IBRegistryUtils.LargeVariantSupplier<? extends Block>> entry : IBBlocks.SMALL_LARGE_BUTTONS.entrySet()) {
                String type = entry.getKey().getPath();

                IBRegistryUtils.LargeVariantSupplier<? extends Block> variantSupplier = entry.getValue();

                String smallName = IBRegistryUtils.BlockInfo.getID(variantSupplier.get(false));
                String largeName = IBRegistryUtils.BlockInfo.getID(variantSupplier.get(true));

                boolean hasCustomTexture = CUSTOM_TEXTURE.contains(type);

                Function<Boolean, ResourceLocation> texture = (isLarge) -> (
                        hasCustomTexture ? ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/" + type + (isLarge ? "_large" : "") + "_button")
                                         : ResourceLocation.fromNamespaceAndPath(entry.getKey().getNamespace(), "block/" + type)
                );

                generateButton(smallName, texture.apply(false), false);
                generateButton(largeName, texture.apply(true), true);
            }

            for (Map.Entry<BlockSetType, RegistrySupplier<NormalButton>> entry : IBBlocks.DEFAULT_LARGE_BUTTONS.entrySet()) {
                BlockSetType type = entry.getKey();
                String name = IBRegistryUtils.BlockInfo.getID(entry.getValue().get());

                generateButton(name, getDefaultTexture(type), true);
            }

            for (IBRegistryUtils.LargeVariantSupplier<CopperButton> variantSupplier : IBBlocks.COPPER_BUTTONS.values()) {
                CopperButton small = variantSupplier.get(false);
                CopperButton large = variantSupplier.get(true);
                String state = small.getAge() == WeatheringCopper.WeatherState.UNAFFECTED ? "copper" : small.getAge().getSerializedName() + "_copper";

                ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/" + state + "_button");

                generateButton(IBRegistryUtils.BlockInfo.getID(small), texture, false);
                generateButton(IBRegistryUtils.BlockInfo.getID(large), texture, true);
            }

            List<String> colors = new ArrayList<>(Arrays.stream(DyeColor.values()).map(DyeColor::getName).toList());
            colors.add("fancy");

            for (String color : colors) {
                Block emergencyButton = IBRegistryUtils.getBlockByID(MOD_ID, color + "_emergency_button");
                Block safetyButton = IBRegistryUtils.getBlockByID(MOD_ID, color + "_safe_emergency_button");

                // Parenting item models
                new SimpleReferenceModel("item/" + color + "_emergency_button", "block/" + color + "_emergency_button")
                        .build(this.modelOutput);
                new SimpleReferenceModel("item/" + color + "_safe_emergency_button", "block/" + color + "_safe_emergency_button_closed")
                        .build(this.modelOutput);

                // Generate safe variant states/models dynamically
                generateSafeEmergencyButton(emergencyButton, safetyButton, color);
            }

            var secret_buttons = new ArrayList<>(IBBlocks.SECRET_BUTTONS.values());
            secret_buttons.addAll(IBModdedBlocks.MOD_SECRET_BUTTONS.values());

            for (RegistrySupplier<SecretButton> secretButton : secret_buttons) {
                generateSecretButton(secretButton.get());
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
        public void generateSafeEmergencyButton(Block emergencyBlock, Block safetyBlock, String color) {
            ResourceLocation emergencyNormal = emergencyButtonRefModel("", color, false).build(this.modelOutput);

            ResourceLocation emergencyPressed = emergencyButtonRefModel("_pressed", color, false).build(this.modelOutput);
            ResourceLocation safetyPressed    = emergencyButtonRefModel("_pressed", color, true).build(this.modelOutput);

            ResourceLocation safetyOpen       = emergencyButtonRefModel("_open", color, true).build(this.modelOutput);
            ResourceLocation safetyClosed     = emergencyButtonRefModel("_closed", color, true).build(this.modelOutput);

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
            this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(emergencyBlock).with(emergencyButtonBuilder));
            this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(safetyBlock).with(safetyButtonBuilder));
        }

        /**
         * <h1>Secret Buttons</h1>
         */

        private void generateSecretButton(SecretButton secretButton) {
            var camouflage = IBRegistryUtils.BlockInfo.from(secretButton.getCamouflage());
            var button = IBRegistryUtils.BlockInfo.from(secretButton);

            var camouflageLocation = ResourceLocation.fromNamespaceAndPath(camouflage.namespace(), "block/" + camouflage.id());
            var buttonLocation = ResourceLocation.fromNamespaceAndPath(button.namespace(), "block/" + button.id());

            ResourceLocation parentLocation = ResourceLocation.fromNamespaceAndPath(MOD_ID,
                    "block/secret_buttons/" + secretButton.type.getSerializedName() + "_secret_button");

            SimpleReferenceModel blockModel = new SimpleReferenceModel(buttonLocation, parentLocation)
                    .withTexture(camouflageLocation); // Model location == Texture location

            // Bookshelves have a separate top/bottom texture
            if (button.id().contains("bookshelf") && !camouflage.namespace().equals("minecraft"))
                blockModel.withTexture(TextureSlot.TOP, IBModdedBlocks.BOOKSHELF_TOP_TEXTURES.get(button.id()));

            blockModel.build(this.modelOutput);

            // Item model
            new SimpleReferenceModel(ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/" + button.id()), buttonLocation)
                    .build(this.modelOutput);


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

            this.blockStateOutput.accept(generator);
        }

        /**
         * Small helper method to simplify creating models
         */
        private static ResourceLocation defineModel(Function<String, ModelTemplate> model, String version, Block block,
                                                    TextureMapping texMap, BiConsumer<ResourceLocation, Supplier<JsonElement>> output) {
            return model.apply(version).createWithSuffix(block, version, texMap, output);
        }
    }

    @Override
    public String getName() {
        return "Models and Blockstates";
    }
}