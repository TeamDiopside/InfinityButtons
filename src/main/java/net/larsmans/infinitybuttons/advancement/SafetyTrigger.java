package net.larsmans.infinitybuttons.advancement;

import com.google.gson.JsonObject;
import net.larsmans.infinitybuttons.InfinityButtons;
import net.larsmans.infinitybuttons.item.custom.SafeEmergencyButtonItem;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;

public class SafetyTrigger extends SimpleCriterionTrigger<SafetyTrigger.TriggerInstance> {
    private static final ResourceLocation ID = new ResourceLocation(InfinityButtons.MOD_ID, "safety_button_head");

    @Override
    protected TriggerInstance createInstance(JsonObject pJson, ContextAwarePredicate pPredicate, DeserializationContext pDeserializationContext) {
        return new TriggerInstance(pPredicate);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public void trigger(ServerPlayer pPlayer) {
        this.trigger(pPlayer, (instance) -> instance.matches(pPlayer));
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {

        public TriggerInstance(ContextAwarePredicate pPredicate) {
            super(SafetyTrigger.ID, pPredicate);
        }

        public boolean matches(ServerPlayer player) {
            return player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof SafeEmergencyButtonItem;
        }
    }
}
