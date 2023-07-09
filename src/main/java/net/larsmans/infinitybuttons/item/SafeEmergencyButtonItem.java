package net.larsmans.infinitybuttons.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.larsmans.infinitybuttons.advancement.InfinityButtonsTriggers;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class SafeEmergencyButtonItem extends BlockItem {
    public SafeEmergencyButtonItem(Block block, FabricItemSettings settings) {
        super(block, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof ServerPlayerEntity && slot == 3) {
            InfinityButtonsTriggers.SAFETY_TRIGGER.trigger((ServerPlayerEntity) entity);
        }
    }
}
