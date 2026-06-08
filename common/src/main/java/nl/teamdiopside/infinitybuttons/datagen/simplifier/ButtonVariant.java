package nl.teamdiopside.infinitybuttons.datagen.simplifier;

public enum ButtonVariant {
    BASE(""),
    PRESSED("_pressed"),
    INVENTORY("_inventory");

    public final String suffix;

    ButtonVariant(String name) {
        this.suffix = name;
    }
}
