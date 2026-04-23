package nl.teamdiopside.infinitybuttons.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import nl.teamdiopside.infinitybuttons.util.BiHashMap;
import nl.teamdiopside.infinitybuttons.util.ShapeManipulator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A button extending vanilla buttons that can be placed on 6 sides
 */
public abstract class ButtonFaced6 extends ButtonBlock {

    public final VoxelShape shapePressed;
    public final VoxelShape shapeUnpressed;
    public final boolean isLever;

    protected final BiHashMap<Direction, AttachFace, VoxelShape> SHAPES_PRESSED = new BiHashMap<>();
    protected final BiHashMap<Direction, AttachFace, VoxelShape> SHAPES_UNPRESSED = new BiHashMap<>();

    public ButtonFaced6(BlockSetType blockSetType, int ticks, Properties properties, VoxelShape shapePressed, VoxelShape shapeUnpressed, boolean isLever) {
        super(blockSetType, ticks, properties);

        this.shapePressed = shapePressed;
        this.shapeUnpressed = shapeUnpressed;
        this.isLever = isLever;

        initShapes(this.shapePressed, SHAPES_PRESSED);
        initShapes(this.shapeUnpressed, SHAPES_UNPRESSED);
    }

    protected void initShapes(VoxelShape inputShape, BiHashMap<Direction, AttachFace, VoxelShape> outputMap) {
        for (Direction dir : Direction.values()) {
            for (AttachFace face : AttachFace.values()) {
                if (dir.getAxis() == Direction.Axis.Y) continue;

                int xTurns = switch (face) {
                    case FLOOR -> 1;
                    case CEILING -> -1;
                    default -> 0;
                };
                int yTurns = (int) (dir.toYRot() / 90) + 2;

                outputMap.put(dir, face, ShapeManipulator.rotate(inputShape, xTurns, yTurns));
            }
        }
    }

    protected abstract int getPressTicks();

    @Override
    public void press(BlockState blockState, Level level, BlockPos blockPos, @Nullable Player player) {
        level.setBlockAndUpdate(blockPos, blockState.setValue(POWERED, true));
        this.updateNeighbours(blockState, level, blockPos);
        if (!this.isLever) level.scheduleTick(blockPos, this, getPressTicks());

        this.playSound(player, level, blockPos, true);
        level.gameEvent(player, GameEvent.BLOCK_ACTIVATE, blockPos);
    }

    public void unpress(BlockState blockState, Level level, BlockPos blockPos, @Nullable Player player) {
        level.setBlockAndUpdate(blockPos, blockState.setValue(POWERED, false));
        this.updateNeighbours(blockState, level, blockPos);

        playSound(player, level, blockPos, false);
        level.gameEvent(player, GameEvent.BLOCK_DEACTIVATE, blockPos);
    }

    // Copied from vanilla
    public void updateNeighbours(BlockState blockState, Level level, BlockPos blockPos) {
        level.updateNeighborsAt(blockPos, this);
        level.updateNeighborsAt(blockPos.relative(getConnectedDirection(blockState).getOpposite()), this);
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
    protected void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (blockState.getValue(POWERED)) {
            unpress(blockState, serverLevel, blockPos, null);
        }
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        AttachFace face = blockState.getValue(FACE);
        Direction dir = blockState.getValue(FACING);
        return blockState.getValue(ButtonBlock.POWERED) ? this.SHAPES_PRESSED.get(dir, face) : this.SHAPES_UNPRESSED.get(dir, face);
    }
}