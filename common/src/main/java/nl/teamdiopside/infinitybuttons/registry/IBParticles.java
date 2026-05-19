package nl.teamdiopside.infinitybuttons.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.MOD_ID;

public class IBParticles {
    private static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(MOD_ID, Registries.PARTICLE_TYPE);

    public static final RegistrySupplier<SimpleParticleType> DIAMOND_SPARKLE =
            PARTICLES.register(
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "diamond_sparkle"),
                    () -> new SimpleParticleType(false) {}
            );

    public static void register() {
        PARTICLES.register();
    }
}