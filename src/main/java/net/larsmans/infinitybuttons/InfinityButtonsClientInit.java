package net.larsmans.infinitybuttons;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.block.custom.letterbutton.LetterButton;
import net.larsmans.infinitybuttons.block.custom.letterbutton.gui.LetterButtonGui;
import net.larsmans.infinitybuttons.config.AlarmEnum;
import net.larsmans.infinitybuttons.particle.DiamondSparkleParticle;
import net.larsmans.infinitybuttons.particle.InfinityButtonsParticleTypes;
import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import static net.larsmans.infinitybuttons.InfinityButtonsInit.*;

public class InfinityButtonsClientInit implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerPackets();
        registerClientEvents();

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
        ClientPlayNetworking.registerGlobalReceiver(ALARM_PACKET, (client, handler, buf, responseSender) -> {
            if (InfinityButtonsInit.CONFIG.muteAlarmSound()) {
                return;
            }
            BlockPos pos = buf.readBlockPos();
            AlarmEnum alarmEnum = buf.readEnumConstant(AlarmEnum.class);
            if (alarmEnum == AlarmEnum.GLOBAL) {
                playGlobalSound(client.world, pos, InfinityButtonsSounds.ALARM, SoundCategory.BLOCKS);
            } else {
                assert client.world != null;
                client.world.playSound(client.player, pos, InfinityButtonsSounds.ALARM, SoundCategory.BLOCKS, (float) InfinityButtonsInit.CONFIG.alarmSoundRange(), 1);
            }
        });
        ClientPlayNetworking.registerGlobalReceiver(JADE_PACKET, (client, handler, buf, responseSender) -> forceHidden = buf.readBoolean());
    }

    public static void playGlobalSound(World world, BlockPos pos, SoundEvent soundEvent, SoundCategory soundCategory) {
        Camera cam = MinecraftClient.getInstance().gameRenderer.getCamera();
        if (cam.isReady()) {
            world.playSound(pos.getX(), pos.getY(), pos.getZ(), soundEvent, soundCategory, (float)cam.getPos().distanceTo(Vec3d.ofCenter(pos))/16.0F + 20.0F, 1.0F, false);
        }
    }

    private static boolean forceHidden = true;

    public static boolean getForceHidden() {
        return forceHidden;
    }

    public static void registerClientEvents() {
        ClientPlayConnectionEvents.JOIN.register((clientPlayNetworkHandler, packetSender, minecraftClient) -> ClientPlayNetworking.send(REQUEST_JADE_PACKET, PacketByteBufs.create().writeString("Safety Buttons are Stylish!")));
    }
}
