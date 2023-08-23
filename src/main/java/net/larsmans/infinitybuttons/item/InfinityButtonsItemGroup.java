package net.larsmans.infinitybuttons.item;

import net.fabricmc.fabric.impl.item.group.ItemGroupExtensions;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.block.custom.DoorbellButton;
import net.larsmans.infinitybuttons.block.custom.LampButton;
import net.larsmans.infinitybuttons.block.custom.LanternButton;
import net.larsmans.infinitybuttons.block.custom.button.AbstractSmallButton;
import net.larsmans.infinitybuttons.block.custom.button.WoodenButton;
import net.larsmans.infinitybuttons.block.custom.consolebutton.ConsoleButton;
import net.larsmans.infinitybuttons.block.custom.emergencybutton.EmergencyButton;
import net.larsmans.infinitybuttons.block.custom.emergencybutton.SafeEmergencyButton;
import net.larsmans.infinitybuttons.block.custom.letterbutton.LetterButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.AbstractSecretButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.BookshelfSecretButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.PlankSecretButton;
import net.larsmans.infinitybuttons.block.custom.torch.RedstoneTorchButton;
import net.larsmans.infinitybuttons.block.custom.torch.TorchButton;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class InfinityButtonsItemGroup extends ItemGroup{

    public static final ItemGroup INFINITYBUTTONS = InfinityButtonsItemGroup.build();

    public InfinityButtonsItemGroup(String id) {
        super(ItemGroup.GROUPS.length - 1, id);
    }

    private static ItemGroup build() {
        ((ItemGroupExtensions) ItemGroup.BUILDING_BLOCKS).fabric_expandArray();
        return new InfinityButtonsItemGroup("infinityButtonsTab");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(InfinityButtonsBlocks.OAK_LARGE_BUTTON);
    }

    @Override
    public void appendStacks(DefaultedList<ItemStack> stacks) {
        List<Item> REGISTRY_FOR_TAB = new ArrayList<>();

        for (Item item : Registry.ITEM)
            if (Objects.equals(Registry.ITEM.getId(item).getNamespace(), "infinitybuttons"))
                REGISTRY_FOR_TAB.add(item);

        List<String> BEFORE_PLANKS = List.of("brick_secret_button", "stone_brick_secret_button", "mossy_stone_brick_secret_button", "cracked_stone_brick_secret_button", "chiseled_stone_brick_secret_button", "deepslate_brick_secret_button", "cracked_deepslate_brick_secret_button", "deepslate_tile_secret_button", "cracked_deepslate_tile_secret_button");
        REGISTRY_FOR_TAB.sort(Comparator.comparing(o -> {
            Block block = Block.getBlockFromItem(o);
            if (block instanceof AbstractSmallButton && !((AbstractSmallButton) block).isLarge()) {
                if (block instanceof WoodenButton) {
                    return "AA";
                } else {
                    return "AZ";
                }
            } else if (block instanceof AbstractSmallButton && ((AbstractSmallButton) block).isLarge()) {
                if (block instanceof WoodenButton) {
                    return "BA";
                } else {
                    return "BZ";
                }
            } else if (block instanceof EmergencyButton || block instanceof SafeEmergencyButton) {
                return "C";
            } else if (block instanceof AbstractSecretButton) {
                if (block instanceof BookshelfSecretButton) {
                    return "DA";
                } else if (BEFORE_PLANKS.contains(Objects.requireNonNull(Registry.BLOCK.getId(block)).getPath())) {
                    return "DB";
                } else if (block instanceof PlankSecretButton) {
                    return "DC";
                } else {
                    return "DZ";
                }
            } else if (block instanceof DoorbellButton || block instanceof LampButton || block instanceof LetterButton) {
                return "E";
            } else if (block instanceof LanternButton) {
                return "F";
            } else if (block instanceof ConsoleButton) {
                return "G";
            } else if (block instanceof TorchButton || block instanceof RedstoneTorchButton) {
                return "H";
            } else {
                return "ZZ";
            }
        }));
        for(Item item : REGISTRY_FOR_TAB) {
            item.appendStacks(this, stacks);
        }
    }
}
