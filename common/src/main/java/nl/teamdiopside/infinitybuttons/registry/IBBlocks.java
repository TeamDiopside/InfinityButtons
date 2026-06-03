package nl.teamdiopside.infinitybuttons.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
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
import nl.teamdiopside.diopside.registry.BlockEntryBuilder;
import nl.teamdiopside.diopside.registry.TooltipBuilder;
import nl.teamdiopside.diopside.tooltip.HoldKeyTooltip;
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
import java.util.function.ToIntFunction;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.LOGGER;
import static nl.teamdiopside.infinitybuttons.InfinityButtons.getResource;

public class IBBlocks {
    public static final HashMap<String, LargeVariantSupplier<? extends Block>> SMALL_LARGE_BUTTONS = new HashMap<>();
    public static final HashMap<String, RegistrySupplier<? extends Block>> ALL_BUTTONS = new HashMap<>();

    /**
     * Stone Buttons
     */
    public static final HashMap<String, LargeVariantSupplier<NormalButton>> STONE_BUTTONS = new HashMap<>();

    public static final LargeVariantSupplier<NormalButton> DEEPSLATE_BUTTON = registerStoneButton("deepslate");
    public static final LargeVariantSupplier<NormalButton> GRANITE_BUTTON = registerStoneButton("granite");
    public static final LargeVariantSupplier<NormalButton> DIORITE_BUTTON = registerStoneButton("diorite");
    public static final LargeVariantSupplier<NormalButton> ANDESITE_BUTTON = registerStoneButton("andesite");
    public static final LargeVariantSupplier<NormalButton> CALCITE_BUTTON = registerStoneButton("calcite");
    public static final LargeVariantSupplier<NormalButton> TUFF_BUTTON = registerStoneButton("tuff");
    public static final LargeVariantSupplier<NormalButton> DRIPSTONE_BUTTON = registerStoneButton("dripstone");

    private static LargeVariantSupplier<NormalButton> registerStoneButton(String type) {
        LargeVariantSupplier<NormalButton> supplier = registerLargeVariantButton(type,
                (properties, large) -> new NormalButton(BlockSetType.STONE, 20, properties, large, false));

        STONE_BUTTONS.put(type, supplier);
        SMALL_LARGE_BUTTONS.put(type, supplier);
        return supplier;
    }

    public static final HashMap<String, LargeVariantSupplier<OneUseButton>> ONE_USE_BUTTONS = new HashMap<>();
    public static final HashMap<DyeColor, LargeVariantSupplier<OneUseButton>> CONCRETE_POWDER_BUTTONS = new HashMap<>(); // Subset of above

    public static final LargeVariantSupplier<OneUseButton> SAND_BUTTON = registerOneUseButton("sand");
    public static final LargeVariantSupplier<OneUseButton> RED_SAND_BUTTON = registerOneUseButton("red_sand");
    public static final LargeVariantSupplier<OneUseButton> GRAVEL_BUTTON = registerOneUseButton("gravel"); // Special case in-method

    static {
        for (DyeColor color : DyeColor.values()) {
            registerConcretePowderButton(color, color.name().toLowerCase() + "_concrete_powder");
        }
    }

    private static LargeVariantSupplier<OneUseButton> registerOneUseButton(String type) {
        LargeVariantSupplier<OneUseButton> supplier = registerLargeVariantButton(type,
                (properties, large) -> new OneUseButton(BlockSetType.STONE, properties, large, false, type.equals("gravel")));
        ONE_USE_BUTTONS.put(type, supplier);
        return supplier;
    }

    private static void registerConcretePowderButton(DyeColor color, String type) {
        LargeVariantSupplier<OneUseButton> supplier = registerOneUseButton(type);
        CONCRETE_POWDER_BUTTONS.put(color, supplier);
    }

    /**
     * Super-duper special buttons
     */
    public static final LargeVariantSupplier<RandomTimeButton> EMERALD_BUTTON = registerLargeVariantButton("emerald",
            (properties, large) -> new RandomTimeButton(BlockSetType.STONE, properties, large, false));

    public static final LargeVariantSupplier<WaterloggableButton> PRISMARINE_BUTTON = registerLargeVariantButton("prismarine",
            (properties, large) -> new WaterloggableButton(BlockSetType.STONE, properties, large, false));
    public static final LargeVariantSupplier<WaterloggableButton> DARK_PRISMARINE_BUTTON = registerLargeVariantButton("dark_prismarine",
            (properties, large) -> new WaterloggableButton(BlockSetType.STONE, properties, large, false));
    public static final LargeVariantSupplier<WaterloggableButton> PRISMARINE_BRICK_BUTTON = registerLargeVariantButton("prismarine_brick",
            (properties, large) -> new WaterloggableButton(BlockSetType.STONE, properties, large, false));

    public static final LargeVariantSupplier<SparklingButton> DIAMOND_BUTTON = registerLargeVariantButton("diamond",
            (properties, large) -> new SparklingButton(BlockSetType.STONE, properties, large, false));

    public static final LargeVariantSupplier<ArrowOnlyButton> IRON_BUTTON = registerLargeVariantButton("iron",
            (properties, large) -> new ArrowOnlyButton(BlockSetType.STONE, properties, large, false));
    public static final LargeVariantSupplier<ArrowOnlyButton> GOLD_BUTTON = registerLargeVariantButton("gold",
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
                BlockEntryBuilder.ofBlock((properties) -> new CopperButton(properties, large, weatherState, copperButtonType))
                        .withTooltip(TooltipBuilder
                                .builder("infinitybuttons.tooltip.sticky_copper_button")
                                .setTooltipClass(HoldKeyTooltip.class)
                                .setTooltipVisible(ignored -> copperButtonType == CopperButtonType.STICKY)
                                .withStyle(ChatFormatting.GRAY)
                        ),
                getDefaultProperties().sound(SoundType.COPPER).requiresCorrectToolForDrops()
        )); // TODO: clean up
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
    public static final HashMap<BlockSetType, RegistrySupplier<NormalButton>> DEFAULT_LARGE_BUTTONS = registerDefaultLargeButtons();

    private static HashMap<BlockSetType, RegistrySupplier<NormalButton>> registerDefaultLargeButtons() {
        HashMap<BlockSetType, RegistrySupplier<NormalButton>> map = new HashMap<>();
        for (BlockSetType type : BlockSetType.values().toList()) {
            if (type == BlockSetType.COPPER || type == BlockSetType.GOLD || type == BlockSetType.IRON) continue;
            map.put(type, registerBlock(
                            type.name() + "_large_button",
                            BlockEntryBuilder.ofBlock((properties) -> new NormalButton(type, type == BlockSetType.STONE ? 20 : 30, properties, true, false)),
                            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)
                    )
            ); // TODO: cleanup
        }
        return map;
    }

    /**
     * Secret Buttons
     */
    public static final HashMap<Block, RegistrySupplier<SecretButton>> SECRET_BUTTONS = new HashMap<>();

    public static final RegistrySupplier<SecretButton> BOOKSHELF_SECRET_BUTTON = registerSecretButton("bookshelf_secret_button",
            SecretButtonType.BOOKSHELF, Blocks.BOOKSHELF );
    public static final RegistrySupplier<SecretButton> BRICK_SECRET_BUTTON = registerSecretButton("brick_secret_button",
            SecretButtonType.FULL_BLOCK_BRICK, Blocks.BRICKS );
    public static final RegistrySupplier<SecretButton> STONE_BRICK_SECRET_BUTTON = registerSecretButton("stone_brick_secret_button",
            SecretButtonType.BIG_BRICK, Blocks.BRICKS );
    public static final RegistrySupplier<SecretButton> MOSSY_STONE_BRICK_SECRET_BUTTON = registerSecretButton("mossy_stone_brick_secret_button",
            SecretButtonType.BIG_BRICK, Blocks.MOSSY_STONE_BRICKS );
    public static final RegistrySupplier<SecretButton> CRACKED_STONE_BRICK_SECRET_BUTTON = registerSecretButton("cracked_stone_brick_secret_button",
            SecretButtonType.BIG_BRICK, Blocks.CRACKED_STONE_BRICKS );
    public static final RegistrySupplier<SecretButton> CHISELED_STONE_BRICK_SECRET_BUTTON = registerSecretButton("chiseled_stone_brick_secret_button",
            SecretButtonType.CHISELED_STONE_BRICK, Blocks.CHISELED_STONE_BRICKS );
    public static final RegistrySupplier<SecretButton> DEEPSLATE_BRICK_SECRET_BUTTON = registerSecretButton("deepslate_brick_secret_button",
            SecretButtonType.BIG_BRICK, Blocks.DEEPSLATE_BRICKS );
    public static final RegistrySupplier<SecretButton> CRACKED_DEEPSLATE_BRICK_SECRET_BUTTON = registerSecretButton("cracked_deepslate_brick_secret_button",
            SecretButtonType.BIG_BRICK, Blocks.CRACKED_DEEPSLATE_BRICKS );
    public static final RegistrySupplier<SecretButton> DEEPSLATE_TILE_SECRET_BUTTON = registerSecretButton("deepslate_tile_secret_button",
            SecretButtonType.DEEPSLATE_TILE, Blocks.DEEPSLATE_TILES );
    public static final RegistrySupplier<SecretButton> CRACKED_DEEPSLATE_TILE_SECRET_BUTTON = registerSecretButton("cracked_deepslate_tile_secret_button",
            SecretButtonType.DEEPSLATE_TILE, Blocks.CRACKED_DEEPSLATE_TILES );

    public static final RegistrySupplier<SecretButton> OAK_PLANK_SECRET_BUTTON = registerSecretButton("oak_plank_secret_button",
            SecretButtonType.PLANK, Blocks.OAK_PLANKS );
    public static final RegistrySupplier<SecretButton> SPRUCE_PLANK_SECRET_BUTTON = registerSecretButton("spruce_plank_secret_button",
            SecretButtonType.PLANK, Blocks.SPRUCE_PLANKS );
    public static final RegistrySupplier<SecretButton> BIRCH_PLANK_SECRET_BUTTON = registerSecretButton("birch_plank_secret_button",
            SecretButtonType.PLANK, Blocks.BIRCH_PLANKS );
    public static final RegistrySupplier<SecretButton> JUNGLE_PLANK_SECRET_BUTTON = registerSecretButton("jungle_plank_secret_button",
            SecretButtonType.PLANK, Blocks.JUNGLE_PLANKS );
    public static final RegistrySupplier<SecretButton> ACACIA_PLANK_SECRET_BUTTON = registerSecretButton("acacia_plank_secret_button",
            SecretButtonType.PLANK, Blocks.ACACIA_PLANKS );
    public static final RegistrySupplier<SecretButton> DARK_OAK_PLANK_SECRET_BUTTON = registerSecretButton("dark_oak_plank_secret_button",
            SecretButtonType.PLANK, Blocks.DARK_OAK_PLANKS );
    public static final RegistrySupplier<SecretButton> MANGROVE_PLANK_SECRET_BUTTON = registerSecretButton("mangrove_plank_secret_button",
            SecretButtonType.PLANK, Blocks.MANGROVE_PLANKS );
    public static final RegistrySupplier<SecretButton> CHERRY_PLANK_SECRET_BUTTON = registerSecretButton("cherry_plank_secret_button",
            SecretButtonType.PLANK, Blocks.CHERRY_PLANKS );
    public static final RegistrySupplier<SecretButton> CRIMSON_PLANK_SECRET_BUTTON = registerSecretButton("crimson_plank_secret_button",
            SecretButtonType.PLANK, Blocks.CRIMSON_PLANKS );
    public static final RegistrySupplier<SecretButton> WARPED_PLANK_SECRET_BUTTON = registerSecretButton("warped_plank_secret_button",
            SecretButtonType.PLANK, Blocks.WARPED_PLANKS );

    public static final RegistrySupplier<SecretButton> MUD_BRICK_SECRET_BUTTON = registerSecretButton("mud_brick_secret_button",
            SecretButtonType.MUD_BRICK, Blocks.MUD_BRICKS );
    public static final RegistrySupplier<SecretButton> END_STONE_BRICK_SECRET_BUTTON = registerSecretButton("end_stone_brick_secret_button",
            SecretButtonType.BIG_BRICK, Blocks.END_STONE_BRICKS );
    public static final RegistrySupplier<SecretButton> PURPUR_BLOCK_SECRET_BUTTON = registerSecretButton("purpur_block_secret_button",
            SecretButtonType.TILE, Blocks.PURPUR_BLOCK );
    public static final RegistrySupplier<SecretButton> QUARTZ_BRICK_SECRET_BUTTON = registerSecretButton("quartz_brick_secret_button",
            SecretButtonType.BIG_BRICK, Blocks.QUARTZ_BLOCK );
    public static final RegistrySupplier<SecretButton> DARK_PRISMARINE_SECRET_BUTTON = registerSecretButton("dark_prismarine_secret_button",
            SecretButtonType.FULL_BLOCK_BRICK, Blocks.DARK_PRISMARINE );
    public static final RegistrySupplier<SecretButton> POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON = registerSecretButton("polished_blackstone_brick_secret_button",
            SecretButtonType.BIG_BRICK, Blocks.POLISHED_BLACKSTONE_BRICKS );
    public static final RegistrySupplier<SecretButton> CRACKED_POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON = registerSecretButton("cracked_polished_blackstone_brick_secret_button",
            SecretButtonType.BIG_BRICK, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS );
    public static final RegistrySupplier<SecretButton> CHISELED_POLISHED_BLACKSTONE_SECRET_BUTTON = registerSecretButton("chiseled_polished_blackstone_secret_button",
            SecretButtonType.CHISELED_STONE_BRICK, Blocks.CHISELED_POLISHED_BLACKSTONE );
    public static final RegistrySupplier<SecretButton> NETHER_BRICK_SECRET_BUTTON = registerSecretButton("nether_brick_secret_button",
            SecretButtonType.FULL_BLOCK_BRICK, Blocks.NETHER_BRICKS );
    public static final RegistrySupplier<SecretButton> CRACKED_NETHER_BRICK_SECRET_BUTTON = registerSecretButton("cracked_nether_brick_secret_button",
            SecretButtonType.FULL_BLOCK_BRICK, Blocks.CRACKED_NETHER_BRICKS );
    public static final RegistrySupplier<SecretButton> CHISELED_NETHER_BRICK_SECRET_BUTTON = registerSecretButton("chiseled_nether_brick_secret_button",
            SecretButtonType.CHISELED_NETHER_BRICK, Blocks.CHISELED_NETHER_BRICKS );
    public static final RegistrySupplier<SecretButton> RED_NETHER_BRICK_SECRET_BUTTON = registerSecretButton("red_nether_brick_secret_button",
            SecretButtonType.FULL_BLOCK_BRICK, Blocks.RED_NETHER_BRICKS );


    private static RegistrySupplier<SecretButton> registerSecretButton(
            String blockId,
            SecretButtonType type,
            Block originalBlock
    ) {
        RegistrySupplier<SecretButton> blockRS = registerBlock(
                blockId,
                BlockEntryBuilder.ofBlock(buttonProperties -> new SecretButton(buttonProperties, type)),
                BlockBehaviour.Properties.ofFullCopy(originalBlock)
        );
        SECRET_BUTTONS.put(originalBlock, blockRS);
        return blockRS;
    }

    /**
     * Torches
     */
    public static final RegistrySupplier<TorchButton> TORCH_BUTTON = registerBlock("torch_button", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(properties, ParticleTypes.FLAME, false, false, false)).withoutItem(), torchProperties(14));
    public static final RegistrySupplier<TorchButton> WALL_TORCH_BUTTON = registerBlock("wall_torch_button", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(properties.dropsLike(TORCH_BUTTON.get()), ParticleTypes.FLAME, false, true, false)).withoutItem(), torchProperties(14));

    public static final RegistrySupplier<TorchButton> TORCH_LEVER = registerBlock("torch_lever", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(properties, ParticleTypes.FLAME, true, false, false)).withoutItem(), torchProperties(14));
    public static final RegistrySupplier<TorchButton> WALL_TORCH_LEVER = registerBlock("wall_torch_lever", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(properties.dropsLike(TORCH_LEVER.get()), ParticleTypes.FLAME, true, true, false)), torchProperties(14));

    public static final RegistrySupplier<TorchButton> SOUL_TORCH_BUTTON = registerBlock("soul_torch_button", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(properties, ParticleTypes.SOUL_FIRE_FLAME, false, false, false)).withoutItem(), torchProperties(10));
    public static final RegistrySupplier<TorchButton> SOUL_WALL_TORCH_BUTTON = registerBlock("soul_wall_torch_button", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(properties.dropsLike(SOUL_TORCH_BUTTON.get()), ParticleTypes.SOUL_FIRE_FLAME, false, true, false)), torchProperties(10));

    public static final RegistrySupplier<TorchButton> SOUL_TORCH_LEVER = registerBlock("soul_torch_lever", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(properties, ParticleTypes.SOUL_FIRE_FLAME, true, false, false)).withoutItem(), torchProperties(10));
    public static final RegistrySupplier<TorchButton> SOUL_WALL_TORCH_LEVER = registerBlock("soul_wall_torch_lever", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(properties.dropsLike(SOUL_TORCH_LEVER.get()), ParticleTypes.SOUL_FIRE_FLAME, true, true, false)), torchProperties(10));

    public static final RegistrySupplier<TorchButton> REDSTONE_TORCH_BUTTON = registerBlock("redstone_torch_button", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(properties, new DustParticleOptions(new Vector3f(1.0F, 0.0F, 0.0F), 1.0F), false, false, true)).withoutItem(), torchProperties(7));
    public static final RegistrySupplier<TorchButton> REDSTONE_WALL_TORCH_BUTTON = registerBlock("redstone_wall_torch_button", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(properties.dropsLike(REDSTONE_TORCH_BUTTON.get()), new DustParticleOptions(new Vector3f(1.0F, 0.0F, 0.0F), 1.0F), false, true, true)), torchProperties(7));

    public static final RegistrySupplier<TorchButton> REDSTONE_TORCH_LEVER = registerBlock("redstone_torch_lever", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(properties, new DustParticleOptions(new Vector3f(1.0F, 0.0F, 0.0F), 1.0F), true, false, true)).withoutItem(), torchProperties(7));
    public static final RegistrySupplier<TorchButton> REDSTONE_WALL_TORCH_LEVER = registerBlock("redstone_wall_torch_lever", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(properties.dropsLike(REDSTONE_TORCH_LEVER.get()), new DustParticleOptions(new Vector3f(1.0F, 0.0F, 0.0F), 1.0F), true, true, true)).withoutItem(), torchProperties(7));

    /**
     * Lanterns
     */
    public static final RegistrySupplier<LanternButton> LANTERN_BUTTON = registerBlock("lantern_button", BlockEntryBuilder.ofBlock(properties ->
            new LanternButton(properties, false)), lanternProperties(15));
    public static final RegistrySupplier<LanternButton> LANTERN_LEVER = registerBlock("lantern_lever", BlockEntryBuilder.ofBlock(properties ->
            new LanternButton(properties, true)), lanternProperties(15));
    public static final RegistrySupplier<LanternButton> SOUL_LANTERN_BUTTON = registerBlock("soul_lantern_button", BlockEntryBuilder.ofBlock(properties ->
            new LanternButton(properties, false)), lanternProperties(10));
    public static final RegistrySupplier<LanternButton> SOUL_LANTERN_LEVER = registerBlock("soul_lantern_lever", BlockEntryBuilder.ofBlock(properties ->
            new LanternButton(properties, true)), lanternProperties(10));

    /**
     * Console Buttons
     */
    public static final RegistrySupplier<ConsoleButton> SMALL_CONSOLE_BUTTON = registerBlock("small_console_button", BlockEntryBuilder.ofBlock(properties ->
            new ConsoleButton(properties, ConsoleButtonType.SMALL, false)), lanternProperties(5));
    public static final RegistrySupplier<ConsoleButton> SMALL_CONSOLE_LEVER = registerBlock("small_console_lever", BlockEntryBuilder.ofBlock(properties ->
            new ConsoleButton(properties, ConsoleButtonType.SMALL, true)), lanternProperties(5));

    public static final RegistrySupplier<ConsoleButton> CONSOLE_BUTTON = registerBlock("console_button", BlockEntryBuilder.ofBlock(properties ->
            new ConsoleButton(properties, ConsoleButtonType.NORMAL, false)), lanternProperties(5));
    public static final RegistrySupplier<ConsoleButton> CONSOLE_LEVER = registerBlock("console_lever", BlockEntryBuilder.ofBlock(properties ->
            new ConsoleButton(properties, ConsoleButtonType.NORMAL, true)), lanternProperties(5));
    public static final RegistrySupplier<ConsoleButton> LARGE_CONSOLE_BUTTON = registerBlock("large_console_button", BlockEntryBuilder.ofBlock(properties ->
            new ConsoleButton(properties, ConsoleButtonType.LARGE, false)), lanternProperties(5));
    public static final RegistrySupplier<ConsoleButton> LARGE_CONSOLE_LEVER = registerBlock("large_console_lever", BlockEntryBuilder.ofBlock(properties ->
            new ConsoleButton(properties, ConsoleButtonType.LARGE, true)), lanternProperties(5));
    public static final RegistrySupplier<ConsoleButton> BIG_CONSOLE_BUTTON = registerBlock("big_console_button", BlockEntryBuilder.ofBlock(properties ->
            new ConsoleButton(properties, ConsoleButtonType.LARGE, false)), lanternProperties(5));
    public static final RegistrySupplier<ConsoleButton> BIG_CONSOLE_LEVER = registerBlock("big_console_lever", BlockEntryBuilder.ofBlock(properties ->
            new ConsoleButton(properties, ConsoleButtonType.LARGE, true)), lanternProperties(5));

    /**
     * Emergency Buttons
     */
    public static final HashMap<DyeColor, RegistrySupplier<EmergencyButton>> EMERGENCY_BUTTONS = new HashMap<>();
    public static final HashMap<DyeColor, RegistrySupplier<SafeEmergencyButton>> SAFE_EMERGENCY_BUTTONS = new HashMap<>();

    public static final RegistrySupplier<EmergencyButton> FANCY_EMERGENCY_BUTTON = registerEmergencyButton(null, "fancy");
    public static final RegistrySupplier<SafeEmergencyButton> FANCY_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton(null, "fancy");

    static {
        for (DyeColor color : DyeColor.values()) {
            registerEmergencyButton(color, color.name().toLowerCase());
            registerSafeEmergencyButton(color, color.name().toLowerCase());
        }
    }

    public static RegistrySupplier<EmergencyButton> registerEmergencyButton(DyeColor color, String name) {
        RegistrySupplier<EmergencyButton> blockRS = registerBlock(name + "_emergency_button",
                BlockEntryBuilder.ofBlock(EmergencyButton::new),
                BlockBehaviour.Properties.of().strength(0.5f).sound(SoundType.METAL));
        EMERGENCY_BUTTONS.put(color, blockRS);
        return blockRS;
    }

    public static RegistrySupplier<SafeEmergencyButton> registerSafeEmergencyButton(DyeColor color, String name) {
        RegistrySupplier<SafeEmergencyButton> blockRS = registerBlock(name + "_safe_emergency_button",
                BlockEntryBuilder.ofBlock(SafeEmergencyButton::new),
                BlockBehaviour.Properties.of().strength(0.5f).sound(SoundType.METAL));
        SAFE_EMERGENCY_BUTTONS.put(color, blockRS);
        return blockRS;
    }

    /**
     * Doorbells
     */
    public static final RegistrySupplier<Doorbell> DOORBELL = registerBlock("doorbell",
            BlockEntryBuilder.ofBlock((properties) -> new Doorbell(properties, false)),
            BlockBehaviour.Properties.of().strength(0.5f).sound(SoundType.METAL));
    public static final RegistrySupplier<Doorbell> DOORBELL_BUTTON = registerBlock("doorbell_button",
            BlockEntryBuilder.ofBlock((properties) -> new Doorbell(properties, true)),
            BlockBehaviour.Properties.of().strength(0.5f).sound(SoundType.METAL));

    /**
     * Lamp buttons
     */
    public static final RegistrySupplier<LampButton> LAMP_BUTTON = registerBlock("lamp_button",
            BlockEntryBuilder.ofBlock((properties) -> new LampButton(BlockSetType.STONE, properties, false)),
            lampProperties(15));

    public static final RegistrySupplier<LampButton> LAMP_LEVER = registerBlock("lamp_lever",
            BlockEntryBuilder.ofBlock((properties) -> new LampButton(BlockSetType.STONE, properties, true)),
            lampProperties(15));

    /**
     * Letter buttons
     */
    public static final RegistrySupplier<LetterButton> LETTER_BUTTON = registerBlock("letter_button",
            BlockEntryBuilder.ofBlock((properties) -> new LetterButton(properties, false)),
            BlockBehaviour.Properties.of().strength(0.5f).sound(SoundType.METAL));
    public static final RegistrySupplier<LetterButton> LETTER_LEVER = registerBlock("letter_lever",
            BlockEntryBuilder.ofBlock((properties) -> new LetterButton(properties, true)),
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
    private static <T extends Block> RegistrySupplier<T> registerBlock(String blockId, BlockEntryBuilder<T> builder, BlockBehaviour.Properties properties) {
        RegistrySupplier<T> registry = builder.register(getResource(blockId), properties);

        ALL_BUTTONS.put(blockId, registry);
        return registry;
    }


    @FunctionalInterface
    private interface LargeButtonConstructor<T extends Block> {
        T create(BlockBehaviour.Properties properties, boolean large);
    }

    private static <T extends Block> LargeVariantSupplier<T> registerLargeVariantButton(
            String type,
            LargeButtonConstructor<T> constructor
    ) {
        LargeVariantSupplier<T> supplier = LargeVariantSupplier.registerVariants((large) -> {
            String blockId = type + (large ? "_large_button" : "_button");

            RegistrySupplier<T> registry = registerBlock(
                    blockId,
                    BlockEntryBuilder.ofBlock(properties -> constructor.create(properties, large)),
                    getDefaultProperties()
            );

            ALL_BUTTONS.put(blockId, registry);
            return registry;
        });
        SMALL_LARGE_BUTTONS.put(type, supplier);
        return supplier;
    }

    public static void register() {
        LOGGER.info("Registering Blocks for Infinity Buttons");
    }
}
