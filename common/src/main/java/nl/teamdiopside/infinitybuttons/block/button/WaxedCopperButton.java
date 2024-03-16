package nl.teamdiopside.infinitybuttons.block.button;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.WallMountedBlock;
import nl.teamdiopside.infinitybuttons.advancement.InfinityButtonsTriggers;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class WaxedCopperButton extends AbstractSmallButton {

    public WaxedCopperButton(Settings settings, boolean large) {
        super(false, large, settings);
    }

    @Override
    protected void playClickSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, boolean pressed) {
        world.playSound(pressed ? player : null, pos, this.getClickSound(pressed), SoundCategory.BLOCKS, 1F, pressed ? 0.6F : 0.5F);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (itemStack.getItem() instanceof AxeItem && !(this instanceof CopperButton)) {
            return state.get(PRESSED) ? ActionResult.CONSUME : scrapeWax(state, world, pos, player, itemStack);
        } else {
            return (!(itemStack.getItem() instanceof HoneyBottleItem) || this instanceof CopperButton) ? super.onUse(state, world, pos, player, hand, hit) : sticky(state, world, pos, player, hand, itemStack);
        }
    }

    public ActionResult scrapeWax(BlockState blockState, World world, BlockPos blockpos, PlayerEntity player, ItemStack itemStack) {
        return WeatheringButton.getUnwaxed(blockState).map((waxedBlockState) -> {
            if (player instanceof ServerPlayerEntity) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity) player, blockpos, itemStack);
            }
            if (!player.getAbilities().creativeMode) {
                itemStack.damage(1, player, (entity) -> entity.sendToolBreakStatus(player.getActiveHand()));
            }
            world.setBlockState(blockpos, waxedBlockState, Block.NOTIFY_ALL_AND_REDRAW);
            world.syncWorldEvent(player, 3004, blockpos, 0);
            world.playSound(player, blockpos, SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.BLOCKS, 1.0f, 1.0f);
            if (player instanceof ServerPlayerEntity serverPlayerEntity)
                InfinityButtonsTriggers.WAX_OFF_TRIGGER.trigger(serverPlayerEntity);
            return ActionResult.success(world.isClient);
        }).orElse(ActionResult.success(world.isClient));
    }

    public ActionResult sticky(BlockState blockState, World world, BlockPos blockpos, PlayerEntity player, Hand hand, ItemStack itemStack) {
        return WeatheringButton.getSticky(blockState).map((waxedBlockState) -> {
            if (player instanceof ServerPlayerEntity) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity) player, blockpos, itemStack);
            }
            if (!player.getAbilities().creativeMode) {
                player.setStackInHand(hand, ItemUsage.exchangeStack(itemStack, player, new ItemStack(Items.GLASS_BOTTLE)));
            }
            world.setBlockState(blockpos, waxedBlockState, Block.NOTIFY_ALL_AND_REDRAW);
            world.syncWorldEvent(player, 3003, blockpos, 0);
            world.playSound(player, blockpos, SoundEvents.ITEM_HONEYCOMB_WAX_ON, SoundCategory.BLOCKS, 1.0f, 1.0f);
            return ActionResult.success(world.isClient);
        }).orElse(ActionResult.success(world.isClient));
    }

    @Override
    public int getPressTicks() {
        return 50;
    }

    @Override
    protected SoundEvent getClickSound(boolean pressed) {
        return SoundEvents.BLOCK_COPPER_BREAK;
    }

    @Override
    protected MapCodec<? extends WallMountedBlock> getCodec() {
        return null;
    }
}
