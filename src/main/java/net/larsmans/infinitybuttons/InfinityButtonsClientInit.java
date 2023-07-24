package net.larsmans.infinitybuttons;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.block.custom.letterbutton.LetterButton;
import net.larsmans.infinitybuttons.block.custom.letterbutton.gui.LetterButtonGui;
import net.larsmans.infinitybuttons.compat.IBNethersDelightBlocks;
import net.larsmans.infinitybuttons.particle.DiamondSparkleParticle;
import net.larsmans.infinitybuttons.particle.InfinityButtonsParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.larsmans.infinitybuttons.InfinityButtonsInit.MOD_ID;

public class InfinityButtonsClientInit implements ClientModInitializer {

    public static final Identifier LETTER_BUTTON_SCREEN_PACKET = new Identifier(MOD_ID, "letter_button_screen");

    @Override
    public void onInitializeClient() {
        registerPackets();

        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> registry.register(new Identifier("infinitybuttons", "particle/diamond_sparkle"))));
        ParticleFactoryRegistry.getInstance().register(InfinityButtonsParticleTypes.DIAMOND_SPARKLE, DiamondSparkleParticle.Factory::new);

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

        transparent(InfinityButtonsBlocks.LETTER_BUTTON);
        transparent(InfinityButtonsBlocks.LETTER_LEVER);
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

    public static void registerPackets() {
        ClientPlayNetworking.registerGlobalReceiver(LETTER_BUTTON_SCREEN_PACKET, (client, handler, buf, responseSender) -> {
            BlockPos pos = buf.readBlockPos();
            World world = client.world;
            BlockState state = world.getBlockState(pos);
            if (state.getBlock() instanceof LetterButton letterButton) {
                client.execute(() -> MinecraftClient.getInstance().setScreen(new LetterButtonGui(letterButton, state, world, pos)));
            }
        });
    }
}
