package nl.teamdiopside.infinitybuttons.block.simple;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import nl.teamdiopside.infinitybuttons.block.InfinityButton;
import nl.teamdiopside.infinitybuttons.compat.jade.JadeCamouflaged;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;
import org.jetbrains.annotations.Nullable;

public class LanternButton extends InfinityButton implements SimpleWaterloggedBlock, JadeCamouflaged {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED; // TODO: Fix :)
    protected final ResourceLocation camouflage;

    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(5.0, 1.0, 5.0, 11.0, 8.0, 11.0),
            Block.box(6.0, 8.0, 6.0, 10.0, 10.0, 10.0));
    public static final VoxelShape SHAPE_PRESSED = SHAPE.move(0, (double) -1 / 16, 0);

    public LanternButton(Properties properties, boolean isLever, ResourceLocation camouflage) {
        super(properties, SHAPE_PRESSED, SHAPE, isLever);
        this.camouflage = camouflage;
    }

    public void updateThings(Level level, BlockPos pos) {
        int distance = checkChains(level, pos);
        level.updateNeighborsAt(pos, this);
        level.updateNeighborsAt(pos.above(distance), this);
        level.updateNeighborsAt(pos.above(distance + 1), this);
    }

    public static int checkChains(Level level, BlockPos pos) {
        int i = 0;
        while (level.getBlockState(pos.above(i + 1)).getBlock() instanceof ChainBlock) {
            i++;
        }
        return i;
    }

    @Override
    protected SoundEvent getSound(boolean press) {
        return press ? SoundEvents.STONE_BUTTON_CLICK_ON : SoundEvents.STONE_BUTTON_CLICK_OFF; // TODO: Custom sounds could be cool
    }

    @Override
    protected int getPressTicks() {
        return 30;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext ctx) {
        FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
        for (Direction direction : ctx.getNearestLookingDirections()) {
            if (direction != Direction.UP) continue;
            return this.defaultBlockState().setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
        }
        return null;
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor levelAccessor, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP && !this.canSurvive(state, levelAccessor, pos)) {
            return Blocks.AIR.defaultBlockState();
        }
        if (state.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }
        return super.updateShape(state, direction, neighborState, levelAccessor, pos, neighborPos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED);
    }

    @Override
    protected boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return Block.canSupportCenter(levelReader, blockPos.relative(Direction.UP), Direction.DOWN);
    }

    @Override
    public void press(BlockState blockState, Level level, BlockPos blockPos, @Nullable Player player) {
        super.press(blockState, level, blockPos, player);
        this.updateThings(level, blockPos);
    }

    @Override
    public void unpress(BlockState blockState, Level level, BlockPos blockPos, @Nullable Player player) {
        super.unpress(blockState, level, blockPos, player);
        this.updateThings(level, blockPos);
    }

    @Override
    public int getDirectSignal(BlockState state, BlockGetter blockGetter, BlockPos pos, Direction direction) {
        return state.getValue(POWERED) && direction == Direction.DOWN ? 15 : 0;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos blockPos) {
        return state.getValue(POWERED) ? 15 : 0;
    }

    @Override
    protected boolean isPathfindable(BlockState blockState, PathComputationType pathComputationType) {
        return false;
    }

    @Override
    protected Direction getConnectedDirection(BlockState state) {
        return Direction.DOWN;
    }

    @Override
    public Block getCamouflage() {
        return IBRegistryUtils.getBlockByID(this.camouflage);
    }
}
