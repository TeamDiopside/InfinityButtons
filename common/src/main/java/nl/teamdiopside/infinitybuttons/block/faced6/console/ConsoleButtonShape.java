package nl.teamdiopside.infinitybuttons.block.faced6.console;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public enum ConsoleButtonShape implements StringRepresentable {
    SMALL(Block.box(3, 5, 14, 13, 11, 16)),
    NORMAL(Block.box(4, 3, 14, 12, 13, 16)),
    LARGE(Block.box(1, 3, 14, 15, 13, 16));

    public static final Codec<ConsoleButtonShape> CODEC = StringRepresentable.fromEnum(ConsoleButtonShape::values);

    public final VoxelShape shape;

    ConsoleButtonShape(VoxelShape shape) {
        this.shape = shape;
    }

    @Override
    public @NotNull String getSerializedName() {
        return name().toLowerCase();
    }
}
