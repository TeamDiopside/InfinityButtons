package nl.teamdiopside.infinitybuttons.datagen.simplifier;

public enum ButtonModelVariant {
    BASE(""),
    PRESSED("_pressed"),
    INVENTORY("_inventory");

    public final String suffix;

    ButtonModelVariant(String name) {
        this.suffix = name;
    }
}
