package nl.teamdiopside.infinitybuttons.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import nl.teamdiopside.infinitybuttons.InfinityButtons;

@Mod(InfinityButtons.MOD_ID)
public class InfinityButtonsForge {
    public InfinityButtonsForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(InfinityButtons.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        InfinityButtons.init();
    }
}