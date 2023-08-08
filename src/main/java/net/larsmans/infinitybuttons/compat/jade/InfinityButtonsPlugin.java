package net.larsmans.infinitybuttons.compat.jade;

import net.larsmans.infinitybuttons.InfinityButtonsClientInit;
import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.larsmans.infinitybuttons.block.custom.secretbutton.AbstractSecretButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.BookshelfSecretButton;
import net.larsmans.infinitybuttons.block.custom.torch.RedstoneTorchButton;
import net.larsmans.infinitybuttons.block.custom.torch.TorchButton;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;
import snownee.jade.api.config.IWailaConfig;
import snownee.jade.api.platform.CustomEnchantPower;

@WailaPlugin
public class InfinityButtonsPlugin implements IWailaPlugin {

    private boolean hidden(Identifier config) {
        if (InfinityButtonsClientInit.getForceHidden())
            return true;
        else
            return IWailaConfig.get().getPlugin().get(config);
    }

    static final Identifier CONFIG_HIDE_SECRET_BUTTONS = new Identifier(InfinityButtonsInit.MOD_ID, "hide_secret_buttons");
    static final Identifier CONFIG_HIDE_TORCH_BUTTONS = new Identifier(InfinityButtonsInit.MOD_ID, "hide_torch_buttons");

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.addConfig(CONFIG_HIDE_SECRET_BUTTONS, true);
        registration.addConfig(CONFIG_HIDE_TORCH_BUTTONS, true);
        registration.markAsClientFeature(CONFIG_HIDE_SECRET_BUTTONS);
        registration.markAsClientFeature(CONFIG_HIDE_TORCH_BUTTONS);

        for (Block block : Registries.BLOCK)
            if (block instanceof BookshelfSecretButton)
                registration.registerCustomEnchantPower(block, (blockState, world, blockPos) -> 1);

        registration.addRayTraceCallback((hitResult, accessor, originalAccessor) -> {
            if (accessor instanceof BlockAccessor blockAccessor) {
                if (hidden(CONFIG_HIDE_SECRET_BUTTONS) && blockAccessor.getBlock() instanceof AbstractSecretButton secretButton) {
                    return registration.blockAccessor().from(blockAccessor).blockState(secretButton.jadeBlock.getDefaultState()).build();
                }
                if (hidden(CONFIG_HIDE_TORCH_BUTTONS) && blockAccessor.getBlock() instanceof TorchButton torchButton) {
                    return registration.blockAccessor().from(blockAccessor).blockState(torchButton.jadeBlock.getDefaultState()).build();
                }
                if (hidden(CONFIG_HIDE_TORCH_BUTTONS) && blockAccessor.getBlock() instanceof RedstoneTorchButton redstoneTorchButton) {
                    return registration.blockAccessor().from(blockAccessor).blockState(redstoneTorchButton.jadeBlock.getDefaultState()).build();
                }
            }
            return accessor;
        });
    }
}
