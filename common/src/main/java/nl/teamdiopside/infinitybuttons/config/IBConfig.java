package nl.teamdiopside.infinitybuttons.config;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.minecraft.resources.ResourceLocation;

import static dev.isxander.yacl3.platform.YACLPlatform.getConfigDir;
import static nl.teamdiopside.infinitybuttons.InfinityButtons.LOGGER;
import static nl.teamdiopside.infinitybuttons.InfinityButtons.MOD_ID;

public class IBConfig {
    public static ConfigClassHandler<IBConfig> HANDLER = ConfigClassHandler.createBuilder(IBConfig.class)
            .id(ResourceLocation.fromNamespaceAndPath(MOD_ID, "config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(getConfigDir().resolve("infinitybuttons.json5"))
                    .setJson5(true)
                    .build())
            .build();

    public enum AlarmSoundType {
        RANGE,
        GLOBAL;

        AlarmSoundType() {}
    }

    @SerialEntry(comment = "Client Side")
    public AlarmSoundType alarmSoundType = AlarmSoundType.GLOBAL;

    @SerialEntry
    public int alarmSoundRange = 6;

    @SerialEntry(comment = "Client Side")
    public boolean muteAlarmSound = false;

    @SerialEntry(comment = "Server Side")
    public boolean alarmVillagerPanic = true;

    @SerialEntry(comment = "Client side")
    public boolean diamondParticles = true;

    @SerialEntry(comment = "Server Side")
    public boolean forceJadeCamouflage = true;

    public static AlarmSoundType alarmSoundType() {
        return HANDLER.instance().alarmSoundType;
    }

    public static int alarmSoundRange() {
        return HANDLER.instance().alarmSoundRange;
    }

    public static boolean muteAlarmSound() {
        return HANDLER.instance().muteAlarmSound;
    }

    public static boolean alarmVillagerPanic() {
        return HANDLER.instance().alarmVillagerPanic;
    }

    public static boolean diamondParticles() {
        return HANDLER.instance().diamondParticles;
    }

    public static boolean forceJadeCamouflage() {
        return HANDLER.instance().forceJadeCamouflage;
    }

    public static void load() {
        HANDLER.load();
        LOGGER.info("Loading Config options for Infinity Buttons");
    }
}