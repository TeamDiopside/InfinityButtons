package nl.teamdiopside.infinitybuttons.datagen;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;

import java.util.Collections;
import java.util.function.BiConsumer;

public class LootTableGenerator extends BlockLootSubProvider {

    public LootTableGenerator(HolderLookup.Provider registries) {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    public void generate() {
        for (RegistrySupplier<? extends Block> blockSupplier : IBBlocks.ALL_BUTTONS.values()) {
            Block block = blockSupplier.get();
            this.dropSelf(block);
        }
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> biConsumer) {
        // Just don't
    }
}