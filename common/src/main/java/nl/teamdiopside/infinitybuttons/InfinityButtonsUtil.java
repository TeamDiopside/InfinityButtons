package nl.teamdiopside.infinitybuttons;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;

public class InfinityButtonsUtil {

    public static ResourceLocation getResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(InfinityButtons.MOD_ID, path);
    }
}
