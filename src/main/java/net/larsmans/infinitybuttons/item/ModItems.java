package net.larsmans.infinitybuttons.item;

import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.larsmans.infinitybuttons.block.ModBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.WallStandingBlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item REDSTONE_TORCH_BUTTON = ModItems.registerItem("redstone_torch_button", new WallStandingBlockItem(ModBlocks.REDSTONE_TORCH_BUTTON, ModBlocks.REDSTONE_WALL_TORCH_BUTTON, new Item.Settings().group(ModItemGroup.INFINITYBUTTONS)));

    public static final Item REDSTONE_TORCH_LEVER = ModItems.registerItem("redstone_torch_lever", new WallStandingBlockItem(ModBlocks.REDSTONE_TORCH_LEVER, ModBlocks.REDSTONE_WALL_TORCH_LEVER, new Item.Settings().group(ModItemGroup.INFINITYBUTTONS)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(InfinityButtonsInit.MOD_ID, name), item);
    }

    public static void registerModItems() {
        InfinityButtonsInit.LOGGER.debug("Registering Mod Items for " + InfinityButtonsInit.MOD_ID);
    }
}
