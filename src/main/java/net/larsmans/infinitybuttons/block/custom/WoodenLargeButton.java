package net.larsmans.infinitybuttons.block.custom;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class WoodenLargeButton extends LargeButton{
    public WoodenLargeButton(FabricBlockSettings settings) {
        super(true, false,settings);
    }

    @Override
    protected SoundEvent getClickSound(boolean powered) {
        return powered ? SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON : SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF;
    }
}

