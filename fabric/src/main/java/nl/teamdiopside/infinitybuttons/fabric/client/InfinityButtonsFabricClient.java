package nl.teamdiopside.infinitybuttons.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;

public final class InfinityButtonsFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.

        // Torches
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.TORCH_BUTTON.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.TORCH_LEVER.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.REDSTONE_TORCH_BUTTON.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.REDSTONE_TORCH_LEVER.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.SOUL_TORCH_BUTTON.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.SOUL_TORCH_LEVER.get(), RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.WALL_TORCH_BUTTON.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.WALL_TORCH_LEVER.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.REDSTONE_WALL_TORCH_BUTTON.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.REDSTONE_WALL_TORCH_LEVER.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.SOUL_WALL_TORCH_BUTTON.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.SOUL_WALL_TORCH_LEVER.get(), RenderType.cutout());

        // Consoles
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.CONSOLE_BUTTON.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.CONSOLE_LEVER.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.SMALL_CONSOLE_BUTTON.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.SMALL_CONSOLE_LEVER.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.BIG_CONSOLE_BUTTON.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.BIG_CONSOLE_LEVER.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.LARGE_CONSOLE_BUTTON.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.LARGE_CONSOLE_LEVER.get(), RenderType.cutout());
    }
}
