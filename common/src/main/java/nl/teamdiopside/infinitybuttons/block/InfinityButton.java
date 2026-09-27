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

    public InfinityButton(Properties properties, VoxelShape shapePressed, VoxelShape shapeUnpressed) {
        super(properties);

        this.shapePressed = shapePressed;
        this.shapeUnpressed = shapeUnpressed;

        this.registerDefaultState(this.stateDefinition.any()
                .setValue(POWERED, false)
        );
    }

    protected abstract SoundEvent getSound(boolean press);

    protected abstract int getPressTicks();

    protected abstract Direction getConnectedDirection(BlockState state);

    protected boolean isLever(Level level, BlockPos pos) {
        return false;
    }

    public void press(BlockState state, Level level, BlockPos pos, @Nullable Player player) {
        level.setBlockAndUpdate(pos, state.setValue(POWERED, true));
        this.updateNeighbours(state, level, pos);
        if (!this.isLever(level, pos)) level.scheduleTick(pos, this, getPressTicks());

        this.playSound(player, level, pos, true);
        if (this.isSignalSource(state)) level.gameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
    }

    public void unpress(BlockState state, Level level, BlockPos pos, @Nullable Player player) {
        level.setBlockAndUpdate(pos, state.setValue(POWERED, false));
        this.updateNeighbours(state, level, pos);
        playSound(player, level, pos, false);

        if (this.isSignalSource(state)) level.gameEvent(player, GameEvent.BLOCK_DEACTIVATE, pos);
    }

    // Copied from vanilla
    protected void updateNeighbours(BlockState state, Level level, BlockPos pos) {
        level.updateNeighborsAt(pos, this);
        level.updateNeighborsAt(pos.relative(getConnectedDirection(state).getOpposite()), this);
    }

    protected void playSound(@Nullable Player player, LevelAccessor levelAccessor, BlockPos pos, boolean press) {
        levelAccessor.playSound(player, pos, this.getSound(press), SoundSource.BLOCKS);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult blockHitResult) {
        if (state.getValue(POWERED)) {
            if (this.isLever(level, pos)) {
                this.unpress(state, level, pos, player);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.CONSUME;
        } else {
            this.press(state, level, pos, player);
            return InteractionResult.SUCCESS;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    @Override
    protected void tick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource random) {
        if (state.getValue(POWERED)) {
            unpress(state, serverLevel, pos, null);
        }
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter blockGetter, BlockPos pos, Direction direction) {
        return state.getValue(POWERED) ? 15 : 0;
    }

    @Override
    protected int getDirectSignal(BlockState state, BlockGetter blockGetter, BlockPos pos, Direction direction) {
        return getSignal(state, blockGetter, pos, direction);
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext) {
        return state.getValue(POWERED) ? this.shapePressed : this.shapeUnpressed;
    }
}
