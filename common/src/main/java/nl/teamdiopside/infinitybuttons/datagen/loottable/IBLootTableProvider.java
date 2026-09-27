package nl.teamdiopside.infinitybuttons.datagen.loottable;

// Modified version of net.minecraft.data.loot.LootTableProvider to work with conditions

import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.Lifecycle;
import dev.architectury.registry.registries.RegistrySupplier;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.RandomSequence;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.RandomSupport;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import nl.teamdiopside.infinitybuttons.InfinityButtons;
import nl.teamdiopside.infinitybuttons.compat.IBModdedBlocks;
import nl.teamdiopside.infinitybuttons.datagen.conditions.DataCondition;
import nl.teamdiopside.infinitybuttons.datagen.conditions.ModLoaded;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class IBLootTableProvider extends LootTableProvider {
    private final PackOutput.PathProvider pathProvider;
    private final Set<ResourceKey<LootTable>> requiredTables;
    private final List<LootTableProvider.SubProviderEntry> subProviders;
    private final CompletableFuture<HolderLookup.Provider> registries;

    public IBLootTableProvider(PackOutput arg, Set<ResourceKey<LootTable>> set, List<LootTableProvider.SubProviderEntry> list, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(arg, set, list, completableFuture);
        this.pathProvider = arg.createRegistryElementsPathProvider(Registries.LOOT_TABLE);
        this.subProviders = list;
        this.requiredTables = set;
        this.registries = completableFuture;
    }

    @Override
    public @NotNull CompletableFuture<?> run(CachedOutput arg) {
        return this.registries.thenCompose(arg2 -> this.runWithConditions(arg, arg2));
    }

    private CompletableFuture<?> runWithConditions(CachedOutput cachedOutput, HolderLookup.Provider provider) {
        //
        WritableRegistry<LootTable> writableRegistry = new MappedRegistry<>(Registries.LOOT_TABLE, Lifecycle.experimental());
        Map<RandomSupport.Seed128bit, ResourceLocation> map = new Object2ObjectOpenHashMap<>();
        this.subProviders.forEach((subProviderEntry) -> subProviderEntry.provider().apply(provider).generate((resourceKey, builder) -> {
            ResourceLocation resourceLocation = resourceKey.location();
            ResourceLocation resourceLocation2 = map.put(RandomSequence.seedForKey(resourceLocation), resourceLocation);
            if (resourceLocation2 != null) {
                Util.logAndPauseIfInIde("Loot table random sequence seed collision on " + resourceLocation2 + " and " + resourceKey.location());
            }

            builder.setRandomSequence(resourceLocation);
            LootTable lootTable = builder.setParamSet(subProviderEntry.paramSet()).build();
            writableRegistry.register(resourceKey, lootTable, RegistrationInfo.BUILT_IN);
        }));
        writableRegistry.freeze();
        ProblemReporter.Collector collector = new ProblemReporter.Collector();
        HolderGetter.Provider provider2 = (new RegistryAccess.ImmutableRegistryAccess(List.of(writableRegistry))).freeze().asGetterLookup();
        ValidationContext validationContext = new ValidationContext(collector, LootContextParamSets.ALL_PARAMS, provider2);

        for(ResourceKey<LootTable> resourceKey : Sets.difference(this.requiredTables, writableRegistry.registryKeySet())) {
            assert resourceKey != null;
            collector.report("Missing built-in table: " + resourceKey.location());
        }

        writableRegistry.holders().forEach((reference) -> reference.value().validate(validationContext.setParams(reference.value().getParamSet()).enterElement("{" + reference.key().location() + "}", reference.key())));
        Multimap<String, String> multimap = collector.get();
        if (!multimap.isEmpty()) {
            multimap.forEach((string, string2) -> InfinityButtons.LOGGER.warning("Found validation problem in " + string + ": " + string2));
            throw new IllegalStateException("Failed to validate loot tables, see logs");
        } else {
            return CompletableFuture.allOf(writableRegistry.entrySet().stream().map((entry) -> {
                ResourceKey<LootTable> resourceKey = entry.getKey();
                LootTable lootTable = entry.getValue();
                Path path = this.pathProvider.json(resourceKey.location());
                return saveWithConditions(cachedOutput, provider, lootTable, path, resourceKey);
            }).toArray(CompletableFuture[]::new));
        }
    }

    private void addModLoadedConditions(ResourceKey<LootTable> key, JsonElement lootTableJson) {
        ResourceLocation keyLocation = key.location();
        if (!keyLocation.getPath().startsWith("blocks/")) return;
        String blockId = keyLocation.getPath().substring("blocks/".length());
        if (!IBBlocks.ALL_BUTTONS.containsKey(blockId)) return;
        RegistrySupplier<? extends Block> block = IBBlocks.ALL_BUTTONS.get(blockId);
        if (!IBModdedBlocks.MODDED_BUTTONS.containsKey(block)) return;
        DataCondition condition = new ModLoaded(IBModdedBlocks.MODDED_BUTTONS.get(block));

        JsonArray fabricConditions = new JsonArray();
        JsonArray neoForgeConditions = new JsonArray();
        fabricConditions.add(condition.fabric());
        neoForgeConditions.add(condition.neoForge());

        lootTableJson.getAsJsonObject().add("fabric:load_conditions", fabricConditions);
        lootTableJson.getAsJsonObject().add("neoforge:conditions", neoForgeConditions);
    }

    private CompletableFuture<?> saveWithConditions(CachedOutput cachedOutput, HolderLookup.Provider provider, LootTable table, Path path, ResourceKey<LootTable> key) {
        RegistryOps<JsonElement> registryOps = provider.createSerializationContext(JsonOps.INSTANCE);
        JsonElement lootTableJson = LootTable.DIRECT_CODEC.encodeStart(registryOps, table).getOrThrow();

        addModLoadedConditions(key, lootTableJson);
        return DataProvider.saveStable(cachedOutput, lootTableJson, path);
    }
}
