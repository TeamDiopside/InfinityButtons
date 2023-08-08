package net.larsmans.infinitybuttons.block.custom.emergencybutton;

import net.minecraft.util.StringIdentifiable;

public enum SEBStateEnum implements StringIdentifiable {

    OPEN("open"),
    CLOSED("closed"),
    PRESSED("pressed");

    private final String name;

    SEBStateEnum(String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return this.name;
    }
}
