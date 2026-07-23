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

public class QuarkBlocks extends IBModdedBlocks {
    public static final QuarkBlocks INSTANCE = new QuarkBlocks();
    public static final String NAMESPACE = "quark";

    private static final BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops();

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
    public static final RegistrySupplier<SecretButton> BLOSSOM_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("blossom");
    public static final RegistrySupplier<SecretButton> AZALEA_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("azalea");
    public static final RegistrySupplier<SecretButton> ANCIENT_BOOKSHELF_SECRET_BUTTON = INSTANCE.registerBookshelf("ancient");

    public static final RegistrySupplier<SecretButton> PERMAFROST_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton("permafrost_brick_secret_button",
            SecretButtonShape.BIG_BRICK, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.5f, 10.0f).noOcclusion()
                    .sound(SoundType.STONE).requiresCorrectToolForDrops(), ResourceLocation.fromNamespaceAndPath(NAMESPACE, "permafrost_bricks"));

    public static final RegistrySupplier<SecretButton> BLUE_NETHER_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton("blue_nether_brick_secret_button",
            SecretButtonShape.FULL_BLOCK_BRICK, BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).strength(2.0f, 6.0f).noOcclusion()
                    .sound(SoundType.NETHER_BRICKS).requiresCorrectToolForDrops(), ResourceLocation.fromNamespaceAndPath(NAMESPACE, "blue_nether_bricks"));

    public static final RegistrySupplier<SecretButton> GRANITE_BRICK_SECRET_BUTTON = INSTANCE.registerBrickButton("granite", MapColor.DIRT);
    public static final RegistrySupplier<SecretButton> DIORITE_BRICK_SECRET_BUTTON = INSTANCE.registerBrickButton("diorite", MapColor.QUARTZ);
    public static final RegistrySupplier<SecretButton> ANDESITE_BRICK_SECRET_BUTTON = INSTANCE.registerBrickButton("andesite", MapColor.STONE);
    public static final RegistrySupplier<SecretButton> CALCITE_BRICK_SECRET_BUTTON = INSTANCE.registerBrickButton("calcite", MapColor.TERRACOTTA_WHITE);
    public static final RegistrySupplier<SecretButton> DRIPSTONE_BRICK_SECRET_BUTTON = INSTANCE.registerBrickButton("dripstone", MapColor.TERRACOTTA_BROWN);
    public static final RegistrySupplier<SecretButton> TUFF_BRICK_SECRET_BUTTON = INSTANCE.registerBrickButton("tuff", MapColor.TERRACOTTA_GRAY);
    public static final RegistrySupplier<SecretButton> LIMESTONE_BRICK_SECRET_BUTTON = INSTANCE.registerBrickButton("limestone", MapColor.STONE);
    public static final RegistrySupplier<SecretButton> JASPER_BRICK_SECRET_BUTTON = INSTANCE.registerBrickButton("jasper", MapColor.TERRACOTTA_RED);
    public static final RegistrySupplier<SecretButton> SHALE_BRICK_SECRET_BUTTON = INSTANCE.registerBrickButton("shale", MapColor.ICE);

    public static final RegistrySupplier<SecretButton> CHISELED_POLISHED_GRANITE_BRICK_SECRET_BUTTON = INSTANCE.registerChiseledPolishedButton("granite", MapColor.DIRT);
    public static final RegistrySupplier<SecretButton> CHISELED_POLISHED_DIORITE_BRICK_SECRET_BUTTON = INSTANCE.registerChiseledPolishedButton("diorite", MapColor.QUARTZ);
    public static final RegistrySupplier<SecretButton> CHISELED_POLISHED_ANDESITE_BRICK_SECRET_BUTTON = INSTANCE.registerChiseledPolishedButton("andesite", MapColor.STONE);
    public static final RegistrySupplier<SecretButton> CHISELED_POLISHED_CALCITE_BRICK_SECRET_BUTTON = INSTANCE.registerChiseledPolishedButton("calcite", MapColor.TERRACOTTA_WHITE);
    public static final RegistrySupplier<SecretButton> CHISELED_POLISHED_DRIPSTONE_BRICK_SECRET_BUTTON = INSTANCE.registerChiseledPolishedButton("dripstone", MapColor.TERRACOTTA_BROWN);
    public static final RegistrySupplier<SecretButton> CHISELED_POLISHED_TUFF_BRICK_SECRET_BUTTON = INSTANCE.registerChiseledPolishedButton("tuff", MapColor.TERRACOTTA_GRAY);
    public static final RegistrySupplier<SecretButton> CHISELED_POLISHED_LIMESTONE_BRICK_SECRET_BUTTON = INSTANCE.registerChiseledPolishedButton("limestone", MapColor.STONE);
    public static final RegistrySupplier<SecretButton> CHISELED_POLISHED_JASPER_BRICK_SECRET_BUTTON = INSTANCE.registerChiseledPolishedButton("jasper", MapColor.TERRACOTTA_RED);
    public static final RegistrySupplier<SecretButton> CHISELED_POLISHED_SHALE_BRICK_SECRET_BUTTON = INSTANCE.registerChiseledPolishedButton("shale", MapColor.ICE);

    private RegistrySupplier<SecretButton> registerChiseledPolishedButton(String type, MapColor mapColor) {
        return registerSecretButton("chiseled_polished_" + type + "_brick_secret_button", SecretButtonShape.CHISELED_STONE_BRICK,
                properties.mapColor(mapColor), ResourceLocation.fromNamespaceAndPath(NAMESPACE, "chiseled_" + type + "_bricks"));
    }

    private RegistrySupplier<SecretButton> registerBrickButton(String type, MapColor mapColor) {
        return registerSecretButton(type + "_brick_secret_button", SecretButtonShape.BIG_BRICK,
                properties.mapColor(mapColor), ResourceLocation.fromNamespaceAndPath(NAMESPACE, type + "_bricks"));
    }

    private QuarkBlocks() {
        super(NAMESPACE);
    }

    @Override
    public void registerMine() {
        LOGGER.info("Infinity Buttons: Registering Quark buttons");
    }
}
