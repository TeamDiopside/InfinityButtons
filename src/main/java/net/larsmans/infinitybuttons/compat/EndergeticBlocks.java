package net.larsmans.infinitybuttons.compat;

import com.teamabnormals.endergetic.core.registry.EEBlocks;
import net.larsmans.infinitybuttons.InfinityButtons;
import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.block.custom.compat.LanternCompatButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.compat.*;
import net.larsmans.infinitybuttons.block.custom.torch.compat.EnderTorchButton;
import net.larsmans.infinitybuttons.block.custom.torch.compat.EnderTorchLever;
import net.larsmans.infinitybuttons.block.custom.torch.compat.EnderWallTorchButton;
import net.larsmans.infinitybuttons.block.custom.torch.compat.EnderWallTorchLever;
import net.larsmans.infinitybuttons.item.InfinityButtonsItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class EndergeticBlocks {

    public static BlockBehaviour.Properties PROP = BlockBehaviour.Properties.of().mapColor(MapColor.NONE).noCollission().instabreak().lightLevel((state) -> 14).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY);

    public static final RegistryObject<Block> POISE_BOOKSHELF_SECRET_BUTTON = registerBlock("poise_bookshelf_secret_button",
            () -> new CompatBookshelfSecretButton(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD)
                    .ignitedByLava().strength(1.5f).noOcclusion().sound(SoundType.WOOD), byName("poise_bookshelf"))); // TODO HARVEST TAGS, REQUIRES TOOL EN HARVEST LEVEL? DATA EN ASSETS

    public static final RegistryObject<Block> CHISELED_END_STONE_BRICK_SECRET_BUTTON = registerBlock("chiseled_end_stone_brick_secret_button",
            () -> new ChiseledEndStoneBrickSecretButton(BlockBehaviour.Properties.of().mapColor(MapColor.SAND)
                    .strength(3.0f, 9.0f).sound(SoundType.STONE), byName("chiseled_end_stone_bricks")));

    public static final RegistryObject<Block> CHISELED_EUMUS_BRICK_SECRET_BUTTON = registerBlock("chiseled_eumus_brick_secret_button",
            () -> new ChiseledEumusBrickSecretButton(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE)
                    .strength(2.0f, 30.0f).sound(SoundType.STONE), byName("chiseled_eumus_bricks")));

    public static final RegistryObject<Block> CRACKED_END_STONE_BRICK_SECRET_BUTTON = registerBlock("cracked_end_stone_brick_secret_button",
            () -> new BigCompatSecretButton(BlockBehaviour.Properties.of().mapColor(MapColor.SAND)
                    .strength(3.0f, 9.0f).sound(SoundType.STONE), byName("cracked_end_stone_bricks")));

    public static final RegistryObject<Block> CRACKED_EUMUS_BRICK_SECRET_BUTTON = registerBlock("cracked_eumus_brick_secret_button",
            () -> new EumusBrickSecretButton(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE)
                    .strength(2.0f, 30.0f).sound(SoundType.STONE), byName("cracked_eumus_bricks")));

    public static final RegistryObject<Block> EUMUS_BRICK_SECRET_BUTTON = registerBlock("eumus_brick_secret_button",
            () -> new EumusBrickSecretButton(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE)
                    .strength(2.0f, 30.0f).sound(SoundType.STONE), byName("eumus_bricks")));

    public static final RegistryObject<Block> ENDER_TORCH_BUTTON = registerTorchBlock("ender_torch_button",
            () -> new EnderTorchButton(PROP, byName("ender_torch")));

    public static final RegistryObject<Block> ENDER_WALL_TORCH_BUTTON = registerTorchBlock("ender_wall_torch_button",
            () -> new EnderWallTorchButton(PROP, byName("ender_torch")));

    public static final RegistryObject<Block> ENDER_TORCH_LEVER = registerTorchBlock("ender_torch_lever",
            () -> new EnderTorchLever(PROP, byName("ender_torch")));

    public static final RegistryObject<Block> ENDER_WALL_TORCH_LEVER = registerTorchBlock("ender_wall_torch_lever",
            () -> new EnderWallTorchLever(PROP, byName("ender_torch")));

    public static final RegistryObject<Block> ENDER_LANTERN_BUTTON = registerBlock("ender_lantern_button",
            () -> new LanternCompatButton(BlockBehaviour.Properties.copy(EEBlocks.ENDER_LANTERN.get()), false, byName("ender_lantern")));

    public static final RegistryObject<Block> ENDER_LANTERN_LEVER = registerBlock("ender_lantern_lever",
            () -> new LanternCompatButton(BlockBehaviour.Properties.copy(EEBlocks.ENDER_LANTERN.get()), true, byName("ender_lantern")));

    /**
     * Methods
     */

    private static Block byName(String block) {
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("endergetic", block));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = InfinityButtonsBlocks.BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        InfinityButtonsItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> registerTorchBlock(String name, Supplier<T> block) {
        return InfinityButtonsBlocks.BLOCKS.register(name, block);
    }

    public static void registerCompatBlocks() {
        InfinityButtons.LOGGER.debug("Registering Endergetic Compat Blocks for Infinity Buttons");
    }
}
