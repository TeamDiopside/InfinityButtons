package net.larsmans.infinitybuttons.mixin;

import net.larsmans.infinitybuttons.block.custom.LanternButton;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import static net.larsmans.infinitybuttons.InfinityButtonsUtil.checkChains;

@Mixin(ChainBlock.class)
public abstract class ChainBlockMixin extends PillarBlock implements Waterloggable {

    public ChainBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return checkAround(state, world, pos) && direction == Direction.DOWN ? 15 : 0;
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return checkAround(state, world, pos) && direction == Direction.DOWN ? 15 : 0;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    public boolean checkAround(BlockState state, BlockView world, BlockPos pos) {
        if (state.get(AXIS) != Direction.Axis.Y || world.getBlockState(pos.up()).getBlock() instanceof ChainBlock) {
            return false;
        }
        int i = 1;
        while (world.getBlockState(pos.down(i)).getBlock() instanceof ChainBlock) {
            if (world.getBlockState(pos.down(i)).get(AXIS) != Direction.Axis.Y) return false;
            i++;
        }
        BlockState blockState = world.getBlockState(pos.down(i));
        if (!(blockState.getBlock() instanceof LanternButton)) {
            return false;
        }
        return blockState.get(LanternButton.PRESSED);
    }

    // Update the top chain too if this chain is updated. If this is the top chain, update the redstone power positions
    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        int distance = checkChains(world, pos);
        if (distance > 0) {
            world.updateNeighbor(pos.up(distance), this, pos);
        } else {
            world.updateNeighbor(pos.up(), this, pos);
            world.updateNeighborsExcept(pos.up(), this, Direction.DOWN);
        }

        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }

    // Update the redstone power positions if this is the top chain
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        int distance = checkChains(world, pos);
        if (distance == 0) {
            world.updateNeighborsExcept(pos.up(), this, Direction.DOWN);
        }

        super.onStateReplaced(state, world, pos, newState, moved);
    }
}
