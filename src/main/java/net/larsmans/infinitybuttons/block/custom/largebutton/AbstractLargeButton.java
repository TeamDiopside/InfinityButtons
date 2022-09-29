package net.larsmans.infinitybuttons.block.custom.largebutton;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
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
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AbstractLargeButton extends WallMountedBlock {
    public static final BooleanProperty PRESSED = BooleanProperty.of("pressed");

    private static final VoxelShape FLOOR_SHAPE = Block.createCuboidShape(4, 0, 4, 12, 2, 12);
    private static final VoxelShape FLOOR_PRESSED_SHAPE = Block.createCuboidShape(4, 0, 4, 12, 1, 12);
    private static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(4, 4, 14, 12, 12, 16);
    private static final VoxelShape NORTH_PRESSED_SHAPE = Block.createCuboidShape(4, 4, 15, 12, 12, 16);
    private static final VoxelShape EAST_SHAPE = Block.createCuboidShape(0, 4, 4, 2, 12, 12);
    private static final VoxelShape EAST_PRESSED_SHAPE = Block.createCuboidShape(0, 4, 4, 1, 12, 12);
    private static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(4, 4, 0, 12, 12, 2);
    private static final VoxelShape SOUTH_PRESSED_SHAPE = Block.createCuboidShape(4, 4, 0, 12, 12, 1);
    private static final VoxelShape WEST_SHAPE = Block.createCuboidShape(14, 4, 4, 16, 12, 12);
    private static final VoxelShape WEST_PRESSED_SHAPE = Block.createCuboidShape(15, 4, 4, 16, 12, 12);
    private static final VoxelShape CEILING_SHAPE = Block.createCuboidShape(4, 14, 4, 12, 16, 12);
    private static final VoxelShape CEILING_PRESSED_SHAPE = Block.createCuboidShape(4, 15, 4, 12, 16, 12);

    private final boolean wooden;

    public AbstractLargeButton(boolean wooden, Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(PRESSED, false)).with(FACE, WallMountLocation.FLOOR));
        this.wooden = wooden;
    }

    public abstract int getPressTicks();

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        boolean bl = state.get(PRESSED);
        switch ((WallMountLocation)state.get(FACE)) {
            case FLOOR: {
                return bl ? FLOOR_PRESSED_SHAPE : FLOOR_SHAPE;
            }
            case WALL: {
                switch (direction) {
                    case EAST: {
                        return bl ? EAST_PRESSED_SHAPE : EAST_SHAPE;
                    }
                    case WEST: {
                        return bl ? WEST_PRESSED_SHAPE : WEST_SHAPE;
                    }
                    case SOUTH: {
                        return bl ? SOUTH_PRESSED_SHAPE : SOUTH_SHAPE;
                    }
                }
                return bl ? NORTH_PRESSED_SHAPE : NORTH_SHAPE;
            }
        }
        return bl ? CEILING_PRESSED_SHAPE : CEILING_SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PRESSED, FACING, FACE);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
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
        world.createAndScheduleBlockTick(pos, this, this.getPressTicks());
    }

    protected void playClickSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, boolean pressed) {
        world.playSound(pressed ? player : null, pos, this.getClickSound(pressed), SoundCategory.BLOCKS, 0.3f, pressed ? 0.6f : 0.5f);
    }

    protected abstract SoundEvent getClickSound(boolean var1);

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
        if (state.get(PRESSED) && AbstractLargeButton.getDirection(state) == direction) {
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
        if (this.wooden) {
            this.tryPowerWithProjectiles(state, world, pos);
        } else {
            world.setBlockState(pos, (BlockState)state.with(PRESSED, false), Block.NOTIFY_ALL);
            this.updateNeighbors(state, world, pos);
            this.playClickSound(null, world, pos, false);
            world.emitGameEvent(null, GameEvent.BLOCK_DEACTIVATE, pos);
        }
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (world.isClient || !this.wooden || state.get(PRESSED)) {
            return;
        }
        this.tryPowerWithProjectiles(state, world, pos);
    }

    private void tryPowerWithProjectiles(BlockState state, World world, BlockPos pos) {
        boolean bl2;
        List<PersistentProjectileEntity> list = world.getNonSpectatingEntities(PersistentProjectileEntity.class, state.getOutlineShape(world, pos).getBoundingBox().offset(pos));
        boolean bl = !list.isEmpty();
        if (bl != (bl2 = state.get(PRESSED))) {
            world.setBlockState(pos, (BlockState)state.with(PRESSED, bl), Block.NOTIFY_ALL);
            this.updateNeighbors(state, world, pos);
            this.playClickSound(null, world, pos, bl);
            world.emitGameEvent((Entity)list.stream().findFirst().orElse(null), bl ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, pos);
        }
        if (bl) {
            world.createAndScheduleBlockTick(new BlockPos(pos), this, this.getPressTicks());
        }
    }


    public void updateNeighbors(BlockState state, World world, BlockPos pos) {
        world.updateNeighborsAlways(pos, this);
        world.updateNeighborsAlways(pos.offset(AbstractLargeButton.getDirection(state).getOpposite()), this);
    }

}



