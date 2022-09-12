package net.larsmans.infinitybuttons.sounds;

import net.larsmans.infinitybuttons.InfinityButtons;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {
    public static SoundEvent ALARM = registerSoundEvent("alarm");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(InfinityButtons.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void registerSounds() {
        System.out.println("Registering ModSounds for " + InfinityButtons.MOD_ID);
    }
}
