package net.larsmans.infinitybuttons.block.custom.button;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class WoodenButton extends AbstractSmallButton {

    public final boolean isNetherWood;

    public WoodenButton(FabricBlockSettings settings, boolean large, boolean isNetherWood) {
        super(true, large, settings);
        this.isNetherWood = isNetherWood;
    }

    @Override
    public int getPressTicks() {
        return 30;
    }

    @Override
    protected SoundEvent getClickSound(boolean powered) {
        if (isNetherWood) {
            return powered ? SoundEvents.BLOCK_NETHER_WOOD_BUTTON_CLICK_ON : SoundEvents.BLOCK_NETHER_WOOD_BUTTON_CLICK_OFF;
        }
        return powered ? SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON : SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF;
    }
}
