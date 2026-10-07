package nl.teamdiopside.infinitybuttons.block.faced6.normal;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CopperButton extends NormalButton implements WeatheringButton {

    public static final int PRESS_TICKS = 50;
    public static final MapCodec<CopperButton> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(propertiesCodec(),
                    Codec.BOOL.fieldOf("large").forGetter(copperButton -> copperButton.large),
                    WeatherState.CODEC.fieldOf("weather_state").forGetter(copperButton -> copperButton.weatherState),
                    CopperButtonType.CODEC.fieldOf("button_type").forGetter(copperButton -> copperButton.buttonType)
                    ).apply(instance, CopperButton::new)
    );

    protected final WeatheringCopper.WeatherState weatherState;
    protected final CopperButtonType buttonType;

    public CopperButton(BlockBehaviour.Properties properties, boolean large, WeatheringCopper.WeatherState weatherState, CopperButtonType buttonType) {
        super(BlockSetType.COPPER, PRESS_TICKS, properties, large, buttonType == CopperButtonType.STICKY);
        this.weatherState = weatherState;
        this.buttonType = buttonType;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource random) {
        this.changeOverTime(state, serverLevel, pos, random);
    }

    @Override // Slightly modified version, original in ChangeOverTimeBlock.class
    public @NotNull Optional<BlockState> getNextState(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource random) {
        int age = this.getAge().ordinal();
        int j = 0;
        int k = 0;

        for (BlockPos closeBlockPos : BlockPos.withinManhattan(pos, 4, 4, 4)) {
            int distManhattan = closeBlockPos.distManhattan(pos);
            if (distManhattan > 4) {
                // I believe this shouldn't be a break statement Mojang...
                continue;
            }

            if (!closeBlockPos.equals(pos)) {
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
        return random.nextFloat() < g ? this.getNext(state) : Optional.empty();
    }

    @Override
    public @NotNull ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
        switch (getButtonType()) {
            case NORMAL -> {
                if (state.getValue(ButtonBlock.POWERED)) {
                    return ItemInteractionResult.CONSUME;
                } else if (stack.getItem() == Items.HONEYCOMB) {
                    return wax(state, level, pos, player, stack);
                } else if (stack.getItem() instanceof AxeItem && getAge() != WeatherState.UNAFFECTED) {
                    return scrape(state, level, pos, player, stack);
                }
            }
            case WAXED -> {
                if (stack.getItem() instanceof AxeItem && !state.getValue(ButtonBlock.POWERED)) {
                    return scrapeWax(state, level, pos, player, stack);
                } else if (stack.getItem() == Items.HONEY_BOTTLE) {
                    return sticky(state, level, pos, player, hand, stack);
                }
            }
            case STICKY -> {
                if (stack.getItem() instanceof AxeItem) {
                    return unSticky(state, level, pos, player, stack);
                }
            }
        }
        return super.useItemOn(stack, state, level, pos, player, hand, blockHitResult);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return buttonType == CopperButtonType.NORMAL && getAge() != WeatherState.OXIDIZED && !state.getValue(ButtonBlock.POWERED);
    }

    @Override
    public @NotNull WeatherState getAge() {
        return this.weatherState;
    }

    public CopperButtonType getButtonType() {
        return buttonType;
    }

    @Override
    protected void playSound(@Nullable Player player, LevelAccessor levelAccessor, BlockPos pos, boolean hitByArrow) {
        levelAccessor.playSound(hitByArrow ? player : null, pos, this.getSound(hitByArrow), SoundSource.BLOCKS, 1F, hitByArrow ? 0.6F : 0.5F);
    }

    @Override
    protected @NotNull SoundEvent getSound(boolean isOn) {
        return SoundEvents.COPPER_BREAK;
    }

    @Override
    protected int getPressTicks(Level level, BlockPos pos) {
        return PRESS_TICKS;
    }

    @Override
    protected @NotNull SoundType getSoundType(BlockState state) {
        return SoundType.COPPER;
    }
}
