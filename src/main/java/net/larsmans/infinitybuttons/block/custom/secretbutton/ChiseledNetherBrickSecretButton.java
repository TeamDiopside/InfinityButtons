package net.larsmans.infinitybuttons.block.custom.secretbutton;

import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.block.Block;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class ChiseledNetherBrickSecretButton extends AbstractSecretButton {
    public ChiseledNetherBrickSecretButton(Settings settings, Block jadeBlock) {
        super (
                settings,
                VoxelShapes.union(BOTTOM, TOP,
                        Block.createCuboidShape(13, 3, 0, 16, 13, 16),
                        Block.createCuboidShape(3, 3, 3, 13, 13, 16),
                        Block.createCuboidShape(0, 3, 0, 3, 13, 16)
                ),
                VoxelShapes.union(BOTTOM, TOP,
                        Block.createCuboidShape(0, 3, 13, 16, 13, 16),
                        Block.createCuboidShape(0, 3, 3, 13, 13, 13),
                        Block.createCuboidShape(0, 3, 0, 16, 13, 3)
                ),
                VoxelShapes.union(BOTTOM, TOP,
                        Block.createCuboidShape(0, 3, 0, 3, 13, 16),
                        Block.createCuboidShape(3, 3, 0, 13, 13, 13),
                        Block.createCuboidShape(13, 3, 0, 16, 13, 16)
                ),
                VoxelShapes.union(BOTTOM, TOP,
                        Block.createCuboidShape(0, 3, 0, 16, 13, 3),
                        Block.createCuboidShape(3, 3, 3, 16, 13, 13),
                        Block.createCuboidShape(0, 3, 13, 16, 13, 16)
                ),
                Block.createCuboidShape(0, 0, 0, 16, 16, 16),
                jadeBlock
        );
    }

    // The bottom part that never moves
    private static final VoxelShape BOTTOM = Block.createCuboidShape(0, 0, 0, 16, 3, 16);
    // The top part that never moves
    private static final VoxelShape TOP = Block.createCuboidShape(0, 13, 0, 16, 16, 16);

    @Override
    protected SoundEvent getClickSound(boolean var1) {
        return InfinityButtonsSounds.STONE_SCRAPE;
    }
}
