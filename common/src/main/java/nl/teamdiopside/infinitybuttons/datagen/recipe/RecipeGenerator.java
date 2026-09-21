package nl.teamdiopside.infinitybuttons.datagen.recipe;

import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import nl.teamdiopside.infinitybuttons.datagen.conditions.DataCondition;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static net.minecraft.data.recipes.RecipeBuilder.getDefaultRecipeId;

public abstract class RecipeGenerator<T extends Block> {

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
     * Generate a simple recipe to convert an item into another
     */
    protected void convertingRecipe(RecipeOutput recipes, ItemLike input, ItemLike output, String suffix, @Nullable String group) {
        var builder = ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, output)
                .requires(input)
                .unlockedBy("has_thing", RecipeProvider.has(input));

        if (group != null) builder.group(group);

        if (!Objects.equals(suffix, "")) {
            builder.save(recipes, getDefaultRecipeId(output) + suffix);
        } else {
            builder.save(recipes);
        }
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
     * Generate a simple shapeless recipe with multiple ingredients
     * @param unlock The item that unlocks this recipe
     * @param outputCount - How many of this item this recipe grants
     * @param ingredient - Ingredients to be included in the recipe
     */
    protected void simpleShapelessRecipe(RecipeOutput recipes, ItemLike unlock, ItemLike output, int outputCount, String suffix, @Nullable String group, ItemLike... ingredient) {
        ShapelessRecipeBuilder builder = ShapelessRecipeBuilder
                .shapeless(RecipeCategory.REDSTONE, output, outputCount)
                .unlockedBy("has_thing", RecipeProvider.has(unlock));

        for (ItemLike item : ingredient) {
            builder.requires(item);
        }

        if (group != null) builder.group(group);

        if (!Objects.equals(suffix, "")) {
            builder.save(recipes, getDefaultRecipeId(output) + suffix);
        } else {
            builder.save(recipes);
        }
    }

    /**
     * Generate simple recipes to convert an item into a Lever and Button variant
     * @param lever The output when combining with a Lever
     * @param button The output when combining with a Button
     * @param outputCount How many of this item this recipe grants
     */
    protected void convertingRecipes(RecipeOutput recipes, ItemLike input, ItemLike button, ItemLike lever, int outputCount, String suffix, @Nullable String group) {
        simpleShapelessRecipe(recipes, input, lever, outputCount, suffix, group, input, Items.LEVER);
        simpleShapelessRecipe(recipes, input, button, outputCount, suffix, group, input, Items.STONE_BUTTON);
    }
}
