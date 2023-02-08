package net.larsmans.infinitybuttons.block.custom.consolebutton;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.larsmans.infinitybuttons.block.custom.button.AbstractLeverableButton;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class ConsoleButton extends AbstractLeverableButton {

    protected static final VoxelShape CEILING_X_SHAPE = Block.createCuboidShape(3, 14, 4, 13, 16, 12);
    protected static final VoxelShape CEILING_Z_SHAPE = Block.createCuboidShape(4, 14, 3, 12, 16, 13);
    protected static final VoxelShape FLOOR_X_SHAPE = Block.createCuboidShape(3, 0, 4, 13, 2, 12);
    protected static final VoxelShape FLOOR_Z_SHAPE = Block.createCuboidShape(4, 0, 3, 12, 2, 13);
    protected static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(4, 3, 14, 12, 13, 16);
    protected static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(4, 3, 0, 12, 13, 2);
    protected static final VoxelShape WEST_SHAPE = Block.createCuboidShape(14, 3, 4, 16, 13, 12);
    protected static final VoxelShape EAST_SHAPE = Block.createCuboidShape(0, 3, 4, 2, 13, 12);

    public ConsoleButton(FabricBlockSettings settings, boolean lever) {
        super(lever, settings);
    }

    @Override
    public int getPressTicks() {
        return 60;
    }

    @Override
    protected SoundEvent getClickSound(boolean pressed) {
        return SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON;
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
