package nl.teamdiopside.infinitybuttons.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import nl.teamdiopside.infinitybuttons.InfinityButtons;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends FabricTagProvider<Block> {

    public BlockTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.BLOCK, registriesFuture);
    }

    public static final TagKey<Block> CONCRETE_POWDER_BUTTONS = add("concrete_powder_buttons");
    public static final TagKey<Block> CONCRETE_POWDER_LARGE_BUTTONS = add("concrete_powder_large_buttons");
    public static final TagKey<Block> LARGE_BUTTONS = add("large_buttons");
    public static final TagKey<Block> WOODEN_LARGE_BUTTONS = add("wooden_large_buttons");
    public static final TagKey<Block> EMERGENCY_BUTTONS = add("emergency_buttons");
    public static final TagKey<Block> SAFE_EMERGENCY_BUTTONS = add("safe_emergency_buttons");
    public static final TagKey<Block> NORMAL_EMERGENCY_BUTTONS = add("normal_emergency_buttons");
    public static final TagKey<Block> NORMAL_SAFE_EMERGENCY_BUTTONS = add("normal_safe_emergency_buttons");
    public static final TagKey<Block> SECRET_BUTTONS = add("secret_buttons");
    public static final TagKey<Block> WOODEN_SECRET_BUTTONS = add("wooden_secret_buttons");
    public static final TagKey<Block> BOOKSHELF_SECRET_BUTTONS = add("bookshelf_secret_buttons");
    public static final TagKey<Block> TORCH_BUTTONS = add("torch_buttons");
    public static final TagKey<Block> CONSOLE_BUTTONS = add("console_buttons");
    public static final TagKey<Block> LANTERN_BUTTONS = add("lantern_buttons");
    public static final TagKey<Block> COPPER_BUTTONS = add("copper_buttons");
    public static final TagKey<Block> COPPER_LARGE_BUTTONS = add("copper_large_buttons");

    static TagKey<Block> add(String name) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityButtons.MOD_ID, name));
    }

    public static final TagKey<Block> MINEABLE_AXE = edit("mineable/axe");
    public static final TagKey<Block> MINEABLE_PICKAXE = edit("mineable/pickaxe");
    public static final TagKey<Block> MINEABLE_SHOVEL = edit("mineable/shovel");
    public static final TagKey<Block> BUTTONS = edit("buttons");
    public static final TagKey<Block> GUARDED_BY_PIGLINS = edit("guarded_by_piglins");
    public static final TagKey<Block> NON_FLAMMABLE_WOOD = edit("non_flammable_wood");
    public static final TagKey<Block> PIGLIN_REPELLENTS = edit("piglin_repellents");
    public static final TagKey<Block> STONE_BRICKS = edit("stone_bricks");
    public static final TagKey<Block> WALL_POST_OVERRIDE = edit("wall_post_override");
    public static final TagKey<Block> NEEDS_STONE_TOOL = edit("needs_stone_tool");
    public static final TagKey<Block> NEEDS_IRON_TOOL = edit("needs_iron_tool");

    static TagKey<Block> edit(String name) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("minecraft", name));
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        generateVanillaTags();
        generateInfinityButtonsTags();
    }

    protected void generateInfinityButtonsTags() {
        for (var value : DyeColor.values()) {
            getOrCreateTagBuilder(CONCRETE_POWDER_BUTTONS)
                    .add(IBRegistryUtils.getBlockByID(InfinityButtons.MOD_ID,
                            value.name().toLowerCase() + "_concrete_powder_button")
                    );

            getOrCreateTagBuilder(CONCRETE_POWDER_LARGE_BUTTONS)
                    .add(IBRegistryUtils.getBlockByID(InfinityButtons.MOD_ID,
                            value.name().toLowerCase() + "_concrete_powder_large_button")
                    );
        }

        getOrCreateTagBuilder(LARGE_BUTTONS)
                .addTag(WOODEN_LARGE_BUTTONS)
                .addTag(COPPER_LARGE_BUTTONS)
                .addTag(CONCRETE_POWDER_LARGE_BUTTONS);

        getOrCreateTagBuilder(WOODEN_LARGE_BUTTONS);

        for (var entry : IBBlocks.EMERGENCY_BUTTONS.values()) {
            getOrCreateTagBuilder(EMERGENCY_BUTTONS)
                    .add(entry.get());

            if (entry == IBBlocks.FANCY_EMERGENCY_BUTTON) continue;
            getOrCreateTagBuilder(NORMAL_EMERGENCY_BUTTONS)
                    .add(entry.get());
        }

        for (var entry : IBBlocks.SAFE_EMERGENCY_BUTTONS.values()) {
            getOrCreateTagBuilder(SAFE_EMERGENCY_BUTTONS)
                    .add(entry.get());

            if (entry == IBBlocks.FANCY_SAFE_EMERGENCY_BUTTON) continue;
            getOrCreateTagBuilder(NORMAL_SAFE_EMERGENCY_BUTTONS)
                    .add(entry.get());
        }

        getOrCreateTagBuilder(SECRET_BUTTONS)
                .addTag(WOODEN_SECRET_BUTTONS)
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "rose_quartz_tile_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "small_rose_quartz_tile_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "cut_granite_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "small_granite_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "cut_diorite_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "small_diorite_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "cut_andesite_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "small_andesite_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "cut_calcite_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "small_calcite_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "cut_dripstone_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "small_dripstone_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "cut_deepslate_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "small_deepslate_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "cut_tuff_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "small_tuff_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "cut_asurine_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "small_asurine_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "cut_crimsite_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "small_crimsite_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "cut_limestone_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "small_limestone_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "cut_ochrum_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "small_ochrum_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "cut_scoria_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "small_scoria_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "cut_scorchia_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "small_scorchia_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "cut_veridium_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "small_veridium_brick_secret_button"));

        for (var entry : IBBlocks.SECRET_BUTTONS.values()) {
            getOrCreateTagBuilder(SECRET_BUTTONS)
                    .add(entry.get());
        }

        getOrCreateTagBuilder(WOODEN_SECRET_BUTTONS)
                .addTag(BOOKSHELF_SECRET_BUTTONS)
                .add(IBBlocks.OAK_PLANK_SECRET_BUTTON.get())
                .add(IBBlocks.SPRUCE_PLANK_SECRET_BUTTON.get())
                .add(IBBlocks.BIRCH_PLANK_SECRET_BUTTON.get())
                .add(IBBlocks.JUNGLE_PLANK_SECRET_BUTTON.get())
                .add(IBBlocks.ACACIA_PLANK_SECRET_BUTTON.get())
                .add(IBBlocks.DARK_OAK_PLANK_SECRET_BUTTON.get())
                .add(IBBlocks.MANGROVE_PLANK_SECRET_BUTTON.get())
                .add(IBBlocks.CRIMSON_PLANK_SECRET_BUTTON.get())
                .add(IBBlocks.CHERRY_PLANK_SECRET_BUTTON.get())
                .add(IBBlocks.BAMBOO_PLANK_SECRET_BUTTON.get())
                .add(IBBlocks.WARPED_PLANK_SECRET_BUTTON.get());

        getOrCreateTagBuilder(BOOKSHELF_SECRET_BUTTONS)
                .add(IBBlocks.BOOKSHELF_SECRET_BUTTON.get()); // TODO: Mod compat bookshelves

        getOrCreateTagBuilder(TORCH_BUTTONS)
                .add(IBBlocks.TORCH_BUTTON.get())
                .add(IBBlocks.TORCH_LEVER.get())
                .add(IBBlocks.SOUL_TORCH_BUTTON.get())
                .add(IBBlocks.SOUL_TORCH_LEVER.get())
                .add(IBBlocks.REDSTONE_TORCH_BUTTON.get())
                .add(IBBlocks.REDSTONE_TORCH_LEVER.get())
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "propelplant_torch_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "propelplant_torch_lever"))
                .add(IBBlocks.WALL_TORCH_BUTTON.get())
                .add(IBBlocks.WALL_TORCH_LEVER.get())
                .add(IBBlocks.SOUL_WALL_TORCH_BUTTON.get())
                .add(IBBlocks.SOUL_WALL_TORCH_LEVER.get())
                .add(IBBlocks.REDSTONE_WALL_TORCH_BUTTON.get())
                .add(IBBlocks.REDSTONE_WALL_TORCH_LEVER.get())
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "propelplant_wall_torch_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "propelplant_wall_torch_lever"));

        getOrCreateTagBuilder(CONSOLE_BUTTONS)
                .add(IBBlocks.SMALL_CONSOLE_BUTTON.get())
                .add(IBBlocks.SMALL_CONSOLE_LEVER.get())
                .add(IBBlocks.CONSOLE_BUTTON.get())
                .add(IBBlocks.CONSOLE_LEVER.get())
                .add(IBBlocks.LARGE_CONSOLE_BUTTON.get())
                .add(IBBlocks.LARGE_CONSOLE_LEVER.get())
                .add(IBBlocks.BIG_CONSOLE_BUTTON.get())
                .add(IBBlocks.BIG_CONSOLE_LEVER.get());

        getOrCreateTagBuilder(LANTERN_BUTTONS)
                .add(IBBlocks.LANTERN_BUTTON.get())
                .add(IBBlocks.LANTERN_LEVER.get())
                .add(IBBlocks.SOUL_LANTERN_BUTTON.get())
                .add(IBBlocks.SOUL_LANTERN_LEVER.get());

        for (var entry : IBBlocks.COPPER_BUTTONS.values()) {
            getOrCreateTagBuilder(COPPER_BUTTONS)
                    .add(entry.getSmall());
            getOrCreateTagBuilder(COPPER_LARGE_BUTTONS)
                    .add(entry.getLarge());
        }
    }

    protected void generateVanillaTags() {
        getOrCreateTagBuilder(MINEABLE_AXE)
                .addTag(WOODEN_LARGE_BUTTONS)
                .addTag(WOODEN_SECRET_BUTTONS)
                .add(IBBlocks.LETTER_BUTTON.get())
                .add(IBBlocks.LETTER_LEVER.get());

        getOrCreateTagBuilder(MINEABLE_PICKAXE)
                .addTag(COPPER_BUTTONS)
                .addTag(COPPER_LARGE_BUTTONS)
                .addTag(EMERGENCY_BUTTONS)
                .addTag(SAFE_EMERGENCY_BUTTONS)
                .add(IBBlocks.LANTERN_BUTTON.get())
                .add(IBBlocks.LANTERN_LEVER.get())
                .add(IBBlocks.SOUL_LANTERN_BUTTON.get())
                .add(IBBlocks.SOUL_LANTERN_LEVER.get())
                .add(IBBlocks.SMALL_CONSOLE_BUTTON.get())
                .add(IBBlocks.SMALL_CONSOLE_LEVER.get())
                .add(IBBlocks.CONSOLE_BUTTON.get())
                .add(IBBlocks.CONSOLE_LEVER.get())
                .add(IBBlocks.LARGE_CONSOLE_BUTTON.get())
                .add(IBBlocks.LARGE_CONSOLE_LEVER.get())
                .add(IBBlocks.BIG_CONSOLE_BUTTON.get())
                .add(IBBlocks.BIG_CONSOLE_LEVER.get());

        for (boolean bool : Set.of(true, false)) {
            getOrCreateTagBuilder(MINEABLE_PICKAXE)
                    .add(IBBlocks.DEEPSLATE_BUTTON.get(bool))
                    .add(IBBlocks.GRANITE_BUTTON.get(bool))
                    .add(IBBlocks.DIORITE_BUTTON.get(bool))
                    .add(IBBlocks.ANDESITE_BUTTON.get(bool))
                    .add(IBBlocks.TUFF_BUTTON.get(bool))
                    .add(IBBlocks.DRIPSTONE_BUTTON.get(bool))
                    .add(IBBlocks.CALCITE_BUTTON.get(bool))
                    .add(IBBlocks.IRON_BUTTON.get(bool))
                    .add(IBBlocks.GOLD_BUTTON.get(bool))
                    .add(IBBlocks.EMERALD_BUTTON.get(bool))
                    .add(IBBlocks.DIAMOND_BUTTON.get(bool))
                    .add(IBBlocks.PRISMARINE_BUTTON.get(bool))
                    .add(IBBlocks.PRISMARINE_BRICK_BUTTON.get(bool))
                    .add(IBBlocks.DARK_PRISMARINE_BUTTON.get(bool));

        }


        for (var entry : IBBlocks.SECRET_BUTTONS.values()) {
            getOrCreateTagBuilder(MINEABLE_PICKAXE)
                    .add(entry.get());
        }

        getOrCreateTagBuilder(MINEABLE_SHOVEL)
                .add(IBBlocks.SAND_BUTTON.get(true))
                .add(IBBlocks.RED_SAND_BUTTON.get(true))
                .add(IBBlocks.GRAVEL_BUTTON.get(true))
                .add(IBBlocks.SAND_BUTTON.get(false))
                .add(IBBlocks.RED_SAND_BUTTON.get(false))
                .add(IBBlocks.GRAVEL_BUTTON.get(false))
                .addTag(CONCRETE_POWDER_BUTTONS)
                .addTag(CONCRETE_POWDER_LARGE_BUTTONS);

        getOrCreateTagBuilder(BUTTONS)
                .addTag(COPPER_BUTTONS)
                .addTag(CONCRETE_POWDER_BUTTONS);


        for (boolean bool : Set.of(true, false)) {
            getOrCreateTagBuilder(BUTTONS)
                    .add(IBBlocks.DEEPSLATE_BUTTON.get(bool))
                    .add(IBBlocks.GRANITE_BUTTON.get(bool))
                    .add(IBBlocks.DIORITE_BUTTON.get(bool))
                    .add(IBBlocks.ANDESITE_BUTTON.get(bool))
                    .add(IBBlocks.TUFF_BUTTON.get(bool))
                    .add(IBBlocks.DRIPSTONE_BUTTON.get(bool))
                    .add(IBBlocks.CALCITE_BUTTON.get(bool))
                    .add(IBBlocks.IRON_BUTTON.get(bool))
                    .add(IBBlocks.GOLD_BUTTON.get(bool))
                    .add(IBBlocks.EMERALD_BUTTON.get(bool))
                    .add(IBBlocks.DIAMOND_BUTTON.get(bool))
                    .add(IBBlocks.PRISMARINE_BUTTON.get(bool))
                    .add(IBBlocks.PRISMARINE_BRICK_BUTTON.get(bool))
                    .add(IBBlocks.DARK_PRISMARINE_BUTTON.get(bool))
                    .add(IBBlocks.SAND_BUTTON.get(bool))
                    .add(IBBlocks.RED_SAND_BUTTON.get(bool))
                    .add(IBBlocks.GRAVEL_BUTTON.get(bool));
        }

        for (var entry : IBBlocks.SMALL_LARGE_BUTTONS.values()) {
            getOrCreateTagBuilder(BUTTONS)
                    .add(entry.getSmall())
                    .add(entry.getLarge());
            getOrCreateTagBuilder(MINEABLE_PICKAXE)
                    .add(entry.getSmall())
                    .add(entry.getLarge());
        }

        getOrCreateTagBuilder(GUARDED_BY_PIGLINS)
                .add(IBBlocks.GOLD_BUTTON.get(false))
                .add(IBBlocks.GOLD_BUTTON.get(true));

        getOrCreateTagBuilder(NON_FLAMMABLE_WOOD)
                .add(IBRegistryUtils.getBlockByID(InfinityButtons.MOD_ID, "crimson_large_button"))
                .add(IBRegistryUtils.getBlockByID(InfinityButtons.MOD_ID, "warped_large_button"))
                .add(IBBlocks.CRIMSON_PLANK_SECRET_BUTTON.get())
                .add(IBBlocks.WARPED_PLANK_SECRET_BUTTON.get());

        getOrCreateTagBuilder(PIGLIN_REPELLENTS)
                .add(IBBlocks.SOUL_TORCH_BUTTON.get())
                .add(IBBlocks.SOUL_WALL_TORCH_BUTTON.get())
                .add(IBBlocks.SOUL_TORCH_LEVER.get())
                .add(IBBlocks.SOUL_WALL_TORCH_LEVER.get())
                .add(IBBlocks.SOUL_LANTERN_BUTTON.get())
                .add(IBBlocks.SOUL_LANTERN_LEVER.get());

        getOrCreateTagBuilder(STONE_BRICKS)
                .add(IBBlocks.STONE_BRICK_SECRET_BUTTON.get())
                .add(IBBlocks.MOSSY_STONE_BRICK_SECRET_BUTTON.get())
                .add(IBBlocks.CRACKED_STONE_BRICK_SECRET_BUTTON.get())
                .add(IBBlocks.CHISELED_STONE_BRICK_SECRET_BUTTON.get());

        getOrCreateTagBuilder(WALL_POST_OVERRIDE)
                .add(IBBlocks.TORCH_BUTTON.get())
                .add(IBBlocks.TORCH_LEVER.get())
                .add(IBBlocks.SOUL_TORCH_BUTTON.get())
                .add(IBBlocks.SOUL_TORCH_LEVER.get())
                .add(IBBlocks.REDSTONE_TORCH_BUTTON.get())
                .add(IBBlocks.REDSTONE_TORCH_LEVER.get())
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "propelplant_torch_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "propelplant_torch_lever"));

        getOrCreateTagBuilder(NEEDS_STONE_TOOL)
                .addTag(COPPER_BUTTONS)
                .addTag(COPPER_LARGE_BUTTONS)
                .add(IBBlocks.IRON_BUTTON.get(true))
                .add(IBBlocks.IRON_BUTTON.get(false));

        for (boolean bool : Set.of(true, false)) {
            getOrCreateTagBuilder(NEEDS_IRON_TOOL)
                    .add(IBBlocks.DIAMOND_BUTTON.get(bool))
                    .add(IBBlocks.GOLD_BUTTON.get(bool))
                    .add(IBBlocks.EMERALD_BUTTON.get(bool));
        }
    }
}