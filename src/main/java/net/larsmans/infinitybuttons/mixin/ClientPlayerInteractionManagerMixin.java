package net.larsmans.infinitybuttons.mixin;

import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public abstract class ClientPlayerInteractionManagerMixin {

    @Shadow @Final private MinecraftClient client;

    @Inject(method = "interactBlockInternal", at = @At("HEAD"), cancellable = true)
    private void interactBlockInternal(ClientPlayerEntity player, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        World world = this.client.world;
        BlockState state = world.getBlockState(hitResult.getBlockPos());
        Block block = state.getBlock();
        if (InfinityButtonsUtil.crouchClickOverrides(block)) {
            cir.setReturnValue(block.onUse(state, world, hitResult.getBlockPos(), player, hand, hitResult));
        }
    }
}
