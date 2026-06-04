package nl.teamdiopside.infinitybuttons;

import net.minecraft.resources.ResourceLocation;
import nl.teamdiopside.infinitybuttons.registry.*;

import java.util.logging.Logger;

public final class InfinityButtons {
    public static final String MOD_ID = "infinitybuttons";
    public static final Logger LOGGER = Logger.getLogger(MOD_ID);

    public static void init() {
        IBSounds.register();
        IBBlocks.register();
        IBItems.register();
        IBCreativeTabs.register();
        IBParticles.register();
        IBNetworking.register();
        IBConfig.register();
    }

    public static ResourceLocation getResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(InfinityButtons.MOD_ID, path);
    }
}
