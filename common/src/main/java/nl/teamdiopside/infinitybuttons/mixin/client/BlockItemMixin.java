package nl.teamdiopside.infinitybuttons.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import nl.teamdiopside.infinitybuttons.block.faced6.LetterButton;
import nl.teamdiopside.infinitybuttons.gui.LetterButtonGUI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public class BlockItemMixin {
    @Inject(method = "place", at = @At("RETURN"))
    private void infinitybuttons$openLetterButtonGui(BlockPlaceContext context, CallbackInfoReturnable<InteractionResult> cir) {
        if (!cir.getReturnValue().consumesAction()) return;

        Level level = context.getLevel();
        if (!level.isClientSide) return;

        BlockState state = level.getBlockState(context.getClickedPos());

        // Interacting with Letter Buttons
        if (!(state.getBlock() instanceof LetterButton letterButton)) return;

        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.screen != null) return;
        minecraft.setScreen(new LetterButtonGUI(letterButton, state, level, context.getClickedPos()));
    }
}
