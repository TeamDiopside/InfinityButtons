package nl.teamdiopside.infinitybuttons.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.NotNull;

public class DiamondSparkleParticle extends TextureSheetParticle {
    RandomSource globalRandom = RandomSource.create();

    public DiamondSparkleParticle(ClientLevel clientLevel, double d, double e, double f) {
        super(clientLevel, d, e, f);

        this.quadSize = 0.150f + (this.globalRandom.nextFloat() * 0.050f);
        this.friction = 0.96f;
        this.speedUpWhenYMotionIsBlocked = true;
        this.hasPhysics = false;
        this.lifetime *= 3;
        this.setAlpha(0);
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    protected int getLightColor(float f) {
        return LightTexture.FULL_BRIGHT;
    }

    @Override
    public void tick() {
        super.tick();
        float fadeInAge = this.getLifetime() * 0.2f;
        float fadeOutAge = this.getLifetime() * 0.6f;
        if (this.age <= fadeInAge) {
            this.setAlpha(this.age / fadeInAge);
        } else if (this.age >= fadeOutAge) {
            this.setAlpha(1 - (this.age - fadeOutAge) / fadeOutAge);
        } else {
            this.setAlpha(1);
        }
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType particleType, ClientLevel clientLevel, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            DiamondSparkleParticle particle = new DiamondSparkleParticle(clientLevel, x, y, z);
            particle.pickSprite(this.sprites);
            particle.xd = xSpeed;
            particle.yd = ySpeed;
            particle.zd = zSpeed;
            return particle;
        }
    }
}
