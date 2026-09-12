package nl.teamdiopside.infinitybuttons.datagen.modelstate;

import net.minecraft.core.Direction;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButton;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButtonType;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class SecretButtonStateGenerator implements StateGenerator {

    private final @NotNull SecretButton secretButton;

    private final ResourceLocation originalBlockModel;
    private final ResourceLocation buttonModel;

    public SecretButtonStateGenerator(@NotNull SecretButton secretButton) {
        this.secretButton = secretButton;
        IBRegistryUtils.BlockInfo originalBlock = IBRegistryUtils.BlockInfo.from(secretButton.getCamouflage());
        this.originalBlockModel = ResourceLocation.fromNamespaceAndPath(originalBlock.namespace(), "block/" + originalBlock.id());
        IBRegistryUtils.BlockInfo button = IBRegistryUtils.BlockInfo.from(secretButton);
        this.buttonModel = ResourceLocation.fromNamespaceAndPath(button.namespace(), "block/" + button.id());
    }

    private BlockStateGenerator brickGenerator() {
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

    private BlockStateGenerator genericGenerator() {
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
    public void generate(Consumer<BlockStateGenerator> consumer) {
        BlockStateGenerator generator = secretButton.type == SecretButtonType.BRICK ? brickGenerator() : genericGenerator();
        consumer.accept(generator);
    }
}
