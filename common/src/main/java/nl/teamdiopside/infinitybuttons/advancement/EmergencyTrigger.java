package nl.teamdiopside.infinitybuttons.advancement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class EmergencyTrigger extends SimpleCriterionTrigger<EmergencyTrigger.TriggerInstance> {

    public void trigger(ServerPlayer pPlayer) {
        this.trigger(pPlayer, TriggerInstance::matches);
    }

    @Override
    public @NotNull Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleInstance {
        public static final Codec<EmergencyTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create((instance) ->
                instance.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(EmergencyTrigger.TriggerInstance::player))
                        .apply(instance, EmergencyTrigger.TriggerInstance::new)
        );

        public boolean matches() {
            return true;
        }
    }
}
