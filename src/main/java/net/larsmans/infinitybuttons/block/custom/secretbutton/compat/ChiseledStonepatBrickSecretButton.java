package net.larsmans.infinitybuttons.block.custom.secretbutton.compat;

import net.larsmans.infinitybuttons.block.custom.secretbutton.ChiseledStoneBrickSecretButton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChiseledStonepatBrickSecretButton extends ChiseledStoneBrickSecretButton {
    public ChiseledStonepatBrickSecretButton(Properties properties, Block jadeBlock) {
        super(properties, jadeBlock);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        return new ArrayList<>(Collections.singleton(new ItemStack(this)));
    }
}
