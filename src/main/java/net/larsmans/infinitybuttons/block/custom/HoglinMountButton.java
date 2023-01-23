/*
 * Decompiled with CFR 0.1.1 (FabricMC 57d88659).
 */
package net.larsmans.infinitybuttons.block.custom;

import net.larsmans.infinitybuttons.block.custom.button.AbstractWallButton;
import net.minecraft.block.Block;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.shape.VoxelShape;

public class HoglinMountButton extends AbstractWallButton {

    protected static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(0, 0, 13, 16, 16, 16);
    protected static final VoxelShape EAST_SHAPE =  Block.createCuboidShape(0, 0, 0, 3, 16, 16);
    protected static final VoxelShape SOUTH_SHAPE =  Block.createCuboidShape(0, 0, 0, 16, 16, 3);
    protected static final VoxelShape WEST_SHAPE =  Block.createCuboidShape(13, 0, 0, 16, 16, 16);
    protected static final VoxelShape NORTH_PRESSED_SHAPE = NORTH_SHAPE;
    protected static final VoxelShape EAST_PRESSED_SHAPE = EAST_SHAPE;
    protected static final VoxelShape SOUTH_PRESSED_SHAPE = SOUTH_SHAPE;
    protected static final VoxelShape WEST_PRESSED_SHAPE = WEST_SHAPE;

    public HoglinMountButton (Settings settings) {
        super(settings, NORTH_SHAPE, EAST_SHAPE, SOUTH_SHAPE, WEST_SHAPE, NORTH_PRESSED_SHAPE, EAST_PRESSED_SHAPE, SOUTH_PRESSED_SHAPE, WEST_PRESSED_SHAPE);
    }

    @Override
    public int getPressTicks() {
        return 15;
    }

    @Override
    protected SoundEvent getClickSound(boolean pressed) {
        return pressed ? SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON : SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF;
    }
}
