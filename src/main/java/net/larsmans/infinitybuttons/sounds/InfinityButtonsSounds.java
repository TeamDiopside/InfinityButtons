package net.larsmans.infinitybuttons.sounds;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class InfinityButtonsSounds {
    public static SoundEvent ALARM = registerSoundEvent("alarm");
    public static SoundEvent STONE_SCRAPE = registerSoundEvent("stone_scrape");
    public static SoundEvent WOOD_SCRAPE = registerSoundEvent("wood_scrape");
    public static SoundEvent DOORBELL = registerSoundEvent("doorbell");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier("infinitybuttons", name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        System.out.println("Registering Sounds for Infinity Buttons");
    }
}
