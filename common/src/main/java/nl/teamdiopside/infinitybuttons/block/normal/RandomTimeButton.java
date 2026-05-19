package nl.teamdiopside.infinitybuttons.block.normal;

import net.minecraft.world.level.block.state.properties.BlockSetType;

public class RandomTimeButton extends NormalButton {
    public final boolean gravelSound;

    public RandomTimeButton(BlockSetType blockSetType, Properties properties, boolean large, boolean isLever, boolean gravelSound) {
        super(blockSetType, 10, properties, large, isLever);
        this.gravelSound = gravelSound;
    }

    @Override
    protected int getPressTicks() {
        return (int) Math.floor(Math.random()*(90-10+1)+10);
    }
}
