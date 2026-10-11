package nl.teamdiopside.infinitybuttons.block.faced6.normal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class RandomTimeButton extends NormalButton {
    public RandomTimeButton(BlockSetType blockSetType, Properties properties, boolean large, boolean isLever, SoundType soundType) {
        super(blockSetType, 10, properties, large, isLever, soundType);
    }

    @Override
    protected int getPressTicks(Level level, BlockPos pos) {
        return (int) Math.floor(Math.random()*(90-10+1)+10);
    }
}
