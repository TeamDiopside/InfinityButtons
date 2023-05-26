package net.larsmans.infinitybuttons.block;

import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.Camera;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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

    public static void playGlobalSound(World world, BlockPos pos, SoundEvent soundEvent, SoundCategory soundCategory) {
        Camera cam = MinecraftClient.getInstance().gameRenderer.getCamera();
        if (cam.isReady()) {
            double x = cam.getPos().x;
            double y = cam.getPos().y;
            double z = cam.getPos().z;
            double d0 = (double)pos.getX() - x;
            double d1 = (double)pos.getY() - y;
            double d2 = (double)pos.getZ() - z;
            double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
            if (d3 > 0.0D) {
                x += d0 / d3 * 2.0D;
                y += d1 / d3 * 2.0D;
                z += d2 / d3 * 2.0D;
            }
            world.playSound(x, y, z, soundEvent, soundCategory, 1.0F, 1.0F, false);
        }
    }
}
