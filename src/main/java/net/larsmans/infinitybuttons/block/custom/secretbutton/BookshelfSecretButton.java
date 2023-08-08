package net.larsmans.infinitybuttons.block.custom.secretbutton;

import net.minecraft.block.Block;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.shape.VoxelShape;

public class BookshelfSecretButton extends AbstractSecretButton {
    public BookshelfSecretButton(Settings settings, Block jadeBlock) {
        super (
                settings,
                FULL,
                FULL,
                FULL,
                FULL,
                FULL,
                jadeBlock
        );
    }

    // Full block because I am too lazy to do the whole voxelshape thing
    private static final VoxelShape FULL = Block.createCuboidShape(0, 0, 0, 16, 16, 16);

    @Override
    protected SoundEvent getClickSound(boolean var1) {
        // Credits to anne for suggesting this immensely satisfying sound
        return SoundEvents.BLOCK_BAMBOO_PLACE;
    }
}
