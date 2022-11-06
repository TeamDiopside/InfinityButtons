package net.larsmans.infinitybuttons.block.custom.largebutton;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.larsmans.infinitybuttons.block.custom.button.DiamondButton;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class DiamondLargeButton extends DiamondButton {

    public DiamondLargeButton(FabricBlockSettings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return LargeButtonShape.outlineShape(state);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (InfinityButtonsInit.CONFIG.diamondParticles()) {
            if (random.nextInt(3) == 0) {
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
            }
        }
    }
}
