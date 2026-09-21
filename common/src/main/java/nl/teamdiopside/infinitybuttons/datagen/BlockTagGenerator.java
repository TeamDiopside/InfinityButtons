package nl.teamdiopside.infinitybuttons.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.VanillaBlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import nl.teamdiopside.infinitybuttons.compat.IBModdedBlocks;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.MOD_ID;

public class BlockTagGenerator extends VanillaBlockTagsProvider {

    public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
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
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MOD_ID, name));
    }

    public static final TagKey<Block> BOOKSHELVES = addCommon("bookshelves");

    static TagKey<Block> addCommon(String name) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", name));
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

    public static final TagKey<Block> FAF_COPPER_BUTTONS = edit("friendsandfoes", "copper_buttons");

    static TagKey<Block> edit(String namespace, String name) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(namespace, name));
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        generateVanillaTags();
        generateInfinityButtonsTags();
        generateFriendsFoesTags();
    }

    protected void generateInfinityButtonsTags() {
        for (var value : DyeColor.values()) {
            this.tag(CONCRETE_POWDER_BUTTONS)
                    .add(IBRegistryUtils.getBlockByID(MOD_ID,
                            value.name().toLowerCase() + "_concrete_powder_button")
                    );

            this.tag(CONCRETE_POWDER_LARGE_BUTTONS)
                    .add(IBRegistryUtils.getBlockByID(MOD_ID,
                            value.name().toLowerCase() + "_concrete_powder_large_button")
                    );
        }

        for (var value : IBBlocks.DEFAULT_LARGE_BUTTONS.entrySet()) {
            if (!IBRegistryUtils.isWoodType(value.getKey().name())) return;
            this.tag(WOODEN_LARGE_BUTTONS)
                    .add(value.getValue().get());
        }

        this.tag(LARGE_BUTTONS)
                .addTag(WOODEN_LARGE_BUTTONS)
                .addTag(COPPER_LARGE_BUTTONS)
                .addTag(CONCRETE_POWDER_LARGE_BUTTONS);

        for (var entry : IBBlocks.EMERGENCY_BUTTONS.values()) {
            this.tag(EMERGENCY_BUTTONS)
                    .add(entry.get());

            if (entry == IBBlocks.FANCY_EMERGENCY_BUTTON) continue;
            this.tag(NORMAL_EMERGENCY_BUTTONS)
                    .add(entry.get());
        }

        for (var entry : IBBlocks.SAFE_EMERGENCY_BUTTONS.values()) {
            this.tag(SAFE_EMERGENCY_BUTTONS)
                    .add(entry.get());

            if (entry == IBBlocks.FANCY_SAFE_EMERGENCY_BUTTON) continue;
            this.tag(NORMAL_SAFE_EMERGENCY_BUTTONS)
                    .add(entry.get());
        }

        this.tag(SECRET_BUTTONS)
                .addTag(WOODEN_SECRET_BUTTONS)
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "rose_quartz_tile_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "small_rose_quartz_tile_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "cut_granite_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "small_granite_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "cut_diorite_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "small_diorite_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "cut_andesite_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "small_andesite_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "cut_calcite_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "small_calcite_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "cut_dripstone_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "small_dripstone_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "cut_deepslate_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "small_deepslate_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "cut_tuff_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "small_tuff_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "cut_asurine_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "small_asurine_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "cut_crimsite_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "small_crimsite_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "cut_limestone_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "small_limestone_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "cut_ochrum_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "small_ochrum_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "cut_scoria_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "small_scoria_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "cut_scorchia_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "small_scorchia_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "cut_veridium_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "small_veridium_brick_secret_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "hoglin_trophy_button"));

        for (var entry : IBBlocks.SECRET_BUTTONS.values()) {
            this.tag(SECRET_BUTTONS).add(entry.get());
        }

        this.tag(WOODEN_SECRET_BUTTONS)
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

        // Bookshelves
        this.tag(BOOKSHELF_SECRET_BUTTONS).add(IBBlocks.BOOKSHELF_SECRET_BUTTON.get());
        this.tag(BOOKSHELVES).add(IBBlocks.BOOKSHELF_SECRET_BUTTON.get());

        for (var entry : IBModdedBlocks.MOD_BOOKSHELVES) {
            this.tag(BOOKSHELF_SECRET_BUTTONS).add(entry.get());
            this.tag(BOOKSHELVES).add(entry.get());
        }

        this.tag(TORCH_BUTTONS)
                .add(IBBlocks.TORCH_BUTTON.get())
                .add(IBBlocks.TORCH_LEVER.get())
                .add(IBBlocks.SOUL_TORCH_BUTTON.get())
                .add(IBBlocks.SOUL_TORCH_LEVER.get())
                .add(IBBlocks.REDSTONE_TORCH_BUTTON.get())
                .add(IBBlocks.REDSTONE_TORCH_LEVER.get())
                .add(IBBlocks.WALL_TORCH_BUTTON.get())
                .add(IBBlocks.WALL_TORCH_LEVER.get())
                .add(IBBlocks.SOUL_WALL_TORCH_BUTTON.get())
                .add(IBBlocks.SOUL_WALL_TORCH_LEVER.get())
                .add(IBBlocks.REDSTONE_WALL_TORCH_BUTTON.get())
                .add(IBBlocks.REDSTONE_WALL_TORCH_LEVER.get())
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "propelplant_torch_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "propelplant_torch_lever"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "propelplant_wall_torch_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "propelplant_wall_torch_lever"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "powdery_torch_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "powdery_torch_lever"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "powdery_wall_torch_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "powdery_wall_torch_lever"));

        this.tag(CONSOLE_BUTTONS)
                .add(IBBlocks.SMALL_CONSOLE_BUTTON.get())
                .add(IBBlocks.SMALL_CONSOLE_LEVER.get())
                .add(IBBlocks.CONSOLE_BUTTON.get())
                .add(IBBlocks.CONSOLE_LEVER.get())
                .add(IBBlocks.LARGE_CONSOLE_BUTTON.get())
                .add(IBBlocks.LARGE_CONSOLE_LEVER.get())
                .add(IBBlocks.BIG_CONSOLE_BUTTON.get())
                .add(IBBlocks.BIG_CONSOLE_LEVER.get());

        this.tag(LANTERN_BUTTONS)
                .add(IBBlocks.LANTERN_BUTTON.get())
                .add(IBBlocks.LANTERN_LEVER.get())
                .add(IBBlocks.SOUL_LANTERN_BUTTON.get())
                .add(IBBlocks.SOUL_LANTERN_LEVER.get());

        for (var entry : IBBlocks.COPPER_BUTTONS.values()) {
            this.tag(COPPER_BUTTONS)
                    .add(entry.getSmall());
            this.tag(COPPER_LARGE_BUTTONS)
                    .add(entry.getLarge());
        }
    }

    protected void generateVanillaTags() {
        this.tag(MINEABLE_AXE)
                .addTag(WOODEN_LARGE_BUTTONS)
                .addTag(WOODEN_SECRET_BUTTONS)
                .add(IBBlocks.LETTER_BUTTON.get())
                .add(IBBlocks.LETTER_LEVER.get())
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "hoglin_trophy_button"));

        this.tag(MINEABLE_PICKAXE)
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
            this.tag(MINEABLE_PICKAXE)
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
                    .add(IBBlocks.NETHERITE_BUTTON.get(bool))
                    .add(IBBlocks.PRISMARINE_BUTTON.get(bool))
                    .add(IBBlocks.PRISMARINE_BRICK_BUTTON.get(bool))
                    .add(IBBlocks.DARK_PRISMARINE_BUTTON.get(bool));

        }


        for (var entry : IBBlocks.SECRET_BUTTONS.values()) {
            this.tag(MINEABLE_PICKAXE)
                    .add(entry.get());
        }

        this.tag(MINEABLE_SHOVEL)
                .add(IBBlocks.SAND_BUTTON.get(true))
                .add(IBBlocks.RED_SAND_BUTTON.get(true))
                .add(IBBlocks.GRAVEL_BUTTON.get(true))
                .add(IBBlocks.SAND_BUTTON.get(false))
                .add(IBBlocks.RED_SAND_BUTTON.get(false))
                .add(IBBlocks.GRAVEL_BUTTON.get(false))
                .addTag(CONCRETE_POWDER_BUTTONS)
                .addTag(CONCRETE_POWDER_LARGE_BUTTONS)
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "arid_sand_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "arid_sand_large_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "red_arid_sand_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "red_arid_sand_large_button"));

        this.tag(BUTTONS)
                .addTag(COPPER_BUTTONS)
                .addTag(CONCRETE_POWDER_BUTTONS);

        this.tag(BUTTONS)
                .add(IBBlocks.DEEPSLATE_BUTTON.get(false))
                .add(IBBlocks.GRANITE_BUTTON.get(false))
                .add(IBBlocks.DIORITE_BUTTON.get(false))
                .add(IBBlocks.ANDESITE_BUTTON.get(false))
                .add(IBBlocks.TUFF_BUTTON.get(false))
                .add(IBBlocks.DRIPSTONE_BUTTON.get(false))
                .add(IBBlocks.CALCITE_BUTTON.get(false));

        for (var entry : IBBlocks.SMALL_LARGE_BUTTONS.values()) {
            this.tag(BUTTONS)
                    .add(entry.getSmall());

            // Non-gravity buttons get pickaxed
            if (!IBBlocks.ONE_USE_BUTTONS.containsValue(entry)) {
                this.tag(MINEABLE_PICKAXE)
                        .add(entry.getSmall())
                        .add(entry.getLarge());
            }
        }

        this.tag(GUARDED_BY_PIGLINS)
                .add(IBBlocks.GOLD_BUTTON.get(false))
                .add(IBBlocks.GOLD_BUTTON.get(true));

        this.tag(NON_FLAMMABLE_WOOD)
                .add(IBRegistryUtils.getBlockByID(MOD_ID, "crimson_large_button"))
                .add(IBRegistryUtils.getBlockByID(MOD_ID, "warped_large_button"))
                .add(IBBlocks.CRIMSON_PLANK_SECRET_BUTTON.get())
                .add(IBBlocks.WARPED_PLANK_SECRET_BUTTON.get());

        this.tag(PIGLIN_REPELLENTS)
                .add(IBBlocks.SOUL_TORCH_BUTTON.get())
                .add(IBBlocks.SOUL_WALL_TORCH_BUTTON.get())
                .add(IBBlocks.SOUL_TORCH_LEVER.get())
                .add(IBBlocks.SOUL_WALL_TORCH_LEVER.get())
                .add(IBBlocks.SOUL_LANTERN_BUTTON.get())
                .add(IBBlocks.SOUL_LANTERN_LEVER.get());

        this.tag(STONE_BRICKS)
                .add(IBBlocks.STONE_BRICK_SECRET_BUTTON.get())
                .add(IBBlocks.MOSSY_STONE_BRICK_SECRET_BUTTON.get())
                .add(IBBlocks.CRACKED_STONE_BRICK_SECRET_BUTTON.get())
                .add(IBBlocks.CHISELED_STONE_BRICK_SECRET_BUTTON.get());

        this.tag(WALL_POST_OVERRIDE)
                .add(IBBlocks.TORCH_BUTTON.get())
                .add(IBBlocks.TORCH_LEVER.get())
                .add(IBBlocks.SOUL_TORCH_BUTTON.get())
                .add(IBBlocks.SOUL_TORCH_LEVER.get())
                .add(IBBlocks.REDSTONE_TORCH_BUTTON.get())
                .add(IBBlocks.REDSTONE_TORCH_LEVER.get())
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "propelplant_torch_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "propelplant_torch_lever"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "powdery_torch_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "powdery_torch_lever"));

        this.tag(NEEDS_STONE_TOOL)
                .addTag(COPPER_BUTTONS)
                .addTag(COPPER_LARGE_BUTTONS)
                .add(IBBlocks.IRON_BUTTON.get(true))
                .add(IBBlocks.IRON_BUTTON.get(false));

        for (boolean bool : Set.of(true, false)) {
            this.tag(NEEDS_IRON_TOOL)
                    .add(IBBlocks.DIAMOND_BUTTON.get(bool))
                    .add(IBBlocks.GOLD_BUTTON.get(bool))
                    .add(IBBlocks.EMERALD_BUTTON.get(bool));
        }
    }

    protected void generateFriendsFoesTags() {
        this.tag(FAF_COPPER_BUTTONS)
                .addTag(COPPER_BUTTONS)
                .addTag(COPPER_LARGE_BUTTONS);
    }
}