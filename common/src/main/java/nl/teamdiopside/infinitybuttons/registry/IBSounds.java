package nl.teamdiopside.infinitybuttons.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import nl.teamdiopside.diopside.registry.SoundEventEntryBuilder;
import nl.teamdiopside.infinitybuttons.InfinityButtons;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.LOGGER;

public class IBSounds {
    // TODO fix subtitles (maybe making it secret? for the blocks we use vanilla events, like mud, subtitle says block placed)
    // if we don't want it to be secret we can make our own event, give it the right subtitle but still reference the vanilla sound in sounds.json
    public static RegistrySupplier<SoundEvent> ALARM = registerSoundEvent("block.emergency_button.alarm");
    public static RegistrySupplier<SoundEvent> STONE_SCRAPE = registerSoundEvent("block.secret_button.stone_scrape");
    public static RegistrySupplier<SoundEvent> WOOD_SCRAPE = registerSoundEvent("block.secret_button.wood_scrape");
    public static RegistrySupplier<SoundEvent> DOORBELL = registerSoundEvent("block.doorbell.doorbell");
    public static RegistrySupplier<SoundEvent> CONSOLE_BEEP = registerSoundEvent("block.console_button.beep");
    public static RegistrySupplier<SoundEvent> CONSOLE_UNBEEP = registerSoundEvent("block.console_button.unbeep");
    public static RegistrySupplier<SoundEvent> CONSOLE_ERROR = registerSoundEvent("block.console_button.error");
    public static RegistrySupplier<SoundEvent> SILENT = registerSoundEvent("block.silent");

    private static RegistrySupplier<SoundEvent> registerSoundEvent(String name) {
        return SoundEventEntryBuilder.create().register(ResourceLocation.fromNamespaceAndPath(InfinityButtons.MOD_ID, name));
    }

    public static void register() {
        LOGGER.info("Registering Sounds for Infinity Buttons");
    }
}
