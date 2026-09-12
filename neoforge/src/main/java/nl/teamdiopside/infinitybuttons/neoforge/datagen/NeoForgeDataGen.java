package nl.teamdiopside.infinitybuttons.neoforge.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import nl.teamdiopside.infinitybuttons.datagen.BlockTagGenerator;
import nl.teamdiopside.infinitybuttons.datagen.ItemTagGenerator;
import nl.teamdiopside.infinitybuttons.datagen.LootTableGenerator;
import nl.teamdiopside.infinitybuttons.datagen.RecipeGenerator;
import nl.teamdiopside.infinitybuttons.datagen.modelstate.ModelAndStateProvider;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NeoForgeDataGen {

    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();

        if (event.includeServer()) {
            BlockTagGenerator blockTags = new BlockTagGenerator(output, registries);
            generator.addProvider(true, blockTags);
            generator.addProvider(true, new ItemTagGenerator(output, registries, blockTags.contentsGetter()));

            generator.addProvider(true, new RecipeGenerator(registries, output));

            generator.addProvider(true, new LootTableProvider(
                    output,
                    Collections.emptySet(),
                    List.of(new LootTableProvider.SubProviderEntry(LootTableGenerator::new, LootContextParamSets.BLOCK)),
                    registries
            ));
        }

        if (event.includeClient()) {
            generator.addProvider(true, new ModelAndStateProvider(output));
        }
    }
}