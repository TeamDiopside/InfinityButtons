package net.larsmans.infinitybuttons.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.larsmans.infinitybuttons.block.custom.Doorbell;
import net.larsmans.infinitybuttons.block.custom.DoorbellButton;
import net.larsmans.infinitybuttons.block.custom.LampButton;
import net.larsmans.infinitybuttons.block.custom.LampLever;
import net.larsmans.infinitybuttons.block.custom.button.*;
import net.larsmans.infinitybuttons.block.custom.emergencybutton.EmergencyButton;
import net.larsmans.infinitybuttons.block.custom.emergencybutton.SafeEmergencyButton;
import net.larsmans.infinitybuttons.block.custom.largebutton.*;
import net.larsmans.infinitybuttons.block.custom.secretbutton.*;
import net.larsmans.infinitybuttons.block.custom.torch.*;
import net.larsmans.infinitybuttons.item.InfinityButtonsItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.ToIntFunction;


public class InfinityButtonsBlocks {

    /**
     * Buttons
     */

    public static final Block DEEPSLATE_BUTTON = registerStoneButton("deepslate");
    public static final Block GRANITE_BUTTON = registerStoneButton("granite");
    public static final Block DIORITE_BUTTON = registerStoneButton("diorite");
    public static final Block ANDESITE_BUTTON = registerStoneButton("andesite");
    public static final Block CALCITE_BUTTON = registerStoneButton("calcite");
    public static final Block TUFF_BUTTON = registerStoneButton("tuff");
    public static final Block DRIPSTONE_BUTTON = registerStoneButton("dripstone");

    public static final Block COPPER_BUTTON = registerCopperButton("");
    public static final Block EXPOSED_COPPER_BUTTON = registerCopperButton("exposed_");
    public static final Block WEATHERED_COPPER_BUTTON = registerCopperButton("weathered_");
    public static final Block OXIDIZED_COPPER_BUTTON = registerCopperButton("oxidized_");

    public static final Block STICKY_COPPER_BUTTON = registerStickyCopperButton("");
    public static final Block STICKY_EXPOSED_COPPER_BUTTON = registerStickyCopperButton("exposed_");
    public static final Block STICKY_WEATHERED_COPPER_BUTTON = registerStickyCopperButton("weathered_");
    public static final Block STICKY_OXIDIZED_COPPER_BUTTON = registerStickyCopperButton("oxidized_");

    public static final Block IRON_BUTTON = registerArrowButton("iron");
    public static final Block GOLD_BUTTON = registerArrowButton("gold");

    public static final Block EMERALD_BUTTON = registerBlockWithItem("emerald_button",
            new EmeraldButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)));

    public static final Block DIAMOND_BUTTON = registerBlockWithItem("diamond_button",
            new DiamondButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)));

    public static final Block PRISMARINE_BUTTON = registerPrismarineButton("prismarine");
    public static final Block PRISMARINE_BRICK_BUTTON = registerPrismarineButton("prismarine_brick");
    public static final Block DARK_PRISMARINE_BUTTON = registerPrismarineButton("dark_prismarine");

    public static final Block SAND_BUTTON = registerSandButton("sand", false);
    public static final Block RED_SAND_BUTTON = registerSandButton("red_sand", false);
    public static final Block GRAVEL_BUTTON = registerSandButton("gravel", true);

    public static final Block RED_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("red");
    public static final Block ORANGE_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("orange");
    public static final Block YELLOW_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("yellow");
    public static final Block LIME_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("lime");
    public static final Block GREEN_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("green");
    public static final Block CYAN_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("cyan");
    public static final Block LIGHT_BLUE_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("light_blue");
    public static final Block BLUE_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("blue");
    public static final Block PURPLE_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("purple");
    public static final Block MAGENTA_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("magenta");
    public static final Block PINK_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("pink");
    public static final Block BROWN_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("brown");
    public static final Block WHITE_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("white");
    public static final Block LIGHT_GRAY_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("light_gray");
    public static final Block GRAY_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("gray");
    public static final Block BLACK_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("black");

    public static Block registerStoneButton(String name) {
        return registerBlockWithItem(name + "_button", new StoneButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.STONE)));
    }

    public static Block registerCopperButton(String name) {
        return registerBlockWithItem(name + "copper_button", new CopperButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)));
    }

    public static Block registerStickyCopperButton(String name) {
        return registerBlockWithItem("sticky_" + name + "copper_button", new StickyCopperButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)));
    }

    public static Block registerArrowButton(String name) {
        return registerBlockWithItem(name + "_button", new ArrowButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)));
    }

    public static Block registerPrismarineButton(String name) {
        return registerBlockWithItem(name + "_button", new PrismarineButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.STONE)));
    }

    public static Block registerConcretePowderButton(String name) {
        return registerSandButton(name + "_concrete_powder", false);
    }

    public static Block registerSandButton(String name, boolean gravel) {
        return registerBlockWithItem(name + "_button", new FallingButton(gravel, FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(gravel ? BlockSoundGroup.GRAVEL : BlockSoundGroup.SAND)));
    }

    /**
     * Large Buttons
     */

    public static final Block OAK_LARGE_BUTTON = registerWoodenLargeButton("oak");
    public static final Block SPRUCE_LARGE_BUTTON = registerWoodenLargeButton("spruce");
    public static final Block BIRCH_LARGE_BUTTON = registerWoodenLargeButton("birch");
    public static final Block ACACIA_LARGE_BUTTON = registerWoodenLargeButton("acacia");
    public static final Block DARK_OAK_LARGE_BUTTON = registerWoodenLargeButton("dark_oak");
    public static final Block JUNGLE_LARGE_BUTTON = registerWoodenLargeButton("jungle");
    public static final Block MANGROVE_LARGE_BUTTON = registerWoodenLargeButton("mangrove");
    public static final Block WARPED_LARGE_BUTTON = registerWoodenLargeButton("warped");
    public static final Block CRIMSON_LARGE_BUTTON = registerWoodenLargeButton("crimson");

    public static final Block STONE_LARGE_BUTTON = registerStoneLargeButton("stone");
    public static final Block DEEPSLATE_LARGE_BUTTON = registerStoneLargeButton("deepslate");
    public static final Block GRANITE_LARGE_BUTTON = registerStoneLargeButton("granite");
    public static final Block DIORITE_LARGE_BUTTON = registerStoneLargeButton("diorite");
    public static final Block ANDESITE_LARGE_BUTTON = registerStoneLargeButton("andesite");
    public static final Block CALCITE_LARGE_BUTTON = registerStoneLargeButton("calcite");
    public static final Block TUFF_LARGE_BUTTON = registerStoneLargeButton("tuff");
    public static final Block DRIPSTONE_LARGE_BUTTON = registerStoneLargeButton("dripstone");
    public static final Block POLISHED_BLACKSTONE_LARGE_BUTTON = registerStoneLargeButton("polished_blackstone");

    public static final Block COPPER_LARGE_BUTTON = registerCopperLargeButton("");
    public static final Block EXPOSED_COPPER_LARGE_BUTTON = registerCopperLargeButton("exposed_");
    public static final Block WEATHERED_COPPER_LARGE_BUTTON = registerCopperLargeButton("weathered_");
    public static final Block OXIDIZED_COPPER_LARGE_BUTTON = registerCopperLargeButton("oxidized_");

    public static final Block STICKY_COPPER_LARGE_BUTTON = registerStickyCopperLargeButton("");
    public static final Block STICKY_EXPOSED_COPPER_LARGE_BUTTON = registerStickyCopperLargeButton("exposed_");
    public static final Block STICKY_WEATHERED_COPPER_LARGE_BUTTON = registerStickyCopperLargeButton("weathered_");
    public static final Block STICKY_OXIDIZED_COPPER_LARGE_BUTTON = registerStickyCopperLargeButton("oxidized_");

    public static final Block IRON_LARGE_BUTTON = registerArrowLargeButton("iron");
    public static final Block GOLD_LARGE_BUTTON = registerArrowLargeButton("gold");

    public static final Block EMERALD_LARGE_BUTTON = registerBlockWithItem("emerald_large_button",
            new EmeraldLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)));

    public static final Block DIAMOND_LARGE_BUTTON = registerBlockWithItem("diamond_large_button",
            new DiamondLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)));

    public static final Block PRISMARINE_LARGE_BUTTON = registerPrismarineLargeButton("prismarine");
    public static final Block PRISMARINE_BRICK_LARGE_BUTTON = registerPrismarineLargeButton("prismarine_brick");
    public static final Block DARK_PRISMARINE_LARGE_BUTTON = registerPrismarineLargeButton("dark_prismarine");

    public static final Block SAND_LARGE_BUTTON = registerSandLargeButton("sand", false);
    public static final Block RED_SAND_LARGE_BUTTON = registerSandLargeButton("red_sand", false);
    public static final Block GRAVEL_LARGE_BUTTON = registerSandLargeButton("gravel", true);

    public static final Block RED_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("red");
    public static final Block ORANGE_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("orange");
    public static final Block YELLOW_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("yellow");
    public static final Block LIME_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("lime");
    public static final Block GREEN_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("green");
    public static final Block CYAN_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("cyan");
    public static final Block LIGHT_BLUE_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("light_blue");
    public static final Block BLUE_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("blue");
    public static final Block PURPLE_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("purple");
    public static final Block MAGENTA_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("magenta");
    public static final Block PINK_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("pink");
    public static final Block BROWN_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("brown");
    public static final Block WHITE_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("white");
    public static final Block LIGHT_GRAY_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("light_gray");
    public static final Block GRAY_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("gray");
    public static final Block BLACK_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("black");

    public static Block registerWoodenLargeButton(String name) {
        return registerBlockWithItem(name + "_large_button", new WoodenLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.WOOD)));
    }

    public static Block registerStoneLargeButton(String name) {
        return registerBlockWithItem(name + "_large_button", new StoneLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.STONE)));
    }

    public static Block registerCopperLargeButton(String name) {
        return registerBlockWithItem(name + "copper_large_button", new CopperLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)));
    }

    public static Block registerStickyCopperLargeButton(String name) {
        return registerBlockWithItem("sticky_" + name + "copper_large_button", new StickyCopperLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)));
    }

    public static Block registerArrowLargeButton(String name) {
        return registerBlockWithItem(name + "_large_button", new ArrowLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL)));
    }

    public static Block registerPrismarineLargeButton(String name) {
        return registerBlockWithItem(name + "_large_button", new PrismarineLargeButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.STONE)));
    }

    public static Block registerConcretePowderLargeButton(String name) {
        return registerSandLargeButton(name + "_concrete_powder", false);
    }

    public static Block registerSandLargeButton(String name, boolean gravel) {
        return registerBlockWithItem(name + "_large_button", new FallingLargeButton(gravel, FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(gravel ? BlockSoundGroup.GRAVEL : BlockSoundGroup.SAND)));
    }

    /**
     * Emergency Buttons
     */

    public static final Block RED_EMERGENCY_BUTTON = registerEmergencyButton("red");
    public static final Block ORANGE_EMERGENCY_BUTTON = registerEmergencyButton("orange");
    public static final Block YELLOW_EMERGENCY_BUTTON = registerEmergencyButton("yellow");
    public static final Block LIME_EMERGENCY_BUTTON = registerEmergencyButton("lime");
    public static final Block GREEN_EMERGENCY_BUTTON = registerEmergencyButton("green");
    public static final Block CYAN_EMERGENCY_BUTTON = registerEmergencyButton("cyan");
    public static final Block LIGHT_BLUE_EMERGENCY_BUTTON = registerEmergencyButton("light_blue");
    public static final Block BLUE_EMERGENCY_BUTTON = registerEmergencyButton("blue");
    public static final Block PURPLE_EMERGENCY_BUTTON = registerEmergencyButton("purple");
    public static final Block MAGENTA_EMERGENCY_BUTTON = registerEmergencyButton("magenta");
    public static final Block PINK_EMERGENCY_BUTTON = registerEmergencyButton("pink");
    public static final Block BROWN_EMERGENCY_BUTTON = registerEmergencyButton("brown");
    public static final Block WHITE_EMERGENCY_BUTTON = registerEmergencyButton("white");
    public static final Block LIGHT_GRAY_EMERGENCY_BUTTON = registerEmergencyButton("light_gray");
    public static final Block GRAY_EMERGENCY_BUTTON = registerEmergencyButton("gray");
    public static final Block BLACK_EMERGENCY_BUTTON = registerEmergencyButton("black");
    public static final Block FANCY_EMERGENCY_BUTTON = registerEmergencyButton("fancy");

    public static final Block RED_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("red");
    public static final Block ORANGE_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("orange");
    public static final Block YELLOW_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("yellow");
    public static final Block LIME_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("lime");
    public static final Block GREEN_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("green");
    public static final Block CYAN_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("cyan");
    public static final Block LIGHT_BLUE_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("light_blue");
    public static final Block BLUE_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("blue");
    public static final Block PURPLE_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("purple");
    public static final Block MAGENTA_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("magenta");
    public static final Block PINK_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("pink");
    public static final Block BROWN_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("brown");
    public static final Block WHITE_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("white");
    public static final Block LIGHT_GRAY_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("light_gray");
    public static final Block GRAY_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("gray");
    public static final Block BLACK_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("black");
    public static final Block FANCY_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("fancy");

    public static Block registerEmergencyButton(String name) {
        return registerBlockWithItem(name + "_emergency_button", new EmergencyButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).nonOpaque().sounds(BlockSoundGroup.METAL)));
    }

    public static Block registerSafeEmergencyButton(String name) {
        return registerBlockWithItem(name + "_safe_emergency_button", new SafeEmergencyButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).nonOpaque().sounds(BlockSoundGroup.METAL)));
    }

    /**
     * Secret Buttons
     */

    public static final Block BOOKSHELF_SECRET_BUTTON = registerBlockWithItem("bookshelf_secret_button",
            new BookshelfSecretButton(FabricBlockSettings.of(Material.WOOD).nonOpaque().sounds(BlockSoundGroup.WOOD).strength(1.5f)));

    public static final Block BRICK_SECRET_BUTTON = registerBlockWithItem("brick_secret_button",
            new FullBlockBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.RED).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool().strength(2.0f, 6.0f)));

    public static final Block STONE_BRICK_SECRET_BUTTON = registerBlockWithItem("stone_brick_secret_button",
            new BigBrickSecretButton(FabricBlockSettings.of(Material.STONE).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool().strength(1.5f, 6.0f)));

    public static final Block MOSSY_STONE_BRICK_SECRET_BUTTON = registerBlockWithItem("mossy_stone_brick_secret_button",
            new BigBrickSecretButton(FabricBlockSettings.of(Material.STONE).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool().strength(1.5f, 6.0f)));

    public static final Block CRACKED_STONE_BRICK_SECRET_BUTTON = registerBlockWithItem("cracked_stone_brick_secret_button",
            new BigBrickSecretButton(FabricBlockSettings.of(Material.STONE).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool().strength(1.5f, 6.0f)));

    public static final Block CHISELED_STONE_BRICK_SECRET_BUTTON = registerBlockWithItem("chiseled_stone_brick_secret_button",
            new ChiseledStoneBrickSecretButton(FabricBlockSettings.of(Material.STONE).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool().strength(1.5f, 6.0f)));

    public static final Block DEEPSLATE_BRICK_SECRET_BUTTON = registerBlockWithItem("deepslate_brick_secret_button",
            new BigBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.DEEPSLATE_GRAY).nonOpaque().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).requiresTool().strength(3.5f, 6.0f)));

    public static final Block CRACKED_DEEPSLATE_BRICK_SECRET_BUTTON = registerBlockWithItem("cracked_deepslate_brick_secret_button",
            new BigBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.DEEPSLATE_GRAY).nonOpaque().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).requiresTool().strength(3.5f, 6.0f)));

    public static final Block DEEPSLATE_TILE_SECRET_BUTTON = registerBlockWithItem("deepslate_tile_secret_button",
            new DeepslateTileSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.DEEPSLATE_GRAY).nonOpaque().sounds(BlockSoundGroup.DEEPSLATE_TILES).requiresTool().strength(3.5f, 6.0f)));

    public static final Block CRACKED_DEEPSLATE_TILE_SECRET_BUTTON = registerBlockWithItem("cracked_deepslate_tile_secret_button",
            new DeepslateTileSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.DEEPSLATE_GRAY).nonOpaque().sounds(BlockSoundGroup.DEEPSLATE_TILES).requiresTool().strength(3.5f, 6.0f)));

    public static final Block OAK_PLANK_SECRET_BUTTON = registerPlankSecretButton("oak", Material.WOOD, MapColor.OAK_TAN);
    public static final Block BIRCH_PLANK_SECRET_BUTTON = registerPlankSecretButton("birch", Material.WOOD, MapColor.PALE_YELLOW);
    public static final Block SPRUCE_PLANK_SECRET_BUTTON = registerPlankSecretButton("spruce", Material.WOOD, MapColor.SPRUCE_BROWN);
    public static final Block ACACIA_PLANK_SECRET_BUTTON = registerPlankSecretButton("acacia", Material.WOOD, MapColor.ORANGE);
    public static final Block DARK_OAK_PLANK_SECRET_BUTTON = registerPlankSecretButton("dark_oak", Material.WOOD, MapColor.BROWN);
    public static final Block JUNGLE_PLANK_SECRET_BUTTON = registerPlankSecretButton("jungle", Material.WOOD, MapColor.DIRT_BROWN);
    public static final Block MANGROVE_PLANK_SECRET_BUTTON = registerPlankSecretButton("mangrove", Material.WOOD, MapColor.RED);
    public static final Block CRIMSON_PLANK_SECRET_BUTTON = registerPlankSecretButton("crimson", Material.NETHER_WOOD, MapColor.DULL_PINK);
    public static final Block WARPED_PLANK_SECRET_BUTTON = registerPlankSecretButton("warped", Material.NETHER_WOOD, MapColor.DARK_AQUA);

    public static final Block MUD_BRICK_SECRET_BUTTON = registerBlockWithItem("mud_brick_secret_button",
            new MudBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.TERRACOTTA_LIGHT_GRAY).nonOpaque().sounds(BlockSoundGroup.MUD_BRICKS).requiresTool().strength(1.5f, 3.0f)));

    public static final Block END_STONE_BRICK_SECRET_BUTTON = registerBlockWithItem("end_stone_brick_secret_button",
            new BigBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.PALE_YELLOW).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool().strength(3.0f, 9.0f)));

    public static final Block QUARTZ_BRICK_SECRET_BUTTON = registerBlockWithItem("quartz_brick_secret_button", new BigBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.OFF_WHITE).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool().strength(0.8f)));

    public static final Block DARK_PRISMARINE_SECRET_BUTTON = registerBlockWithItem("dark_prismarine_secret_button",
            new FullBlockBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.DIAMOND_BLUE).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool().strength(1.5f, 6.0f)));

    public static final Block POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON = registerBlockWithItem("polished_blackstone_brick_secret_button", new BigBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.BLACK).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool().strength(2.0f, 6.0f)));

    public static final Block CRACKED_POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON = registerBlockWithItem("cracked_polished_blackstone_brick_secret_button", new BigBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.BLACK).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool().strength(2.0f, 6.0f)));

    public static final Block CHISELED_POLISHED_BLACKSTONE_SECRET_BUTTON = registerBlockWithItem("chiseled_polished_blackstone_secret_button",
            new ChiseledStoneBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.BLACK).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool().strength(2.0f, 6.0f)));

    public static final Block NETHER_BRICK_SECRET_BUTTON = registerBlockWithItem("nether_brick_secret_button",
            new FullBlockBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.DARK_RED).nonOpaque().sounds(BlockSoundGroup.NETHER_BRICKS).requiresTool().strength(2.0f, 6.0f)));

    public static final Block CRACKED_NETHER_BRICK_SECRET_BUTTON = registerBlockWithItem("cracked_nether_brick_secret_button",
            new FullBlockBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.DARK_RED).nonOpaque().sounds(BlockSoundGroup.NETHER_BRICKS).requiresTool().strength(2.0f, 6.0f)));

    public static final Block CHISELED_NETHER_BRICK_SECRET_BUTTON = registerBlockWithItem("chiseled_nether_brick_secret_button",
            new ChiseledNetherBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.DARK_RED).nonOpaque().sounds(BlockSoundGroup.NETHER_BRICKS).requiresTool().strength(2.0f, 6.0f)));

    public static final Block RED_NETHER_BRICK_SECRET_BUTTON = registerBlockWithItem("red_nether_brick_secret_button",
            new FullBlockBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.DARK_RED).nonOpaque().sounds(BlockSoundGroup.NETHER_BRICKS).requiresTool().strength(2.0f, 6.0f)));

    public static Block registerPlankSecretButton(String name, Material material, MapColor color) {
        return registerBlockWithItem(name + "_plank_secret_button", new PlankSecretButton(FabricBlockSettings.of(material, color).nonOpaque().sounds(BlockSoundGroup.WOOD).strength(2.0f, 3.0f)));
    }

    /**
     * Misc
     */

    public static final Block DOORBELL = registerBlockWithItem("doorbell",
            new Doorbell(FabricBlockSettings.of(Material.DECORATION).nonOpaque().noCollision().strength(0.5f).sounds(BlockSoundGroup.WOOD)));

    public static final Block DOORBELL_BUTTON = registerBlockWithItem("doorbell_button",
            new DoorbellButton(FabricBlockSettings.of(Material.DECORATION).nonOpaque().noCollision().strength(0.5f).sounds(BlockSoundGroup.WOOD)));

    public static final Block LAMP_BUTTON = registerBlockWithItem("lamp_button",
            new LampButton(FabricBlockSettings.of(Material.DECORATION).nonOpaque().strength(0.3f).sounds(BlockSoundGroup.GLASS).luminance(getLampButtonLight())));

    public static final Block LAMP_LEVER = registerBlockWithItem("lamp_lever",
            new LampLever(FabricBlockSettings.of(Material.DECORATION).nonOpaque().strength(0.3f).sounds(BlockSoundGroup.GLASS).luminance(getLampButtonLight())));

    /**
     * Torches
     */

    public static final Block TORCH_BUTTON = registerOnlyBlock("torch_button",
            new TorchButton(FabricBlockSettings.of(Material.DECORATION).nonOpaque().noCollision().breakInstantly().luminance(14).sounds(BlockSoundGroup.WOOD), ParticleTypes.FLAME));

    public static final Block WALL_TORCH_BUTTON = registerOnlyBlock("wall_torch_button",
            new WallTorchButton(FabricBlockSettings.of(Material.DECORATION).nonOpaque().noCollision().breakInstantly().luminance(14).sounds(BlockSoundGroup.WOOD).dropsLike(TORCH_BUTTON), ParticleTypes.FLAME));

    public static final Block TORCH_LEVER = registerOnlyBlock("torch_lever",
            new TorchLever(FabricBlockSettings.of(Material.DECORATION).nonOpaque().noCollision().breakInstantly().luminance(14).sounds(BlockSoundGroup.WOOD), ParticleTypes.FLAME));

    public static final Block WALL_TORCH_LEVER = registerOnlyBlock("wall_torch_lever",
            new WallTorchLever(FabricBlockSettings.of(Material.DECORATION).nonOpaque().noCollision().breakInstantly().luminance(14).sounds(BlockSoundGroup.WOOD).dropsLike(TORCH_LEVER), ParticleTypes.FLAME));

    public static final Block SOUL_TORCH_BUTTON = registerOnlyBlock("soul_torch_button",
            new TorchButton(FabricBlockSettings.of(Material.DECORATION).nonOpaque().noCollision().breakInstantly().luminance(10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));

    public static final Block SOUL_WALL_TORCH_BUTTON = registerOnlyBlock("soul_wall_torch_button",
            new WallTorchButton(FabricBlockSettings.of(Material.DECORATION).nonOpaque().noCollision().breakInstantly().luminance(10).sounds(BlockSoundGroup.WOOD).dropsLike(SOUL_TORCH_BUTTON), ParticleTypes.SOUL_FIRE_FLAME));

    public static final Block SOUL_TORCH_LEVER = registerOnlyBlock("soul_torch_lever",
            new TorchLever(FabricBlockSettings.of(Material.DECORATION).nonOpaque().noCollision().breakInstantly().luminance(10).sounds(BlockSoundGroup.WOOD), ParticleTypes.SOUL_FIRE_FLAME));

    public static final Block SOUL_WALL_TORCH_LEVER = registerOnlyBlock("soul_wall_torch_lever",
            new WallTorchLever(FabricBlockSettings.of(Material.DECORATION).nonOpaque().noCollision().breakInstantly().luminance(10).sounds(BlockSoundGroup.WOOD).dropsLike(SOUL_TORCH_LEVER), ParticleTypes.SOUL_FIRE_FLAME));

    public static final Block REDSTONE_TORCH_BUTTON = registerOnlyBlock("redstone_torch_button",
            new RedstoneTorchButton(FabricBlockSettings.of(Material.DECORATION).nonOpaque().noCollision().breakInstantly().luminance(7).sounds(BlockSoundGroup.WOOD)));

    public static final Block REDSTONE_WALL_TORCH_BUTTON = registerOnlyBlock("redstone_wall_torch_button",
            new RedstoneWallTorchButton(FabricBlockSettings.of(Material.DECORATION).nonOpaque().noCollision().breakInstantly().luminance(7).sounds(BlockSoundGroup.WOOD).dropsLike(REDSTONE_TORCH_BUTTON)));

    public static final Block REDSTONE_TORCH_LEVER = registerOnlyBlock("redstone_torch_lever",
            new RedstoneTorchLever(FabricBlockSettings.of(Material.DECORATION).nonOpaque().noCollision().breakInstantly().luminance(7).sounds(BlockSoundGroup.WOOD)));

    public static final Block REDSTONE_WALL_TORCH_LEVER = registerOnlyBlock("redstone_wall_torch_lever",
            new RedstoneWallTorchLever(FabricBlockSettings.of(Material.DECORATION).nonOpaque().noCollision().breakInstantly().luminance(7).sounds(BlockSoundGroup.WOOD).dropsLike(REDSTONE_TORCH_LEVER)));

    /**
     * Methods
     */

    private static Block registerBlockWithItem(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registry.BLOCK, new Identifier("infinitybuttons", name), block);
    }

    private static Block registerOnlyBlock(String name, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier("infinitybuttons", name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registry.ITEM, new Identifier("infinitybuttons", name),
                new BlockItem(block, new FabricItemSettings().group(InfinityButtonsItemGroup.INFINITYBUTTONS)));
    }

    public static void registerModBlocks() {
        InfinityButtonsInit.LOGGER.debug("Registering Mod Blocks for Infinity Buttons");
    }

    private static ToIntFunction<BlockState> getLampButtonLight() {
        return state -> state.get(LampButton.PRESSED) ? 15 : 0;
    }
}
