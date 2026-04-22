package nl.teamdiopside.infinitybuttons.block.emergency;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
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
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EmergencyButton extends ButtonFaced6 {
    private static final VoxelShape BASE = Block.box(4, 4, 15, 12, 12, 16);
    private static final VoxelShape SHAPE_UNPRESSED = Shapes.or(
            Block.box(5, 5, 11, 11, 11, 15), BASE);
    private static final VoxelShape SHAPE_PRESSED = Shapes.or(
            Block.box(5, 5, 13, 11, 11, 15), BASE);

    public static final int PRESS_TICKS = 10;

    public EmergencyButton(BlockSetType blockSetType, Properties properties, VoxelShape shapePressed, VoxelShape shapeUnpressed) {
        super(blockSetType, PRESS_TICKS, properties, shapePressed, shapeUnpressed);
    }

    public EmergencyButton(Properties properties) {
        super(BlockSetType.STONE, PRESS_TICKS, properties, SHAPE_PRESSED, SHAPE_UNPRESSED);
    }

    public static void emergencySound(Level level, BlockPos pos) {
        // TODO: WEEEEEEOOEOEOOOOOOOH !!!!!!
    }

    protected static Direction getDirection(BlockState state) {
        return switch (state.getValue(FACE)) {
            case CEILING -> Direction.DOWN;
            case FLOOR -> Direction.UP;
            default -> state.getValue(FACING);
        };
    }

    @Override
    public void press(BlockState state, Level level, BlockPos pos, @Nullable Player player) {
        super.press(state, level, pos, player);

        emergencySound(level, pos);
        if (player instanceof ServerPlayer) {
            // TODO: Advancement trigger - InfinityButtonsTriggers.EMERGENCY_TRIGGER.trigger((ServerPlayer) player);
        }
        if (!level.isClientSide) { // TODO: Config - InfinityButtonsInit.CONFIG.alarmVillagerPanic()
            List<LivingEntity> villagers = new ArrayList<>();
            if (true) { // TODO: Config - InfinityButtonsInit.CONFIG.alarmSoundType() == AlarmEnum.GLOBAL
                villagers = new ArrayList<>();
                List<LivingEntity> villagersDup = level.getEntitiesOfClass(LivingEntity.class, new AABB(pos).inflate(512), entity -> entity.getType() == EntityType.VILLAGER);
                for (Player player1 : level.players()) {
                    villagersDup.addAll(level.getEntitiesOfClass(LivingEntity.class, new AABB(player1.blockPosition()).inflate(512), entity -> entity.getType() == EntityType.VILLAGER));
                }
                for (LivingEntity villager : villagersDup) {
                    if (!villagers.contains(villager)) {
                        villagers.add(villager);
                    }
                }
            }
            if (true) { // TODO: Config - InfinityButtonsInit.CONFIG.alarmSoundType() == AlarmEnum.RANGE
                villagers = level.getEntitiesOfClass(
                        LivingEntity.class,
                        new AABB(pos).inflate(16), entity -> entity.getType() == EntityType.VILLAGER // TODO: Config - InfinityButtonsInit.CONFIG.alarmSoundRange()
                );
            }
            for (LivingEntity villagerEntity : villagers) {
                if (villagerEntity instanceof Villager villager) {
                    villager.getBrain().setMemory(MemoryModuleType.HEARD_BELL_TIME, level.dayTime());
                }
            }
        }
    }

    @Override
    protected SoundEvent getSound(boolean press) {
        return SoundEvents.BONE_BLOCK_BREAK;
    }
}
