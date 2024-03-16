package nl.teamdiopside.infinitybuttons.block.secretbutton;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.HorizontalFacingBlock;
import nl.teamdiopside.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.block.Block;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class PlankSecretButton extends AbstractSecretButton {
    public PlankSecretButton(Settings settings, Block jadeBlock) {
        super (
                settings,
                VoxelShapes.union(BOTTOM, TOP,
                        Block.createCuboidShape(0, 4, 3, 16, 9, 16)),
                VoxelShapes.union(BOTTOM, TOP,
                        Block.createCuboidShape(0, 4, 0, 13, 9, 16)),
                VoxelShapes.union(BOTTOM, TOP,
                        Block.createCuboidShape(0, 4, 0, 16, 9, 13)),
                VoxelShapes.union(BOTTOM, TOP,
                        Block.createCuboidShape(3, 4, 0, 16, 9, 16)),

                Block.createCuboidShape(0, 0, 0, 16, 16, 16),
                jadeBlock
        );
    }

    // The bottom part that never moves
    private static final VoxelShape BOTTOM = Block.createCuboidShape(0, 0, 0, 16, 4, 16);
    // The top part that never moves
    private static final VoxelShape TOP = Block.createCuboidShape(0, 9, 0, 16, 16, 16);

    @Override
    protected SoundEvent getClickSound(boolean var1) {
        return InfinityButtonsSounds.WOOD_SCRAPE;
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return null;
    }
}
