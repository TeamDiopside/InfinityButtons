package net.larsmans.infinitybuttons.compat;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.larsmans.infinitybuttons.item.InfinityButtonsItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.WallStandingBlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class IBNethersDelightItems {

    public static final Item PROPELPLANT_TORCH_BUTTON = registerItem("propelplant_torch_button", new WallStandingBlockItem(IBNethersDelightBlocks.PROPELPLANT_TORCH_BUTTON, IBNethersDelightBlocks.PROPELPLANT_WALL_TORCH_BUTTON, new FabricItemSettings().group(InfinityButtonsItemGroup.INFINITYBUTTONS)));

    public static final Item PROPELPLANT_TORCH_LEVER = registerItem("propelplant_torch_lever", new WallStandingBlockItem(IBNethersDelightBlocks.PROPELPLANT_TORCH_LEVER, IBNethersDelightBlocks.PROPELPLANT_WALL_TORCH_LEVER, new FabricItemSettings().group(InfinityButtonsItemGroup.INFINITYBUTTONS)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier("infinitybuttons", name), item);
    }

    public static void registerCompatItems() {
        InfinityButtonsInit.LOGGER.debug("Registering Compat Items for Infinity Buttons");
    }
}
