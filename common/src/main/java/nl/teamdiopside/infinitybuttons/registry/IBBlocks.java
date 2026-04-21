package nl.teamdiopside.infinitybuttons.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import nl.teamdiopside.diopside.registry.DiopsideBlocks;
import nl.teamdiopside.infinitybuttons.block.normal.CopperButton;
import nl.teamdiopside.infinitybuttons.block.normal.CopperButtonType;
import nl.teamdiopside.infinitybuttons.block.normal.NormalButton;
import nl.teamdiopside.infinitybuttons.block.secret.SecretButton;
import nl.teamdiopside.infinitybuttons.block.secret.SecretButtonType;
import nl.teamdiopside.infinitybuttons.registry.RegistryUtils.LargeVariantSupplier;
import nl.teamdiopside.infinitybuttons.util.BiHashMap;

import java.util.HashMap;
import java.util.function.Function;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.LOGGER;
import static nl.teamdiopside.infinitybuttons.InfinityButtons.MOD_ID;

public class IBBlocks {

    /**
     * Stone Buttons
     */
    public static final HashMap<String, LargeVariantSupplier<Block>> STONE_BUTTONS = new HashMap<>();

    public static final LargeVariantSupplier<Block> DEEPSLATE_BUTTON = registerStoneButton("deepslate");
    public static final LargeVariantSupplier<Block> GRANITE_BUTTON = registerStoneButton("granite");
    public static final LargeVariantSupplier<Block> DIORITE_BUTTON = registerStoneButton("diorite");
    public static final LargeVariantSupplier<Block> ANDESITE_BUTTON = registerStoneButton("andesite");
    public static final LargeVariantSupplier<Block> CALCITE_BUTTON = registerStoneButton("calcite");
    public static final LargeVariantSupplier<Block> TUFF_BUTTON = registerStoneButton("tuff");
    public static final LargeVariantSupplier<Block> DRIPSTONE_BUTTON = registerStoneButton("dripstone");

    private static LargeVariantSupplier<Block> registerStoneButton(String type) {
        LargeVariantSupplier<Block> supplier =
                LargeVariantSupplier.registerVariants((large) -> registerBlock(
                        type + (large ? "_large_button" : "_button"),
                        (properties) -> new NormalButton(BlockSetType.STONE, 20, properties, large),
                        getDefaultProperties()
                ));
        STONE_BUTTONS.put(type, supplier);
        return supplier;
    }

    /**
     * Copper Buttons
     */
    public static final BiHashMap<CopperButtonType, WeatheringCopper.WeatherState, LargeVariantSupplier<CopperButton>> COPPER_BUTTONS = registerCopperButtons();

    private static LargeVariantSupplier<CopperButton> registerCopperButton(CopperButtonType copperButtonType, WeatheringCopper.WeatherState weatherState) {
        String state = weatherState == WeatheringCopper.WeatherState.UNAFFECTED ? "copper" : weatherState.getSerializedName() + "_copper";
        String type = copperButtonType == CopperButtonType.NORMAL ? state : copperButtonType.getName() + "_" + state;
        return LargeVariantSupplier.registerVariants((large) -> registerBlock(
                type + (large ? "_large_button" : "_button"),
                (properties) -> new CopperButton(properties, large, weatherState, copperButtonType),
                getDefaultProperties().sound(SoundType.COPPER).requiresCorrectToolForDrops()
        ));
    }

    private static BiHashMap<CopperButtonType, WeatheringCopper.WeatherState, LargeVariantSupplier<CopperButton>> registerCopperButtons() {
        BiHashMap<CopperButtonType, WeatheringCopper.WeatherState, LargeVariantSupplier<CopperButton>> typeMap = new BiHashMap<>();
        for (CopperButtonType type : CopperButtonType.values()) {
            for (WeatheringCopper.WeatherState state : WeatheringCopper.WeatherState.values()) {
                typeMap.put(type, state, registerCopperButton(type, state));
            }
        }
        return typeMap;
    }

    /**
     * Large Vanilla Buttons
     */
    public static final HashMap<BlockSetType, RegistrySupplier<Block>> DEFAULT_LARGE_BUTTONS = registerDefaultLargeButtons();

    private static HashMap<BlockSetType, RegistrySupplier<Block>> registerDefaultLargeButtons() {
        HashMap<BlockSetType, RegistrySupplier<Block>> map = new HashMap<>();
        for (BlockSetType type : BlockSetType.values().toList()) {
            if (type == BlockSetType.COPPER || type == BlockSetType.GOLD || type == BlockSetType.IRON) continue;
            map.put(type, registerBlock(
                            type.name() + "_large_button",
                            (properties) -> new NormalButton(type, type == BlockSetType.STONE ? 20 : 30, properties, true),
                            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)
                    )
            );
        }
        return map;
    }

    /**
     * Secret Buttons
     */
    public static final HashMap<String, RegistrySupplier<Block>> SECRET_BUTTONS = new HashMap<>();

    public static final RegistrySupplier<Block> BOOKSHELF_SECRET_BUTTON = registerSecretButton("bookshelf_secret_button",
            SecretButtonType.BOOKSHELF, BlockSetType.OAK, Blocks.BOOKSHELF );
    public static final RegistrySupplier<Block> BRICK_SECRET_BUTTON = registerSecretButton("brick_secret_button",
            SecretButtonType.FULL_BLOCK_BRICK, BlockSetType.STONE, Blocks.BRICKS );
    public static final RegistrySupplier<Block> STONE_BRICK_SECRET_BUTTON = registerSecretButton("stone_brick_secret_button",
            SecretButtonType.BIG_BRICK, BlockSetType.STONE, Blocks.BRICKS );
    public static final RegistrySupplier<Block> MOSSY_STONE_BRICK_SECRET_BUTTON = registerSecretButton("mossy_stone_brick_secret_button",
            SecretButtonType.BIG_BRICK, BlockSetType.STONE, Blocks.MOSSY_STONE_BRICKS );
    public static final RegistrySupplier<Block> CRACKED_STONE_BRICK_SECRET_BUTTON = registerSecretButton("cracked_stone_brick_secret_button",
            SecretButtonType.BIG_BRICK, BlockSetType.STONE, Blocks.CRACKED_STONE_BRICKS );
    public static final RegistrySupplier<Block> CHISELED_STONE_BRICK_SECRET_BUTTON = registerSecretButton("chiseled_stone_brick_secret_button",
            SecretButtonType.CHISELED_STONE_BRICK, BlockSetType.STONE, Blocks.CHISELED_STONE_BRICKS );
    public static final RegistrySupplier<Block> DEEPSLATE_BRICK_SECRET_BUTTON = registerSecretButton("deepslate_brick_secret_button",
            SecretButtonType.BIG_BRICK, BlockSetType.STONE, Blocks.DEEPSLATE_BRICKS );
    public static final RegistrySupplier<Block> CRACKED_DEEPSLATE_BRICK_SECRET_BUTTON = registerSecretButton("cracked_deepslate_brick_secret_button",
            SecretButtonType.BIG_BRICK, BlockSetType.STONE, Blocks.CRACKED_DEEPSLATE_BRICKS );
    public static final RegistrySupplier<Block> DEEPSLATE_TILE_SECRET_BUTTON = registerSecretButton("deepslate_tile_secret_button",
            SecretButtonType.DEEPSLATE_TILE, BlockSetType.STONE, Blocks.DEEPSLATE_TILES );
    public static final RegistrySupplier<Block> CRACKED_DEEPSLATE_TILE_SECRET_BUTTON = registerSecretButton("cracked_deepslate_tile_secret_button",
            SecretButtonType.DEEPSLATE_TILE, BlockSetType.STONE, Blocks.CRACKED_DEEPSLATE_TILES );

    public static final RegistrySupplier<Block> OAK_PLANK_SECRET_BUTTON = registerSecretButton("oak_plank_secret_button",
            SecretButtonType.PLANK, BlockSetType.OAK, Blocks.OAK_PLANKS );
    public static final RegistrySupplier<Block> SPRUCE_PLANK_SECRET_BUTTON = registerSecretButton("spruce_plank_secret_button",
            SecretButtonType.PLANK, BlockSetType.SPRUCE, Blocks.SPRUCE_PLANKS );
    public static final RegistrySupplier<Block> BIRCH_PLANK_SECRET_BUTTON = registerSecretButton("birch_plank_secret_button",
            SecretButtonType.PLANK, BlockSetType.BIRCH, Blocks.BIRCH_PLANKS );
    public static final RegistrySupplier<Block> JUNGLE_PLANK_SECRET_BUTTON = registerSecretButton("jungle_plank_secret_button",
            SecretButtonType.PLANK, BlockSetType.JUNGLE, Blocks.JUNGLE_PLANKS );
    public static final RegistrySupplier<Block> ACACIA_PLANK_SECRET_BUTTON = registerSecretButton("acacia_plank_secret_button",
            SecretButtonType.PLANK, BlockSetType.ACACIA, Blocks.ACACIA_PLANKS );
    public static final RegistrySupplier<Block> DARK_OAK_PLANK_SECRET_BUTTON = registerSecretButton("dark_oak_plank_secret_button",
            SecretButtonType.PLANK, BlockSetType.DARK_OAK, Blocks.DARK_OAK_PLANKS );
    public static final RegistrySupplier<Block> MANGROVE_PLANK_SECRET_BUTTON = registerSecretButton("mangrove_plank_secret_button",
            SecretButtonType.PLANK, BlockSetType.MANGROVE, Blocks.MANGROVE_PLANKS );
    public static final RegistrySupplier<Block> CRIMSON_PLANK_SECRET_BUTTON = registerSecretButton("crimson_plank_secret_button",
            SecretButtonType.PLANK, BlockSetType.CRIMSON, Blocks.CRIMSON_PLANKS );
    public static final RegistrySupplier<Block> WARPED_PLANK_SECRET_BUTTON = registerSecretButton("warped_plank_secret_button",
            SecretButtonType.PLANK, BlockSetType.WARPED, Blocks.WARPED_PLANKS );

    public static final RegistrySupplier<Block> MUD_BRICK_SECRET_BUTTON = registerSecretButton("mud_brick_secret_button",
            SecretButtonType.MUD_BRICK, BlockSetType.STONE, Blocks.MUD_BRICKS );
    public static final RegistrySupplier<Block> END_STONE_BRICK_SECRET_BUTTON = registerSecretButton("end_stone_brick_secret_button",
            SecretButtonType.BIG_BRICK, BlockSetType.STONE, Blocks.END_STONE_BRICKS );
    public static final RegistrySupplier<Block> PURPUR_BLOCK_SECRET_BUTTON = registerSecretButton("purpur_block_secret_button",
            SecretButtonType.TILE, BlockSetType.STONE, Blocks.PURPUR_BLOCK );
    public static final RegistrySupplier<Block> QUARTZ_BRICK_SECRET_BUTTON = registerSecretButton("quartz_brick_secret_button",
            SecretButtonType.BIG_BRICK, BlockSetType.STONE, Blocks.QUARTZ_BLOCK );
    public static final RegistrySupplier<Block> DARK_PRISMARINE_SECRET_BUTTON = registerSecretButton("dark_prismarine_secret_button",
            SecretButtonType.FULL_BLOCK_BRICK, BlockSetType.STONE, Blocks.DARK_PRISMARINE );
    public static final RegistrySupplier<Block> POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON = registerSecretButton("polished_blackstone_brick_secret_button",
            SecretButtonType.BIG_BRICK, BlockSetType.STONE, Blocks.POLISHED_BLACKSTONE_BRICKS );
    public static final RegistrySupplier<Block> CRACKED_POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON = registerSecretButton("cracked_polished_blackstone_brick_secret_button",
            SecretButtonType.BIG_BRICK, BlockSetType.STONE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS );
    public static final RegistrySupplier<Block> CHISELED_POLISHED_BLACKSTONE_SECRET_BUTTON = registerSecretButton("chiseled_polished_blackstone_secret_button",
            SecretButtonType.CHISELED_STONE_BRICK, BlockSetType.STONE, Blocks.CHISELED_POLISHED_BLACKSTONE );
    public static final RegistrySupplier<Block> NETHER_BRICK_SECRET_BUTTON = registerSecretButton("nether_brick_secret_button",
            SecretButtonType.FULL_BLOCK_BRICK, BlockSetType.STONE, Blocks.NETHER_BRICKS );
    public static final RegistrySupplier<Block> CRACKED_NETHER_BRICK_SECRET_BUTTON = registerSecretButton("cracked_nether_brick_secret_button",
            SecretButtonType.FULL_BLOCK_BRICK, BlockSetType.STONE, Blocks.CRACKED_NETHER_BRICKS );
    public static final RegistrySupplier<Block> CHISELED_NETHER_BRICK_SECRET_BUTTON = registerSecretButton("chiseled_nether_brick_secret_button",
            SecretButtonType.CHISELED_NETHER_BRICK, BlockSetType.STONE, Blocks.CHISELED_NETHER_BRICKS );
    public static final RegistrySupplier<Block> RED_NETHER_BRICK_SECRET_BUTTON = registerSecretButton("red_nether_brick_secret_button",
            SecretButtonType.FULL_BLOCK_BRICK, BlockSetType.STONE, Blocks.RED_NETHER_BRICKS );


    private static RegistrySupplier<Block> registerSecretButton(
            String blockId,
            SecretButtonType type,
            BlockSetType blockSetType,
            BlockBehaviour behaviour
    ) {
        RegistrySupplier<Block> blockRS = registerBlock(
                blockId,
                buttonProperties -> new SecretButton(buttonProperties, type),
                BlockBehaviour.Properties.ofFullCopy(behaviour)
        );
        SECRET_BUTTONS.put(blockId, blockRS);
        return blockRS;
    }

    /**
     * Properties
     */
    private static BlockBehaviour.Properties getDefaultProperties() {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON);
    }

    /**
     * Base Registry Functions
     */
    private static <T extends Block> RegistrySupplier<T> registerBlock(String blockId, Function<BlockBehaviour.Properties, T> blockFunction, BlockBehaviour.Properties properties) {
        return DiopsideBlocks.INSTANCE.registerBlock(ResourceLocation.fromNamespaceAndPath(MOD_ID, blockId), blockFunction, properties);
    }

    public static void register() {
        LOGGER.info("Registering Blocks for Infinity Buttons");
    }
}
