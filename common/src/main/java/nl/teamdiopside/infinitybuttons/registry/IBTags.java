package nl.teamdiopside.infinitybuttons.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.MOD_ID;

public class IBTags {
    public class Blocks {
        public static final TagKey<Block> CONCRETE_POWDER_BUTTONS = createBlockTag("concrete_powder_buttons");
        public static final TagKey<Block> CONCRETE_POWDER_LARGE_BUTTONS = createBlockTag("concrete_powder_large_buttons");
        public static final TagKey<Block> LARGE_BUTTONS = createBlockTag("large_buttons");
        public static final TagKey<Block> WOODEN_LARGE_BUTTONS = createBlockTag("wooden_large_buttons");
        public static final TagKey<Block> EMERGENCY_BUTTONS = createBlockTag("emergency_buttons");
        public static final TagKey<Block> SAFE_EMERGENCY_BUTTONS = createBlockTag("safe_emergency_buttons");
        public static final TagKey<Block> NORMAL_EMERGENCY_BUTTONS = createBlockTag("normal_emergency_buttons");
        public static final TagKey<Block> NORMAL_SAFE_EMERGENCY_BUTTONS = createBlockTag("normal_safe_emergency_buttons");
        public static final TagKey<Block> SECRET_BUTTONS = createBlockTag("secret_buttons");
        public static final TagKey<Block> WOODEN_SECRET_BUTTONS = createBlockTag("wooden_secret_buttons");
        public static final TagKey<Block> BOOKSHELF_SECRET_BUTTONS = createBlockTag("bookshelf_secret_buttons");
        public static final TagKey<Block> TORCH_BUTTONS = createBlockTag("torch_buttons");
        public static final TagKey<Block> CONSOLE_BUTTONS = createBlockTag("console_buttons");
        public static final TagKey<Block> LANTERN_BUTTONS = createBlockTag("lantern_buttons");
        public static final TagKey<Block> COPPER_BUTTONS = createBlockTag("copper_buttons");
        public static final TagKey<Block> COPPER_LARGE_BUTTONS = createBlockTag("copper_large_buttons");
    }

    public class Items {
        public static final TagKey<Item> CONCRETE_POWDER_BUTTONS = createItemTag("concrete_powder_buttons");
        public static final TagKey<Item> CONCRETE_POWDER_LARGE_BUTTONS = createItemTag("concrete_powder_large_buttons");
        public static final TagKey<Item> LARGE_BUTTONS = createItemTag("large_buttons");
        public static final TagKey<Item> WOODEN_LARGE_BUTTONS = createItemTag("wooden_large_buttons");
        public static final TagKey<Item> EMERGENCY_BUTTONS = createItemTag("emergency_buttons");
        public static final TagKey<Item> SAFE_EMERGENCY_BUTTONS = createItemTag("safe_emergency_buttons");
        public static final TagKey<Item> NORMAL_EMERGENCY_BUTTONS = createItemTag("normal_emergency_buttons");
        public static final TagKey<Item> NORMAL_SAFE_EMERGENCY_BUTTONS = createItemTag("normal_safe_emergency_buttons");
        public static final TagKey<Item> SECRET_BUTTONS = createItemTag("secret_buttons");
        public static final TagKey<Item> WOODEN_SECRET_BUTTONS = createItemTag("wooden_secret_buttons");
        public static final TagKey<Item> BOOKSHELF_SECRET_BUTTONS = createItemTag("bookshelf_secret_buttons");
        public static final TagKey<Item> TORCH_BUTTONS = createItemTag("torch_buttons");
        public static final TagKey<Item> CONSOLE_BUTTONS = createItemTag("console_buttons");
        public static final TagKey<Item> LANTERN_BUTTONS = createItemTag("lantern_buttons");
        public static final TagKey<Item> COPPER_BUTTONS = createItemTag("copper_buttons");
        public static final TagKey<Item> COPPER_LARGE_BUTTONS = createItemTag("copper_large_buttons");
    }


    private static TagKey<Block> createBlockTag(String tagKey) {
        return TagKey.create(
                Registries.BLOCK,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, tagKey)
        );
    }

    private static TagKey<Item> createItemTag(String tagKey) {
        return TagKey.create(
                Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, tagKey)
        );
    }
}
