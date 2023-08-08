package net.larsmans.infinitybuttons.block.custom;

import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.larsmans.infinitybuttons.block.custom.button.AbstractWallButton;
import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DoorbellButton extends AbstractWallButton {

    protected static final VoxelShape NORTH_PRESSED_SHAPE =
            Block.createCuboidShape(6, 4, 14, 10, 12, 16);
    protected static final VoxelShape EAST_PRESSED_SHAPE =
            Block.createCuboidShape(0, 4, 6, 2, 12, 10);
    protected static final VoxelShape SOUTH_PRESSED_SHAPE =
            Block.createCuboidShape(6, 4, 0, 10, 12, 2);
    protected static final VoxelShape WEST_PRESSED_SHAPE =
            Block.createCuboidShape(14, 4, 6, 16, 12, 10);
    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.union(NORTH_PRESSED_SHAPE,
            Block.createCuboidShape(7, 6, 13, 9, 10, 14));
    protected static final VoxelShape EAST_SHAPE = VoxelShapes.union(EAST_PRESSED_SHAPE,
            Block.createCuboidShape(2, 6, 7, 3, 10, 9));
    protected static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(SOUTH_PRESSED_SHAPE,
            Block.createCuboidShape(7, 6, 2, 9, 10, 3));
    protected static final VoxelShape WEST_SHAPE = VoxelShapes.union(WEST_PRESSED_SHAPE,
            Block.createCuboidShape(13, 6, 7, 14, 10, 9));

    public DoorbellButton (Settings settings) {
        super(settings, NORTH_SHAPE, EAST_SHAPE, SOUTH_SHAPE, WEST_SHAPE, NORTH_PRESSED_SHAPE, EAST_PRESSED_SHAPE, SOUTH_PRESSED_SHAPE, WEST_PRESSED_SHAPE);
    }

    @Override
    public int getPressTicks() {
        return 15;
    }

    @Override
    protected void playClickSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, boolean pressed) {
        world.playSound(pressed ? player : null, pos, this.getClickSound(pressed), SoundCategory.BLOCKS, 0.3f, 1f);
    }

    @Override
    protected SoundEvent getClickSound(boolean pressed) {
        return InfinityButtonsSounds.DOORBELL;
    }

    // no unpressed sound
    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(PRESSED)) {
            world.setBlockState(pos, state.with(PRESSED, false), Block.NOTIFY_ALL);
            this.updateNeighbors(state, world, pos);
            world.emitGameEvent(null, GameEvent.BLOCK_DEACTIVATE, pos);
        }
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return state;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        InfinityButtonsUtil.tooltip(tooltip, "doorbell_button");
    }
}
