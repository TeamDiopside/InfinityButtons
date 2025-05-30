package net.larsmans.infinitybuttons.compat;

import net.larsmans.infinitybuttons.InfinityButtons;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static net.larsmans.infinitybuttons.item.InfinityButtonsItems.ITEMS;

public class EndergeticItems {

    public static final RegistryObject<Item> ENDER_TORCH_BUTTON = registerItem("ender_torch_button",
            () -> new StandingAndWallBlockItem(EndergeticBlocks.ENDER_TORCH_BUTTON.get(), EndergeticBlocks.ENDER_WALL_TORCH_BUTTON.get(), new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> ENDER_TORCH_LEVER = registerItem("ender_torch_lever",
            () -> new StandingAndWallBlockItem(EndergeticBlocks.ENDER_TORCH_LEVER.get(), EndergeticBlocks.ENDER_WALL_TORCH_LEVER.get(), new Item.Properties(), Direction.DOWN));

    /**
     * Methods
     */

    private static <T extends Item> RegistryObject<Item> registerItem(String name, Supplier<T> item) {
        return ITEMS.register(name, item);
    }

    public static void registerCompatItems() {
        InfinityButtons.LOGGER.debug("Registering Endergetic Compat Items for Infinity Buttons");
    }
}
