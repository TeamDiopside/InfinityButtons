package net.larsmans.infinitybuttons.block.custom.secretbutton;

import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.block.Block;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class ChiseledStoneBrickSecretButton extends AbstractSecretButton {
    public ChiseledStoneBrickSecretButton(Settings settings, Block jadeBlock) {
        super (
                settings,
                VoxelShapes.union(BOTTOM, TOP,
                        Block.createCuboidShape(14, 3, 0, 16, 14, 16),
                        Block.createCuboidShape(3, 3, 3, 14, 14, 16),
                        Block.createCuboidShape(0, 3, 0, 3, 14, 16)
                ),
                VoxelShapes.union(BOTTOM, TOP,
                        Block.createCuboidShape(0, 3, 14, 16, 14, 16),
                        Block.createCuboidShape(0, 3, 3, 13, 14, 14),
                        Block.createCuboidShape(0, 3, 0, 16, 14, 3)
                ),
                VoxelShapes.union(BOTTOM, TOP,
                        Block.createCuboidShape(0, 3, 0, 2, 14, 16),
                        Block.createCuboidShape(2, 3, 0, 13, 14, 13),
                        Block.createCuboidShape(13, 3, 0, 16, 14, 16)
                ),
                VoxelShapes.union(BOTTOM, TOP,
                        Block.createCuboidShape(0, 3, 0, 16, 14, 2),
                        Block.createCuboidShape(3, 3, 2, 16, 14, 13),
                        Block.createCuboidShape(0, 3, 13, 16, 14, 16)
                ),
                Block.createCuboidShape(0, 0, 0, 16, 16, 16),
                jadeBlock
        );
    }

    // The bottom part that never moves
    private static final VoxelShape BOTTOM = Block.createCuboidShape(0, 0, 0, 16, 3, 16);
    // The top part that never moves
    private static final VoxelShape TOP = Block.createCuboidShape(0, 14, 0, 16, 16, 16);

    @Override
    protected SoundEvent getClickSound(boolean var1) {
        return InfinityButtonsSounds.STONE_SCRAPE;
    }
}
