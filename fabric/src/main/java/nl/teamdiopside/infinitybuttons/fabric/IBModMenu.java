package nl.teamdiopside.infinitybuttons.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import nl.teamdiopside.infinitybuttons.registry.IBConfig;

public class IBModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parentScreen -> IBConfig.HANDLER.instance().generateScreen(parentScreen);
    }
}
