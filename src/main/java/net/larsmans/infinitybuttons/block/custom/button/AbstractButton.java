/*
 * Decompiled with CFR 0.1.1 (FabricMC 57d88659).
 */
package net.larsmans.infinitybuttons.block.custom.button;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallMountedBlock;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
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

import java.util.List;

public abstract class AbstractButton extends WallMountedBlock {

    public static final BooleanProperty PRESSED = BooleanProperty.of("pressed");

    protected final boolean projectile;

    protected AbstractButton(boolean projectile, FabricBlockSettings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(PRESSED, false).with(FACE, WallMountLocation.FLOOR));
        this.projectile = projectile;
    }

    public abstract int getPressTicks();

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(PRESSED)) {
            return ActionResult.CONSUME;
        }
        this.powerOn(state, world, pos);
        this.playClickSound(player, world, pos, true);
        world.emitGameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
        return ActionResult.success(world.isClient);
    }

    public void powerOn(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, state.with(PRESSED, true), Block.NOTIFY_ALL);
        this.updateNeighbors(state, world, pos);
        world.scheduleBlockTick(pos, this, this.getPressTicks());
    }

    protected void playClickSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, boolean pressed) {
        world.playSound(pressed ? player : null, pos, this.getClickSound(pressed), SoundCategory.BLOCKS);
    }

    protected abstract SoundEvent getClickSound(boolean pressed);

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (moved || state.isOf(newState.getBlock())) {
            return;
        }
        if (state.get(PRESSED)) {
            this.updateNeighbors(state, world, pos);
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(PRESSED) ? 15 : 0;
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (state.get(PRESSED) && getDirection(state) == direction) {
            return 15;
        }
        return 0;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.get(PRESSED)) {
            return;
        }
        if (this.projectile) {
            this.tryPowerWithProjectiles(state, world, pos);
        } else {
            world.setBlockState(pos, state.with(PRESSED, false), Block.NOTIFY_ALL);
            this.updateNeighbors(state, world, pos);
            this.playClickSound(null, world, pos, false);
            world.emitGameEvent(null, GameEvent.BLOCK_DEACTIVATE, pos);
        }
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (world.isClient || !this.projectile || state.get(PRESSED)) {
            return;
        }
        this.tryPowerWithProjectiles(state, world, pos);
    }

    private void tryPowerWithProjectiles(BlockState state, World world, BlockPos pos) {
        boolean bl2;
        List<PersistentProjectileEntity> list = world.getNonSpectatingEntities(PersistentProjectileEntity.class, state.getOutlineShape(world, pos).getBoundingBox().offset(pos));
        boolean bl = !list.isEmpty();
        if (bl != (bl2 = state.get(PRESSED))) {
            world.setBlockState(pos, state.with(PRESSED, bl), Block.NOTIFY_ALL);
            this.updateNeighbors(state, world, pos);
            this.playClickSound(null, world, pos, bl);
            world.emitGameEvent(list.stream().findFirst().orElse(null), bl ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, pos);
        }
        if (bl) {
            world.scheduleBlockTick(new BlockPos(pos), this, this.getPressTicks());
        }
    }

    public void updateNeighbors(BlockState state, World world, BlockPos pos) {
        world.updateNeighborsAlways(pos, this);
        world.updateNeighborsAlways(pos.offset(getDirection(state).getOpposite()), this);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, PRESSED, FACE);
    }
}

