package nl.teamdiopside.infinitybuttons.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.fabric.impl.resource.loader.ResourceManagerHelperImpl;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import nl.teamdiopside.infinitybuttons.InfinityButtons;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.LOGGER;
import static nl.teamdiopside.infinitybuttons.InfinityButtons.MOD_ID;

public final class InfinityButtonsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        InfinityButtons.init();

        var container = FabricLoader.getInstance()
                .getModContainer("infinitybuttons")
                .orElseThrow();

        //noinspection UnstableApiUsage
        boolean registered = ResourceManagerHelperImpl.registerBuiltinResourcePack(
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "more_buttons"),
                "datapacks/more_buttons",
                container,
                ResourcePackActivationType.DEFAULT_ENABLED
        );
        if (registered) {
            LOGGER.info("Successfully registered more buttons datapack!");
        } else {
            LOGGER.warning("Failed to register more buttons datapack!");
        }
    }
}
