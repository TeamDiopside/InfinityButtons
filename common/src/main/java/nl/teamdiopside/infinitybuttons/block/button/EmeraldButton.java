package nl.teamdiopside.infinitybuttons.block.button;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.WallMountedBlock;
import nl.teamdiopside.infinitybuttons.InfinityButtonsUtil;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EmeraldButton extends AbstractSmallButton {
    public EmeraldButton(Settings settings, boolean large) {
        super(false, large, settings);
    }

    @Override
    public int getPressTicks() {
        return (int)Math.floor(Math.random()*(90-10+1)+10);
    }

    @Override
    protected SoundEvent getClickSound(boolean powered) {
        return powered ? SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON : SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        InfinityButtonsUtil.tooltip(tooltip, "emerald_button1", "emerald_button2");
    }

    @Override
    protected MapCodec<? extends WallMountedBlock> getCodec() {
        return null;
    }
}