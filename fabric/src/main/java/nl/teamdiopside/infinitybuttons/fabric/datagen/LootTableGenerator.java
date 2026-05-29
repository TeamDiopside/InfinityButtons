package nl.teamdiopside.infinitybuttons.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;

import java.util.concurrent.CompletableFuture;

public class LootTableGenerator extends FabricBlockLootTableProvider {

    protected LootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        for (Block block : IBBlocks.ALL_BUTTONS.values()) {
//            if (block.defaultBlockState().is(BlockTagGenerator.MINEABLE_SHOVEL)) continue;
            dropSelf(block);
        }
    }
}
