package net.larsmans.infinitybuttons.block.custom.signbutton;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.larsmans.infinitybuttons.block.custom.button.AbstractButtonWithEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class LetterButton extends AbstractButtonWithEntity {

    public static final EnumProperty<LetterButtonEnum> CHARACTER = EnumProperty.of("character", LetterButtonEnum.class);

    public LetterButton(FabricBlockSettings settings) {
        super(true, settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(PRESSED, false).with(FACE, WallMountLocation.FLOOR).with(CHARACTER, LetterButtonEnum.NONE));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new LetterButtonEntity(pos, state);
    }

    @Override
    public int getPressTicks() {
        return 30;
    }

    @Override
    protected SoundEvent getClickSound(boolean pressed) {
        return SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON;
    }
}
