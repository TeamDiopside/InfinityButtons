package nl.teamdiopside.infinitybuttons.block.faced6.normal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
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
    protected final boolean isLever;

    protected final SoundType soundType;

    public NormalButton(BlockSetType blockSetType, int ticks, Properties properties, boolean large, boolean isLever) {
        this(blockSetType, ticks, properties, large, isLever, SoundType.STONE);
    }

    public NormalButton(BlockSetType blockSetType, int ticks, Properties properties, boolean large, boolean isLever, SoundType soundType) {
        super(blockSetType, ticks, properties,
                large ? LARGE_PRESSED   : SMALL_PRESSED,
                large ? LARGE_UNPRESSED : SMALL_UNPRESSED
        );
        this.large = large;
        this.pressTicks = ticks;
        this.soundType = soundType;
        this.isLever = isLever;
    }

    public boolean isLarge() {
        return large;
    }

    @Override
    protected boolean isLever(Level level, BlockPos blockPos) {
        return this.isLever;
    }

    @Override
    protected int getPressTicks(Level level, BlockPos blockPos) {
        return this.pressTicks;
    }

    @Override
    protected SoundType getSoundType(BlockState blockState) {
        return this.soundType;
    }
}
