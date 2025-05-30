package net.larsmans.infinitybuttons.block.custom.torch.compat;

import com.teamabnormals.endergetic.core.registry.EEParticleTypes;
import net.larsmans.infinitybuttons.block.custom.torch.TorchLever;
import net.larsmans.infinitybuttons.compat.EndergeticItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class EnderTorchLever extends TorchLever {
    public EnderTorchLever(Properties properties, Block jadeBlock) {
        super(properties, ParticleTypes.FLAME, jadeBlock);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
        Direction direction = stateIn.getValue(FACING);
        if (stateIn.getValue(PRESSED)) {
            double d0 = (double) pos.getX() + 0.5D;
            double d1 = (double) pos.getY() + 0.63D;
            double d2 = (double) pos.getZ() + 0.5D;
            Direction direction2 = direction.getOpposite();
            worldIn.addParticle(ParticleTypes.SMOKE,
                    d0 + 0.23D * (double) direction2.getStepX(),
                    d1,
                    d2 + 0.23D * (double) direction2.getStepZ(),
                    0.0D, 0.0D, 0.0D);
            worldIn.addParticle(EEParticleTypes.ENDER_FIRE_FLAME.get(),
                    d0 + 0.23D * (double) direction2.getStepX(),
                    d1,
                    d2 + 0.23D * (double) direction2.getStepZ(),
                    0.0D, 0.0D, 0.0D);
        } else {
            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY() + 0.7D;
            double d2 = (double)pos.getZ() + 0.5D;
            worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            worldIn.addParticle(EEParticleTypes.ENDER_FIRE_FLAME.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        return List.of(new ItemStack(EndergeticItems.ENDER_TORCH_LEVER.get()));
    }
}
