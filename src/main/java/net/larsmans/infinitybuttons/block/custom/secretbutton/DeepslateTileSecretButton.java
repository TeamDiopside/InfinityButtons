package net.larsmans.infinitybuttons.block.custom.secretbutton;

import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.block.Block;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class DeepslateTileSecretButton extends AbstractSecretButton {
    public DeepslateTileSecretButton(Settings settings, Block jadeBlock) {
        super (
                settings,
                VoxelShapes.union(BASE,
                        Block.createCuboidShape(13, 10, 0, 16, 16, 16),
                        Block.createCuboidShape(0, 10, 3, 13, 16, 19)
                ),
                VoxelShapes.union(BASE,
                        Block.createCuboidShape(0, 10, 13, 16, 16, 16),
                        Block.createCuboidShape(-3, 10, 0, 13, 16, 13)
                ),
                VoxelShapes.union(BASE,
                        Block.createCuboidShape(0, 10, 0, 3, 16, 16),
                        Block.createCuboidShape(3, 10, -3, 16, 16, 13)
                ),
                VoxelShapes.union(BASE,
                        Block.createCuboidShape(0, 10, 0, 16, 16, 3),
                        Block.createCuboidShape(3, 10, 3, 19, 16, 16)
                ),
                Block.createCuboidShape(0, 0, 0, 16, 16, 16),
                jadeBlock
        );
    }

    // The bottom part that never moves
    private static final VoxelShape BASE = Block.createCuboidShape(0, 0, 0, 16, 10, 16);

    @Override
    protected SoundEvent getClickSound(boolean var1) {
        return InfinityButtonsSounds.STONE_SCRAPE;
    }
}
