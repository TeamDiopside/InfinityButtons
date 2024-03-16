package nl.teamdiopside.infinitybuttons.block.button;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.WallMountedBlock;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class WoodenButton extends AbstractSmallButton {

    public final boolean isNetherWood;

    public WoodenButton(Settings settings, boolean large, boolean isNetherWood) {
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

    @Override
    protected MapCodec<? extends WallMountedBlock> getCodec() {
        return null;
    }
}
