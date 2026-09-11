package nl.teamdiopside.infinitybuttons.datagen;

import dev.architectury.platform.Platform;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import nl.teamdiopside.infinitybuttons.InfinityButtons;
import nl.teamdiopside.infinitybuttons.block.faced6.normal.CopperButton;
import nl.teamdiopside.infinitybuttons.block.faced6.normal.CopperButtonType;
import nl.teamdiopside.infinitybuttons.compat.blocks.MyNethersDelightBlocks;
import nl.teamdiopside.infinitybuttons.compat.items.MyNethersDelightItems;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static net.minecraft.data.recipes.RecipeBuilder.getDefaultRecipeId;

public class RecipeGenerator extends RecipeProvider {
    // Sub-provider instance constructor
    public RecipeGenerator(CompletableFuture<HolderLookup.Provider> registries, PackOutput output) {
        super(output, registries);
    }

    @Override
    public void buildRecipes(RecipeOutput recipes) {
        // Basic Small / Large Buttons
        for (var entry : IBBlocks.SMALL_LARGE_BUTTONS.entrySet()) { // Does NOT include copper buttons because gay
            ResourceLocation key = entry.getKey();
            if (key.getPath().equals("netherite")) continue;

            IBRegistryUtils.LargeVariantSupplier<? extends Block> value = entry.getValue();

            String id = value.getSmall().getDescriptionId();

            // I WILL use a pattern matching switch statement because it is COOL
            Function<String, String> group = infix -> switch (id) {
                case String s when s.contains("concrete_powder") -> "concrete_powder" + infix + "_buttons";
                default -> null;
            };

            boolean yearnsToBeANugget = false;
            if (key.getPath().equals("dripstone")) key.withPath("dripstone_block"); // Fuck you Mojang
            if (key.getPath().equals("prismarine_brick")) key.withPath("prismarine_bricks"); // Fuck you Lars

            if (Set.of("gold", "iron", "diamond", "emerald").contains(key.getPath())) { // Materials
                if (Set.of("gold", "iron").contains(key.getPath())) key.withPath(key.getPath() + "_ingot");

                yearnsToBeANugget = true; // All materials for consistency
            }

            Item materialItem = IBRegistryUtils.getItemByID(key.getNamespace(), key.getPath());

            if (!yearnsToBeANugget) {
                smallLargeButton(recipes, value, materialItem, "", null);
            } else {
                convertingRecipe(recipes, materialItem, value.getSmall(), false, 2, "", group.apply(""));
                largeButton(recipes, value, materialItem, "", group.apply("_large"));
            }
        }

        // Vanilla Large variants
        for (var entry : IBBlocks.DEFAULT_LARGE_BUTTONS.entrySet()) {
            Item itemByID = IBRegistryUtils.getItemByID(entry.getKey().name() + "_button");

            boolean isWood = IBRegistryUtils.isWoodType(entry.getKey().name());
            String group = isWood ? "wooden_large_buttons" : null;

            largeButton(recipes, itemByID, entry.getValue().get().asItem(), entry.getValue().get(), "", group);
        }

        // Copper Buttons
        for (var copperType : CopperButtonType.values()) {
            for (var weatherState : WeatheringCopper.WeatherState.values()) {
                String state = weatherState == WeatheringCopper.WeatherState.UNAFFECTED ? "copper" : weatherState.getSerializedName() + "_copper";
                String type = copperType == CopperButtonType.NORMAL ? state : copperType.getName() + "_" + state;

                IBRegistryUtils.LargeVariantSupplier<CopperButton> buttons = IBBlocks.COPPER_BUTTONS.get(copperType, weatherState);

                Function<String, String> group = (infix) -> (copperType == CopperButtonType.NORMAL ? copperType.getName() + "_" : "")
                        + "copper" + infix + "_buttons";

                if (copperType != CopperButtonType.STICKY) {
                    Item materialItem = IBRegistryUtils.getItemByID(type +
                            (weatherState == WeatheringCopper.WeatherState.UNAFFECTED ? "_block" : "") // WTF Mojang
                    );

                    convertingRecipe(recipes, materialItem, buttons.getSmall(), false, 2, "", group.apply(""));

                    largeButton(recipes, buttons, materialItem, "", group.apply("_large"));

                    if (copperType == CopperButtonType.WAXED) {
                        simpleShapelessRecipe(recipes, materialItem, buttons.getSmall(), 1, "_honeycomb", group.apply(""),
                                IBBlocks.COPPER_BUTTONS.get(CopperButtonType.NORMAL, weatherState).getSmall(), Items.HONEYCOMB);
                        simpleShapelessRecipe(recipes, materialItem, buttons.getLarge(), 1, "_honeycomb", group.apply("_large"),
                                IBBlocks.COPPER_BUTTONS.get(CopperButtonType.NORMAL, weatherState).getLarge(), Items.HONEYCOMB);
                    }

                } else { // copperType == CopperButtonType.STICKY
                    largeButton(recipes, buttons, Items.COPPER_BLOCK, "", group.apply("_large"));

                    simpleShapelessRecipe(recipes, Items.COPPER_BLOCK, buttons.getSmall(), 1, "_honey", group.apply(""),
                            IBBlocks.COPPER_BUTTONS.get(CopperButtonType.NORMAL, weatherState).getSmall(), Items.HONEY_BOTTLE);
                    simpleShapelessRecipe(recipes, Items.COPPER_BLOCK, buttons.getLarge(), 1, "_honey", group.apply("_large"),
                            IBBlocks.COPPER_BUTTONS.get(CopperButtonType.NORMAL, weatherState).getLarge(), Items.HONEY_BOTTLE);
                }
            }
        }

        // Secret Buttons
        for (var entry : IBBlocks.SECRET_BUTTONS.entrySet()) {
            Block originalBlock = IBRegistryUtils.getBlockByID(entry.getKey().getNamespace(), entry.getKey().getPath());
            convertingRecipe(recipes, originalBlock, entry.getValue().get(), false, 1, "", "secret_buttons");
        }

        // Nether's Delight recipes
        if (Platform.isModLoaded(MyNethersDelightBlocks.NAMESPACE)) {
            Item waxedHoglinTrophy = IBRegistryUtils.getItemByID(MyNethersDelightBlocks.NAMESPACE, "waxed_hoglin_trophy");
            convertingRecipe(recipes, waxedHoglinTrophy, MyNethersDelightBlocks.HOGLIN_TROPHY_BUTTON.get(), false, 1, "", "secret_buttons");

            Item powderyTorch = IBRegistryUtils.getItemByID(MyNethersDelightBlocks.NAMESPACE, "powdery_torch");
            convertingRecipes(recipes, powderyTorch, MyNethersDelightItems.POWDERY_TORCH_BUTTON.get(), MyNethersDelightItems.POWDERY_TORCH_LEVER.get(), 1, "", null);
        }

        // Emergency & Safety Buttons
        for (var dyeColor : DyeColor.values()) {
            Item dyeItem = IBRegistryUtils.getItemByID(dyeColor.name().toLowerCase() + "_dye");

            Item emergencyButton = IBBlocks.EMERGENCY_BUTTONS.get(dyeColor).get().asItem();
            Item safetyButton = IBBlocks.SAFE_EMERGENCY_BUTTONS.get(dyeColor).get().asItem();

            convertingRecipe(recipes, dyeItem, emergencyButton, false, 1, "", "emergency_buttons");

            TagKey<Item> glassPaneTag = TagKey.create(
                    Registries.ITEM,
                    ResourceLocation.fromNamespaceAndPath("c",  "glass_panes")
            );

            ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, safetyButton)
                    .pattern("OOO")
                    .pattern("O.O")
                    .define('O', glassPaneTag)
                    .group("safe_emergency_buttons")
                    .define('.', emergencyButton)
                    .unlockedBy("has_thing", RecipeProvider.has(dyeItem))
                    .save(recipes);
        }

        // Fancy
        for (var button : Set.of(IBBlocks.FANCY_EMERGENCY_BUTTON, IBBlocks.FANCY_SAFE_EMERGENCY_BUTTON)) {
            TagKey<Item> itemTag = button == IBBlocks.FANCY_EMERGENCY_BUTTON
                    ? ItemTagGenerator.NORMAL_EMERGENCY_BUTTONS
                    : ItemTagGenerator.NORMAL_SAFE_EMERGENCY_BUTTONS;
            String group = button == IBBlocks.FANCY_EMERGENCY_BUTTON
                    ? "emergency_buttons"
                    : "safe_emergency_buttons";

            ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, button.get())
                    .pattern("OOO")
                    .pattern("O.O")
                    .pattern("OOO")
                    .group(group)
                    .define('O', Items.GLOWSTONE_DUST)
                    .define('.', itemTag)
                    .unlockedBy("has_thing", RecipeProvider.has(itemTag))
                    .save(recipes);
        }

        // Lanterns, Torches etc.
        convertingRecipes(recipes, Items.LANTERN,        IBBlocks.LANTERN_BUTTON.get(),        IBBlocks.LANTERN_LEVER.get(),        1, "", null);
        convertingRecipes(recipes, Items.SOUL_LANTERN,   IBBlocks.SOUL_LANTERN_BUTTON.get(),   IBBlocks.SOUL_LANTERN_LEVER.get(),   1, "", null);
        convertingRecipes(recipes, Items.TORCH,          IBBlocks.TORCH_BUTTON.get(),          IBBlocks.TORCH_LEVER.get(),          1, "", null);
        convertingRecipes(recipes, Items.SOUL_TORCH,     IBBlocks.SOUL_TORCH_BUTTON.get(),     IBBlocks.SOUL_TORCH_LEVER.get(),     1, "", null);
        convertingRecipes(recipes, Items.REDSTONE_TORCH, IBBlocks.REDSTONE_TORCH_BUTTON.get(), IBBlocks.REDSTONE_TORCH_LEVER.get(), 1, "", null);

        convertingRecipes(recipes, Items.REDSTONE_LAMP, IBBlocks.LAMP_BUTTON.get(), IBBlocks.LAMP_LEVER.get(), 2, "", null);

        // Doorbells
        simpleShapelessRecipe(recipes, Items.DARK_OAK_PLANKS, IBBlocks.DOORBELL.get(), 1, "", null,
                Items.GOLD_NUGGET, IBRegistryUtils.getItemByID(InfinityButtons.MOD_ID, "dark_oak_large_button"));
        simpleShapelessRecipe(recipes, Items.DARK_OAK_PLANKS, IBBlocks.DOORBELL_BUTTON.get(), 1, "", null,
                Items.REDSTONE, Items.GOLD_NUGGET, IBRegistryUtils.getItemByID(InfinityButtons.MOD_ID, "dark_oak_large_button"));

        // Console Buttons
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, IBBlocks.SMALL_CONSOLE_BUTTON.get(), 4)
                .pattern("iri")
                .pattern("iii")
                .define('i', Items.IRON_INGOT)
                .define('r', Items.REDSTONE)
                .unlockedBy("has_thing", RecipeProvider.has(Items.IRON_INGOT))
                .save(recipes);

        simpleShapelessRecipe(recipes, IBBlocks.SMALL_CONSOLE_BUTTON.get(), IBBlocks.CONSOLE_BUTTON.get(), 1, "", null,
                IBBlocks.SMALL_CONSOLE_BUTTON.get(), Items.IRON_INGOT); // Small -> Normal
        simpleShapelessRecipe(recipes, IBBlocks.CONSOLE_BUTTON.get(), IBBlocks.LARGE_CONSOLE_BUTTON.get(), 1, "", null,
                IBBlocks.CONSOLE_BUTTON.get(), Items.IRON_INGOT); // Normal -> Large
        simpleShapelessRecipe(recipes, IBBlocks.LARGE_CONSOLE_BUTTON.get(), IBBlocks.BIG_CONSOLE_BUTTON.get(), 1, "", null,
                IBBlocks.LARGE_CONSOLE_BUTTON.get(), Items.IRON_INGOT); // Large -> Big

        convertingRecipe(recipes, IBBlocks.SMALL_CONSOLE_BUTTON.get(), IBBlocks.SMALL_CONSOLE_LEVER.get(), true, 1, "", null);
        convertingRecipe(recipes, IBBlocks.CONSOLE_BUTTON.get(),       IBBlocks.CONSOLE_LEVER.get(),       true, 1, "", null);
        convertingRecipe(recipes, IBBlocks.BIG_CONSOLE_BUTTON.get(),   IBBlocks.BIG_CONSOLE_LEVER.get(),   true, 1, "", null);
        convertingRecipe(recipes, IBBlocks.LARGE_CONSOLE_BUTTON.get(), IBBlocks.LARGE_CONSOLE_LEVER.get(), true, 1, "", null);

        // Letter Buttons
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, IBBlocks.LETTER_BUTTON.get())
                .pattern("i.i")
                .pattern(" i ")
                .define('i', Items.IRON_INGOT)
                .define('.', IBRegistryUtils.getItemByID(InfinityButtons.MOD_ID, "spruce_large_button"))
                .unlockedBy("has_thing", RecipeProvider.has(Items.IRON_INGOT))
                .save(recipes);

        convertingRecipe(recipes, IBBlocks.LETTER_BUTTON.get(), IBBlocks.LETTER_LEVER.get(), true, 1, "", null);
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
