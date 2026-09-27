package nl.teamdiopside.infinitybuttons.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
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
public abstract class ButtonFaced6 extends ButtonBlock implements MaybeArrowActivated {

    public final VoxelShape shapePressed;
    public final VoxelShape shapeUnpressed;

    protected final BiHashMap<Direction, AttachFace, VoxelShape> SHAPES_PRESSED = new BiHashMap<>();
    protected final BiHashMap<Direction, AttachFace, VoxelShape> SHAPES_UNPRESSED = new BiHashMap<>();

    public ButtonFaced6(BlockSetType blockSetType, Properties properties, VoxelShape shapePressed, VoxelShape shapeUnpressed) {
        super(blockSetType, 20, properties);

        this.shapePressed = shapePressed;
        this.shapeUnpressed = shapeUnpressed;

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

    protected abstract int getPressTicks(Level level, BlockPos pos);

    protected boolean isLever(Level level, BlockPos pos) {
        return false;
    }

    @Override
    public void press(BlockState state, Level level, BlockPos pos, @Nullable Player player) {
        level.setBlockAndUpdate(pos, state.setValue(POWERED, true));
        this.updateNeighbours(state, level, pos);
        if (!this.isLever(level, pos)) level.scheduleTick(pos, this, getPressTicks(level, pos));

        this.playSound(player, level, pos, true);
        level.gameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
    }

    public void unpress(BlockState state, Level level, BlockPos pos, @Nullable Player player) {
        level.setBlockAndUpdate(pos, state.setValue(POWERED, false));
        this.updateNeighbours(state, level, pos);

        playSound(player, level, pos, false);
        level.gameEvent(player, GameEvent.BLOCK_DEACTIVATE, pos);
    }

    // Copied from vanilla
    protected void updateNeighbours(BlockState state, Level level, BlockPos pos) {
        level.updateNeighborsAt(pos, this);
        level.updateNeighborsAt(pos.relative(getConnectedDirection(state).getOpposite()), this);
    }

    // Copied from ButtonBlock.checkPressed
    protected boolean arrowPressing(BlockState state, ServerLevel level, BlockPos pos) {
        AbstractArrow abstractArrow = this.infinityButtons$activatedByArrows()
                ? level.getEntitiesOfClass(AbstractArrow.class, state.getShape(level, pos).bounds().move(pos))
                        .stream()
                        .findFirst()
                        .orElse(null)
                : null;
        return abstractArrow != null;
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
    protected void tick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource random) {
        if (arrowPressing(state, serverLevel, pos)) {
            serverLevel.scheduleTick(pos, this, getPressTicks(serverLevel, pos));
            return;
        }

        if (state.getValue(POWERED)) {
            unpress(state, serverLevel, pos, null);
        }
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext) {
        AttachFace face = state.getValue(FACE);
        Direction dir = state.getValue(FACING);
        return state.getValue(ButtonBlock.POWERED) ? this.SHAPES_PRESSED.get(dir, face) : this.SHAPES_UNPRESSED.get(dir, face);
    }

    @Override
    public boolean infinityButtons$activatedByArrows() {
        return false;
    }
}