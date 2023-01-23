package net.larsmans.infinitybuttons.block.custom.button;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.larsmans.infinitybuttons.block.InfinityButtonsUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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

public class StickyCopperButton extends AbstractButton {

    public StickyCopperButton(FabricBlockSettings settings, boolean large) {
        super(false, large, settings);
    }

    @Override
    public int getPressTicks() {
        return 0;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(PRESSED)) {
            this.powerOff(state, world, pos);
            this.playClickSound(player, world, pos, false);
            world.emitGameEvent(player, GameEvent.BLOCK_DEACTIVATE, pos);
        } else {
            this.powerOn(state, world, pos);
            this.playClickSound(player, world, pos, true);
            world.emitGameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
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