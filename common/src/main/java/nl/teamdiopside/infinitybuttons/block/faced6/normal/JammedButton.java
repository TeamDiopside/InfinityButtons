package nl.teamdiopside.infinitybuttons.block.faced6.normal;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MaceItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class JammedButton extends NormalButton {
    public JammedButton(BlockSetType blockSetType, Properties properties, boolean large) {
        super(blockSetType, 20, properties, large, false, SoundType.NETHERITE_BLOCK);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult blockHitResult) {
        player.displayClientMessage(Component.translatable("actionbar.infinitybuttons.jammed_button"), true);
        return InteractionResult.CONSUME;
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
        ItemStack inHand = player.getItemInHand(player.getUsedItemHand());
        if (inHand.getItem() instanceof MaceItem) {
            this.press(state, level, pos, player);
            level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.MACE_SMASH_AIR, player.getSoundSource(), 1.0F, 1.0F);
            spawnDestroyParticles(level, player, pos, state);
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}
