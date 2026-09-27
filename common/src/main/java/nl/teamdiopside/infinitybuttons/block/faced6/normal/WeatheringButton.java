package nl.teamdiopside.infinitybuttons.block.faced6.normal;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import nl.teamdiopside.infinitybuttons.registry.IBAdvancementTriggers;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static net.minecraft.world.ItemInteractionResult.sidedSuccess;

public interface WeatheringButton extends WeatheringCopper {

    private Optional<BlockState> getOfType(CopperButtonType type, WeatherState weatherState, BlockState blockState) {
        if (blockState.getBlock() instanceof CopperButton copperButton) {
            return Optional.of(IBBlocks.COPPER_BUTTONS.get(type, weatherState).get(copperButton.isLarge()).withPropertiesOf(blockState));
        }
        return Optional.empty();
    }

    private Optional<BlockState> getOfType(CopperButtonType type, BlockState state) {
        if (state.getBlock() instanceof CopperButton copperButton) {
            return getOfType(type, copperButton.getAge(), state);
        }
        return Optional.empty();
    }

    private static WeatherState nextState(WeatherState state) {
        switch (state) {
            case UNAFFECTED -> {
                return WeatherState.EXPOSED;
            }
            case EXPOSED -> {
                return WeatherState.WEATHERED;
            }
            case WEATHERED, OXIDIZED -> {
                return WeatherState.OXIDIZED;
            }
            default -> {
                return state;
            }
        }
    }

    private static WeatherState previousState(WeatherState state) {
        switch (state) {
            case UNAFFECTED, EXPOSED -> {
                return WeatherState.UNAFFECTED;
            }
            case WEATHERED -> {
                return WeatherState.EXPOSED;
            }
            case OXIDIZED -> {
                return WeatherState.WEATHERED;
            }
            default -> {
                return state;
            }
        }
    }

    @Override
    default @NotNull Optional<BlockState> getNext(BlockState state) {
        if (state.getBlock() instanceof CopperButton copperButton) {
            WeatherState weatherState = nextState(copperButton.getAge());
            if (copperButton.getAge() == weatherState) return Optional.empty();
            return getOfType(copperButton.getButtonType(), weatherState, state);
        }
        return Optional.empty();
    }

    private Optional<BlockState> getPrevious(BlockState state) {
        if (state.getBlock() instanceof CopperButton copperButton) {
            WeatherState weatherState = previousState(copperButton.getAge());
            if (copperButton.getAge() == weatherState) return Optional.empty();
            return getOfType(copperButton.getButtonType(), weatherState, state);
        }
        return Optional.empty();
    }

    private void itemUsed(BlockPos pos, Player player, ItemStack stack) {
        if (player instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);
        }
    }

    private void hurtAxe(Player player, ItemStack stack) {
        stack.hurtAndBreak(1, player, player.getUsedItemHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
    }

    default ItemInteractionResult wax(BlockState state, Level level, BlockPos pos, Player player, ItemStack stack) {
        return getOfType(CopperButtonType.WAXED, state).map((waxedBlockState) -> {
            itemUsed(pos, player, stack);
            if (!player.getAbilities().instabuild) stack.shrink(1);
            level.setBlock(pos, waxedBlockState, Block.UPDATE_ALL_IMMEDIATE);
            level.levelEvent(player, 3003, pos, 0);
            level.playSound(player, pos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0f, 1.0f);
            return sidedSuccess(level.isClientSide());
        }).orElse(sidedSuccess(level.isClientSide()));
    }

    default ItemInteractionResult scrape(BlockState state, Level level, BlockPos pos, Player player, ItemStack stack) {
        return getPrevious(state).map((previousBlockState) -> {
            itemUsed(pos, player, stack);
            if (!player.getAbilities().instabuild) hurtAxe(player, stack);
            level.setBlock(pos, previousBlockState, Block.UPDATE_ALL_IMMEDIATE);
            level.levelEvent(player, 3005, pos, 0);
            level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0f, 1.0f);
            return sidedSuccess(level.isClientSide());
        }).orElse(sidedSuccess(level.isClientSide()));
    }

    default ItemInteractionResult scrapeWax(BlockState state, Level level, BlockPos pos, Player player, ItemStack stack) {
        return getOfType(CopperButtonType.NORMAL, state).map((waxedBlockState) -> {
            itemUsed(pos, player, stack);
            if (!player.getAbilities().instabuild) hurtAxe(player, stack);
            level.setBlock(pos, waxedBlockState, Block.UPDATE_ALL_IMMEDIATE);
            level.levelEvent(player, 3004, pos, 0);
            level.playSound(player, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0f, 1.0f);
            if (player instanceof ServerPlayer serverPlayer)
                IBAdvancementTriggers.WAX_OFF_TRIGGER.get().trigger(serverPlayer);
            return sidedSuccess(level.isClientSide());
        }).orElse(sidedSuccess(level.isClientSide()));
    }

    default ItemInteractionResult sticky(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
        return getOfType(CopperButtonType.STICKY, state).map((waxedBlockState) -> {
            itemUsed(pos, player, stack);
            if (!player.getAbilities().instabuild) {
                player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
            }
            level.setBlock(pos, waxedBlockState, Block.UPDATE_ALL_IMMEDIATE);
            level.levelEvent(player, 3003, pos, 0);
            level.playSound(player, pos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0f, 1.0f);
            return sidedSuccess(level.isClientSide());
        }).orElse(sidedSuccess(level.isClientSide()));
    }

    default ItemInteractionResult unSticky(BlockState blockState, Level level, BlockPos blockPos, Player player, ItemStack itemStack) {
        return getOfType(CopperButtonType.WAXED, blockState).map((waxedBlockState) -> {
            itemUsed(blockPos, player, itemStack);
            if (!player.getAbilities().instabuild) hurtAxe(player, itemStack);
            level.setBlock(blockPos, waxedBlockState.setValue(ButtonBlock.POWERED, false), Block.UPDATE_ALL_IMMEDIATE);
            level.levelEvent(player, 3004, blockPos, 0);
            level.playSound(player, blockPos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0f, 1.0f);
            return sidedSuccess(level.isClientSide());
        }).orElse(ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION);
    }
}
