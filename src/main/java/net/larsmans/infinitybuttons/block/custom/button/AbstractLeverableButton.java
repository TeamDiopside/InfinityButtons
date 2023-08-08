package net.larsmans.infinitybuttons.block.custom.button;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public abstract class AbstractLeverableButton extends AbstractButton {

    public final boolean lever;

    public AbstractLeverableButton(boolean lever, FabricBlockSettings settings) {
        super(false, settings);
        this.lever = lever;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (lever) {
            if (state.get(PRESSED)) {
                this.powerOff(state, world, pos);
                this.playClickSound(player, world, pos, false);
                world.emitGameEvent(player, GameEvent.BLOCK_DEACTIVATE, pos);
            } else {
                this.powerOn(state, world, pos);
                this.playClickSound(player, world, pos, true);
                world.emitGameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
            }
        } else {
            if (state.get(PRESSED)) {
                return ActionResult.CONSUME;
            }
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
        if (!lever) {
            world.scheduleBlockTick(pos, this, this.getPressTicks());
        }
    }

    public void powerOff(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, state.with(PRESSED, false), Block.NOTIFY_ALL);
        this.updateNeighbors(state, world, pos);
    }
}
