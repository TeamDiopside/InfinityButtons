package nl.teamdiopside.infinitybuttons.block.faced6;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import nl.teamdiopside.infinitybuttons.block.ButtonFaced6;

public class LampButton extends ButtonFaced6 {
    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(3, 3, 15, 13, 13, 16),
            Block.box(4, 4, 8, 12, 12, 15));

    protected final boolean isLever;

    public LampButton(BlockSetType blockSetType, Properties properties, boolean isLever) {
        super(blockSetType, 20, properties, SHAPE, SHAPE);
        this.isLever = isLever;
    }

    @Override
    protected boolean isLever(Level level, BlockPos blockPos) {
        return this.isLever;
    }

    @Override
    protected int getPressTicks(Level level, BlockPos blockPos) {
        return 20;
    }

    @Override
    protected SoundType getSoundType(BlockState blockState) {
        return SoundType.GLASS;
    }
}
