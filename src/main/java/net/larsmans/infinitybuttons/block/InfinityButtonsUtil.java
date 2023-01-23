package net.larsmans.infinitybuttons.block;

import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class InfinityButtonsUtil {

    public static final BooleanProperty PRESSED = BooleanProperty.of("pressed");
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public static void tooltip(List<Text> tooltip, String name) {
        if (InfinityButtonsInit.CONFIG.tooltips()) {
            if (Screen.hasShiftDown()) {
                tooltip.add(Text.translatable("infinitybuttons.tooltip." + name).formatted(Formatting.GRAY));
            } else {
                tooltip.add(Text.translatable("infinitybuttons.tooltip.hold_shift").formatted(Formatting.GRAY));
            }
        }
    }

    public static void tooltip(List<Text> tooltip, String name1, String name2) {
        if (InfinityButtonsInit.CONFIG.tooltips()) {
            if (Screen.hasShiftDown()) {
                tooltip.add(Text.translatable("infinitybuttons.tooltip." + name1).formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("infinitybuttons.tooltip." + name2).formatted(Formatting.GRAY));
            } else {
                tooltip.add(Text.translatable("infinitybuttons.tooltip.hold_shift").formatted(Formatting.GRAY));
            }
        }
    }
}
