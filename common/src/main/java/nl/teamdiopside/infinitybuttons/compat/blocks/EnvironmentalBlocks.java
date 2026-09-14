package nl.teamdiopside.infinitybuttons.compat.blocks;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButton;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButtonType;
import nl.teamdiopside.infinitybuttons.compat.IBModdedBlocks;

import static com.mojang.text2speech.Narrator.LOGGER;

public class EnvironmentalBlocks extends IBModdedBlocks {
    public static final EnvironmentalBlocks INSTANCE = new EnvironmentalBlocks();
    public static final String NAMESPACE = "environmental";

    public static final RegistrySupplier<SecretButton> WILLOW_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("willow");
    public static final RegistrySupplier<SecretButton> PINE_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("pine");
    public static final RegistrySupplier<SecretButton> PLUM_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("plum");
    public static final RegistrySupplier<SecretButton> WISTERIA_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("wisteria");

    private static final BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(1.5f, 2.5f)
            .noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops();

    public static final RegistrySupplier<SecretButton> CHISELED_MUD_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton("chiseled_mud_brick_secret_button",
            SecretButtonType.CHISELED_BRICK, properties, ResourceLocation.fromNamespaceAndPath(NAMESPACE, "chiseled_mud_bricks"));

    private EnvironmentalBlocks() {
        super(NAMESPACE);
    }

    @Override
    public void registerMine() {
        LOGGER.info("Infinity Buttons: Registering Environmental buttons");
    }
}
