package nl.teamdiopside.infinitybuttons.advancement;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.entity.LootContextPredicateValidator;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import nl.teamdiopside.infinitybuttons.InfinityButtons;

import java.util.Optional;

public class EmergencyTrigger extends AbstractCriterion<EmergencyTrigger.TriggerInstance> {
    // TODO FIX ADVANCEMENT TRIGGERS WANT IK SNAP T NIET
    private static final Identifier ID = new Identifier(InfinityButtons.MOD_ID, "emergency_button_press");

    @Override
    public Codec<TriggerInstance> getConditionsCodec() {
        return null;
    }

    @Override
    public AdvancementCriterion<TriggerInstance> create(TriggerInstance conditions) {
        return super.create(conditions);
    }

    public void trigger(ServerPlayerEntity player) {
        this.trigger(player, TriggerInstance::matches);
    }

    public static class TriggerInstance implements CriterionConditions, Conditions {

        public TriggerInstance() {
            super();
        }

        public boolean matches() {
            return true;
        }

        @Override
        public Optional<LootContextPredicate> player() {
            return Optional.empty();
        }
    }
}
