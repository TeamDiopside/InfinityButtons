package net.larsmans.infinitybuttons.block.custom.compat;

import net.larsmans.infinitybuttons.block.custom.LanternButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.BigBrickSecretButton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class LanternCompatButton extends LanternButton {
    public LanternCompatButton(Properties properties, boolean isLever, Block jadeBlock) {
        super(properties, isLever, jadeBlock);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        return List.of(new ItemStack(this));
    }
}
