package net.larsmans.infinitybuttons.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class DiamondSparkleParticle extends SpriteBillboardParticle {

    protected DiamondSparkleParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider) {
        super(world, x, y, z);
        this.velocityMultiplier = 0.96f;
        this.field_28787 = true;
        this.scale *= 0.75f;
        this.collidesWithWorld = false;
        this.setSpriteForAge(spriteProvider);
    }

    @Override
    public void tick() {
        super.tick();
        float fadeInAge = getMaxAge() * 0.2f;
        float fadeOutAge = getMaxAge() * 0.6f;
        if (age <= fadeInAge) {
            setAlpha(age / fadeInAge);
        } else if (age >= fadeOutAge) {
            setAlpha(1 - (age - fadeOutAge) / fadeOutAge);
        } else {
            setAlpha(1);
        }
    }

    @Override
    protected int getBrightness(float tint) {
        return LightmapTextureManager.MAX_BLOCK_LIGHT_COORDINATE;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            DiamondSparkleParticle glowParticle = new DiamondSparkleParticle(clientWorld, d, e, f, this.spriteProvider);
            glowParticle.setMaxAge(clientWorld.random.nextInt(30) + 10);
            glowParticle.setAlpha(0);
            return glowParticle;
        }
    }
}
