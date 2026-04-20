package nl.teamdiopside.infinitybuttons.block.normal;

public enum CopperButtonType {
    NORMAL("normal"),
    WAXED("waxed"),
    STICKY("sticky");

    private final String name;

    CopperButtonType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}