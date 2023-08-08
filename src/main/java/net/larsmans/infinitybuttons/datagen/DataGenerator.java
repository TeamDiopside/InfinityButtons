package net.larsmans.infinitybuttons.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(LootTableGen::new);
        pack.addProvider(TagItemGen::new);
        pack.addProvider(TagBlockGen::new);
        pack.addProvider(RecipeGen::new);
    }
}
