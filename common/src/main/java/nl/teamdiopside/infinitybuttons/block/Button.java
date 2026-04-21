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
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public abstract class Button extends Block {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public final VoxelShape shapePressed;
    public final VoxelShape shapeUnpressed;

    protected final HashMap<StringRepresentable, VoxelShape> SHAPES_PRESSED = new HashMap<>();
    protected final HashMap<StringRepresentable, VoxelShape> SHAPES_UNPRESSED = new HashMap<>();

    public Button(Properties properties, VoxelShape shapePressed, VoxelShape shapeUnpressed) {
        super(properties);

        this.shapePressed = shapePressed;
        this.shapeUnpressed = shapeUnpressed;

        this.registerDefaultState(this.stateDefinition.any()
                .setValue(POWERED, false)
        );
    }

    abstract protected void initShapes();

    protected abstract SoundEvent getSound();

    protected abstract int getPressTicks();

    public void press(BlockState blockState, Level level, BlockPos blockPos, @Nullable Player player) {
        level.setBlockAndUpdate(blockPos, blockState.setValue(POWERED, true));
        level.scheduleTick(blockPos, this, getPressTicks());

        this.playSound(player, level, blockPos);
        level.gameEvent(player, GameEvent.BLOCK_ACTIVATE, blockPos);
    }

    protected void playSound(@Nullable Player player, LevelAccessor levelAccessor, BlockPos blockPos) {
        levelAccessor.playSound(player, blockPos, this.getSound(), SoundSource.BLOCKS);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        if (blockState.getValue(POWERED)) {
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
            serverLevel.setBlockAndUpdate(blockPos, blockState.setValue(POWERED, false));
            playSound(null, serverLevel, blockPos);
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

}
