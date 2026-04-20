package nl.teamdiopside.infinitybuttons.block.normal;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import nl.teamdiopside.infinitybuttons.registry.RegistryUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static nl.teamdiopside.infinitybuttons.InfinityButtonsUtil.sided;

public interface WeatheringButton extends WeatheringCopper {

    private Optional<BlockState> getOfType(CopperButtonType type, WeatherState weatherState, BlockState state) {
        if (state.getBlock() instanceof CopperButton copperButton) {
            return Optional.of(IBBlocks.COPPER_BUTTONS.get(type).get(weatherState).get(copperButton.isLarge()).withPropertiesOf(state));
        }
        return Optional.empty();
    }

    private Optional<BlockState> getOfType(CopperButtonType type, BlockState state) {
        if (state.getBlock() instanceof CopperButton copperButton) {
            return getOfType(type, copperButton.getAge(), state);
        }
        return Optional.empty();
    }

    @Override
    default @NotNull Optional<BlockState> getNext(BlockState state) {
        if (state.getBlock() instanceof CopperButton copperButton) {
            WeatherState weatherState = copperButton.getAge().next();
            if (copperButton.getAge() == weatherState) return Optional.empty();
            return getOfType(copperButton.getButtonType(), weatherState, state);
        }
        return Optional.empty();
    }

    private Optional<BlockState> getPrevious(BlockState state) {
        if (state.getBlock() instanceof CopperButton copperButton) {
            WeatherState weatherState = copperButton.getAge().previous();
            if (copperButton.getAge() == weatherState) return Optional.empty();
            return getOfType(copperButton.getButtonType(), weatherState, state);
        }
        return Optional.empty();
    }

    private void itemUsed(BlockPos blockPos, Player player, ItemStack itemStack) {
        if (player instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, blockPos, itemStack);
        }
    }

    private void hurtAxe(Player player, ItemStack itemStack) {
        itemStack.hurtAndBreak(1, player, player.getUsedItemHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
    }

    default InteractionResult wax(BlockState state, Level level, BlockPos blockPos, Player player, ItemStack itemStack) {
        return getOfType(CopperButtonType.WAXED, state).map((waxedBlockState) -> {
            itemUsed(blockPos, player, itemStack);
            if (!player.getAbilities().instabuild) itemStack.shrink(1);
            level.setBlock(blockPos, waxedBlockState, Block.UPDATE_ALL_IMMEDIATE);
            level.levelEvent(player, 3003, blockPos, 0);
            level.playSound(player, blockPos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0f, 1.0f);
            return sided(level.isClientSide());
        }).orElse(sided(level.isClientSide()));
    }

    default InteractionResult scrape(BlockState state, Level level, BlockPos blockPos, Player player, ItemStack itemStack) {
        return getPrevious(state).map((previousBlockState) -> {
            itemUsed(blockPos, player, itemStack);
            if (!player.getAbilities().instabuild) hurtAxe(player, itemStack);
            level.setBlock(blockPos, previousBlockState, Block.UPDATE_ALL_IMMEDIATE);
            level.levelEvent(player, 3005, blockPos, 0);
            level.playSound(player, blockPos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0f, 1.0f);
            return sided(level.isClientSide());
        }).orElse(sided(level.isClientSide()));
    }

    default InteractionResult scrapeWax(BlockState blockState, Level level, BlockPos blockPos, Player player, ItemStack itemStack) {
        return getOfType(CopperButtonType.NORMAL, blockState).map((waxedBlockState) -> {
            itemUsed(blockPos, player, itemStack);
            if (!player.getAbilities().instabuild) hurtAxe(player, itemStack);
            level.setBlock(blockPos, waxedBlockState, Block.UPDATE_ALL_IMMEDIATE);
            level.levelEvent(player, 3004, blockPos, 0);
            level.playSound(player, blockPos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0f, 1.0f);
            // TODO, advancement
//            if (player instanceof ServerPlayer serverPlayer)
//                InfinityButtonsTriggers.WAX_OFF_TRIGGER.trigger(serverPlayer);
            return sided(level.isClientSide());
        }).orElse(sided(level.isClientSide()));
    }

    default InteractionResult sticky(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, ItemStack itemStack) {
        return getOfType(CopperButtonType.STICKY, blockState).map((waxedBlockState) -> {
            itemUsed(blockPos, player, itemStack);
            if (!player.getAbilities().instabuild) {
                player.setItemInHand(hand, ItemUtils.createFilledResult(itemStack, player, new ItemStack(Items.GLASS_BOTTLE)));
            }
            level.setBlock(blockPos, waxedBlockState, Block.UPDATE_ALL_IMMEDIATE);
            level.levelEvent(player, 3003, blockPos, 0);
            level.playSound(player, blockPos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0f, 1.0f);
            return sided(level.isClientSide());
        }).orElse(sided(level.isClientSide()));
    }

    default InteractionResult unSticky(BlockState blockState, Level level, BlockPos blockPos, Player player, ItemStack itemStack) {
        return getOfType(CopperButtonType.WAXED, blockState).map((waxedBlockState) -> {
            itemUsed(blockPos, player, itemStack);
            if (!player.getAbilities().instabuild) hurtAxe(player, itemStack);
            level.setBlock(blockPos, waxedBlockState.setValue(ButtonBlock.POWERED, false), Block.UPDATE_ALL_IMMEDIATE);
            level.levelEvent(player, 3004, blockPos, 0);
            level.playSound(player, blockPos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0f, 1.0f);
            return sided(level.isClientSide());
        }).orElse(InteractionResult.PASS);
    }
}
