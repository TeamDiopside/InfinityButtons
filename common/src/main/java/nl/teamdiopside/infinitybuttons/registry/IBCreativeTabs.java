package nl.teamdiopside.infinitybuttons.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import nl.teamdiopside.diopside.registry.DiopsideCreativeTabs;
import nl.teamdiopside.infinitybuttons.InfinityButtons;
import nl.teamdiopside.infinitybuttons.block.faced6.normal.CopperButtonType;

import java.util.function.Supplier;

import static nl.teamdiopside.diopside.registry.RegistryUtils.sortedDyeColors;
import static nl.teamdiopside.infinitybuttons.InfinityButtons.LOGGER;
import static nl.teamdiopside.infinitybuttons.InfinityButtons.getResource;

public class IBCreativeTabs {

    public static final RegistrySupplier<CreativeModeTab> INFINITYBUTTONS = registerTab(tabSupplier());

    /**
     * @return The supplier for the Infinity Buttons creative mode tab.
     */
    private static Supplier<CreativeModeTab> tabSupplier() {
        return DiopsideCreativeTabs.INSTANCE.buildTab(builder ->
                builder.icon(() -> new ItemStack(IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.OAK).get()))
                        .displayItems(itemsGenerator())
                        .title(Component.translatable("itemGroup.infinityButtonsTab"))
                        .build());
    }

    /**
     * @return The display items generator for the Infinity Buttons tab.
     */
    private static CreativeModeTab.DisplayItemsGenerator itemsGenerator() {
        return (features, output) -> {
            addWoodenButtons(output, false);
            addStoneButtons(output, false);
            addCopperButtons(output, false);
            addSpecialButtons(output, false);
            addOneUseButtons(output, false);

            addWoodenButtons(output, true);
            addStoneButtons(output, true);
            addCopperButtons(output, true);
            addSpecialButtons(output, true);
            addOneUseButtons(output, true);

            addEmergencyButtons(output);
            addSecretButtons(output);
            addCoolButtons(output);
            addTorchButtons(output);
        };
    }

    /**
     * Adds wooden buttons to the creative tab.
     * @param output The output instance to add the blocks to.
     * @param large Specifies if the small or large variant should be added.
     */
    public static void addWoodenButtons(CreativeModeTab.Output output, boolean large) {
        if (!large) {
            addBlock(output, Blocks.OAK_BUTTON);
            addBlock(output, Blocks.SPRUCE_BUTTON);
            addBlock(output, Blocks.BIRCH_BUTTON);
            addBlock(output, Blocks.JUNGLE_BUTTON);
            addBlock(output, Blocks.ACACIA_BUTTON);
            addBlock(output, Blocks.DARK_OAK_BUTTON);
            addBlock(output, Blocks.MANGROVE_BUTTON);
            addBlock(output, Blocks.CHERRY_BUTTON);
            addBlock(output, Blocks.BAMBOO_BUTTON);
            addBlock(output, Blocks.CRIMSON_BUTTON);
            addBlock(output, Blocks.WARPED_BUTTON);
        } else {
            addBlock(output, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.OAK).get());
            addBlock(output, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.SPRUCE).get());
            addBlock(output, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.BIRCH).get());
            addBlock(output, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.JUNGLE).get());
            addBlock(output, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.ACACIA).get());
            addBlock(output, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.DARK_OAK).get());
            addBlock(output, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.MANGROVE).get());
            addBlock(output, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.CHERRY).get());
            addBlock(output, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.BAMBOO).get());
            addBlock(output, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.CRIMSON).get());
            addBlock(output, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.WARPED).get());
        }
    }

    /**
     * Adds stone buttons to the creative tab.
     * @param output The output instance to add the blocks to.
     * @param large Specifies if the small or large variant should be added.
     */
    public static void addStoneButtons(CreativeModeTab.Output output, boolean large) {
        addBlock(output, large ? IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.STONE).get() : Blocks.STONE_BUTTON);
        addBlock(output, IBBlocks.DEEPSLATE_BUTTON.get(large));
        addBlock(output, IBBlocks.GRANITE_BUTTON.get(large));
        addBlock(output, IBBlocks.DIORITE_BUTTON.get(large));
        addBlock(output, IBBlocks.ANDESITE_BUTTON.get(large));
        addBlock(output, IBBlocks.CALCITE_BUTTON.get(large));
        addBlock(output, IBBlocks.TUFF_BUTTON.get(large));
        addBlock(output, IBBlocks.DRIPSTONE_BUTTON.get(large));
        addBlock(output, large ? IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.POLISHED_BLACKSTONE).get() : Blocks.POLISHED_BLACKSTONE_BUTTON);
    }

    /**
     * Adds copper buttons to the creative tab.
     * @param output The output instance to add the blocks to.
     * @param large Specifies if the small or large variant should be added.
     */
    public static void addCopperButtons(CreativeModeTab.Output output, boolean large) {
        for (CopperButtonType type : CopperButtonType.values()) {
            for (WeatheringCopper.WeatherState state : WeatheringCopper.WeatherState.values()) {
                addBlock(output, IBBlocks.COPPER_BUTTONS.get(type, state).get(large));
            }
        }
    }

    /**
     * Adds special buttons to the creative tab.
     * @param output The output instance to add the blocks to.
     * @param large Specifies if the small or large variant should be added.
     */
    public static void addSpecialButtons(CreativeModeTab.Output output, boolean large) {
        output.accept(new ItemStack(IBBlocks.IRON_BUTTON.get(large)));
        output.accept(new ItemStack(IBBlocks.GOLD_BUTTON.get(large)));
        output.accept(new ItemStack(IBBlocks.EMERALD_BUTTON.get(large)));
        output.accept(new ItemStack(IBBlocks.DIAMOND_BUTTON.get(large)));
        output.accept(new ItemStack(IBBlocks.PRISMARINE_BUTTON.get(large)));
        output.accept(new ItemStack(IBBlocks.PRISMARINE_BRICK_BUTTON.get(large)));
        output.accept(new ItemStack(IBBlocks.DARK_PRISMARINE_BUTTON.get(large)));
    }

    /**
     * Adds single-use buttons to the creative tab.
     * @param output The output instance to add the blocks to.
     * @param large Specifies if the small or large variant should be added.
     */
    public static void addOneUseButtons(CreativeModeTab.Output output, boolean large) {
        addBlock(output, IBBlocks.SAND_BUTTON.get(large));
        addBlock(output, IBBlocks.RED_SAND_BUTTON.get(large));
        addBlock(output, IBBlocks.GRAVEL_BUTTON.get(large));
        // TODO ATMOSPHERIC SAND BUTTONS
        addConcretePowderButtons(output, large);
    }

    /**
     * Adds concrete powder buttons to the creative tab.
     * @param output The output instance to add the blocks to.
     * @param large Specifies if the small or large variant should be added.
     */
    public static void addConcretePowderButtons(CreativeModeTab.Output output, boolean large) {
        for (DyeColor color : sortedDyeColors()) {
            addBlock(output, IBBlocks.CONCRETE_POWDER_BUTTONS.get(color).get(large));
        }
    }

    /**
     * Adds emergency buttons to the creative tab.
     * @param output The output instance to add the blocks to.
     */
    public static void addEmergencyButtons(CreativeModeTab.Output output) {
        for (DyeColor color : sortedDyeColors()) {
            addBlock(output, IBBlocks.EMERGENCY_BUTTONS.get(color).get());
        }
        addBlock(output, IBBlocks.FANCY_EMERGENCY_BUTTON.get());
        for (DyeColor color : sortedDyeColors()) {
            addBlock(output, IBBlocks.SAFETY_BUTTONS.get(color).get());
        }
        addBlock(output, IBBlocks.FANCY_SAFE_EMERGENCY_BUTTON.get());
    }

    /**
     * Adds secret buttons to the creative tab.
     * @param output The output instance to add the blocks to.
     */
    public static void addSecretButtons(CreativeModeTab.Output output) {
        // TODO compat secret buttons
        addBlock(output, IBBlocks.BOOKSHELF_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.BRICK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.STONE_BRICK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.MOSSY_STONE_BRICK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.CRACKED_STONE_BRICK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.CHISELED_STONE_BRICK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.DEEPSLATE_BRICK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.CRACKED_DEEPSLATE_BRICK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.DEEPSLATE_TILE_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.CRACKED_DEEPSLATE_TILE_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.OAK_PLANK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.SPRUCE_PLANK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.BIRCH_PLANK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.JUNGLE_PLANK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.ACACIA_PLANK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.DARK_OAK_PLANK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.MANGROVE_PLANK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.CHERRY_PLANK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.CRIMSON_PLANK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.WARPED_PLANK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.MUD_BRICK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.END_STONE_BRICK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.PURPUR_BLOCK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.QUARTZ_BRICK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.DARK_PRISMARINE_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.CHISELED_POLISHED_BLACKSTONE_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.NETHER_BRICK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.CRACKED_NETHER_BRICK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.CHISELED_NETHER_BRICK_SECRET_BUTTON.get());
        addBlock(output, IBBlocks.RED_NETHER_BRICK_SECRET_BUTTON.get());
    }

    /**
     * Adds unique button types to the creative tab.
     * @param output The output instance to add the blocks to.
     */
    public static void addCoolButtons(CreativeModeTab.Output output) {
        addBlock(output, IBBlocks.DOORBELL.get());
        addBlock(output, IBBlocks.DOORBELL_BUTTON.get());
        // TODO HOGLIN MOUNT BUTTON
        addBlock(output, IBBlocks.LAMP_BUTTON.get());
        addBlock(output, IBBlocks.LAMP_LEVER.get());
        addBlock(output, IBBlocks.LETTER_BUTTON.get());
        addBlock(output, IBBlocks.LETTER_LEVER.get());

        addBlock(output, IBBlocks.LANTERN_BUTTON.get());
        addBlock(output, IBBlocks.LANTERN_LEVER.get());
        addBlock(output, IBBlocks.SOUL_LANTERN_BUTTON.get());
        addBlock(output, IBBlocks.SOUL_LANTERN_LEVER.get());
        // TODO ENDER LANTERN

        addBlock(output, IBBlocks.SMALL_CONSOLE_BUTTON.get());
        addBlock(output, IBBlocks.SMALL_CONSOLE_LEVER.get());
        addBlock(output, IBBlocks.CONSOLE_BUTTON.get());
        addBlock(output, IBBlocks.CONSOLE_LEVER.get());
        addBlock(output, IBBlocks.LARGE_CONSOLE_BUTTON.get());
        addBlock(output, IBBlocks.LARGE_CONSOLE_LEVER.get());
        addBlock(output, IBBlocks.BIG_CONSOLE_BUTTON.get());
        addBlock(output, IBBlocks.BIG_CONSOLE_LEVER.get());
    }

    /**
     * Adds Torch Buttons to the creative mode tab.
     * @param output The output instance to add the blocks to.
     */
    public static void addTorchButtons(CreativeModeTab.Output output) {
        addBlock(output, IBBlocks.TORCH_BUTTON.get());
        addBlock(output, IBBlocks.TORCH_LEVER.get());
        addBlock(output, IBBlocks.SOUL_TORCH_BUTTON.get());
        addBlock(output, IBBlocks.SOUL_TORCH_LEVER.get());
        addBlock(output, IBBlocks.REDSTONE_TORCH_BUTTON.get());
        addBlock(output, IBBlocks.REDSTONE_TORCH_LEVER.get());
        // TODO PROPELPLANT TORCH
        // TODO ENDER TORCH
    }

    /**
     * Adds a block to a creative tab
     * @param output The output instance to add the blocks to.
     * @param block The block to add to the output.
     */
    private static void addBlock(CreativeModeTab.Output output, Block block) {
        output.accept(new ItemStack(block));
    }

    /*
     * Vanilla Tabs
     */

    /**
     * Modifies all vanilla tabs.
     */
    private static void modifyVanillaTabs() {
        modifyBuildingBlocks();
        modifyRedstoneBlocks();
        modifyColoredBlocks();
    }

    /**
     * Modifies the building blocks tab.
     */
    @SuppressWarnings("all")
    public static void modifyBuildingBlocks() {
        DiopsideCreativeTabs.INSTANCE.modifyTab(CreativeModeTabs.BUILDING_BLOCKS, (features, output, canUseGameMasterBlocks) -> {
            output.acceptAfter(Blocks.OAK_BUTTON, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.OAK).get());
            output.acceptAfter(Blocks.SPRUCE_BUTTON, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.SPRUCE).get());
            output.acceptAfter(Blocks.BIRCH_BUTTON, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.BIRCH).get());
            output.acceptAfter(Blocks.JUNGLE_BUTTON, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.JUNGLE).get());
            output.acceptAfter(Blocks.ACACIA_BUTTON, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.ACACIA).get());
            output.acceptAfter(Blocks.DARK_OAK_BUTTON, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.DARK_OAK).get());
            output.acceptAfter(Blocks.MANGROVE_BUTTON, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.MANGROVE).get());
            output.acceptAfter(Blocks.CHERRY_BUTTON, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.CHERRY).get());
            output.acceptAfter(Blocks.BAMBOO_BUTTON, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.BAMBOO).get());
            output.acceptAfter(Blocks.CRIMSON_BUTTON, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.CRIMSON).get());
            output.acceptAfter(Blocks.WARPED_BUTTON, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.WARPED).get());
            output.acceptAfter(Blocks.STONE_BUTTON, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.STONE).get());
            output.acceptAfter(Blocks.POLISHED_BLACKSTONE_BUTTON, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.POLISHED_BLACKSTONE).get());

            output.acceptAfter(Blocks.DEEPSLATE, IBBlocks.DEEPSLATE_BUTTON.get(false));
            output.acceptAfter(IBBlocks.DEEPSLATE_BUTTON.get(false), IBBlocks.DEEPSLATE_BUTTON.get(true));
            output.acceptAfter(Blocks.GRANITE_WALL, IBBlocks.GRANITE_BUTTON.get(false));
            output.acceptAfter(IBBlocks.GRANITE_BUTTON.get(false), IBBlocks.GRANITE_BUTTON.get(true));
            output.acceptAfter(Blocks.DIORITE_WALL, IBBlocks.DIORITE_BUTTON.get(false));
            output.acceptAfter(IBBlocks.DIORITE_BUTTON.get(false), IBBlocks.DIORITE_BUTTON.get(true));
            output.acceptAfter(Blocks.ANDESITE_WALL, IBBlocks.ANDESITE_BUTTON.get(false));
            output.acceptAfter(IBBlocks.ANDESITE_BUTTON.get(false), IBBlocks.ANDESITE_BUTTON.get(true));
            output.acceptAfter(Blocks.TUFF_WALL, IBBlocks.TUFF_BUTTON.get(false));
            output.acceptAfter(IBBlocks.TUFF_BUTTON.get(false), IBBlocks.TUFF_BUTTON.get(true));

            output.acceptAfter(Blocks.PRISMARINE_WALL, IBBlocks.PRISMARINE_BUTTON.get(false));
            output.acceptAfter(IBBlocks.PRISMARINE_BUTTON.get(false), IBBlocks.PRISMARINE_BUTTON.get(true));
            output.acceptAfter(Blocks.PRISMARINE_BRICK_SLAB, IBBlocks.PRISMARINE_BRICK_BUTTON.get(false));
            output.acceptAfter(IBBlocks.PRISMARINE_BRICK_BUTTON.get(false), IBBlocks.PRISMARINE_BRICK_BUTTON.get(true));
            output.acceptAfter(Blocks.DARK_PRISMARINE_SLAB, IBBlocks.DARK_PRISMARINE_BUTTON.get(false));
            output.acceptAfter(IBBlocks.DARK_PRISMARINE_BUTTON.get(false), IBBlocks.DARK_PRISMARINE_BUTTON.get(true));

            output.acceptAfter(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE, IBBlocks.IRON_BUTTON.get(false));
            output.acceptAfter(IBBlocks.IRON_BUTTON.get(false), IBBlocks.IRON_BUTTON.get(true));
            output.acceptAfter(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE, IBBlocks.GOLD_BUTTON.get(false));
            output.acceptAfter(IBBlocks.GOLD_BUTTON.get(false), IBBlocks.GOLD_BUTTON.get(true));

            var normalCopperButton = IBBlocks.COPPER_BUTTONS.get(CopperButtonType.NORMAL, WeatheringCopper.WeatherState.UNAFFECTED);
            output.acceptAfter(Blocks.CUT_COPPER_SLAB, normalCopperButton.get(false));
            output.acceptAfter(normalCopperButton.get(false), normalCopperButton.get(true));
            var exposedCopperButton = IBBlocks.COPPER_BUTTONS.get(CopperButtonType.NORMAL, WeatheringCopper.WeatherState.EXPOSED);
            output.acceptAfter(Blocks.EXPOSED_CUT_COPPER_SLAB, exposedCopperButton.get(false));
            output.acceptAfter(exposedCopperButton.get(false), exposedCopperButton.get(true));
            var weatheredCopperButton = IBBlocks.COPPER_BUTTONS.get(CopperButtonType.NORMAL, WeatheringCopper.WeatherState.WEATHERED);
            output.acceptAfter(Blocks.WEATHERED_CUT_COPPER_SLAB, weatheredCopperButton.get(false));
            output.acceptAfter(weatheredCopperButton.get(false), weatheredCopperButton.get(true));
            var oxidizedCopperButton = IBBlocks.COPPER_BUTTONS.get(CopperButtonType.NORMAL, WeatheringCopper.WeatherState.OXIDIZED);
            output.acceptAfter(Blocks.OXIDIZED_CUT_COPPER_SLAB, oxidizedCopperButton.get(false));
            output.acceptAfter(oxidizedCopperButton.get(false), oxidizedCopperButton.get(true));

            var normalWaxedCopperButton = IBBlocks.COPPER_BUTTONS.get(CopperButtonType.WAXED, WeatheringCopper.WeatherState.UNAFFECTED);
            output.acceptAfter(Blocks.WAXED_CUT_COPPER_SLAB, normalWaxedCopperButton.get(false));
            output.acceptAfter(normalWaxedCopperButton.get(false), normalWaxedCopperButton.get(true));
            var exposedWaxedCopperButton = IBBlocks.COPPER_BUTTONS.get(CopperButtonType.WAXED, WeatheringCopper.WeatherState.EXPOSED);
            output.acceptAfter(Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB, exposedWaxedCopperButton.get(false));
            output.acceptAfter(exposedWaxedCopperButton.get(false), exposedWaxedCopperButton.get(true));
            var weatheredWaxedCopperButton = IBBlocks.COPPER_BUTTONS.get(CopperButtonType.WAXED, WeatheringCopper.WeatherState.WEATHERED);
            output.acceptAfter(Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB, weatheredWaxedCopperButton.get(false));
            output.acceptAfter(weatheredWaxedCopperButton.get(false), weatheredWaxedCopperButton.get(true));
            var oxidizedWaxedCopperButton = IBBlocks.COPPER_BUTTONS.get(CopperButtonType.WAXED, WeatheringCopper.WeatherState.OXIDIZED);
            output.acceptAfter(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB, oxidizedWaxedCopperButton.get(false));
            output.acceptAfter(oxidizedWaxedCopperButton.get(false), oxidizedWaxedCopperButton.get(true));
        });
    }


    /**
     * Modifies the redstone blocks tab.
     */
    @SuppressWarnings("all")
    public static void modifyRedstoneBlocks() {
        DiopsideCreativeTabs.INSTANCE.modifyTab(CreativeModeTabs.REDSTONE_BLOCKS, (features, output, canUseGameMasterBlocks) -> {
            output.acceptAfter(Blocks.OAK_BUTTON, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.OAK).get());
            output.acceptAfter(Blocks.STONE_BUTTON, IBBlocks.DEFAULT_LARGE_BUTTONS.get(BlockSetType.STONE).get());

            output.acceptAfter(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE, IBBlocks.GOLD_BUTTON.get(true));
            output.acceptAfter(IBBlocks.GOLD_BUTTON.get(true), IBBlocks.IRON_BUTTON.get(true));
            output.acceptAfter(IBBlocks.IRON_BUTTON.get(true), IBBlocks.EMERALD_BUTTON.get(true));

            output.acceptAfter(Blocks.REDSTONE_LAMP, IBBlocks.LAMP_BUTTON.get());
        });
    }

    /**
     * Modifies the colored blocks tab.
     */
    @SuppressWarnings("all")
    public static void modifyColoredBlocks() {
        DiopsideCreativeTabs.INSTANCE.modifyTab(CreativeModeTabs.COLORED_BLOCKS, (features, output, canUseGameMasterBlocks) -> {
            addConcretePowderButtons(output, false);
            addConcretePowderButtons(output, true);
            addEmergencyButtons(output);
        });
    }

    /*
     * Register Functions
     */

    /**
     * Registers a tab
     * @param tab A supplier for the tab to register.
     * @return A registry supplier for the tab.
     */
    private static <T extends CreativeModeTab> RegistrySupplier<T> registerTab(Supplier<T> tab) {
        ResourceLocation id = getResource(InfinityButtons.MOD_ID);
        return DiopsideCreativeTabs.INSTANCE.registerTab(id, tab);
    }

    /**
     * Registers the Infinity Buttons creative tab and modifies vanilla tabs.
     */
    public static void register() {
        LOGGER.info("Registering Creative Tabs for Infinity Buttons");
        modifyVanillaTabs();
    }
}