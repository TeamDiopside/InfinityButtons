package nl.teamdiopside.infinitybuttons.registry;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.LOGGER;

public class IBSounds {
    public static SoundEvent ALARM = registerSoundEvent("alarm");
    public static SoundEvent STONE_SCRAPE = registerSoundEvent("stone_scrape");
    public static SoundEvent WOOD_SCRAPE = registerSoundEvent("wood_scrape");
    public static SoundEvent DOORBELL = registerSoundEvent("doorbell");

    private static SoundEvent registerSoundEvent(String name) {
//        Identifier id = new Identifier(InfinityButtons.MOD_ID, name);
//        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
        return SoundEvents.IRON_GOLEM_DAMAGE;

        // TODO: Replace with Diopside registry method?
        // Something very curious: Even with this placeholder, buttons already seem to play the right sound somehow.
    }

    public static void register() {
        LOGGER.info("Registering Sounds for Infinity Buttons");
    }
}
