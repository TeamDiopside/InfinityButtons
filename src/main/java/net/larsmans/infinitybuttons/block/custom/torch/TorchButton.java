package net.larsmans.infinitybuttons.block.custom.torch;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.larsmans.infinitybuttons.block.custom.button.AbstractHorizontalButton;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class TorchButton extends AbstractHorizontalButton {

    protected static final VoxelShape BOUNDING_SHAPE = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 10.0, 10.0);
    protected final ParticleEffect particle;

    public TorchButton(FabricBlockSettings settings, ParticleEffect particle) {
        super(settings, BOUNDING_SHAPE, BOUNDING_SHAPE, BOUNDING_SHAPE, BOUNDING_SHAPE, BOUNDING_SHAPE, BOUNDING_SHAPE, BOUNDING_SHAPE, BOUNDING_SHAPE);
        this.particle = particle;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BOUNDING_SHAPE;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.DOWN && !this.canPlaceAt(state, world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return state;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return sideCoversSmallSquare(world, pos.down(), Direction.UP);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        Direction direction = state.get(FACING);
        if (state.get(PRESSED)) {
            double d = (double) pos.getX() + 0.5;
            double e = (double) pos.getY() + 0.63;
            double f = (double) pos.getZ() + 0.5;
            Direction direction2 = direction.getOpposite();
            world.addParticle(ParticleTypes.SMOKE,
                    d + 0.23 * (double) direction2.getOffsetX(),
                    e,
                    f + 0.23 * (double) direction2.getOffsetZ(),
                    0.0, 0.0, 0.0);
            world.addParticle(this.particle,
                    d + 0.23 * (double) direction2.getOffsetX(),
                    e,
                    f + 0.23 * (double) direction2.getOffsetZ(),
                    0.0, 0.0, 0.0);
        } else {
            super.randomDisplayTick(state, world, pos, random);
        }
    }

    @Override
    public int getPressTicks() {
        return 50;
    }

    @Override
    protected SoundEvent getClickSound(boolean pressed) {
        return SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON;
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (state.get(PRESSED) && Direction.DOWN.getOpposite() == direction) {
            return 15;
        }
        return 0;
    }

    @Override
    public void updateNeighbors(BlockState state, World world, BlockPos pos) {
        world.updateNeighborsAlways(pos, this);
        world.updateNeighborsAlways(pos.offset(Direction.DOWN), this);
    }
}
