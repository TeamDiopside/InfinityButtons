package net.larsmans.infinitybuttons.block.custom.letterbutton;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.larsmans.infinitybuttons.block.custom.button.AbstractSmallButton;
import net.larsmans.infinitybuttons.block.custom.letterbutton.gui.LetterButtonGui;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class LetterButton extends AbstractSmallButton {

    public static final EnumProperty<LetterButtonEnum> CHARACTER = EnumProperty.of("character", LetterButtonEnum.class);

    public LetterButton(FabricBlockSettings settings) {
        super(true, true, settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(PRESSED, false).with(FACE, WallMountLocation.FLOOR).with(CHARACTER, LetterButtonEnum.NONE));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.isSneaking()) {
            if (world.isClient) {
                MinecraftClient.getInstance().setScreen(new LetterButtonGui(this, state, world, pos));
            }
            return ActionResult.success(world.isClient);
        } else {
            return super.onUse(state, world, pos, player, hand, hit);
        }
    }

    @Override
    public int getPressTicks() {
        return 30;
    }

    @Override
    protected SoundEvent getClickSound(boolean pressed) {
        return SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON;
    }

    public int getEnumId(BlockState state) {
        return state.get(CHARACTER).ordinal();
    }

    public void setState(BlockState state, World world, BlockPos pos, LetterButtonEnum buttonEnum) {
        world.setBlockState(pos, state.with(CHARACTER, buttonEnum), 3);
        this.updateNeighbors(state, world, pos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(CHARACTER);
    }
}
