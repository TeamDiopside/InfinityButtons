package nl.teamdiopside.infinitybuttons.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.EnumControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerFieldControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import nl.teamdiopside.infinitybuttons.IBNetworking;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.MOD_ID;
import static nl.teamdiopside.infinitybuttons.config.IBConfig.HANDLER;

public class IBConfigScreen {

    public static Screen generateScreen(Screen parentScreen) {
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

    private static ConfigCategory.Builder tooltips(ConfigCategory.Builder configBuilder) {

        OptionGroup.Builder optionBuilder = OptionGroup.createBuilder()
                .name(createName("config"))
                .description(createDescription("config"));

        var muteAlarmSoundOption = createBooleanConfigOption("alarm.mute", false,
                () -> HANDLER.instance().muteAlarmSound, (newVal) -> HANDLER.instance().muteAlarmSound = newVal);
        var alarmVillagerPanicOption = createBooleanConfigOption("alarm.villager_panic", true,
                () -> HANDLER.instance().alarmVillagerPanic, (newVal) -> HANDLER.instance().alarmVillagerPanic = newVal);
        var diamondParticlesOption = createBooleanConfigOption("diamond_particles", false,
                () -> HANDLER.instance().diamondParticles, (newVal) -> HANDLER.instance().diamondParticles = newVal);
        var forceJadeCamouflageOption = createBooleanConfigOption("force_jade_camouflage", false,
                () -> HANDLER.instance().forceJadeCamouflage, (newVal) -> HANDLER.instance().forceJadeCamouflage = newVal);

        var alarmTypeOption = Option.<IBConfig.AlarmSoundType>createBuilder()
                .name(createName("alarm_type"))
                .description(createDescription("alarm_type"))

                .binding(IBConfig.AlarmSoundType.GLOBAL, () -> HANDLER.instance().alarmSoundType, newVal -> HANDLER.instance().alarmSoundType = newVal)
                .controller(o -> EnumControllerBuilder.create(o).enumClass(IBConfig.AlarmSoundType.class));

        var alarmRangeOption = Option.<Integer>createBuilder()
                .name(createName("alarm_range"))
                .description(createDescription("alarm_range"))

                .binding(6, () -> HANDLER.instance().alarmSoundRange, newVal -> HANDLER.instance().alarmSoundRange = newVal)
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

    private static Component createName(String key) {
        return Component.translatable("config." + MOD_ID + "." + key + ".name");
    }

    private static OptionDescription createDescription(String key) {
        return OptionDescription.of(
                Component.translatable("config." + MOD_ID + "." + key + ".description")
        );
    }

    private static Option.Builder<Boolean> createBooleanConfigOption(String key, boolean defaultVal,
                                                              Supplier<Boolean> getter, Consumer<Boolean> setter) {
        return Option.<Boolean>createBuilder()
                .name(createName(key))
                .description(createDescription(key))
                .binding(defaultVal, getter, setter)
                .controller(TickBoxControllerBuilder::create);
    }
}
