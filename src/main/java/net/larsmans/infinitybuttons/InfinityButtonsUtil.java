package net.larsmans.infinitybuttons;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.block.custom.emergencybutton.SafeEmergencyButton;
import net.larsmans.infinitybuttons.block.custom.letterbutton.LetterButton;
import net.larsmans.infinitybuttons.item.SafeEmergencyButtonItem;
import net.minecraft.block.Block;
import net.minecraft.block.ChainBlock;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.registry.Registries;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class InfinityButtonsUtil {

    public static final MutableText HOLD_SHIFT_TEXT = Text.translatable("infinitybuttons.tooltip.hold_shift").formatted(Formatting.GRAY);
    public static final MutableText SAFE_EMERGENCY_BUTTON_ACTIONBAR_TEXT = Text.translatable("infinitybuttons.actionbar.closed_safety_button");

    public static List<SafeEmergencyButtonItem> SAFETY_BUTTONS;

    public static Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK;
    public static Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK;
    public static Supplier<BiMap<Block, Block>> WAX_ON_BY_BLOCK;
    public static Supplier<BiMap<Block, Block>> WAX_OFF_BY_BLOCK;
    public static Supplier<BiMap<Block, Block>> STICKY_ON_BY_BLOCK;
    public static Supplier<BiMap<Block, Block>> STICKY_OFF_BY_BLOCK;

    public static boolean crouchClickOverrides(Block block) {
        return block instanceof SafeEmergencyButton || block instanceof LetterButton;
    }

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

    public static void buildSafety() {
        if (SAFETY_BUTTONS == null) {
            SAFETY_BUTTONS = new ArrayList<>();
            for (Block block : Registries.BLOCK.stream().toList())
                if (block instanceof SafeEmergencyButton)
                    SAFETY_BUTTONS.add((SafeEmergencyButtonItem) block.asItem());
        }
    }

    public static int checkChains(World world, BlockPos pos) {
        int i = 0;
        while (world.getBlockState(pos.up(i + 1)).getBlock() instanceof ChainBlock) {
            i++;
        }
        return i;
    }
}
