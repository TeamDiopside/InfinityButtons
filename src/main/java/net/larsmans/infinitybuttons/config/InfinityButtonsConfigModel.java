package net.larsmans.infinitybuttons.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.RangeConstraint;

@Modmenu(modId = "infinitybuttons")
@Config(name = "infinity-buttons-config", wrapperName = "InfinityButtonsConfig")
public class InfinityButtonsConfigModel {

    public AlarmEnum alarmSoundType = AlarmEnum.RANGE;

    @RangeConstraint(min = 16, max = 512)
    public int alarmSoundRange = 64;

    public boolean alarmVillagerPanic = true;

    public boolean tooltips = true;

    public boolean diamondParticles = true;
}
