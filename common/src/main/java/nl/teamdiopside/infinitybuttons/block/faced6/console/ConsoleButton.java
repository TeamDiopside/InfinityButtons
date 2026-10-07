package nl.teamdiopside.infinitybuttons.block.faced6.console;

import dev.architectury.registry.menu.MenuRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import nl.teamdiopside.infinitybuttons.block.ButtonFaced6;
import nl.teamdiopside.infinitybuttons.gui.ConsoleButtonMenu;
import nl.teamdiopside.infinitybuttons.registry.IBBlockEntities;
import nl.teamdiopside.infinitybuttons.registry.IBSounds;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConsoleButton extends ButtonFaced6 implements EntityBlock {

    public static BooleanProperty BROKEN = BooleanProperty.create("broken");
    protected ConsoleButtonType type;

    public ConsoleButton(Properties properties, ConsoleButtonType type) {
        super(BlockSetType.IRON, properties, type.shape, type.shape);
        this.type = type;
        this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, false).setValue(BROKEN, false));
    }

    public ConsoleButtonType getType() {
        return type;
    }

    @Override
    protected int getPressTicks(Level level, BlockPos pos) {
        ConsoleButtonBlockEntity entity = (ConsoleButtonBlockEntity) level.getBlockEntity(pos);

        if (entity != null) return entity.getPressTicks();

        return 50;
    }

    @Override
    protected @NotNull SoundEvent getSound(boolean press) {
        return press ? IBSounds.CONSOLE_BEEP.get() : IBSounds.CONSOLE_BOOP.get();
    }

    @Override
    protected @NotNull SoundType getSoundType(BlockState state) {
        return SoundType.METAL;
    }

    @Override
    protected boolean isLever(Level level, BlockPos pos) {
        ConsoleButtonBlockEntity entity = (ConsoleButtonBlockEntity) level.getBlockEntity(pos);
        if (entity != null) return entity.isLever;

        return super.isLever(level, pos);
    }

    protected void breakButton(BlockState state, Level level, BlockPos pos) {
        level.setBlockAndUpdate(pos, state.setValue(BROKEN, true).setValue(POWERED, false));
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult blockHitResult) {
        if (state.getValue(BROKEN)) {
            level.playSound(null, pos, SoundEvents.SPYGLASS_USE, SoundSource.BLOCKS);
            return InteractionResult.SUCCESS;
        }
        ConsoleButtonBlockEntity entity = (ConsoleButtonBlockEntity) level.getBlockEntity(pos);

        if (entity != null && !entity.validatePlayerItem(player)) {
            player.displayClientMessage(Component.translatable("infinitybuttons.actionbar.console_button"), true);
            level.playSound(null, pos, IBSounds.CONSOLE_ERROR.get(), SoundSource.BLOCKS);
            return InteractionResult.CONSUME;
        }

        InteractionResult useResult = super.useWithoutItem(state, level, pos, player, blockHitResult);
        if (useResult == InteractionResult.SUCCESS && entity != null) {
            entity.use();
        }
        return useResult;
    }

    @Override
    public void unpress(BlockState state, Level level, BlockPos pos, @Nullable Player player) {
        super.unpress(state, level, pos, player);

        ConsoleButtonBlockEntity entity = (ConsoleButtonBlockEntity) level.getBlockEntity(pos);
        if (entity == null) return;
        if (entity.getDurability() == 0) {
            level.playSound(null, pos, SoundEvents.ITEM_BREAK, SoundSource.BLOCKS);
            level.playSound(null, pos, SoundEvents.GLASS_BREAK, SoundSource.BLOCKS);
            this.breakButton(state, level, pos);
        }
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);

        if (level.isClientSide || !(placer instanceof ServerPlayer serverPlayer)) return;

        MenuRegistry.openExtendedMenu(serverPlayer, new SimpleMenuProvider(
                (containerId, inv, p) -> new ConsoleButtonMenu(containerId, inv, pos),
                Component.translatable("block.infinitybuttons.console_button")
        ), buf -> buf.writeBlockPos(pos));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return IBBlockEntities.CONSOLE_BUTTON.get().create(pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BROKEN);
    }
}
