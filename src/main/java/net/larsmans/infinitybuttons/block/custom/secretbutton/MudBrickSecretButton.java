package net.larsmans.infinitybuttons.block.custom.secretbutton;

import net.minecraft.block.Block;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class MudBrickSecretButton extends AbstractSecretButton {
    public MudBrickSecretButton(Settings settings, Block jadeBlock) {
        super (
                settings,
                VoxelShapes.union(BASE,
                        Block.createCuboidShape(12, 0, 0, 16, 9, 16),
                        Block.createCuboidShape(3, 0, 3, 12, 9, 16),
                        Block.createCuboidShape(0, 0, 0, 3, 9, 16)
                ),
                VoxelShapes.union(BASE,
                        Block.createCuboidShape(0, 0, 12, 16, 9, 16),
                        Block.createCuboidShape(0, 0, 3, 13, 9, 12),
                        Block.createCuboidShape(0, 0, 0, 16, 9, 3)
                ),
                VoxelShapes.union(BASE,
                        Block.createCuboidShape(0, 0, 0, 4, 9, 16),
                        Block.createCuboidShape(3, 0, 0, 13, 9, 13),
                        Block.createCuboidShape(13, 0, 0, 16, 9, 16)
                ),
                VoxelShapes.union(BASE,
                        Block.createCuboidShape(0, 0, 0, 16, 9, 4),
                        Block.createCuboidShape(3, 0, 3, 16, 9, 13),
                        Block.createCuboidShape(0, 0, 13, 16, 9, 16)
                ),
                Block.createCuboidShape(0, 0, 0, 16, 16, 16),
                jadeBlock
        );
    }

    // The top part that never moves
    private static final VoxelShape BASE = Block.createCuboidShape(0, 9, 0, 16, 16, 16);

    @Override
    protected SoundEvent getClickSound(boolean var1) {
        return SoundEvents.BLOCK_MUD_BRICKS_PLACE;
    }
}
