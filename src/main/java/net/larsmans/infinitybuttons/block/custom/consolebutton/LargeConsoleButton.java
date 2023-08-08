package net.larsmans.infinitybuttons.block.custom.consolebutton;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class LargeConsoleButton extends ConsoleButton {

    protected static final VoxelShape CEILING_X_SHAPE = Block.createCuboidShape(3, 14, 1, 13, 16, 15);
    protected static final VoxelShape CEILING_Z_SHAPE = Block.createCuboidShape(1, 14, 3, 15, 16, 13);
    protected static final VoxelShape FLOOR_X_SHAPE = Block.createCuboidShape(3, 0, 1, 13, 2, 15);
    protected static final VoxelShape FLOOR_Z_SHAPE = Block.createCuboidShape(1, 0, 3, 15, 2, 13);
    protected static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(1, 3, 14, 15, 13, 16);
    protected static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(1, 3, 0, 15, 13, 2);
    protected static final VoxelShape WEST_SHAPE = Block.createCuboidShape(14, 3, 1, 16, 13, 15);
    protected static final VoxelShape EAST_SHAPE = Block.createCuboidShape(0, 3, 1, 2, 13, 15);

    public LargeConsoleButton(FabricBlockSettings settings, boolean lever) {
        super(settings, lever);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        switch (state.get(FACE)) {
            case FLOOR -> {
                if (direction.getAxis() == Direction.Axis.X) {
                    return FLOOR_X_SHAPE;
                }
                return FLOOR_Z_SHAPE;
            }
            case WALL -> {
                switch (direction) {
                    case EAST -> {
                        return EAST_SHAPE;
                    }
                    case WEST -> {
                        return WEST_SHAPE;
                    }
                    case SOUTH -> {
                        return SOUTH_SHAPE;
                    }
                }
                return NORTH_SHAPE;
            }
        }
        if (direction.getAxis() == Direction.Axis.X) {
            return CEILING_X_SHAPE;
        }
        return CEILING_Z_SHAPE;
    }
}
