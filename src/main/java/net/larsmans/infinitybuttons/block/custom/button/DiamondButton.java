package net.larsmans.infinitybuttons.block.custom.button;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.larsmans.infinitybuttons.particle.InfinityButtonsParticleTypes;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
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
                    case FLOOR -> addParticle(3, 10, 2, 1, 3, 10, world, pos, random);
                    case WALL -> {
                        switch (state.get(FACING)) {
                            case NORTH -> addParticle(3, 10, 3, 10, 13, 1, world, pos, random);
                            case EAST -> addParticle(2, 1, 3, 10, 3, 10, world, pos, random);
                            case SOUTH -> addParticle(3, 10, 3, 10, 2, 1, world, pos, random);
                            case WEST -> addParticle(13, 1, 3, 10, 3, 10, world, pos, random);
                        }
                    }
                    case CEILING -> addParticle(3, 10, 13, 1, 3, 10, world, pos, random);
                }
            } else {
                switch (state.get(FACE)) {
                    case FLOOR -> {
                        switch (state.get(FACING)) {
                            case NORTH, SOUTH -> addParticle(4, 8, 2, 1, 5, 6, world, pos, random);
                            case EAST, WEST -> addParticle(5, 6, 2, 1, 4, 8, world, pos, random);
                        }
                    }
                    case WALL -> {
                        switch (state.get(FACING)) {
                            case NORTH -> addParticle(4, 8, 5, 6, 13, 1, world, pos, random);
                            case EAST -> addParticle(2, 1, 5, 6, 4, 8, world, pos, random);
                            case SOUTH -> addParticle(4, 8, 5, 6, 2, 1, world, pos, random);
                            case WEST -> addParticle(13, 1, 5, 6, 4, 8, world, pos, random);
                        }
                    }
                    case CEILING -> {
                        switch (state.get(FACING)) {
                            case NORTH, SOUTH -> addParticle(4, 8, 13, 1, 5, 6, world, pos, random);
                            case EAST, WEST -> addParticle(5, 6, 13, 1, 4, 8, world, pos, random);
                        }
                    }
                }
            }
        }
    }

    public void addParticle(int x1, int x2, int y1, int y2, int z1, int z2, World world, BlockPos pos, Random random) {
        // we kunnen het in de rewrite met een VoxelShape doen en ook draaien enzo :)
        world.addParticle(InfinityButtonsParticleTypes.DIAMOND_SPARKLE,
                pos.getX() + (double) x1 / 16 + random.nextFloat() * (double) x2 / 16,
                pos.getY() + (double) y1 / 16 + random.nextFloat() * (double) y2 / 16,
                pos.getZ() + (double) z1 / 16 + random.nextFloat() * (double) z2 / 16,
                0, 0, 0);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        InfinityButtonsUtil.tooltip(tooltip, "diamond_button");
    }
}
