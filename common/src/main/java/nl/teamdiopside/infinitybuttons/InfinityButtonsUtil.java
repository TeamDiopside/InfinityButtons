package nl.teamdiopside.infinitybuttons;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;

public class InfinityButtonsUtil {

    public static ResourceLocation getResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(InfinityButtons.MOD_ID, path);
    }

    public static InteractionResult sided(boolean isClient) {
        return isClient ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
    }
}
