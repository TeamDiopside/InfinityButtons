package nl.teamdiopside.infinitybuttons.compat.blocks;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButton;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButtonShape;
import nl.teamdiopside.infinitybuttons.compat.IBModdedBlocks;

import static com.mojang.text2speech.Narrator.LOGGER;

public class NeapolitanBlocks extends IBModdedBlocks {
    public static final NeapolitanBlocks INSTANCE = new NeapolitanBlocks();
    public static final String NAMESPACE = "neapolitan";

    private static final BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN)
            .ignitedByLava().strength(2.0f, 3.0f).noOcclusion().sound(SoundType.WOOD).requiresCorrectToolForDrops();

    public static final RegistrySupplier<SecretButton> CHISELED_CHOCOLATE_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton("chiseled_chocolate_brick_secret_button",
            SecretButtonShape.CHISELED_NETHER_BRICK, properties, ResourceLocation.fromNamespaceAndPath(NAMESPACE, "chiseled_chocolate_bricks"));

    private NeapolitanBlocks() {
        super(NAMESPACE);
    }

    @Override
    public void registerMine() {
        LOGGER.info("Infinity Buttons: Registering Neapolitan buttons");
    }
}
