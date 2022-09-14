package net.larsmans.infinitybuttons.block.custom;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EmeraldLargeButton extends LargeButton{
    public EmeraldLargeButton(FabricBlockSettings settings) {
        super(false, settings);
    }

    @Override
    public int getPressTicks() {
        return (int)Math.floor(Math.random()*(90-10+1)+10);
    }

    @Override
    protected SoundEvent getClickSound(boolean var1) {
        return SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_ON;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("infinitybuttons.tooltip.emerald_button").formatted(Formatting.GRAY));
        } else {
            tooltip.add(Text.translatable("infinitybuttons.tooltip.hold_shift").formatted(Formatting.GRAY));
        }
    }
}
