package nl.teamdiopside.infinitybuttons.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import nl.teamdiopside.infinitybuttons.datagen.BlockTagGenerator;
import nl.teamdiopside.infinitybuttons.datagen.ItemTagGenerator;
import nl.teamdiopside.infinitybuttons.datagen.LootTableGenerator;
import nl.teamdiopside.infinitybuttons.datagen.RecipeGenerator;
import nl.teamdiopside.infinitybuttons.datagen.modelstate.ModelAndStateProvider;

import java.util.Collections;
import java.util.List;

public class FabricDataGen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        BlockTagGenerator blockTags = pack.addProvider(BlockTagGenerator::new);
        pack.addProvider((output, registries) -> new ItemTagGenerator(output, registries, blockTags.contentsGetter()));

        pack.addProvider((output, registries) -> new RecipeGenerator(registries, output));

        pack.addProvider((output, registries) -> new LootTableProvider(
                output,
                Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(LootTableGenerator::new, LootContextParamSets.BLOCK)),
                registries
        ));

        pack.addProvider((output, registries) -> new ModelAndStateProvider(output));
    }
}