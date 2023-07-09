package net.larsmans.infinitybuttons.advancement;

import com.google.gson.JsonObject;
import net.larsmans.infinitybuttons.InfinityButtonsInit;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class SafetyTrigger extends AbstractCriterion<SafetyTrigger.TriggerInstance> {
    private static final Identifier ID = new Identifier(InfinityButtonsInit.MOD_ID, "safety_button_head");

    @Override
    protected TriggerInstance conditionsFromJson(JsonObject obj, EntityPredicate.Extended playerPredicate, AdvancementEntityPredicateDeserializer predicateDeserializer) {
        return new TriggerInstance(playerPredicate);
    }

    @Override
    public Identifier getId() {
        return ID;
    }

    public void trigger(ServerPlayerEntity pPlayer) {
        this.trigger(pPlayer, TriggerInstance::matches);
    }

    public static class TriggerInstance extends AbstractCriterionConditions {

        public TriggerInstance(EntityPredicate.Extended pPlayer) {
            super(SafetyTrigger.ID, pPlayer);
        }

        public boolean matches() {
            return true;
        }
    }
}
