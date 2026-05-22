package nl.teamdiopside.infinitybuttons.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(ModelAndStateGenerator::new);
        pack.addProvider(ItemTagGenerator::new);
        pack.addProvider(RecipeGenerator::new);
    }

}