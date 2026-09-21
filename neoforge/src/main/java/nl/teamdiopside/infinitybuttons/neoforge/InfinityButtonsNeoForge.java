package nl.teamdiopside.infinitybuttons.neoforge;

import dev.architectury.platform.Platform;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import nl.teamdiopside.infinitybuttons.IBConfig;
import nl.teamdiopside.infinitybuttons.InfinityButtons;
import nl.teamdiopside.infinitybuttons.compat.blocks.QuarkBlocks;
import nl.teamdiopside.infinitybuttons.gui.ConsoleButtonGUI;
import nl.teamdiopside.infinitybuttons.neoforge.compat.MyaliteColorHandler;
import nl.teamdiopside.infinitybuttons.neoforge.datagen.NeoForgeDataGen;
import nl.teamdiopside.infinitybuttons.registry.IBMenus;

@Mod(InfinityButtons.MOD_ID)
public final class InfinityButtonsNeoForge {
    public InfinityButtonsNeoForge(IEventBus modEventBus) {
        // Run our common setup.
        InfinityButtons.init();

        modEventBus.addListener(NeoForgeDataGen::gatherData);
        modEventBus.addListener(this::registerBlockColorHandlers);
        modEventBus.addListener(this::registerItemColorHandlers);
        modEventBus.addListener(this::registerMenuScreens);

        // Register config screen
        ModLoadingContext.get().registerExtensionPoint(
                IConfigScreenFactory.class,
                () -> (client, parent) ->
                        IBConfig.HANDLER.instance().generateScreen(parent)
        );
    }

    private void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(IBMenus.CONSOLE_INVENTORY.get(), ConsoleButtonGUI::new);
    }

    private void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event) {
        if (!Platform.isModLoaded(QuarkBlocks.NAMESPACE)) return;
        event.register(MyaliteColorHandler.INSTANCE, QuarkBlocks.MYALITE_BRICK_SECRET_BUTTON.get());
        event.register(MyaliteColorHandler.INSTANCE, QuarkBlocks.CHISELED_MYALITE_BRICK_SECRET_BUTTON.get());

    }

    private void registerItemColorHandlers(RegisterColorHandlersEvent.Item event) {
        if (!Platform.isModLoaded(QuarkBlocks.NAMESPACE)) return;
        event.register(MyaliteColorHandler.INSTANCE, QuarkBlocks.MYALITE_BRICK_SECRET_BUTTON.get());
        event.register(MyaliteColorHandler.INSTANCE, QuarkBlocks.CHISELED_MYALITE_BRICK_SECRET_BUTTON.get());

    }
}
