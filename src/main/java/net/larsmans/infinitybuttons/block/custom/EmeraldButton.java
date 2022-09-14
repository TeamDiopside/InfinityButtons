package net.larsmans.infinitybuttons.block.custom;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EmeraldButton extends Button{
    public EmeraldButton(FabricBlockSettings settings) {
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
        tooltip.add(Text.literal("This button stays randomly pressed for 0.5 to 5 seconds"));
    }
}
