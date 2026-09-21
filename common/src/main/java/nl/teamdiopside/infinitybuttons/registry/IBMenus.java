package nl.teamdiopside.infinitybuttons.registry;

import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import nl.teamdiopside.infinitybuttons.gui.PlainInventoryMenu;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.MOD_ID;

public class IBMenus {
    private static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(MOD_ID, Registries.MENU);

    // Extended: carries the source block's BlockPos to the client when opened, see ConsoleInventoryMenu
    public static final RegistrySupplier<MenuType<PlainInventoryMenu>> CONSOLE_INVENTORY = MENUS.register("console_inventory",
            () -> MenuRegistry.ofExtended(PlainInventoryMenu::new));

    public static void register() {
        MENUS.register();
    }
}
