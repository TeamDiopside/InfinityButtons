package net.larsmans.infinitybuttons.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class InfinityButtonsItemGroup {
    public static final ItemGroup INFINITYBUTTONS = FabricItemGroupBuilder.build(new Identifier(InfinityButtonsInit.MOD_ID, "infinitybuttons"), () -> new ItemStack(InfinityButtonsBlocks.OAK_LARGE_BUTTON));
}
