package net.larsmans.infinitybuttons.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.larsmans.infinitybuttons.block.custom.Doorbell;
import net.larsmans.infinitybuttons.block.custom.DoorbellButton;
import net.larsmans.infinitybuttons.block.custom.LampButton;
import net.larsmans.infinitybuttons.block.custom.LanternButton;
import net.larsmans.infinitybuttons.block.custom.button.*;
import net.larsmans.infinitybuttons.block.custom.consolebutton.ConsoleButton;
import net.larsmans.infinitybuttons.block.custom.consolebutton.LargeConsoleButton;
import net.larsmans.infinitybuttons.block.custom.consolebutton.SmallConsoleButton;
import net.larsmans.infinitybuttons.block.custom.emergencybutton.EmergencyButton;
import net.larsmans.infinitybuttons.block.custom.emergencybutton.SafeEmergencyButton;
import net.larsmans.infinitybuttons.block.custom.letterbutton.LetterButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.*;
import net.larsmans.infinitybuttons.block.custom.torch.*;
import net.larsmans.infinitybuttons.item.InfinityButtonsItemGroup;
import net.larsmans.infinitybuttons.item.SafeEmergencyButtonItem;
import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
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

    public static final Block COPPER_BUTTON = registerCopperButton("copper", Oxidizable.OxidationLevel.UNAFFECTED);
    public static final Block EXPOSED_COPPER_BUTTON = registerCopperButton("exposed_copper", Oxidizable.OxidationLevel.EXPOSED);
    public static final Block WEATHERED_COPPER_BUTTON = registerCopperButton("weathered_copper", Oxidizable.OxidationLevel.WEATHERED);
    public static final Block OXIDIZED_COPPER_BUTTON = registerCopperButton("oxidized_copper", Oxidizable.OxidationLevel.OXIDIZED);

    public static final Block WAXED_COPPER_BUTTON = registerWaxedCopperButton("copper");
    public static final Block WAXED_EXPOSED_COPPER_BUTTON = registerWaxedCopperButton("exposed_copper");
    public static final Block WAXED_WEATHERED_COPPER_BUTTON = registerWaxedCopperButton("weathered_copper");
    public static final Block WAXED_OXIDIZED_COPPER_BUTTON = registerWaxedCopperButton("oxidized_copper");

    public static final Block STICKY_COPPER_BUTTON = registerStickyCopperButton("copper");
    public static final Block STICKY_EXPOSED_COPPER_BUTTON = registerStickyCopperButton("exposed_copper");
    public static final Block STICKY_WEATHERED_COPPER_BUTTON = registerStickyCopperButton("weathered_copper");
    public static final Block STICKY_OXIDIZED_COPPER_BUTTON = registerStickyCopperButton("oxidized_copper");

    public static final Block IRON_BUTTON = registerArrowButton("iron");
    public static final Block GOLD_BUTTON = registerArrowButton("gold");

    public static final Block EMERALD_BUTTON = registerBlockWithItem("emerald_button",
            new EmeraldButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL), false));

    public static final Block DIAMOND_BUTTON = registerBlockWithItem("diamond_button",
            new DiamondButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL), false));

    public static final Block PRISMARINE_BUTTON = registerPrismarineButton("prismarine");
    public static final Block PRISMARINE_BRICK_BUTTON = registerPrismarineButton("prismarine_brick");
    public static final Block DARK_PRISMARINE_BUTTON = registerPrismarineButton("dark_prismarine");

    public static final Block SAND_BUTTON = registerSandButton("sand", false);
    public static final Block RED_SAND_BUTTON = registerSandButton("red_sand", false);
    public static final Block GRAVEL_BUTTON = registerSandButton("gravel", true);

    public static final Block WHITE_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("white");
    public static final Block LIGHT_GRAY_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("light_gray");
    public static final Block GRAY_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("gray");
    public static final Block BLACK_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("black");
    public static final Block BROWN_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("brown");
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

    public static Block registerStoneButton(String name) {
        return registerBlockWithItem(name + "_button", new StoneButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.STONE), false));
    }

    public static Block registerCopperButton(String name, Oxidizable.OxidationLevel level) {
        return registerBlockWithItem(name + "_button", new CopperButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL), false, level));
    }

    public static Block registerWaxedCopperButton(String name) {
        return registerBlockWithItem("waxed_" + name + "_button", new WaxedCopperButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL), false));
    }

    public static Block registerStickyCopperButton(String name) {
        return registerBlockWithItem("sticky_" + name + "_button", new StickyCopperButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL), false));
    }

    public static Block registerArrowButton(String name) {
        return registerBlockWithItem(name + "_button", new ArrowButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL), false));
    }

    public static Block registerPrismarineButton(String name) {
        return registerBlockWithItem(name + "_button", new PrismarineButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.STONE), false));
    }

    public static Block registerConcretePowderButton(String name) {
        return registerSandButton(name + "_concrete_powder", false);
    }

    public static Block registerSandButton(String name, boolean gravel) {
        return registerBlockWithItem(name + "_button", new FallingButton(gravel, FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(gravel ? BlockSoundGroup.GRAVEL : BlockSoundGroup.SAND), false));
    }

    /**
     * Large Buttons
     */

    public static final Block OAK_LARGE_BUTTON = registerWoodenLargeButton("oak");
    public static final Block SPRUCE_LARGE_BUTTON = registerWoodenLargeButton("spruce");
    public static final Block BIRCH_LARGE_BUTTON = registerWoodenLargeButton("birch");
    public static final Block JUNGLE_LARGE_BUTTON = registerWoodenLargeButton("jungle");
    public static final Block ACACIA_LARGE_BUTTON = registerWoodenLargeButton("acacia");
    public static final Block DARK_OAK_LARGE_BUTTON = registerWoodenLargeButton("dark_oak");
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

    public static final Block COPPER_LARGE_BUTTON = registerCopperLargeButton("copper", Oxidizable.OxidationLevel.UNAFFECTED);
    public static final Block EXPOSED_COPPER_LARGE_BUTTON = registerCopperLargeButton("exposed_copper", Oxidizable.OxidationLevel.EXPOSED);
    public static final Block WEATHERED_COPPER_LARGE_BUTTON = registerCopperLargeButton("weathered_copper", Oxidizable.OxidationLevel.WEATHERED);
    public static final Block OXIDIZED_COPPER_LARGE_BUTTON = registerCopperLargeButton("oxidized_copper", Oxidizable.OxidationLevel.OXIDIZED);

    public static final Block WAXED_COPPER_LARGE_BUTTON = registerWaxedCopperLargeButton("copper");
    public static final Block WAXED_EXPOSED_COPPER_LARGE_BUTTON = registerWaxedCopperLargeButton("exposed_copper");
    public static final Block WAXED_WEATHERED_COPPER_LARGE_BUTTON = registerWaxedCopperLargeButton("weathered_copper");
    public static final Block WAXED_OXIDIZED_COPPER_LARGE_BUTTON = registerWaxedCopperLargeButton("oxidized_copper");

    public static final Block STICKY_COPPER_LARGE_BUTTON = registerStickyCopperLargeButton("copper");
    public static final Block STICKY_EXPOSED_COPPER_LARGE_BUTTON = registerStickyCopperLargeButton("exposed_copper");
    public static final Block STICKY_WEATHERED_COPPER_LARGE_BUTTON = registerStickyCopperLargeButton("weathered_copper");
    public static final Block STICKY_OXIDIZED_COPPER_LARGE_BUTTON = registerStickyCopperLargeButton("oxidized_copper");

    public static final Block IRON_LARGE_BUTTON = registerArrowLargeButton("iron");
    public static final Block GOLD_LARGE_BUTTON = registerArrowLargeButton("gold");

    public static final Block EMERALD_LARGE_BUTTON = registerBlockWithItem("emerald_large_button",
            new EmeraldButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL), true));

    public static final Block DIAMOND_LARGE_BUTTON = registerBlockWithItem("diamond_large_button",
            new DiamondButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL), true));

    public static final Block PRISMARINE_LARGE_BUTTON = registerPrismarineLargeButton("prismarine");
    public static final Block PRISMARINE_BRICK_LARGE_BUTTON = registerPrismarineLargeButton("prismarine_brick");
    public static final Block DARK_PRISMARINE_LARGE_BUTTON = registerPrismarineLargeButton("dark_prismarine");

    public static final Block SAND_LARGE_BUTTON = registerSandLargeButton("sand", false);
    public static final Block RED_SAND_LARGE_BUTTON = registerSandLargeButton("red_sand", false);
    public static final Block GRAVEL_LARGE_BUTTON = registerSandLargeButton("gravel", true);

    public static final Block WHITE_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("white");
    public static final Block LIGHT_GRAY_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("light_gray");
    public static final Block GRAY_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("gray");
    public static final Block BLACK_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("black");
    public static final Block BROWN_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("brown");
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

    public static Block registerWoodenLargeButton(String name) {
        return registerBlockWithItem(name + "_large_button", new WoodenButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.WOOD), true));
    }

    public static Block registerStoneLargeButton(String name) {
        return registerBlockWithItem(name + "_large_button", new StoneButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.STONE), true));
    }

    public static Block registerCopperLargeButton(String name, Oxidizable.OxidationLevel level) {
        return registerBlockWithItem(name + "_large_button", new CopperButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL), true, level));
    }

    public static Block registerWaxedCopperLargeButton(String name) {
        return registerBlockWithItem("waxed_" + name + "_large_button", new WaxedCopperButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL), true));
    }

    public static Block registerStickyCopperLargeButton(String name) {
        return registerBlockWithItem("sticky_" + name + "_large_button", new StickyCopperButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL), true));
    }

    public static Block registerArrowLargeButton(String name) {
        return registerBlockWithItem(name + "_large_button", new ArrowButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.METAL), true));
    }

    public static Block registerPrismarineLargeButton(String name) {
        return registerBlockWithItem(name + "_large_button", new PrismarineButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(BlockSoundGroup.STONE), true));
    }

    public static Block registerConcretePowderLargeButton(String name) {
        return registerSandLargeButton(name + "_concrete_powder", false);
    }

    public static Block registerSandLargeButton(String name, boolean gravel) {
        return registerBlockWithItem(name + "_large_button", new FallingButton(gravel, FabricBlockSettings.of(Material.DECORATION).strength(0.5f).collidable(false).nonOpaque().sounds(gravel ? BlockSoundGroup.GRAVEL : BlockSoundGroup.SAND), true));
    }

    /**
     * Emergency Buttons
     */

    public static final Block WHITE_EMERGENCY_BUTTON = registerEmergencyButton("white");
    public static final Block LIGHT_GRAY_EMERGENCY_BUTTON = registerEmergencyButton("light_gray");
    public static final Block GRAY_EMERGENCY_BUTTON = registerEmergencyButton("gray");
    public static final Block BLACK_EMERGENCY_BUTTON = registerEmergencyButton("black");
    public static final Block BROWN_EMERGENCY_BUTTON = registerEmergencyButton("brown");
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
    public static final Block FANCY_EMERGENCY_BUTTON = registerEmergencyButton("fancy");

    public static final Block WHITE_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("white");
    public static final Block LIGHT_GRAY_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("light_gray");
    public static final Block GRAY_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("gray");
    public static final Block BLACK_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("black");
    public static final Block BROWN_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("brown");
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
    public static final Block FANCY_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("fancy");

    public static Block registerEmergencyButton(String name) {
        return registerBlockWithItem(name + "_emergency_button", new EmergencyButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).nonOpaque().sounds(BlockSoundGroup.METAL)));
    }

    public static Block registerSafeEmergencyButton(String name) {
        Block block = registerOnlyBlock(name + "_safe_emergency_button", new SafeEmergencyButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).nonOpaque().sounds(BlockSoundGroup.METAL)));
        Registry.register(Registry.ITEM, new Identifier("infinitybuttons", name + "_safe_emergency_button"),
                new SafeEmergencyButtonItem(block, new FabricItemSettings().equipmentSlot(stack -> EquipmentSlot.HEAD).group(InfinityButtonsItemGroup.INFINITYBUTTONS)));
        return block;
    }

    /**
     * Secret Buttons
     */

    public static final Block BOOKSHELF_SECRET_BUTTON = registerBlockWithItem("bookshelf_secret_button",
            new BookshelfSecretButton(FabricBlockSettings.of(Material.WOOD).nonOpaque().sounds(BlockSoundGroup.WOOD).strength(1.5f), byName("bookshelf")));

    public static final Block BRICK_SECRET_BUTTON = registerBlockWithItem("brick_secret_button",
            new FullBlockBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.RED).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool().strength(2.0f, 6.0f), byName("bricks")));

    public static final Block STONE_BRICK_SECRET_BUTTON = registerBlockWithItem("stone_brick_secret_button",
            new BigBrickSecretButton(FabricBlockSettings.of(Material.STONE).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool().strength(1.5f, 6.0f), byName("stone_bricks")));

    public static final Block MOSSY_STONE_BRICK_SECRET_BUTTON = registerBlockWithItem("mossy_stone_brick_secret_button",
            new BigBrickSecretButton(FabricBlockSettings.of(Material.STONE).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool().strength(1.5f, 6.0f), byName("mossy_stone_bricks")));

    public static final Block CRACKED_STONE_BRICK_SECRET_BUTTON = registerBlockWithItem("cracked_stone_brick_secret_button",
            new BigBrickSecretButton(FabricBlockSettings.of(Material.STONE).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool().strength(1.5f, 6.0f), byName("cracked_stone_bricks")));

    public static final Block CHISELED_STONE_BRICK_SECRET_BUTTON = registerBlockWithItem("chiseled_stone_brick_secret_button",
            new ChiseledStoneBrickSecretButton(FabricBlockSettings.of(Material.STONE).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool().strength(1.5f, 6.0f), byName("chiseled_stone_bricks")));

    public static final Block DEEPSLATE_BRICK_SECRET_BUTTON = registerBlockWithItem("deepslate_brick_secret_button",
            new BigBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.DEEPSLATE_GRAY).nonOpaque().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).requiresTool().strength(3.5f, 6.0f), byName("deepslate_bricks")));

    public static final Block CRACKED_DEEPSLATE_BRICK_SECRET_BUTTON = registerBlockWithItem("cracked_deepslate_brick_secret_button",
            new BigBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.DEEPSLATE_GRAY).nonOpaque().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).requiresTool().strength(3.5f, 6.0f), byName("cracked_deepslate_bricks")));

    public static final Block DEEPSLATE_TILE_SECRET_BUTTON = registerBlockWithItem("deepslate_tile_secret_button",
            new DeepslateTileSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.DEEPSLATE_GRAY).nonOpaque().sounds(BlockSoundGroup.DEEPSLATE_TILES).requiresTool().strength(3.5f, 6.0f), byName("deepslate_tiles")));

    public static final Block CRACKED_DEEPSLATE_TILE_SECRET_BUTTON = registerBlockWithItem("cracked_deepslate_tile_secret_button",
            new DeepslateTileSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.DEEPSLATE_GRAY).nonOpaque().sounds(BlockSoundGroup.DEEPSLATE_TILES).requiresTool().strength(3.5f, 6.0f), byName("cracked_deepslate_tiles")));

    public static final Block OAK_PLANK_SECRET_BUTTON = registerPlankSecretButton("oak", MapColor.OAK_TAN);
    public static final Block SPRUCE_PLANK_SECRET_BUTTON = registerPlankSecretButton("spruce", MapColor.SPRUCE_BROWN);
    public static final Block BIRCH_PLANK_SECRET_BUTTON = registerPlankSecretButton("birch", MapColor.PALE_YELLOW);
    public static final Block JUNGLE_PLANK_SECRET_BUTTON = registerPlankSecretButton("jungle", MapColor.DIRT_BROWN);
    public static final Block ACACIA_PLANK_SECRET_BUTTON = registerPlankSecretButton("acacia", MapColor.ORANGE);
    public static final Block DARK_OAK_PLANK_SECRET_BUTTON = registerPlankSecretButton("dark_oak", MapColor.BROWN);
    public static final Block MANGROVE_PLANK_SECRET_BUTTON = registerPlankSecretButton("mangrove", MapColor.RED);
    public static final Block CRIMSON_PLANK_SECRET_BUTTON = registerNetherPlankSecretButton("crimson", MapColor.DULL_PINK);
    public static final Block WARPED_PLANK_SECRET_BUTTON = registerNetherPlankSecretButton("warped", MapColor.DARK_AQUA);

    public static final Block MUD_BRICK_SECRET_BUTTON = registerBlockWithItem("mud_brick_secret_button",
            new MudBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.TERRACOTTA_LIGHT_GRAY).nonOpaque().sounds(BlockSoundGroup.MUD_BRICKS).requiresTool().strength(1.5f, 3.0f), byName("mud_bricks")));

    public static final Block END_STONE_BRICK_SECRET_BUTTON = registerBlockWithItem("end_stone_brick_secret_button",
            new BigBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.PALE_YELLOW).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool().strength(3.0f, 9.0f), byName("end_stone_bricks")));

    public static final Block PURPUR_BLOCK_SECRET_BUTTON = registerBlockWithItem("purpur_block_secret_button",
            new TileSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.MAGENTA).strength(1.5f, 6.0f).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool(), byName("purpur_block")));

    public static final Block QUARTZ_BRICK_SECRET_BUTTON = registerBlockWithItem("quartz_brick_secret_button",
            new BigBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.OFF_WHITE).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool().strength(0.8f), byName("quartz_bricks")));

    public static final Block DARK_PRISMARINE_SECRET_BUTTON = registerBlockWithItem("dark_prismarine_secret_button",
            new FullBlockBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.DIAMOND_BLUE).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool().strength(1.5f, 6.0f), byName("dark_prismarine")));

    public static final Block POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON = registerBlockWithItem("polished_blackstone_brick_secret_button",
            new BigBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.BLACK).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool().strength(2.0f, 6.0f), byName("polished_blackstone_bricks")));

    public static final Block CRACKED_POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON = registerBlockWithItem("cracked_polished_blackstone_brick_secret_button",
            new BigBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.BLACK).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool().strength(2.0f, 6.0f), byName("cracked_polished_blackstone_bricks")));

    public static final Block CHISELED_POLISHED_BLACKSTONE_SECRET_BUTTON = registerBlockWithItem("chiseled_polished_blackstone_secret_button",
            new ChiseledStoneBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.BLACK).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool().strength(2.0f, 6.0f), byName("chiseled_polished_blackstone")));

    public static final Block NETHER_BRICK_SECRET_BUTTON = registerBlockWithItem("nether_brick_secret_button",
            new FullBlockBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.DARK_RED).nonOpaque().sounds(BlockSoundGroup.NETHER_BRICKS).requiresTool().strength(2.0f, 6.0f), byName("nether_bricks")));

    public static final Block CRACKED_NETHER_BRICK_SECRET_BUTTON = registerBlockWithItem("cracked_nether_brick_secret_button",
            new FullBlockBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.DARK_RED).nonOpaque().sounds(BlockSoundGroup.NETHER_BRICKS).requiresTool().strength(2.0f, 6.0f), byName("cracked_nether_bricks")));

    public static final Block CHISELED_NETHER_BRICK_SECRET_BUTTON = registerBlockWithItem("chiseled_nether_brick_secret_button",
            new ChiseledNetherBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.DARK_RED).nonOpaque().sounds(BlockSoundGroup.NETHER_BRICKS).requiresTool().strength(2.0f, 6.0f), byName("chiseled_nether_bricks")));

    public static final Block RED_NETHER_BRICK_SECRET_BUTTON = registerBlockWithItem("red_nether_brick_secret_button",
            new FullBlockBrickSecretButton(FabricBlockSettings.of(Material.STONE, MapColor.DARK_RED).nonOpaque().sounds(BlockSoundGroup.NETHER_BRICKS).requiresTool().strength(2.0f, 6.0f), byName("red_nether_bricks")));

    public static Block registerPlankSecretButton(String name, MapColor color) {
        return registerBlockWithItem(name + "_plank_secret_button", new PlankSecretButton(FabricBlockSettings.of(Material.WOOD, color).nonOpaque().sounds(BlockSoundGroup.WOOD).strength(2.0f, 3.0f), byName(name + "_planks")));
    }

    public static Block registerNetherPlankSecretButton(String name, MapColor color) {
        return registerBlockWithItem(name + "_plank_secret_button", new PlankSecretButton(FabricBlockSettings.of(Material.NETHER_WOOD, color).nonOpaque().sounds(BlockSoundGroup.WOOD).strength(2.0f, 3.0f), byName(name + "_planks")));
    }

    /**
     * Misc
     */

    public static final Block DOORBELL = registerBlockWithItem("doorbell", new Doorbell(doorbellSettings()));
    public static final Block DOORBELL_BUTTON = registerBlockWithItem("doorbell_button", new DoorbellButton(doorbellSettings()));
    public static final Block LAMP_BUTTON = registerBlockWithItem("lamp_button", new LampButton(lampSettings(), false));
    public static final Block LAMP_LEVER = registerBlockWithItem("lamp_lever", new LampButton(lampSettings(), true));
    public static final Block LETTER_BUTTON = registerBlockWithItem("letter_button", new LetterButton(FabricBlockSettings.of(Material.WOOD).strength(0.5f).noCollision().sounds(BlockSoundGroup.WOOD)));
    public static final Block LANTERN_BUTTON = registerBlockWithItem("lantern_button", new LanternButton(lanternSettings().luminance(state -> 15), false));
    public static final Block LANTERN_LEVER = registerBlockWithItem("lantern_lever", new LanternButton(lanternSettings().luminance(state -> 15), true));
    public static final Block SOUL_LANTERN_BUTTON = registerBlockWithItem("soul_lantern_button", new LanternButton(lanternSettings().luminance(state -> 10), false));
    public static final Block SOUL_LANTERN_LEVER = registerBlockWithItem("soul_lantern_lever", new LanternButton(lanternSettings().luminance(state -> 10), true));

    public static FabricBlockSettings doorbellSettings() {
        return FabricBlockSettings.of(Material.DECORATION).nonOpaque().noCollision().strength(0.5f).sounds(BlockSoundGroup.WOOD);
    }

    public static FabricBlockSettings lampSettings() {
        return FabricBlockSettings.of(Material.DECORATION).nonOpaque().strength(0.3f).sounds(BlockSoundGroup.GLASS).luminance(getLampButtonLight());
    }

    public static FabricBlockSettings lanternSettings() {
        return FabricBlockSettings.of(Material.METAL).nonOpaque().requiresTool().strength(3.5f).sounds(BlockSoundGroup.LANTERN).nonOpaque();
    }

    /**
     * Console Buttons
     */

    public static final Block SMALL_CONSOLE_BUTTON = registerBlockWithItem("small_console_button", new SmallConsoleButton(consoleButtonSettings(), false));
    public static final Block SMALL_CONSOLE_LEVER = registerBlockWithItem("small_console_lever", new SmallConsoleButton(consoleButtonSettings(), true));
    public static final Block CONSOLE_BUTTON = registerBlockWithItem("console_button", new ConsoleButton(consoleButtonSettings(), false));
    public static final Block CONSOLE_LEVER = registerBlockWithItem("console_lever", new ConsoleButton(consoleButtonSettings(), true));
    public static final Block LARGE_CONSOLE_BUTTON = registerBlockWithItem("large_console_button", new LargeConsoleButton(consoleButtonSettings(), false));
    public static final Block LARGE_CONSOLE_LEVER = registerBlockWithItem("large_console_lever", new LargeConsoleButton(consoleButtonSettings(), true));
    public static final Block BIG_CONSOLE_BUTTON = registerBlockWithItem("big_console_button", new LargeConsoleButton(consoleButtonSettings(), false));
    public static final Block BIG_CONSOLE_LEVER = registerBlockWithItem("big_console_lever", new LargeConsoleButton(consoleButtonSettings(), true));

    public static FabricBlockSettings consoleButtonSettings() {
        return FabricBlockSettings.of(Material.METAL).nonOpaque().strength(0.5f).sounds(BlockSoundGroup.METAL);
    }

    /**
     * Torches
     */

    public static final Block TORCH_BUTTON = registerOnlyBlock("torch_button", new TorchButton(torchSettings(14), ParticleTypes.FLAME, byName("torch")));
    public static final Block WALL_TORCH_BUTTON = registerOnlyBlock("wall_torch_button", new WallTorchButton(torchSettings(14).dropsLike(TORCH_BUTTON), ParticleTypes.FLAME, byName("torch")));
    public static final Block TORCH_LEVER = registerOnlyBlock("torch_lever", new TorchLever(torchSettings(14), ParticleTypes.FLAME, byName("torch")));
    public static final Block WALL_TORCH_LEVER = registerOnlyBlock("wall_torch_lever", new WallTorchLever(torchSettings(14).dropsLike(TORCH_LEVER), ParticleTypes.FLAME, byName("torch")));
    public static final Block SOUL_TORCH_BUTTON = registerOnlyBlock("soul_torch_button", new TorchButton(torchSettings(10), ParticleTypes.SOUL_FIRE_FLAME, byName("soul_torch")));
    public static final Block SOUL_WALL_TORCH_BUTTON = registerOnlyBlock("soul_wall_torch_button", new WallTorchButton(torchSettings(10).dropsLike(SOUL_TORCH_BUTTON), ParticleTypes.SOUL_FIRE_FLAME, byName("soul_torch")));
    public static final Block SOUL_TORCH_LEVER = registerOnlyBlock("soul_torch_lever", new TorchLever(torchSettings(10), ParticleTypes.SOUL_FIRE_FLAME, byName("soul_torch")));
    public static final Block SOUL_WALL_TORCH_LEVER = registerOnlyBlock("soul_wall_torch_lever", new WallTorchLever(torchSettings(10).dropsLike(SOUL_TORCH_LEVER), ParticleTypes.SOUL_FIRE_FLAME, byName("soul_torch")));
    public static final Block REDSTONE_TORCH_BUTTON = registerOnlyBlock("redstone_torch_button", new RedstoneTorchButton(torchSettings(7), byName("redstone_torch")));
    public static final Block REDSTONE_WALL_TORCH_BUTTON = registerOnlyBlock("redstone_wall_torch_button", new RedstoneWallTorchButton(torchSettings(7).dropsLike(REDSTONE_TORCH_BUTTON), byName("redstone_torch")));
    public static final Block REDSTONE_TORCH_LEVER = registerOnlyBlock("redstone_torch_lever", new RedstoneTorchLever(torchSettings(7), byName("redstone_torch")));
    public static final Block REDSTONE_WALL_TORCH_LEVER = registerOnlyBlock("redstone_wall_torch_lever", new RedstoneWallTorchLever(torchSettings(7).dropsLike(REDSTONE_TORCH_LEVER), byName("redstone_torch")));

    public static FabricBlockSettings torchSettings(int light) {
        return FabricBlockSettings.of(Material.DECORATION).nonOpaque().noCollision().breakInstantly().luminance(light).sounds(BlockSoundGroup.WOOD);
    }

    /**
     * Methods
     */

    private static Block byName(String block) {
        return Registry.BLOCK.get(new Identifier("minecraft", block));
    }

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
