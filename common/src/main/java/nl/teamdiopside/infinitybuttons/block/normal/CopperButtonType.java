package nl.teamdiopside.infinitybuttons.block.normal;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum CopperButtonType implements StringRepresentable {
    NORMAL("normal"),
    WAXED("waxed"),
    STICKY("sticky");

    public static final Codec<CopperButtonType> CODEC = StringRepresentable.fromEnum(CopperButtonType::values);

    private final String name;

    CopperButtonType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public @NotNull String getSerializedName() {
        return getName();
    }
}