package nl.teamdiopside.infinitybuttons.compat.blocks;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButton;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButtonType;
import nl.teamdiopside.infinitybuttons.compat.IBModdedBlocks;

import java.util.EnumMap;
import java.util.Map;

import static com.mojang.text2speech.Narrator.LOGGER;

public class ClayworksBlocks extends IBModdedBlocks {
    public static final ClayworksBlocks INSTANCE = new ClayworksBlocks();
    public static final String NAMESPACE = "clayworks";

    public static final RegistrySupplier<SecretButton> CHISELED_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "chiseled_brick_secret_button", SecretButtonType.CHISELED_NETHER_BRICK,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(2.0f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(),
            ResourceLocation.fromNamespaceAndPath(NAMESPACE, "chiseled_bricks"));

    public static final RegistrySupplier<SecretButton> TERRACOTTA_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "terracotta_brick_secret_button", SecretButtonType.FULL_BLOCK_BRICK,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(1.25f, 4.2f).noOcclusion().requiresCorrectToolForDrops(),
            ResourceLocation.fromNamespaceAndPath(NAMESPACE, "terracotta_bricks"));

    public static final RegistrySupplier<SecretButton> CHISELED_TERRACOTTA_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "chiseled_terracotta_brick_secret_button", SecretButtonType.CHISELED_NETHER_BRICK,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(1.25f, 4.2f).noOcclusion().requiresCorrectToolForDrops(),
            ResourceLocation.fromNamespaceAndPath(NAMESPACE, "chiseled_terracotta_bricks"));

    public static final Map<DyeColor, MapColor> TERRACOTTA_BY_DYE = Util.make(new EnumMap<>(DyeColor.class), map -> {
        map.put(DyeColor.WHITE, MapColor.TERRACOTTA_WHITE);
        map.put(DyeColor.ORANGE, MapColor.TERRACOTTA_ORANGE);
        map.put(DyeColor.MAGENTA, MapColor.TERRACOTTA_MAGENTA);
        map.put(DyeColor.LIGHT_BLUE, MapColor.TERRACOTTA_LIGHT_BLUE);
        map.put(DyeColor.YELLOW, MapColor.TERRACOTTA_YELLOW);
        map.put(DyeColor.LIME, MapColor.TERRACOTTA_LIGHT_GREEN);
        map.put(DyeColor.PINK, MapColor.TERRACOTTA_PINK);
        map.put(DyeColor.GRAY, MapColor.TERRACOTTA_GRAY);
        map.put(DyeColor.LIGHT_GRAY, MapColor.TERRACOTTA_LIGHT_GRAY);
        map.put(DyeColor.CYAN, MapColor.TERRACOTTA_CYAN);
        map.put(DyeColor.PURPLE, MapColor.TERRACOTTA_PURPLE);
        map.put(DyeColor.BLUE, MapColor.TERRACOTTA_BLUE);
        map.put(DyeColor.BROWN, MapColor.TERRACOTTA_BROWN);
        map.put(DyeColor.GREEN, MapColor.TERRACOTTA_GREEN);
        map.put(DyeColor.RED, MapColor.TERRACOTTA_RED);
        map.put(DyeColor.BLACK, MapColor.TERRACOTTA_BLACK);
    });

    static {
        for (DyeColor color : DyeColor.values()) {
            String colorName = color.getSerializedName();
            MapColor mapColor = TERRACOTTA_BY_DYE.get(color);

            registerTerracotta(colorName, mapColor);
            registerChiseledTerracotta(colorName, mapColor);
        }
    }

    public static void registerTerracotta(String color, MapColor mapColor) {
        INSTANCE.registerSecretButton(
                color + "_terracotta_brick_secret_button", SecretButtonType.FULL_BLOCK_BRICK,
                BlockBehaviour.Properties.of().mapColor(mapColor).strength(1.25f, 4.2f).noOcclusion().requiresCorrectToolForDrops(),
                ResourceLocation.fromNamespaceAndPath(NAMESPACE, color + "_terracotta_bricks"));
    }

    public static void registerChiseledTerracotta(String color, MapColor mapColor) {
        INSTANCE.registerSecretButton(
                "chiseled_" + color + "_terracotta_brick_secret_button", SecretButtonType.CHISELED_NETHER_BRICK,
                BlockBehaviour.Properties.of().mapColor(mapColor).strength(1.25f, 4.2f).noOcclusion().requiresCorrectToolForDrops(),
                ResourceLocation.fromNamespaceAndPath(NAMESPACE, "chiseled_" + color + "_terracotta_bricks"));
    }

    private ClayworksBlocks() {
        super(NAMESPACE);
    }

    @Override
    public void registerMine() {
        LOGGER.info("Infinity Buttons: Registering Clayworks buttons");
    }
}
