package nl.teamdiopside.infinitybuttons.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.PushReaction;
import nl.teamdiopside.diopside.registry.DiopsideBlocks;
import nl.teamdiopside.infinitybuttons.block.emergency.EmergencyButton;
import nl.teamdiopside.infinitybuttons.block.emergency.SafeEmergencyButton;
import nl.teamdiopside.infinitybuttons.block.faced4.Doorbell;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButton;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButtonType;
import nl.teamdiopside.infinitybuttons.block.faced4.TorchButton;
import nl.teamdiopside.infinitybuttons.block.faced6.ConsoleButton;
import nl.teamdiopside.infinitybuttons.block.faced6.ConsoleButtonType;
import nl.teamdiopside.infinitybuttons.block.faced6.LampButton;
import nl.teamdiopside.infinitybuttons.block.faced6.LetterButton;
import nl.teamdiopside.infinitybuttons.block.faced6.normal.*;
import nl.teamdiopside.infinitybuttons.block.simple.LanternButton;
import nl.teamdiopside.infinitybuttons.registry.RegistryUtils.LargeVariantSupplier;
import nl.teamdiopside.infinitybuttons.util.BiHashMap;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.LOGGER;
import static nl.teamdiopside.infinitybuttons.InfinityButtons.MOD_ID;

public class IBBlocks {
    public static final HashMap<String, LargeVariantSupplier<Block>> SMALL_LARGE_BUTTONS = new HashMap<>();

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
        LargeVariantSupplier<Block> supplier = registerLargeVariantButton(type,
                (properties, large) -> new NormalButton(BlockSetType.STONE, 20, properties, large, false));

        STONE_BUTTONS.put(type, supplier);
        SMALL_LARGE_BUTTONS.put(type, supplier);
        return supplier;
    }

    public static final HashMap<String, LargeVariantSupplier<Block>> ONE_USE_BUTTONS = new HashMap<>();
    public static final HashMap<DyeColor, LargeVariantSupplier<Block>> CONCRETE_POWDER_BUTTONS = new HashMap<>(); // Subset of above

    public static final LargeVariantSupplier<Block> SAND_BUTTON = registerOneUseButton("sand");
    public static final LargeVariantSupplier<Block> RED_SAND_BUTTON = registerOneUseButton("red_sand");
    public static final LargeVariantSupplier<Block> GRAVEL_BUTTON = registerOneUseButton("gravel"); // Special case in-method

    static {
        for (DyeColor color : DyeColor.values()) {
            registerConcretePowderButton(color, color.name().toLowerCase() + "_concrete_powder");
        }
    }

    private static LargeVariantSupplier<Block> registerOneUseButton(String type) {
        LargeVariantSupplier<Block> supplier = registerLargeVariantButton(type,
                (properties, large) -> new OneUseButton(BlockSetType.STONE, properties, large, false, type.equals("gravel")));
        ONE_USE_BUTTONS.put(type, supplier);
        return supplier;
    }

    private static void registerConcretePowderButton(DyeColor color, String type) {
        LargeVariantSupplier<Block> supplier = registerOneUseButton(type);
        CONCRETE_POWDER_BUTTONS.put(color, supplier);
    }

    /**
     * Super-duper special buttons
     */
    public static final LargeVariantSupplier<Block> EMERALD_BUTTON = registerLargeVariantButton("emerald",
            (properties, large) -> new RandomTimeButton(BlockSetType.STONE, properties, large, false));

    public static final LargeVariantSupplier<Block> PRISMARINE_BUTTON = registerLargeVariantButton("prismarine",
            (properties, large) -> new WaterloggableButton(BlockSetType.STONE, properties, large, false));
    public static final LargeVariantSupplier<Block> DARK_PRISMARINE_BUTTON = registerLargeVariantButton("dark_prismarine",
            (properties, large) -> new WaterloggableButton(BlockSetType.STONE, properties, large, false));
    public static final LargeVariantSupplier<Block> PRISMARINE_BRICK_BUTTON = registerLargeVariantButton("prismarine_brick",
            (properties, large) -> new WaterloggableButton(BlockSetType.STONE, properties, large, false));

    public static final LargeVariantSupplier<Block> DIAMOND_BUTTON = registerLargeVariantButton("diamond",
            (properties, large) -> new SparklingButton(BlockSetType.STONE, properties, large, false));

    public static final LargeVariantSupplier<Block> IRON_BUTTON = registerLargeVariantButton("iron",
            (properties, large) -> new ArrowOnlyButton(BlockSetType.STONE, properties, large, false));
    public static final LargeVariantSupplier<Block> GOLD_BUTTON = registerLargeVariantButton("gold",
            (properties, large) -> new ArrowOnlyButton(BlockSetType.STONE, properties, large, false));

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
                            (properties) -> new NormalButton(type, type == BlockSetType.STONE ? 20 : 30, properties, true, false),
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
     * Torches
     */
    public static final RegistrySupplier<Block> TORCH_BUTTON = registerOnlyBlock("torch_button",
            new TorchButton(torchProperties(14), ParticleTypes.FLAME, false, false, false));
    public static final RegistrySupplier<Block> WALL_TORCH_BUTTON = registerOnlyBlock("wall_torch_button",
            new TorchButton(torchProperties(14).dropsLike(TORCH_BUTTON.get()), ParticleTypes.FLAME, false, true, false));

    public static final RegistrySupplier<Block> TORCH_LEVER = registerOnlyBlock("torch_lever",
            new TorchButton(torchProperties(14), ParticleTypes.FLAME, true, false, false));
    public static final RegistrySupplier<Block> WALL_TORCH_LEVER = registerOnlyBlock("wall_torch_lever",
            new TorchButton(torchProperties(14).dropsLike(TORCH_LEVER.get()), ParticleTypes.FLAME, true, true, false));

    public static final RegistrySupplier<Block> SOUL_TORCH_BUTTON = registerOnlyBlock("soul_torch_button",
            new TorchButton(torchProperties(10), ParticleTypes.SOUL_FIRE_FLAME, false, false, false));
    public static final RegistrySupplier<Block> SOUL_WALL_TORCH_BUTTON = registerOnlyBlock("soul_wall_torch_button",
            new TorchButton(torchProperties(10).dropsLike(SOUL_TORCH_BUTTON.get()), ParticleTypes.SOUL_FIRE_FLAME, false, true, false));

    public static final RegistrySupplier<Block> SOUL_TORCH_LEVER = registerOnlyBlock("soul_torch_lever",
            new TorchButton(torchProperties(10), ParticleTypes.SOUL_FIRE_FLAME, true, false, false));
    public static final RegistrySupplier<Block> SOUL_WALL_TORCH_LEVER = registerOnlyBlock("soul_wall_torch_lever",
            new TorchButton(torchProperties(10).dropsLike(SOUL_TORCH_LEVER.get()), ParticleTypes.SOUL_FIRE_FLAME, true, true, false));

    public static final RegistrySupplier<Block> REDSTONE_TORCH_BUTTON = registerOnlyBlock("redstone_torch_button",
            new TorchButton(torchProperties(7), new DustParticleOptions(new Vector3f(1.0F, 0.0F, 0.0F), 1.0F), false, false, true));
    public static final RegistrySupplier<Block> REDSTONE_WALL_TORCH_BUTTON = registerOnlyBlock("redstone_wall_torch_button",
            new TorchButton(torchProperties(7).dropsLike(REDSTONE_TORCH_BUTTON.get()), new DustParticleOptions(new Vector3f(1.0F, 0.0F, 0.0F), 1.0F), false, true, true));

    public static final RegistrySupplier<Block> REDSTONE_TORCH_LEVER = registerOnlyBlock("redstone_torch_lever",
            new TorchButton(torchProperties(7), new DustParticleOptions(new Vector3f(1.0F, 0.0F, 0.0F), 1.0F), true, false, true));
    public static final RegistrySupplier<Block> REDSTONE_WALL_TORCH_LEVER = registerOnlyBlock("redstone_wall_torch_lever",
            new TorchButton(torchProperties(7).dropsLike(REDSTONE_TORCH_LEVER.get()), new DustParticleOptions(new Vector3f(1.0F, 0.0F, 0.0F), 1.0F), true, true, true));

    /**
     * Lanterns
     */
    public static final RegistrySupplier<Block> LANTERN_BUTTON = registerBlock("lantern_button", properties ->
            new LanternButton(properties, false), lanternProperties(15));
    public static final RegistrySupplier<Block> LANTERN_LEVER = registerBlock("lantern_lever", properties ->
            new LanternButton(properties, true), lanternProperties(15));
    public static final RegistrySupplier<Block> SOUL_LANTERN_BUTTON = registerBlock("soul_lantern_button", properties ->
            new LanternButton(properties, false), lanternProperties(10));
    public static final RegistrySupplier<Block> SOUL_LANTERN_LEVER = registerBlock("soul_lantern_lever", properties ->
            new LanternButton(properties, true), lanternProperties(10));

    /**
     * Console Buttons
     */
    public static final RegistrySupplier<Block> SMALL_CONSOLE_BUTTON = registerBlock("small_console_button", properties ->
            new ConsoleButton(properties, ConsoleButtonType.SMALL, false), lanternProperties(5));
    public static final RegistrySupplier<Block> SMALL_CONSOLE_LEVER = registerBlock("small_console_lever", properties ->
            new ConsoleButton(properties, ConsoleButtonType.SMALL, true), lanternProperties(5));

    public static final RegistrySupplier<Block> CONSOLE_BUTTON = registerBlock("console_button", properties ->
            new ConsoleButton(properties, ConsoleButtonType.NORMAL, false), lanternProperties(5));
    public static final RegistrySupplier<Block> CONSOLE_LEVER = registerBlock("console_lever", properties ->
            new ConsoleButton(properties, ConsoleButtonType.NORMAL, true), lanternProperties(5));
    public static final RegistrySupplier<Block> LARGE_CONSOLE_BUTTON = registerBlock("large_console_button", properties ->
            new ConsoleButton(properties, ConsoleButtonType.LARGE, false), lanternProperties(5));
    public static final RegistrySupplier<Block> LARGE_CONSOLE_LEVER = registerBlock("large_console_lever", properties ->
            new ConsoleButton(properties, ConsoleButtonType.LARGE, true), lanternProperties(5));
    public static final RegistrySupplier<Block> BIG_CONSOLE_BUTTON = registerBlock("big_console_button", properties ->
            new ConsoleButton(properties, ConsoleButtonType.LARGE, false), lanternProperties(5));
    public static final RegistrySupplier<Block> BIG_CONSOLE_LEVER = registerBlock("big_console_lever", properties ->
            new ConsoleButton(properties, ConsoleButtonType.LARGE, true), lanternProperties(5));

    /**
     * Emergency Buttons
     */
    public static final HashMap<DyeColor, RegistrySupplier<Block>> EMERGENCY_BUTTONS = new HashMap<>();
    public static final HashMap<DyeColor, RegistrySupplier<Block>> SAFETY_BUTTONS = new HashMap<>();

    public static final RegistrySupplier<Block> FANCY_EMERGENCY_BUTTON = registerEmergencyButton(null, "fancy");
    public static final RegistrySupplier<Block> FANCY_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton(null, "fancy");

    static {
        for (DyeColor color : DyeColor.values()) {
            registerEmergencyButton(color, color.name().toLowerCase());
            registerSafeEmergencyButton(color, color.name().toLowerCase());
        }
    }

    public static RegistrySupplier<Block> registerEmergencyButton(DyeColor color, String name) {
        RegistrySupplier<Block> blockRS = registerBlock(name + "_emergency_button", EmergencyButton::new,
                BlockBehaviour.Properties.of().strength(0.5f).sound(SoundType.METAL));
        EMERGENCY_BUTTONS.put(color, blockRS);
        return blockRS;
    }

    public static RegistrySupplier<Block> registerSafeEmergencyButton(DyeColor color, String name) {
        RegistrySupplier<Block> blockRS = registerBlock(name + "_safe_emergency_button", SafeEmergencyButton::new,
                BlockBehaviour.Properties.of().strength(0.5f).sound(SoundType.METAL));
        SAFETY_BUTTONS.put(color, blockRS);
        return blockRS;
    }

    /**
     * Doorbells
     */
    public static final RegistrySupplier<Block> DOORBELL = registerBlock("doorbell",
            (properties) -> new Doorbell(properties, false),
            BlockBehaviour.Properties.of().strength(0.5f).sound(SoundType.METAL));
    public static final RegistrySupplier<Block> DOORBELL_BUTTON = registerBlock("doorbell_button",
            (properties) -> new Doorbell(properties, true),
            BlockBehaviour.Properties.of().strength(0.5f).sound(SoundType.METAL));

    /**
     * Lamp buttons
     */
    public static final RegistrySupplier<Block> LAMP_BUTTON = registerBlock("lamp_button",
            (properties) -> new LampButton(BlockSetType.STONE, properties, false),
            lampProperties(15));

    public static final RegistrySupplier<Block> LAMP_LEVER = registerBlock("lamp_lever",
            (properties) -> new LampButton(BlockSetType.STONE, properties, true),
            lampProperties(15));

    /**
     * Letter buttons
     */
    public static final RegistrySupplier<Block> LETTER_BUTTON = registerBlock("letter_button",
            (properties) -> new LetterButton(properties, false),
            BlockBehaviour.Properties.of().strength(0.5f).sound(SoundType.METAL));
    public static final RegistrySupplier<Block> LETTER_LEVER = registerBlock("letter_lever",
            (properties) -> new LetterButton(properties, true),
            BlockBehaviour.Properties.of().strength(0.5f).sound(SoundType.METAL));

    /**
     * Properties
     */
    private static BlockBehaviour.Properties getDefaultProperties() {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON);
    }

    private static BlockBehaviour.Properties lampProperties(int light) {
        return BlockBehaviour.Properties.of().lightLevel(litBlockEmission(light)).sound(SoundType.GLASS).pushReaction(PushReaction.DESTROY);
    }

    private static BlockBehaviour.Properties torchProperties(int light) {
        return BlockBehaviour.Properties.of().noCollission().strength(0.3f).instabreak().lightLevel(litBlockEmission(light)).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY);
    }

    private static BlockBehaviour.Properties lanternProperties(int light) {
        return BlockBehaviour.Properties.of().lightLevel((p) -> light).sound(SoundType.LANTERN)
                .pushReaction(PushReaction.DESTROY).requiresCorrectToolForDrops().strength(3.5f);
    }

    private static ToIntFunction<BlockState> litBlockEmission(int light) {
        return blockState -> blockState.getValue(BlockStateProperties.POWERED) ? light : 0;
    }

    /**
     * Base Registry Functions
     */
    private static <T extends Block> RegistrySupplier<T> registerBlock(String blockId, Function<BlockBehaviour.Properties, T> blockFunction, BlockBehaviour.Properties properties) {
        return DiopsideBlocks.INSTANCE.registerBlock(ResourceLocation.fromNamespaceAndPath(MOD_ID, blockId), blockFunction, properties);
    }

    private static RegistrySupplier<Block> registerOnlyBlock(String blockId, Block block) {
        return DiopsideBlocks.INSTANCE.registerBlockWithoutItem(ResourceLocation.fromNamespaceAndPath(MOD_ID, blockId), (BlockBehaviour.Properties h) -> block, block.properties());
    }


    @FunctionalInterface
    private interface LargeButtonConstructor<T extends Block> {
        T create(BlockBehaviour.Properties properties, boolean large);
    }

    private static <T extends Block> LargeVariantSupplier<T> registerLargeVariantButton(
            String type,
            LargeButtonConstructor<T> constructor
    ) {
        LargeVariantSupplier<T> supplier = LargeVariantSupplier.registerVariants((large) -> registerBlock(
                type + (large ? "_large_button" : "_button"),
                properties -> constructor.create(properties, large),
                getDefaultProperties()
        ));
        SMALL_LARGE_BUTTONS.put(type, (LargeVariantSupplier<Block>) supplier);
        return supplier;
    }

    public static void register() {
        LOGGER.info("Registering Blocks for Infinity Buttons");
    }
}
