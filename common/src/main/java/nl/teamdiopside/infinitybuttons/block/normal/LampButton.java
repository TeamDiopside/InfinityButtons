package nl.teamdiopside.infinitybuttons.block.normal;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import nl.teamdiopside.infinitybuttons.block.ButtonFaced6;

public class LampButton extends ButtonFaced6 {
    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(3, 3, 15, 13, 13, 16),
            Block.box(4, 4, 8, 12, 12, 15));

    public LampButton(BlockSetType blockSetType, Properties properties, boolean isLever) {
        super(blockSetType, 20, properties, SHAPE, SHAPE, isLever);
    }

    @Override
    protected int getPressTicks() {
        return 20;
    }
}
