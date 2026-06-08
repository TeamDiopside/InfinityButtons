package nl.teamdiopside.infinitybuttons.compat.blocks;

import dev.architectury.registry.registries.RegistrySupplier;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButton;
import nl.teamdiopside.infinitybuttons.compat.IBModdedBlocks;

import static com.mojang.text2speech.Narrator.LOGGER;

public class EnvironmentalBlocks extends IBModdedBlocks {
    public static final EnvironmentalBlocks INSTANCE = new EnvironmentalBlocks();
    public static final String NAMESPACE = "environmental";

    public static final RegistrySupplier<SecretButton> WILLOW_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("willow");
    public static final RegistrySupplier<SecretButton> PINE_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("pine");
    public static final RegistrySupplier<SecretButton> PLUM_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("plum");
    public static final RegistrySupplier<SecretButton> WISTERIA_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("wisteria");

    private EnvironmentalBlocks() {
        super(NAMESPACE);
    }

    @Override
    public void registerMine() {
        LOGGER.info("Infinity Buttons: Registering Environmental buttons");
    }
}
