package nl.teamdiopside.infinitybuttons.block.secretbutton;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.HorizontalFacingBlock;
import nl.teamdiopside.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.block.Block;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class BigBrickSecretButton extends AbstractSecretButton {
    public BigBrickSecretButton(Settings settings, Block jadeBlock) {
        super (
                settings,
                VoxelShapes.union(BASE,
                        Block.createCuboidShape(0, 8, 3, 16, 16, 19)
                ),
                VoxelShapes.union(BASE,
                        Block.createCuboidShape(-3, 8, 0, 13, 16, 16)
                ),
                VoxelShapes.union(BASE,
                        Block.createCuboidShape(0, 8, -3, 16, 16, 13)
                ),
                VoxelShapes.union(BASE,
                        Block.createCuboidShape(3, 8, 0, 19, 16, 16)
                ),
                Block.createCuboidShape(0, 0, 0, 16, 16, 16),
                jadeBlock
        );
    }

    // The bottom part that doesn't move
    private static final VoxelShape BASE = Block.createCuboidShape(0, 0, 0, 16, 8, 16);

    @Override
    protected SoundEvent getClickSound(boolean var1) {
        return InfinityButtonsSounds.STONE_SCRAPE;
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return null;
    }
}
