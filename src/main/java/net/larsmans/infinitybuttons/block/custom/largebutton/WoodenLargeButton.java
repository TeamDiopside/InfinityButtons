package net.larsmans.infinitybuttons.block.custom.largebutton;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.WoodenButtonBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class WoodenLargeButton extends WoodenButtonBlock {
    public WoodenLargeButton(FabricBlockSettings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return LargeButtonShape.outlineShape(state);
    }
}

