package nl.teamdiopside.infinitybuttons.block.faced4;

import com.mojang.serialization.Codec;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import nl.teamdiopside.infinitybuttons.registry.IBSounds;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum SecretButtonType implements StringRepresentable {
    BIG_BRICK(
            Shapes.or(
                    Block.box(0, 8, 3, 16, 16, 19),
                    Block.box(0, 0, 0, 16, 8, 16)
            ),
            IBSounds.STONE_SCRAPE
    ),
    BOOKSHELF(
            Shapes.block(),
            SoundEvents.BAMBOO_PLACE
    ),
    BRICK(
            Shapes.block(),
            IBSounds.STONE_SCRAPE
    ),
    CHISELED_NETHER_BRICK(
            Shapes.or(
                    Block.box(0, 0, 0, 16, 3, 16),
                    Block.box(0, 13, 0, 16, 16, 16),
                    Block.box(13, 3, 0, 16, 13, 16),
                    Block.box(3, 3, 3, 13, 13, 16),
                    Block.box(0, 3, 0, 3, 13, 16)
            ),
            IBSounds.STONE_SCRAPE
    ),
    CHISELED_STONE_BRICK(
            Shapes.or(
                    Block.box(0, 0, 0, 16, 3, 16),
                    Block.box(0, 14, 0, 16, 16, 16),
                    Block.box(0, 3, 0, 2, 14, 16),
                    Block.box(2, 3, 0, 13, 14, 13),
                    Block.box(13, 3, 0, 16, 14, 16)
            ),
            IBSounds.STONE_SCRAPE
    ),
    CHISELED_TOOTH(
            Shapes.or(
                    Block.box(0, 0, 0, 16, 1, 16),
                    Block.box(0, 15, 0, 16, 16, 16),
                    Block.box(0, 1, 0, 1, 15, 16),
                    Block.box(1, 1, 3, 15, 15, 16),
                    Block.box(15, 1, 0, 16, 15, 16)
            ),
            IBSounds.STONE_SCRAPE
    ),
    DEEPSLATE_TILE(
            Shapes.or(
                    Block.box(0, 0, 0, 16, 10, 16),
                    Block.box(13, 10, 0, 16, 16, 16),
                    Block.box(0, 10, 3, 13, 16, 19)
            ),
            IBSounds.STONE_SCRAPE
    ),
    EIGHTS_TILES(
            Shapes.or(
                    Block.box(0, 0, 0, 16, 8, 16),
                    Block.box(0, 8, 0, 8, 16, 16),
                    Block.box(8, 8, 3, 16, 16, 19)
            ),
            IBSounds.STONE_SCRAPE
    ),
    LONG_SMALL_BRICK(
            Shapes.block(),
            IBSounds.STONE_SCRAPE
    ),
    MUD_BRICK(
            Shapes.or(
                    Block.box(0, 9, 0, 16, 16, 16),
                    Block.box(12, 0, 0, 16, 9, 16),
                    Block.box(3, 0, 3, 12, 9, 16),
                    Block.box(0, 0, 0, 3, 9, 16)
            ),
            SoundEvents.MUD_BRICKS_PLACE
    ),
    NETHER_BRICK(
            Shapes.block(),
            IBSounds.STONE_SCRAPE
    ),
    PLANK(
            Shapes.or(
                    Block.box(0, 0, 0, 16, 4, 16),
                    Block.box(0, 9, 0, 16, 16, 16),
                    Block.box(0, 4, 3, 16, 9, 16)
            ),
            IBSounds.WOOD_SCRAPE
    ),
    SMALL_BRICK(
            Shapes.block(),
            IBSounds.STONE_SCRAPE
    ),
    SMALL_TILE(
            Shapes.block(),
            IBSounds.STONE_SCRAPE
    ),
    TINTED_BIG_BRICK(
            Shapes.or(
                    Block.box(0, 8, 3, 16, 16, 19),
                    Block.box(0, 0, 0, 16, 8, 16)
            ),
            IBSounds.STONE_SCRAPE
    ),
    TINTED_CHISELED_STONE_BRICK(
            Shapes.or(
                    Block.box(0, 0, 0, 16, 3, 16),
                    Block.box(0, 14, 0, 16, 16, 16),
                    Block.box(0, 3, 0, 2, 14, 16),
                    Block.box(2, 3, 0, 13, 14, 13),
                    Block.box(13, 3, 0, 16, 14, 16)
            ),
            IBSounds.STONE_SCRAPE
    );

    public static final Codec<SecretButtonType> CODEC = StringRepresentable.fromEnum(SecretButtonType::values);

    public final VoxelShape shapePressed;
    public final VoxelShape shapeUnpressed;
    public final Supplier<SoundEvent> sound;

    SecretButtonType(VoxelShape shapePressed, VoxelShape shapeUnpressed, Supplier<SoundEvent> sound) {
        this.shapePressed = shapePressed;
        this.shapeUnpressed = shapeUnpressed;
        this.sound = sound;
    }

    SecretButtonType(VoxelShape shapePressed, SoundEvent sound) {
        this(shapePressed, Shapes.block(), () -> sound);
    }

    SecretButtonType(VoxelShape shapePressed, RegistrySupplier<SoundEvent> sound) {
        this(shapePressed, Shapes.block(), sound);
    }

    @Override
    public @NotNull String getSerializedName() {
        return name().toLowerCase();
    }
}
