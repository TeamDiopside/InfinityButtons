package nl.teamdiopside.infinitybuttons.datagen.recipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import nl.teamdiopside.infinitybuttons.datagen.conditions.DataCondition;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;

import static net.minecraft.data.recipes.RecipeBuilder.getDefaultRecipeId;

public abstract class RecipeGenerator<T> {

    protected final IBRecipeOutput output;

    protected RecipeGenerator(IBRecipeOutput output) {
        this.output = output;
    }

    public abstract void generate(T block);

    public void addConditions(Block block, DataCondition... conditions) {
        IBRegistryUtils.BlockInfo info = IBRegistryUtils.BlockInfo.from(block);
        this.output.addConditions(ResourceLocation.fromNamespaceAndPath(info.namespace(), info.id()), conditions);
    }

    /**
     * Generate a simple recipe to convert an item into a Lever or Button variant
     * @param withLever Whether to use a Lever (otherwise Stone Button)
     * @param outputCount How many of this item this recipe grants
     */
    protected void convertingRecipe(RecipeOutput recipes, ItemLike input, ItemLike output, boolean withLever, int outputCount, String suffix, @Nullable String group) {
        simpleShapelessRecipe(recipes, input, output, outputCount, suffix, group, input, withLever ? Items.LEVER : Items.STONE_BUTTON);
    }

    /**
     * Generate a simple recipe to convert an item into a Lever or Button variant
     * @param withLever Whether to use a Lever (otherwise Stone Button)
     * @param outputCount How many of this item this recipe grants
     */
    protected void convertingRecipe(RecipeOutput recipes, TagKey<Item> input, ItemLike output, boolean withLever, int outputCount, String suffix, @Nullable String group) {
        simpleShapelessRecipe(recipes, input, output, outputCount, suffix, group, Ingredient.of(input), Ingredient.of(withLever ? Items.LEVER : Items.STONE_BUTTON));
    }

    /**
     * Generate a simple shapeless recipe with multiple ingredients
     * @param unlock The item that unlocks this recipe
     * @param outputCount - How many of this item this recipe grants
     * @param itemLikes - Ingredients to be included in the recipe
     */
    protected void simpleShapelessRecipe(RecipeOutput recipes, ItemLike unlock, ItemLike output, int outputCount, String suffix, @Nullable String group, ItemLike... itemLikes) {
        Ingredient[] ingredients = Arrays.stream(itemLikes).map(Ingredient::of).toArray(Ingredient[]::new);
        simpleShapelessRecipe(recipes, unlock, output, outputCount, suffix, group, ingredients);
    }

    /**
     * Generate a simple shapeless recipe with multiple ingredients
     * @param unlock The item that unlocks this recipe
     * @param outputCount - How many of this item this recipe grants
     * @param itemLikes - Ingredients to be included in the recipe
     */
    protected void simpleShapelessRecipe(RecipeOutput recipes, TagKey<Item> unlock, ItemLike output, int outputCount, String suffix, @Nullable String group, ItemLike... itemLikes) {
        Ingredient[] ingredients = Arrays.stream(itemLikes).map(Ingredient::of).toArray(Ingredient[]::new);
        simpleShapelessRecipe(recipes, unlock, output, outputCount, suffix, group, ingredients);
    }

    /**
     * Generate a simple shapeless recipe with multiple ingredients
     * @param unlock The item that unlocks this recipe
     * @param outputCount - How many of this item this recipe grants
     * @param ingredients - Ingredients to be included in the recipe
     */
    protected void simpleShapelessRecipe(RecipeOutput recipes, ItemLike unlock, ItemLike output, int outputCount, String suffix, @Nullable String group, Ingredient... ingredients) {
        simpleShapelessRecipe(recipes, RecipeProvider.has(unlock), output, outputCount, suffix, group, ingredients);
    }

    /**
     * Generate a simple shapeless recipe with multiple ingredients
     * @param unlock The item that unlocks this recipe
     * @param outputCount - How many of this item this recipe grants
     * @param ingredients - Ingredients to be included in the recipe
     */
    protected void simpleShapelessRecipe(RecipeOutput recipes, TagKey<Item> unlock, ItemLike output, int outputCount, String suffix, @Nullable String group, Ingredient... ingredients) {
        simpleShapelessRecipe(recipes, RecipeProvider.has(unlock), output, outputCount, suffix, group, ingredients);
    }

    /**
     * Generate a simple shapeless recipe with multiple ingredients
     * @param criterion The criterion that unlocks the recipe
     * @param outputCount - How many of this item this recipe grants
     * @param ingredients - Ingredients to be included in the recipe
     */
    private void simpleShapelessRecipe(RecipeOutput recipes, Criterion<?> criterion, ItemLike output, int outputCount, String suffix, @Nullable String group, Ingredient... ingredients) {
        ShapelessRecipeBuilder builder = ShapelessRecipeBuilder
                .shapeless(RecipeCategory.REDSTONE, output, outputCount)
                .unlockedBy("has_thing", criterion);

        for (Ingredient item : ingredients) {
            builder.requires(item);
        }

        if (group != null) builder.group(group);

        if (!Objects.equals(suffix, "")) {
            builder.save(recipes, getDefaultRecipeId(output) + suffix);
        } else {
            builder.save(recipes);
        }
    }
}
