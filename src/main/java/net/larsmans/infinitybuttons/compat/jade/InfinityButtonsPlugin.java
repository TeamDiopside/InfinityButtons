package net.larsmans.infinitybuttons.compat.jade;

import net.larsmans.infinitybuttons.InfinityButtonsClientInit;
import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.larsmans.infinitybuttons.block.custom.HoglinMountButton;
import net.larsmans.infinitybuttons.block.custom.LanternButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.AbstractSecretButton;
import net.larsmans.infinitybuttons.block.custom.torch.RedstoneTorchButton;
import net.larsmans.infinitybuttons.block.custom.torch.TorchButton;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;
import snownee.jade.api.config.IWailaConfig;

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
    static final Identifier CONFIG_HIDE_LANTERN_BUTTONS = new Identifier(InfinityButtonsInit.MOD_ID, "hide_lantern_buttons");

    private static Block HOGLIN_MOUNT = Registry.BLOCK.get(new Identifier("nethersdelight", "hoglin_mount"));

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.addConfig(CONFIG_HIDE_SECRET_BUTTONS, true);
        registration.addConfig(CONFIG_HIDE_TORCH_BUTTONS, true);
        registration.addConfig(CONFIG_HIDE_LANTERN_BUTTONS, true);
        registration.addRayTraceCallback((hitResult, accessor, originalAccessor) -> {
            if (accessor instanceof BlockAccessor blockAccessor) {
                if (hidden(CONFIG_HIDE_SECRET_BUTTONS) && blockAccessor.getBlock() instanceof AbstractSecretButton secretButton) {
                    return registration.blockAccessor().from(blockAccessor).blockState(secretButton.jadeBlock.getDefaultState()).build();
                }
                if (hidden(CONFIG_HIDE_SECRET_BUTTONS) && blockAccessor.getBlock() instanceof HoglinMountButton) {
                    return registration.blockAccessor().from(blockAccessor).blockState(HOGLIN_MOUNT.getDefaultState()).build();
                }
                if (hidden(CONFIG_HIDE_TORCH_BUTTONS) && blockAccessor.getBlock() instanceof TorchButton torchButton) {
                    return registration.blockAccessor().from(blockAccessor).blockState(torchButton.jadeBlock.getDefaultState()).build();
                }
                if (hidden(CONFIG_HIDE_TORCH_BUTTONS) && blockAccessor.getBlock() instanceof RedstoneTorchButton redstoneTorchButton) {
                    return registration.blockAccessor().from(blockAccessor).blockState(redstoneTorchButton.jadeBlock.getDefaultState()).build();
                }
                if (hidden(CONFIG_HIDE_LANTERN_BUTTONS) && blockAccessor.getBlock() instanceof LanternButton lanternButton) {
                    return registration.blockAccessor().from(blockAccessor).blockState(lanternButton.jadeBlock.getDefaultState()).build();
                }
            }
            return accessor;
        });
    }
}
