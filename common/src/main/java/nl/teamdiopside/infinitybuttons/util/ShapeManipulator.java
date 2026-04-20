package nl.teamdiopside.infinitybuttons.util;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class ShapeManipulator {
    public static VoxelShape translate(VoxelShape shape, double x, double y, double z) {
        VoxelShape[] result = new VoxelShape[]{Shapes.empty()};

        shape.forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> {
            result[0] = Shapes.or(
                result[0],
                Shapes.create(
                    new AABB(
                        minX + x, minY + y, minZ + z,
                        maxX + x, maxY + y, maxZ + z
                    )
                )
            );
        });

        return result[0].optimize();
    }

    /**
     * Rotates a voxel shape around the X axis first, then around the Y axis. (both global)
     */
    public static VoxelShape rotate(VoxelShape shape, int xQuarterTurns, int yQuarterTurns) {
        VoxelShape[] result = new VoxelShape[]{Shapes.empty()};

        final int xTurns = Math.floorMod(xQuarterTurns, 4);
        final int yTurns = Math.floorMod(yQuarterTurns, 4);

        shape.forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> {
            double[][] corners = new double[][]{
                    {minX, minY, minZ},
                    {minX, minY, maxZ},
                    {minX, maxY, minZ},
                    {minX, maxY, maxZ},
                    {maxX, minY, minZ},
                    {maxX, minY, maxZ},
                    {maxX, maxY, minZ},
                    {maxX, maxY, maxZ}
            };

            for (double[] p : corners) {
                double x = p[0] - 0.5;
                double y = p[1] - 0.5;
                double z = p[2] - 0.5;

                for (int i = 0; i < xTurns; i++) {
                    double newY = -z;
                    double newZ = y;
                    y = newY;
                    z = newZ;
                }

                for (int i = 0; i < yTurns; i++) {
                    double newX = -z;
                    double newZ = x;
                    x = newX;
                    z = newZ;
                }

                p[0] = x + 0.5;
                p[1] = y + 0.5;
                p[2] = z + 0.5;
            }

            double rotatedMinX = Double.POSITIVE_INFINITY;
            double rotatedMinY = Double.POSITIVE_INFINITY;
            double rotatedMinZ = Double.POSITIVE_INFINITY;
            double rotatedMaxX = Double.NEGATIVE_INFINITY;
            double rotatedMaxY = Double.NEGATIVE_INFINITY;
            double rotatedMaxZ = Double.NEGATIVE_INFINITY;

            for (double[] p : corners) {
                rotatedMinX = Math.min(rotatedMinX, p[0]);
                rotatedMinY = Math.min(rotatedMinY, p[1]);
                rotatedMinZ = Math.min(rotatedMinZ, p[2]);
                rotatedMaxX = Math.max(rotatedMaxX, p[0]);
                rotatedMaxY = Math.max(rotatedMaxY, p[1]);
                rotatedMaxZ = Math.max(rotatedMaxZ, p[2]);
            }

            result[0] = Shapes.or(
                    result[0],
                    Shapes.create(new AABB(rotatedMinX, rotatedMinY, rotatedMinZ, rotatedMaxX, rotatedMaxY, rotatedMaxZ))
            );
        });

        return result[0].optimize();
    }

    public static VoxelShape rotateY(VoxelShape shape, int quarterTurns) {
        return rotate(shape, 0, quarterTurns);
    }

    public static VoxelShape rotateX(VoxelShape shape, int quarterTurns) {
        return rotate(shape, quarterTurns, 0);
    }
}