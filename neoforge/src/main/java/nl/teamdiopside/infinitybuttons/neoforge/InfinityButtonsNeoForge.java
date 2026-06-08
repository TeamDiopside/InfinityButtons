package nl.teamdiopside.infinitybuttons.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import nl.teamdiopside.infinitybuttons.IBConfig;
import nl.teamdiopside.infinitybuttons.InfinityButtons;
import nl.teamdiopside.infinitybuttons.neoforge.datagen.NeoForgeDataGen;

@Mod(InfinityButtons.MOD_ID)
public final class InfinityButtonsNeoForge {
    public InfinityButtonsNeoForge(IEventBus modEventBus) {
        // Run our common setup.
        InfinityButtons.init();

        modEventBus.addListener(NeoForgeDataGen::gatherData);

        // Register config screen
        ModLoadingContext.get().registerExtensionPoint(
                IConfigScreenFactory.class,
                () -> (client, parent) ->
                        IBConfig.HANDLER.instance().generateScreen(parent)
        );
    }
}
