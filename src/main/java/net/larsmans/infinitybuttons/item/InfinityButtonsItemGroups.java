package net.larsmans.infinitybuttons.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.larsmans.infinitybuttons.InfinityButtonsInit.MOD_ID;

public class InfinityButtonsItemGroups {
    public static final RegistryKey<ItemGroup> INFINITYBUTTONS = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MOD_ID, "infinitybuttons"));

    private static void registerGroup() {
        Registry.register(Registries.ITEM_GROUP, INFINITYBUTTONS, FabricItemGroup.builder()
                .icon(() -> new ItemStack(InfinityButtonsBlocks.OAK_LARGE_BUTTON))
                .entries((enabledFeatures, entries) -> {
                    entries.add(Blocks.OAK_BUTTON);
                    entries.add(Blocks.SPRUCE_BUTTON);
                    entries.add(Blocks.BIRCH_BUTTON);
                    entries.add(Blocks.JUNGLE_BUTTON);
                    entries.add(Blocks.ACACIA_BUTTON);
                    entries.add(Blocks.DARK_OAK_BUTTON);
                    entries.add(Blocks.MANGROVE_BUTTON);
                    entries.add(Blocks.CRIMSON_BUTTON);
                    entries.add(Blocks.WARPED_BUTTON);
                    entries.add(Blocks.STONE_BUTTON);
                    entries.add(InfinityButtonsBlocks.DEEPSLATE_BUTTON);
                    entries.add(InfinityButtonsBlocks.GRANITE_BUTTON);
                    entries.add(InfinityButtonsBlocks.DIORITE_BUTTON);
                    entries.add(InfinityButtonsBlocks.ANDESITE_BUTTON);
                    entries.add(InfinityButtonsBlocks.CALCITE_BUTTON);
                    entries.add(InfinityButtonsBlocks.TUFF_BUTTON);
                    entries.add(InfinityButtonsBlocks.DRIPSTONE_BUTTON);
                    entries.add(Blocks.POLISHED_BLACKSTONE_BUTTON);
                    entries.add(InfinityButtonsBlocks.COPPER_BUTTON);
                    entries.add(InfinityButtonsBlocks.EXPOSED_COPPER_BUTTON);
                    entries.add(InfinityButtonsBlocks.WEATHERED_COPPER_BUTTON);
                    entries.add(InfinityButtonsBlocks.OXIDIZED_COPPER_BUTTON);
                    entries.add(InfinityButtonsBlocks.WAXED_COPPER_BUTTON);
                    entries.add(InfinityButtonsBlocks.WAXED_EXPOSED_COPPER_BUTTON);
                    entries.add(InfinityButtonsBlocks.WAXED_WEATHERED_COPPER_BUTTON);
                    entries.add(InfinityButtonsBlocks.WAXED_OXIDIZED_COPPER_BUTTON);
                    entries.add(InfinityButtonsBlocks.STICKY_COPPER_BUTTON);
                    entries.add(InfinityButtonsBlocks.STICKY_EXPOSED_COPPER_BUTTON);
                    entries.add(InfinityButtonsBlocks.STICKY_WEATHERED_COPPER_BUTTON);
                    entries.add(InfinityButtonsBlocks.STICKY_OXIDIZED_COPPER_BUTTON);
                    entries.add(InfinityButtonsBlocks.IRON_BUTTON);
                    entries.add(InfinityButtonsBlocks.GOLD_BUTTON);
                    entries.add(InfinityButtonsBlocks.EMERALD_BUTTON);
                    entries.add(InfinityButtonsBlocks.DIAMOND_BUTTON);
                    entries.add(InfinityButtonsBlocks.PRISMARINE_BUTTON);
                    entries.add(InfinityButtonsBlocks.PRISMARINE_BRICK_BUTTON);
                    entries.add(InfinityButtonsBlocks.DARK_PRISMARINE_BUTTON);
                    entries.add(InfinityButtonsBlocks.SAND_BUTTON);
                    entries.add(InfinityButtonsBlocks.RED_SAND_BUTTON);
                    entries.add(InfinityButtonsBlocks.GRAVEL_BUTTON);
                    entries.add(InfinityButtonsBlocks.WHITE_CONCRETE_POWDER_BUTTON);
                    entries.add(InfinityButtonsBlocks.LIGHT_GRAY_CONCRETE_POWDER_BUTTON);
                    entries.add(InfinityButtonsBlocks.GRAY_CONCRETE_POWDER_BUTTON);
                    entries.add(InfinityButtonsBlocks.BLACK_CONCRETE_POWDER_BUTTON);
                    entries.add(InfinityButtonsBlocks.BROWN_CONCRETE_POWDER_BUTTON);
                    entries.add(InfinityButtonsBlocks.RED_CONCRETE_POWDER_BUTTON);
                    entries.add(InfinityButtonsBlocks.ORANGE_CONCRETE_POWDER_BUTTON);
                    entries.add(InfinityButtonsBlocks.YELLOW_CONCRETE_POWDER_BUTTON);
                    entries.add(InfinityButtonsBlocks.LIME_CONCRETE_POWDER_BUTTON);
                    entries.add(InfinityButtonsBlocks.GREEN_CONCRETE_POWDER_BUTTON);
                    entries.add(InfinityButtonsBlocks.CYAN_CONCRETE_POWDER_BUTTON);
                    entries.add(InfinityButtonsBlocks.LIGHT_BLUE_CONCRETE_POWDER_BUTTON);
                    entries.add(InfinityButtonsBlocks.BLUE_CONCRETE_POWDER_BUTTON);
                    entries.add(InfinityButtonsBlocks.PURPLE_CONCRETE_POWDER_BUTTON);
                    entries.add(InfinityButtonsBlocks.MAGENTA_CONCRETE_POWDER_BUTTON);
                    entries.add(InfinityButtonsBlocks.PINK_CONCRETE_POWDER_BUTTON);

                    entries.add(InfinityButtonsBlocks.OAK_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.SPRUCE_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.BIRCH_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.JUNGLE_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.ACACIA_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.DARK_OAK_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.MANGROVE_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.CRIMSON_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.WARPED_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.STONE_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.DEEPSLATE_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.GRANITE_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.DIORITE_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.ANDESITE_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.TUFF_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.DRIPSTONE_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.CALCITE_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.POLISHED_BLACKSTONE_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.COPPER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.EXPOSED_COPPER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.WEATHERED_COPPER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.OXIDIZED_COPPER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.WAXED_COPPER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.WAXED_EXPOSED_COPPER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.WAXED_WEATHERED_COPPER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.WAXED_OXIDIZED_COPPER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.STICKY_COPPER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.STICKY_EXPOSED_COPPER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.STICKY_WEATHERED_COPPER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.STICKY_OXIDIZED_COPPER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.IRON_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.GOLD_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.EMERALD_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.DIAMOND_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.PRISMARINE_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.PRISMARINE_BRICK_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.DARK_PRISMARINE_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.SAND_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.RED_SAND_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.GRAVEL_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.WHITE_CONCRETE_POWDER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.LIGHT_GRAY_CONCRETE_POWDER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.GRAY_CONCRETE_POWDER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.BLACK_CONCRETE_POWDER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.BROWN_CONCRETE_POWDER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.RED_CONCRETE_POWDER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.ORANGE_CONCRETE_POWDER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.YELLOW_CONCRETE_POWDER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.LIME_CONCRETE_POWDER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.GREEN_CONCRETE_POWDER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.CYAN_CONCRETE_POWDER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.LIGHT_BLUE_CONCRETE_POWDER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.BLUE_CONCRETE_POWDER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.PURPLE_CONCRETE_POWDER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.MAGENTA_CONCRETE_POWDER_LARGE_BUTTON);
                    entries.add(InfinityButtonsBlocks.PINK_CONCRETE_POWDER_LARGE_BUTTON);

                    entries.add(InfinityButtonsBlocks.WHITE_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.LIGHT_GRAY_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.GRAY_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.BLACK_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.BROWN_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.RED_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.ORANGE_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.YELLOW_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.LIME_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.GREEN_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.CYAN_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.LIGHT_BLUE_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.BLUE_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.PURPLE_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.MAGENTA_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.PINK_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.FANCY_EMERGENCY_BUTTON);

                    entries.add(InfinityButtonsBlocks.WHITE_SAFE_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.LIGHT_GRAY_SAFE_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.GRAY_SAFE_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.BLACK_SAFE_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.BROWN_SAFE_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.RED_SAFE_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.ORANGE_SAFE_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.YELLOW_SAFE_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.LIME_SAFE_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.GREEN_SAFE_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.CYAN_SAFE_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.LIGHT_BLUE_SAFE_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.BLUE_SAFE_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.PURPLE_SAFE_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.MAGENTA_SAFE_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.PINK_SAFE_EMERGENCY_BUTTON);
                    entries.add(InfinityButtonsBlocks.FANCY_SAFE_EMERGENCY_BUTTON);

                    entries.add(InfinityButtonsBlocks.BOOKSHELF_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.BRICK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.STONE_BRICK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.MOSSY_STONE_BRICK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.CRACKED_STONE_BRICK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.CHISELED_STONE_BRICK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.DEEPSLATE_BRICK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.CRACKED_DEEPSLATE_BRICK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.DEEPSLATE_TILE_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.CRACKED_DEEPSLATE_TILE_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.OAK_PLANK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.SPRUCE_PLANK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.BIRCH_PLANK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.JUNGLE_PLANK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.ACACIA_PLANK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.DARK_OAK_PLANK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.MANGROVE_PLANK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.CRIMSON_PLANK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.WARPED_PLANK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.MUD_BRICK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.END_STONE_BRICK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.PURPUR_BLOCK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.QUARTZ_BRICK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.DARK_PRISMARINE_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.CHISELED_POLISHED_BLACKSTONE_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.NETHER_BRICK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.CRACKED_NETHER_BRICK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.CHISELED_NETHER_BRICK_SECRET_BUTTON);
                    entries.add(InfinityButtonsBlocks.RED_NETHER_BRICK_SECRET_BUTTON);

                    entries.add(InfinityButtonsBlocks.DOORBELL);
                    entries.add(InfinityButtonsBlocks.DOORBELL_BUTTON);
                    entries.add(InfinityButtonsBlocks.LAMP_BUTTON);
                    entries.add(InfinityButtonsBlocks.LAMP_LEVER);
                    entries.add(InfinityButtonsBlocks.LETTER_BUTTON);
                    entries.add(InfinityButtonsBlocks.LETTER_LEVER);
                    entries.add(InfinityButtonsBlocks.LANTERN_BUTTON);
                    entries.add(InfinityButtonsBlocks.LANTERN_LEVER);
                    entries.add(InfinityButtonsBlocks.SOUL_LANTERN_BUTTON);
                    entries.add(InfinityButtonsBlocks.SOUL_LANTERN_LEVER);

                    entries.add(InfinityButtonsBlocks.SMALL_CONSOLE_BUTTON);
                    entries.add(InfinityButtonsBlocks.SMALL_CONSOLE_LEVER);
                    entries.add(InfinityButtonsBlocks.CONSOLE_BUTTON);
                    entries.add(InfinityButtonsBlocks.CONSOLE_LEVER);
                    entries.add(InfinityButtonsBlocks.LARGE_CONSOLE_BUTTON);
                    entries.add(InfinityButtonsBlocks.LARGE_CONSOLE_LEVER);
                    entries.add(InfinityButtonsBlocks.BIG_CONSOLE_BUTTON);
                    entries.add(InfinityButtonsBlocks.BIG_CONSOLE_LEVER);

                    entries.add(InfinityButtonsItems.TORCH_BUTTON);
                    entries.add(InfinityButtonsItems.TORCH_LEVER);
                    entries.add(InfinityButtonsItems.SOUL_TORCH_BUTTON);
                    entries.add(InfinityButtonsItems.SOUL_TORCH_LEVER);
                    entries.add(InfinityButtonsItems.REDSTONE_TORCH_BUTTON);
                    entries.add(InfinityButtonsItems.REDSTONE_TORCH_LEVER);
                })
                .displayName(Text.translatable("itemGroup.infinityButtonsTab"))
                .build());
    }

    private static void addButtonsToVanillaTabs() {
        addAfterInBuildingBlocks(Items.OAK_BUTTON, InfinityButtonsBlocks.OAK_LARGE_BUTTON);
        addAfterInBuildingBlocks(Items.SPRUCE_BUTTON, InfinityButtonsBlocks.SPRUCE_LARGE_BUTTON);
        addAfterInBuildingBlocks(Items.BIRCH_BUTTON, InfinityButtonsBlocks.BIRCH_LARGE_BUTTON);
        addAfterInBuildingBlocks(Items.JUNGLE_BUTTON, InfinityButtonsBlocks.JUNGLE_LARGE_BUTTON);
        addAfterInBuildingBlocks(Items.ACACIA_BUTTON, InfinityButtonsBlocks.ACACIA_LARGE_BUTTON);
        addAfterInBuildingBlocks(Items.DARK_OAK_BUTTON, InfinityButtonsBlocks.DARK_OAK_LARGE_BUTTON);
        addAfterInBuildingBlocks(Items.MANGROVE_BUTTON, InfinityButtonsBlocks.MANGROVE_LARGE_BUTTON);
        addAfterInBuildingBlocks(Items.CRIMSON_BUTTON, InfinityButtonsBlocks.CRIMSON_LARGE_BUTTON);
        addAfterInBuildingBlocks(Items.WARPED_BUTTON, InfinityButtonsBlocks.WARPED_LARGE_BUTTON);
        addAfterInBuildingBlocks(Items.STONE_BUTTON, InfinityButtonsBlocks.STONE_LARGE_BUTTON);

        addMultipleAfterInBuildingBlocks(Items.GRANITE_WALL, new ItemConvertible[]{
                InfinityButtonsBlocks.GRANITE_BUTTON,
                InfinityButtonsBlocks.GRANITE_LARGE_BUTTON,
        });
        addMultipleAfterInBuildingBlocks(Items.DIORITE_WALL, new ItemConvertible[]{
                InfinityButtonsBlocks.DIORITE_BUTTON,
                InfinityButtonsBlocks.DIORITE_LARGE_BUTTON,
        });
        addMultipleAfterInBuildingBlocks(Items.ANDESITE_WALL, new ItemConvertible[]{
                InfinityButtonsBlocks.ANDESITE_BUTTON,
                InfinityButtonsBlocks.ANDESITE_LARGE_BUTTON,
        });
        addMultipleAfterInBuildingBlocks(Items.PRISMARINE_WALL, new ItemConvertible[]{
                InfinityButtonsBlocks.PRISMARINE_BUTTON,
                InfinityButtonsBlocks.PRISMARINE_LARGE_BUTTON,
        });
        addMultipleAfterInBuildingBlocks(Items.PRISMARINE_BRICK_SLAB, new ItemConvertible[]{
                InfinityButtonsBlocks.PRISMARINE_BRICK_BUTTON,
                InfinityButtonsBlocks.PRISMARINE_BRICK_LARGE_BUTTON,
        });
        addMultipleAfterInBuildingBlocks(Items.DARK_PRISMARINE_SLAB, new ItemConvertible[]{
                InfinityButtonsBlocks.DARK_PRISMARINE_BUTTON,
                InfinityButtonsBlocks.DARK_PRISMARINE_LARGE_BUTTON,
        });
        addMultipleAfterInBuildingBlocks(Items.DIORITE_WALL, new ItemConvertible[]{
                InfinityButtonsBlocks.DIORITE_BUTTON,
                InfinityButtonsBlocks.DIORITE_LARGE_BUTTON,
        });

        addAfterInBuildingBlocks(Items.POLISHED_BLACKSTONE_BUTTON, InfinityButtonsBlocks.POLISHED_BLACKSTONE_LARGE_BUTTON);

        addMultipleAfterInBuildingBlocks(Items.HEAVY_WEIGHTED_PRESSURE_PLATE, new ItemConvertible[]{
                InfinityButtonsBlocks.IRON_BUTTON,
                InfinityButtonsBlocks.IRON_LARGE_BUTTON,
        });
        addMultipleAfterInBuildingBlocks(Items.LIGHT_WEIGHTED_PRESSURE_PLATE, new ItemConvertible[]{
                InfinityButtonsBlocks.GOLD_BUTTON,
                InfinityButtonsBlocks.GOLD_LARGE_BUTTON,
        });
        addMultipleAfterInBuildingBlocks(Items.CUT_COPPER_SLAB, new ItemConvertible[]{
                InfinityButtonsBlocks.COPPER_BUTTON,
                InfinityButtonsBlocks.COPPER_LARGE_BUTTON,
        });
        addMultipleAfterInBuildingBlocks(Items.EXPOSED_CUT_COPPER_SLAB, new ItemConvertible[]{
                InfinityButtonsBlocks.EXPOSED_COPPER_BUTTON,
                InfinityButtonsBlocks.EXPOSED_COPPER_LARGE_BUTTON,
        });
        addMultipleAfterInBuildingBlocks(Items.WEATHERED_CUT_COPPER_SLAB, new ItemConvertible[]{
                InfinityButtonsBlocks.WEATHERED_COPPER_BUTTON,
                InfinityButtonsBlocks.WEATHERED_COPPER_LARGE_BUTTON,
        });
        addMultipleAfterInBuildingBlocks(Items.OXIDIZED_CUT_COPPER_SLAB, new ItemConvertible[]{
                InfinityButtonsBlocks.OXIDIZED_COPPER_BUTTON,
                InfinityButtonsBlocks.OXIDIZED_COPPER_LARGE_BUTTON,
        });
        addMultipleAfterInBuildingBlocks(Items.WAXED_CUT_COPPER_SLAB, new ItemConvertible[]{
                InfinityButtonsBlocks.WAXED_COPPER_BUTTON,
                InfinityButtonsBlocks.WAXED_COPPER_LARGE_BUTTON,
        });
        addMultipleAfterInBuildingBlocks(Items.WAXED_EXPOSED_CUT_COPPER_SLAB, new ItemConvertible[]{
                InfinityButtonsBlocks.WAXED_EXPOSED_COPPER_BUTTON,
                InfinityButtonsBlocks.WAXED_EXPOSED_COPPER_LARGE_BUTTON,
        });
        addMultipleAfterInBuildingBlocks(Items.WAXED_WEATHERED_CUT_COPPER_SLAB, new ItemConvertible[]{
                InfinityButtonsBlocks.WAXED_WEATHERED_COPPER_BUTTON,
                InfinityButtonsBlocks.WAXED_WEATHERED_COPPER_LARGE_BUTTON,
        });
        addMultipleAfterInBuildingBlocks(Items.WAXED_OXIDIZED_CUT_COPPER_SLAB, new ItemConvertible[]{
                InfinityButtonsBlocks.WAXED_OXIDIZED_COPPER_BUTTON,
                InfinityButtonsBlocks.WAXED_OXIDIZED_COPPER_LARGE_BUTTON,
        });

        addMultipleAfterInRedstone(Items.STONE_BUTTON, new ItemConvertible[]{
                InfinityButtonsBlocks.WAXED_COPPER_LARGE_BUTTON,
                InfinityButtonsBlocks.STICKY_COPPER_LARGE_BUTTON,
        });

        addAfterInRedstone(Items.REDSTONE_TORCH, InfinityButtonsBlocks.REDSTONE_TORCH_LEVER);

        addMultipleAfterInRedstone(Items.HEAVY_WEIGHTED_PRESSURE_PLATE, new ItemConvertible[]{
                InfinityButtonsBlocks.GOLD_LARGE_BUTTON,
                InfinityButtonsBlocks.IRON_LARGE_BUTTON,
                InfinityButtonsBlocks.EMERALD_LARGE_BUTTON,
        });
        addMultipleAfterInRedstone(Items.REDSTONE_LAMP, new ItemConvertible[]{
                InfinityButtonsBlocks.LAMP_BUTTON,
                InfinityButtonsBlocks.LAMP_LEVER,
        });

        addToColoredBlocks(InfinityButtonsBlocks.WHITE_CONCRETE_POWDER_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.LIGHT_GRAY_CONCRETE_POWDER_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.GRAY_CONCRETE_POWDER_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.BLACK_CONCRETE_POWDER_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.BROWN_CONCRETE_POWDER_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.RED_CONCRETE_POWDER_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.ORANGE_CONCRETE_POWDER_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.YELLOW_CONCRETE_POWDER_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.LIME_CONCRETE_POWDER_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.GREEN_CONCRETE_POWDER_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.CYAN_CONCRETE_POWDER_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.LIGHT_BLUE_CONCRETE_POWDER_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.BLUE_CONCRETE_POWDER_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.PURPLE_CONCRETE_POWDER_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.MAGENTA_CONCRETE_POWDER_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.PINK_CONCRETE_POWDER_BUTTON);

        addToColoredBlocks(InfinityButtonsBlocks.WHITE_CONCRETE_POWDER_LARGE_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.LIGHT_GRAY_CONCRETE_POWDER_LARGE_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.GRAY_CONCRETE_POWDER_LARGE_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.BLACK_CONCRETE_POWDER_LARGE_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.BROWN_CONCRETE_POWDER_LARGE_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.RED_CONCRETE_POWDER_LARGE_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.ORANGE_CONCRETE_POWDER_LARGE_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.YELLOW_CONCRETE_POWDER_LARGE_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.LIME_CONCRETE_POWDER_LARGE_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.GREEN_CONCRETE_POWDER_LARGE_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.CYAN_CONCRETE_POWDER_LARGE_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.LIGHT_BLUE_CONCRETE_POWDER_LARGE_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.BLUE_CONCRETE_POWDER_LARGE_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.PURPLE_CONCRETE_POWDER_LARGE_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.MAGENTA_CONCRETE_POWDER_LARGE_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.PINK_CONCRETE_POWDER_LARGE_BUTTON);

        addToColoredBlocks(InfinityButtonsBlocks.WHITE_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.LIGHT_GRAY_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.GRAY_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.BLACK_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.BROWN_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.RED_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.ORANGE_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.YELLOW_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.LIME_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.GREEN_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.CYAN_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.LIGHT_BLUE_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.BLUE_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.PURPLE_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.MAGENTA_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.PINK_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.FANCY_EMERGENCY_BUTTON);

        addToColoredBlocks(InfinityButtonsBlocks.WHITE_SAFE_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.LIGHT_GRAY_SAFE_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.GRAY_SAFE_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.BLACK_SAFE_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.BROWN_SAFE_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.RED_SAFE_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.ORANGE_SAFE_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.YELLOW_SAFE_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.LIME_SAFE_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.GREEN_SAFE_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.CYAN_SAFE_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.LIGHT_BLUE_SAFE_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.BLUE_SAFE_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.PURPLE_SAFE_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.MAGENTA_SAFE_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.PINK_SAFE_EMERGENCY_BUTTON);
        addToColoredBlocks(InfinityButtonsBlocks.FANCY_SAFE_EMERGENCY_BUTTON);
    }

    public static void addMultipleAfterInBuildingBlocks(ItemConvertible afterItem, ItemConvertible[] list) {
        addAfterInBuildingBlocks(afterItem, list[0]);
        for (int i = 1; i < list.length; i++) {
            addAfterInBuildingBlocks(list[i - 1], list[i]);
        }
    }

    public static void addMultipleAfterInRedstone(ItemConvertible afterItem, ItemConvertible[] list) {
        addAfterInRedstone(afterItem, list[0]);
        for (int i = 1; i < list.length; i++) {
            addAfterInRedstone(list[i - 1], list[i]);
        }
    }

    public static void addAfterInBuildingBlocks(ItemConvertible afterItem, ItemConvertible newItem) {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> entries.addAfter(afterItem, newItem));
    }

    public static void addAfterInRedstone(ItemConvertible afterItem, ItemConvertible newItem) {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> entries.addAfter(afterItem, newItem));
    }

    public static void addToColoredBlocks(Block item) {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(entries -> entries.add(item));
    }

    public static void loadItemGroup() {
        InfinityButtonsInit.LOGGER.debug("Registering Item Group for Infinity Buttons");
        registerGroup();
        addButtonsToVanillaTabs();
    }
}
