package nl.teamdiopside.infinitybuttons.datagen.recipe;

import dev.architectury.platform.Platform;
import net.minecraft.world.item.Item;
import nl.teamdiopside.infinitybuttons.compat.blocks.MyNethersDelightBlocks;
import nl.teamdiopside.infinitybuttons.compat.items.MyNethersDelightItems;
import nl.teamdiopside.infinitybuttons.datagen.conditions.ModLoaded;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;

public class NethersDelightGenerator extends RecipeGenerator {

    @Override
    public void generate(IBRecipeOutput output) {
        if (!Platform.isModLoaded(MyNethersDelightBlocks.NAMESPACE)) throw new IllegalStateException("Nether's Delight not loaded!");

        addConditions(output, MyNethersDelightBlocks.HOGLIN_TROPHY_BUTTON.get(), new ModLoaded(MyNethersDelightBlocks.NAMESPACE));
        addConditions(output, MyNethersDelightItems.POWDERY_TORCH_BUTTON.get(), new ModLoaded(MyNethersDelightBlocks.NAMESPACE));

        Item waxedHoglinTrophy = IBRegistryUtils.getItemByID(MyNethersDelightBlocks.NAMESPACE, "waxed_hoglin_trophy");
        convertingRecipe(output, waxedHoglinTrophy, MyNethersDelightBlocks.HOGLIN_TROPHY_BUTTON.get(), false, 1, "", "secret_buttons");
        Item powderyTorch = IBRegistryUtils.getItemByID(MyNethersDelightBlocks.NAMESPACE, "powdery_torch");
        convertingRecipes(output, powderyTorch, MyNethersDelightItems.POWDERY_TORCH_BUTTON.get(), MyNethersDelightItems.POWDERY_TORCH_LEVER.get(), 1, "", null);
    }
}
