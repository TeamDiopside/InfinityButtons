package net.larsmans.infinitybuttons.block.custom.button;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractWallButton extends AbstractHorizontalButton {

    protected AbstractWallButton(Settings settings, VoxelShape north_shape, VoxelShape east_shape, VoxelShape south_shape, VoxelShape west_shape, VoxelShape north_pressed_shape, VoxelShape east_pressed_shape, VoxelShape south_pressed_shape, VoxelShape west_pressed_shape) {
        super(settings, north_shape, east_shape, south_shape, west_shape, north_pressed_shape, east_pressed_shape, south_pressed_shape, west_pressed_shape);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = state.get(FACING);
        BlockPos blockPos = pos.offset(direction.getOpposite());
        BlockState blockState = world.getBlockState(blockPos);
        return blockState.isSideSolidFullSquare(world, blockPos, direction);
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction[] directions;
        BlockState blockState = this.getDefaultState();
        World worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        for (Direction direction : directions = ctx.getPlacementDirections()) {
            Direction direction2;
            if (!direction.getAxis().isHorizontal() || !(blockState = blockState.with(FACING, direction2 = direction.getOpposite())).canPlaceAt(worldView, blockPos)) continue;
            return blockState;
        }
        return null;
    }
}
