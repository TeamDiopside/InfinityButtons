package net.larsmans.infinitybuttons.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class InfinityButtonsParticleTypes {
    public static final DefaultParticleType DIAMOND_SPARKLE = Registry.register(Registry.PARTICLE_TYPE, new Identifier("infinitybuttons", "diamond_sparkle"), FabricParticleTypes.simple());

    public static void register() {
        InfinityButtonsInit.LOGGER.info("Registering Particle Types");
    }
}
