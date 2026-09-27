package nl.teamdiopside.infinitybuttons.block.faced4;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import nl.teamdiopside.infinitybuttons.block.ButtonFaced4;
import nl.teamdiopside.infinitybuttons.registry.IBSounds;
import org.jetbrains.annotations.Nullable;

public class Doorbell extends ButtonFaced4 {

    protected static final VoxelShape PRESSED_SHAPE = Block.box(6, 4, 14, 10, 12, 16);
    protected static final VoxelShape FULL_SHAPE = Shapes.or(PRESSED_SHAPE, Block.box(7, 7, 13, 9, 9, 14));

    public final boolean emitsPower;

    public Doorbell(Properties properties, boolean emitsPower) {
        super(properties, PRESSED_SHAPE, FULL_SHAPE);
        this.emitsPower = emitsPower;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        if (blockPlaceContext.getClickedFace().getAxis().isVertical()) {
            return null;
        }
        return defaultBlockState().setValue(FACING, blockPlaceContext.getClickedFace());
    }

    @Override
    protected SoundEvent getSound(boolean press) {
        return press ? IBSounds.DOORBELL.get() : IBSounds.SILENT.get();
    }

    @Override
    protected int getPressTicks() {
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
