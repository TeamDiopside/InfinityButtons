package nl.teamdiopside.infinitybuttons.compat.jade;

import net.minecraft.resources.ResourceLocation;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButton;
import nl.teamdiopside.infinitybuttons.block.faced4.TorchButton;
import nl.teamdiopside.infinitybuttons.block.simple.LanternButton;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;
import snownee.jade.api.config.IWailaConfig;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.MOD_ID;

@WailaPlugin
public class JadeIntegration implements IWailaPlugin {

    static final ResourceLocation CONFIG_HIDE_SECRET_BUTTONS = ResourceLocation.fromNamespaceAndPath(MOD_ID, "hide_secret_buttons");
    static final ResourceLocation CONFIG_HIDE_TORCH_BUTTONS = ResourceLocation.fromNamespaceAndPath(MOD_ID, "hide_torch_buttons");
    static final ResourceLocation CONFIG_HIDE_LANTERN_BUTTONS = ResourceLocation.fromNamespaceAndPath(MOD_ID, "hide_lantern_buttons");

    private static boolean serverMandatesCamouflage = false;

    public static void setServerMandatesCamouflage(boolean value) {
        serverMandatesCamouflage = value;
    }

    private boolean shouldHide(ResourceLocation config) {
        if (serverMandatesCamouflage) return true;

        return IWailaConfig.get().getPlugin().get(config);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        IWailaPlugin.super.registerClient(registration);

        registration.addConfig(CONFIG_HIDE_SECRET_BUTTONS, true);
        registration.addConfig(CONFIG_HIDE_TORCH_BUTTONS, true);
        registration.addConfig(CONFIG_HIDE_LANTERN_BUTTONS, true);

        registration.addRayTraceCallback(((hitResult, accessor, originalAccessor) -> {
            if (!(accessor instanceof BlockAccessor blockAccessor)) return accessor;

            // Check if this block is camouflaged
            if (!(blockAccessor.getBlock() instanceof JadeCamouflaged camouflaged)) return accessor;

            return switch (camouflaged) {
                case SecretButton  block when !shouldHide(CONFIG_HIDE_SECRET_BUTTONS) -> accessor;
                case TorchButton   block when !shouldHide(CONFIG_HIDE_TORCH_BUTTONS) -> accessor;
                case LanternButton block when !shouldHide(CONFIG_HIDE_LANTERN_BUTTONS) -> accessor;

                default -> registration.blockAccessor().from(blockAccessor).blockState(
                        camouflaged.getCamouflage().defaultBlockState()
                ).build();
            };
        }));
    }
}
