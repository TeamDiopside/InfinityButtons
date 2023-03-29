/*
 * Decompiled with CFR 0.1.1 (FabricMC 57d88659).
 */
package net.larsmans.infinitybuttons.block.custom.button;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public abstract class AbstractSmallButton extends AbstractButton {

    protected static final VoxelShape CEILING_X_SHAPE = Block.createCuboidShape(6, 14, 5, 10, 16, 11);
    protected static final VoxelShape CEILING_Z_SHAPE = Block.createCuboidShape(5, 14, 6, 11, 16, 10);
    protected static final VoxelShape FLOOR_X_SHAPE = Block.createCuboidShape(6, 0, 5, 10, 2, 11);
    protected static final VoxelShape FLOOR_Z_SHAPE = Block.createCuboidShape(5, 0, 6, 11, 2, 10);
    protected static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(5, 6, 14, 11, 10, 16);
    protected static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(5, 6, 0, 11, 10, 2);
    protected static final VoxelShape WEST_SHAPE = Block.createCuboidShape(14, 6, 5, 16, 10, 11);
    protected static final VoxelShape EAST_SHAPE = Block.createCuboidShape(0, 6, 5, 2, 10, 11);
    protected static final VoxelShape CEILING_X_PRESSED_SHAPE = Block.createCuboidShape(6, 15, 5, 10, 16, 11);
    protected static final VoxelShape CEILING_Z_PRESSED_SHAPE = Block.createCuboidShape(5, 15, 6, 11, 16, 10);
    protected static final VoxelShape FLOOR_X_PRESSED_SHAPE = Block.createCuboidShape(6, 0, 5, 10, 1, 11);
    protected static final VoxelShape FLOOR_Z_PRESSED_SHAPE = Block.createCuboidShape(5, 0, 6, 11, 1, 10);
    protected static final VoxelShape NORTH_PRESSED_SHAPE = Block.createCuboidShape(5, 6, 15, 11, 10, 16);
    protected static final VoxelShape SOUTH_PRESSED_SHAPE = Block.createCuboidShape(5, 6, 0, 11, 10, 1);
    protected static final VoxelShape WEST_PRESSED_SHAPE = Block.createCuboidShape(15, 6, 5, 16, 10, 11);
    protected static final VoxelShape EAST_PRESSED_SHAPE = Block.createCuboidShape(0, 6, 5, 1, 10, 11);

    private final boolean large;

    protected AbstractSmallButton(boolean projectile, boolean large, FabricBlockSettings settings) {
        super(projectile, settings);
        this.large = large;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (large) {
            return LargeButtonShape.outlineShape(state);
        }
        Direction direction = state.get(FACING);
        boolean bl = state.get(PRESSED);
        switch (state.get(FACE)) {
            case FLOOR -> {
                if (direction.getAxis() == Direction.Axis.X) {
                    return bl ? FLOOR_X_PRESSED_SHAPE : FLOOR_X_SHAPE;
                }
                return bl ? FLOOR_Z_PRESSED_SHAPE : FLOOR_Z_SHAPE;
            }
            case WALL -> {
                switch (direction) {
                    case EAST -> {
                        return bl ? EAST_PRESSED_SHAPE : EAST_SHAPE;
                    }
                    case WEST -> {
                        return bl ? WEST_PRESSED_SHAPE : WEST_SHAPE;
                    }
                    case SOUTH -> {
                        return bl ? SOUTH_PRESSED_SHAPE : SOUTH_SHAPE;
                    }
                }
                return bl ? NORTH_PRESSED_SHAPE : NORTH_SHAPE;
            }
        }
        if (direction.getAxis() == Direction.Axis.X) {
            return bl ? CEILING_X_PRESSED_SHAPE : CEILING_X_SHAPE;
        }
        return bl ? CEILING_Z_PRESSED_SHAPE : CEILING_Z_SHAPE;
    }
}

