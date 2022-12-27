package net.larsmans.infinitybuttons.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.minecraft.data.server.RecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class RecipeGen extends FabricRecipeProvider {
    public RecipeGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> i) {
        generateButton(i, InfinityButtonsBlocks.DEEPSLATE_BUTTON, Items.DEEPSLATE, InfinityButtonsBlocks.DEEPSLATE_LARGE_BUTTON);
        generateButton(i, InfinityButtonsBlocks.GRANITE_BUTTON, Items.GRANITE, InfinityButtonsBlocks.GRANITE_LARGE_BUTTON);
        generateButton(i, InfinityButtonsBlocks.DIORITE_BUTTON, Items.DIORITE, InfinityButtonsBlocks.DIORITE_LARGE_BUTTON);
        generateButton(i, InfinityButtonsBlocks.ANDESITE_BUTTON, Items.ANDESITE, InfinityButtonsBlocks.ANDESITE_LARGE_BUTTON);
        generateButton(i, InfinityButtonsBlocks.CALCITE_BUTTON, Items.CALCITE, InfinityButtonsBlocks.CALCITE_LARGE_BUTTON);
        generateButton(i, InfinityButtonsBlocks.TUFF_BUTTON, Items.TUFF, InfinityButtonsBlocks.TUFF_LARGE_BUTTON);
        generateButton(i, InfinityButtonsBlocks.DRIPSTONE_BUTTON, Items.DRIPSTONE_BLOCK, InfinityButtonsBlocks.DRIPSTONE_LARGE_BUTTON);

        generateCopperButton(i, InfinityButtonsBlocks.COPPER_BUTTON, Items.COPPER_BLOCK, InfinityButtonsBlocks.COPPER_LARGE_BUTTON);
        generateCopperButton(i, InfinityButtonsBlocks.EXPOSED_COPPER_BUTTON, Items.EXPOSED_COPPER, InfinityButtonsBlocks.EXPOSED_COPPER_LARGE_BUTTON);
        generateCopperButton(i, InfinityButtonsBlocks.WEATHERED_COPPER_BUTTON, Items.WEATHERED_COPPER, InfinityButtonsBlocks.WEATHERED_COPPER_LARGE_BUTTON);
        generateCopperButton(i, InfinityButtonsBlocks.OXIDIZED_COPPER_BUTTON, Items.OXIDIZED_COPPER, InfinityButtonsBlocks.OXIDIZED_COPPER_LARGE_BUTTON);
        generateStickyCopperButton(i, InfinityButtonsBlocks.STICKY_COPPER_BUTTON, InfinityButtonsBlocks.COPPER_BUTTON, InfinityButtonsBlocks.STICKY_COPPER_LARGE_BUTTON);
        generateStickyCopperButton(i, InfinityButtonsBlocks.STICKY_EXPOSED_COPPER_BUTTON, InfinityButtonsBlocks.EXPOSED_COPPER_BUTTON, InfinityButtonsBlocks.STICKY_EXPOSED_COPPER_LARGE_BUTTON);
        generateStickyCopperButton(i, InfinityButtonsBlocks.STICKY_WEATHERED_COPPER_BUTTON, InfinityButtonsBlocks.WEATHERED_COPPER_BUTTON, InfinityButtonsBlocks.STICKY_WEATHERED_COPPER_LARGE_BUTTON);
        generateStickyCopperButton(i, InfinityButtonsBlocks.STICKY_OXIDIZED_COPPER_BUTTON, InfinityButtonsBlocks.OXIDIZED_COPPER_BUTTON, InfinityButtonsBlocks.STICKY_OXIDIZED_COPPER_LARGE_BUTTON);

        generateButtonFromStone(i, InfinityButtonsBlocks.IRON_BUTTON, Items.IRON_INGOT, InfinityButtonsBlocks.IRON_LARGE_BUTTON);
        generateButtonFromStone(i, InfinityButtonsBlocks.GOLD_BUTTON, Items.GOLD_INGOT, InfinityButtonsBlocks.GOLD_LARGE_BUTTON);
        generateButtonFromStone(i, InfinityButtonsBlocks.EMERALD_BUTTON, Items.EMERALD, InfinityButtonsBlocks.EMERALD_LARGE_BUTTON);
        generateButtonFromStone(i, InfinityButtonsBlocks.DIAMOND_BUTTON, Items.DIAMOND, InfinityButtonsBlocks.DIAMOND_LARGE_BUTTON);

        generateButton(i, InfinityButtonsBlocks.PRISMARINE_BUTTON, Items.PRISMARINE, InfinityButtonsBlocks.PRISMARINE_LARGE_BUTTON);
        generateButton(i, InfinityButtonsBlocks.PRISMARINE_BRICK_BUTTON, Items.PRISMARINE_BRICKS, InfinityButtonsBlocks.PRISMARINE_BRICK_LARGE_BUTTON);
        generateButton(i, InfinityButtonsBlocks.DARK_PRISMARINE_BUTTON, Items.DARK_PRISMARINE, InfinityButtonsBlocks.DARK_PRISMARINE_LARGE_BUTTON);
        generateButton(i, InfinityButtonsBlocks.SAND_BUTTON, Items.SAND, InfinityButtonsBlocks.SAND_LARGE_BUTTON);
        generateButton(i, InfinityButtonsBlocks.RED_SAND_BUTTON, Items.RED_SAND, InfinityButtonsBlocks.RED_SAND_LARGE_BUTTON);
        generateButton(i, InfinityButtonsBlocks.GRAVEL_BUTTON, Items.GRAVEL, InfinityButtonsBlocks.GRAVEL_LARGE_BUTTON);

        generateSandButton(i, InfinityButtonsBlocks.WHITE_CONCRETE_POWDER_BUTTON, Items.WHITE_CONCRETE_POWDER, InfinityButtonsBlocks.WHITE_CONCRETE_POWDER_LARGE_BUTTON);
        generateSandButton(i, InfinityButtonsBlocks.LIGHT_GRAY_CONCRETE_POWDER_BUTTON, Items.LIGHT_GRAY_CONCRETE_POWDER, InfinityButtonsBlocks.LIGHT_GRAY_CONCRETE_POWDER_LARGE_BUTTON);
        generateSandButton(i, InfinityButtonsBlocks.GRAY_CONCRETE_POWDER_BUTTON, Items.GRAY_CONCRETE_POWDER, InfinityButtonsBlocks.GRAY_CONCRETE_POWDER_LARGE_BUTTON);
        generateSandButton(i, InfinityButtonsBlocks.BLACK_CONCRETE_POWDER_BUTTON, Items.BLACK_CONCRETE_POWDER, InfinityButtonsBlocks.BLACK_CONCRETE_POWDER_LARGE_BUTTON);
        generateSandButton(i, InfinityButtonsBlocks.BROWN_CONCRETE_POWDER_BUTTON, Items.BROWN_CONCRETE_POWDER, InfinityButtonsBlocks.BROWN_CONCRETE_POWDER_LARGE_BUTTON);
        generateSandButton(i, InfinityButtonsBlocks.RED_CONCRETE_POWDER_BUTTON, Items.RED_CONCRETE_POWDER, InfinityButtonsBlocks.RED_CONCRETE_POWDER_LARGE_BUTTON);
        generateSandButton(i, InfinityButtonsBlocks.ORANGE_CONCRETE_POWDER_BUTTON, Items.ORANGE_CONCRETE_POWDER, InfinityButtonsBlocks.ORANGE_CONCRETE_POWDER_LARGE_BUTTON);
        generateSandButton(i, InfinityButtonsBlocks.YELLOW_CONCRETE_POWDER_BUTTON, Items.YELLOW_CONCRETE_POWDER, InfinityButtonsBlocks.YELLOW_CONCRETE_POWDER_LARGE_BUTTON);
        generateSandButton(i, InfinityButtonsBlocks.LIME_CONCRETE_POWDER_BUTTON, Items.LIME_CONCRETE_POWDER, InfinityButtonsBlocks.LIME_CONCRETE_POWDER_LARGE_BUTTON);
        generateSandButton(i, InfinityButtonsBlocks.GREEN_CONCRETE_POWDER_BUTTON, Items.GREEN_CONCRETE_POWDER, InfinityButtonsBlocks.GREEN_CONCRETE_POWDER_LARGE_BUTTON);
        generateSandButton(i, InfinityButtonsBlocks.CYAN_CONCRETE_POWDER_BUTTON, Items.CYAN_CONCRETE_POWDER, InfinityButtonsBlocks.CYAN_CONCRETE_POWDER_LARGE_BUTTON);
        generateSandButton(i, InfinityButtonsBlocks.LIGHT_BLUE_CONCRETE_POWDER_BUTTON, Items.LIGHT_BLUE_CONCRETE_POWDER, InfinityButtonsBlocks.LIGHT_BLUE_CONCRETE_POWDER_LARGE_BUTTON);
        generateSandButton(i, InfinityButtonsBlocks.BLUE_CONCRETE_POWDER_BUTTON, Items.BLUE_CONCRETE_POWDER, InfinityButtonsBlocks.BLUE_CONCRETE_POWDER_LARGE_BUTTON);
        generateSandButton(i, InfinityButtonsBlocks.PURPLE_CONCRETE_POWDER_BUTTON, Items.PURPLE_CONCRETE_POWDER, InfinityButtonsBlocks.PURPLE_CONCRETE_POWDER_LARGE_BUTTON);
        generateSandButton(i, InfinityButtonsBlocks.MAGENTA_CONCRETE_POWDER_BUTTON, Items.MAGENTA_CONCRETE_POWDER, InfinityButtonsBlocks.MAGENTA_CONCRETE_POWDER_LARGE_BUTTON);
        generateSandButton(i, InfinityButtonsBlocks.PINK_CONCRETE_POWDER_BUTTON, Items.PINK_CONCRETE_POWDER, InfinityButtonsBlocks.PINK_CONCRETE_POWDER_LARGE_BUTTON);

        generateWoodenLargeButton(i, InfinityButtonsBlocks.OAK_LARGE_BUTTON, Items.OAK_BUTTON, Items.OAK_PLANKS);
        generateWoodenLargeButton(i, InfinityButtonsBlocks.SPRUCE_LARGE_BUTTON, Items.SPRUCE_BUTTON, Items.SPRUCE_PLANKS);
        generateWoodenLargeButton(i, InfinityButtonsBlocks.BIRCH_LARGE_BUTTON, Items.BIRCH_BUTTON, Items.BIRCH_PLANKS);
        generateWoodenLargeButton(i, InfinityButtonsBlocks.JUNGLE_LARGE_BUTTON, Items.JUNGLE_BUTTON, Items.JUNGLE_PLANKS);
        generateWoodenLargeButton(i, InfinityButtonsBlocks.ACACIA_LARGE_BUTTON, Items.ACACIA_BUTTON, Items.ACACIA_PLANKS);
        generateWoodenLargeButton(i, InfinityButtonsBlocks.DARK_OAK_LARGE_BUTTON, Items.DARK_OAK_BUTTON, Items.DARK_OAK_PLANKS);
        generateWoodenLargeButton(i, InfinityButtonsBlocks.MANGROVE_LARGE_BUTTON, Items.MANGROVE_BUTTON, Items.MANGROVE_PLANKS);
        generateWoodenLargeButton(i, InfinityButtonsBlocks.CRIMSON_LARGE_BUTTON, Items.CRIMSON_BUTTON, Items.CRIMSON_PLANKS);
        generateWoodenLargeButton(i, InfinityButtonsBlocks.WARPED_LARGE_BUTTON, Items.WARPED_BUTTON, Items.WARPED_PLANKS);

        generateLargeButton(i, InfinityButtonsBlocks.STONE_LARGE_BUTTON, Items.STONE_BUTTON, Items.STONE);
        generateLargeButton(i, InfinityButtonsBlocks.POLISHED_BLACKSTONE_LARGE_BUTTON, Items.POLISHED_BLACKSTONE_BUTTON, Items.POLISHED_BLACKSTONE);

        generateEmergencyButton(i, InfinityButtonsBlocks.WHITE_EMERGENCY_BUTTON, Items.WHITE_DYE, InfinityButtonsBlocks.WHITE_SAFE_EMERGENCY_BUTTON);
        generateEmergencyButton(i, InfinityButtonsBlocks.LIGHT_GRAY_EMERGENCY_BUTTON, Items.LIGHT_GRAY_DYE, InfinityButtonsBlocks.LIGHT_GRAY_SAFE_EMERGENCY_BUTTON);
        generateEmergencyButton(i, InfinityButtonsBlocks.GRAY_EMERGENCY_BUTTON, Items.GRAY_DYE, InfinityButtonsBlocks.GRAY_SAFE_EMERGENCY_BUTTON);
        generateEmergencyButton(i, InfinityButtonsBlocks.BLACK_EMERGENCY_BUTTON, Items.BLACK_DYE, InfinityButtonsBlocks.BLACK_SAFE_EMERGENCY_BUTTON);
        generateEmergencyButton(i, InfinityButtonsBlocks.BROWN_EMERGENCY_BUTTON, Items.BROWN_DYE, InfinityButtonsBlocks.BROWN_SAFE_EMERGENCY_BUTTON);
        generateEmergencyButton(i, InfinityButtonsBlocks.RED_EMERGENCY_BUTTON, Items.RED_DYE, InfinityButtonsBlocks.RED_SAFE_EMERGENCY_BUTTON);
        generateEmergencyButton(i, InfinityButtonsBlocks.ORANGE_EMERGENCY_BUTTON, Items.ORANGE_DYE, InfinityButtonsBlocks.ORANGE_SAFE_EMERGENCY_BUTTON);
        generateEmergencyButton(i, InfinityButtonsBlocks.YELLOW_EMERGENCY_BUTTON, Items.YELLOW_DYE, InfinityButtonsBlocks.YELLOW_SAFE_EMERGENCY_BUTTON);
        generateEmergencyButton(i, InfinityButtonsBlocks.LIME_EMERGENCY_BUTTON, Items.LIME_DYE, InfinityButtonsBlocks.LIME_SAFE_EMERGENCY_BUTTON);
        generateEmergencyButton(i, InfinityButtonsBlocks.GREEN_EMERGENCY_BUTTON, Items.GREEN_DYE, InfinityButtonsBlocks.GREEN_SAFE_EMERGENCY_BUTTON);
        generateEmergencyButton(i, InfinityButtonsBlocks.CYAN_EMERGENCY_BUTTON, Items.CYAN_DYE, InfinityButtonsBlocks.CYAN_SAFE_EMERGENCY_BUTTON);
        generateEmergencyButton(i, InfinityButtonsBlocks.LIGHT_BLUE_EMERGENCY_BUTTON, Items.LIGHT_BLUE_DYE, InfinityButtonsBlocks.LIGHT_BLUE_SAFE_EMERGENCY_BUTTON);
        generateEmergencyButton(i, InfinityButtonsBlocks.BLUE_EMERGENCY_BUTTON, Items.BLUE_DYE, InfinityButtonsBlocks.BLUE_SAFE_EMERGENCY_BUTTON);
        generateEmergencyButton(i, InfinityButtonsBlocks.PURPLE_EMERGENCY_BUTTON, Items.PURPLE_DYE, InfinityButtonsBlocks.PURPLE_SAFE_EMERGENCY_BUTTON);
        generateEmergencyButton(i, InfinityButtonsBlocks.MAGENTA_EMERGENCY_BUTTON, Items.MAGENTA_DYE, InfinityButtonsBlocks.MAGENTA_SAFE_EMERGENCY_BUTTON);
        generateEmergencyButton(i, InfinityButtonsBlocks.PINK_EMERGENCY_BUTTON, Items.PINK_DYE, InfinityButtonsBlocks.PINK_SAFE_EMERGENCY_BUTTON);

        ShapedRecipeJsonBuilder.create(InfinityButtonsBlocks.FANCY_EMERGENCY_BUTTON)
                .pattern("GGG").input('G', Items.GLOWSTONE_DUST)
                .pattern("GBG").input('B', TagItemGen.EMERGENCY_BUTTONS)
                .pattern("GGG")
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.GLOWSTONE_DUST)).offerTo(i);

        ShapedRecipeJsonBuilder.create(InfinityButtonsBlocks.FANCY_SAFE_EMERGENCY_BUTTON)
                .pattern("GGG").input('G', ConventionalItemTags.GLASS_PANES)
                .pattern("GBG").input('B', InfinityButtonsBlocks.FANCY_EMERGENCY_BUTTON)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.GLOWSTONE_DUST)).offerTo(i);

        generateSecretButton(i, InfinityButtonsBlocks.BOOKSHELF_SECRET_BUTTON, Items.BOOKSHELF);
        generateSecretButton(i, InfinityButtonsBlocks.BRICK_SECRET_BUTTON, Items.BRICKS);
        generateSecretButton(i, InfinityButtonsBlocks.STONE_BRICK_SECRET_BUTTON, Items.STONE_BRICKS);
        generateSecretButton(i, InfinityButtonsBlocks.MOSSY_STONE_BRICK_SECRET_BUTTON, Items.MOSSY_STONE_BRICKS);
        generateSecretButton(i, InfinityButtonsBlocks.CRACKED_STONE_BRICK_SECRET_BUTTON, Items.CRACKED_STONE_BRICKS);
        generateSecretButton(i, InfinityButtonsBlocks.CHISELED_STONE_BRICK_SECRET_BUTTON, Items.CHISELED_STONE_BRICKS);
        generateSecretButton(i, InfinityButtonsBlocks.DEEPSLATE_BRICK_SECRET_BUTTON, Items.DEEPSLATE_BRICKS);
        generateSecretButton(i, InfinityButtonsBlocks.CRACKED_DEEPSLATE_BRICK_SECRET_BUTTON, Items.CRACKED_DEEPSLATE_BRICKS);
        generateSecretButton(i, InfinityButtonsBlocks.DEEPSLATE_TILE_SECRET_BUTTON, Items.DEEPSLATE_TILES);
        generateSecretButton(i, InfinityButtonsBlocks.CRACKED_DEEPSLATE_TILE_SECRET_BUTTON, Items.CRACKED_DEEPSLATE_TILES);
        generateSecretButton(i, InfinityButtonsBlocks.OAK_PLANK_SECRET_BUTTON, Items.OAK_PLANKS);
        generateSecretButton(i, InfinityButtonsBlocks.SPRUCE_PLANK_SECRET_BUTTON, Items.SPRUCE_PLANKS);
        generateSecretButton(i, InfinityButtonsBlocks.BIRCH_PLANK_SECRET_BUTTON, Items.BIRCH_PLANKS);
        generateSecretButton(i, InfinityButtonsBlocks.JUNGLE_PLANK_SECRET_BUTTON, Items.JUNGLE_PLANKS);
        generateSecretButton(i, InfinityButtonsBlocks.ACACIA_PLANK_SECRET_BUTTON, Items.ACACIA_PLANKS);
        generateSecretButton(i, InfinityButtonsBlocks.DARK_OAK_PLANK_SECRET_BUTTON, Items.DARK_OAK_PLANKS);
        generateSecretButton(i, InfinityButtonsBlocks.MANGROVE_PLANK_SECRET_BUTTON, Items.MANGROVE_PLANKS);
        generateSecretButton(i, InfinityButtonsBlocks.CRIMSON_PLANK_SECRET_BUTTON, Items.CRIMSON_PLANKS);
        generateSecretButton(i, InfinityButtonsBlocks.WARPED_PLANK_SECRET_BUTTON, Items.WARPED_PLANKS);
        generateSecretButton(i, InfinityButtonsBlocks.MUD_BRICK_SECRET_BUTTON, Items.MUD_BRICKS);
        generateSecretButton(i, InfinityButtonsBlocks.END_STONE_BRICK_SECRET_BUTTON, Items.END_STONE_BRICKS);
        generateSecretButton(i, InfinityButtonsBlocks.QUARTZ_BRICK_SECRET_BUTTON, Items.QUARTZ_BRICKS);
        generateSecretButton(i, InfinityButtonsBlocks.DARK_PRISMARINE_SECRET_BUTTON, Items.DARK_PRISMARINE);
        generateSecretButton(i, InfinityButtonsBlocks.POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON, Items.POLISHED_BLACKSTONE_BRICKS);
        generateSecretButton(i, InfinityButtonsBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON, Items.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        generateSecretButton(i, InfinityButtonsBlocks.CHISELED_POLISHED_BLACKSTONE_SECRET_BUTTON, Items.CHISELED_POLISHED_BLACKSTONE);
        generateSecretButton(i, InfinityButtonsBlocks.NETHER_BRICK_SECRET_BUTTON, Items.NETHER_BRICKS);
        generateSecretButton(i, InfinityButtonsBlocks.CRACKED_NETHER_BRICK_SECRET_BUTTON, Items.CRACKED_NETHER_BRICKS);
        generateSecretButton(i, InfinityButtonsBlocks.CHISELED_NETHER_BRICK_SECRET_BUTTON, Items.CHISELED_NETHER_BRICKS);
        generateSecretButton(i, InfinityButtonsBlocks.RED_NETHER_BRICK_SECRET_BUTTON, Items.RED_NETHER_BRICKS);

        ShapelessRecipeJsonBuilder.create(InfinityButtonsBlocks.DOORBELL)
                .input(InfinityButtonsBlocks.DARK_OAK_LARGE_BUTTON)
                .input(Items.GOLD_NUGGET)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.DARK_OAK_PLANKS)).offerTo(i);

        ShapelessRecipeJsonBuilder.create(InfinityButtonsBlocks.DOORBELL_BUTTON)
                .input(InfinityButtonsBlocks.DARK_OAK_LARGE_BUTTON)
                .input(Items.GOLD_NUGGET)
                .input(Items.REDSTONE)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.DARK_OAK_PLANKS)).offerTo(i, new Identifier("doorbell_button_1"));

        ShapelessRecipeJsonBuilder.create(InfinityButtonsBlocks.DOORBELL_BUTTON)
                .input(InfinityButtonsBlocks.DOORBELL)
                .input(Items.REDSTONE)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.DARK_OAK_PLANKS)).offerTo(i, new Identifier("doorbell_button_2"));

        ShapelessRecipeJsonBuilder.create(InfinityButtonsBlocks.LAMP_BUTTON, 2)
                .input(Items.REDSTONE_LAMP)
                .input(Items.STONE_BUTTON)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.REDSTONE_LAMP)).offerTo(i);

        ShapelessRecipeJsonBuilder.create(InfinityButtonsBlocks.LAMP_LEVER, 2)
                .input(Items.REDSTONE_LAMP)
                .input(Items.LEVER)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.REDSTONE_LAMP)).offerTo(i);

        ShapedRecipeJsonBuilder.create(InfinityButtonsBlocks.TORCH_BUTTON)
                .pattern("C").input('C', ItemTags.COALS)
                .pattern("#").input('#', Items.STONE_BUTTON)
                .pattern("S").input('S', Items.STICK)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.STONE_PICKAXE)).offerTo(i);

        ShapedRecipeJsonBuilder.create(InfinityButtonsBlocks.TORCH_LEVER)
                .pattern("C").input('C', ItemTags.COALS)
                .pattern("#").input('#', Items.LEVER)
                .pattern("S").input('S', Items.STICK)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.STONE_PICKAXE)).offerTo(i);

        ShapedRecipeJsonBuilder.create(InfinityButtonsBlocks.SOUL_TORCH_BUTTON)
                .pattern(" C").input('C', ItemTags.COALS)
                .pattern("#S").input('#', Items.STONE_BUTTON)
                .pattern(" O").input('S', Items.STICK)
                .input('O', ItemTags.SOUL_FIRE_BASE_BLOCKS)
                .criterion("has_thing", RecipeProvider.conditionsFromTag(ItemTags.SOUL_FIRE_BASE_BLOCKS)).offerTo(i);

        ShapedRecipeJsonBuilder.create(InfinityButtonsBlocks.SOUL_TORCH_LEVER)
                .pattern(" C").input('C', ItemTags.COALS)
                .pattern("#S").input('#', Items.LEVER)
                .pattern(" O").input('S', Items.STICK)
                .input('O', ItemTags.SOUL_FIRE_BASE_BLOCKS)
                .criterion("has_thing", RecipeProvider.conditionsFromTag(ItemTags.SOUL_FIRE_BASE_BLOCKS)).offerTo(i);

        ShapedRecipeJsonBuilder.create(InfinityButtonsBlocks.REDSTONE_TORCH_BUTTON)
                .pattern("C").input('C', Items.REDSTONE)
                .pattern("#").input('#', Items.STONE_BUTTON)
                .pattern("S").input('S', Items.STICK)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.REDSTONE)).offerTo(i);

        ShapedRecipeJsonBuilder.create(InfinityButtonsBlocks.REDSTONE_TORCH_LEVER)
                .pattern("C").input('C', Items.REDSTONE)
                .pattern("#").input('#', Items.LEVER)
                .pattern("S").input('S', Items.STICK)
                .criterion("has_thing", RecipeProvider.conditionsFromItem(Items.REDSTONE)).offerTo(i);

        generateStonecutter(i, Items.STONE_BUTTON, Items.STONE);
        generateStonecutter(i, InfinityButtonsBlocks.DEEPSLATE_BUTTON, Items.DEEPSLATE);
        generateStonecutter(i, InfinityButtonsBlocks.GRANITE_BUTTON, Items.GRANITE);
        generateStonecutter(i, InfinityButtonsBlocks.DIORITE_BUTTON, Items.DIORITE);
        generateStonecutter(i, InfinityButtonsBlocks.ANDESITE_BUTTON, Items.ANDESITE);
        generateStonecutter(i, InfinityButtonsBlocks.CALCITE_BUTTON, Items.CALCITE);
        generateStonecutter(i, InfinityButtonsBlocks.TUFF_BUTTON, Items.TUFF);
        generateStonecutter(i, InfinityButtonsBlocks.DRIPSTONE_BUTTON, Items.DRIPSTONE_BLOCK);
        generateStonecutter(i, InfinityButtonsBlocks.PRISMARINE_BUTTON, Items.PRISMARINE);
        generateStonecutter(i, InfinityButtonsBlocks.PRISMARINE_BRICK_BUTTON, Items.PRISMARINE_BRICKS);
        generateStonecutter(i, InfinityButtonsBlocks.DARK_PRISMARINE_BUTTON, Items.DARK_PRISMARINE);
        generateStonecutter(i, Items.POLISHED_BLACKSTONE_BUTTON, Items.POLISHED_BLACKSTONE);
    }

    protected void generateButton(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in, ItemConvertible large) {
        ShapelessRecipeJsonBuilder.create(out, 4).input(in).criterion("has_thing", RecipeProvider.conditionsFromItem(in)).offerTo(i);
        generateLargeButton(i, large, out, in);
    }

    protected void generateCopperButton(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in, ItemConvertible large) {
        ShapelessRecipeJsonBuilder.create(out, 2).input(in).input(Items.STONE_BUTTON).group("copper_buttons").criterion("has_thing", RecipeProvider.conditionsFromItem(Items.COPPER_INGOT)).offerTo(i);
        generateLargeButton(i, large, out, Items.COPPER_INGOT, "copper_large_button");
    }

    protected void generateStickyCopperButton(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in, ItemConvertible large) {
        ShapelessRecipeJsonBuilder.create(out).input(in).input(Items.HONEYCOMB).criterion("has_thing", RecipeProvider.conditionsFromItem(Items.COPPER_INGOT)).offerTo(i);
        generateLargeButton(i, large, out, Items.COPPER_INGOT, "sticky_copper_large_button");
    }

    protected void generateSandButton(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in, ItemConvertible large) {
        ShapelessRecipeJsonBuilder.create(out, 4).input(in).group("concrete_powder_buttons").criterion("has_thing", RecipeProvider.conditionsFromItem(in)).offerTo(i);
        generateLargeButton(i, large, out, in, "concrete_powder_large_button");
    }

    protected void generateSecretButton(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in) {
        ShapelessRecipeJsonBuilder.create(out).input(in).input(Items.STONE_BUTTON).group("secret_button").criterion("has_thing", RecipeProvider.conditionsFromItem(in)).offerTo(i);
    }

    protected void generateButtonFromStone(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in, ItemConvertible large) {
        ShapelessRecipeJsonBuilder.create(out, 2).input(in).input(Items.STONE_BUTTON).criterion("has_thing", RecipeProvider.conditionsFromItem(in)).offerTo(i);
        generateLargeButton(i, large, out, in);
    }

    protected void generateEmergencyButton(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible dye, ItemConvertible safe) {
        ShapelessRecipeJsonBuilder.create(out).input(dye).input(Items.STONE_BUTTON).group("emergency_button").criterion("has_thing", RecipeProvider.conditionsFromItem(dye)).offerTo(i);
        generateSafetyButton(i, safe, out, dye);
    }

    protected void generateSafetyButton(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in, ItemConvertible dye) {
        ShapedRecipeJsonBuilder.create(out)
                .pattern("GGG").input('G', ConventionalItemTags.GLASS_PANES)
                .pattern("GBG").input('B', in)
                .group("safe_emergency_buttons").criterion("has_thing", RecipeProvider.conditionsFromItem(dye)).offerTo(i);
    }

    // Without group
    protected void generateLargeButton(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in, ItemConvertible adv) {
        ShapelessRecipeJsonBuilder.create(out).input(in).input(in).criterion("has_thing", RecipeProvider.conditionsFromItem(adv)).offerTo(i);
    }

    // With group
    protected void generateLargeButton(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in, ItemConvertible adv, String group) {
        ShapelessRecipeJsonBuilder.create(out).input(in).input(in).group(group).criterion("has_thing", RecipeProvider.conditionsFromItem(adv)).offerTo(i);
    }

    protected void generateWoodenLargeButton(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in, ItemConvertible adv) {
        generateLargeButton(i, out, in, adv, "wooden_large_button");
    }

    protected void generateStonecutter(Consumer<RecipeJsonProvider> i, ItemConvertible out, ItemConvertible in) {
        RecipeProvider.offerStonecuttingRecipe(i, out, in, 8);
    }
}
