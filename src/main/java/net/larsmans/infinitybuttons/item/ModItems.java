package net.larsmans.infinitybuttons.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.larsmans.infinitybuttons.InfinityButtons;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(InfinityButtons.MOD_ID, name), item);
    }

    public static void registerModItems() {
        InfinityButtons.LOGGER.debug("Registering Mod Items for " + InfinityButtons.MOD_ID);
    }
}
