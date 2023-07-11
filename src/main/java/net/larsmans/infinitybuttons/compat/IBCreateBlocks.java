package net.larsmans.infinitybuttons.compat;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.larsmans.infinitybuttons.block.custom.secretbutton.BigBrickSecretButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.FullBlockBrickSecretButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.TileSecretButton;
import net.larsmans.infinitybuttons.item.InfinityButtonsItemGroup;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class IBCreateBlocks {

    public static final Block ROSE_QUARTZ_TILE_SECRET_BUTTON = registerBlockWithItem("rose_quartz_tile_secret_button",
            new TileSecretButton(FabricBlockSettings.copy(Blocks.DEEPSLATE), byName("rose_quartz_tiles")));
    public static final Block SMALL_ROSE_QUARTZ_TILE_SECRET_BUTTON = registerBlockWithItem("small_rose_quartz_tile_secret_button",
            new FullBlockBrickSecretButton(FabricBlockSettings.copy(Blocks.DEEPSLATE), byName("small_rose_quartz_tiles")));

    public static final Block CUT_GRANITE_BRICK_SECRET_BUTTON = registerCut("granite", Blocks.GRANITE);
    public static final Block SMALL_GRANITE_BRICK_SECRET_BUTTON = registerSmall("granite", Blocks.GRANITE);

    public static final Block CUT_DIORITE_BRICK_SECRET_BUTTON = registerCut("diorite", Blocks.DIORITE);
    public static final Block SMALL_DIORITE_BRICK_SECRET_BUTTON = registerSmall("diorite", Blocks.DIORITE);

    public static final Block CUT_ANDESITE_BRICK_SECRET_BUTTON = registerCut("andesite", Blocks.ANDESITE);
    public static final Block SMALL_ANDESITE_BRICK_SECRET_BUTTON = registerSmall("andesite", Blocks.ANDESITE);

    public static final Block CUT_CALCITE_BRICK_SECRET_BUTTON = registerCut("calcite", Blocks.CALCITE);
    public static final Block SMALL_CALCITE_BRICK_SECRET_BUTTON = registerSmall("calcite", Blocks.CALCITE);

    public static final Block CUT_DRIPSTONE_BRICK_SECRET_BUTTON = registerCut("dripstone", Blocks.DRIPSTONE_BLOCK);
    public static final Block SMALL_DRIPSTONE_BRICK_SECRET_BUTTON = registerSmall("dripstone", Blocks.DRIPSTONE_BLOCK);

    public static final Block CUT_DEEPSLATE_BRICK_SECRET_BUTTON = registerCut("deepslate", Blocks.DEEPSLATE);
    public static final Block SMALL_DEEPSLATE_BRICK_SECRET_BUTTON = registerSmall("deepslate", Blocks.DEEPSLATE);

    public static final Block CUT_TUFF_BRICK_SECRET_BUTTON = registerCut("tuff", Blocks.TUFF);
    public static final Block SMALL_TUFF_BRICK_SECRET_BUTTON = registerSmall("tuff", Blocks.TUFF);

    public static final Block CUT_ASURINE_BRICK_SECRET_BUTTON = registerCutMineral("asurine", Blocks.DEEPSLATE, MapColor.BLUE);
    public static final Block SMALL_ASURINE_BRICK_SECRET_BUTTON = registerSmallMineral("asurine", Blocks.DEEPSLATE, MapColor.BLUE);

    public static final Block CUT_CRIMSITE_BRICK_SECRET_BUTTON = registerCutMineral("crimsite", Blocks.DEEPSLATE, MapColor.RED);
    public static final Block SMALL_CRIMSITE_BRICK_SECRET_BUTTON = registerSmallMineral("crimsite", Blocks.DEEPSLATE, MapColor.RED);

    public static final Block CUT_LIMESTONE_BRICK_SECRET_BUTTON = registerCutMineral("limestone", Blocks.SANDSTONE, MapColor.PALE_YELLOW);
    public static final Block SMALL_LIMESTONE_BRICK_SECRET_BUTTON = registerSmallMineral("limestone", Blocks.SANDSTONE, MapColor.PALE_YELLOW);

    public static final Block CUT_OCHRUM_BRICK_SECRET_BUTTON = registerCutMineral("ochrum", Blocks.CALCITE, MapColor.TERRACOTTA_YELLOW);
    public static final Block SMALL_OCHRUM_BRICK_SECRET_BUTTON = registerSmallMineral("ochrum", Blocks.CALCITE, MapColor.TERRACOTTA_YELLOW);

    public static final Block CUT_SCORIA_BRICK_SECRET_BUTTON = registerCut("scoria", FabricBlockSettings.copy(Blocks.BLACKSTONE).mapColor(MapColor.BROWN));
    public static final Block SMALL_SCORIA_BRICK_SECRET_BUTTON = registerSmall("scoria", FabricBlockSettings.copy(Blocks.BLACKSTONE).mapColor(MapColor.BROWN));

    public static final Block CUT_SCORCHIA_BRICK_SECRET_BUTTON = registerCut("scorchia", FabricBlockSettings.copy(Blocks.BLACKSTONE).mapColor(MapColor.TERRACOTTA_GRAY));
    public static final Block SMALL_SCORCHIA_BRICK_SECRET_BUTTON = registerSmall("scorchia", FabricBlockSettings.copy(Blocks.BLACKSTONE).mapColor(MapColor.TERRACOTTA_GRAY));

    public static final Block CUT_VERIDIUM_BRICK_SECRET_BUTTON = registerCutMineral("veridium", Blocks.TUFF, MapColor.TEAL);
    public static final Block SMALL_VERIDIUM_BRICK_SECRET_BUTTON = registerSmallMineral("veridium", Blocks.TUFF, MapColor.TEAL);

    /**
     * Methods
     */

    private static Block registerCutMineral(String type, Block properties, MapColor color) {
        return registerCut(type, FabricBlockSettings.copy(properties).hardness(1.25F).mapColor(color));
    }

    private static Block registerSmallMineral(String type, Block properties, MapColor color) {
        return registerSmall(type, FabricBlockSettings.copy(properties).hardness(1.25F).mapColor(color));
    }

    private static Block registerCut(String type, Block properties) {
        return registerBlockWithItem("cut_" + type + "_brick_secret_button",
                new BigBrickSecretButton(FabricBlockSettings.copy(properties).nonOpaque(), byName("cut_" + type + "_bricks")));
    }

    private static Block registerCut(String type, AbstractBlock.Settings properties) {
        return registerBlockWithItem("cut_" + type + "_brick_secret_button",
                new BigBrickSecretButton(properties.nonOpaque(), byName("cut_" + type + "_bricks")));
    }

    private static Block registerSmall(String type, Block properties) {
        return registerBlockWithItem("small_" + type + "_brick_secret_button",
                new FullBlockBrickSecretButton(FabricBlockSettings.copy(properties).nonOpaque(), byName("small_" + type + "_bricks")));
    }

    private static Block registerSmall(String type, AbstractBlock.Settings properties) {
        return registerBlockWithItem("small_" + type + "_brick_secret_button",
                new FullBlockBrickSecretButton(properties.nonOpaque(), byName("small_" + type + "_bricks")));
    }

    private static Block byName(String block) {
        return Registry.BLOCK.get(new Identifier("create", block));
    }

    private static Block registerBlockWithItem(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registry.BLOCK, new Identifier("infinitybuttons", name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registry.ITEM, new Identifier("infinitybuttons", name),
                new BlockItem(block, new FabricItemSettings().group(InfinityButtonsItemGroup.INFINITYBUTTONS)));
    }

    public static void registerCompatBlocks() {
        InfinityButtonsInit.LOGGER.debug("Registering Create Compat Blocks for Infinity Buttons");
    }
}
