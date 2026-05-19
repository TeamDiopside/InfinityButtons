package nl.teamdiopside.infinitybuttons.block.normal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import org.jetbrains.annotations.NotNull;

public class OneUseButton extends NormalButton {
    public final boolean gravelSound;
    public static final int PRESS_TICKS = 10;

    public OneUseButton(BlockSetType blockSetType, Properties properties, boolean large, boolean isLever, boolean gravelSound) {
        super(blockSetType, PRESS_TICKS, properties, large, isLever);
        this.gravelSound = gravelSound;
    }

    @Override
    protected int getPressTicks() {
        return PRESS_TICKS;
    }

    @Override
    protected @NotNull SoundEvent getSound(boolean bl) {
        return this.gravelSound ? SoundEvents.GRAVEL_BREAK : SoundEvents.SAND_BREAK;
    }

    @Override
    protected void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        super.tick(blockState, serverLevel, blockPos, randomSource);

        serverLevel.destroyBlock(blockPos, false);
    }

    @Override
    protected @NotNull SoundType getSoundType(BlockState blockState) {
        return this.gravelSound ? SoundType.GRAVEL : SoundType.SAND;
    }
}
