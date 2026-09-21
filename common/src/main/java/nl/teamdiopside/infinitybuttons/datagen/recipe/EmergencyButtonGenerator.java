package nl.teamdiopside.infinitybuttons.datagen.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import nl.teamdiopside.infinitybuttons.block.emergency.EmergencyButton;
import nl.teamdiopside.infinitybuttons.block.emergency.SafeEmergencyButton;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;

public class EmergencyButtonGenerator extends RecipeGenerator {
    protected EmergencyButtonGenerator(IBRecipeOutput output) {
        super(output);
    }

    public void generate(DyeColor dyeColor) {
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
