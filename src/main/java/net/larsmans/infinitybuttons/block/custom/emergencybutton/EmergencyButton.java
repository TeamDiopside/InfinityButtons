package net.larsmans.infinitybuttons.block.custom.emergencybutton;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.larsmans.infinitybuttons.advancement.InfinityButtonsTriggers;
import net.larsmans.infinitybuttons.block.InfinityButtonsUtil;
import net.larsmans.infinitybuttons.block.custom.button.AbstractButton;
import net.larsmans.infinitybuttons.config.AlarmEnum;
import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class EmergencyButton extends AbstractButton {

    private static final VoxelShape STONE_DOWN  = Block.createCuboidShape(4, 0, 4, 12, 1, 12);
    private static final VoxelShape STONE_UP    = Block.createCuboidShape(4, 15, 4, 12, 16, 12);
    private static final VoxelShape STONE_NORTH = Block.createCuboidShape(4, 4, 15, 12, 12, 16);
    private static final VoxelShape STONE_EAST  = Block.createCuboidShape(0, 4, 4, 1, 12, 12);
    private static final VoxelShape STONE_SOUTH = Block.createCuboidShape(4, 4, 0, 12, 12, 1);
    private static final VoxelShape STONE_WEST  = Block.createCuboidShape(15, 4, 4, 16, 12, 12);

    private static final VoxelShape FLOOR_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(5, 1, 5, 11, 5, 11), STONE_DOWN);
    private static final VoxelShape FLOOR_PRESSED_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(5, 1, 5, 11, 3, 11), STONE_DOWN);
    private static final VoxelShape NORTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(5, 5, 11, 11, 11, 15), STONE_NORTH);
    private static final VoxelShape NORTH_PRESSED_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(5, 5, 13, 11, 11, 15), STONE_NORTH);
    private static final VoxelShape EAST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(1, 5, 5, 5, 11, 11), STONE_EAST);
    private static final VoxelShape EAST_PRESSED_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(1, 5, 5, 3, 11, 11), STONE_EAST);
    private static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(5, 5, 1, 11, 11, 5), STONE_SOUTH);
    private static final VoxelShape SOUTH_PRESSED_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(5, 5, 1, 11, 11, 3), STONE_SOUTH);
    private static final VoxelShape WEST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(11, 5, 5, 15, 11, 11), STONE_WEST);
    private static final VoxelShape WEST_PRESSED_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(13, 5, 5, 15, 11, 11), STONE_WEST);
    private static final VoxelShape CEILING_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(5, 11, 5, 11, 15, 11), STONE_UP);
    private static final VoxelShape CEILING_PRESSED_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(5, 13, 5, 11, 15, 11), STONE_UP);

    public EmergencyButton(FabricBlockSettings settings) {
        super(false, settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        boolean bl = state.get(PRESSED);
        switch (state.get(FACE)) {
            case FLOOR -> {
                return bl ? FLOOR_PRESSED_SHAPE : FLOOR_SHAPE;
            }
            case WALL -> {
                switch (direction) {
                    case EAST -> {
                        return bl ? EAST_PRESSED_SHAPE : EAST_SHAPE;
                    }
                    case WEST -> {
                        return bl ? WEST_PRESSED_SHAPE : WEST_SHAPE;
                    }
                    case SOUTH -> {
                        return bl ? SOUTH_PRESSED_SHAPE : SOUTH_SHAPE;
                    }
                }
                return bl ? NORTH_PRESSED_SHAPE : NORTH_SHAPE;
            }
        }
        return bl ? CEILING_PRESSED_SHAPE : CEILING_SHAPE;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(PRESSED)) {
            return ActionResult.CONSUME;
        }
        this.powerOn(state, world, pos);
        this.playClickSound(player, world, pos, true);
        if (InfinityButtonsInit.CONFIG.alarmSoundType() != AlarmEnum.OFF) {
            emergencySound(world, pos, player);
        }
        if (player instanceof ServerPlayerEntity) {
            InfinityButtonsTriggers.EMERGENCY_TRIGGER.trigger((ServerPlayerEntity) player);
        }
        if (!world.isClient && InfinityButtonsInit.CONFIG.alarmVillagerPanic()) {
            List<LivingEntity> villagers = world.getEntitiesByClass(LivingEntity.class, new Box(pos).expand(InfinityButtonsInit.CONFIG.alarmSoundRange()), entity -> entity.getType() == EntityType.VILLAGER);
            for (LivingEntity villager : villagers) {
                if (villager instanceof VillagerEntity villagerEntity) {
                    villagerEntity.getBrain().remember(MemoryModuleType.HEARD_BELL_TIME, world.getTime());
                }
            }
        }
        world.emitGameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
        return ActionResult.success(world.isClient);
    }

    @Override
    public int getPressTicks() {
        return 10;
    }

    @Override
    protected void playClickSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, boolean pressed) {
        world.playSound(pressed ? player : null, pos, this.getClickSound(pressed), SoundCategory.BLOCKS, 1, pressed ? 0.6f : 0.5f);
    }

    @Override
    protected SoundEvent getClickSound(boolean pressed) {
        return SoundEvents.BLOCK_BONE_BLOCK_BREAK;
    }

    public static void emergencySound(World world, BlockPos pos, PlayerEntity player) {
        if (InfinityButtonsInit.CONFIG.alarmSoundType() == AlarmEnum.GLOBAL) {
            InfinityButtonsUtil.playGlobalSound(world, pos, InfinityButtonsSounds.ALARM, SoundCategory.BLOCKS);
        } else {
            world.playSound(player, pos, InfinityButtonsSounds.ALARM, SoundCategory.BLOCKS, (float) InfinityButtonsInit.CONFIG.alarmSoundRange() / 16f, 1);
        }
    }
}



