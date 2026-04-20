package nl.teamdiopside.infinitybuttons.block.normal;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.redstone.ExperimentalRedstoneUtils;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import nl.teamdiopside.diopside.block.BlockTooltip;
import nl.teamdiopside.diopside.datacomponent.HoldShiftTooltipComponent;
import nl.teamdiopside.diopside.registry.DiopsideDataComponents;
import nl.teamdiopside.infinitybuttons.registry.RegistryUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Consumer;

import static nl.teamdiopside.infinitybuttons.InfinityButtonsUtil.sided;

public class CopperButton extends NormalButton implements WeatheringButton, BlockTooltip<HoldShiftTooltipComponent> {

    protected final WeatheringCopper.WeatherState weatherState;
    protected final CopperButtonType buttonType;

    public CopperButton(BlockBehaviour.Properties properties, boolean large, WeatheringCopper.WeatherState weatherState, CopperButtonType buttonType) {
        super(BlockSetType.COPPER, 50, properties, large);
        this.weatherState = weatherState;
        this.buttonType = buttonType;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        this.changeOverTime(pState, pLevel, pPos, pRandom);
    }

    @Override
    // Slightly modified version, original in ChangeOverTimeBlock.class
    public @NotNull Optional<BlockState> getNextState(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        int age = this.getAge().ordinal();
        int j = 0;
        int k = 0;

        for (BlockPos closeBlockPos : BlockPos.withinManhattan(blockPos, 4, 4, 4)) {
            int distManhattan = closeBlockPos.distManhattan(blockPos);
            if (distManhattan > 4) {
                // I believe this shouldn't be a break statement Mojang...
                continue;
            }

            if (!closeBlockPos.equals(blockPos)) {
                Block closeBlock = serverLevel.getBlockState(closeBlockPos).getBlock();
                // Waxed buttons should not influence oxidization!
                if (closeBlock instanceof CopperButton copperButton && copperButton.getButtonType() != CopperButtonType.NORMAL) continue;
                if (closeBlock instanceof ChangeOverTimeBlock<?> changeOverTimeBlock) {
                    Enum<?> otherBlockEnum = changeOverTimeBlock.getAge();
                    if (this.getAge().getClass() == otherBlockEnum.getClass()) {
                        int otherBlockAge = otherBlockEnum.ordinal();
                        if (otherBlockAge < age) {
                            return Optional.empty();
                        }

                        if (otherBlockAge > age) {
                            ++k;
                        } else {
                            ++j;
                        }
                    }
                }
            }
        }

        float f = (float)(k + 1) / (float)(k + j + 1);
        float g = f * f * this.getChanceModifier();
        return randomSource.nextFloat() < g ? this.getNext(blockState) : Optional.empty();
    }

    @Override
    public @NotNull InteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (blockState.getValue(ButtonBlock.POWERED) && getButtonType() != CopperButtonType.STICKY) {
            return InteractionResult.CONSUME;
        }
        switch (getButtonType()) {
            case NORMAL -> {
                if (itemStack.getItem() == Items.HONEYCOMB) {
                    return wax(blockState, level, blockPos, player, itemStack);
                } else if (itemStack.getItem() instanceof AxeItem && getAge() != WeatherState.UNAFFECTED) {
                    return scrape(blockState, level, blockPos, player, itemStack);
                }
            }
            case WAXED -> {
                if (itemStack.getItem() instanceof AxeItem) {
                    return scrapeWax(blockState, level, blockPos, player, itemStack);
                } else if (itemStack.getItem() == Items.HONEY_BOTTLE) {
                    return sticky(blockState, level, blockPos, player, interactionHand, itemStack);
                }
            }
            case STICKY -> {
                if (itemStack.getItem() instanceof AxeItem) {
                    return unSticky(blockState, level, blockPos, player, itemStack);
                } else if (blockState.getValue(ButtonBlock.POWERED)) {
                    this.unpress(blockState, level, blockPos, player);
                    return sided(level.isClientSide());
                }
            }
        }
        return super.useItemOn(itemStack, blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Override
    public void press(BlockState blockState, Level level, BlockPos blockPos, @Nullable Player player) {
        if (getButtonType() != CopperButtonType.STICKY) {
            super.press(blockState, level, blockPos, player);
            return;
        }
        level.setBlock(blockPos, blockState.setValue(ButtonBlock.POWERED, true), 3);
        this.updateNeighbours(blockState, level, blockPos);
        this.playSound(player, level, blockPos, true);
        level.gameEvent(player, GameEvent.BLOCK_ACTIVATE, blockPos);

    }

    public void unpress(BlockState blockState, Level level, BlockPos blockPos, @Nullable Player player) {
        level.setBlock(blockPos, blockState.setValue(ButtonBlock.POWERED, false), 3);
        this.updateNeighbours(blockState, level, blockPos);
        this.playSound(player, level, blockPos, false);
        level.gameEvent(player, GameEvent.BLOCK_DEACTIVATE, blockPos);
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return buttonType == CopperButtonType.NORMAL && getAge() != WeatherState.OXIDIZED && !blockState.getValue(ButtonBlock.POWERED);
    }

    @Override
    public @NotNull WeatherState getAge() {
        return this.weatherState;
    }

    public CopperButtonType getButtonType() {
        return buttonType;
    }

    @Override
    protected void playSound(@Nullable Player playerIn, LevelAccessor worldIn, BlockPos pos, boolean hitByArrow) {
        worldIn.playSound(hitByArrow ? playerIn : null, pos, this.getSound(hitByArrow), SoundSource.BLOCKS, 1F, hitByArrow ? 0.6F : 0.5F);
    }

    @Override
    protected @NotNull SoundEvent getSound(boolean isOn) {
        return SoundEvents.COPPER_BREAK;
    }

    public void updateNeighbours(BlockState blockState, Level level, BlockPos blockPos) {
        Direction direction = FaceAttachedHorizontalDirectionalBlock.getConnectedDirection(blockState).getOpposite();
        Orientation orientation = ExperimentalRedstoneUtils.initialOrientation(level, direction, direction.getAxis().isHorizontal() ? Direction.UP : blockState.getValue(HorizontalDirectionalBlock.FACING));
        level.updateNeighborsAt(blockPos, this, orientation);
        level.updateNeighborsAt(blockPos.relative(direction), this, orientation);
    }

    @Override
    public void addToTooltip(Item.TooltipContext tooltipContext, Consumer<Component> consumer, TooltipFlag tooltipFlag, DataComponentGetter dataComponentGetter) {
        consumer.accept(Component.translatable("infinitybuttons.tooltip.sticky_copper_button").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public DataComponentType<HoldShiftTooltipComponent> getDataComponentType() {
        return DiopsideDataComponents.HOLD_SHIFT_TOOLTIP.get();
    }

    @Override
    public HoldShiftTooltipComponent getTooltipProvider() {
        return new HoldShiftTooltipComponent(this);
    }

    @Override
    public boolean tooltipVisible() {
        // TODO configurable!
        return getButtonType() == CopperButtonType.STICKY;
    }
}
