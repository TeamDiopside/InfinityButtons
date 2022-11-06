package net.larsmans.infinitybuttons.block.custom.largebutton;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.larsmans.infinitybuttons.block.custom.button.WoodenButton;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class WoodenLargeButton extends WoodenButton {
    public WoodenLargeButton(FabricBlockSettings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return LargeButtonShape.outlineShape(state);
    }
}

