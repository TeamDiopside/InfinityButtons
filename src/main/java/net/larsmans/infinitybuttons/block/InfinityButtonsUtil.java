package net.larsmans.infinitybuttons.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.Camera;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Supplier;

public class InfinityButtonsUtil {

    public static final MutableText HOLD_SHIFT_TEXT = Text.translatable("infinitybuttons.tooltip.hold_shift").formatted(Formatting.GRAY);
    public static final MutableText SAFE_EMERGENCY_BUTTON_ACTIONBAR_TEXT = Text.translatable("infinitybuttons.actionbar.closed_safety_button");

    public static Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK;
    public static Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK;
    public static Supplier<BiMap<Block, Block>> WAX_ON_BY_BLOCK;
    public static Supplier<BiMap<Block, Block>> WAX_OFF_BY_BLOCK;
    public static Supplier<BiMap<Block, Block>> STICKY_ON_BY_BLOCK;
    public static Supplier<BiMap<Block, Block>> STICKY_OFF_BY_BLOCK;

    public static void tooltip(List<Text> tooltip, String name) {
        if (InfinityButtonsInit.CONFIG.tooltips()) {
            if (Screen.hasShiftDown()) {
                tooltip.add(Text.translatable("infinitybuttons.tooltip." + name).formatted(Formatting.GRAY));
            } else {
                tooltip.add(HOLD_SHIFT_TEXT);
            }
        }
    }

    public static void tooltip(List<Text> tooltip, String name1, String name2) {
        if (InfinityButtonsInit.CONFIG.tooltips()) {
            if (Screen.hasShiftDown()) {
                tooltip.add(Text.translatable("infinitybuttons.tooltip." + name1).formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("infinitybuttons.tooltip." + name2).formatted(Formatting.GRAY));
            } else {
                tooltip.add(HOLD_SHIFT_TEXT);
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

    public static void buildNext() {
        if (NEXT_BY_BLOCK == null) {
            NEXT_BY_BLOCK = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
                    .put(InfinityButtonsBlocks.COPPER_BUTTON, InfinityButtonsBlocks.EXPOSED_COPPER_BUTTON)
                    .put(InfinityButtonsBlocks.EXPOSED_COPPER_BUTTON, InfinityButtonsBlocks.WEATHERED_COPPER_BUTTON)
                    .put(InfinityButtonsBlocks.WEATHERED_COPPER_BUTTON, InfinityButtonsBlocks.OXIDIZED_COPPER_BUTTON)
                    .put(InfinityButtonsBlocks.COPPER_LARGE_BUTTON, InfinityButtonsBlocks.EXPOSED_COPPER_LARGE_BUTTON)
                    .put(InfinityButtonsBlocks.EXPOSED_COPPER_LARGE_BUTTON, InfinityButtonsBlocks.WEATHERED_COPPER_LARGE_BUTTON)
                    .put(InfinityButtonsBlocks.WEATHERED_COPPER_LARGE_BUTTON, InfinityButtonsBlocks.OXIDIZED_COPPER_LARGE_BUTTON).build());
            PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> NEXT_BY_BLOCK.get().inverse());
        }
    }

    public static void buildWax() {
        if (WAX_ON_BY_BLOCK == null) {
            WAX_ON_BY_BLOCK = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
                    .put(InfinityButtonsBlocks.COPPER_BUTTON, InfinityButtonsBlocks.WAXED_COPPER_BUTTON)
                    .put(InfinityButtonsBlocks.EXPOSED_COPPER_BUTTON, InfinityButtonsBlocks.WAXED_EXPOSED_COPPER_BUTTON)
                    .put(InfinityButtonsBlocks.WEATHERED_COPPER_BUTTON, InfinityButtonsBlocks.WAXED_WEATHERED_COPPER_BUTTON)
                    .put(InfinityButtonsBlocks.OXIDIZED_COPPER_BUTTON, InfinityButtonsBlocks.WAXED_OXIDIZED_COPPER_BUTTON)
                    .put(InfinityButtonsBlocks.COPPER_LARGE_BUTTON, InfinityButtonsBlocks.WAXED_COPPER_LARGE_BUTTON)
                    .put(InfinityButtonsBlocks.EXPOSED_COPPER_LARGE_BUTTON, InfinityButtonsBlocks.WAXED_EXPOSED_COPPER_LARGE_BUTTON)
                    .put(InfinityButtonsBlocks.WEATHERED_COPPER_LARGE_BUTTON, InfinityButtonsBlocks.WAXED_WEATHERED_COPPER_LARGE_BUTTON)
                    .put(InfinityButtonsBlocks.OXIDIZED_COPPER_LARGE_BUTTON, InfinityButtonsBlocks.WAXED_OXIDIZED_COPPER_LARGE_BUTTON).build());
            WAX_OFF_BY_BLOCK = Suppliers.memoize(() -> WAX_ON_BY_BLOCK.get().inverse());
        }
    }

    public static void buildSticky() {
        if (STICKY_ON_BY_BLOCK == null) {
            STICKY_ON_BY_BLOCK = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
                    .put(InfinityButtonsBlocks.WAXED_COPPER_BUTTON, InfinityButtonsBlocks.STICKY_COPPER_BUTTON)
                    .put(InfinityButtonsBlocks.WAXED_EXPOSED_COPPER_BUTTON, InfinityButtonsBlocks.STICKY_EXPOSED_COPPER_BUTTON)
                    .put(InfinityButtonsBlocks.WAXED_WEATHERED_COPPER_BUTTON, InfinityButtonsBlocks.STICKY_WEATHERED_COPPER_BUTTON)
                    .put(InfinityButtonsBlocks.WAXED_OXIDIZED_COPPER_BUTTON, InfinityButtonsBlocks.STICKY_OXIDIZED_COPPER_BUTTON)
                    .put(InfinityButtonsBlocks.WAXED_COPPER_LARGE_BUTTON, InfinityButtonsBlocks.STICKY_COPPER_LARGE_BUTTON)
                    .put(InfinityButtonsBlocks.WAXED_EXPOSED_COPPER_LARGE_BUTTON, InfinityButtonsBlocks.STICKY_EXPOSED_COPPER_LARGE_BUTTON)
                    .put(InfinityButtonsBlocks.WAXED_WEATHERED_COPPER_LARGE_BUTTON, InfinityButtonsBlocks.STICKY_WEATHERED_COPPER_LARGE_BUTTON)
                    .put(InfinityButtonsBlocks.WAXED_OXIDIZED_COPPER_LARGE_BUTTON, InfinityButtonsBlocks.STICKY_OXIDIZED_COPPER_LARGE_BUTTON).build());
            STICKY_OFF_BY_BLOCK = Suppliers.memoize(() -> STICKY_ON_BY_BLOCK.get().inverse());
        }
    }
}
