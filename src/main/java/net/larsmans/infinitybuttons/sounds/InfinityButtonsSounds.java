package net.larsmans.infinitybuttons.sounds;

import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class InfinityButtonsSounds {
    public static SoundEvent ALARM = registerSoundEvent("alarm");
    public static SoundEvent STONE_SCRAPE = registerSoundEvent("stone_scrape");
    public static SoundEvent WOOD_SCRAPE = registerSoundEvent("wood_scrape");
    public static SoundEvent DOORBELL = registerSoundEvent("doorbell");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(InfinityButtonsInit.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void registerSounds() {
        System.out.println("Registering InfinityButtonsSounds for " + InfinityButtonsInit.MOD_ID);
    }
}
