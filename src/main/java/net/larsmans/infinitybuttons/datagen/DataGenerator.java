package net.larsmans.infinitybuttons.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(LootTableGen::new);
        fabricDataGenerator.addProvider(TagItemGen::new);
        fabricDataGenerator.addProvider(TagBlockGen::new);
        fabricDataGenerator.addProvider(RecipeGen::new);
    }
}
