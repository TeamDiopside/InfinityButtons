package net.larsmans.infinitybuttons.block.custom.secretbutton.compat;

import net.larsmans.infinitybuttons.block.custom.secretbutton.AbstractSecretButton;
import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class ChiseledEndStoneBrickSecretButton extends AbstractSecretButton {
    public ChiseledEndStoneBrickSecretButton(Properties properties, Block jadeBlock) {
        super(
                properties,
                Shapes.or(BOTTOM, TOP,
                        Block.box(0, 4, 3, 16, 13, 16)),
                Shapes.or(BOTTOM, TOP,
                        Block.box(0, 4, 0, 13, 13, 16)),
                Shapes.or(BOTTOM, TOP,
                        Block.box(0, 4, 0, 16, 13, 13)),
                Shapes.or(BOTTOM, TOP,
                        Block.box(3, 4, 0, 16, 13, 16)),

                Block.box(0, 0, 0, 16, 16, 16),
                jadeBlock
        );
    }

    // The bottom part that never moves
    private static final VoxelShape BOTTOM = Block.box(0, 0, 0, 16, 4, 16);
    // The top part that never moves
    private static final VoxelShape TOP = Block.box(0, 13, 0, 16, 16, 16);

    @Override
    protected SoundEvent getSoundEvent(boolean isOn) {
        return InfinityButtonsSounds.STONE_SCRAPE.get();
    }

    @Override
    public List<ItemStack> getDrops(net.minecraft.world.level.block.state.BlockState state, LootParams.Builder builder) {
        return List.of(new net.minecraft.world.item.ItemStack(this));
    }
}
