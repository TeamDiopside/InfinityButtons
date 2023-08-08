package net.larsmans.infinitybuttons.block.custom.torch;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TorchBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class RedstoneTorchButton extends TorchBlock {
    public static final BooleanProperty LIT = Properties.LIT;
    public final Block jadeBlock;

    public RedstoneTorchButton(FabricBlockSettings settings, Block jadeBlock) {
        super(settings, DustParticleEffect.DEFAULT);
        this.setDefaultState(this.stateManager.getDefaultState().with(LIT, false));
        this.jadeBlock = jadeBlock;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(LIT)) {
            return ActionResult.CONSUME;
        }
        this.powerOn(state, world, pos);
        this.playClickSound(player, world, pos, true);
        world.emitGameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
        return ActionResult.success(world.isClient);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(LIT)) {
            world.setBlockState(pos, state.with(LIT, false), Block.NOTIFY_ALL);
            this.updateNeighbors(state, world, pos);
            this.playClickSound(null, world, pos, false);
            world.emitGameEvent(null, GameEvent.BLOCK_DEACTIVATE, pos);
        }
    }

    public void powerOn(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, state.with(LIT, true), Block.NOTIFY_ALL);
        this.updateNeighbors(state, world, pos);
        world.scheduleBlockTick(pos, this, 60);
    }

    protected void playClickSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, boolean powered) {
        world.playSound(powered ? player : null, pos, powered ? SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF : SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 0.3f, powered ? 0.6f : 0.5f);
    }

    public void updateNeighbors(BlockState state, World world, BlockPos pos) {
        world.updateNeighborsAlways(pos, this);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        for (Direction direction : Direction.values()) {
            world.updateNeighborsAlways(pos.offset(direction), this);
        }
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (moved) {
            return;
        }
        for (Direction direction : Direction.values()) {
            world.updateNeighborsAlways(pos.offset(direction), this);
        }
    }
    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (direction == Direction.DOWN) {
            return state.getWeakRedstonePower(world, pos, direction);
        }
        return 0;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (state.get(LIT) && Direction.UP != direction) {
            return 15;
        }
        return 0;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(LIT)) {
            double d = (double) pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.2;
            double e = (double) pos.getY() + 0.7 + (random.nextDouble() - 0.5) * 0.2;
            double f = (double) pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.2;
            world.addParticle(this.particle, d, e, f, 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }
}
