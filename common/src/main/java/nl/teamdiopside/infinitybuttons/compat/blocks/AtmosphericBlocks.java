package nl.teamdiopside.infinitybuttons.compat.blocks;

import dev.architectury.registry.registries.RegistrySupplier;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButton;
import nl.teamdiopside.infinitybuttons.block.faced6.normal.OneUseButton;
import nl.teamdiopside.infinitybuttons.compat.IBModdedBlocks;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;

import static com.mojang.text2speech.Narrator.LOGGER;

public class AtmosphericBlocks extends IBModdedBlocks {
    public static final AtmosphericBlocks INSTANCE = new AtmosphericBlocks();
    public static final String NAMESPACE = "atmospheric";

    public static final IBRegistryUtils.LargeVariantSupplier<OneUseButton> SAND_BUTTON = registerOneUseButton("sand");
    public static final IBRegistryUtils.LargeVariantSupplier<OneUseButton> RED_SAND_BUTTON = registerOneUseButton("red_sand");

    public static final RegistrySupplier<SecretButton> ROSEWOOD_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("rosewood");
    public static final RegistrySupplier<SecretButton> MORADO_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("morado");
    public static final RegistrySupplier<SecretButton> YUCCA_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("yucca");
    public static final RegistrySupplier<SecretButton> LAUREL_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("laurel");
    public static final RegistrySupplier<SecretButton> ASPEN_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("aspen");
    public static final RegistrySupplier<SecretButton> KOUSA_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("kousa");
    public static final RegistrySupplier<SecretButton> GRIMWOOD_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("grimwood");

    private AtmosphericBlocks() {
        super(NAMESPACE);
    }

    @Override
    public void registerMine() {
        LOGGER.info("Infinity Buttons: Registering Atmospheric buttons");
    }
}
