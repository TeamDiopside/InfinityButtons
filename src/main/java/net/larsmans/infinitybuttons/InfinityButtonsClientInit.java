package net.larsmans.infinitybuttons;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.minecraft.client.render.RenderLayer;

public class InfinityButtonsClientInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(InfinityButtonsBlocks.TORCH_BUTTON, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(InfinityButtonsBlocks.WALL_TORCH_BUTTON, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(InfinityButtonsBlocks.TORCH_LEVER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(InfinityButtonsBlocks.WALL_TORCH_LEVER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(InfinityButtonsBlocks.SOUL_TORCH_BUTTON, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(InfinityButtonsBlocks.SOUL_WALL_TORCH_BUTTON, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(InfinityButtonsBlocks.SOUL_TORCH_LEVER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(InfinityButtonsBlocks.SOUL_WALL_TORCH_LEVER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(InfinityButtonsBlocks.REDSTONE_TORCH_BUTTON, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(InfinityButtonsBlocks.REDSTONE_WALL_TORCH_BUTTON, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(InfinityButtonsBlocks.REDSTONE_TORCH_LEVER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(InfinityButtonsBlocks.REDSTONE_WALL_TORCH_LEVER, RenderLayer.getCutout());
    }
}
