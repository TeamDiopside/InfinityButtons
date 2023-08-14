package net.larsmans.infinitybuttons.block.custom.button;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StickyCopperButton extends AbstractSmallButton {

    public StickyCopperButton(FabricBlockSettings settings, boolean large) {
        super(false, large, settings);
    }

    @Override
    public int getPressTicks() {
        return 0;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.getItem() instanceof AxeItem) {
            return WeatheringButton.getUnsticky(state).map((waxedBlockState) -> {
                if (player instanceof ServerPlayerEntity) {
                    Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity) player, pos, itemStack);
                }
                if (!player.getAbilities().creativeMode) {
                    itemStack.damage(1, player, (entity) -> entity.sendToolBreakStatus(player.getActiveHand()));
                }
                world.setBlockState(pos, waxedBlockState.with(PRESSED, false), Block.field_31022);
                world.syncWorldEvent(player, 3004, pos, 0);
                world.playSound(player, pos, SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.BLOCKS, 1.0f, 1.0f);
                return ActionResult.success(world.isClient);
            }).orElse(ActionResult.PASS);
        } else {
            if (state.get(PRESSED)) {
                this.powerOff(state, world, pos);
                this.playClickSound(player, world, pos, false);
                world.emitGameEvent(player, GameEvent.BLOCK_DEACTIVATE, pos);
            } else {
                this.powerOn(state, world, pos);
                this.playClickSound(player, world, pos, true);
                world.emitGameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
            }
        }
        return ActionResult.success(world.isClient);
    }

    @Override
    public void powerOn(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, state.with(PRESSED, true), Block.NOTIFY_ALL);
        this.updateNeighbors(state, world, pos);
    }

    public void powerOff(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, state.with(PRESSED, false), Block.NOTIFY_ALL);
        this.updateNeighbors(state, world, pos);
    }

    @Override
    protected void playClickSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, boolean powered) {
        world.playSound(powered ? player : null, pos, this.getClickSound(powered), SoundCategory.BLOCKS, 1f, powered ? 0.6f : 0.5f);
    }

    @Override
    protected SoundEvent getClickSound(boolean powered) {
        return SoundEvents.BLOCK_COPPER_BREAK;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        InfinityButtonsUtil.tooltip(tooltip, "sticky_copper_button");
    }
}