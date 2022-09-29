package net.larsmans.infinitybuttons.sounds;

import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {
    public static SoundEvent ALARM = registerSoundEvent("alarm");
    public static SoundEvent STONE_SCRAPE = registerSoundEvent("stone_scrape");
    public static SoundEvent WOOD_SCRAPE = registerSoundEvent("wood_scrape");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(InfinityButtonsInit.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void registerSounds() {
        System.out.println("Registering ModSounds for " + InfinityButtonsInit.MOD_ID);
    }
}
