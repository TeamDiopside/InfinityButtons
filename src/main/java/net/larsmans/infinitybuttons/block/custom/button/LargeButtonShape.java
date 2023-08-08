package net.larsmans.infinitybuttons.block.custom.button;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;

public abstract class LargeButtonShape extends AbstractButton {

    private static final VoxelShape FLOOR_SHAPE =           Block.createCuboidShape(4, 0, 4, 12, 2, 12);
    private static final VoxelShape FLOOR_PRESSED_SHAPE =   Block.createCuboidShape(4, 0, 4, 12, 1, 12);
    private static final VoxelShape NORTH_SHAPE =           Block.createCuboidShape(4, 4, 14, 12, 12, 16);
    private static final VoxelShape NORTH_PRESSED_SHAPE =   Block.createCuboidShape(4, 4, 15, 12, 12, 16);
    private static final VoxelShape EAST_SHAPE =            Block.createCuboidShape(0, 4, 4, 2, 12, 12);
    private static final VoxelShape EAST_PRESSED_SHAPE =    Block.createCuboidShape(0, 4, 4, 1, 12, 12);
    private static final VoxelShape SOUTH_SHAPE =           Block.createCuboidShape(4, 4, 0, 12, 12, 2);
    private static final VoxelShape SOUTH_PRESSED_SHAPE =   Block.createCuboidShape(4, 4, 0, 12, 12, 1);
    private static final VoxelShape WEST_SHAPE =            Block.createCuboidShape(14, 4, 4, 16, 12, 12);
    private static final VoxelShape WEST_PRESSED_SHAPE =    Block.createCuboidShape(15, 4, 4, 16, 12, 12);
    private static final VoxelShape CEILING_SHAPE =         Block.createCuboidShape(4, 14, 4, 12, 16, 12);
    private static final VoxelShape CEILING_PRESSED_SHAPE = Block.createCuboidShape(4, 15, 4, 12, 16, 12);

    public LargeButtonShape(boolean projectile, FabricBlockSettings settings, boolean large) {
        super(projectile, settings);
    }

    public static VoxelShape outlineShape(BlockState state) {
        Direction direction = state.get(FACING);
        boolean bl = state.get(PRESSED);
        switch (state.get(FACE)) {
            case FLOOR -> {
                return bl ? FLOOR_PRESSED_SHAPE : FLOOR_SHAPE;
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
        return bl ? CEILING_PRESSED_SHAPE : CEILING_SHAPE;
    }
}
