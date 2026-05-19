package nl.teamdiopside.infinitybuttons.block.faced6.normal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import nl.teamdiopside.infinitybuttons.registry.IBParticles;
import org.joml.Vector3f;

public class SparklingButton extends NormalButton {
    // Now with extra carbonation

    public SparklingButton(BlockSetType blockSetType, Properties properties, boolean large, boolean isLever) {
        super(blockSetType, 20, properties, large, isLever);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        super.animateTick(blockState, level, blockPos, randomSource);

        if (randomSource.nextInt(5) != 0) return;

        VoxelShape test = this.getShape(blockState, level, blockPos, null);
        Vec3 center = test.toAabbs().getFirst().getCenter().add(blockPos.getCenter()).subtract(0.5, 0.5, 0.5);

        Direction normalDir = getConnectedDirection(blockState);
        Vector3f step = normalDir.step().mul((float) 2 / 16);
        Vec3 particleCenter = center.add(step.x, step.y, step.z);

        Direction.Axis axis = normalDir.getAxis();

        // TODO: config

        double spread = this.large ? 0.55 : 0.35;

        double x = particleCenter.x + (axis == Direction.Axis.X ? 0 : (randomSource.nextDouble() - 0.5D) * spread);
        double y = particleCenter.y + (axis == Direction.Axis.Y ? 0 : (randomSource.nextDouble() - 0.5D) * spread);
        double z = particleCenter.z + (axis == Direction.Axis.Z ? 0 : (randomSource.nextDouble() - 0.5D) * spread);


        level.addParticle( IBParticles.DIAMOND_SPARKLE.get(), x, y, z, 0, 0, 0);
    }
}
