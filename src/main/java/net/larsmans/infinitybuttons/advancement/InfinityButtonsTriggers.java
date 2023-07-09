package net.larsmans.infinitybuttons.advancement;

import net.minecraft.advancement.criterion.Criteria;

public class InfinityButtonsTriggers {
    public static SafetyTrigger SAFETY_TRIGGER = new SafetyTrigger();
    public static EmergencyTrigger EMERGENCY_TRIGGER = new EmergencyTrigger();

    public static void register() {
        Criteria.register(SAFETY_TRIGGER);
        Criteria.register(EMERGENCY_TRIGGER);
    }
}
