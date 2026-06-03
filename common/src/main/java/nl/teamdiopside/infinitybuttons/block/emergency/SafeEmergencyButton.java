package nl.teamdiopside.infinitybuttons.block.emergency;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import nl.teamdiopside.infinitybuttons.util.BiHashMap;
import org.jetbrains.annotations.NotNull;

public class SafeEmergencyButton extends EmergencyButton {
    public static final BooleanProperty CLOSED = BooleanProperty.create("closed");

    private static final VoxelShape BASE = Block.box(3, 3, 14, 13, 13, 16);
    private static final VoxelShape SHAPE_CLOSED = Shapes.or(Block.box(4, 4, 7, 12, 12, 14), BASE);
    private static final VoxelShape SHAPE_OPEN = Shapes.or(Block.box(5, 5, 10, 11, 11, 14), BASE);
    private static final VoxelShape SHAPE_PRESSED = Shapes.or(Block.box(5, 5, 12, 11, 11, 14), BASE);

    protected final BiHashMap<Direction, AttachFace, VoxelShape> SHAPES_CLOSED = new BiHashMap<>();

    public SafeEmergencyButton(Properties properties) {
        super(BlockSetType.STONE, properties, SHAPE_PRESSED, SHAPE_OPEN);

        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(POWERED, false)
                .setValue(CLOSED, true)
        );

        initShapes(SHAPE_CLOSED, this.SHAPES_CLOSED);
    }

    public void openCase(BlockState state, Level level, BlockPos pos) {
        level.setBlockAndUpdate(pos, state.setValue(CLOSED, false));
        this.updateNeighbors(state, level, pos);
    }

    public void closeCase(BlockState state, Level level, BlockPos pos) {
        level.setBlockAndUpdate(pos, state.setValue(CLOSED, true));
        this.updateNeighbors(state, level, pos);
    }

    private void updateNeighbors(BlockState state, Level level, BlockPos pos) {
        level.updateNeighborsAt(pos, this);
        level.updateNeighborsAt(pos.relative(EmergencyButton.getDirection(state).getOpposite()), this);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CLOSED, FACING, FACE, POWERED);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        if (!player.isShiftKeyDown()) {
            if (blockState.getValue(CLOSED)) return InteractionResult.FAIL; // TODO: Show actionbar
            return super.useWithoutItem(blockState, level, blockPos, player, blockHitResult);
        }

        if (blockState.getValue(CLOSED)) {
            this.openCase(blockState, level, blockPos);
            return InteractionResult.SUCCESS;
        } else {
            this.closeCase(blockState, level, blockPos);
            return InteractionResult.SUCCESS;
        }
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        AttachFace face = blockState.getValue(FACE);
        Direction dir = blockState.getValue(FACING);

        if (blockState.getValue(CLOSED)) return this.SHAPES_CLOSED.get(dir, face);

        return super.getShape(blockState, blockGetter, blockPos, collisionContext);
    }
}
