package net.larsmans.infinitybuttons.block.custom.secretbutton.compat;

import net.larsmans.infinitybuttons.block.custom.secretbutton.AbstractSecretButton;
import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class EumusBrickSecretButton extends AbstractSecretButton {
    public EumusBrickSecretButton(Properties properties, Block jadeBlock) {
        super(
                properties,
                Shapes.or(BOTTOM, TOP,
                        Block.box(10, 5, 0, 16, 12, 16),
                        Block.box(0, 5, 3, 10, 12, 16)
                ),
                Shapes.or(BOTTOM, TOP,
                        Block.box(0, 5, 10, 16, 12, 16),
                        Block.box(0, 5, 0, 13, 12, 10)
                ),
                Shapes.or(BOTTOM, TOP,
                        Block.box(0, 5, 0, 6, 12, 16),
                        Block.box(6, 5, 0, 16, 12, 13)
                ),
                Shapes.or(BOTTOM, TOP,
                        Block.box(0, 5, 0, 16, 12, 6),
                        Block.box(3, 5, 6, 16, 12, 16)
                ),
                Block.box(0, 0, 0, 16, 16, 16),
                jadeBlock
        );
    }

    // The bottom part that never moves
    private static final VoxelShape BOTTOM = Block.box(0, 0, 0, 16, 5, 16);
    // The top part that never moves
    private static final VoxelShape TOP = Block.box(0, 12, 0, 16, 16, 16);

    @Override
    protected SoundEvent getSoundEvent(boolean isOn) {
        return InfinityButtonsSounds.STONE_SCRAPE.get();
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        return List.of(new ItemStack(this));
    }
}
