package nl.teamdiopside.infinitybuttons.block.normal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class ArrowOnlyButton extends NormalButton {
    public ArrowOnlyButton(BlockSetType blockSetType, Properties properties, boolean large, boolean isLever) {
        super(blockSetType, 20, properties, large, isLever);
    }

    @Override
    public boolean infinityButtons$activatedByArrows() {
        return true;
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        return InteractionResult.CONSUME;
    }
}
