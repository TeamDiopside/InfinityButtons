package nl.teamdiopside.infinitybuttons;

import dev.architectury.networking.NetworkManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.state.BlockState;
import nl.teamdiopside.infinitybuttons.block.faced6.LetterButton;
import nl.teamdiopside.infinitybuttons.block.faced6.LetterButtonState;

public class IBNetworking {
    public static void register() {
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, SetLetterButtonPayload.TYPE,
                SetLetterButtonPayload.STREAM_CODEC, IBNetworking::handleSetLetterButton);
    }

    public static void sendSetLetterButton(BlockPos pos, LetterButtonState letterButtonState) {
        NetworkManager.sendToServer(new SetLetterButtonPayload(pos, letterButtonState));
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
        public static final Type<SetLetterButtonPayload> TYPE =
                new Type<>(InfinityButtons.getResource("set_letter_button"));

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
}