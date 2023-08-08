package net.larsmans.infinitybuttons.block.custom.secretbutton;

import net.larsmans.infinitybuttons.block.custom.button.AbstractHorizontalButton;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractSecretButton extends AbstractHorizontalButton {

    public final VoxelShape NORTH_SHAPE;
    public final VoxelShape EAST_SHAPE;
    public final VoxelShape SOUTH_SHAPE;
    public final VoxelShape WEST_SHAPE;
    public final VoxelShape OFF_SHAPE;
    public final Block jadeBlock;

    protected AbstractSecretButton(Settings settings, VoxelShape north_shape, VoxelShape east_shape, VoxelShape south_shape, VoxelShape west_shape, VoxelShape off_shape, Block jadeBlock) {
        super(settings, off_shape, off_shape, off_shape, off_shape, north_shape, east_shape, south_shape, west_shape);
        NORTH_SHAPE = north_shape;
        EAST_SHAPE = east_shape;
        SOUTH_SHAPE = south_shape;
        WEST_SHAPE = west_shape;
        OFF_SHAPE = off_shape;
        this.jadeBlock = jadeBlock;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!state.get(PRESSED) && hit.getSide() == state.get(FACING)) {
            this.powerOn(state, world, pos);
            this.playClickSound(player, world, pos, true);
            world.emitGameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
            return ActionResult.success(world.isClient);
        }
        return ActionResult.FAIL;
    }

    @Override
    protected void playClickSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, boolean pressed) {
        world.playSound(pressed ? player : null, pos, this.getClickSound(pressed), SoundCategory.BLOCKS, 1f, pressed ? 0.6f : 0.5f);
    }

    @Override
    public int getPressTicks() {
        return 50;
    }
}