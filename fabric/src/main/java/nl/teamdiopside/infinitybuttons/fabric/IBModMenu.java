package nl.teamdiopside.infinitybuttons.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import nl.teamdiopside.infinitybuttons.config.IBConfigScreen;

public class IBModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return IBConfigScreen::generateScreen;
    }
}
