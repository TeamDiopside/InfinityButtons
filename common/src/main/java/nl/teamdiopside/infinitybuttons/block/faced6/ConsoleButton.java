package nl.teamdiopside.infinitybuttons.block.faced6;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import nl.teamdiopside.infinitybuttons.block.ButtonFaced6;
import nl.teamdiopside.infinitybuttons.registry.IBSounds;

public class ConsoleButton extends ButtonFaced6 {
    public static final int PRESS_TICKS = 50;

    public ConsoleButton(Properties properties, ConsoleButtonType type, boolean isLever) {
        super(BlockSetType.IRON, PRESS_TICKS, properties, type.shape, type.shape, isLever);
    }

    @Override
    protected int getPressTicks() {
        return PRESS_TICKS;
    }

    @Override
    protected SoundEvent getSound(boolean press) {
        return press ? IBSounds.CONSOLE_BEEP.get() : isLever ? IBSounds.CONSOLE_UNBEEP.get() : IBSounds.SILENT.get();
    }

    @Override
    protected SoundType getSoundType(BlockState blockState) {
        return SoundType.METAL;
    }
}
