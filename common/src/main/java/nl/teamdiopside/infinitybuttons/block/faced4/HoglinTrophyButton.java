package nl.teamdiopside.infinitybuttons.block.faced4;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import nl.teamdiopside.infinitybuttons.block.ButtonFaced4;
import nl.teamdiopside.infinitybuttons.compat.jade.JadeCamouflaged;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;

import java.util.stream.Stream;

public class HoglinTrophyButton extends ButtonFaced4 implements JadeCamouflaged {
    protected static final VoxelShape SHAPE = Stream.of(
            Block.box(1.0F, 0.0F, 15.0F, 15.0F, 3.0F, 16.0F),
            Block.box(0.0F, 3.0F, 15.0F, 16.0F, 15.0F, 16.0F),
            Block.box(1.0F, 4.0F, 13.0F, 15.0F, 13.0F, 15.0F),
            Block.box(2.0F, 8.0F, 9.0F, 14.0F, 12.0F, 13.0F),
            Block.box(2.0F, 6.0F, 7.0F, 14.0F, 10.0F, 11.0F),
            Block.box(2.0F, 4.0F, 5.0F, 14.0F, 8.0F, 9.0F),
            Block.box(2.0F, 2.0F, 3.0F, 14.0F, 6.0F, 7.0F)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public HoglinTrophyButton(Properties properties) {
        super(properties, SHAPE, SHAPE);
    }

    @Override
    protected SoundEvent getSound(boolean press) {
        return press ? SoundEvents.STONE_BUTTON_CLICK_ON : SoundEvents.STONE_BUTTON_CLICK_OFF;
    }

    @Override
    protected int getPressTicks() {
        return 15;
    }

    @Override
    public Block getCamouflage() {
        return IBRegistryUtils.getBlockByID(ResourceLocation.fromNamespaceAndPath("mynethersdelight", "waxed_hoglin_trophy"));
    }
}
