package nl.teamdiopside.infinitybuttons.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import nl.teamdiopside.infinitybuttons.compat.IBModdedBlocks;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;

import java.util.concurrent.CompletableFuture;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.MOD_ID;

public class ItemTagGenerator extends ItemTagsProvider {

    public ItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, registriesFuture, blockTags);
    }

    public static final TagKey<Item> CONCRETE_POWDER_BUTTONS = add("concrete_powder_buttons");
    public static final TagKey<Item> CONCRETE_POWDER_LARGE_BUTTONS = add("concrete_powder_large_buttons");
    public static final TagKey<Item> LARGE_BUTTONS = add("large_buttons");
    public static final TagKey<Item> WOODEN_LARGE_BUTTONS = add("wooden_large_buttons");
    public static final TagKey<Item> EMERGENCY_BUTTONS = add("emergency_buttons");
    public static final TagKey<Item> SAFE_EMERGENCY_BUTTONS = add("safe_emergency_buttons");
    public static final TagKey<Item> NORMAL_EMERGENCY_BUTTONS = add("normal_emergency_buttons");
    public static final TagKey<Item> NORMAL_SAFE_EMERGENCY_BUTTONS = add("normal_safe_emergency_buttons");
    public static final TagKey<Item> SECRET_BUTTONS = add("secret_buttons");
    public static final TagKey<Item> WOODEN_SECRET_BUTTONS = add("wooden_secret_buttons");
    public static final TagKey<Item> BOOKSHELF_SECRET_BUTTONS = add("bookshelf_secret_buttons");
    public static final TagKey<Item> TORCH_BUTTONS = add("torch_buttons");
    public static final TagKey<Item> CONSOLE_BUTTONS = add("console_buttons");
    public static final TagKey<Item> LANTERN_BUTTONS = add("lantern_buttons");
    public static final TagKey<Item> COPPER_BUTTONS = add("copper_buttons");
    public static final TagKey<Item> COPPER_LARGE_BUTTONS = add("copper_large_buttons");

    static TagKey<Item> add(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MOD_ID, name));
    }

    public static final TagKey<Item> BOOKSHELVES = addCommon("bookshelves");

    static TagKey<Item> addCommon(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", name));
    }

    public static final TagKey<Item> BUTTONS = edit("buttons");
    public static final TagKey<Item> NON_FLAMMABLE_WOOD = edit("non_flammable_wood");
    public static final TagKey<Item> PIGLIN_REPELLENTS = edit("piglin_repellents");
    public static final TagKey<Item> STONE_BRICKS = edit("stone_bricks");

    static TagKey<Item> edit(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("minecraft", name));
    }

    public static final TagKey<Item> FAF_COPPER_BUTTONS = edit("friendsandfoes", "copper_buttons");

    static TagKey<Item> edit(String namespace, String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(namespace, name));
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
                    .add(IBRegistryUtils.getItemByID(MOD_ID,
                            value.name().toLowerCase() + "_concrete_powder_button")
                    );

            this.tag(CONCRETE_POWDER_LARGE_BUTTONS)
                    .add(IBRegistryUtils.getItemByID(MOD_ID,
                            value.name().toLowerCase() + "_concrete_powder_large_button")
                    );
        }

        for (var value : IBBlocks.DEFAULT_LARGE_BUTTONS.entrySet()) {
            if (!IBRegistryUtils.isWoodType(value.getKey().name())) continue;
            this.tag(WOODEN_LARGE_BUTTONS)
                    .add(value.getValue().get().asItem());
        }

        this.tag(LARGE_BUTTONS)
                .addTag(WOODEN_LARGE_BUTTONS)
                .addTag(COPPER_LARGE_BUTTONS)
                .addTag(CONCRETE_POWDER_LARGE_BUTTONS);

        for (var entry : IBBlocks.EMERGENCY_BUTTONS.values()) {
            this.tag(EMERGENCY_BUTTONS)
                    .add(entry.get().asItem());

            if (entry == IBBlocks.FANCY_EMERGENCY_BUTTON) continue;
            this.tag(NORMAL_EMERGENCY_BUTTONS)
                    .add(entry.get().asItem());
        }

        for (var entry : IBBlocks.SAFE_EMERGENCY_BUTTONS.values()) {
            this.tag(SAFE_EMERGENCY_BUTTONS)
                    .add(entry.get().asItem());

            if (entry == IBBlocks.FANCY_SAFE_EMERGENCY_BUTTON) continue;
            this.tag(NORMAL_SAFE_EMERGENCY_BUTTONS)
                    .add(entry.get().asItem());
        }

        this.tag(WOODEN_SECRET_BUTTONS)
                .addTag(BOOKSHELF_SECRET_BUTTONS)
                .add(IBBlocks.OAK_PLANK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.SPRUCE_PLANK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.BIRCH_PLANK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.JUNGLE_PLANK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.ACACIA_PLANK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.DARK_OAK_PLANK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.MANGROVE_PLANK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.CRIMSON_PLANK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.CHERRY_PLANK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.BAMBOO_PLANK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.WARPED_PLANK_SECRET_BUTTON.get().asItem());

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
            this.tag(SECRET_BUTTONS).add(entry.get().asItem());
        }

        // Bookshelves
        this.tag(BOOKSHELF_SECRET_BUTTONS).add(IBBlocks.BOOKSHELF_SECRET_BUTTON.get().asItem());
        this.tag(BOOKSHELVES).add(IBBlocks.BOOKSHELF_SECRET_BUTTON.get().asItem());

        for (var entry : IBModdedBlocks.MOD_BOOKSHELVES) {
            IBRegistryUtils.BlockInfo info = IBRegistryUtils.BlockInfo.from(entry.get());
            ResourceLocation location = ResourceLocation.fromNamespaceAndPath(info.namespace(), info.id());
            this.tag(BOOKSHELF_SECRET_BUTTONS).addOptional(location);
            this.tag(BOOKSHELVES).addOptional(location);
        }

        this.tag(TORCH_BUTTONS)
                .add(IBBlocks.TORCH_BUTTON.get().asItem())
                .add(IBBlocks.TORCH_LEVER.get().asItem())
                .add(IBBlocks.SOUL_TORCH_BUTTON.get().asItem())
                .add(IBBlocks.SOUL_TORCH_LEVER.get().asItem())
                .add(IBBlocks.REDSTONE_TORCH_BUTTON.get().asItem())
                .add(IBBlocks.REDSTONE_TORCH_LEVER.get().asItem())
                .add(IBBlocks.WALL_TORCH_BUTTON.get().asItem())
                .add(IBBlocks.WALL_TORCH_LEVER.get().asItem())
                .add(IBBlocks.SOUL_WALL_TORCH_BUTTON.get().asItem())
                .add(IBBlocks.SOUL_WALL_TORCH_LEVER.get().asItem())
                .add(IBBlocks.REDSTONE_WALL_TORCH_BUTTON.get().asItem())
                .add(IBBlocks.REDSTONE_WALL_TORCH_LEVER.get().asItem())
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "propelplant_torch_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "propelplant_torch_lever"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "propelplant_wall_torch_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "propelplant_wall_torch_lever"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "powdery_torch_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "powdery_torch_lever"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "powdery_wall_torch_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(MOD_ID, "powdery_wall_torch_lever"));

        this.tag(CONSOLE_BUTTONS)
                .add(IBBlocks.SMALL_CONSOLE_BUTTON.get().asItem())
                .add(IBBlocks.SMALL_CONSOLE_LEVER.get().asItem())
                .add(IBBlocks.CONSOLE_BUTTON.get().asItem())
                .add(IBBlocks.CONSOLE_LEVER.get().asItem())
                .add(IBBlocks.LARGE_CONSOLE_BUTTON.get().asItem())
                .add(IBBlocks.LARGE_CONSOLE_LEVER.get().asItem())
                .add(IBBlocks.BIG_CONSOLE_BUTTON.get().asItem())
                .add(IBBlocks.BIG_CONSOLE_LEVER.get().asItem());

        this.tag(LANTERN_BUTTONS)
                .add(IBBlocks.LANTERN_BUTTON.get().asItem())
                .add(IBBlocks.LANTERN_LEVER.get().asItem())
                .add(IBBlocks.SOUL_LANTERN_BUTTON.get().asItem())
                .add(IBBlocks.SOUL_LANTERN_LEVER.get().asItem());

        for (var entry : IBBlocks.COPPER_BUTTONS.values()) {
            this.tag(COPPER_BUTTONS)
                    .add(entry.getSmall().asItem());
            this.tag(COPPER_LARGE_BUTTONS)
                    .add(entry.getLarge().asItem());
        }
    }

    protected void generateVanillaTags() {
        this.tag(BUTTONS)
                .addTag(COPPER_BUTTONS)
                .addTag(CONCRETE_POWDER_BUTTONS);

        this.tag(BUTTONS)
                .add(IBBlocks.DEEPSLATE_BUTTON.get(false).asItem())
                .add(IBBlocks.GRANITE_BUTTON.get(false).asItem())
                .add(IBBlocks.DIORITE_BUTTON.get(false).asItem())
                .add(IBBlocks.ANDESITE_BUTTON.get(false).asItem())
                .add(IBBlocks.TUFF_BUTTON.get(false).asItem())
                .add(IBBlocks.DRIPSTONE_BUTTON.get(false).asItem())
                .add(IBBlocks.CALCITE_BUTTON.get(false).asItem());

        for (var entry : IBBlocks.SMALL_LARGE_BUTTONS.values()) {
            this.tag(BUTTONS)
                    .add(entry.getSmall().asItem());
        }

        this.tag(NON_FLAMMABLE_WOOD)
                .add(IBRegistryUtils.getItemByID(MOD_ID, "crimson_large_button"))
                .add(IBRegistryUtils.getItemByID(MOD_ID, "warped_large_button"))
                .add(IBBlocks.CRIMSON_PLANK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.WARPED_PLANK_SECRET_BUTTON.get().asItem());

        this.tag(PIGLIN_REPELLENTS)
                .add(IBBlocks.SOUL_TORCH_BUTTON.get().asItem())
                .add(IBBlocks.SOUL_WALL_TORCH_BUTTON.get().asItem())
                .add(IBBlocks.SOUL_TORCH_LEVER.get().asItem())
                .add(IBBlocks.SOUL_WALL_TORCH_LEVER.get().asItem())
                .add(IBBlocks.SOUL_LANTERN_BUTTON.get().asItem())
                .add(IBBlocks.SOUL_LANTERN_LEVER.get().asItem());

        this.tag(STONE_BRICKS)
                .add(IBBlocks.STONE_BRICK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.MOSSY_STONE_BRICK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.CRACKED_STONE_BRICK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.CHISELED_STONE_BRICK_SECRET_BUTTON.get().asItem());
    }

    protected void generateFriendsFoesTags() {
        this.tag(FAF_COPPER_BUTTONS)
                .addTag(COPPER_BUTTONS)
                .addTag(COPPER_LARGE_BUTTONS);
    }
}