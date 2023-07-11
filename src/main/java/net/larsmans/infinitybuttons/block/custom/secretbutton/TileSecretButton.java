package net.larsmans.infinitybuttons.block.custom.secretbutton;

import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.block.Block;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class TileSecretButton extends AbstractSecretButton{
    public TileSecretButton(Settings properties, Block jadeBlock) {
        super(
                properties,
                VoxelShapes.union(BASE,
                        Block.createCuboidShape(0, 8, 0, 8, 16, 16),
                        Block.createCuboidShape(8, 8, 3, 16, 16, 19)
                ),
                VoxelShapes.union(BASE,
                        Block.createCuboidShape(0, 8, 0, 16, 16, 8),
                        Block.createCuboidShape(-3, 8, 8, 13, 16, 16)
                ),
                VoxelShapes.union(BASE,
                        Block.createCuboidShape(8, 8, 0, 16, 16, 16),
                        Block.createCuboidShape(0, 8, -3, 8, 16, 13)
                ),
                VoxelShapes.union(BASE,
                        Block.createCuboidShape(0, 8, 8, 16, 16, 16),
                        Block.createCuboidShape(3, 8, 0, 19, 16, 8)
                ),
                Block.createCuboidShape(0, 0, 0, 16, 16, 16),
                jadeBlock
        );
    }

    private static final VoxelShape BASE = Block.createCuboidShape(0, 0, 0, 16, 8, 16);

    @Override
    protected SoundEvent getClickSound(boolean pressed) {
        return InfinityButtonsSounds.STONE_SCRAPE;
    }
}
