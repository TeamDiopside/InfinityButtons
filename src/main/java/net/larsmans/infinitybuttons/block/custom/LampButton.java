package net.larsmans.infinitybuttons.block.custom;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.larsmans.infinitybuttons.block.custom.button.AbstractButton;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class LampButton extends AbstractButton {
    public LampButton(FabricBlockSettings settings) {
        super(false, settings);
    }

    @Override
    public int getPressTicks() {
        return 20;
    }

    @Override
    protected SoundEvent getClickSound(boolean pressed) {
        return pressed ? SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON : SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF;
    }

    private static final VoxelShape FLOOR_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(3, 0, 3, 13, 1, 13),
            Block.createCuboidShape(4, 1, 4, 12, 8, 12));
    private static final VoxelShape CEILING_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(3, 15, 3, 13, 16, 13),
            Block.createCuboidShape(4, 8, 4, 12, 15, 12));
    private static final VoxelShape NORTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(3, 3, 15, 13, 13, 16),
            Block.createCuboidShape(4, 4, 8, 12, 12, 15));
    private static final VoxelShape EAST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0, 3, 3, 1, 13, 13),
            Block.createCuboidShape(1, 4, 4, 8, 12, 12));
    private static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(3, 3, 0, 13, 13, 1),
            Block.createCuboidShape(4, 4, 1, 12, 12, 8));
    private static final VoxelShape WEST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(15, 3, 3, 16, 13, 13),
            Block.createCuboidShape(8, 4, 4, 15, 12, 12));

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        switch (state.get(FACE)) {
            case FLOOR -> {
                return FLOOR_SHAPE;
            }
            case WALL -> {
                switch (direction) {
                    case NORTH -> {
                        return NORTH_SHAPE;
                    }
                    case EAST -> {
                        return EAST_SHAPE;
                    }
                    case SOUTH -> {
                        return SOUTH_SHAPE;
                    }
                }
                return WEST_SHAPE;
            }
        }
        return CEILING_SHAPE;
    }
}
