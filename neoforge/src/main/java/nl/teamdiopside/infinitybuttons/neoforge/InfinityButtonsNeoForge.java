package nl.teamdiopside.infinitybuttons.neoforge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import nl.teamdiopside.infinitybuttons.InfinityButtons;
import nl.teamdiopside.infinitybuttons.neoforge.datagen.NeoForgeDataGen;

@Mod(InfinityButtons.MOD_ID)
public final class InfinityButtonsNeoForge {
    public InfinityButtonsNeoForge(IEventBus modEventBus) {
        // Run our common setup.
        InfinityButtons.init();

        modEventBus.addListener(NeoForgeDataGen::gatherData);

        if (FMLLoader.getDist() != Dist.CLIENT) return;
        IBNeoClient.registerClient(modEventBus);
    }
}
