package nl.teamdiopside.infinitybuttons.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.advancements.CriterionTrigger;
import nl.teamdiopside.diopside.registry.TriggerEntryBuilder;
import nl.teamdiopside.infinitybuttons.advancement.EmergencyTrigger;
import nl.teamdiopside.infinitybuttons.advancement.SafetyTrigger;
import nl.teamdiopside.infinitybuttons.advancement.WaxOffTrigger;

import java.util.function.Supplier;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.LOGGER;
import static nl.teamdiopside.infinitybuttons.InfinityButtons.getResource;

public class IBAdvancementTriggers {

    public static RegistrySupplier<SafetyTrigger> SAFETY_TRIGGER = registerTrigger("safety_button_head", SafetyTrigger::new);
    public static RegistrySupplier<EmergencyTrigger> EMERGENCY_TRIGGER = registerTrigger("emergency_button_press", EmergencyTrigger::new);
    public static RegistrySupplier<WaxOffTrigger> WAX_OFF_TRIGGER = registerTrigger("wax_off", WaxOffTrigger::new);

    public static <T extends CriterionTrigger<?>> RegistrySupplier<T> registerTrigger(String name, Supplier<T> supplier) {
        return TriggerEntryBuilder.<T>builder().register(getResource(name), supplier);
    }

    public static void register() {
        LOGGER.info("Registering Advancement Triggers for Infinity Buttons");
    }
}
