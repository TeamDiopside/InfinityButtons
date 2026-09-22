package nl.teamdiopside.infinitybuttons.datagen.recipe;

import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import nl.teamdiopside.infinitybuttons.InfinityButtons;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;

public class SpecialButtonsGenerator extends RecipeGenerator {
    @Override
    public void generate(IBRecipeOutput output) {
        // Lanterns, Torches etc.
        convertingRecipes(output, Items.LANTERN,        IBBlocks.LANTERN_BUTTON.get(),        IBBlocks.LANTERN_LEVER.get(),        1, "", null);
        convertingRecipes(output, Items.SOUL_LANTERN,   IBBlocks.SOUL_LANTERN_BUTTON.get(),   IBBlocks.SOUL_LANTERN_LEVER.get(),   1, "", null);
        convertingRecipes(output, Items.TORCH,          IBBlocks.TORCH_BUTTON.get(),          IBBlocks.TORCH_LEVER.get(),          1, "", null);
        convertingRecipes(output, Items.SOUL_TORCH,     IBBlocks.SOUL_TORCH_BUTTON.get(),     IBBlocks.SOUL_TORCH_LEVER.get(),     1, "", null);
        convertingRecipes(output, Items.REDSTONE_TORCH, IBBlocks.REDSTONE_TORCH_BUTTON.get(), IBBlocks.REDSTONE_TORCH_LEVER.get(), 1, "", null);

        convertingRecipes(output, Items.REDSTONE_LAMP, IBBlocks.LAMP_BUTTON.get(), IBBlocks.LAMP_LEVER.get(), 2, "", null);

        // Doorbells
        simpleShapelessRecipe(output, Items.DARK_OAK_PLANKS, IBBlocks.DOORBELL.get(), 1, "", null,
                Items.GOLD_NUGGET, IBRegistryUtils.getItemByID(InfinityButtons.MOD_ID, "dark_oak_large_button"));
        simpleShapelessRecipe(output, Items.DARK_OAK_PLANKS, IBBlocks.DOORBELL_BUTTON.get(), 1, "", null,
                Items.REDSTONE, Items.GOLD_NUGGET, IBRegistryUtils.getItemByID(InfinityButtons.MOD_ID, "dark_oak_large_button"));

        // Console Buttons
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, IBBlocks.SMALL_CONSOLE_BUTTON.get(), 4)
                .pattern("iri")
                .pattern("iii")
                .define('i', Items.IRON_INGOT)
                .define('r', Items.REDSTONE)
                .unlockedBy("has_thing", RecipeProvider.has(Items.IRON_INGOT))
                .save(output);

        simpleShapelessRecipe(output, IBBlocks.SMALL_CONSOLE_BUTTON.get(), IBBlocks.CONSOLE_BUTTON.get(), 1, "", null,
                IBBlocks.SMALL_CONSOLE_BUTTON.get(), Items.IRON_INGOT); // Small -> Normal
        simpleShapelessRecipe(output, IBBlocks.CONSOLE_BUTTON.get(), IBBlocks.LARGE_CONSOLE_BUTTON.get(), 1, "", null,
                IBBlocks.CONSOLE_BUTTON.get(), Items.IRON_INGOT); // Normal -> Large
        simpleShapelessRecipe(output, IBBlocks.LARGE_CONSOLE_BUTTON.get(), IBBlocks.BIG_CONSOLE_BUTTON.get(), 1, "", null,
                IBBlocks.LARGE_CONSOLE_BUTTON.get(), Items.IRON_INGOT); // Large -> Big

        // Letter Buttons
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, IBBlocks.LETTER_BUTTON.get())
                .pattern("i.i")
                .pattern(" i ")
                .define('i', Items.IRON_INGOT)
                .define('.', IBRegistryUtils.getItemByID(InfinityButtons.MOD_ID, "spruce_large_button"))
                .unlockedBy("has_thing", RecipeProvider.has(Items.IRON_INGOT))
                .save(output);

        convertingRecipe(output, IBBlocks.LETTER_BUTTON.get(), IBBlocks.LETTER_LEVER.get(), true, 1, "", null);
    }
}
