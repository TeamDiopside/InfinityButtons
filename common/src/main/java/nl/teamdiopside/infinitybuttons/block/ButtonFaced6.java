package nl.teamdiopside.infinitybuttons.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import nl.teamdiopside.infinitybuttons.util.BiHashMap;
import nl.teamdiopside.infinitybuttons.util.ShapeManipulator;
import org.jetbrains.annotations.NotNull;

/**
 * A button extending vanilla buttons that can be placed on 6 sides
 */
public abstract class ButtonFaced6 extends ButtonBlock {

    public final VoxelShape shapePressed;
    public final VoxelShape shapeUnpressed;

    protected final BiHashMap<Direction, AttachFace, VoxelShape> SHAPES_PRESSED = new BiHashMap<>();
    protected final BiHashMap<Direction, AttachFace, VoxelShape> SHAPES_UNPRESSED = new BiHashMap<>();

    public ButtonFaced6(BlockSetType blockSetType, int ticks, Properties properties, VoxelShape shapePressed, VoxelShape shapeUnpressed) {
        super(blockSetType, ticks, properties);

        this.shapePressed = shapePressed;
        this.shapeUnpressed = shapeUnpressed;

        initShapes();
    }

    private void initShapes() {
        for (Direction dir : Direction.values()) {
            for (AttachFace face : AttachFace.values()) {
                if (dir.getAxis() == Direction.Axis.Y) continue;

                int xTurns = switch (face) {
                    case FLOOR -> 1;
                    case CEILING -> -1;
                    default -> 0;
                };
                int yTurns = (int) (dir.toYRot() / 90) + 2;

                this.SHAPES_PRESSED.put(dir, face, ShapeManipulator.rotate(this.shapePressed, xTurns, yTurns));
                this.SHAPES_UNPRESSED.put(dir, face, ShapeManipulator.rotate(this.shapeUnpressed, xTurns, yTurns));
            }
        }
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        AttachFace face = blockState.getValue(FACE);
        Direction dir = blockState.getValue(FACING);
        return blockState.getValue(ButtonBlock.POWERED) ? this.SHAPES_PRESSED.get(dir, face) : this.SHAPES_UNPRESSED.get(dir, face);
    }
}