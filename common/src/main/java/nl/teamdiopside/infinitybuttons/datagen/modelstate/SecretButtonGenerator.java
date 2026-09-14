package nl.teamdiopside.infinitybuttons.datagen.modelstate;

import com.google.gson.JsonElement;
import net.minecraft.core.Direction;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButton;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButtonType;
import nl.teamdiopside.infinitybuttons.compat.IBModdedBlocks;
import nl.teamdiopside.infinitybuttons.datagen.simplifier.SimpleReferenceModel;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.MOD_ID;

public class SecretButtonGenerator implements ModelStateGenerator {

    private final @NotNull SecretButton secretButton;

    private final IBRegistryUtils.BlockInfo originalBlockInfo;
    private final IBRegistryUtils.BlockInfo buttonBlockInfo;

    private final ResourceLocation originalBlockModel;
    private final ResourceLocation buttonModel;

    private final ResourceLocation textureLocation;

    public SecretButtonGenerator(@NotNull SecretButton secretButton) {
        this.secretButton = secretButton;
        this.originalBlockInfo = IBRegistryUtils.BlockInfo.from(secretButton.getCamouflage());
        this.originalBlockModel = ResourceLocation.fromNamespaceAndPath(originalBlockInfo.namespace(), "block/" + originalBlockInfo.id());
        this.buttonBlockInfo = IBRegistryUtils.BlockInfo.from(secretButton);
        this.buttonModel = ResourceLocation.fromNamespaceAndPath(buttonBlockInfo.namespace(), "block/secret_buttons/" + buttonBlockInfo.id());

        // Some camouflages don't keep their texture at the naive <namespace>:block/<id> path
        this.textureLocation = IBModdedBlocks.SECRET_BUTTON_TEXTURE_OVERRIDES.getOrDefault(buttonBlockInfo.id(), originalBlockModel);
    }

    /**
     * <h1>Blockstates</h1>
     */

    private BlockStateGenerator brickStateGenerator() {
        MultiPartGenerator generator = MultiPartGenerator.multiPart(secretButton);
        ResourceLocation topModel = buttonModel.withSuffix("_top");

        Condition isPressed = Condition.condition().term(BlockStateProperties.POWERED, true);
        Condition notPressed = Condition.condition().term(BlockStateProperties.POWERED, false);

        // Use original model when not pressed
        Variant original = Variant.variant()
                .with(VariantProperties.MODEL, originalBlockModel)
                .with(VariantProperties.UV_LOCK, true);
        generator.with(notPressed, original);

        // Add top when pressed
        Variant top = Variant.variant()
                .with(VariantProperties.MODEL, topModel)
                .with(VariantProperties.UV_LOCK, true);
        generator.with(isPressed, top);

        // Add rotated model when pressed
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            Condition facing = Condition.condition().term(BlockStateProperties.HORIZONTAL_FACING, dir);
            Variant activeVariant = Variant.variant()
                    .with(VariantProperties.MODEL, buttonModel);

            int rotation = (((int)(dir.toYRot() / 90f)) + 2) % 4;
            if (rotation > 0) {
                activeVariant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.values()[rotation]);
            }
            generator.with(Condition.and(isPressed, facing), activeVariant);
        }

        return generator;
    }

    private BlockStateGenerator genericStateGenerator() {
        MultiVariantGenerator generator = MultiVariantGenerator.multiVariant(secretButton);

        PropertyDispatch.C2<Boolean, Direction> poweredFacingDispatch = PropertyDispatch.properties(
                BlockStateProperties.POWERED,
                BlockStateProperties.HORIZONTAL_FACING
        );

        Direction[] directions = { Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST };

        for (int i = 0; i < directions.length; i++) {
            poweredFacingDispatch.select(false, directions[i], Variant.variant().with(VariantProperties.MODEL, originalBlockModel));

            Variant activeVariant = Variant.variant()
                    .with(VariantProperties.MODEL, buttonModel)
                    .with(VariantProperties.UV_LOCK, true);

            if (i != 0) {
                activeVariant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.values()[i]);
            }

            poweredFacingDispatch.select(true, directions[i], activeVariant);
        }

        generator.with(poweredFacingDispatch);
        return generator;
    }

    @Override
    public void generateState(Consumer<BlockStateGenerator> consumer) {
        BlockStateGenerator generator = secretButton.type == SecretButtonType.HORIZONTAL_BRICK ? brickStateGenerator() : genericStateGenerator();
        consumer.accept(generator);
    }

    /**
     * <h1>Block Models</h1>
     */

    private void generateBrickTop(BiConsumer<ResourceLocation, Supplier<JsonElement>> consumer, ResourceLocation parentLocation) {
        if (secretButton.type == SecretButtonType.HORIZONTAL_BRICK) {
            // Normal Bricks need an extra top model.
            SimpleReferenceModel topModel = new SimpleReferenceModel(buttonModel.withSuffix("_top"), parentLocation.withSuffix("_top"))
                    .withTexture(textureLocation); // Model location == Texture location (unless overridden)
            topModel.build(consumer);
        }
    }

    @Override
    public void generateBlockModels(BiConsumer<ResourceLocation, Supplier<JsonElement>> consumer) {
        if (secretButton.type == SecretButtonType.BOOKSHELF) return; // SKIP BOOKSHELFS
        ResourceLocation parentLocation = ResourceLocation.fromNamespaceAndPath(MOD_ID,
                "block/secret_buttons/types/" + secretButton.type.getSerializedName() + "_secret_button");

        SimpleReferenceModel blockModel = new SimpleReferenceModel(buttonModel, parentLocation)
                .withTexture(textureLocation); // Model location == Texture location (unless overridden)

        blockModel.build(consumer);
        generateBrickTop(consumer, parentLocation);
    }

    /**
     * <h1>Items Models</h1>
     */

    @Override
    public void generateItemModels(BiConsumer<ResourceLocation, Supplier<JsonElement>> consumer) {
        var itemLocation = ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/" + buttonBlockInfo.id());
        if (secretButton.type != SecretButtonType.HORIZONTAL_BRICK) {
            new SimpleReferenceModel(itemLocation, buttonModel).build(consumer);
            return;
        }

        ResourceLocation brickParent = ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/brick_secret_button_parent");
        var brickModel = new SimpleReferenceModel(itemLocation, brickParent);
        brickModel.withTexture(textureLocation);
        brickModel.build(consumer);
    }
}
