package net.larsmans.infinitybuttons.compat;

import net.larsmans.infinitybuttons.InfinityButtons;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.block.custom.secretbutton.compat.BigCompatSecretButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.compat.ChiseledStonepatBrickSecretButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.compat.CompatBookshelfSecretButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.compat.FullCompatBrickSecretButton;
import net.larsmans.infinitybuttons.item.InfinityButtonsItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class QuarkBlocks {

    public static final RegistryObject<Block> SPRUCE_BOOKSHELF_SECRET_BUTTON = registerBookshelf("spruce");
    public static final RegistryObject<Block> BIRCH_BOOKSHELF_SECRET_BUTTON = registerBookshelf("birch");
    public static final RegistryObject<Block> JUNGLE_BOOKSHELF_SECRET_BUTTON = registerBookshelf("jungle");
    public static final RegistryObject<Block> ACACIA_BOOKSHELF_SECRET_BUTTON = registerBookshelf("acacia");
    public static final RegistryObject<Block> DARK_OAK_BOOKSHELF_SECRET_BUTTON = registerBookshelf("dark_oak");
    public static final RegistryObject<Block> MANGROVE_BOOKSHELF_SECRET_BUTTON = registerBookshelf("mangrove");
    public static final RegistryObject<Block> CHERRY_BOOKSHELF_SECRET_BUTTON = registerBookshelf("cherry");
    public static final RegistryObject<Block> BAMBOO_BOOKSHELF_SECRET_BUTTON = registerBookshelf("bamboo");
    public static final RegistryObject<Block> CRIMSON_BOOKSHELF_SECRET_BUTTON = registerBookshelf("crimson");
    public static final RegistryObject<Block> WARPED_BOOKSHELF_SECRET_BUTTON = registerBookshelf("warped");
    public static final RegistryObject<Block> BLOSSOM_BOOKSHELF_SECRET_BUTTON = registerBookshelf("blossom");
    public static final RegistryObject<Block> AZALEA_BOOKSHELF_SECRET_BUTTON = registerBookshelf("azalea");
    public static final RegistryObject<Block> ANCIENT_BOOKSHELF_SECRET_BUTTON = registerBookshelf("ancient");

    public static final RegistryObject<Block> PERMAFROST_BRICK_SECRET_BUTTON = registerBlock("permafrost_brick_secret_button",
            () -> new BigCompatSecretButton(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.5f, 10.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(), byName("permafrost_bricks")));

    public static final RegistryObject<Block> BLUE_NETHER_BRICK_SECRET_BUTTON = registerBlock("blue_nether_brick_secret_button",
            () -> new FullCompatBrickSecretButton(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).strength(2.0f, 6.0f).noOcclusion().sound(SoundType.NETHER_BRICKS).requiresCorrectToolForDrops(), byName("blue_nether_bricks")));

    public static final RegistryObject<Block> POLISHED_GRANITE_BRICK_SECRET_BUTTON = registerPolishedButton("granite", MapColor.DIRT);
    public static final RegistryObject<Block> POLISHED_DIORITE_BRICK_SECRET_BUTTON = registerPolishedButton("diorite", MapColor.QUARTZ);
    public static final RegistryObject<Block> POLISHED_ANDESITE_BRICK_SECRET_BUTTON = registerPolishedButton("andesite", MapColor.STONE);
    public static final RegistryObject<Block> POLISHED_CALCITE_BRICK_SECRET_BUTTON = registerPolishedButton("calcite", MapColor.TERRACOTTA_WHITE);
    public static final RegistryObject<Block> POLISHED_DRIPSTONE_BRICK_SECRET_BUTTON = registerPolishedButton("dripstone", MapColor.TERRACOTTA_BROWN);
    public static final RegistryObject<Block> POLISHED_TUFF_BRICK_SECRET_BUTTON = registerPolishedButton("tuff", MapColor.TERRACOTTA_GRAY);
    public static final RegistryObject<Block> POLISHED_LIMESTONE_BRICK_SECRET_BUTTON = registerPolishedButton("limestone", MapColor.STONE);
    public static final RegistryObject<Block> POLISHED_JASPER_BRICK_SECRET_BUTTON = registerPolishedButton("jasper", MapColor.TERRACOTTA_RED);
    public static final RegistryObject<Block> POLISHED_SHALE_BRICK_SECRET_BUTTON = registerPolishedButton("shale", MapColor.ICE);

    public static final RegistryObject<Block> CHISELED_POLISHED_GRANITE_BRICK_SECRET_BUTTON = registerChiseledPolishedButton("granite", MapColor.DIRT);
    public static final RegistryObject<Block> CHISELED_POLISHED_DIORITE_BRICK_SECRET_BUTTON = registerChiseledPolishedButton("diorite", MapColor.QUARTZ);
    public static final RegistryObject<Block> CHISELED_POLISHED_ANDESITE_BRICK_SECRET_BUTTON = registerChiseledPolishedButton("andesite", MapColor.STONE);
    public static final RegistryObject<Block> CHISELED_POLISHED_CALCITE_BRICK_SECRET_BUTTON = registerChiseledPolishedButton("calcite", MapColor.TERRACOTTA_WHITE);
    public static final RegistryObject<Block> CHISELED_POLISHED_DRIPSTONE_BRICK_SECRET_BUTTON = registerChiseledPolishedButton("dripstone", MapColor.TERRACOTTA_BROWN);
    public static final RegistryObject<Block> CHISELED_POLISHED_TUFF_BRICK_SECRET_BUTTON = registerChiseledPolishedButton("tuff", MapColor.TERRACOTTA_GRAY);
    public static final RegistryObject<Block> CHISELED_POLISHED_LIMESTONE_BRICK_SECRET_BUTTON = registerChiseledPolishedButton("limestone", MapColor.STONE);
    public static final RegistryObject<Block> CHISELED_POLISHED_JASPER_BRICK_SECRET_BUTTON = registerChiseledPolishedButton("jasper", MapColor.TERRACOTTA_RED);
    public static final RegistryObject<Block> CHISELED_POLISHED_SHALE_BRICK_SECRET_BUTTON = registerChiseledPolishedButton("shale", MapColor.ICE);

    /**
     * Methods
     */

    private static Block byName(String block) {
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark", block));
    }

    private static RegistryObject<Block> registerChiseledPolishedButton(String type, MapColor mapColor) {
        return registerBlock("chiseled_polished_" + type + "_brick_secret_button", () -> new ChiseledStonepatBrickSecretButton(BlockBehaviour.Properties.of().mapColor(mapColor).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(), byName("chiseled_" + type + "_bricks")));
    }

    private static RegistryObject<Block> registerPolishedButton(String type, MapColor mapColor) {
        return registerBlock("polished_" + type + "_brick_secret_button", () -> new BigCompatSecretButton(BlockBehaviour.Properties.of().mapColor(mapColor).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(), byName(type + "_bricks")));
    }

    private static RegistryObject<Block> registerBookshelf(String wood) {
        return registerBlock(wood + "_bookshelf_secret_button", () -> new CompatBookshelfSecretButton(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).ignitedByLava().strength(1.5f).noOcclusion().sound(SoundType.WOOD), byName(wood + "_bookshelf")));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = InfinityButtonsBlocks.BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        InfinityButtonsItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()));
    }

    public static void registerCompatBlocks() {
        InfinityButtons.LOGGER.debug("Registering Quark Compat Blocks for Infinity Buttons");
    }
}
