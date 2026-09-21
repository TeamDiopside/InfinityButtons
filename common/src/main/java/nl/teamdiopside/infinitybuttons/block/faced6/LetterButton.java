package nl.teamdiopside.infinitybuttons.block.faced6;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.VoxelShape;
import nl.teamdiopside.infinitybuttons.block.ButtonFaced6;

public class LetterButton extends ButtonFaced6 {
    public static final EnumProperty<LetterButtonState> CHARACTER = EnumProperty.create("character", LetterButtonState.class);

    private static final VoxelShape SHAPE_PRESSED = Block.box(4, 4, 15, 12, 12, 16);
    private static final VoxelShape SHAPE_UNPRESSED = Block.box(4, 4, 14, 12, 12, 16);

    protected final boolean isLever;

    public LetterButton(Properties properties, boolean isLever) {
        super(BlockSetType.STONE, 30, properties, SHAPE_PRESSED, SHAPE_UNPRESSED);
        this.isLever = isLever;

        this.registerDefaultState(this.defaultBlockState()
                .setValue(CHARACTER, LetterButtonState.NONE)
        );
    }

    @Override
    protected boolean isLever(Level level, BlockPos blockPos) {
        return this.isLever;
    }

    @Override
    protected int getPressTicks(Level level, BlockPos blockPos) {
        return 30;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CHARACTER);
    }
}
