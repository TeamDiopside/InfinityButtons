package nl.teamdiopside.infinitybuttons.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import nl.teamdiopside.infinitybuttons.block.simple.LanternButton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ChainBlock.class)
public class ChainBlockMixin extends RotatedPillarBlock implements SimpleWaterloggedBlock {
    public ChainBlockMixin(Properties settings) {
        super(settings);
    }

    @Override
    protected int getSignal(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
        return infinityButtons$emitsRedstone(blockState, blockGetter, blockPos) && direction == Direction.DOWN ? 15 : 0;
    }

    @Override
    public int getDirectSignal(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
        return getSignal(blockState, blockGetter, blockPos, direction);
    }

    @Override
    protected boolean isSignalSource(BlockState blockState) {
        return true;
    }

    @Unique
    public boolean infinityButtons$emitsRedstone(BlockState state, BlockGetter level, BlockPos pos) {
        if (state.getValue(AXIS) != Direction.Axis.Y || level.getBlockState(pos.above()).getBlock() instanceof ChainBlock) {
            return false;
        }
        int i = 1;
        while (level.getBlockState(pos.below(i)).getBlock() instanceof ChainBlock) {
            if (level.getBlockState(pos.below(i)).getValue(AXIS) != Direction.Axis.Y) return false;
            i++;
        }
        BlockState blockState = level.getBlockState(pos.below(i));
        if (!(blockState.getBlock() instanceof LanternButton)) {
            return false;
        }
        return blockState.getValue(BlockStateProperties.POWERED);
    }

    // Update the top chain too if this chain is updated. If this is the top chain, update the redstone power positions
    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block source, BlockPos sourcePos, boolean notify) {
        int distance = LanternButton.checkChains(level, pos);
        if (distance > 0) {
            level.neighborChanged(pos.above(distance), this, pos);
        } else {
            level.neighborChanged(pos.above(), this, pos);
            level.updateNeighborsAtExceptFromFacing(pos.above(), this, Direction.DOWN);
        }

        super.neighborChanged(state, level, pos, source, sourcePos, notify);
    }


    // Update the redstone power positions if this is the top chain
    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moved) {
        int distance = LanternButton.checkChains(level, pos);
        if (distance == 0) {
            level.updateNeighborsAtExceptFromFacing(pos.above(), this, Direction.DOWN);
        }

        super.onRemove(state, level, pos, newState, moved);
    }

    // TODO: Placing a Redstone lamp directly above a set of chains and powering them does NOT power the lamp on
    // TODO:     for some reason, I urge someone who understands redstone to fix this.
}
