package nl.teamdiopside.infinitybuttons.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public abstract class InfinityButton extends Block {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    protected final HashMap<StringRepresentable, VoxelShape> SHAPES_PRESSED = new HashMap<>();
    protected final HashMap<StringRepresentable, VoxelShape> SHAPES_UNPRESSED = new HashMap<>();

    public final VoxelShape shapePressed;
    public final VoxelShape shapeUnpressed;
    public final boolean isLever;

    public InfinityButton(Properties properties, VoxelShape shapePressed, VoxelShape shapeUnpressed, boolean isLever) {
        super(properties);

        this.shapePressed = shapePressed;
        this.shapeUnpressed = shapeUnpressed;
        this.isLever = isLever;

        this.registerDefaultState(this.stateDefinition.any()
                .setValue(POWERED, false)
        );
    }

    protected abstract SoundEvent getSound(boolean press);

    protected abstract int getPressTicks();

    protected abstract Direction getConnectedDirection(BlockState state);

    public void press(BlockState blockState, Level level, BlockPos blockPos, @Nullable Player player) {
        level.setBlockAndUpdate(blockPos, blockState.setValue(POWERED, true));
        this.updateNeighbours(blockState, level, blockPos);
        if (!this.isLever) level.scheduleTick(blockPos, this, getPressTicks());

        this.playSound(player, level, blockPos, true);
        if (this.isSignalSource(blockState)) level.gameEvent(player, GameEvent.BLOCK_ACTIVATE, blockPos);
    }

    public void unpress(BlockState blockState, Level level, BlockPos blockPos, @Nullable Player player) {
        level.setBlockAndUpdate(blockPos, blockState.setValue(POWERED, false));
        this.updateNeighbours(blockState, level, blockPos);
        playSound(player, level, blockPos, false);

        if (this.isSignalSource(blockState)) level.gameEvent(player, GameEvent.BLOCK_DEACTIVATE, blockPos);
    }

    // Copied from vanilla
    protected void updateNeighbours(BlockState blockState, Level level, BlockPos blockPos) {
        level.updateNeighborsAt(blockPos, this);
        level.updateNeighborsAt(blockPos.relative(getConnectedDirection(blockState).getOpposite()), this);
    }

    protected void playSound(@Nullable Player player, LevelAccessor levelAccessor, BlockPos blockPos, boolean press) {
        levelAccessor.playSound(player, blockPos, this.getSound(press), SoundSource.BLOCKS);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        if (blockState.getValue(POWERED)) {
            if (this.isLever) {
                this.unpress(blockState, level, blockPos, player);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.CONSUME;
        } else {
            this.press(blockState, level, blockPos, player);
            return InteractionResult.SUCCESS;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    @Override
    protected void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (blockState.getValue(POWERED)) {
            unpress(blockState, serverLevel, blockPos, null);
        }
    }

    @Override
    protected boolean isSignalSource(BlockState blockState) {
        return true;
    }

    @Override
    protected int getSignal(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
        return blockState.getValue(POWERED) ? 15 : 0;
    }

    @Override
    protected int getDirectSignal(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
        return getSignal(blockState, blockGetter, blockPos, direction);
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return blockState.getValue(POWERED) ? this.shapePressed : this.shapeUnpressed;
    }
}
