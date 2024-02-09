package nl.teamdiopside.infinitybuttons.fabric;

import net.fabricmc.api.ModInitializer;
import nl.teamdiopside.infinitybuttons.InfinityButtons;

public class InfinityButtonsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        InfinityButtons.init();
    }
}