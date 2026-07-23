package nl.teamdiopside.infinitybuttons;

import nl.teamdiopside.infinitybuttons.compat.IBModdedBlocks;
import nl.teamdiopside.infinitybuttons.registry.*;

import java.util.logging.Logger;

public final class InfinityButtons {
    public static final String MOD_ID = "infinitybuttons";
    public static final Logger LOGGER = Logger.getLogger(MOD_ID);

    public static void init() {
        IBSounds.register();
        IBBlocks.register();
        IBItems.register();

        IBModdedBlocks.register();

        IBCreativeTabs.register();
        IBParticles.register();
        IBAdvancementTriggers.register();
        IBNetworking.register();

        IBConfig.load();
    }

}
