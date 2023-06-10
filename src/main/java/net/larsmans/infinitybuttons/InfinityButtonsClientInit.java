package net.larsmans.infinitybuttons;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.loader.api.FabricLoader;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.compat.IBNethersDelightBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

public class InfinityButtonsClientInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        transparent(InfinityButtonsBlocks.TORCH_BUTTON);
        transparent(InfinityButtonsBlocks.WALL_TORCH_BUTTON);
        transparent(InfinityButtonsBlocks.TORCH_LEVER);
        transparent(InfinityButtonsBlocks.WALL_TORCH_LEVER);
        transparent(InfinityButtonsBlocks.SOUL_TORCH_BUTTON);
        transparent(InfinityButtonsBlocks.SOUL_WALL_TORCH_BUTTON);
        transparent(InfinityButtonsBlocks.SOUL_TORCH_LEVER);
        transparent(InfinityButtonsBlocks.SOUL_WALL_TORCH_LEVER);
        transparent(InfinityButtonsBlocks.REDSTONE_TORCH_BUTTON);
        transparent(InfinityButtonsBlocks.REDSTONE_WALL_TORCH_BUTTON);
        transparent(InfinityButtonsBlocks.REDSTONE_TORCH_LEVER);
        transparent(InfinityButtonsBlocks.REDSTONE_WALL_TORCH_LEVER);

        transparent(InfinityButtonsBlocks.RED_SAFE_EMERGENCY_BUTTON);
        transparent(InfinityButtonsBlocks.ORANGE_SAFE_EMERGENCY_BUTTON);
        transparent(InfinityButtonsBlocks.YELLOW_SAFE_EMERGENCY_BUTTON);
        transparent(InfinityButtonsBlocks.LIME_SAFE_EMERGENCY_BUTTON);
        transparent(InfinityButtonsBlocks.GREEN_SAFE_EMERGENCY_BUTTON);
        transparent(InfinityButtonsBlocks.CYAN_SAFE_EMERGENCY_BUTTON);
        transparent(InfinityButtonsBlocks.LIGHT_BLUE_SAFE_EMERGENCY_BUTTON);
        transparent(InfinityButtonsBlocks.BLUE_SAFE_EMERGENCY_BUTTON);
        transparent(InfinityButtonsBlocks.PURPLE_SAFE_EMERGENCY_BUTTON);
        transparent(InfinityButtonsBlocks.MAGENTA_SAFE_EMERGENCY_BUTTON);
        transparent(InfinityButtonsBlocks.PINK_SAFE_EMERGENCY_BUTTON);
        transparent(InfinityButtonsBlocks.BROWN_SAFE_EMERGENCY_BUTTON);
        transparent(InfinityButtonsBlocks.WHITE_SAFE_EMERGENCY_BUTTON);
        transparent(InfinityButtonsBlocks.LIGHT_GRAY_SAFE_EMERGENCY_BUTTON);
        transparent(InfinityButtonsBlocks.GRAY_SAFE_EMERGENCY_BUTTON);
        transparent(InfinityButtonsBlocks.BLACK_SAFE_EMERGENCY_BUTTON);
        transparent(InfinityButtonsBlocks.FANCY_SAFE_EMERGENCY_BUTTON);

        transparent(InfinityButtonsBlocks.LANTERN_BUTTON);
        transparent(InfinityButtonsBlocks.LANTERN_LEVER);
        transparent(InfinityButtonsBlocks.SOUL_LANTERN_BUTTON);
        transparent(InfinityButtonsBlocks.SOUL_LANTERN_LEVER);

        transparent(InfinityButtonsBlocks.SMALL_CONSOLE_BUTTON);
        transparent(InfinityButtonsBlocks.SMALL_CONSOLE_LEVER);
        transparent(InfinityButtonsBlocks.CONSOLE_BUTTON);
        transparent(InfinityButtonsBlocks.CONSOLE_LEVER);
        transparent(InfinityButtonsBlocks.LARGE_CONSOLE_BUTTON);
        transparent(InfinityButtonsBlocks.LARGE_CONSOLE_LEVER);
        transparent(InfinityButtonsBlocks.BIG_CONSOLE_BUTTON);
        transparent(InfinityButtonsBlocks.BIG_CONSOLE_LEVER);

        if (FabricLoader.getInstance().isModLoaded("nethersdelight")) {
            transparent(IBNethersDelightBlocks.PROPELPLANT_TORCH_BUTTON);
            transparent(IBNethersDelightBlocks.PROPELPLANT_WALL_TORCH_BUTTON);
            transparent(IBNethersDelightBlocks.PROPELPLANT_TORCH_LEVER);
            transparent(IBNethersDelightBlocks.PROPELPLANT_WALL_TORCH_LEVER);
        }
    }

    private void transparent(Block block) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
    }
}
