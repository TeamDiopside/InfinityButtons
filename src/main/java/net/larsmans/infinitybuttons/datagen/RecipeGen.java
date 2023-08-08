package net.larsmans.infinitybuttons.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class RecipeGen extends FabricRecipeProvider {
    public RecipeGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> i) {
        genBothButtons(i, InfinityButtonsBlocks.DEEPSLATE_BUTTON, Items.DEEPSLATE, InfinityButtonsBlocks.DEEPSLATE_LARGE_BUTTON);
        genBothButtons(i, InfinityButtonsBlocks.GRANITE_BUTTON, Items.GRANITE, InfinityButtonsBlocks.GRANITE_LARGE_BUTTON);
        genBothButtons(i, InfinityButtonsBlocks.DIORITE_BUTTON, Items.DIORITE, InfinityButtonsBlocks.DIORITE_LARGE_BUTTON);
        genBothButtons(i, InfinityButtonsBlocks.ANDESITE_BUTTON, Items.ANDESITE, InfinityButtonsBlocks.ANDESITE_LARGE_BUTTON);
        genBothButtons(i, InfinityButtonsBlocks.CALCITE_BUTTON, Items.CALCITE, InfinityButtonsBlocks.CALCITE_LARGE_BUTTON);
        genBothButtons(i, InfinityButtonsBlocks.TUFF_BUTTON, Items.TUFF, InfinityButtonsBlocks.TUFF_LARGE_BUTTON);
        genBothButtons(i, InfinityButtonsBlocks.DRIPSTONE_BUTTON, Items.DRIPSTONE_BLOCK, InfinityButtonsBlocks.DRIPSTONE_LARGE_BUTTON);

        genBothCopperButtons(i, InfinityButtonsBlocks.COPPER_BUTTON, Items.COPPER_BLOCK, InfinityButtonsBlocks.COPPER_LARGE_BUTTON);
        genBothCopperButtons(i, InfinityButtonsBlocks.EXPOSED_COPPER_BUTTON, Items.EXPOSED_COPPER, InfinityButtonsBlocks.EXPOSED_COPPER_LARGE_BUTTON);
        genBothCopperButtons(i, InfinityButtonsBlocks.WEATHERED_COPPER_BUTTON, Items.WEATHERED_COPPER, InfinityButtonsBlocks.WEATHERED_COPPER_LARGE_BUTTON);
        genBothCopperButtons(i, InfinityButtonsBlocks.OXIDIZED_COPPER_BUTTON, Items.OXIDIZED_COPPER, InfinityButtonsBlocks.OXIDIZED_COPPER_LARGE_BUTTON);
        genBothWaxedCopperButtons(i, InfinityButtonsBlocks.WAXED_COPPER_BUTTON, InfinityButtonsBlocks.COPPER_BUTTON, InfinityButtonsBlocks.WAXED_COPPER_LARGE_BUTTON, InfinityButtonsBlocks.COPPER_LARGE_BUTTON);
        genBothWaxedCopperButtons(i, InfinityButtonsBlocks.WAXED_EXPOSED_COPPER_BUTTON, InfinityButtonsBlocks.EXPOSED_COPPER_BUTTON, InfinityButtonsBlocks.WAXED_EXPOSED_COPPER_LARGE_BUTTON, InfinityButtonsBlocks.EXPOSED_COPPER_LARGE_BUTTON);
        genBothWaxedCopperButtons(i, InfinityButtonsBlocks.WAXED_WEATHERED_COPPER_BUTTON, InfinityButtonsBlocks.WEATHERED_COPPER_BUTTON, InfinityButtonsBlocks.WAXED_WEATHERED_COPPER_LARGE_BUTTON, InfinityButtonsBlocks.WEATHERED_COPPER_LARGE_BUTTON);
        genBothWaxedCopperButtons(i, InfinityButtonsBlocks.WAXED_OXIDIZED_COPPER_BUTTON, InfinityButtonsBlocks.OXIDIZED_COPPER_BUTTON, InfinityButtonsBlocks.WAXED_OXIDIZED_COPPER_LARGE_BUTTON, InfinityButtonsBlocks.OXIDIZED_COPPER_LARGE_BUTTON);
        genBothStickyCopperButtons(i, InfinityButtonsBlocks.STICKY_COPPER_BUTTON, InfinityButtonsBlocks.WAXED_COPPER_BUTTON, InfinityButtonsBlocks.STICKY_COPPER_LARGE_BUTTON, InfinityButtonsBlocks.WAXED_COPPER_LARGE_BUTTON);
        genBothStickyCopperButtons(i, InfinityButtonsBlocks.STICKY_EXPOSED_COPPER_BUTTON, InfinityButtonsBlocks.WAXED_EXPOSED_COPPER_BUTTON, InfinityButtonsBlocks.STICKY_EXPOSED_COPPER_LARGE_BUTTON, InfinityButtonsBlocks.WAXED_EXPOSED_COPPER_LARGE_BUTTON);
        genBothStickyCopperButtons(i, InfinityButtonsBlocks.STICKY_WEATHERED_COPPER_BUTTON, InfinityButtonsBlocks.WAXED_WEATHERED_COPPER_BUTTON, InfinityButtonsBlocks.STICKY_WEATHERED_COPPER_LARGE_BUTTON, InfinityButtonsBlocks.WAXED_WEATHERED_COPPER_LARGE_BUTTON);
        genBothStickyCopperButtons(i, InfinityButtonsBlocks.STICKY_OXIDIZED_COPPER_BUTTON, InfinityButtonsBlocks.WAXED_OXIDIZED_COPPER_BUTTON, InfinityButtonsBlocks.STICKY_OXIDIZED_COPPER_LARGE_BUTTON, InfinityButtonsBlocks.WAXED_OXIDIZED_COPPER_LARGE_BUTTON);

        genBothButtonsFromStone(i, InfinityButtonsBlocks.IRON_BUTTON, Items.IRON_INGOT, InfinityButtonsBlocks.IRON_LARGE_BUTTON);
        genBothButtonsFromStone(i, InfinityButtonsBlocks.GOLD_BUTTON, Items.GOLD_INGOT, InfinityButtonsBlocks.GOLD_LARGE_BUTTON);
        genBothButtonsFromStone(i, InfinityButtonsBlocks.EMERALD_BUTTON, Items.EMERALD, InfinityButtonsBlocks.EMERALD_LARGE_BUTTON);
        genBothButtonsFromStone(i, InfinityButtonsBlocks.DIAMOND_BUTTON, Items.DIAMOND, InfinityButtonsBlocks.DIAMOND_LARGE_BUTTON);

        genBothButtons(i, InfinityButtonsBlocks.PRISMARINE_BUTTON, Items.PRISMARINE, InfinityButtonsBlocks.PRISMARINE_LARGE_BUTTON);
        genBothButtons(i, InfinityButtonsBlocks.PRISMARINE_BRICK_BUTTON, Items.PRISMARINE_BRICKS, InfinityButtonsBlocks.PRISMARINE_BRICK_LARGE_BUTTON);
        genBothButtons(i, InfinityButtonsBlocks.DARK_PRISMARINE_BUTTON, Items.DARK_PRISMARINE, InfinityButtonsBlocks.DARK_PRISMARINE_LARGE_BUTTON);
        genBothButtons(i, InfinityButtonsBlocks.SAND_BUTTON, Items.SAND, InfinityButtonsBlocks.SAND_LARGE_BUTTON);
        genBothButtons(i, InfinityButtonsBlocks.RED_SAND_BUTTON, Items.RED_SAND, InfinityButtonsBlocks.RED_SAND_LARGE_BUTTON);
        genBothButtons(i, InfinityButtonsBlocks.GRAVEL_BUTTON, Items.GRAVEL, InfinityButtonsBlocks.GRAVEL_LARGE_BUTTON);

        genBothConcreteButtons(i, InfinityButtonsBlocks.WHITE_CONCRETE_POWDER_BUTTON, Items.WHITE_CONCRETE_POWDER, InfinityButtonsBlocks.WHITE_CONCRETE_POWDER_LARGE_BUTTON);
        genBothConcreteButtons(i, InfinityButtonsBlocks.LIGHT_GRAY_CONCRETE_POWDER_BUTTON, Items.LIGHT_GRAY_CONCRETE_POWDER, InfinityButtonsBlocks.LIGHT_GRAY_CONCRETE_POWDER_LARGE_BUTTON);
        genBothConcreteButtons(i, InfinityButtonsBlocks.GRAY_CONCRETE_POWDER_BUTTON, Items.GRAY_CONCRETE_POWDER, InfinityButtonsBlocks.GRAY_CONCRETE_POWDER_LARGE_BUTTON);
        genBothConcreteButtons(i, InfinityButtonsBlocks.BLACK_CONCRETE_POWDER_BUTTON, Items.BLACK_CONCRETE_POWDER, InfinityButtonsBlocks.BLACK_CONCRETE_POWDER_LARGE_BUTTON);
        genBothConcreteButtons(i, InfinityButtonsBlocks.BROWN_CONCRETE_POWDER_BUTTON, Items.BROWN_CONCRETE_POWDER, InfinityButtonsBlocks.BROWN_CONCRETE_POWDER_LARGE_BUTTON);
        genBothConcreteButtons(i, InfinityButtonsBlocks.RED_CONCRETE_POWDER_BUTTON, Items.RED_CONCRETE_POWDER, InfinityButtonsBlocks.RED_CONCRETE_POWDER_LARGE_BUTTON);
        genBothConcreteButtons(i, InfinityButtonsBlocks.ORANGE_CONCRETE_POWDER_BUTTON, Items.ORANGE_CONCRETE_POWDER, InfinityButtonsBlocks.ORANGE_CONCRETE_POWDER_LARGE_BUTTON);
        genBothConcreteButtons(i, InfinityButtonsBlocks.YELLOW_CONCRETE_POWDER_BUTTON, Items.YELLOW_CONCRETE_POWDER, InfinityButtonsBlocks.YELLOW_CONCRETE_POWDER_LARGE_BUTTON);
        genBothConcreteButtons(i, InfinityButtonsBlocks.LIME_CONCRETE_POWDER_BUTTON, Items.LIME_CONCRETE_POWDER, InfinityButtonsBlocks.LIME_CONCRETE_POWDER_LARGE_BUTTON);
        genBothConcreteButtons(i, InfinityButtonsBlocks.GREEN_CONCRETE_POWDER_BUTTON, Items.GREEN_CONCRETE_POWDER, InfinityButtonsBlocks.GREEN_CONCRETE_POWDER_LARGE_BUTTON);
        genBothConcreteButtons(i, InfinityButtonsBlocks.CYAN_CONCRETE_POWDER_BUTTON, Items.CYAN_CONCRETE_POWDER, InfinityButtonsBlocks.CYAN_CONCRETE_POWDER_LARGE_BUTTON);
        genBothConcreteButtons(i, InfinityButtonsBlocks.LIGHT_BLUE_CONCRETE_POWDER_BUTTON, Items.LIGHT_BLUE_CONCRETE_POWDER, InfinityButtonsBlocks.LIGHT_BLUE_CONCRETE_POWDER_LARGE_BUTTON);
        genBothConcreteButtons(i, InfinityButtonsBlocks.BLUE_CONCRETE_POWDER_BUTTON, Items.BLUE_CONCRETE_POWDER, InfinityButtonsBlocks.BLUE_CONCRETE_POWDER_LARGE_BUTTON);
        genBothConcreteButtons(i, InfinityButtonsBlocks.PURPLE_CONCRETE_POWDER_BUTTON, Items.PURPLE_CONCRETE_POWDER, InfinityButtonsBlocks.PURPLE_CONCRETE_POWDER_LARGE_BUTTON);
        genBothConcreteButtons(i, InfinityButtonsBlocks.MAGENTA_CONCRETE_POWDER_BUTTON, Items.MAGENTA_CONCRETE_POWDER, InfinityButtonsBlocks.MAGENTA_CONCRETE_POWDER_LARGE_BUTTON);
        genBothConcreteButtons(i, InfinityButtonsBlocks.PINK_CONCRETE_POWDER_BUTTON, Items.PINK_CONCRETE_POWDER, InfinityButtonsBlocks.PINK_CONCRETE_POWDER_LARGE_BUTTON);

        genWoodenLargeButton(i, InfinityButtonsBlocks.OAK_LARGE_BUTTON, Items.OAK_BUTTON, Items.OAK_PLANKS);
        genWoodenLargeButton(i, InfinityButtonsBlocks.SPRUCE_LARGE_BUTTON, Items.SPRUCE_BUTTON, Items.SPRUCE_PLANKS);
        genWoodenLargeButton(i, InfinityButtonsBlocks.BIRCH_LARGE_BUTTON, Items.BIRCH_BUTTON, Items.BIRCH_PLANKS);
        genWoodenLargeButton(i, InfinityButtonsBlocks.JUNGLE_LARGE_BUTTON, Items.JUNGLE_BUTTON, Items.JUNGLE_PLANKS);
        genWoodenLargeButton(i, InfinityButtonsBlocks.ACACIA_LARGE_BUTTON, Items.ACACIA_BUTTON, Items.ACACIA_PLANKS);
        genWoodenLargeButton(i, InfinityButtonsBlocks.DARK_OAK_LARGE_BUTTON, Items.DARK_OAK_BUTTON, Items.DARK_OAK_PLANKS);
        genWoodenLargeButton(i, InfinityButtonsBlocks.MANGROVE_LARGE_BUTTON, Items.MANGROVE_BUTTON, Items.MANGROVE_PLANKS);
        genWoodenLargeButton(i, InfinityButtonsBlocks.CRIMSON_LARGE_BUTTON, Items.CRIMSON_BUTTON, Items.CRIMSON_PLANKS);
        genWoodenLargeButton(i, InfinityButtonsBlocks.WARPED_LARGE_BUTTON, Items.WARPED_BUTTON, Items.WARPED_PLANKS);

        genLargeButton(i, InfinityButtonsBlocks.STONE_LARGE_BUTTON, Items.STONE_BUTTON, Items.STONE);
        genLargeButton(i, InfinityButtonsBlocks.POLISHED_BLACKSTONE_LARGE_BUTTON, Items.POLISHED_BLACKSTONE_BUTTON, Items.POLISHED_BLACKSTONE);

        genEmergencyButton(i, InfinityButtonsBlocks.WHITE_EMERGENCY_BUTTON, Items.WHITE_DYE, InfinityButtonsBlocks.WHITE_SAFE_EMERGENCY_BUTTON);
        genEmergencyButton(i, InfinityButtonsBlocks.LIGHT_GRAY_EMERGENCY_BUTTON, Items.LIGHT_GRAY_DYE, InfinityButtonsBlocks.LIGHT_GRAY_SAFE_EMERGENCY_BUTTON);
        genEmergencyButton(i, InfinityButtonsBlocks.GRAY_EMERGENCY_BUTTON, Items.GRAY_DYE, InfinityButtonsBlocks.GRAY_SAFE_EMERGENCY_BUTTON);
        genEmergencyButton(i, InfinityButtonsBlocks.BLACK_EMERGENCY_BUTTON, Items.BLACK_DYE, InfinityButtonsBlocks.BLACK_SAFE_EMERGENCY_BUTTON);
        genEmergencyButton(i, InfinityButtonsBlocks.BROWN_EMERGENCY_BUTTON, Items.BROWN_DYE, InfinityButtonsBlocks.BROWN_SAFE_EMERGENCY_BUTTON);
        genEmergencyButton(i, InfinityButtonsBlocks.RED_EMERGENCY_BUTTON, Items.RED_DYE, InfinityButtonsBlocks.RED_SAFE_EMERGENCY_BUTTON);
        genEmergencyButton(i, InfinityButtonsBlocks.ORANGE_EMERGENCY_BUTTON, Items.ORANGE_DYE, InfinityButtonsBlocks.ORANGE_SAFE_EMERGENCY_BUTTON);
        genEmergencyButton(i, InfinityButtonsBlocks.YELLOW_EMERGENCY_BUTTON, Items.YELLOW_DYE, InfinityButtonsBlocks.YELLOW_SAFE_EMERGENCY_BUTTON);
        genEmergencyButton(i, InfinityButtonsBlocks.LIME_EMERGENCY_BUTTON, Items.LIME_DYE, InfinityButtonsBlocks.LIME_SAFE_EMERGENCY_BUTTON);
        genEmergencyButton(i, InfinityButtonsBlocks.GREEN_EMERGENCY_BUTTON, Items.GREEN_DYE, InfinityButtonsBlocks.GREEN_SAFE_EMERGENCY_BUTTON);
        genEmergencyButton(i, InfinityButtonsBlocks.CYAN_EMERGENCY_BUTTON, Items.CYAN_DYE, InfinityButtonsBlocks.CYAN_SAFE_EMERGENCY_BUTTON);
        genEmergencyButton(i, InfinityButtonsBlocks.LIGHT_BLUE_EMERGENCY_BUTTON, Items.LIGHT_BLUE_DYE, InfinityButtonsBlocks.LIGHT_BLUE_SAFE_EMERGENCY_BUTTON);
        genEmergencyButton(i, InfinityButtonsBlocks.BLUE_EMERGENCY_BUTTON, Items.BLUE_DYE, InfinityButtonsBlocks.BLUE_SAFE_EMERGENCY_BUTTON);
        genEmergencyButton(i, InfinityButtonsBlocks.PURPLE_EMERGENCY_BUTTON, Items.PURPLE_DYE, InfinityButtonsBlocks.PURPLE_SAFE_EMERGENCY_BUTTON);
        genEmergencyButton(i, InfinityButtonsBlocks.MAGENTA_EMERGENCY_BUTTON, Items.MAGENTA_DYE, InfinityButtonsBlocks.MAGENTA_SAFE_EMERGENCY_BUTTON);
        genEmergencyButton(i, InfinityButtonsBlocks.PINK_EMERGENCY_BUTTON, Items.PINK_DYE, InfinityButtonsBlocks.PINK_SAFE_EMERGENCY_BUTTON);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, InfinityButtonsBlocks.FANCY_EMERGENCY_BUTTON)
                .pattern("GGG").input('G', Items.GLOWSTONE_DUST)
                .pattern("GBG").input('B', TagItemGen.EMERGENCY_BUTTONS)
                .pattern("GGG")
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.GLOWSTONE_DUST)).offerTo(i);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, InfinityButtonsBlocks.FANCY_SAFE_EMERGENCY_BUTTON)
                .pattern("GGG").input('G', ConventionalItemTags.GLASS_PANES)
                .pattern("GBG").input('B', InfinityButtonsBlocks.FANCY_EMERGENCY_BUTTON)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.GLOWSTONE_DUST)).offerTo(i);

        genSecretButton(i, InfinityButtonsBlocks.BOOKSHELF_SECRET_BUTTON, Items.BOOKSHELF);
        genSecretButton(i, InfinityButtonsBlocks.BRICK_SECRET_BUTTON, Items.BRICKS);
        genSecretButton(i, InfinityButtonsBlocks.STONE_BRICK_SECRET_BUTTON, Items.STONE_BRICKS);
        genSecretButton(i, InfinityButtonsBlocks.MOSSY_STONE_BRICK_SECRET_BUTTON, Items.MOSSY_STONE_BRICKS);
        genSecretButton(i, InfinityButtonsBlocks.CRACKED_STONE_BRICK_SECRET_BUTTON, Items.CRACKED_STONE_BRICKS);
        genSecretButton(i, InfinityButtonsBlocks.CHISELED_STONE_BRICK_SECRET_BUTTON, Items.CHISELED_STONE_BRICKS);
        genSecretButton(i, InfinityButtonsBlocks.DEEPSLATE_BRICK_SECRET_BUTTON, Items.DEEPSLATE_BRICKS);
        genSecretButton(i, InfinityButtonsBlocks.CRACKED_DEEPSLATE_BRICK_SECRET_BUTTON, Items.CRACKED_DEEPSLATE_BRICKS);
        genSecretButton(i, InfinityButtonsBlocks.DEEPSLATE_TILE_SECRET_BUTTON, Items.DEEPSLATE_TILES);
        genSecretButton(i, InfinityButtonsBlocks.CRACKED_DEEPSLATE_TILE_SECRET_BUTTON, Items.CRACKED_DEEPSLATE_TILES);
        genSecretButton(i, InfinityButtonsBlocks.OAK_PLANK_SECRET_BUTTON, Items.OAK_PLANKS);
        genSecretButton(i, InfinityButtonsBlocks.SPRUCE_PLANK_SECRET_BUTTON, Items.SPRUCE_PLANKS);
        genSecretButton(i, InfinityButtonsBlocks.BIRCH_PLANK_SECRET_BUTTON, Items.BIRCH_PLANKS);
        genSecretButton(i, InfinityButtonsBlocks.JUNGLE_PLANK_SECRET_BUTTON, Items.JUNGLE_PLANKS);
        genSecretButton(i, InfinityButtonsBlocks.ACACIA_PLANK_SECRET_BUTTON, Items.ACACIA_PLANKS);
        genSecretButton(i, InfinityButtonsBlocks.DARK_OAK_PLANK_SECRET_BUTTON, Items.DARK_OAK_PLANKS);
        genSecretButton(i, InfinityButtonsBlocks.MANGROVE_PLANK_SECRET_BUTTON, Items.MANGROVE_PLANKS);
        genSecretButton(i, InfinityButtonsBlocks.CRIMSON_PLANK_SECRET_BUTTON, Items.CRIMSON_PLANKS);
        genSecretButton(i, InfinityButtonsBlocks.WARPED_PLANK_SECRET_BUTTON, Items.WARPED_PLANKS);
        genSecretButton(i, InfinityButtonsBlocks.MUD_BRICK_SECRET_BUTTON, Items.MUD_BRICKS);
        genSecretButton(i, InfinityButtonsBlocks.END_STONE_BRICK_SECRET_BUTTON, Items.END_STONE_BRICKS);
        genSecretButton(i, InfinityButtonsBlocks.PURPUR_BLOCK_SECRET_BUTTON, Items.PURPUR_BLOCK);
        genSecretButton(i, InfinityButtonsBlocks.QUARTZ_BRICK_SECRET_BUTTON, Items.QUARTZ_BRICKS);
        genSecretButton(i, InfinityButtonsBlocks.DARK_PRISMARINE_SECRET_BUTTON, Items.DARK_PRISMARINE);
        genSecretButton(i, InfinityButtonsBlocks.POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON, Items.POLISHED_BLACKSTONE_BRICKS);
        genSecretButton(i, InfinityButtonsBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON, Items.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        genSecretButton(i, InfinityButtonsBlocks.CHISELED_POLISHED_BLACKSTONE_SECRET_BUTTON, Items.CHISELED_POLISHED_BLACKSTONE);
        genSecretButton(i, InfinityButtonsBlocks.NETHER_BRICK_SECRET_BUTTON, Items.NETHER_BRICKS);
        genSecretButton(i, InfinityButtonsBlocks.CRACKED_NETHER_BRICK_SECRET_BUTTON, Items.CRACKED_NETHER_BRICKS);
        genSecretButton(i, InfinityButtonsBlocks.CHISELED_NETHER_BRICK_SECRET_BUTTON, Items.CHISELED_NETHER_BRICKS);
        genSecretButton(i, InfinityButtonsBlocks.RED_NETHER_BRICK_SECRET_BUTTON, Items.RED_NETHER_BRICKS);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, InfinityButtonsBlocks.DOORBELL)
                .input(InfinityButtonsBlocks.DARK_OAK_LARGE_BUTTON)
                .input(Items.GOLD_NUGGET)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.DARK_OAK_PLANKS)).offerTo(i);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, InfinityButtonsBlocks.DOORBELL_BUTTON)
                .input(InfinityButtonsBlocks.DARK_OAK_LARGE_BUTTON)
                .input(Items.GOLD_NUGGET)
                .input(Items.REDSTONE)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.DARK_OAK_PLANKS)).offerTo(i, new Identifier("doorbell_button_1"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, InfinityButtonsBlocks.DOORBELL_BUTTON)
                .input(InfinityButtonsBlocks.DOORBELL)
                .input(Items.REDSTONE)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.DARK_OAK_PLANKS)).offerTo(i, new Identifier("doorbell_button_2"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, InfinityButtonsBlocks.LAMP_BUTTON, 2)
                .input(Items.REDSTONE_LAMP)
                .input(Items.STONE_BUTTON)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.REDSTONE_LAMP)).offerTo(i);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, InfinityButtonsBlocks.LAMP_LEVER, 2)
                .input(Items.REDSTONE_LAMP)
                .input(Items.LEVER)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.REDSTONE_LAMP)).offerTo(i);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, InfinityButtonsBlocks.LETTER_BUTTON)
                .pattern("ISI").input('S', InfinityButtonsBlocks.SPRUCE_LARGE_BUTTON)
                .pattern(" I ").input('I', Items.IRON_INGOT)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.IRON_INGOT)).offerTo(i);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, InfinityButtonsBlocks.LETTER_LEVER)
                .input(InfinityButtonsBlocks.LETTER_BUTTON)
                .input(Items.LEVER)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(InfinityButtonsBlocks.LETTER_BUTTON)).offerTo(i);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, InfinityButtonsBlocks.LANTERN_BUTTON)
                .input(Items.LANTERN)
                .input(Items.STONE_BUTTON)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.LANTERN)).offerTo(i);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, InfinityButtonsBlocks.LANTERN_LEVER)
                .input(Items.LANTERN)
                .input(Items.LEVER)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.LANTERN)).offerTo(i);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, InfinityButtonsBlocks.SOUL_LANTERN_BUTTON)
                .input(Items.SOUL_LANTERN)
                .input(Items.STONE_BUTTON)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.SOUL_LANTERN)).offerTo(i);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, InfinityButtonsBlocks.SOUL_LANTERN_LEVER)
                .input(Items.SOUL_LANTERN)
                .input(Items.LEVER)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.SOUL_LANTERN)).offerTo(i);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, InfinityButtonsBlocks.SMALL_CONSOLE_BUTTON, 4)
                .pattern("IRI").input('R', Items.REDSTONE)
                .pattern("III").input('I', Items.IRON_INGOT)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.IRON_INGOT)).offerTo(i);

        genConsoleLever(i, InfinityButtonsBlocks.SMALL_CONSOLE_LEVER, InfinityButtonsBlocks.SMALL_CONSOLE_BUTTON);

        genConsoleButton(i, InfinityButtonsBlocks.CONSOLE_BUTTON, InfinityButtonsBlocks.SMALL_CONSOLE_BUTTON);
        genConsoleLever(i, InfinityButtonsBlocks.CONSOLE_LEVER, InfinityButtonsBlocks.CONSOLE_BUTTON);

        genConsoleButton(i, InfinityButtonsBlocks.LARGE_CONSOLE_BUTTON, InfinityButtonsBlocks.CONSOLE_BUTTON);
        genConsoleLever(i, InfinityButtonsBlocks.LARGE_CONSOLE_LEVER, InfinityButtonsBlocks.LARGE_CONSOLE_BUTTON);

        genConsoleButton(i, InfinityButtonsBlocks.BIG_CONSOLE_BUTTON, InfinityButtonsBlocks.LARGE_CONSOLE_BUTTON);
        genConsoleLever(i, InfinityButtonsBlocks.BIG_CONSOLE_LEVER, InfinityButtonsBlocks.BIG_CONSOLE_BUTTON);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, InfinityButtonsBlocks.TORCH_BUTTON)
                .pattern("C").input('C', ItemTags.COALS)
                .pattern("#").input('#', Items.STONE_BUTTON)
                .pattern("S").input('S', Items.STICK)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.STONE_PICKAXE)).offerTo(i);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, InfinityButtonsBlocks.TORCH_LEVER)
                .pattern("C").input('C', ItemTags.COALS)
                .pattern("#").input('#', Items.LEVER)
                .pattern("S").input('S', Items.STICK)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.STONE_PICKAXE)).offerTo(i);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, InfinityButtonsBlocks.SOUL_TORCH_BUTTON)
                .pattern(" C").input('C', ItemTags.COALS)
                .pattern("#S").input('#', Items.STONE_BUTTON)
                .pattern(" O").input('S', Items.STICK)
                .input('O', ItemTags.SOUL_FIRE_BASE_BLOCKS)
                .criterion("has_thing", RecipeProvider.conditionsFromTag(ItemTags.SOUL_FIRE_BASE_BLOCKS)).offerTo(i);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, InfinityButtonsBlocks.SOUL_TORCH_LEVER)
                .pattern(" C").input('C', ItemTags.COALS)
                .pattern("#S").input('#', Items.LEVER)
                .pattern(" O").input('S', Items.STICK)
                .input('O', ItemTags.SOUL_FIRE_BASE_BLOCKS)
                .criterion("has_thing", RecipeProvider.conditionsFromTag(ItemTags.SOUL_FIRE_BASE_BLOCKS)).offerTo(i);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, InfinityButtonsBlocks.REDSTONE_TORCH_BUTTON)
                .pattern("C").input('C', Items.REDSTONE)
                .pattern("#").input('#', Items.STONE_BUTTON)
                .pattern("S").input('S', Items.STICK)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.REDSTONE)).offerTo(i);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, InfinityButtonsBlocks.REDSTONE_TORCH_LEVER)
                .pattern("C").input('C', Items.REDSTONE)
                .pattern("#").input('#', Items.LEVER)
                .pattern("S").input('S', Items.STICK)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.REDSTONE)).offerTo(i);

        genStonecutter(i, Items.STONE_BUTTON, Items.STONE);
        genStonecutter(i, InfinityButtonsBlocks.DEEPSLATE_BUTTON, Items.DEEPSLATE);
        genStonecutter(i, InfinityButtonsBlocks.GRANITE_BUTTON, Items.GRANITE);
        genStonecutter(i, InfinityButtonsBlocks.DIORITE_BUTTON, Items.DIORITE);
        genStonecutter(i, InfinityButtonsBlocks.ANDESITE_BUTTON, Items.ANDESITE);
        genStonecutter(i, InfinityButtonsBlocks.CALCITE_BUTTON, Items.CALCITE);
        genStonecutter(i, InfinityButtonsBlocks.TUFF_BUTTON, Items.TUFF);
        genStonecutter(i, InfinityButtonsBlocks.DRIPSTONE_BUTTON, Items.DRIPSTONE_BLOCK);
        genStonecutter(i, InfinityButtonsBlocks.PRISMARINE_BUTTON, Items.PRISMARINE);
        genStonecutter(i, InfinityButtonsBlocks.PRISMARINE_BRICK_BUTTON, Items.PRISMARINE_BRICKS);
        genStonecutter(i, InfinityButtonsBlocks.DARK_PRISMARINE_BUTTON, Items.DARK_PRISMARINE);
        genStonecutter(i, Items.POLISHED_BLACKSTONE_BUTTON, Items.POLISHED_BLACKSTONE);
    }

    protected void genBothButtons(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in, ItemConvertible large) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, out, 4).input(in).criterion("has_thing", RecipeProvider.conditionsFromItem(in)).offerTo(i);
        genLargeButton(i, large, out, in);
    }

    protected void genBothCopperButtons(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in, ItemConvertible large) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, out, 2).input(in).input(Items.STONE_BUTTON).group("copper_buttons").criterion("has_thing", RecipeProvider.conditionsFromItem(Items.COPPER_INGOT)).offerTo(i);
        genLargeButton(i, large, out, Items.COPPER_INGOT, "copper_large_buttons");
    }

    protected void genBothWaxedCopperButtons(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in, ItemConvertible largeOut, ItemConvertible largeIn) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, out).input(in).input(Items.HONEYCOMB).group("waxed_copper_buttons").criterion("has_thing", RecipeProvider.conditionsFromItem(Items.COPPER_INGOT)).offerTo(i);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, largeOut).input(largeIn).input(Items.HONEYCOMB).group("waxed_copper_large_buttons").criterion("has_thing", RecipeProvider.conditionsFromItem(Items.COPPER_INGOT)).offerTo(i, CraftingRecipeJsonBuilder.getItemId(largeOut) + "_waxing");
        genLargeButton(i, largeOut, out, Items.COPPER_INGOT, "waxed_copper_large_buttons");
    }

    protected void genBothStickyCopperButtons(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in, ItemConvertible largeOut, ItemConvertible largeIn) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, out).input(in).input(Items.HONEY_BOTTLE).group("sticky_copper_buttons").criterion("has_thing", RecipeProvider.conditionsFromItem(Items.COPPER_INGOT)).offerTo(i);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, largeOut).input(largeIn).input(Items.HONEY_BOTTLE).group("sticky_copper_large_buttons").criterion("has_thing", RecipeProvider.conditionsFromItem(Items.COPPER_INGOT)).offerTo(i, CraftingRecipeJsonBuilder.getItemId(largeOut) + "_pouring_honey");
        genLargeButton(i, largeOut, out, Items.COPPER_INGOT, "sticky_copper_large_buttons");
    }
    protected void genBothConcreteButtons(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in, ItemConvertible large) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, out, 4).input(in).group("concrete_powder_buttons").criterion("has_thing", RecipeProvider.conditionsFromItem(in)).offerTo(i);
        genLargeButton(i, large, out, in, "concrete_powder_large_buttons");
    }

    protected void genSecretButton(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, out).input(in).input(Items.STONE_BUTTON).group("secret_buttons").criterion("has_thing", RecipeProvider.conditionsFromItem(in)).offerTo(i);
    }

    protected void genBothButtonsFromStone(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in, ItemConvertible large) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, out, 2).input(in).input(Items.STONE_BUTTON).criterion("has_thing", RecipeProvider.conditionsFromItem(in)).offerTo(i);
        genLargeButton(i, large, out, in);
    }

    protected void genEmergencyButton(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible dye, ItemConvertible safe) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, out).input(dye).input(Items.STONE_BUTTON).group("emergency_buttons").criterion("has_thing", RecipeProvider.conditionsFromItem(dye)).offerTo(i);
        genSafetyButton(i, safe, out, dye);
    }

    protected void genSafetyButton(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in, ItemConvertible dye) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, out)
                .pattern("GGG").input('G', ConventionalItemTags.GLASS_PANES)
                .pattern("GBG").input('B', in)
                .group("safe_emergency_buttons").criterion("has_thing", RecipeProvider.conditionsFromItem(dye)).offerTo(i);
    }

    // Without group
    protected void genLargeButton(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in, ItemConvertible adv) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, out).input(in).input(in).criterion("has_thing", RecipeProvider.conditionsFromItem(adv)).offerTo(i);
    }

    // With group
    protected void genLargeButton(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in, ItemConvertible adv, String group) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, out).input(in).input(in).group(group).criterion("has_thing", RecipeProvider.conditionsFromItem(adv)).offerTo(i);
    }

    protected void genWoodenLargeButton(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in, ItemConvertible adv) {
        genLargeButton(i, out, in, adv, "wooden_large_buttons");
    }

    protected void genConsoleButton(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, out).input(in).input(Items.IRON_INGOT).criterion("has_thing", RecipeProvider.conditionsFromItem(Items.IRON_INGOT)).offerTo(i);
    }

    protected void genConsoleLever(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, out).input(in).input(Items.LEVER).criterion("has_thing", RecipeProvider.conditionsFromItem(Items.IRON_INGOT)).offerTo(i);
    }

    protected void genStonecutter(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in) {
        RecipeProvider.offerStonecuttingRecipe(i, RecipeCategory.REDSTONE, out, in, 8);
    }
}
