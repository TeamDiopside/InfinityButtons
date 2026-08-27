package nl.teamdiopside.infinitybuttons.compat.blocks;

import dev.architectury.registry.registries.RegistrySupplier;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButton;
import nl.teamdiopside.infinitybuttons.compat.IBModdedBlocks;

import static com.mojang.text2speech.Narrator.LOGGER;

public class AutumnityBlocks extends IBModdedBlocks {
    public static final AutumnityBlocks INSTANCE = new AutumnityBlocks();
    public static final String NAMESPACE = "autumnity";

    public static final RegistrySupplier<SecretButton> MAPLE_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("maple");

    private AutumnityBlocks() {
        super(NAMESPACE);
    }

    @Override
    public void registerMine() {
        LOGGER.info("Infinity Buttons: Registering Autumnity buttons");
    }
}
