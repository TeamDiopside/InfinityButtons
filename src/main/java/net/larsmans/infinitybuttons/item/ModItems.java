package net.larsmans.infinitybuttons.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.larsmans.infinitybuttons.block.ModBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.WallStandingBlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    /**
     * Torches
     */

    public static final Item TORCH_BUTTON = ModItems.registerItem("torch_button", new WallStandingBlockItem(ModBlocks.TORCH_BUTTON, ModBlocks.WALL_TORCH_BUTTON, new FabricItemSettings().group(ModItemGroup.INFINITYBUTTONS)));

    public static final Item TORCH_LEVER = ModItems.registerItem("torch_lever", new WallStandingBlockItem(ModBlocks.TORCH_LEVER, ModBlocks.WALL_TORCH_LEVER, new FabricItemSettings().group(ModItemGroup.INFINITYBUTTONS)));

    public static final Item SOUL_TORCH_BUTTON = ModItems.registerItem("soul_torch_button", new WallStandingBlockItem(ModBlocks.SOUL_TORCH_BUTTON, ModBlocks.SOUL_WALL_TORCH_BUTTON, new FabricItemSettings().group(ModItemGroup.INFINITYBUTTONS)));

    public static final Item SOUL_TORCH_LEVER = ModItems.registerItem("soul_torch_lever", new WallStandingBlockItem(ModBlocks.SOUL_TORCH_LEVER, ModBlocks.SOUL_WALL_TORCH_LEVER, new FabricItemSettings().group(ModItemGroup.INFINITYBUTTONS)));

    public static final Item REDSTONE_TORCH_BUTTON = ModItems.registerItem("redstone_torch_button", new WallStandingBlockItem(ModBlocks.REDSTONE_TORCH_BUTTON, ModBlocks.REDSTONE_WALL_TORCH_BUTTON, new FabricItemSettings().group(ModItemGroup.INFINITYBUTTONS)));

    public static final Item REDSTONE_TORCH_LEVER = ModItems.registerItem("redstone_torch_lever", new WallStandingBlockItem(ModBlocks.REDSTONE_TORCH_LEVER, ModBlocks.REDSTONE_WALL_TORCH_LEVER, new FabricItemSettings().group(ModItemGroup.INFINITYBUTTONS)));

    /**
     * Methods
     */

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(InfinityButtonsInit.MOD_ID, name), item);
    }

    public static void registerModItems() {
        InfinityButtonsInit.LOGGER.debug("Registering Mod Items for " + InfinityButtonsInit.MOD_ID);
    }
}
