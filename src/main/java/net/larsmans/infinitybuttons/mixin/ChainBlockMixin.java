package net.larsmans.infinitybuttons.mixin;

import net.larsmans.infinitybuttons.block.custom.LanternButton;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChainBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ChainBlock.class)
public abstract class ChainBlockMixin extends PillarBlock implements Waterloggable {

    public ChainBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return checkAround(state, world, pos) ? 15 : 0;
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

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        world.updateNeighbors(pos.up(), this);
        super.onBreak(world, pos, state, player);
    }
}
