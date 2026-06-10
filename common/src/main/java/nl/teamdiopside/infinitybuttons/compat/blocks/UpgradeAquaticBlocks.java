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

public class UpgradeAquaticBlocks extends IBModdedBlocks {
    public static final UpgradeAquaticBlocks INSTANCE = new UpgradeAquaticBlocks();
    public static final String NAMESPACE = "upgrade_aquatic";

    private static final BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().mapColor(MapColor.STONE).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops();

    public static final RegistrySupplier<SecretButton> DRIFTWOOD_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("driftwood");
    public static final RegistrySupplier<SecretButton> RIVER_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("river");

    public static final RegistrySupplier<SecretButton> KELPY_STONE_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton("kelpy_stone_brick_secret_button",
            SecretButtonShape.BIG_BRICK, properties.strength(1.5f, 6.0f), ResourceLocation.fromNamespaceAndPath(NAMESPACE, "kelpy_stone_bricks"));

    public static final RegistrySupplier<SecretButton> CHISELED_TOOTH_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton("chiseled_tooth_brick_secret_button",
            SecretButtonShape.CHISELED_TOOTH, properties.strength(3.0f, 9.0f), ResourceLocation.fromNamespaceAndPath(NAMESPACE, "chiseled_tooth_bricks"));

    private UpgradeAquaticBlocks() {
        super(NAMESPACE);
    }

    @Override
    public void registerMine() {
        LOGGER.info("Infinity Buttons: Registering Upgrade Aquatic buttons");
    }
}
