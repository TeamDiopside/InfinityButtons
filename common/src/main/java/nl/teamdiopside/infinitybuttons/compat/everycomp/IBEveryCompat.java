package nl.teamdiopside.infinitybuttons.compat.everycomp;

import net.mehvahdjukaar.every_compat.api.EveryCompatAPI;
import nl.teamdiopside.infinitybuttons.InfinityButtons;

public class IBEveryCompat {
    public static void loadModules() {
        EveryCompatAPI.registerOptionalModule(InfinityButtons.MOD_ID, () -> IBCompatModule.class);
    }
}
