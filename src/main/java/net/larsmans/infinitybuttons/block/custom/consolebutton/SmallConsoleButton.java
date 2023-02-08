package net.larsmans.infinitybuttons.block.custom.consolebutton;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class SmallConsoleButton extends ConsoleButton {

    protected static final VoxelShape CEILING_X_SHAPE = createCuboidShape(5, 14, 3, 11, 16, 13);
    protected static final VoxelShape CEILING_Z_SHAPE = createCuboidShape(3, 14, 5, 13, 16, 11);
    protected static final VoxelShape FLOOR_X_SHAPE = createCuboidShape(5, 0, 3, 11, 2, 13);
    protected static final VoxelShape FLOOR_Z_SHAPE = createCuboidShape(3, 0, 5, 13, 2, 11);
    protected static final VoxelShape NORTH_SHAPE = createCuboidShape(3, 5, 14, 13, 11, 16);
    protected static final VoxelShape SOUTH_SHAPE = createCuboidShape(3, 5, 0, 13, 11, 2);
    protected static final VoxelShape WEST_SHAPE = createCuboidShape(14, 5, 3, 16, 11, 13);
    protected static final VoxelShape EAST_SHAPE = createCuboidShape(0, 5, 3, 2, 11, 13);

    public SmallConsoleButton(FabricBlockSettings settings, boolean lever) {
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
