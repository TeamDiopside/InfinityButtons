package nl.teamdiopside.infinitybuttons.block.faced4;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import nl.teamdiopside.infinitybuttons.block.ButtonFaced6;
import nl.teamdiopside.infinitybuttons.registry.IBSounds;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Doorbell extends ButtonFaced6 {

    protected static final VoxelShape PRESSED_SHAPE = Block.box(6, 4, 14, 10, 12, 16);
    protected static final VoxelShape FULL_SHAPE = Shapes.or(PRESSED_SHAPE, Block.box(7, 7, 13, 9, 9, 14));

    public final boolean emitsPower;

    public Doorbell(Properties properties, boolean emitsPower) {
        super(BlockSetType.DARK_OAK, properties, PRESSED_SHAPE, FULL_SHAPE);
        this.emitsPower = emitsPower;
    }

    @Override
    protected void playSound(@Nullable Player playerIn, LevelAccessor worldIn, BlockPos pos, boolean pressed) {
        worldIn.playSound(pressed ? playerIn : null, pos, this.getSound(pressed), SoundSource.BLOCKS, 0.3f, 1f);
    }

    @Override
    protected @NotNull SoundEvent getSound(boolean pressed) {
        return IBSounds.DOORBELL.get();
    }

    @Override
    public void unpress(BlockState state, Level level, BlockPos pos, @Nullable Player player) {
        // No sound when unpressing
        level.setBlockAndUpdate(pos, state.setValue(POWERED, false));
        this.updateNeighbours(state, level, pos);
        level.gameEvent(player, GameEvent.BLOCK_DEACTIVATE, pos);
    }

    @Override
    protected int getPressTicks(Level level, BlockPos pos) {
        return 15;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return this.emitsPower ? super.getAnalogOutputSignal(state, level, pos) : 0;
    }

    @Override
    protected int getDirectSignal(BlockState state, BlockGetter blockGetter, BlockPos pos, Direction direction) {
        return this.emitsPower ? super.getDirectSignal(state, blockGetter, pos, direction) : 0;
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter blockGetter, BlockPos pos, Direction direction) {
        return this.emitsPower ? super.getSignal(state, blockGetter, pos, direction) : 0;
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return this.emitsPower;
    }
}
