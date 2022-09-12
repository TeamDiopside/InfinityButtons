package net.larsmans.infinitybuttons;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;

@Modmenu(modId = "infinitybuttons")
@Config(name = "infinity-buttons-config", wrapperName = "InfinityButtonsConfig")
public class InfinityButtonsConfigModel {
    public boolean alarmSound = true;
}
