package nl.teamdiopside.infinitybuttons.datagen.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class IBRecipeProvider extends RecipeProvider {
    final PackOutput.PathProvider recipePathProvider;
    final PackOutput.PathProvider advancementPathProvider;

    // Sub-provider instance constructor
    public IBRecipeProvider(CompletableFuture<HolderLookup.Provider> registries, PackOutput output) {
        super(output, registries);
        this.recipePathProvider = output.createRegistryElementsPathProvider(Registries.RECIPE);
        this.advancementPathProvider = output.createRegistryElementsPathProvider(Registries.ADVANCEMENT);
    }

    @Override
    public @NotNull CompletableFuture<?> run(CachedOutput cachedOutput, HolderLookup.Provider provider) {
        final List<CompletableFuture<?>> futures = new ArrayList<>();
        this.buildRecipes(new IBRecipeOutput(futures, this.recipePathProvider, this.advancementPathProvider, cachedOutput, provider));
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public void buildRecipes(RecipeOutput recipes) {
        if (!(recipes instanceof IBRecipeOutput ibRecipeOutput)) throw new RuntimeException("Wrong instance of RecipeOutput detected!");
        List<RecipeGenerator> generators = List.of(
                new SmallLargeGenerator(),
                new CopperButtonGenerator(),
                new SecretButtonGenerator(),
                new NethersDelightGenerator(),
                new EmergencyButtonGenerator(),
                new SpecialButtonsGenerator()
        );

        for (var generator : generators) {
            generator.generate(ibRecipeOutput);
        }
    }
}
