package nl.teamdiopside.infinitybuttons.datagen.modelstate;

import net.minecraft.data.models.blockstates.BlockStateGenerator;

import java.util.function.Consumer;

public interface StateGenerator {
    void generate(Consumer<BlockStateGenerator> consumer);
}
