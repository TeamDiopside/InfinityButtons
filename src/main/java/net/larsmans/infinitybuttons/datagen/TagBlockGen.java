package net.larsmans.infinitybuttons.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class TagBlockGen extends FabricTagProvider<Block> {
    public TagBlockGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BLOCK, registriesFuture);
    }

    public static final TagKey<Block> CONCRETE_POWDER_BUTTONS = add("concrete_powder_buttons");
    public static final TagKey<Block> CONCRETE_POWDER_LARGE_BUTTONS = add("concrete_powder_large_buttons");
    public static final TagKey<Block> LARGE_BUTTONS = add("large_buttons");
    public static final TagKey<Block> WOODEN_LARGE_BUTTONS = add("wooden_large_buttons");
    public static final TagKey<Block> EMERGENCY_BUTTONS = add("emergency_buttons");
    public static final TagKey<Block> SAFE_EMERGENCY_BUTTONS = add("safe_emergency_buttons");
    public static final TagKey<Block> SECRET_BUTTONS = add("secret_buttons");
    public static final TagKey<Block> WOODEN_SECRET_BUTTONS = add("wooden_secret_buttons");
    public static final TagKey<Block> BOOKSHELF_SECRET_BUTTONS = add("bookshelf_secret_buttons");
    public static final TagKey<Block> TORCH_BUTTONS = add("torch_buttons");
    public static final TagKey<Block> CONSOLE_BUTTONS = add("console_buttons");
    public static final TagKey<Block> LANTERN_BUTTONS = add("lantern_buttons");
    public static final TagKey<Block> COPPER_BUTTONS = add("copper_buttons");
    public static final TagKey<Block> COPPER_LARGE_BUTTONS = add("copper_large_buttons");

    static TagKey<Block> add(String name) {
        return TagKey.of(RegistryKeys.BLOCK, new Identifier("infinitybuttons:" + name));
    }

    protected void generateInfinityButtonsTags() {
        getOrCreateTagBuilder(CONCRETE_POWDER_BUTTONS)
                .add(InfinityButtonsBlocks.WHITE_CONCRETE_POWDER_BUTTON)
                .add(InfinityButtonsBlocks.LIGHT_GRAY_CONCRETE_POWDER_BUTTON)
                .add(InfinityButtonsBlocks.GRAY_CONCRETE_POWDER_BUTTON)
                .add(InfinityButtonsBlocks.BLACK_CONCRETE_POWDER_BUTTON)
                .add(InfinityButtonsBlocks.BROWN_CONCRETE_POWDER_BUTTON)
                .add(InfinityButtonsBlocks.RED_CONCRETE_POWDER_BUTTON)
                .add(InfinityButtonsBlocks.ORANGE_CONCRETE_POWDER_BUTTON)
                .add(InfinityButtonsBlocks.YELLOW_CONCRETE_POWDER_BUTTON)
                .add(InfinityButtonsBlocks.LIME_CONCRETE_POWDER_BUTTON)
                .add(InfinityButtonsBlocks.GREEN_CONCRETE_POWDER_BUTTON)
                .add(InfinityButtonsBlocks.CYAN_CONCRETE_POWDER_BUTTON)
                .add(InfinityButtonsBlocks.LIGHT_BLUE_CONCRETE_POWDER_BUTTON)
                .add(InfinityButtonsBlocks.BLUE_CONCRETE_POWDER_BUTTON)
                .add(InfinityButtonsBlocks.PURPLE_CONCRETE_POWDER_BUTTON)
                .add(InfinityButtonsBlocks.MAGENTA_CONCRETE_POWDER_BUTTON)
                .add(InfinityButtonsBlocks.PINK_CONCRETE_POWDER_BUTTON)
        ;

        getOrCreateTagBuilder(CONCRETE_POWDER_LARGE_BUTTONS)
                .add(InfinityButtonsBlocks.WHITE_CONCRETE_POWDER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.LIGHT_GRAY_CONCRETE_POWDER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.GRAY_CONCRETE_POWDER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.BLACK_CONCRETE_POWDER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.BROWN_CONCRETE_POWDER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.RED_CONCRETE_POWDER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.ORANGE_CONCRETE_POWDER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.YELLOW_CONCRETE_POWDER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.LIME_CONCRETE_POWDER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.GREEN_CONCRETE_POWDER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.CYAN_CONCRETE_POWDER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.LIGHT_BLUE_CONCRETE_POWDER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.BLUE_CONCRETE_POWDER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.PURPLE_CONCRETE_POWDER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.MAGENTA_CONCRETE_POWDER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.PINK_CONCRETE_POWDER_LARGE_BUTTON)
        ;

        getOrCreateTagBuilder(LARGE_BUTTONS)
                .addOptionalTag(WOODEN_LARGE_BUTTONS)
                .addOptionalTag(COPPER_LARGE_BUTTONS)
                .addOptionalTag(CONCRETE_POWDER_LARGE_BUTTONS)
                .add(InfinityButtonsBlocks.STONE_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.DEEPSLATE_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.GRANITE_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.DIORITE_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.ANDESITE_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.TUFF_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.DRIPSTONE_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.CALCITE_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.POLISHED_BLACKSTONE_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.IRON_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.GOLD_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.EMERALD_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.DIAMOND_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.PRISMARINE_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.PRISMARINE_BRICK_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.DARK_PRISMARINE_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.SAND_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.RED_SAND_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.GRAVEL_LARGE_BUTTON)
        ;

        getOrCreateTagBuilder(WOODEN_LARGE_BUTTONS)
                .add(InfinityButtonsBlocks.OAK_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.SPRUCE_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.BIRCH_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.JUNGLE_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.ACACIA_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.DARK_OAK_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.MANGROVE_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.CRIMSON_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.WARPED_LARGE_BUTTON)
        ;

        getOrCreateTagBuilder(EMERGENCY_BUTTONS)
                .add(InfinityButtonsBlocks.WHITE_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.LIGHT_GRAY_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.GRAY_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.BLACK_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.BROWN_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.RED_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.ORANGE_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.YELLOW_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.LIME_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.GREEN_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.CYAN_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.LIGHT_BLUE_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.BLUE_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.PURPLE_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.MAGENTA_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.PINK_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.FANCY_EMERGENCY_BUTTON)
        ;

        getOrCreateTagBuilder(SAFE_EMERGENCY_BUTTONS)
                .add(InfinityButtonsBlocks.WHITE_SAFE_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.LIGHT_GRAY_SAFE_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.GRAY_SAFE_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.BLACK_SAFE_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.BROWN_SAFE_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.RED_SAFE_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.ORANGE_SAFE_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.YELLOW_SAFE_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.LIME_SAFE_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.GREEN_SAFE_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.CYAN_SAFE_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.LIGHT_BLUE_SAFE_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.BLUE_SAFE_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.PURPLE_SAFE_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.MAGENTA_SAFE_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.PINK_SAFE_EMERGENCY_BUTTON)
                .add(InfinityButtonsBlocks.FANCY_SAFE_EMERGENCY_BUTTON)
        ;

        getOrCreateTagBuilder(SECRET_BUTTONS)
                .addOptionalTag(WOODEN_SECRET_BUTTONS)
                .add(InfinityButtonsBlocks.BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.STONE_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.MOSSY_STONE_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.CRACKED_STONE_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.CHISELED_STONE_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.DEEPSLATE_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.CRACKED_DEEPSLATE_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.DEEPSLATE_TILE_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.CRACKED_DEEPSLATE_TILE_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.MUD_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.END_STONE_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.PURPUR_BLOCK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.QUARTZ_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.DARK_PRISMARINE_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.CHISELED_POLISHED_BLACKSTONE_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.NETHER_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.CRACKED_NETHER_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.CHISELED_NETHER_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.RED_NETHER_BRICK_SECRET_BUTTON)
        ;

        getOrCreateTagBuilder(WOODEN_SECRET_BUTTONS)
                .addOptionalTag(BOOKSHELF_SECRET_BUTTONS)
                .add(InfinityButtonsBlocks.OAK_PLANK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.SPRUCE_PLANK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.BIRCH_PLANK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.JUNGLE_PLANK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.ACACIA_PLANK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.DARK_OAK_PLANK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.MANGROVE_PLANK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.CRIMSON_PLANK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.WARPED_PLANK_SECRET_BUTTON)
        ;

        getOrCreateTagBuilder(BOOKSHELF_SECRET_BUTTONS)
                .add(InfinityButtonsBlocks.BOOKSHELF_SECRET_BUTTON)
        ;

        getOrCreateTagBuilder(TORCH_BUTTONS)
                .add(InfinityButtonsBlocks.TORCH_BUTTON)
                .add(InfinityButtonsBlocks.TORCH_LEVER)
                .add(InfinityButtonsBlocks.SOUL_TORCH_BUTTON)
                .add(InfinityButtonsBlocks.SOUL_TORCH_LEVER)
                .add(InfinityButtonsBlocks.REDSTONE_TORCH_BUTTON)
                .add(InfinityButtonsBlocks.REDSTONE_TORCH_LEVER)
                .add(InfinityButtonsBlocks.WALL_TORCH_BUTTON)
                .add(InfinityButtonsBlocks.WALL_TORCH_LEVER)
                .add(InfinityButtonsBlocks.SOUL_WALL_TORCH_BUTTON)
                .add(InfinityButtonsBlocks.SOUL_WALL_TORCH_LEVER)
                .add(InfinityButtonsBlocks.REDSTONE_WALL_TORCH_BUTTON)
                .add(InfinityButtonsBlocks.REDSTONE_WALL_TORCH_LEVER)
        ;

        getOrCreateTagBuilder(CONSOLE_BUTTONS)
                .add(InfinityButtonsBlocks.SMALL_CONSOLE_BUTTON)
                .add(InfinityButtonsBlocks.SMALL_CONSOLE_LEVER)
                .add(InfinityButtonsBlocks.CONSOLE_BUTTON)
                .add(InfinityButtonsBlocks.CONSOLE_LEVER)
                .add(InfinityButtonsBlocks.LARGE_CONSOLE_BUTTON)
                .add(InfinityButtonsBlocks.LARGE_CONSOLE_LEVER)
                .add(InfinityButtonsBlocks.BIG_CONSOLE_BUTTON)
                .add(InfinityButtonsBlocks.BIG_CONSOLE_LEVER)
        ;

        getOrCreateTagBuilder(LANTERN_BUTTONS)
                .add(InfinityButtonsBlocks.LANTERN_BUTTON)
                .add(InfinityButtonsBlocks.LANTERN_LEVER)
                .add(InfinityButtonsBlocks.SOUL_LANTERN_BUTTON)
                .add(InfinityButtonsBlocks.SOUL_LANTERN_LEVER)
        ;

        getOrCreateTagBuilder(COPPER_BUTTONS)
                .add(InfinityButtonsBlocks.COPPER_BUTTON)
                .add(InfinityButtonsBlocks.EXPOSED_COPPER_BUTTON)
                .add(InfinityButtonsBlocks.WEATHERED_COPPER_BUTTON)
                .add(InfinityButtonsBlocks.OXIDIZED_COPPER_BUTTON)
                .add(InfinityButtonsBlocks.WAXED_COPPER_BUTTON)
                .add(InfinityButtonsBlocks.WAXED_EXPOSED_COPPER_BUTTON)
                .add(InfinityButtonsBlocks.WAXED_WEATHERED_COPPER_BUTTON)
                .add(InfinityButtonsBlocks.WAXED_OXIDIZED_COPPER_BUTTON)
                .add(InfinityButtonsBlocks.STICKY_COPPER_BUTTON)
                .add(InfinityButtonsBlocks.STICKY_EXPOSED_COPPER_BUTTON)
                .add(InfinityButtonsBlocks.STICKY_WEATHERED_COPPER_BUTTON)
                .add(InfinityButtonsBlocks.STICKY_OXIDIZED_COPPER_BUTTON)
        ;

        getOrCreateTagBuilder(COPPER_LARGE_BUTTONS)
                .add(InfinityButtonsBlocks.COPPER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.EXPOSED_COPPER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.WEATHERED_COPPER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.OXIDIZED_COPPER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.WAXED_COPPER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.WAXED_EXPOSED_COPPER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.WAXED_WEATHERED_COPPER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.WAXED_OXIDIZED_COPPER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.STICKY_COPPER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.STICKY_EXPOSED_COPPER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.STICKY_WEATHERED_COPPER_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.STICKY_OXIDIZED_COPPER_LARGE_BUTTON)
        ;
    }

    public static final TagKey<Block> MINEABLE_AXE = edit("mineable/axe");
    public static final TagKey<Block> MINEABLE_PICKAXE = edit("mineable/pickaxe");
    public static final TagKey<Block> MINEABLE_SHOVEL = edit("mineable/shovel");
    public static final TagKey<Block> BUTTONS = edit("buttons");
    public static final TagKey<Block> GUARDED_BY_PIGLINS = edit("guarded_by_piglins");
    public static final TagKey<Block> NON_FLAMMABLE_WOOD = edit("non_flammable_wood");
    public static final TagKey<Block> PIGLIN_REPELLENTS = edit("piglin_repellents");
    public static final TagKey<Block> STONE_BRICKS = edit("stone_bricks");
    public static final TagKey<Block> WALL_POST_OVERRIDE = edit("wall_post_override");
    public static final TagKey<Block> NEEDS_STONE_TOOL = edit("needs_stone_tool");
    public static final TagKey<Block> NEEDS_IRON_TOOL = edit("needs_iron_tool");

    static TagKey<Block> edit(String name) {
        return TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft:" + name));
    }

    protected void generateVanillaTags() {
        getOrCreateTagBuilder(MINEABLE_AXE)
                .addOptionalTag(WOODEN_LARGE_BUTTONS)
                .addOptionalTag(WOODEN_SECRET_BUTTONS)
                .add(InfinityButtonsBlocks.LETTER_BUTTON)
                .add(InfinityButtonsBlocks.LETTER_LEVER)
        ;

        getOrCreateTagBuilder(MINEABLE_PICKAXE)
                .addOptionalTag(COPPER_BUTTONS)
                .addOptionalTag(COPPER_LARGE_BUTTONS)
                .add(InfinityButtonsBlocks.DEEPSLATE_BUTTON)
                .add(InfinityButtonsBlocks.GRANITE_BUTTON)
                .add(InfinityButtonsBlocks.DIORITE_BUTTON)
                .add(InfinityButtonsBlocks.ANDESITE_BUTTON)
                .add(InfinityButtonsBlocks.TUFF_BUTTON)
                .add(InfinityButtonsBlocks.DRIPSTONE_BUTTON)
                .add(InfinityButtonsBlocks.CALCITE_BUTTON)
                .add(InfinityButtonsBlocks.IRON_BUTTON)
                .add(InfinityButtonsBlocks.GOLD_BUTTON)
                .add(InfinityButtonsBlocks.EMERALD_BUTTON)
                .add(InfinityButtonsBlocks.DIAMOND_BUTTON)
                .add(InfinityButtonsBlocks.PRISMARINE_BUTTON)
                .add(InfinityButtonsBlocks.PRISMARINE_BRICK_BUTTON)
                .add(InfinityButtonsBlocks.DARK_PRISMARINE_BUTTON)

                .add(InfinityButtonsBlocks.STONE_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.DEEPSLATE_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.GRANITE_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.DIORITE_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.ANDESITE_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.TUFF_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.DRIPSTONE_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.CALCITE_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.POLISHED_BLACKSTONE_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.IRON_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.GOLD_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.EMERALD_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.DIAMOND_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.PRISMARINE_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.PRISMARINE_BRICK_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.DARK_PRISMARINE_LARGE_BUTTON)

                .addOptionalTag(EMERGENCY_BUTTONS)
                .add(InfinityButtonsBlocks.FANCY_EMERGENCY_BUTTON)

                .addOptionalTag(SAFE_EMERGENCY_BUTTONS)
                .add(InfinityButtonsBlocks.FANCY_SAFE_EMERGENCY_BUTTON)

                .add(InfinityButtonsBlocks.BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.STONE_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.MOSSY_STONE_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.CRACKED_STONE_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.CHISELED_STONE_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.DEEPSLATE_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.CRACKED_DEEPSLATE_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.DEEPSLATE_TILE_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.CRACKED_DEEPSLATE_TILE_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.MUD_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.END_STONE_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.PURPUR_BLOCK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.QUARTZ_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.DARK_PRISMARINE_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.CHISELED_POLISHED_BLACKSTONE_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.NETHER_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.CRACKED_NETHER_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.CHISELED_NETHER_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.RED_NETHER_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.LANTERN_BUTTON)
                .add(InfinityButtonsBlocks.LANTERN_LEVER)
                .add(InfinityButtonsBlocks.SOUL_LANTERN_BUTTON)
                .add(InfinityButtonsBlocks.SOUL_LANTERN_LEVER)
                .add(InfinityButtonsBlocks.SMALL_CONSOLE_BUTTON)
                .add(InfinityButtonsBlocks.SMALL_CONSOLE_LEVER)
                .add(InfinityButtonsBlocks.CONSOLE_BUTTON)
                .add(InfinityButtonsBlocks.CONSOLE_LEVER)
                .add(InfinityButtonsBlocks.LARGE_CONSOLE_BUTTON)
                .add(InfinityButtonsBlocks.LARGE_CONSOLE_LEVER)
                .add(InfinityButtonsBlocks.BIG_CONSOLE_BUTTON)
                .add(InfinityButtonsBlocks.BIG_CONSOLE_LEVER)
        ;

        getOrCreateTagBuilder(MINEABLE_SHOVEL)
                .add(InfinityButtonsBlocks.SAND_BUTTON)
                .add(InfinityButtonsBlocks.RED_SAND_BUTTON)
                .add(InfinityButtonsBlocks.GRAVEL_BUTTON)
                .addOptionalTag(CONCRETE_POWDER_BUTTONS)
                .add(InfinityButtonsBlocks.SAND_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.RED_SAND_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.GRAVEL_LARGE_BUTTON)
                .addOptionalTag(CONCRETE_POWDER_LARGE_BUTTONS)
        ;

        getOrCreateTagBuilder(BUTTONS)
                .addOptionalTag(COPPER_BUTTONS)
                .addOptionalTag(CONCRETE_POWDER_BUTTONS)
                .add(InfinityButtonsBlocks.DEEPSLATE_BUTTON)
                .add(InfinityButtonsBlocks.GRANITE_BUTTON)
                .add(InfinityButtonsBlocks.DIORITE_BUTTON)
                .add(InfinityButtonsBlocks.ANDESITE_BUTTON)
                .add(InfinityButtonsBlocks.TUFF_BUTTON)
                .add(InfinityButtonsBlocks.DRIPSTONE_BUTTON)
                .add(InfinityButtonsBlocks.CALCITE_BUTTON)
                .add(InfinityButtonsBlocks.IRON_BUTTON)
                .add(InfinityButtonsBlocks.GOLD_BUTTON)
                .add(InfinityButtonsBlocks.EMERALD_BUTTON)
                .add(InfinityButtonsBlocks.DIAMOND_BUTTON)
                .add(InfinityButtonsBlocks.PRISMARINE_BUTTON)
                .add(InfinityButtonsBlocks.PRISMARINE_BRICK_BUTTON)
                .add(InfinityButtonsBlocks.DARK_PRISMARINE_BUTTON)
                .add(InfinityButtonsBlocks.SAND_BUTTON)
                .add(InfinityButtonsBlocks.RED_SAND_BUTTON)
                .add(InfinityButtonsBlocks.GRAVEL_BUTTON)
        ;

        getOrCreateTagBuilder(GUARDED_BY_PIGLINS)
                .add(InfinityButtonsBlocks.GOLD_BUTTON)
                .add(InfinityButtonsBlocks.GOLD_LARGE_BUTTON)
        ;

        getOrCreateTagBuilder(NON_FLAMMABLE_WOOD)
                .add(InfinityButtonsBlocks.CRIMSON_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.WARPED_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.CRIMSON_PLANK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.WARPED_PLANK_SECRET_BUTTON)
        ;

        getOrCreateTagBuilder(PIGLIN_REPELLENTS)
                .add(InfinityButtonsBlocks.SOUL_TORCH_BUTTON)
                .add(InfinityButtonsBlocks.SOUL_WALL_TORCH_BUTTON)
                .add(InfinityButtonsBlocks.SOUL_TORCH_LEVER)
                .add(InfinityButtonsBlocks.SOUL_WALL_TORCH_LEVER)
                .add(InfinityButtonsBlocks.SOUL_LANTERN_BUTTON)
                .add(InfinityButtonsBlocks.SOUL_LANTERN_LEVER)
        ;

        getOrCreateTagBuilder(STONE_BRICKS)
                .add(InfinityButtonsBlocks.STONE_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.MOSSY_STONE_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.CRACKED_STONE_BRICK_SECRET_BUTTON)
                .add(InfinityButtonsBlocks.CHISELED_STONE_BRICK_SECRET_BUTTON)
        ;

        getOrCreateTagBuilder(WALL_POST_OVERRIDE)
                .add(InfinityButtonsBlocks.TORCH_BUTTON)
                .add(InfinityButtonsBlocks.TORCH_LEVER)
                .add(InfinityButtonsBlocks.SOUL_TORCH_BUTTON)
                .add(InfinityButtonsBlocks.SOUL_TORCH_LEVER)
                .add(InfinityButtonsBlocks.REDSTONE_TORCH_BUTTON)
                .add(InfinityButtonsBlocks.REDSTONE_TORCH_LEVER)
        ;

        getOrCreateTagBuilder(NEEDS_STONE_TOOL)
                .addOptionalTag(COPPER_BUTTONS)
                .addOptionalTag(COPPER_LARGE_BUTTONS)
                .add(InfinityButtonsBlocks.IRON_BUTTON)
                .add(InfinityButtonsBlocks.IRON_LARGE_BUTTON)
        ;

        getOrCreateTagBuilder(NEEDS_IRON_TOOL)
                .add(InfinityButtonsBlocks.DIAMOND_BUTTON)
                .add(InfinityButtonsBlocks.DIAMOND_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.GOLD_BUTTON)
                .add(InfinityButtonsBlocks.GOLD_LARGE_BUTTON)
                .add(InfinityButtonsBlocks.EMERALD_BUTTON)
                .add(InfinityButtonsBlocks.EMERALD_LARGE_BUTTON)
        ;
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        generateVanillaTags();
        generateInfinityButtonsTags();
    }
}
