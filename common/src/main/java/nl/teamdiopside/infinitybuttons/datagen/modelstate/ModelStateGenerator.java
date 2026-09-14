package nl.teamdiopside.infinitybuttons.datagen.modelstate;

import com.google.gson.JsonElement;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface ModelStateGenerator {
    void generateState(Consumer<BlockStateGenerator> consumer);
    void generateBlockModels(BiConsumer<ResourceLocation, Supplier<JsonElement>> consumer);
    void generateItemModels(BiConsumer<ResourceLocation, Supplier<JsonElement>> consumer);
}
