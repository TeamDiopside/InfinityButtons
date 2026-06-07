package nl.teamdiopside.infinitybuttons.neoforge;

import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import nl.teamdiopside.infinitybuttons.IBConfig;
import nl.teamdiopside.infinitybuttons.InfinityButtons;

@Mod(InfinityButtons.MOD_ID)
public final class InfinityButtonsNeoForge {
    public InfinityButtonsNeoForge() {
        // Run our common setup.
        InfinityButtons.init();

        // Register config screen
        ModLoadingContext.get().registerExtensionPoint(
                IConfigScreenFactory.class,
                () -> (client, parent) ->
                        IBConfig.HANDLER.instance().generateScreen(parent)
        );
    }
}
