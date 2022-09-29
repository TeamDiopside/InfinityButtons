package net.larsmans.infinitybuttons.block.custom.secretbutton;

import net.larsmans.infinitybuttons.sounds.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.shape.VoxelShape;

public class FullBlockBrickSecretButton extends AbstractSecretButton {
    public FullBlockBrickSecretButton(Settings settings) {
        super (
                settings,
                FULL,
                FULL,
                FULL,
                FULL,
                FULL
        );
    }

    // Full block because I am too lazy to do the whole voxelshape thing
    private static final VoxelShape FULL = Block.createCuboidShape(0, 0, 0, 16, 16, 16);

    @Override
    protected SoundEvent getClickSound(boolean var1) {
        return ModSounds.STONE_SCRAPE;
    }
}
