package nl.teamdiopside.infinitybuttons;

import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import nl.teamdiopside.infinitybuttons.registry.IBItems;
import nl.teamdiopside.infinitybuttons.registry.IBParticles;

import java.util.logging.Logger;

public final class InfinityButtons {
    public static final String MOD_ID = "infinitybuttons";
    public static final Logger LOGGER = Logger.getLogger(MOD_ID);

    public static void init() {
        IBBlocks.register();
        IBItems.register();
        IBParticles.register();
    }
}
