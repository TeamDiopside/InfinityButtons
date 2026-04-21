package nl.teamdiopside.infinitybuttons.block.secret;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import nl.teamdiopside.infinitybuttons.block.ButtonFaced4;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SecretButton extends ButtonFaced4 {

    public static final MapCodec<SecretButton> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(propertiesCodec(),
                    SecretButtonType.CODEC.fieldOf("type").forGetter(secretButton -> secretButton.type)
            ).apply(instance, SecretButton::new)
    );

    public static final int PRESS_TICKS = 50;

    public final SecretButtonType type;

    public SecretButton(Properties properties, SecretButtonType type) {
        super(properties, type.shapePressed, type.shapeUnpressed);
        this.type = type;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        Player player = blockPlaceContext.getPlayer();
        return (player == null) ? this.defaultBlockState() : this.defaultBlockState().setValue(
                FACING, player.getDirection().getOpposite()
        );
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        if (blockHitResult.getDirection() != blockState.getValue(FACING)) {
            return InteractionResult.FAIL;
        }

        return super.useWithoutItem(blockState, level, blockPos, player, blockHitResult);
    }

    @Override
    protected SoundEvent getSound() {
        return this.type.sound;
    }

    @Override
    protected int getPressTicks() {
        return PRESS_TICKS;
    }

    @Override
    protected @NotNull MapCodec<? extends SecretButton> codec() {
        return CODEC;
    }
}
