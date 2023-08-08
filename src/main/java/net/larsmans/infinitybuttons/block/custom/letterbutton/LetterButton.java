package net.larsmans.infinitybuttons.block.custom.letterbutton;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.larsmans.infinitybuttons.block.custom.button.AbstractLeverableButton;
import net.larsmans.infinitybuttons.block.custom.button.LargeButtonShape;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

import static net.larsmans.infinitybuttons.InfinityButtonsInit.LETTER_BUTTON_SCREEN_PACKET;

public class LetterButton extends AbstractLeverableButton {

    public static final EnumProperty<LetterButtonEnum> CHARACTER = EnumProperty.of("character", LetterButtonEnum.class);

    public LetterButton(FabricBlockSettings settings, boolean lever) {
        super(lever, settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(PRESSED, false).with(FACE, WallMountLocation.FLOOR).with(CHARACTER, LetterButtonEnum.NONE));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        GameMode gameMode = GameMode.DEFAULT;
        if (world.isClient) {
            ClientPlayNetworkHandler connection = MinecraftClient.getInstance().getNetworkHandler();
            assert connection != null;
            gameMode = Objects.requireNonNull(connection.getPlayerListEntry(player.getGameProfile().getId())).getGameMode();
        } else if (player instanceof ServerPlayerEntity serverPlayer) {
            gameMode = serverPlayer.interactionManager.getGameMode();
        }
        if (gameMode == GameMode.SPECTATOR)
            return ActionResult.FAIL;

        if (player.isSneaking() && gameMode != GameMode.ADVENTURE) {
            openScreen(pos, player);
            return ActionResult.success(world.isClient);
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        openScreen(pos, placer);
    }

    public void openScreen(BlockPos pos, LivingEntity entity) {
        if (entity instanceof ServerPlayerEntity player) {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeBlockPos(pos);
            ServerPlayNetworking.send(player, LETTER_BUTTON_SCREEN_PACKET, buf);
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return LargeButtonShape.outlineShape(state);
    }

    @Override
    public int getPressTicks() {
        return 30;
    }

    @Override
    protected SoundEvent getClickSound(boolean pressed) {
        return SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON;
    }

    public int getEnumId(BlockState state) {
        return state.get(CHARACTER).ordinal();
    }

    public void setState(BlockState state, World world, BlockPos pos, LetterButtonEnum buttonEnum) {
        world.setBlockState(pos, state.with(CHARACTER, buttonEnum), 3);
        this.updateNeighbors(state, world, pos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(CHARACTER);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        InfinityButtonsUtil.tooltip(tooltip, "letter_button");
    }
}
