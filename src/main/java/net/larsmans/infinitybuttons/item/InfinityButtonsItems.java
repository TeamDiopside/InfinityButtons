package net.larsmans.infinitybuttons.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.WallStandingBlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class InfinityButtonsItems {

    /**
     * Torches
     */

    public static final Item TORCH_BUTTON = InfinityButtonsItems.registerItem("torch_button", new WallStandingBlockItem(InfinityButtonsBlocks.TORCH_BUTTON, InfinityButtonsBlocks.WALL_TORCH_BUTTON, new FabricItemSettings().group(InfinityButtonsItemGroup.INFINITYBUTTONS)));

    public static final Item TORCH_LEVER = InfinityButtonsItems.registerItem("torch_lever", new WallStandingBlockItem(InfinityButtonsBlocks.TORCH_LEVER, InfinityButtonsBlocks.WALL_TORCH_LEVER, new FabricItemSettings().group(InfinityButtonsItemGroup.INFINITYBUTTONS)));

    public static final Item SOUL_TORCH_BUTTON = InfinityButtonsItems.registerItem("soul_torch_button", new WallStandingBlockItem(InfinityButtonsBlocks.SOUL_TORCH_BUTTON, InfinityButtonsBlocks.SOUL_WALL_TORCH_BUTTON, new FabricItemSettings().group(InfinityButtonsItemGroup.INFINITYBUTTONS)));

    public static final Item SOUL_TORCH_LEVER = InfinityButtonsItems.registerItem("soul_torch_lever", new WallStandingBlockItem(InfinityButtonsBlocks.SOUL_TORCH_LEVER, InfinityButtonsBlocks.SOUL_WALL_TORCH_LEVER, new FabricItemSettings().group(InfinityButtonsItemGroup.INFINITYBUTTONS)));

    public static final Item REDSTONE_TORCH_BUTTON = InfinityButtonsItems.registerItem("redstone_torch_button", new WallStandingBlockItem(InfinityButtonsBlocks.REDSTONE_TORCH_BUTTON, InfinityButtonsBlocks.REDSTONE_WALL_TORCH_BUTTON, new FabricItemSettings().group(InfinityButtonsItemGroup.INFINITYBUTTONS)));

    public static final Item REDSTONE_TORCH_LEVER = InfinityButtonsItems.registerItem("redstone_torch_lever", new WallStandingBlockItem(InfinityButtonsBlocks.REDSTONE_TORCH_LEVER, InfinityButtonsBlocks.REDSTONE_WALL_TORCH_LEVER, new FabricItemSettings().group(InfinityButtonsItemGroup.INFINITYBUTTONS)));

    /**
     * Methods
     */

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(InfinityButtonsInit.MOD_ID, name), item);
    }

    public static void registerModItems() {
        InfinityButtonsInit.LOGGER.debug("Registering Mod Items for Infinity Buttons");
    }
}
