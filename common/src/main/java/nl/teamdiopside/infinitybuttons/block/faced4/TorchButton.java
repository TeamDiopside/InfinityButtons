package nl.teamdiopside.infinitybuttons.block.faced4;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import nl.teamdiopside.infinitybuttons.block.ButtonFaced4;
import org.jetbrains.annotations.Nullable;

public class TorchButton extends ButtonFaced4 {
    protected static final VoxelShape TORCH_SHAPE = Block.box(6.0, 0.0, 6.0, 10.0, 10.0, 10.0);
    protected static final VoxelShape WALL_TORCH_SHAPE = Block.box(5.5, 3.0, 11.0, 10.5, 13.0, 16.0);

    protected final ParticleOptions particle;
    public final boolean isWall;
    public final boolean isRedstone;

    public TorchButton(Properties properties, ParticleOptions particle, boolean isLever, boolean isWall, boolean isRedstone) {
        super(
                properties,
                isWall ? WALL_TORCH_SHAPE : TORCH_SHAPE,
                isWall ? WALL_TORCH_SHAPE : TORCH_SHAPE,
                isLever
        );

        this.particle = particle;
        this.isWall = isWall;
        this.isRedstone = isRedstone;
    }


    @Override
    protected SoundEvent getSound(boolean press) {
        return press ? SoundEvents.WOODEN_BUTTON_CLICK_ON : SoundEvents.WOODEN_BUTTON_CLICK_OFF;
    }

    @Override
    protected int getPressTicks() {
        return 50;
    }

//    @Override
//    protected int getSignal(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
//        boolean properDirectionPower = blockState.getValue(POWERED) && direction == Direction.UP;
//        return properDirectionPower ? 15 : 0;
//    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        // For some reason, when this is called, it's always the wall variant of the torch.
        // Therefore, we cannot use this.isWall to determine placement logic.
        if (blockPlaceContext.getClickedFace().getAxis().isVertical()) {
            return super.getStateForPlacement(blockPlaceContext);
        }
        return this.defaultBlockState().setValue(FACING, blockPlaceContext.getClickedFace());
    }

    @Override
    protected boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        Direction horse = this.isWall ? blockState.getValue(FACING).getOpposite() : Direction.DOWN;

        BlockPos blockPos2 = blockPos.relative(horse);
        BlockState otherState = levelReader.getBlockState(blockPos2);

        return otherState.isFaceSturdy(levelReader, blockPos2, horse.getOpposite());
    }
    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor levelAccessor, BlockPos pos, BlockPos neighborPos) {
        Direction horse = this.isWall ? state.getValue(FACING).getOpposite() : Direction.DOWN;
        if (direction == horse && !this.canSurvive(state, levelAccessor, pos)) {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, direction, neighborState, levelAccessor, pos, neighborPos);
    }

    @Override // Particles
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        boolean isRedstone = this.particle instanceof DustParticleOptions;
        Direction direction = state.getValue(FACING);
        Direction opposite = direction.getOpposite();

        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.7;
        double z = pos.getZ() + 0.5;

        double offset = 0.0;
        double verticalOffset = 0.0;

        if (state.getValue(POWERED) && !isRedstone) {
            if (this.isWall) {
                y = pos.getY() + 0.6;
                offset = 0.05;
                verticalOffset = 0.15;
            } else {
                y = pos.getY() + 0.63;
                offset = 0.23;
            }
        } else if (this.isWall) {
            offset = 0.27;
            verticalOffset = 0.22;
        }

        if (isRedstone && !state.getValue(POWERED)) return; // Redstone off -> No particles
        level.addParticle(
                this.particle,
                x + offset * opposite.getStepX(),
                y + verticalOffset,
                z + offset * opposite.getStepZ(),
                0.0, 0.0, 0.0
        );

        if (isRedstone) return; // Redstone doesn't have smoke
        level.addParticle(
                ParticleTypes.SMOKE,
                x + offset * opposite.getStepX(),
                y + verticalOffset,
                z + offset * opposite.getStepZ(),
                0.0, 0.0, 0.0
        );
    }
}
