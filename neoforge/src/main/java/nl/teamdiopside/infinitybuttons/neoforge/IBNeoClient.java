package nl.teamdiopside.infinitybuttons.neoforge;

import dev.architectury.platform.Platform;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import nl.teamdiopside.infinitybuttons.compat.blocks.QuarkBlocks;
import nl.teamdiopside.infinitybuttons.config.IBConfigScreen;
import nl.teamdiopside.infinitybuttons.gui.ConsoleButtonGUI;
import nl.teamdiopside.infinitybuttons.neoforge.compat.MyaliteColorHandler;
import nl.teamdiopside.infinitybuttons.registry.IBMenus;

public class IBNeoClient {
    static void registerClient(IEventBus modEventBus) {
        if (FMLLoader.getDist() != Dist.CLIENT) return;

        modEventBus.addListener(IBNeoClient::registerBlockColorHandlers);
        modEventBus.addListener(IBNeoClient::registerItemColorHandlers);
        modEventBus.addListener(IBNeoClient::registerMenuScreens);

        // Register config screen
        ModLoadingContext.get().registerExtensionPoint(
                IConfigScreenFactory.class,
                () -> (client, parent) ->
                        IBConfigScreen.generateScreen(parent)
        );
    }

    private static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(IBMenus.CONSOLE_INVENTORY.get(), ConsoleButtonGUI::new);
    }

    private static void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event) {
        if (!Platform.isModLoaded(QuarkBlocks.NAMESPACE)) return;
        event.register(MyaliteColorHandler.INSTANCE, QuarkBlocks.MYALITE_BRICK_SECRET_BUTTON.get());
        event.register(MyaliteColorHandler.INSTANCE, QuarkBlocks.CHISELED_MYALITE_BRICK_SECRET_BUTTON.get());

    }

    private static void registerItemColorHandlers(RegisterColorHandlersEvent.Item event) {
        if (!Platform.isModLoaded(QuarkBlocks.NAMESPACE)) return;
        event.register(MyaliteColorHandler.INSTANCE, QuarkBlocks.MYALITE_BRICK_SECRET_BUTTON.get());
        event.register(MyaliteColorHandler.INSTANCE, QuarkBlocks.CHISELED_MYALITE_BRICK_SECRET_BUTTON.get());

    }
}
