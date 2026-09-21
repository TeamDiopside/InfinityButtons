package nl.teamdiopside.infinitybuttons.datagen.recipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import nl.teamdiopside.infinitybuttons.block.faced6.normal.NormalButton;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static net.minecraft.data.recipes.RecipeBuilder.getDefaultRecipeId;

public class SmallLargeGenerator extends RecipeGenerator<Map.Entry<ResourceLocation, IBRegistryUtils.LargeVariantSupplier<? extends Block>>> {
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
    protected void largeButton(RecipeOutput recipes, IBRegistryUtils.LargeVariantSupplier<? extends Block> button, TagKey<Item> material, String suffix, @Nullable String group) {
        largeButton(recipes, button.getSmall(), button.getLarge(), material, suffix, group);
    }

    /**
     * Generate a large button recipe: 2 small -> 1 big
     * @param material The material item that unlocks the recipe
     */
    protected void largeButton(RecipeOutput recipes, ItemLike small, ItemLike large, ItemLike material, String suffix, @Nullable String group) {
        largeButton(recipes, small ,large, RecipeProvider.has(material), suffix, group);
    }

    /**
     * Generate a large button recipe: 2 small -> 1 big
     * @param material The material item that unlocks the recipe
     */
    protected void largeButton(RecipeOutput recipes, ItemLike small, ItemLike large, TagKey<Item> material, String suffix, @Nullable String group) {
        largeButton(recipes, small ,large, RecipeProvider.has(material), suffix, group);
    }

    /**
     * Generate a large button recipe: 2 small -> 1 big
     * @param criterion The criterion that unlocks the recipe
     */
    private void largeButton(RecipeOutput recipes, ItemLike small, ItemLike large, Criterion<?> criterion, String suffix, @Nullable String group) {
        var builder = ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, large)
                .requires(small, 2)
                .unlockedBy("has_thing", criterion);

        if (group != null) builder.group(group);

        if (!Objects.equals(suffix, "")) {
            builder.save(recipes, getDefaultRecipeId(large) + suffix);
        } else {
            builder.save(recipes);
        }
    }

    private @Nullable TagKey<Item> getInputTag(ResourceLocation textureMaterial) {
        Function<String, TagKey<Item>> gemKey = s -> TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gems/" + s));
        Function<String, TagKey<Item>> ingotKey = s -> TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/" + s));
        return switch (textureMaterial.getPath()) {
            case "gold_block" -> ingotKey.apply("gold");
            case "iron_block" -> ingotKey.apply("iron");
            case "diamond_block" -> gemKey.apply("diamond");
            case "emerald_block" -> gemKey.apply("emerald");
            default -> null;
        };
    }

    @Override
    public void generate(Map.Entry<ResourceLocation, IBRegistryUtils.LargeVariantSupplier<? extends Block>> entry) {
        ResourceLocation textureMaterial = entry.getKey();
        if (textureMaterial.getPath().equals("netherite")) return;
        IBRegistryUtils.LargeVariantSupplier<? extends Block> supplier = entry.getValue();
        String id = supplier.getSmall().getDescriptionId();

        Function<String, String> group = infix ->
                id.contains("concrete_powder") ? "concrete_powder" + infix + "_buttons" : null;

        if (textureMaterial.getPath().equals("dripstone")) textureMaterial.withPath("dripstone_block"); // Fuck you Mojang
        if (textureMaterial.getPath().equals("prismarine_brick")) textureMaterial.withPath("prismarine_bricks"); // Fuck you Lars
        TagKey<Item> materialTag = getInputTag(textureMaterial);

        Item materialItem = IBRegistryUtils.getItemByID(textureMaterial.getNamespace(), textureMaterial.getPath());

        if (materialTag == null) {
            // Create small button from just the material
            smallLargeButton(output, supplier, materialItem, "", null);
        } else {
            // Create small button from material and stone button
            convertingRecipe(output, materialTag, supplier.getSmall(), false, 2, "", group.apply(""));
            largeButton(output, supplier, materialTag, "", group.apply("_large"));
        }
    }

    public void generateVanillaLargeVariant(BlockSetType vanillaType, NormalButton large) {
        Item itemByID = IBRegistryUtils.getItemByID("minecraft", vanillaType.name() + "_button");

        boolean isWood = IBRegistryUtils.isWoodType(vanillaType.name());
        String group = isWood ? "wooden_large_buttons" : null;

        largeButton(output, itemByID, large.asItem(), large, "", group);
    }
}
