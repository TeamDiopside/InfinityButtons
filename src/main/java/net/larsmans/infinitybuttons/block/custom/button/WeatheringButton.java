package net.larsmans.infinitybuttons.block.custom.button;

import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface WeatheringButton extends Oxidizable {

    static Optional<BlockState> getWaxed(BlockState state) {
        InfinityButtonsUtil.buildWax();
        return Optional.ofNullable(InfinityButtonsUtil.WAX_ON_BY_BLOCK.get().get(state.getBlock())).map((block) -> block.getStateWithProperties(state));
    }

    static Optional<BlockState> getUnwaxed(BlockState state) {
        InfinityButtonsUtil.buildWax();
        return Optional.ofNullable(InfinityButtonsUtil.WAX_OFF_BY_BLOCK.get().get(state.getBlock())).map((block) -> block.getStateWithProperties(state));
    }


    static Optional<BlockState> getSticky(BlockState state) {
        InfinityButtonsUtil.buildSticky();
        return Optional.ofNullable(InfinityButtonsUtil.STICKY_ON_BY_BLOCK.get().get(state.getBlock())).map((block) -> block.getStateWithProperties(state));
    }

    static Optional<BlockState> getUnsticky(BlockState state) {
        InfinityButtonsUtil.buildSticky();
        return Optional.ofNullable(InfinityButtonsUtil.STICKY_OFF_BY_BLOCK.get().get(state.getBlock())).map((block) -> block.getStateWithProperties(state));
    }

    @Override
    default @NotNull Optional<BlockState> getDegradationResult(BlockState state) {
        return getNext(state.getBlock()).map((block) -> block.getStateWithProperties(state));
    }

    static Optional<Block> getNext(Block block) {
        InfinityButtonsUtil.buildNext();
        return Optional.ofNullable(InfinityButtonsUtil.NEXT_BY_BLOCK.get().get(block));
    }

    static Optional<Block> getPrevious(Block block) {
        InfinityButtonsUtil.buildNext();
        return Optional.ofNullable(InfinityButtonsUtil.PREVIOUS_BY_BLOCK.get().get(block));
    }

    static Optional<BlockState> getPrevious(BlockState state) {
        return getPrevious(state.getBlock()).map((block) -> block.getStateWithProperties(state));
    }

    default ActionResult wax(BlockState state, World level, BlockPos blockpos, PlayerEntity player, ItemStack itemStack) {
        return getWaxed(state).map((waxedBlockState) -> {
            if (player instanceof ServerPlayerEntity) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity) player, blockpos, itemStack);
            }
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            level.setBlockState(blockpos, waxedBlockState, Block.field_31022);
            level.syncWorldEvent(player, 3003, blockpos, 0);
            return ActionResult.success(level.isClient);
        }).orElse(ActionResult.success(level.isClient));
    }

    default ActionResult scrape(BlockState state, World level, BlockPos blockpos, PlayerEntity player, ItemStack itemStack) {
        return getPrevious(state).map((previousBlockState) -> {
            if (player instanceof ServerPlayerEntity) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity) player, blockpos, itemStack);
            }
            if (!player.getAbilities().creativeMode) {
                itemStack.damage(1, player, (entity) -> entity.sendToolBreakStatus(player.getActiveHand()));
            }
            level.setBlockState(blockpos, previousBlockState, Block.field_31022);
            level.syncWorldEvent(player, 3005, blockpos, 0);
            return ActionResult.success(level.isClient);
        }).orElse(ActionResult.success(level.isClient));
    }
}
