package net.larsmans.infinitybuttons.item;

import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(InfinityButtonsInit.MOD_ID, name), item);
    }

    public static void registerModItems() {
        InfinityButtonsInit.LOGGER.debug("Registering Mod Items for " + InfinityButtonsInit.MOD_ID);
    }
}
