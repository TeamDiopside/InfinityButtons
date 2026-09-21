package nl.teamdiopside.infinitybuttons.datagen.recipe;

import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import nl.teamdiopside.infinitybuttons.block.faced6.normal.NormalButton;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import static net.minecraft.data.recipes.RecipeBuilder.getDefaultRecipeId;

public class SmallLargeGenerator extends RecipeGenerator<NormalButton> {
    protected SmallLargeGenerator(IBRecipeOutput output) {
        super(output);
    }

    /**
     * Generate a small and large button recipe: 1 material -> 4 small, 2 small -> 1 big
     * @param material The material item that unlocks both recipes and creates the small button
     */
    protected void smallLargeButton(RecipeOutput recipes, IBRegistryUtils.LargeVariantSupplier<? extends Block> button, ItemLike material, String suffix, @Nullable Function<String, String> group) {
        var builder = ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, button.getSmall(), 4)
                .requires(material)
                .unlockedBy("has_thing", RecipeProvider.has(material));

        String largeGroup = null;
        if (group != null) {
            builder.group(group.apply(""));
            largeGroup = group.apply("_large");
        }

        if (!Objects.equals(suffix, "")) {
            builder.save(recipes, getDefaultRecipeId(button.getSmall()) + suffix);
        } else {
            builder.save(recipes);
        }
        largeButton(recipes, button.getSmall(), button.getLarge(), material, suffix, largeGroup);
    }

    /**
     * Generate a large button recipe: 2 small -> 1 big
     * @param material The material item that unlocks the recipe
     */
    protected void largeButton(RecipeOutput recipes, IBRegistryUtils.LargeVariantSupplier<? extends Block> button, ItemLike material, String suffix, @Nullable String group) {
        largeButton(recipes, button.getSmall(), button.getLarge(), material, suffix, group);
    }

    /**
     * Generate a large button recipe: 2 small -> 1 big
     * @param material The material item that unlocks the recipe
     */
    protected void largeButton(RecipeOutput recipes, ItemLike small, ItemLike large, ItemLike material, String suffix, @Nullable String group) {
        var builder = ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, large)
                .requires(small, 2)
                .unlockedBy("has_thing", RecipeProvider.has(material));

        if (group != null) builder.group(group);

        if (!Objects.equals(suffix, "")) {
            builder.save(recipes, getDefaultRecipeId(large) + suffix);
        } else {
            builder.save(recipes);
        }
    }

    @Override
    public void generate(NormalButton block) {
        ResourceLocation material = entry.getKey();
        if (material.getPath().equals("netherite")) return;

        IBRegistryUtils.LargeVariantSupplier<? extends Block> value = entry.getValue();

        String id = value.getSmall().getDescriptionId();

        // I WILL use a pattern matching switch statement because it is COOL
        Function<String, String> group = infix -> switch (id) {
            case String s when s.contains("concrete_powder") -> "concrete_powder" + infix + "_buttons";
            default -> null;
        };

        boolean yearnsToBeANugget = false;
        if (material.getPath().equals("dripstone")) material.withPath("dripstone_block"); // Fuck you Mojang
        if (material.getPath().equals("prismarine_brick")) material.withPath("prismarine_bricks"); // Fuck you Lars

        if (Set.of("gold", "iron", "diamond", "emerald").contains(material.getPath())) { // Materials
            if (Set.of("gold", "iron").contains(material.getPath())) material.withPath(material.getPath() + "_ingot");

            yearnsToBeANugget = true; // All materials for consistency
        }

        Item materialItem = IBRegistryUtils.getItemByID(material.getNamespace(), material.getPath());

        if (!yearnsToBeANugget) {
            smallLargeButton(output, value, materialItem, "", null);
        } else {
            convertingRecipe(output, materialItem, value.getSmall(), false, 2, "", group.apply(""));
            largeButton(output, value, materialItem, "", group.apply("_large"));
        }
    }
}
