package net.larsmans.infinitybuttons.compat;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.larsmans.infinitybuttons.block.custom.HoglinMountButton;
import net.larsmans.infinitybuttons.block.custom.torch.PropelTorchButton;
import net.larsmans.infinitybuttons.block.custom.torch.PropelTorchLever;
import net.larsmans.infinitybuttons.block.custom.torch.PropelWallTorchButton;
import net.larsmans.infinitybuttons.block.custom.torch.PropelWallTorchLever;
import net.larsmans.infinitybuttons.item.InfinityButtonsItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class IBNethersDelightBlocks {

    public static final Block HOGLIN_MOUNT_BUTTON = registerBlockWithItem("hoglin_mount_button",
            new HoglinMountButton(FabricBlockSettings.of(Material.WOOL, MapColor.BROWN).strength(0.8f).sounds(BlockSoundGroup.WOOL)));

    public static final Block PROPELPLANT_TORCH_BUTTON = registerBlock("propelplant_torch_button", new PropelTorchButton(torchSettings()));

    public static final Block PROPELPLANT_WALL_TORCH_BUTTON = registerBlock("propelplant_wall_torch_button", new PropelWallTorchButton(torchSettings().dropsLike(PROPELPLANT_TORCH_BUTTON)));

    public static final Block PROPELPLANT_TORCH_LEVER = registerBlock("propelplant_torch_lever", new PropelTorchLever(torchSettings()));

    public static final Block PROPELPLANT_WALL_TORCH_LEVER = registerBlock("propelplant_wall_torch_lever", new PropelWallTorchLever(torchSettings().dropsLike(PROPELPLANT_TORCH_LEVER)));

    public static FabricBlockSettings torchSettings() {
        return FabricBlockSettings.of(Material.DECORATION).nonOpaque().noCollision().breakInstantly().luminance(12).sounds(BlockSoundGroup.WOOD);
    }

    private static Block registerBlockWithItem(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registry.BLOCK, new Identifier("infinitybuttons", name), block);
    }

    private static Block registerBlock(String name, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier("infinitybuttons", name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registry.ITEM, new Identifier("infinitybuttons", name),
                new BlockItem(block, new FabricItemSettings().group(InfinityButtonsItemGroup.INFINITYBUTTONS)));
    }

    public static void registerCompatBlocks() {
        InfinityButtonsInit.LOGGER.debug("Registering Compat Blocks for Infinity Buttons");
    }
}
