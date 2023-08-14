package net.larsmans.infinitybuttons.advancement;

import net.minecraft.advancement.criterion.Criteria;

public class InfinityButtonsTriggers {
    public static SafetyTrigger SAFETY_TRIGGER = new SafetyTrigger();
    public static EmergencyTrigger EMERGENCY_TRIGGER = new EmergencyTrigger();
    public static WaxOffTrigger WAX_OFF_TRIGGER = new WaxOffTrigger();

    public static void register() {
        Criteria.register(SAFETY_TRIGGER);
        Criteria.register(EMERGENCY_TRIGGER);
        Criteria.register(WAX_OFF_TRIGGER);
    }
}
