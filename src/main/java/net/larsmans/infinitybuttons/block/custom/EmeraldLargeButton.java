package net.larsmans.infinitybuttons.block.custom;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class EmeraldLargeButton extends LargeButton{
    public EmeraldLargeButton(FabricBlockSettings settings) {
        super(false, false,settings);
    }

    @Override
    protected int getPressTicks() {
        return (int)Math.floor(Math.random()*(90-10+1)+10);
    }

    @Override
    protected SoundEvent getClickSound(boolean var1) {
        return SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_ON;
    }
}
