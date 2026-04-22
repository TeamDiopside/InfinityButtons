package nl.teamdiopside.infinitybuttons.block.emergency;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum SafeEmergencyButtonState implements StringRepresentable {
    OPEN("open"),
    CLOSED("closed"),
    PRESSED("pressed");

    private final String name;

    SafeEmergencyButtonState(String name) {
        this.name = name;
    }

    @Override
    public @NotNull String getSerializedName() {
            return this.name;
        }
}
