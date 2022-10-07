/*
 * Decompiled with CFR 0.1.1 (FabricMC 57d88659).
 */
package net.larsmans.infinitybuttons.block.custom;

import net.larsmans.infinitybuttons.sounds.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class Doorbell extends HorizontalFacingBlock {

    public static final BooleanProperty PRESSED = BooleanProperty.of("pressed");

    protected static final VoxelShape NORTH_PRESSED_SHAPE =
            Block.createCuboidShape(6, 4, 14, 10, 12, 16);

    protected static final VoxelShape EAST_PRESSED_SHAPE =
            Block.createCuboidShape(0, 4, 6, 2, 12, 10);

    protected static final VoxelShape SOUTH_PRESSED_SHAPE =
            Block.createCuboidShape(6, 4, 0, 10, 12, 2);

    protected static final VoxelShape WEST_PRESSED_SHAPE =
            Block.createCuboidShape(14, 4, 6, 16, 12, 10);

    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.union(NORTH_PRESSED_SHAPE,
            Block.createCuboidShape(7, 6, 13, 9, 10, 14));

    protected static final VoxelShape EAST_SHAPE = VoxelShapes.union(EAST_PRESSED_SHAPE,
            Block.createCuboidShape(2, 6, 7, 3, 10, 9));

    protected static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(SOUTH_PRESSED_SHAPE,
            Block.createCuboidShape(7, 6, 2, 9, 10, 3));

    protected static final VoxelShape WEST_SHAPE = VoxelShapes.union(WEST_PRESSED_SHAPE,
            Block.createCuboidShape(13, 6, 7, 14, 10, 9));

    public Doorbell (Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(PRESSED, false)));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        boolean bl = state.get(PRESSED);
        switch (state.get(FACING)) {
            case NORTH -> {
                return bl ? NORTH_PRESSED_SHAPE : NORTH_SHAPE;
            }
            case EAST -> {
                return bl ? EAST_PRESSED_SHAPE : EAST_SHAPE;
            }
            case SOUTH -> {
                return bl ? SOUTH_PRESSED_SHAPE : SOUTH_SHAPE;
            }
        }
        return bl ? WEST_PRESSED_SHAPE : WEST_SHAPE;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(PRESSED)) {
            return ActionResult.CONSUME;
        }
        this.powerOn(state, world, pos);
        this.playClickSound(player, world, pos, true);
        world.emitGameEvent((Entity)player, GameEvent.BLOCK_ACTIVATE, pos);
        return ActionResult.success(world.isClient);
    }

    public void powerOn(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, (BlockState)state.with(PRESSED, true), Block.NOTIFY_ALL);
        this.updateNeighbors(state, world, pos);
        world.createAndScheduleBlockTick(pos, this, 15);
    }

    protected void playClickSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, boolean powered) {
        world.playSound(powered ? player : null, pos, ModSounds.DOORBELL, SoundCategory.BLOCKS, 0.3f, 1f);
    }

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
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.get(PRESSED)) {
            return;
        }
        world.setBlockState(pos, (BlockState)state.with(PRESSED, false), Block.NOTIFY_ALL);
        this.updateNeighbors(state, world, pos);
        world.emitGameEvent(null, GameEvent.BLOCK_DEACTIVATE, pos);
    }

    public void updateNeighbors(BlockState state, World world, BlockPos pos) {
        world.updateNeighborsAlways(pos, this);
        world.updateNeighborsAlways(pos.offset(state.get(FACING).getOpposite()), this);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, PRESSED);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = state.get(FACING);
        BlockPos blockPos = pos.offset(direction.getOpposite());
        BlockState blockState = world.getBlockState(blockPos);
        return blockState.isSideSolidFullSquare(world, blockPos, direction);
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction[] directions;
        BlockState blockState = this.getDefaultState();
        World worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        for (Direction direction : directions = ctx.getPlacementDirections()) {
            Direction direction2;
            if (!direction.getAxis().isHorizontal() || !(blockState = (BlockState)blockState.with(FACING, direction2 = direction.getOpposite())).canPlaceAt(worldView, blockPos)) continue;
            return blockState;
        }
        return null;
    }
}

