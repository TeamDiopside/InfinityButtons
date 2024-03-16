package nl.teamdiopside.infinitybuttons.block;

import nl.teamdiopside.infinitybuttons.InfinityButtonsUtil;
import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
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

import java.util.List;

import static nl.teamdiopside.infinitybuttons.InfinityButtonsUtil.checkChains;
import static net.minecraft.block.LanternBlock.WATERLOGGED;

public class LanternButton extends Block implements Waterloggable {

    public static final BooleanProperty PRESSED = BooleanProperty.of("pressed");
    private static final VoxelShape HANGING_SHAPE = VoxelShapes.union(Block.createCuboidShape(5.0, 1.0, 5.0, 11.0, 8.0, 11.0), Block.createCuboidShape(6.0, 8.0, 6.0, 10.0, 10.0, 10.0));
    public static final VoxelShape SHAPE_PRESSED = HANGING_SHAPE.offset(0, (double) -1 / 16, 0);
    private final boolean isLever;
    public final Block jadeBlock;

    public LanternButton(Settings settings, boolean isLever, Block jadeBlock) {
        super(settings);
        this.isLever = isLever;
        this.jadeBlock = jadeBlock;
        this.setDefaultState(this.stateManager.getDefaultState().with(WATERLOGGED, false).with(PRESSED, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(PRESSED) ? SHAPE_PRESSED : HANGING_SHAPE;
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        for (Direction direction : ctx.getPlacementDirections()) {
            if (direction != Direction.UP) continue;
            return this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        }
        return null;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, PRESSED);
    }

    protected int getPressTicks() {
        return 30;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return direction == Direction.UP && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (isLever) {
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
            world.scheduleBlockTick(pos, this, this.getPressTicks());
            this.playClickSound(player, world, pos, true);
            world.emitGameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
        }
        return ActionResult.success(world.isClient);
    }

    public void powerOn(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, state.with(PRESSED, true), Block.NOTIFY_ALL);
        this.updateNeighbors(world, pos);
    }

    public void powerOff(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, state.with(PRESSED, false), Block.NOTIFY_ALL);
        this.updateNeighbors(world, pos);
    }

    protected void playClickSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, boolean pressed) {
        world.playSound(pressed ? player : null, pos, this.getClickSound(pressed), SoundCategory.BLOCKS);
    }

    protected SoundEvent getClickSound(boolean pressed) {
        return pressed ? SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON : SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (moved || state.isOf(newState.getBlock())) {
            return;
        }
        if (state.get(PRESSED)) {
            this.updateNeighbors(world, pos);
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(PRESSED) ? 15 : 0;
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (state.get(PRESSED) && direction == Direction.DOWN) {
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
        world.setBlockState(pos, state.with(PRESSED, false), Block.NOTIFY_ALL);
        this.updateNeighbors(world, pos);
        this.playClickSound(null, world, pos, false);
        world.emitGameEvent(null, GameEvent.BLOCK_DEACTIVATE, pos);
    }

    public void updateNeighbors(World world, BlockPos pos) {
        int distance = checkChains(world, pos);
        world.updateNeighborsAlways(pos, this);
        world.updateNeighborsAlways(pos.up(distance), this);
        world.updateNeighborsAlways(pos.up(distance + 1), this);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        InfinityButtonsUtil.tooltip(tooltip, "lantern_button");
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return Block.sideCoversSmallSquare(world, pos.offset(Direction.UP), Direction.DOWN);
    }
}
