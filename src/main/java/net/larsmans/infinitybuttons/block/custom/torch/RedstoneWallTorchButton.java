package net.larsmans.infinitybuttons.block.custom.torch;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class RedstoneWallTorchButton extends RedstoneTorchButton {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public RedstoneWallTorchButton(FabricBlockSettings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(LIT, false).with(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return WallTorchBlock.getBoundingShape(state);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return Blocks.WALL_TORCH.canPlaceAt(state, world, pos);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return Blocks.WALL_TORCH.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = Blocks.WALL_TORCH.getPlacementState(ctx);
        return blockState == null ? null : this.getDefaultState().with(FACING, blockState.get(FACING));
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!state.get(LIT)) {
            return;
        }
        Direction direction = state.get(FACING).getOpposite();
        double e = (double)pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.2 + 0.27 * (double)direction.getOffsetX();
        double f = (double)pos.getY() + 0.7 + (random.nextDouble() - 0.5) * 0.2 + 0.22;
        double g = (double)pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.2 + 0.27 * (double)direction.getOffsetZ();
        world.addParticle(this.particle, e, f, g, 0.0, 0.0, 0.0);
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (state.get(LIT) && state.get(FACING) != direction) {
            return 15;
        }
        return 0;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return Blocks.WALL_TORCH.rotate(state, rotation);
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return Blocks.WALL_TORCH.mirror(state, mirror);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }
}
