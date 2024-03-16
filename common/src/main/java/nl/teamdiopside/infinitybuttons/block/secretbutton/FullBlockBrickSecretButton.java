package nl.teamdiopside.infinitybuttons.block.secretbutton;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.HorizontalFacingBlock;
import nl.teamdiopside.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.block.Block;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.shape.VoxelShape;

public class FullBlockBrickSecretButton extends AbstractSecretButton {
    public FullBlockBrickSecretButton(Settings settings, Block jadeBlock) {
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
        return InfinityButtonsSounds.STONE_SCRAPE;
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return null;
    }
}
