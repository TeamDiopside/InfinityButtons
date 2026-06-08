package nl.teamdiopside.infinitybuttons.compat.blocks;

import dev.architectury.registry.registries.RegistrySupplier;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButton;
import nl.teamdiopside.infinitybuttons.compat.IBModdedBlocks;

import static com.mojang.text2speech.Narrator.LOGGER;

public class WoodworksBlocks extends IBModdedBlocks {
    public static final WoodworksBlocks INSTANCE = new WoodworksBlocks();
    public static final String NAMESPACE = "woodworks";

    public static final RegistrySupplier<SecretButton> SPRUCE_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("spruce");
    public static final RegistrySupplier<SecretButton> BIRCH_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("birch");
    public static final RegistrySupplier<SecretButton> JUNGLE_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("jungle");
    public static final RegistrySupplier<SecretButton> ACACIA_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("acacia");
    public static final RegistrySupplier<SecretButton> DARK_OAK_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("dark_oak");
    public static final RegistrySupplier<SecretButton> MANGROVE_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("mangrove");
    public static final RegistrySupplier<SecretButton> CHERRY_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("cherry");
    public static final RegistrySupplier<SecretButton> BAMBOO_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("bamboo");
    public static final RegistrySupplier<SecretButton> CRIMSON_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("crimson");
    public static final RegistrySupplier<SecretButton> WARPED_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("warped");

    private WoodworksBlocks() {
        super(NAMESPACE);
    }

    @Override
    public void registerMine() {
        LOGGER.info("Infinity Buttons: Registering Woodworks buttons");
    }
}
