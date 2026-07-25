package nl.teamdiopside.infinitybuttons.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
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
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButtonShape;
import nl.teamdiopside.infinitybuttons.block.faced4.TorchButton;
import nl.teamdiopside.infinitybuttons.block.faced6.ConsoleButton;
import nl.teamdiopside.infinitybuttons.block.faced6.ConsoleButtonType;
import nl.teamdiopside.infinitybuttons.block.faced6.LampButton;
import nl.teamdiopside.infinitybuttons.block.faced6.LetterButton;
import nl.teamdiopside.infinitybuttons.block.faced6.normal.*;
import nl.teamdiopside.infinitybuttons.block.simple.LanternButton;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils.LargeVariantSupplier;
import nl.teamdiopside.infinitybuttons.util.BiHashMap;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.LOGGER;
import static nl.teamdiopside.infinitybuttons.InfinityButtons.MOD_ID;

public class IBBlocks {
    public static final IBBlocks INSTANCE = new IBBlocks();

    public static final HashMap<String, RegistrySupplier<? extends Block>> ALL_BUTTONS = new HashMap<>();

    public static final HashMap<BlockSetType, RegistrySupplier<NormalButton>> DEFAULT_LARGE_BUTTONS = new HashMap<>();

    public static final HashMap<ResourceLocation, LargeVariantSupplier<? extends Block>> SMALL_LARGE_BUTTONS = new HashMap<>();
    public static final HashMap<ResourceLocation, RegistrySupplier<SecretButton>> SECRET_BUTTONS = new HashMap<>();
    public static final BiHashMap<CopperButtonType, WeatheringCopper.WeatherState, LargeVariantSupplier<CopperButton>> COPPER_BUTTONS = new BiHashMap<>();

    public static final HashMap<DyeColor, RegistrySupplier<EmergencyButton>> EMERGENCY_BUTTONS = new HashMap<>();
    public static final HashMap<DyeColor, RegistrySupplier<SafeEmergencyButton>> SAFE_EMERGENCY_BUTTONS = new HashMap<>();

    public static ResourceLocation material(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    /**
     * Stone Buttons
     */
    public static final HashMap<String, LargeVariantSupplier<NormalButton>> STONE_BUTTONS = new HashMap<>();

    public static final LargeVariantSupplier<NormalButton> DEEPSLATE_BUTTON = INSTANCE.registerStoneButton(material(Blocks.DEEPSLATE), "deepslate", SoundType.DEEPSLATE);
    public static final LargeVariantSupplier<NormalButton> GRANITE_BUTTON = INSTANCE.registerStoneButton(material(Blocks.GRANITE), "granite", SoundType.STONE);
    public static final LargeVariantSupplier<NormalButton> DIORITE_BUTTON = INSTANCE.registerStoneButton(material(Blocks.DIORITE), "diorite", SoundType.STONE);
    public static final LargeVariantSupplier<NormalButton> ANDESITE_BUTTON = INSTANCE.registerStoneButton(material(Blocks.ANDESITE), "andesite", SoundType.STONE);
    public static final LargeVariantSupplier<NormalButton> CALCITE_BUTTON = INSTANCE.registerStoneButton(material(Blocks.CALCITE), "calcite", SoundType.CALCITE);
    public static final LargeVariantSupplier<NormalButton> TUFF_BUTTON = INSTANCE.registerStoneButton(material(Blocks.TUFF), "tuff", SoundType.TUFF);
    public static final LargeVariantSupplier<NormalButton> DRIPSTONE_BUTTON = INSTANCE.registerStoneButton(material(Blocks.DRIPSTONE_BLOCK), "dripstone", SoundType.DRIPSTONE_BLOCK);

    public static final HashMap<String, LargeVariantSupplier<OneUseButton>> ONE_USE_BUTTONS = new HashMap<>();
    public static final HashMap<DyeColor, LargeVariantSupplier<OneUseButton>> CONCRETE_POWDER_BUTTONS = new HashMap<>(); // Subset of above

    public static final LargeVariantSupplier<OneUseButton> SAND_BUTTON = INSTANCE.registerOneUseButton(material(Blocks.SAND), "sand");
    public static final LargeVariantSupplier<OneUseButton> RED_SAND_BUTTON = INSTANCE.registerOneUseButton(material(Blocks.RED_SAND), "red_sand");
    public static final LargeVariantSupplier<OneUseButton> GRAVEL_BUTTON = INSTANCE.registerOneUseButton(material(Blocks.GRAVEL), "gravel"); // Special case in-method

    /**
     * Super-duper special buttons
     */
    public static final LargeVariantSupplier<RandomTimeButton> EMERALD_BUTTON = INSTANCE.registerLargeVariantButton(material(Blocks.EMERALD_BLOCK), "emerald",
            (properties, large) -> new RandomTimeButton(BlockSetType.STONE, properties, large, false), "emerald_button");

    public static final LargeVariantSupplier<WaterloggableButton> PRISMARINE_BUTTON = INSTANCE.registerLargeVariantButton(material(Blocks.PRISMARINE), "prismarine",
            (properties, large) -> new WaterloggableButton(BlockSetType.STONE, properties, large, false), "prismarine_button");
    public static final LargeVariantSupplier<WaterloggableButton> DARK_PRISMARINE_BUTTON = INSTANCE.registerLargeVariantButton(material(Blocks.DARK_PRISMARINE), "dark_prismarine",
            (properties, large) -> new WaterloggableButton(BlockSetType.STONE, properties, large, false), "prismarine_button");
    public static final LargeVariantSupplier<WaterloggableButton> PRISMARINE_BRICK_BUTTON = INSTANCE.registerLargeVariantButton(material(Blocks.PRISMARINE_BRICKS), "prismarine_brick",
            (properties, large) -> new WaterloggableButton(BlockSetType.STONE, properties, large, false), "prismarine_button");

    public static final LargeVariantSupplier<SparklingButton> DIAMOND_BUTTON = INSTANCE.registerLargeVariantButton(material(Blocks.DIAMOND_BLOCK), "diamond",
            (properties, large) -> new SparklingButton(BlockSetType.STONE, properties, large, false), "diamond_button");

    public static final LargeVariantSupplier<ArrowOnlyButton> IRON_BUTTON = INSTANCE.registerLargeVariantButton(material(Blocks.IRON_BLOCK), "iron",
            (properties, large) -> new ArrowOnlyButton(BlockSetType.IRON, properties, large, false, SoundType.METAL), "arrow_button");
    public static final LargeVariantSupplier<ArrowOnlyButton> GOLD_BUTTON = INSTANCE.registerLargeVariantButton(material(Blocks.GOLD_BLOCK), "gold",
            (properties, large) -> new ArrowOnlyButton(BlockSetType.GOLD, properties, large, false, SoundType.STONE), "arrow_button");

    public static final LargeVariantSupplier<JammedButton> NETHERITE_BUTTON = INSTANCE.registerLargeVariantButton(material(Blocks.NETHERITE_BLOCK), "netherite",
            (properties, large) -> new JammedButton(BlockSetType.STONE, properties, large), "netherite_button");

    /**
     * Secret Buttons
     */
    public static final RegistrySupplier<SecretButton> BOOKSHELF_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "bookshelf_secret_button", SecretButtonShape.BOOKSHELF, Blocks.BOOKSHELF );
    public static final RegistrySupplier<SecretButton> BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "brick_secret_button", SecretButtonShape.FULL_BLOCK_BRICK, Blocks.BRICKS );
    public static final RegistrySupplier<SecretButton> STONE_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "stone_brick_secret_button", SecretButtonShape.BIG_BRICK, Blocks.STONE_BRICKS );
    public static final RegistrySupplier<SecretButton> MOSSY_STONE_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "mossy_stone_brick_secret_button", SecretButtonShape.BIG_BRICK, Blocks.MOSSY_STONE_BRICKS );
    public static final RegistrySupplier<SecretButton> CRACKED_STONE_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "cracked_stone_brick_secret_button", SecretButtonShape.BIG_BRICK, Blocks.CRACKED_STONE_BRICKS );
    public static final RegistrySupplier<SecretButton> CHISELED_STONE_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "chiseled_stone_brick_secret_button", SecretButtonShape.CHISELED_STONE_BRICK, Blocks.CHISELED_STONE_BRICKS );
    public static final RegistrySupplier<SecretButton> DEEPSLATE_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "deepslate_brick_secret_button", SecretButtonShape.BIG_BRICK, Blocks.DEEPSLATE_BRICKS );
    public static final RegistrySupplier<SecretButton> CRACKED_DEEPSLATE_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "cracked_deepslate_brick_secret_button", SecretButtonShape.BIG_BRICK, Blocks.CRACKED_DEEPSLATE_BRICKS );
    public static final RegistrySupplier<SecretButton> DEEPSLATE_TILE_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "deepslate_tile_secret_button", SecretButtonShape.DEEPSLATE_TILE, Blocks.DEEPSLATE_TILES );
    public static final RegistrySupplier<SecretButton> CRACKED_DEEPSLATE_TILE_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "cracked_deepslate_tile_secret_button", SecretButtonShape.DEEPSLATE_TILE, Blocks.CRACKED_DEEPSLATE_TILES );

    public static final RegistrySupplier<SecretButton> OAK_PLANK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "oak_plank_secret_button", SecretButtonShape.PLANK, Blocks.OAK_PLANKS );
    public static final RegistrySupplier<SecretButton> SPRUCE_PLANK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "spruce_plank_secret_button", SecretButtonShape.PLANK, Blocks.SPRUCE_PLANKS );
    public static final RegistrySupplier<SecretButton> BIRCH_PLANK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "birch_plank_secret_button", SecretButtonShape.PLANK, Blocks.BIRCH_PLANKS );
    public static final RegistrySupplier<SecretButton> JUNGLE_PLANK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "jungle_plank_secret_button", SecretButtonShape.PLANK, Blocks.JUNGLE_PLANKS );
    public static final RegistrySupplier<SecretButton> ACACIA_PLANK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "acacia_plank_secret_button", SecretButtonShape.PLANK, Blocks.ACACIA_PLANKS );
    public static final RegistrySupplier<SecretButton> DARK_OAK_PLANK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "dark_oak_plank_secret_button", SecretButtonShape.PLANK, Blocks.DARK_OAK_PLANKS );
    public static final RegistrySupplier<SecretButton> MANGROVE_PLANK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "mangrove_plank_secret_button", SecretButtonShape.PLANK, Blocks.MANGROVE_PLANKS );
    public static final RegistrySupplier<SecretButton> CHERRY_PLANK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "cherry_plank_secret_button", SecretButtonShape.PLANK, Blocks.CHERRY_PLANKS );
    public static final RegistrySupplier<SecretButton> CRIMSON_PLANK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "crimson_plank_secret_button", SecretButtonShape.PLANK, Blocks.CRIMSON_PLANKS );
    public static final RegistrySupplier<SecretButton> WARPED_PLANK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "warped_plank_secret_button", SecretButtonShape.PLANK, Blocks.WARPED_PLANKS );
    public static final RegistrySupplier<SecretButton> BAMBOO_PLANK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "bamboo_plank_secret_button", SecretButtonShape.PLANK, Blocks.BAMBOO_PLANKS );

    public static final RegistrySupplier<SecretButton> MUD_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "mud_brick_secret_button", SecretButtonShape.MUD_BRICK, Blocks.MUD_BRICKS );
    public static final RegistrySupplier<SecretButton> END_STONE_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "end_stone_brick_secret_button", SecretButtonShape.BIG_BRICK, Blocks.END_STONE_BRICKS );
    public static final RegistrySupplier<SecretButton> PURPUR_BLOCK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "purpur_block_secret_button", SecretButtonShape.EIGHTS_TILES, Blocks.PURPUR_BLOCK );
    public static final RegistrySupplier<SecretButton> QUARTZ_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "quartz_brick_secret_button", SecretButtonShape.BIG_BRICK, Blocks.QUARTZ_BRICKS );
    public static final RegistrySupplier<SecretButton> DARK_PRISMARINE_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "dark_prismarine_secret_button", SecretButtonShape.FULL_BLOCK_BRICK, Blocks.DARK_PRISMARINE );
    public static final RegistrySupplier<SecretButton> POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "polished_blackstone_brick_secret_button", SecretButtonShape.BIG_BRICK, Blocks.POLISHED_BLACKSTONE_BRICKS );
    public static final RegistrySupplier<SecretButton> CRACKED_POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "cracked_polished_blackstone_brick_secret_button", SecretButtonShape.BIG_BRICK, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS );
    public static final RegistrySupplier<SecretButton> CHISELED_POLISHED_BLACKSTONE_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "chiseled_polished_blackstone_secret_button", SecretButtonShape.CHISELED_STONE_BRICK, Blocks.CHISELED_POLISHED_BLACKSTONE );
    public static final RegistrySupplier<SecretButton> NETHER_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "nether_brick_secret_button", SecretButtonShape.FULL_BLOCK_BRICK, Blocks.NETHER_BRICKS );
    public static final RegistrySupplier<SecretButton> CRACKED_NETHER_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "cracked_nether_brick_secret_button", SecretButtonShape.FULL_BLOCK_BRICK, Blocks.CRACKED_NETHER_BRICKS );
    public static final RegistrySupplier<SecretButton> CHISELED_NETHER_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "chiseled_nether_brick_secret_button", SecretButtonShape.CHISELED_NETHER_BRICK, Blocks.CHISELED_NETHER_BRICKS );
    public static final RegistrySupplier<SecretButton> RED_NETHER_BRICK_SECRET_BUTTON = INSTANCE.registerSecretButton(
            "red_nether_brick_secret_button", SecretButtonShape.FULL_BLOCK_BRICK, Blocks.RED_NETHER_BRICKS );

    /**
     * Torches
     */
    public static final RegistrySupplier<TorchButton> TORCH_BUTTON = INSTANCE.registerBlock("torch_button", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(torchProperties(properties, 14), ParticleTypes.FLAME, false, false, material(Blocks.TORCH))
    ).withoutItem(), null);
    public static final RegistrySupplier<TorchButton> WALL_TORCH_BUTTON = INSTANCE.registerBlock("wall_torch_button", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(torchProperties(properties, 14).dropsLike(TORCH_BUTTON.get()), ParticleTypes.FLAME, false, true, material(Blocks.TORCH))
    ).withoutItem(), null);

    public static final RegistrySupplier<TorchButton> TORCH_LEVER = INSTANCE.registerBlock("torch_lever", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(torchProperties(properties, 14), ParticleTypes.FLAME, true, false, material(Blocks.TORCH))
    ).withoutItem(), null);
    public static final RegistrySupplier<TorchButton> WALL_TORCH_LEVER = INSTANCE.registerBlock("wall_torch_lever", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(torchProperties(properties, 14).dropsLike(TORCH_LEVER.get()), ParticleTypes.FLAME, true, true, material(Blocks.TORCH))
    ), null);

    public static final RegistrySupplier<TorchButton> SOUL_TORCH_BUTTON = INSTANCE.registerBlock("soul_torch_button", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(torchProperties(properties, 10), ParticleTypes.SOUL_FIRE_FLAME, false, false, material(Blocks.SOUL_TORCH))
    ).withoutItem(), null);
    public static final RegistrySupplier<TorchButton> SOUL_WALL_TORCH_BUTTON = INSTANCE.registerBlock("soul_wall_torch_button", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(torchProperties(properties, 10).dropsLike(SOUL_TORCH_BUTTON.get()), ParticleTypes.SOUL_FIRE_FLAME, false, true, material(Blocks.SOUL_TORCH))
    ), null);

    public static final RegistrySupplier<TorchButton> SOUL_TORCH_LEVER = INSTANCE.registerBlock("soul_torch_lever", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(torchProperties(properties, 10), ParticleTypes.SOUL_FIRE_FLAME, true, false, material(Blocks.SOUL_TORCH))
    ).withoutItem(), null);
    public static final RegistrySupplier<TorchButton> SOUL_WALL_TORCH_LEVER = INSTANCE.registerBlock("soul_wall_torch_lever", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(torchProperties(properties, 10).dropsLike(SOUL_TORCH_LEVER.get()), ParticleTypes.SOUL_FIRE_FLAME, true, true, material(Blocks.SOUL_TORCH))
    ), null);

    public static final RegistrySupplier<TorchButton> REDSTONE_TORCH_BUTTON = INSTANCE.registerBlock("redstone_torch_button", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(torchProperties(properties, 7), new DustParticleOptions(new Vector3f(1.0F, 0.0F, 0.0F), 1.0F), false, false, material(Blocks.REDSTONE_TORCH))
    ).withoutItem(), null);
    public static final RegistrySupplier<TorchButton> REDSTONE_WALL_TORCH_BUTTON = INSTANCE.registerBlock("redstone_wall_torch_button", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(torchProperties(properties, 7).dropsLike(REDSTONE_TORCH_BUTTON.get()), new DustParticleOptions(new Vector3f(1.0F, 0.0F, 0.0F), 1.0F), false, true, material(Blocks.REDSTONE_TORCH))
    ), null);

    public static final RegistrySupplier<TorchButton> REDSTONE_TORCH_LEVER = INSTANCE.registerBlock("redstone_torch_lever", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(torchProperties(properties, 7), new DustParticleOptions(new Vector3f(1.0F, 0.0F, 0.0F), 1.0F), true, false, material(Blocks.REDSTONE_TORCH))
    ).withoutItem(), null);
    public static final RegistrySupplier<TorchButton> REDSTONE_WALL_TORCH_LEVER = INSTANCE.registerBlock("redstone_wall_torch_lever", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(torchProperties(properties, 7).dropsLike(REDSTONE_TORCH_LEVER.get()), new DustParticleOptions(new Vector3f(1.0F, 0.0F, 0.0F), 1.0F), true, true, material(Blocks.REDSTONE_TORCH))
    ).withoutItem(), null);

    /**
     * Lanterns
     */
    public static final RegistrySupplier<LanternButton> LANTERN_BUTTON = INSTANCE.registerBlock("lantern_button", BlockEntryBuilder.ofBlock(properties ->
            new LanternButton(lanternProperties(properties, 15), false, material(Blocks.LANTERN))), "lantern_button");
    public static final RegistrySupplier<LanternButton> LANTERN_LEVER = INSTANCE.registerBlock("lantern_lever", BlockEntryBuilder.ofBlock(properties ->
            new LanternButton(lanternProperties(properties, 15), true, material(Blocks.LANTERN))), "lantern_button");
    public static final RegistrySupplier<LanternButton> SOUL_LANTERN_BUTTON = INSTANCE.registerBlock("soul_lantern_button", BlockEntryBuilder.ofBlock(properties ->
            new LanternButton(lanternProperties(properties, 10), false, material(Blocks.SOUL_LANTERN))), "lantern_button");
    public static final RegistrySupplier<LanternButton> SOUL_LANTERN_LEVER = INSTANCE.registerBlock("soul_lantern_lever", BlockEntryBuilder.ofBlock(properties ->
            new LanternButton(lanternProperties(properties, 10), true, material(Blocks.SOUL_LANTERN))), "lantern_button");

    /**
     * Console Buttons
     */
    public static final RegistrySupplier<ConsoleButton> SMALL_CONSOLE_BUTTON = INSTANCE.registerBlock("small_console_button", BlockEntryBuilder.ofBlock(properties ->
            new ConsoleButton(lanternProperties(properties, 5), ConsoleButtonType.SMALL, false)), null);
    public static final RegistrySupplier<ConsoleButton> SMALL_CONSOLE_LEVER = INSTANCE.registerBlock("small_console_lever", BlockEntryBuilder.ofBlock(properties ->
            new ConsoleButton(lanternProperties(properties, 5), ConsoleButtonType.SMALL, true)), null);
    public static final RegistrySupplier<ConsoleButton> CONSOLE_BUTTON = INSTANCE.registerBlock("console_button", BlockEntryBuilder.ofBlock(properties ->
            new ConsoleButton(lanternProperties(properties, 5), ConsoleButtonType.NORMAL, false)), null);
    public static final RegistrySupplier<ConsoleButton> CONSOLE_LEVER = INSTANCE.registerBlock("console_lever", BlockEntryBuilder.ofBlock(properties ->
            new ConsoleButton(lanternProperties(properties, 5), ConsoleButtonType.NORMAL, true)), null);
    public static final RegistrySupplier<ConsoleButton> LARGE_CONSOLE_BUTTON = INSTANCE.registerBlock("large_console_button", BlockEntryBuilder.ofBlock(properties ->
            new ConsoleButton(lanternProperties(properties, 5), ConsoleButtonType.LARGE, false)), null);
    public static final RegistrySupplier<ConsoleButton> LARGE_CONSOLE_LEVER = INSTANCE.registerBlock("large_console_lever", BlockEntryBuilder.ofBlock(properties ->
            new ConsoleButton(lanternProperties(properties, 5), ConsoleButtonType.LARGE, true)), null);
    public static final RegistrySupplier<ConsoleButton> BIG_CONSOLE_BUTTON = INSTANCE.registerBlock("big_console_button", BlockEntryBuilder.ofBlock(properties ->
            new ConsoleButton(lanternProperties(properties, 5), ConsoleButtonType.LARGE, false)), null);
    public static final RegistrySupplier<ConsoleButton> BIG_CONSOLE_LEVER = INSTANCE.registerBlock("big_console_lever", BlockEntryBuilder.ofBlock(properties ->
            new ConsoleButton(lanternProperties(properties, 5), ConsoleButtonType.LARGE, true)), null);

    /**
     * Doorbells
     */
    public static final RegistrySupplier<Doorbell> DOORBELL = INSTANCE.registerBlock("doorbell", BlockEntryBuilder.ofBlock((properties) ->
            new Doorbell(properties.noCollission().strength(0.5f).sound(SoundType.METAL), false)),null);
    public static final RegistrySupplier<Doorbell> DOORBELL_BUTTON = INSTANCE.registerBlock("doorbell_button", BlockEntryBuilder.ofBlock((properties) ->
            new Doorbell(properties.noCollission().strength(0.5f).sound(SoundType.METAL), true)), null);

    /**
     * Lamp buttons
     */
    public static final RegistrySupplier<LampButton> LAMP_BUTTON = INSTANCE.registerBlock("lamp_button", BlockEntryBuilder.ofBlock((properties) ->
            new LampButton(BlockSetType.STONE, lampProperties(properties, 15), false)), null);

    public static final RegistrySupplier<LampButton> LAMP_LEVER = INSTANCE.registerBlock("lamp_lever", BlockEntryBuilder.ofBlock((properties) ->
            new LampButton(BlockSetType.STONE, lampProperties(properties, 15), true)),null);

    /**
     * Letter buttons
     */
    public static final RegistrySupplier<LetterButton> LETTER_BUTTON = INSTANCE.registerBlock("letter_button", BlockEntryBuilder.ofBlock((properties) ->
            new LetterButton(properties.strength(0.5f).noCollission().sound(SoundType.METAL), false)), null);

    public static final RegistrySupplier<LetterButton> LETTER_LEVER = INSTANCE.registerBlock("letter_lever", BlockEntryBuilder.ofBlock((properties) ->
            new LetterButton(properties.strength(0.5f).noCollission().sound(SoundType.METAL), true)), null);


    /**
     * Emergency Buttons
     */
    public static final RegistrySupplier<EmergencyButton> FANCY_EMERGENCY_BUTTON = INSTANCE.registerEmergencyButton(null, "fancy");
    public static final RegistrySupplier<SafeEmergencyButton> FANCY_SAFE_EMERGENCY_BUTTON = INSTANCE.registerSafeEmergencyButton(null, "fancy");

    static {
        for (DyeColor color : DyeColor.values()) {
            INSTANCE.registerEmergencyButton(color, color.name().toLowerCase());
            INSTANCE.registerSafeEmergencyButton(color, color.name().toLowerCase());

            INSTANCE.registerConcretePowderButton(color, color.name().toLowerCase() + "_concrete_powder");
        }

        INSTANCE.registerDefaultLargeButtons();
        INSTANCE.registerCopperButtons();
    }

    protected LargeVariantSupplier<NormalButton> registerStoneButton(ResourceLocation material, String type, SoundType soundType) {
        LargeVariantSupplier<nl.teamdiopside.infinitybuttons.block.faced6.normal.NormalButton> supplier = registerLargeVariantButton(material, type,
                (properties, large) -> new nl.teamdiopside.infinitybuttons.block.faced6.normal.NormalButton(BlockSetType.STONE, 20, properties, large, false, soundType), null);

        STONE_BUTTONS.put(type, supplier);
        SMALL_LARGE_BUTTONS.put(material, supplier);
        return supplier;
    }

    protected LargeVariantSupplier<OneUseButton> registerOneUseButton(ResourceLocation material, String type) {
        LargeVariantSupplier<OneUseButton> supplier = registerLargeVariantButton(material, type,
                (properties, large) -> new OneUseButton(BlockSetType.STONE, properties, large, false, type.equals("gravel")), "falling_button");

        ONE_USE_BUTTONS.put(type, supplier);
        return supplier;
    }

    protected void registerConcretePowderButton(DyeColor color, String type) {
        ResourceLocation material = ResourceLocation.withDefaultNamespace(color + "_concrete_powder");
        LargeVariantSupplier<OneUseButton> supplier = registerOneUseButton(material, type);

        CONCRETE_POWDER_BUTTONS.put(color, supplier);
    }

    protected LargeVariantSupplier<CopperButton> registerCopperButton(CopperButtonType copperButtonType, WeatheringCopper.WeatherState weatherState) {
        String state = weatherState == WeatheringCopper.WeatherState.UNAFFECTED ? "copper" : weatherState.getSerializedName() + "_copper";
        String type = copperButtonType == CopperButtonType.NORMAL ? state : copperButtonType.getName() + "_" + state;

        return LargeVariantSupplier.registerVariants((large) -> registerBlock(
                type + (large ? "_large_button" : "_button"), BlockEntryBuilder.ofBlock((properties) ->
                        new CopperButton(properties.sound(SoundType.COPPER).requiresCorrectToolForDrops(), large, weatherState, copperButtonType)),

                "sticky_copper_button",
                ignored -> copperButtonType == CopperButtonType.STICKY
        ));
    }

    protected BiHashMap<CopperButtonType, WeatheringCopper.WeatherState, LargeVariantSupplier<CopperButton>> registerCopperButtons() {
        BiHashMap<CopperButtonType, WeatheringCopper.WeatherState, LargeVariantSupplier<CopperButton>> typeMap = new BiHashMap<>();
        for (CopperButtonType type : CopperButtonType.values()) {
            for (WeatheringCopper.WeatherState state : WeatheringCopper.WeatherState.values()) {
                var registry = registerCopperButton(type, state);

                COPPER_BUTTONS.put(type, state, registry);
            }
        }
        return typeMap;
    }

    protected void registerDefaultLargeButtons() {
        for (BlockSetType type : BlockSetType.values().toList()) {
            if (type == BlockSetType.COPPER || type == BlockSetType.GOLD || type == BlockSetType.IRON) continue;

            var registry = registerBlock(type.name() + "_large_button", BlockEntryBuilder.ofBlock((properties) ->
                    new NormalButton(type, type == BlockSetType.STONE ? 20 : 30, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON),
                            true, false)), null);

            DEFAULT_LARGE_BUTTONS.put(type, registry);
        }
    }

    protected RegistrySupplier<SecretButton> registerSecretButton(String blockId, SecretButtonShape type, Block originalBlock) {
        return registerSecretButton(blockId, type, BlockBehaviour.Properties.ofFullCopy(originalBlock), material(originalBlock));
    }

    protected RegistrySupplier<SecretButton> registerSecretButton(String blockId, SecretButtonShape type, BlockBehaviour.Properties properties, ResourceLocation material ) {
        RegistrySupplier<SecretButton> blockRS = registerBlock(
                blockId,
                BlockEntryBuilder.ofBlock(
                        ignored -> new SecretButton(properties, type, material)
                ),
                "secret_button"
        );
        SECRET_BUTTONS.put(material, blockRS);
        return blockRS;
    }

    public RegistrySupplier<EmergencyButton> registerEmergencyButton(DyeColor color, String name) {
        var registry = registerBlock(name + "_emergency_button", BlockEntryBuilder.ofBlock(properties ->
                new EmergencyButton(properties.strength(0.5f).sound(SoundType.METAL))),"emergency_button");

        EMERGENCY_BUTTONS.put(color, registry);
        return registry;
    }

    public RegistrySupplier<SafeEmergencyButton> registerSafeEmergencyButton(DyeColor color, String name) {
        var registry = registerBlock(name + "_safe_emergency_button", BlockEntryBuilder.ofBlock(properties ->
                new SafeEmergencyButton(properties.strength(0.5f).sound(SoundType.METAL))),"safe_emergency_button");

        SAFE_EMERGENCY_BUTTONS.put(color, registry);
        return registry;
    }

    /**
     * Properties
     */
    protected static BlockBehaviour.Properties getDefaultProperties() {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON);
    }

    protected static BlockBehaviour.Properties lampProperties(BlockBehaviour.Properties properties, int light) {
        return properties.lightLevel(litBlockEmission(light)).sound(SoundType.GLASS).pushReaction(PushReaction.DESTROY);
    }

    protected static BlockBehaviour.Properties torchProperties(BlockBehaviour.Properties properties, int light) {
        return properties.noCollission().strength(0.3f).instabreak().lightLevel(litBlockEmission(light)).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY);
    }

    protected static BlockBehaviour.Properties lanternProperties(BlockBehaviour.Properties properties, int light) {
        return properties.lightLevel((p) -> light).sound(SoundType.LANTERN)
                .pushReaction(PushReaction.DESTROY).requiresCorrectToolForDrops().strength(3.5f);
    }

    protected static ToIntFunction<BlockState> litBlockEmission(int light) {
        return blockState -> blockState.getValue(BlockStateProperties.POWERED) ? light : 0;
    }

    /**
     * Base Registry Functions
     */
    protected <T extends Block> RegistrySupplier<T> registerBlock(String blockId, BlockEntryBuilder<T> builder, String tooltipKey) {
        return registerBlock(blockId, builder, tooltipKey, ignored -> tooltipKey != null);
    }

    protected <T extends Block> RegistrySupplier<T> registerBlock(String blockId, BlockEntryBuilder<T> builder, String tooltipKey, Function<ItemStack, Boolean> tooltipCondition) {
        RegistrySupplier<T> registry = builder.withTooltip(TooltipBuilder
                .builder("infinitybuttons.tooltip." + tooltipKey)
                .setTooltipClass(HoldKeyTooltip.class)
                .setTooltipVisible(tooltipCondition)
                .withStyle(ChatFormatting.GRAY)
        ).register(ResourceLocation.fromNamespaceAndPath(MOD_ID, blockId), getDefaultProperties());

        ALL_BUTTONS.put(blockId, registry);
        return registry;
    }

    @FunctionalInterface
    protected interface LargeButtonConstructor<T extends Block> {
        T create(BlockBehaviour.Properties properties, boolean large);
    }

    protected <T extends Block> LargeVariantSupplier<T> registerLargeVariantButton(ResourceLocation material, String type, LargeButtonConstructor<T> constructor, String tooltipKey) {
        LargeVariantSupplier<T> supplier = LargeVariantSupplier.registerVariants((large) -> {
            String blockId = type + (large ? "_large_button" : "_button");

            RegistrySupplier<T> registry = registerBlock(
                    blockId,
                    BlockEntryBuilder.ofBlock(properties -> constructor.create(properties, large)),
                    tooltipKey
            );

            ALL_BUTTONS.put(blockId, registry);
            return registry;
        });
        SMALL_LARGE_BUTTONS.put(material, supplier);
        return supplier;
    }

    public static void register() {
        LOGGER.info("Registering Blocks for Infinity Buttons");
    }
}
