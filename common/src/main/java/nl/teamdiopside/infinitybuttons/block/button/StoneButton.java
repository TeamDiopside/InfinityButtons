package nl.teamdiopside.infinitybuttons.block.button;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.WallMountedBlock;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class StoneButton extends AbstractSmallButton {

    public StoneButton(Settings settings, boolean large) {
        super(false, large, settings);
    }

    @Override
    public int getPressTicks() {
        return 20;
    }

    @Override
    protected SoundEvent getClickSound(boolean powered) {
        return powered ? SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON : SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF;
    }

    @Override
    protected MapCodec<? extends WallMountedBlock> getCodec() {
        return null;
    }
}
