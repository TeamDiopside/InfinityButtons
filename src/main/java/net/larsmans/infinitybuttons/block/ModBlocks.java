package net.larsmans.infinitybuttons.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.larsmans.infinitybuttons.InfinityButtons;
import net.larsmans.infinitybuttons.block.custom.*;
import net.larsmans.infinitybuttons.item.ModItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModBlocks {

    /*
    -------------
    Large Buttons
    -------------
    */

    public static final Block OAK_LARGE_BUTTON = registerBlock("oak_large_button",
            new WoodenLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.WOOD)), ModItemGroup.INFINITYBUTTONS);

    public static final Block BIRCH_LARGE_BUTTON = registerBlock("birch_large_button",
            new WoodenLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.WOOD)), ModItemGroup.INFINITYBUTTONS);

    public static final Block SPRUCE_LARGE_BUTTON = registerBlock("spruce_large_button",
            new WoodenLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.WOOD)), ModItemGroup.INFINITYBUTTONS);

    public static final Block ACACIA_LARGE_BUTTON = registerBlock("acacia_large_button",
            new WoodenLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.WOOD)), ModItemGroup.INFINITYBUTTONS);

    public static final Block DARK_OAK_LARGE_BUTTON = registerBlock("dark_oak_large_button",
            new WoodenLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.WOOD)), ModItemGroup.INFINITYBUTTONS);

    public static final Block JUNGLE_LARGE_BUTTON = registerBlock("jungle_large_button",
            new WoodenLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.WOOD)), ModItemGroup.INFINITYBUTTONS);

    public static final Block MANGROVE_LARGE_BUTTON = registerBlock("mangrove_large_button",
            new WoodenLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.WOOD)), ModItemGroup.INFINITYBUTTONS);

    public static final Block WARPED_LARGE_BUTTON = registerBlock("warped_large_button",
            new WoodenLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.WOOD)), ModItemGroup.INFINITYBUTTONS);

    public static final Block CRIMSON_LARGE_BUTTON = registerBlock("crimson_large_button",
            new WoodenLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.WOOD)), ModItemGroup.INFINITYBUTTONS);

    public static final Block STONE_LARGE_BUTTON = registerBlock("stone_large_button",
            new StoneLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.STONE)), ModItemGroup.INFINITYBUTTONS);

    public static final Block POLISHED_BLACKSTONE_LARGE_BUTTON = registerBlock("polished_blackstone_large_button",
            new StoneLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.STONE)), ModItemGroup.INFINITYBUTTONS);

    public static final Block COPPER_LARGE_BUTTON = registerBlock("copper_large_button",
            new CopperLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.COPPER)), ModItemGroup.INFINITYBUTTONS);

    public static final Block EXPOSED_COPPER_LARGE_BUTTON = registerBlock("exposed_copper_large_button",
            new CopperLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.COPPER)), ModItemGroup.INFINITYBUTTONS);

    public static final Block WEATHERED_COPPER_LARGE_BUTTON = registerBlock("weathered_copper_large_button",
            new CopperLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.COPPER)), ModItemGroup.INFINITYBUTTONS);

    public static final Block OXIDIZED_COPPER_LARGE_BUTTON = registerBlock("oxidized_copper_large_button",
            new CopperLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.COPPER)), ModItemGroup.INFINITYBUTTONS);

    public static final Block STICKY_COPPER_LARGE_BUTTON = registerBlock("sticky_copper_large_button",
            new StickyCopperLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.COPPER)), ModItemGroup.INFINITYBUTTONS);

    public static final Block STICKY_EXPOSED_COPPER_LARGE_BUTTON = registerBlock("sticky_exposed_copper_large_button",
            new StickyCopperLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.COPPER)), ModItemGroup.INFINITYBUTTONS);

    public static final Block STICKY_WEATHERED_COPPER_LARGE_BUTTON = registerBlock("sticky_weathered_copper_large_button",
            new StickyCopperLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.COPPER)), ModItemGroup.INFINITYBUTTONS);

    public static final Block STICKY_OXIDIZED_COPPER_LARGE_BUTTON = registerBlock("sticky_oxidized_copper_large_button",
            new StickyCopperLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.COPPER)), ModItemGroup.INFINITYBUTTONS);

    public static final Block EMERALD_LARGE_BUTTON = registerBlock("emerald_large_button",
            new EmeraldLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block IRON_LARGE_BUTTON = registerBlock("iron_large_button",
            new ArrowLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block GOLD_LARGE_BUTTON = registerBlock("gold_large_button",
            new ArrowLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    /*
    -------
    Buttons
    -------
    */

    public static final Block COPPER_BUTTON = registerBlock("copper_button",
            new CopperButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block EXPOSED_COPPER_BUTTON = registerBlock("exposed_copper_button",
            new CopperButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block WEATHERED_COPPER_BUTTON = registerBlock("weathered_copper_button",
            new CopperButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block OXIDIZED_COPPER_BUTTON = registerBlock("oxidized_copper_button",
            new CopperButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block STICKY_COPPER_BUTTON = registerBlock("sticky_copper_button",
            new StickyCopperButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block STICKY_EXPOSED_COPPER_BUTTON = registerBlock("sticky_exposed_copper_button",
            new StickyCopperButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block STICKY_WEATHERED_COPPER_BUTTON = registerBlock("sticky_weathered_copper_button",
            new StickyCopperButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block STICKY_OXIDIZED_COPPER_BUTTON = registerBlock("sticky_oxidized_copper_button",
            new StickyCopperButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block EMERALD_BUTTON = registerBlock("emerald_button",
            new EmeraldButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block IRON_BUTTON = registerBlock("iron_button",
            new ArrowButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block GOLD_BUTTON = registerBlock("gold_button",
            new ArrowButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f)
                    .collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    /*
    -----------------
    Emergency Buttons
    -----------------
    */

    public static final Block RED_EMERGENCY_BUTTON = registerBlock("red_emergency_button",
            new EmergencyButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block ORANGE_EMERGENCY_BUTTON = registerBlock("orange_emergency_button",
            new EmergencyButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block YELLOW_EMERGENCY_BUTTON = registerBlock("yellow_emergency_button",
            new EmergencyButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block LIME_EMERGENCY_BUTTON = registerBlock("lime_emergency_button",
            new EmergencyButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block GREEN_EMERGENCY_BUTTON = registerBlock("green_emergency_button",
            new EmergencyButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block CYAN_EMERGENCY_BUTTON = registerBlock("cyan_emergency_button",
            new EmergencyButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block LIGHT_BLUE_EMERGENCY_BUTTON = registerBlock("light_blue_emergency_button",
            new EmergencyButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block BLUE_EMERGENCY_BUTTON = registerBlock("blue_emergency_button",
            new EmergencyButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block PURPLE_EMERGENCY_BUTTON = registerBlock("purple_emergency_button",
            new EmergencyButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block MAGENTA_EMERGENCY_BUTTON = registerBlock("magenta_emergency_button",
            new EmergencyButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block PINK_EMERGENCY_BUTTON = registerBlock("pink_emergency_button",
            new EmergencyButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block BROWN_EMERGENCY_BUTTON = registerBlock("brown_emergency_button",
            new EmergencyButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block WHITE_EMERGENCY_BUTTON = registerBlock("white_emergency_button",
            new EmergencyButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block LIGHT_GRAY_EMERGENCY_BUTTON = registerBlock("light_gray_emergency_button",
            new EmergencyButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block GRAY_EMERGENCY_BUTTON = registerBlock("gray_emergency_button",
            new EmergencyButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);

    public static final Block BLACK_EMERGENCY_BUTTON = registerBlock("black_emergency_button",
            new EmergencyButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.INFINITYBUTTONS);



    private static Block registerBlock(String name, Block block, ItemGroup tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new Identifier(InfinityButtons.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup tab) {
        return Registry.register(Registry.ITEM, new Identifier(InfinityButtons.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(tab)));
    }

    public static void registerModBlocks() {
        InfinityButtons.LOGGER.debug("Registering Mod Blocks for " + InfinityButtons.MOD_ID);
    }
}
