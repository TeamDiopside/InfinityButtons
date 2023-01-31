package net.larsmans.infinitybuttons.compat;

import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.larsmans.infinitybuttons.item.InfinityButtonsItems;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class IBNethersDelightItems {

    public static final Item PROPELPLANT_TORCH_BUTTON = InfinityButtonsItems.registerTorch("propelplant_torch_button", IBNethersDelightBlocks.PROPELPLANT_TORCH_BUTTON, IBNethersDelightBlocks.PROPELPLANT_WALL_TORCH_BUTTON);

    public static final Item PROPELPLANT_TORCH_LEVER = InfinityButtonsItems.registerTorch("propelplant_torch_lever", IBNethersDelightBlocks.PROPELPLANT_TORCH_LEVER, IBNethersDelightBlocks.PROPELPLANT_WALL_TORCH_LEVER);

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier("infinitybuttons", name), item);
    }

    public static void registerCompatItems() {
        InfinityButtonsInit.LOGGER.debug("Registering Compat Items for Infinity Buttons");
    }
}
