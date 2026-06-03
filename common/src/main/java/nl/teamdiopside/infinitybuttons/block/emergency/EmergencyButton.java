package nl.teamdiopside.infinitybuttons.block.emergency;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import nl.teamdiopside.infinitybuttons.block.ButtonFaced6;
import nl.teamdiopside.infinitybuttons.registry.IBSounds;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public class EmergencyButton extends ButtonFaced6 {
    private static final VoxelShape BASE = Block.box(4, 4, 15, 12, 12, 16);
    private static final VoxelShape SHAPE_UNPRESSED = Shapes.or(
            Block.box(5, 5, 11, 11, 11, 15), BASE);
    private static final VoxelShape SHAPE_PRESSED = Shapes.or(
            Block.box(5, 5, 13, 11, 11, 15), BASE);

    public static final int PRESS_TICKS = 10;

    public EmergencyButton(BlockSetType blockSetType, Properties properties, VoxelShape shapePressed, VoxelShape shapeUnpressed) {
        super(blockSetType, PRESS_TICKS, properties, shapePressed, shapeUnpressed, false);
    }

    public EmergencyButton(Properties properties) {
        super(BlockSetType.STONE, PRESS_TICKS, properties, SHAPE_PRESSED, SHAPE_UNPRESSED, false);
    }

    public static void emergencySound(Level level, BlockPos pos) {
        if (!level.isClientSide && level.getServer() != null) {
            // Weewooh all players
            for (var player : level.getServer().getPlayerList().getPlayers()) {
                player.connection.send(new ClientboundSoundPacket(IBSounds.ALARM, SoundSource.BLOCKS,
                        pos.getX(), pos.getY(), pos.getZ(), 1.0F, 1.0F, level.getRandom().nextLong()
                ));
            } // TODO: different config options
        }
    }

    public static void scareVillagers(ServerLevel level, BlockPos pos) {

        Set<LivingEntity> villagersToScare = new HashSet<>();

        // TODO: IBConfig.alarmSoundType() == AlarmEnum.GLOBAL
        if (true) {
            for (Player player : level.players()) {
                villagersToScare.addAll(level.getEntitiesOfClass(
                        LivingEntity.class,
                        new AABB(player.blockPosition()).inflate(512),
                        entity -> entity.getType() == EntityType.VILLAGER
                ));
            }
        }

        double alarm_range = 16; // TODO: IBConfig.alarmSoundRange()

        // TODO: IBConfig.alarmSoundType() == AlarmEnum.RANGE
        if (true) {
            villagersToScare.addAll(level.getEntitiesOfClass(
                    LivingEntity.class,
                    new AABB(pos).inflate(alarm_range), entity -> entity.getType() == EntityType.VILLAGER
            ));
        }

        // Scare them!!!
        for (LivingEntity villagerEntity : villagersToScare) {
            if (villagerEntity instanceof Villager villager) {
                villager.getBrain().setMemory(MemoryModuleType.HEARD_BELL_TIME, level.dayTime());
            }
        }
    }

    protected static Direction getDirection(BlockState state) {
        return switch (state.getValue(FACE)) {
            case CEILING -> Direction.DOWN;
            case FLOOR -> Direction.UP;
            default -> state.getValue(FACING);
        };
    }

    @Override
    protected int getPressTicks() {
        return PRESS_TICKS;
    }

    @Override
    public void press(BlockState state, Level level, BlockPos pos, @Nullable Player player) {
        super.press(state, level, pos, player);

        // TODO: IBConfig.muteAlarmSound()
        if (true) {
            emergencySound(level, pos);
        }

        // TODO: IBConfig.alarmVillagerPanic()
        if (level instanceof ServerLevel) {
            scareVillagers((ServerLevel) level, pos);
        }
    }

    @Override
    protected SoundEvent getSound(boolean press) {
        return SoundEvents.BONE_BLOCK_BREAK;
    }
}
