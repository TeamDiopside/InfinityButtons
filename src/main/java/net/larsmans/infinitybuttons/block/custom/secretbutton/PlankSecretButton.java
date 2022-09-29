package net.larsmans.infinitybuttons.block.custom.secretbutton;

import net.larsmans.infinitybuttons.sounds.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class PlankSecretButton extends AbstractSecretButton {
    public PlankSecretButton(Settings settings) {
        super (
                settings,
                VoxelShapes.union(BOTTOM, TOP,
                        Block.createCuboidShape(0, 4, 3, 16, 9, 16)),
                VoxelShapes.union(BOTTOM, TOP,
                        Block.createCuboidShape(0, 4, 12, 13, 9, 16)),
                VoxelShapes.union(BOTTOM, TOP,
                        Block.createCuboidShape(0, 4, 0, 4, 9, 13)),
                VoxelShapes.union(BOTTOM, TOP,
                        Block.createCuboidShape(3, 4, 0, 16, 9, 16)),

                Block.createCuboidShape(0, 0, 0, 16, 16, 16)
        );
    }

    // The bottom part that never moves
    private static final VoxelShape BOTTOM = Block.createCuboidShape(0, 0, 0, 16, 4, 16);
    // The top part that never moves
    private static final VoxelShape TOP = Block.createCuboidShape(0, 9, 0, 16, 16, 16);

    @Override
    protected SoundEvent getClickSound(boolean var1) {
        return ModSounds.WOOD_SCRAPE;
    }
}
