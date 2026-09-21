package nl.teamdiopside.infinitybuttons;

import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.networking.NetworkManager;
import dev.architectury.platform.Platform;
import net.fabricmc.api.EnvType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import nl.teamdiopside.infinitybuttons.block.faced6.LetterButton;
import nl.teamdiopside.infinitybuttons.block.faced6.LetterButtonState;
import nl.teamdiopside.infinitybuttons.block.faced6.console.ConsoleButtonBlockEntity;
import nl.teamdiopside.infinitybuttons.compat.jade.JadeIntegration;

public class IBNetworking {
    public static void register() {
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, SetLetterButtonPayload.TYPE,
                SetLetterButtonPayload.STREAM_CODEC, IBNetworking::handleSetLetterButton);

        NetworkManager.registerReceiver(NetworkManager.Side.C2S, SetConsoleButtonPayload.TYPE,
                SetConsoleButtonPayload.STREAM_CODEC, IBNetworking::handleSetConsoleButton);

        // Listen to 'Force Jade Camouflage' packets
        if (Platform.getEnv() == EnvType.CLIENT) {
            NetworkManager.registerReceiver(NetworkManager.Side.S2C, JadeSyncPayload.TYPE,
                    JadeSyncPayload.STREAM_CODEC, IBNetworking::handleJadeSync);
        }

        // Send 'Force Jade Camouflage' packets
        PlayerEvent.PLAYER_JOIN.register(player -> {
            sendJadeSyncToAll(player.server);
        });
    }

    public static void sendJadeSyncToAll(net.minecraft.server.MinecraftServer server) {
        if (server == null) return;

        JadeSyncPayload payload = new JadeSyncPayload(IBConfig.forceJadeCamouflage());
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            NetworkManager.sendToPlayer(player, payload);
        }
    }

    public static void sendSetLetterButton(BlockPos pos, LetterButtonState letterButtonState) {
        NetworkManager.sendToServer(new SetLetterButtonPayload(pos, letterButtonState));
    }

    public static void sendSetConsoleButton(BlockPos pos, boolean isLever, int minPressTicks, int maxPressTicks, ItemStack keyItem) {
        NetworkManager.sendToServer(new SetConsoleButtonPayload(pos, isLever, minPressTicks, maxPressTicks, keyItem));
    }

    private static void handleSetConsoleButton(SetConsoleButtonPayload payload, NetworkManager.PacketContext context) {
        context.queue(() -> {
            ServerPlayer player = (ServerPlayer) context.getPlayer();

            if (player == null) return;
            if (!player.level().isLoaded(payload.pos())) return;

            BlockEntity entity = player.level().getBlockEntity(payload.pos());
            if (!(entity instanceof ConsoleButtonBlockEntity consoleEntity)) return;

            ItemStack keyItem = payload.keyItem().isEmpty() ? null : payload.keyItem();
            consoleEntity.configure(payload.isLever(), payload.minPressTicks(), payload.maxPressTicks(), keyItem);
        });
    }

    public record SetConsoleButtonPayload(BlockPos pos, boolean isLever, int minPressTicks, int maxPressTicks, ItemStack keyItem) implements CustomPacketPayload {
        public static final Type<SetConsoleButtonPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(InfinityButtons.MOD_ID, "set_console_button"));

        public static final StreamCodec<RegistryFriendlyByteBuf, SetConsoleButtonPayload> STREAM_CODEC = StreamCodec.of(
                (buf, payload) -> {
                    buf.writeBlockPos(payload.pos());
                    buf.writeBoolean(payload.isLever());
                    buf.writeInt(payload.minPressTicks());
                    buf.writeInt(payload.maxPressTicks());
                    ItemStack.OPTIONAL_STREAM_CODEC.encode(buf, payload.keyItem());
                },
                buf -> new SetConsoleButtonPayload(
                        buf.readBlockPos(),
                        buf.readBoolean(),
                        buf.readInt(),
                        buf.readInt(),
                        ItemStack.OPTIONAL_STREAM_CODEC.decode(buf)
                )
        );

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    private static void handleSetLetterButton(SetLetterButtonPayload payload, NetworkManager.PacketContext context) {
        context.queue(() -> {
            ServerPlayer player = (ServerPlayer) context.getPlayer();

            if (player == null) return;
            if (!player.level().isLoaded(payload.pos())) return;

            BlockState state = player.level().getBlockState(payload.pos());

            if (!(state.getBlock() instanceof LetterButton)) return;

            player.level().setBlock(payload.pos(), state.setValue(LetterButton.CHARACTER, payload.letterButtonState()), 3);
        });
    }

    public record SetLetterButtonPayload(BlockPos pos, LetterButtonState letterButtonState) implements CustomPacketPayload {
        public static final Type<SetLetterButtonPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(InfinityButtons.MOD_ID, "set_letter_button"));

        public static final StreamCodec<RegistryFriendlyByteBuf, SetLetterButtonPayload> STREAM_CODEC = StreamCodec.of(
                (buf, payload) -> {
                    buf.writeBlockPos(payload.pos());
                    buf.writeEnum(payload.letterButtonState());
                },
                buf -> new SetLetterButtonPayload(
                        buf.readBlockPos(),
                        buf.readEnum(LetterButtonState.class)
                )
        );

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }


    private static void handleJadeSync(JadeSyncPayload payload, NetworkManager.PacketContext context) {
        context.queue(() -> JadeIntegration.setServerMandatesCamouflage(payload.forceHide()));
    }

    public record JadeSyncPayload(boolean forceHide) implements CustomPacketPayload {
        public static final Type<JadeSyncPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(InfinityButtons.MOD_ID, "jade_sync"));

        public static final StreamCodec<RegistryFriendlyByteBuf, JadeSyncPayload> STREAM_CODEC = StreamCodec.of(
                (buf, payload) -> buf.writeBoolean(payload.forceHide()),
                buf -> new JadeSyncPayload(buf.readBoolean())
        );

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }
}