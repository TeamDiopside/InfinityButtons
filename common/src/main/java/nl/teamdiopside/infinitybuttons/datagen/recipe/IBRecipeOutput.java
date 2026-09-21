package nl.teamdiopside.infinitybuttons.datagen.recipe;

import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import nl.teamdiopside.infinitybuttons.datagen.conditions.DataCondition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class IBRecipeOutput implements RecipeOutput {

    private final Set<ResourceLocation> set;
    private final Map<ResourceLocation, HashSet<DataCondition>> conditions;
    private final List<CompletableFuture<?>> futures;

    private final PackOutput.PathProvider recipePathProvider;
    private final PackOutput.PathProvider advancementPathProvider;

    private final CachedOutput cachedOutput;
    private final HolderLookup.Provider provider;

    public IBRecipeOutput(final Map<ResourceLocation, HashSet<DataCondition>> conditions, final List<CompletableFuture<?>> futures, final PackOutput.PathProvider recipePathProvider, final PackOutput.PathProvider advancementPathProvider, final CachedOutput cachedOutput, final HolderLookup.Provider provider) {
        this.set = new HashSet<>();
        this.conditions = conditions;
        this.futures = futures;
        this.recipePathProvider = recipePathProvider;
        this.advancementPathProvider = advancementPathProvider;
        this.cachedOutput = cachedOutput;
        this.provider = provider;
    }

    public void addConditions(ResourceLocation location, DataCondition... conditions) {
        if (set.contains(location)) throw new IllegalStateException("Can't add new conditions for already generated recipes!");
        if (this.conditions.containsKey(location)) {
            // Add extra conditions
            this.conditions.get(location).addAll(Set.of(conditions));
            return;
        }
        this.conditions.put(location, Sets.newHashSet(conditions));
    }

    @Override
    public void accept(ResourceLocation resourceLocation, Recipe<?> recipe, @Nullable AdvancementHolder advancementHolder) {
        if (!set.add(resourceLocation)) {
            throw new IllegalStateException("Duplicate recipe " + resourceLocation);
        }
        RegistryOps<JsonElement> registryOps = provider.createSerializationContext(JsonOps.INSTANCE);

        JsonObject recipeJson = Recipe.CODEC.encodeStart(registryOps, recipe).getOrThrow(IllegalStateException::new).getAsJsonObject();
        JsonObject advancementJson = null;
        if (advancementHolder != null) {
            advancementJson = Advancement.CODEC.encodeStart(registryOps, advancementHolder.value()).getOrThrow(IllegalStateException::new).getAsJsonObject();
        }

        JsonArray fabricConditions = new JsonArray();
        JsonArray neoForgeConditions = new JsonArray();
        Set<DataCondition> conditions = this.conditions.get(resourceLocation);
        if (conditions != null) {
            for (DataCondition condition : conditions) {
                fabricConditions.add(condition.fabric());
                neoForgeConditions.add(condition.neoForge());
            }

            recipeJson.add("fabric:load_conditions", fabricConditions);
            recipeJson.add("neoforge:conditions", neoForgeConditions);
            if (advancementHolder != null && advancementJson != null) {
                advancementJson.add("fabric:load_conditions", fabricConditions);
                advancementJson.add("neoforge:conditions", neoForgeConditions);
            }
        }
        futures.add(DataProvider.saveStable(cachedOutput, recipeJson, this.recipePathProvider.json(resourceLocation)));
        if (advancementHolder == null) return;
        futures.add(DataProvider.saveStable(cachedOutput, advancementJson, this.advancementPathProvider.json(advancementHolder.id())));
    }

    @Override
    public @NotNull Advancement.Builder advancement() {
        return Advancement.Builder.recipeAdvancement().parent(new AdvancementHolder(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT, null));
    }
}
