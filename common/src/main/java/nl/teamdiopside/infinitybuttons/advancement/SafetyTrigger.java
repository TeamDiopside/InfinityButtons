package nl.teamdiopside.infinitybuttons.advancement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import nl.teamdiopside.infinitybuttons.item.SafeEmergencyButtonItem;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class SafetyTrigger extends SimpleCriterionTrigger<SafetyTrigger.TriggerInstance> {

    public void trigger(ServerPlayer pPlayer) {
        this.trigger(pPlayer, (instance) -> instance.matches(pPlayer));
    }

    @Override
    public @NotNull Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleInstance {
        public static final Codec<SafetyTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create((instance) ->
                instance.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(SafetyTrigger.TriggerInstance::player))
                        .apply(instance, SafetyTrigger.TriggerInstance::new)
        );

        public boolean matches(ServerPlayer player) {
            return player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof SafeEmergencyButtonItem;
        }
    }
}
