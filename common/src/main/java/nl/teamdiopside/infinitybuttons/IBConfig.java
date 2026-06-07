package nl.teamdiopside.infinitybuttons;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.EnumControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerFieldControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;
import java.util.function.Supplier;

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

    private ConfigCategory.Builder tooltips(ConfigCategory.Builder configBuilder) {

        OptionGroup.Builder optionBuilder = OptionGroup.createBuilder()
                .name(createName("config"))
                .description(createDescription("config"));

        var muteAlarmSoundOption = createBooleanConfigOption("alarm.mute", false,
                () -> this.muteAlarmSound, (newVal) -> this.muteAlarmSound = newVal);
        var alarmVillagerPanicOption = createBooleanConfigOption("alarm.villager_panic", true,
                () -> this.alarmVillagerPanic, (newVal) -> this.alarmVillagerPanic = newVal);
        var diamondParticlesOption = createBooleanConfigOption("diamond_particles", false,
                () -> this.diamondParticles, (newVal) -> this.diamondParticles = newVal);
        var forceJadeCamouflageOption = createBooleanConfigOption("force_jade_camouflage", false,
                () -> this.forceJadeCamouflage, (newVal) -> this.forceJadeCamouflage = newVal);

        var alarmTypeOption = Option.<AlarmSoundType>createBuilder()
                        .name(createName("alarm_type"))
                        .description(createDescription("alarm_type"))

                        .binding(AlarmSoundType.GLOBAL, () -> this.alarmSoundType, newVal -> this.alarmSoundType = newVal)
                        .controller(o -> EnumControllerBuilder.create(o).enumClass(AlarmSoundType.class));

        var alarmRangeOption = Option.<Integer>createBuilder()
                .name(createName("alarm_range"))
                .description(createDescription("alarm_range"))

                .binding(6, () -> this.alarmSoundRange, newVal -> this.alarmSoundRange = newVal)
                .controller(o -> IntegerFieldControllerBuilder.create(o).min(1).max(32));

        optionBuilder.option(muteAlarmSoundOption.build());

        optionBuilder.option(alarmVillagerPanicOption.build());
        optionBuilder.option(alarmTypeOption.build());
        optionBuilder.option(alarmRangeOption.build());

        optionBuilder.option(diamondParticlesOption.build());
        optionBuilder.option(forceJadeCamouflageOption.build());

        configBuilder.group(optionBuilder.build());
        return configBuilder;
    }

    private Component createName(String key) {
        return Component.translatable("config." + MOD_ID + "." + key + ".name");
    }

    private OptionDescription createDescription(String key) {
        return OptionDescription.of(
                Component.translatable("config." + MOD_ID + "." + key + ".description")
        );
    }

    private Option.Builder<Boolean> createBooleanConfigOption(String key, boolean defaultVal,
                                           Supplier<Boolean> getter, Consumer<Boolean> setter) {
        return Option.<Boolean>createBuilder()
                .name(createName(key))
                .description(createDescription(key))
                .binding(defaultVal, getter, setter)
                .controller(TickBoxControllerBuilder::create);
    }

    public Screen generateScreen(Screen parentScreen) {
        return YetAnotherConfigLib.createBuilder()
                .title(Component.translatable("config.infinitybuttons.title"))
                .category(tooltips(ConfigCategory.createBuilder())
                        .name(Component.translatable("config.infinitybuttons.title"))
                        .build()
                )
                .save(() -> {
                    HANDLER.save();

                    // Tell all clients about this hip new Force Jade Camouflage thing
                    Minecraft mc = Minecraft.getInstance();
                    if (mc.getSingleplayerServer() != null) {
                        IBNetworking.sendJadeSyncToAll(mc.getSingleplayerServer());
                    }
                })
                .build()
                .generateScreen(parentScreen);
    }

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