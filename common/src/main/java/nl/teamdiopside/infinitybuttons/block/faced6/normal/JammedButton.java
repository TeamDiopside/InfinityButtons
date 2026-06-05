package nl.teamdiopside.infinitybuttons.block.faced6.normal;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class JammedButton extends NormalButton {
    public JammedButton(BlockSetType blockSetType, Properties properties, boolean large, boolean isLever) {
        super(blockSetType, 20, properties, large, isLever, SoundType.NETHERITE_BLOCK);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {

        player.displayClientMessage(Component.translatable("infinitybuttons.actionbar.jammed_button"), true);

        return InteractionResult.CONSUME;
    }
}
