package nl.teamdiopside.infinitybuttons.compat.blocks;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButton;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButtonType;
import nl.teamdiopside.infinitybuttons.compat.IBModdedBlocks;

import static com.mojang.text2speech.Narrator.LOGGER;

public class CreateBlocks extends IBModdedBlocks {
    public static final CreateBlocks INSTANCE = new CreateBlocks();
    public static final String NAMESPACE = "create";

    private static final BlockBehaviour.Properties ROSE_QUARTZ_PROPERTIES = BlockBehaviour.Properties.of()
            .mapColor(MapColor.TERRACOTTA_PINK).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops();

    // Create's rose quartz tiles don't keep their texture at the naive create:block/<id> path either
    public static final RegistrySupplier<SecretButton> ROSE_QUARTZ_TILE_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "rose_quartz_tile_secret_button", SecretButtonType.EIGHTS_TILES, ROSE_QUARTZ_PROPERTIES,
            ResourceLocation.fromNamespaceAndPath(NAMESPACE, "rose_quartz_tiles"),
            ResourceLocation.fromNamespaceAndPath(NAMESPACE, "block/palettes/rose_quartz_tiles")
    );
    public static final RegistrySupplier<SecretButton> SMALL_ROSE_QUARTZ_TILE_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "small_rose_quartz_tile_secret_button", SecretButtonType.SMALL_TILE, ROSE_QUARTZ_PROPERTIES,
            ResourceLocation.fromNamespaceAndPath(NAMESPACE, "small_rose_quartz_tiles"),
            ResourceLocation.fromNamespaceAndPath(NAMESPACE, "block/palettes/small_rose_quartz_tiles")
    );

    public static final RegistrySupplier<SecretButton> CUT_GRANITE_BRICK_SECRET_BUTTON = INSTANCE.registerCut("granite", Blocks.GRANITE);
    public static final RegistrySupplier<SecretButton> SMALL_GRANITE_BRICK_SECRET_BUTTON = INSTANCE.registerSmall("granite", Blocks.GRANITE);

    public static final RegistrySupplier<SecretButton> CUT_DIORITE_BRICK_SECRET_BUTTON = INSTANCE.registerCut("diorite", Blocks.DIORITE);
    public static final RegistrySupplier<SecretButton> SMALL_DIORITE_BRICK_SECRET_BUTTON = INSTANCE.registerSmall("diorite", Blocks.DIORITE);

    public static final RegistrySupplier<SecretButton> CUT_ANDESITE_BRICK_SECRET_BUTTON = INSTANCE.registerCut("andesite", Blocks.ANDESITE);
    public static final RegistrySupplier<SecretButton> SMALL_ANDESITE_BRICK_SECRET_BUTTON = INSTANCE.registerSmall("andesite", Blocks.ANDESITE);

    public static final RegistrySupplier<SecretButton> CUT_CALCITE_BRICK_SECRET_BUTTON = INSTANCE.registerCut("calcite", Blocks.CALCITE);
    public static final RegistrySupplier<SecretButton> SMALL_CALCITE_BRICK_SECRET_BUTTON = INSTANCE.registerSmall("calcite", Blocks.CALCITE);

    public static final RegistrySupplier<SecretButton> CUT_DRIPSTONE_BRICK_SECRET_BUTTON = INSTANCE.registerCut("dripstone", Blocks.DRIPSTONE_BLOCK);
    public static final RegistrySupplier<SecretButton> SMALL_DRIPSTONE_BRICK_SECRET_BUTTON = INSTANCE.registerSmall("dripstone", Blocks.DRIPSTONE_BLOCK);

    public static final RegistrySupplier<SecretButton> CUT_DEEPSLATE_BRICK_SECRET_BUTTON = INSTANCE.registerCut("deepslate", Blocks.DEEPSLATE);
    public static final RegistrySupplier<SecretButton> SMALL_DEEPSLATE_BRICK_SECRET_BUTTON = INSTANCE.registerSmall("deepslate", Blocks.DEEPSLATE);

    public static final RegistrySupplier<SecretButton> CUT_TUFF_BRICK_SECRET_BUTTON = INSTANCE.registerCut("tuff", Blocks.TUFF);
    public static final RegistrySupplier<SecretButton> SMALL_TUFF_BRICK_SECRET_BUTTON = INSTANCE.registerSmall("tuff", Blocks.TUFF);

    public static final RegistrySupplier<SecretButton> CUT_ASURINE_BRICK_SECRET_BUTTON = INSTANCE.registerCutMineral("asurine", Blocks.DEEPSLATE, MapColor.COLOR_BLUE);
    public static final RegistrySupplier<SecretButton> SMALL_ASURINE_BRICK_SECRET_BUTTON = INSTANCE.registerSmallMineral("asurine", Blocks.DEEPSLATE, MapColor.COLOR_BLUE);

    public static final RegistrySupplier<SecretButton> CUT_CRIMSITE_BRICK_SECRET_BUTTON = INSTANCE.registerCutMineral("crimsite", Blocks.DEEPSLATE, MapColor.COLOR_RED);
    public static final RegistrySupplier<SecretButton> SMALL_CRIMSITE_BRICK_SECRET_BUTTON = INSTANCE.registerSmallMineral("crimsite", Blocks.DEEPSLATE, MapColor.COLOR_RED);

    public static final RegistrySupplier<SecretButton> CUT_LIMESTONE_BRICK_SECRET_BUTTON = INSTANCE.registerCutMineral("limestone", Blocks.SANDSTONE, MapColor.SAND);
    public static final RegistrySupplier<SecretButton> SMALL_LIMESTONE_BRICK_SECRET_BUTTON = INSTANCE.registerSmallMineral("limestone", Blocks.SANDSTONE, MapColor.SAND);

    public static final RegistrySupplier<SecretButton> CUT_OCHRUM_BRICK_SECRET_BUTTON = INSTANCE.registerCutMineral("ochrum", Blocks.CALCITE, MapColor.TERRACOTTA_YELLOW);
    public static final RegistrySupplier<SecretButton> SMALL_OCHRUM_BRICK_SECRET_BUTTON = INSTANCE.registerSmallMineral("ochrum", Blocks.CALCITE, MapColor.TERRACOTTA_YELLOW);

    public static final RegistrySupplier<SecretButton> CUT_SCORIA_BRICK_SECRET_BUTTON = INSTANCE.registerCut("scoria", BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).mapColor(MapColor.COLOR_BROWN));
    public static final RegistrySupplier<SecretButton> SMALL_SCORIA_BRICK_SECRET_BUTTON = INSTANCE.registerSmall("scoria", BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).mapColor(MapColor.COLOR_BROWN));

    public static final RegistrySupplier<SecretButton> CUT_SCORCHIA_BRICK_SECRET_BUTTON = INSTANCE.registerCut("scorchia", BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).mapColor(MapColor.TERRACOTTA_GRAY));
    public static final RegistrySupplier<SecretButton> SMALL_SCORCHIA_BRICK_SECRET_BUTTON = INSTANCE.registerSmall("scorchia", BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).mapColor(MapColor.TERRACOTTA_GRAY));

    public static final RegistrySupplier<SecretButton> CUT_VERIDIUM_BRICK_SECRET_BUTTON = INSTANCE.registerCutMineral("veridium", Blocks.TUFF, MapColor.WARPED_NYLIUM);
    public static final RegistrySupplier<SecretButton> SMALL_VERIDIUM_BRICK_SECRET_BUTTON = INSTANCE.registerSmallMineral("veridium", Blocks.TUFF, MapColor.WARPED_NYLIUM);

    private CreateBlocks() {
        super(NAMESPACE);
    }

    private RegistrySupplier<SecretButton> registerCutMineral(String type, Block properties, MapColor color) {
        return registerCut(type, BlockBehaviour.Properties.ofFullCopy(properties).destroyTime(1.25F).mapColor(color));
    }

    private RegistrySupplier<SecretButton> registerSmallMineral(String type, Block properties, MapColor color) {
        return registerSmall(type, BlockBehaviour.Properties.ofFullCopy(properties).destroyTime(1.25F).mapColor(color));
    }

    // Create doing whatever it wants
    private ResourceLocation createTexture(String type, boolean small) {
        return ResourceLocation.fromNamespaceAndPath(NAMESPACE, small
                ? "block/palettes/stone_types/small_brick/" + type + "_cut_small_brick"
                : "block/palettes/stone_types/brick/" + type + "_cut_brick");
    }

    private RegistrySupplier<SecretButton> registerCut(String type, Block properties) {
        return this.registerSecretButton("cut_" + type + "_brick_secret_button", 
                SecretButtonType.BIG_BRICK,
                BlockBehaviour.Properties.ofFullCopy(properties).noOcclusion(),
                ResourceLocation.fromNamespaceAndPath(NAMESPACE, "cut_" + type + "_bricks"),
                createTexture(type, false)
        );
    }

    private RegistrySupplier<SecretButton> registerCut(String type, BlockBehaviour.Properties properties) {
        return this.registerSecretButton("cut_" + type + "_brick_secret_button",
                SecretButtonType.BIG_BRICK,
                properties.noOcclusion(),
                ResourceLocation.fromNamespaceAndPath(NAMESPACE, "cut_" + type + "_bricks"),
                createTexture(type, false)
        );
    }

    private RegistrySupplier<SecretButton> registerSmall(String type, Block properties) {
        return this.registerSecretButton("small_" + type + "_brick_secret_button",
                SecretButtonType.SMALL_BRICK,
                BlockBehaviour.Properties.ofFullCopy(properties).noOcclusion(),
                ResourceLocation.fromNamespaceAndPath(NAMESPACE, "small_" + type + "_bricks"),
                createTexture(type, true)
        );
    }

    private RegistrySupplier<SecretButton> registerSmall(String type, BlockBehaviour.Properties properties) {
        return this.registerSecretButton("small_" + type + "_brick_secret_button",
                SecretButtonType.SMALL_BRICK,
                properties.noOcclusion(),
                ResourceLocation.fromNamespaceAndPath(NAMESPACE, "small_" + type + "_bricks"),
                createTexture(type, true)
        );
    }

    @Override
    public void registerMine() {
        LOGGER.info("Infinity Buttons: Registering Create buttons");
    }
}
