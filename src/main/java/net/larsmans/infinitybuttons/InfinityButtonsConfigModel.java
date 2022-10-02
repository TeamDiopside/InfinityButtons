package net.larsmans.infinitybuttons;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.Sync;

@Modmenu(modId = "infinitybuttons")
@Config(name = "infinity-buttons-config", wrapperName = "InfinityButtonsConfig")
public class InfinityButtonsConfigModel {

    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public boolean alarmSound = true;

    public boolean tooltips = true;
}
