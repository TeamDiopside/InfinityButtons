package net.larsmans.infinitybuttons.block.custom.button;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.larsmans.infinitybuttons.block.InfinityButtonsUtil;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DiamondButton extends AbstractSmallButton {

    private final boolean large;

    public DiamondButton(FabricBlockSettings settings, boolean large) {
        super(false, large, settings);
        this.large = large;
    }

    @Override
    public int getPressTicks() {
        return 20;
    }

    @Override
    protected SoundEvent getClickSound(boolean var1) {
        return var1 ? SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON : SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (InfinityButtonsInit.CONFIG.diamondParticles() && random.nextInt(3) == 0) {
            if (large) {
                switch (state.get(FACE)) {
                    case FLOOR -> world.addParticle(ParticleTypes.SCRAPE,
                            (double) pos.getX() + 0.1875 + (double) random.nextFloat() * 0.625,
                            (double) pos.getY() + 0.125 + (double) random.nextFloat() * 0.0625,
                            (double) pos.getZ() + 0.1875 + (double) random.nextFloat() * 0.625,
                            0, 0, 0);
                    case WALL -> {
                        switch (state.get(FACING)) {
                            case NORTH -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.1875 + (double) random.nextFloat() * 0.625,
                                    (double) pos.getY() + 0.1875 + (double) random.nextFloat() * 0.625,
                                    (double) pos.getZ() + 0.8125 + (double) random.nextFloat() * 0.0625,
                                    0, 0, 0);

                            case EAST -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.125 + (double) random.nextFloat() * 0.0625,
                                    (double) pos.getY() + 0.1875 + (double) random.nextFloat() * 0.625,
                                    (double) pos.getZ() + 0.1875 + (double) random.nextFloat() * 0.625,
                                    0, 0, 0);

                            case SOUTH -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.1875 + (double) random.nextFloat() * 0.625,
                                    (double) pos.getY() + 0.1875 + (double) random.nextFloat() * 0.625,
                                    (double) pos.getZ() + 0.125 + (double) random.nextFloat() * 0.0625,
                                    0, 0, 0);

                            case WEST -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.8125 + (double) random.nextFloat() * 0.0625,
                                    (double) pos.getY() + 0.1875 + (double) random.nextFloat() * 0.625,
                                    (double) pos.getZ() + 0.1875 + (double) random.nextFloat() * 0.625,
                                    0, 0, 0);
                        }
                    }
                    case CEILING -> world.addParticle(ParticleTypes.SCRAPE,
                            (double) pos.getX() + 0.1875 + (double) random.nextFloat() * 0.625,
                            (double) pos.getY() + 0.8125 + (double) random.nextFloat() * 0.0625,
                            (double) pos.getZ() + 0.1875 + (double) random.nextFloat() * 0.625,
                            0, 0, 0);
                }
            } else {
                switch (state.get(FACE)) {
                    case FLOOR -> {
                        switch (state.get(FACING)) {
                            case NORTH, SOUTH -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.25 + (double) random.nextFloat() * 0.5,
                                    (double) pos.getY() + 0.125 + (double) random.nextFloat() * 0.0625,
                                    (double) pos.getZ() + 0.3125 + (double) random.nextFloat() * 0.375,
                                    0, 0, 0);
                            case EAST, WEST -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.3125 + (double) random.nextFloat() * 0.375,
                                    (double) pos.getY() + 0.125 + (double) random.nextFloat() * 0.0625,
                                    (double) pos.getZ() + 0.25 + (double) random.nextFloat() * 0.5,
                                    0, 0, 0);
                        }
                    }
                    case WALL -> {
                        switch (state.get(FACING)) {
                            case NORTH -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.25 + (double) random.nextFloat() * 0.5,
                                    (double) pos.getY() + 0.3125 + (double) random.nextFloat() * 0.375,
                                    (double) pos.getZ() + 0.8125 + (double) random.nextFloat() * 0.0625,
                                    0, 0, 0);

                            case EAST -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.125 + (double) random.nextFloat() * 0.0625,
                                    (double) pos.getY() + 0.3125 + (double) random.nextFloat() * 0.375,
                                    (double) pos.getZ() + 0.25 + (double) random.nextFloat() * 0.5,
                                    0, 0, 0);

                            case SOUTH -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.25 + (double) random.nextFloat() * 0.5,
                                    (double) pos.getY() + 0.3125 + (double) random.nextFloat() * 0.375,
                                    (double) pos.getZ() + 0.125 + (double) random.nextFloat() * 0.0625,
                                    0, 0, 0);

                            case WEST -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.8125 + (double) random.nextFloat() * 0.0625,
                                    (double) pos.getY() + 0.3125 + (double) random.nextFloat() * 0.375,
                                    (double) pos.getZ() + 0.25 + (double) random.nextFloat() * 0.5,
                                    0, 0, 0);
                        }
                    }
                    case CEILING -> {
                        switch (state.get(FACING)) {
                            case NORTH, SOUTH -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.25 + (double) random.nextFloat() * 0.5,
                                    (double) pos.getY() + 0.8125 + (double) random.nextFloat() * 0.0625,
                                    (double) pos.getZ() + 0.3125 + (double) random.nextFloat() * 0.375,
                                    0, 0, 0);
                            case EAST, WEST -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.3125 + (double) random.nextFloat() * 0.375,
                                    (double) pos.getY() + 0.8125 + (double) random.nextFloat() * 0.0625,
                                    (double) pos.getZ() + 0.25 + (double) random.nextFloat() * 0.5,
                                    0, 0, 0);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        InfinityButtonsUtil.tooltip(tooltip, "diamond_button");
    }
}
