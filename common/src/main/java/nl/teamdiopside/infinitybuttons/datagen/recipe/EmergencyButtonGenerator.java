package nl.teamdiopside.infinitybuttons.datagen.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import nl.teamdiopside.infinitybuttons.block.emergency.EmergencyButton;
import nl.teamdiopside.infinitybuttons.block.emergency.SafeEmergencyButton;
import nl.teamdiopside.infinitybuttons.datagen.ItemTagGenerator;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;

import java.util.Set;

public class EmergencyButtonGenerator extends RecipeGenerator {

    public void generateNormalButtons(IBRecipeOutput output) {
        for (var dyeColor : DyeColor.values()) {
            Item dyeItem = IBRegistryUtils.getItemByID("minecraft", dyeColor.name().toLowerCase() + "_dye");
            EmergencyButton emergencyButton = IBBlocks.EMERGENCY_BUTTONS.get(dyeColor).get();
            SafeEmergencyButton safetyButton = IBBlocks.SAFE_EMERGENCY_BUTTONS.get(dyeColor).get();
            convertingRecipe(output, dyeItem, emergencyButton, false, 1, "", "emergency_buttons");

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
                    .save(output);
        }
    }

    public void generateFancyButtons(IBRecipeOutput output) {
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
                    .save(output);
        }
    }

    @Override
    public void generate(IBRecipeOutput output) {
        generateNormalButtons(output);
        generateFancyButtons(output);
    }
}
