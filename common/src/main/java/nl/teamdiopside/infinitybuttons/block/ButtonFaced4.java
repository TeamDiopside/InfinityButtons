package nl.teamdiopside.infinitybuttons.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import nl.teamdiopside.infinitybuttons.util.ShapeManipulator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A button NOT extending vanilla buttons that can be placed on 4 sides
 */
public abstract class ButtonFaced4 extends InfinityButton {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

    protected ButtonFaced4(Properties properties, VoxelShape shapePressed, VoxelShape shapeUnpressed, boolean isLever) {
        super(properties, shapePressed, shapeUnpressed, isLever);

        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(POWERED, false)
        );

        this.initShapes();
    }

    protected void initShapes() {
        for (Direction dir : Direction.values()) {
            if (dir.getAxis() == Direction.Axis.Y) continue;

            this.SHAPES_PRESSED.put(dir, ShapeManipulator.rotateY(this.shapePressed, (int) (dir.toYRot() / 90) + 2));
            this.SHAPES_UNPRESSED.put(dir, ShapeManipulator.rotateY(this.shapeUnpressed, (int) (dir.toYRot() / 90) + 2));
        }
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return blockState.getValue(POWERED)
                ? this.SHAPES_PRESSED.get(blockState.getValue(FACING))
                : this.SHAPES_UNPRESSED.get(blockState.getValue(FACING));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        Player player = blockPlaceContext.getPlayer();
        return (player == null) ? this.defaultBlockState() : this.defaultBlockState().setValue(
                FACING, player.getDirection().getOpposite()
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    protected @NotNull BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    @Override
    protected @NotNull BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }

    @Override
    protected Direction getConnectedDirection(BlockState state) {
        return state.getValue(FACING).getOpposite();
    }
}
