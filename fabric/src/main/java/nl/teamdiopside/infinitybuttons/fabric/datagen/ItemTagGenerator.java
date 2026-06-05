package nl.teamdiopside.infinitybuttons.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import nl.teamdiopside.infinitybuttons.InfinityButtons;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends FabricTagProvider<Item> {

    public ItemTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.ITEM, registriesFuture);
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
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("infinitybuttons", name));
    }

    public static final TagKey<Item> BUTTONS = edit("buttons");
    public static final TagKey<Item> NON_FLAMMABLE_WOOD = edit("non_flammable_wood");
    public static final TagKey<Item> PIGLIN_REPELLENTS = edit("piglin_repellents");
    public static final TagKey<Item> STONE_BRICKS = edit("stone_bricks");

    static TagKey<Item> edit(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("minecraft", name));
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        // Vanilla Tags
        getOrCreateTagBuilder(BUTTONS)
                .addTag(COPPER_BUTTONS)
                .addTag(CONCRETE_POWDER_BUTTONS);

        for (var entry : IBBlocks.SMALL_LARGE_BUTTONS.values()) {
            getOrCreateTagBuilder(BUTTONS)
                    .add(entry.getSmall().asItem())
                    .add(entry.getLarge().asItem());
        }

        getOrCreateTagBuilder(NON_FLAMMABLE_WOOD)
                .add(IBRegistryUtils.getItemByID(InfinityButtons.MOD_ID, "crimson_large_button").asItem())
                .add(IBRegistryUtils.getItemByID(InfinityButtons.MOD_ID, "warped_large_button").asItem())
                .add(IBBlocks.CRIMSON_PLANK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.WARPED_PLANK_SECRET_BUTTON.get().asItem());

        getOrCreateTagBuilder(PIGLIN_REPELLENTS)
                .add(IBBlocks.SOUL_TORCH_BUTTON.get().asItem())
                .add(IBBlocks.SOUL_TORCH_LEVER.get().asItem())
                .add(IBBlocks.SOUL_LANTERN_BUTTON.get().asItem())
                .add(IBBlocks.SOUL_LANTERN_LEVER.get().asItem());

        getOrCreateTagBuilder(STONE_BRICKS)
                .add(IBBlocks.STONE_BRICK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.MOSSY_STONE_BRICK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.CRACKED_STONE_BRICK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.CHISELED_STONE_BRICK_SECRET_BUTTON.get().asItem());

        // Concrete Powder
        for (var value : DyeColor.values()) {
            getOrCreateTagBuilder(CONCRETE_POWDER_BUTTONS)
                    .add(IBRegistryUtils.getItemByID(InfinityButtons.MOD_ID,
                            value.name().toLowerCase() + "concrete_powder_button").asItem()
                    );

            getOrCreateTagBuilder(CONCRETE_POWDER_LARGE_BUTTONS)
                    .add(IBRegistryUtils.getItemByID(InfinityButtons.MOD_ID,
                            value.name().toLowerCase() + "concrete_powder_large_button").asItem()
                    );
        }

        getOrCreateTagBuilder(LARGE_BUTTONS)
                .addTag(WOODEN_LARGE_BUTTONS)
                .addTag(COPPER_LARGE_BUTTONS)
                .addTag(CONCRETE_POWDER_LARGE_BUTTONS); // TODO
//                .add(IBBlocks.STONE_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.DEEPSLATE_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.GRANITE_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.DIORITE_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.ANDESITE_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.TUFF_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.DRIPSTONE_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.CALCITE_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.POLISHED_BLACKSTONE_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.IRON_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.GOLD_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.EMERALD_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.DIAMOND_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.PRISMARINE_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.PRISMARINE_BRICK_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.DARK_PRISMARINE_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.SAND_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.RED_SAND_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.GRAVEL_LARGE_BUTTON.get().asItem());

        getOrCreateTagBuilder(WOODEN_LARGE_BUTTONS); // TODO
//                .add(IBBlocks.OAK_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.SPRUCE_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.BIRCH_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.JUNGLE_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.ACACIA_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.DARK_OAK_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.MANGROVE_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.CRIMSON_LARGE_BUTTON.get().asItem())
//                .add(IBBlocks.WARPED_LARGE_BUTTON.get().asItem());

        // Emergency Buttons
        for (var entry : IBBlocks.EMERGENCY_BUTTONS.values()) {
            getOrCreateTagBuilder(EMERGENCY_BUTTONS)
                    .add(entry.get().asItem());

            if (entry == IBBlocks.FANCY_EMERGENCY_BUTTON) continue;
            getOrCreateTagBuilder(NORMAL_EMERGENCY_BUTTONS)
                    .add(entry.get().asItem());
        }
        for (var entry : IBBlocks.SAFE_EMERGENCY_BUTTONS.values()) {
            getOrCreateTagBuilder(SAFE_EMERGENCY_BUTTONS)
                    .add(entry.get().asItem());
            if (entry == IBBlocks.FANCY_SAFE_EMERGENCY_BUTTON) continue;
            getOrCreateTagBuilder(NORMAL_SAFE_EMERGENCY_BUTTONS)
                    .add(entry.get().asItem());
        }

        // Secret Buttons
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
                    .add(entry.get().asItem());
        }

        getOrCreateTagBuilder(WOODEN_SECRET_BUTTONS)
                .addTag(BOOKSHELF_SECRET_BUTTONS)
                .add(IBBlocks.OAK_PLANK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.SPRUCE_PLANK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.BIRCH_PLANK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.JUNGLE_PLANK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.ACACIA_PLANK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.DARK_OAK_PLANK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.MANGROVE_PLANK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.CHERRY_PLANK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.BAMBOO_PLANK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.CRIMSON_PLANK_SECRET_BUTTON.get().asItem())
                .add(IBBlocks.WARPED_PLANK_SECRET_BUTTON.get().asItem());

        getOrCreateTagBuilder(BOOKSHELF_SECRET_BUTTONS)
                .add(IBBlocks.BOOKSHELF_SECRET_BUTTON.get().asItem());


        getOrCreateTagBuilder(TORCH_BUTTONS)
                .add(IBBlocks.TORCH_BUTTON.get().asItem())
                .add(IBBlocks.TORCH_LEVER.get().asItem())
                .add(IBBlocks.SOUL_TORCH_BUTTON.get().asItem())
                .add(IBBlocks.SOUL_TORCH_LEVER.get().asItem())
                .add(IBBlocks.REDSTONE_TORCH_BUTTON.get().asItem())
                .add(IBBlocks.REDSTONE_TORCH_LEVER.get().asItem())
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "propelplant_torch_button"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("infinitybuttons", "propelplant_torch_lever"));

        getOrCreateTagBuilder(CONSOLE_BUTTONS)
                .add(IBBlocks.SMALL_CONSOLE_BUTTON.get().asItem())
                .add(IBBlocks.SMALL_CONSOLE_LEVER.get().asItem())
                .add(IBBlocks.CONSOLE_BUTTON.get().asItem())
                .add(IBBlocks.CONSOLE_LEVER.get().asItem())
                .add(IBBlocks.LARGE_CONSOLE_BUTTON.get().asItem())
                .add(IBBlocks.LARGE_CONSOLE_LEVER.get().asItem())
                .add(IBBlocks.BIG_CONSOLE_BUTTON.get().asItem())
                .add(IBBlocks.BIG_CONSOLE_LEVER.get().asItem());

        getOrCreateTagBuilder(LANTERN_BUTTONS)
                .add(IBBlocks.LANTERN_BUTTON.get().asItem())
                .add(IBBlocks.LANTERN_LEVER.get().asItem())
                .add(IBBlocks.SOUL_LANTERN_BUTTON.get().asItem())
                .add(IBBlocks.SOUL_LANTERN_LEVER.get().asItem());

        for (var entry : IBBlocks.COPPER_BUTTONS.values()) {
            getOrCreateTagBuilder(COPPER_BUTTONS)
                    .add(entry.getSmall().asItem());
            getOrCreateTagBuilder(COPPER_LARGE_BUTTONS)
                    .add(entry.getLarge().asItem());
        }
    }
}