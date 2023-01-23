package net.larsmans.infinitybuttons.block.custom.torch;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class PropelTorchLever extends TorchLever {
    public PropelTorchLever(FabricBlockSettings settings) {
        super(settings, ParticleTypes.FLAME);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
    }
}
