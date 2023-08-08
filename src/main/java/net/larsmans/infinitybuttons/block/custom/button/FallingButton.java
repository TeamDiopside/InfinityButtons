package net.larsmans.infinitybuttons.block.custom.button;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FallingButton extends AbstractSmallButton {

    public boolean gravel;

    public FallingButton(boolean gravel, FabricBlockSettings settings, boolean large) {
        super(false, large, settings);
        this.gravel = gravel;
    }

    @Override
    public int getPressTicks() {
        return 10;
    }

    @Override
    protected SoundEvent getClickSound(boolean pressed) {
        return gravel ? SoundEvents.BLOCK_GRAVEL_BREAK : SoundEvents.BLOCK_SAND_BREAK;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(PRESSED)) {
            world.setBlockState(pos, state.with(PRESSED, false), Block.NOTIFY_ALL);
            this.updateNeighbors(state, world, pos);
            this.playClickSound(null, world, pos, false);
            world.breakBlock(pos, false);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        InfinityButtonsUtil.tooltip(tooltip, "falling_button");
    }
}
