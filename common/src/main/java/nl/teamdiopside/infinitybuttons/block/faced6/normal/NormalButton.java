package nl.teamdiopside.infinitybuttons.block.faced6.normal;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.shapes.VoxelShape;
import nl.teamdiopside.infinitybuttons.block.ButtonFaced6;

public class NormalButton extends ButtonFaced6 {
    private static final VoxelShape LARGE_PRESSED = Block.box(4, 4, 15, 12, 12, 16);
    private static final VoxelShape LARGE_UNPRESSED = Block.box(4, 4, 14, 12, 12, 16);

    private static final VoxelShape SMALL_PRESSED = Block.box(5, 6, 15, 11, 10, 16);
    private static final VoxelShape SMALL_UNPRESSED = Block.box(5, 6, 14, 11, 10, 16);
    protected final boolean large;
    protected final int pressTicks;


    public NormalButton(BlockSetType blockSetType, int ticks, Properties properties, boolean large, boolean isLever) {
        super(blockSetType, ticks, properties,
                large ? LARGE_PRESSED   : SMALL_PRESSED,
                large ? LARGE_UNPRESSED : SMALL_UNPRESSED,
                isLever
        );
        this.large = large;
        this.pressTicks = ticks;
    }

    public boolean isLarge() {
        return large;
    }

    @Override
    protected int getPressTicks() {
        return this.pressTicks;
    }

}
