package nl.teamdiopside.infinitybuttons.fabric.client;

import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import nl.teamdiopside.infinitybuttons.particle.DiamondSparkleParticle;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import nl.teamdiopside.infinitybuttons.registry.IBParticles;

public final class InfinityButtonsFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Diamond particle
        ParticleFactoryRegistry.getInstance().register(
                IBParticles.DIAMOND_SPARKLE.get(),
                DiamondSparkleParticle.Provider::new
        );

        setRenderMaps();
    }

    public void setRenderMaps() {
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

        // Console buttons
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.CONSOLE_BUTTON.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.CONSOLE_LEVER.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.SMALL_CONSOLE_BUTTON.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.SMALL_CONSOLE_LEVER.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.BIG_CONSOLE_BUTTON.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.BIG_CONSOLE_LEVER.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.LARGE_CONSOLE_BUTTON.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.LARGE_CONSOLE_LEVER.get(), RenderType.cutout());

        // Safety buttons
        for (RegistrySupplier<Block> blockRS : IBBlocks.SAFETY_BUTTONS.values())
            BlockRenderLayerMap.INSTANCE.putBlock(blockRS.get(), RenderType.cutout());

        // Letter Buttons
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.LETTER_BUTTON.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(IBBlocks.LETTER_LEVER.get(), RenderType.cutout());
    }
}
