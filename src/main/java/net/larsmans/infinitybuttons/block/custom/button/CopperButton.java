package net.larsmans.infinitybuttons.block.custom.button;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class CopperButton extends AbstractButton {
    public CopperButton(FabricBlockSettings settings, boolean large) {
        super(false, large, settings);
    }

    @Override
    protected void playClickSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, boolean powered) {
        world.playSound(powered ? player : null, pos, this.getClickSound(powered), SoundCategory.BLOCKS, 1f, powered ? 0.6f : 0.5f);
    }

    @Override
    public int getPressTicks() {
        return 50;
    }

    @Override
    protected SoundEvent getClickSound(boolean powered) {
        return SoundEvents.BLOCK_COPPER_BREAK;
    }
}
