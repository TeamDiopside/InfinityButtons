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

public class BuzzierBeesBlocks extends IBModdedBlocks {
    public static final BuzzierBeesBlocks INSTANCE = new BuzzierBeesBlocks();
    public static final String NAMESPACE = "buzzier_bees";

    // Stolen straight from com.teamabnormals.buzzier_bees.core.registry.BBBlocks
    private static final BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(2.0F, 6.0F).sound(SoundType.CORAL_BLOCK);

    public static final RegistrySupplier<SecretButton> HONEYCOMB_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton("honeycomb_brick_secret_button",
            SecretButtonType.HONEYCOMB_BRICK, properties, ResourceLocation.fromNamespaceAndPath(NAMESPACE, "honeycomb_bricks"));

    public static final RegistrySupplier<SecretButton> CHISELED_HONEYCOMB_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton("chiseled_honeycomb_brick_secret_button",
            SecretButtonType.CHISELED_HONEYCOMB_BRICK, properties, ResourceLocation.fromNamespaceAndPath(NAMESPACE, "chiseled_honeycomb_bricks"));

    private BuzzierBeesBlocks() {
        super(NAMESPACE);
    }

    @Override
    public void registerMine() {
        LOGGER.info("Infinity Buttons: Registering Buzzier Bees buttons");
    }
}
