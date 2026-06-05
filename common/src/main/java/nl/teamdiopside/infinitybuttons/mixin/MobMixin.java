package nl.teamdiopside.infinitybuttons.mixin;

import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import nl.teamdiopside.infinitybuttons.block.emergency.SafeEmergencyButton;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(Mob.class)
public abstract class MobMixin extends LivingEntity implements EquipmentUser, Leashable, Targeting {
    protected MobMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow
    public abstract void setItemSlot(EquipmentSlot equipmentSlot, ItemStack itemStack);

    @Shadow public abstract void setDropChance(EquipmentSlot equipmentSlot, float chance);

    @Inject(method = "populateDefaultEquipmentSlots", at = @At("HEAD"), cancellable = true)
    protected void populateDefaultEquipmentSlots(RandomSource randomSource, DifficultyInstance difficultyInstance, CallbackInfo ci) {
        if (random.nextDouble() < 0.001) {
            var safeEmergencyButtons = new ArrayList<>(IBBlocks.SAFE_EMERGENCY_BUTTONS.values());
            SafeEmergencyButton chosenOne = safeEmergencyButtons.get(random.nextInt(safeEmergencyButtons.size())).get();

            this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(chosenOne));
            this.setDropChance(EquipmentSlot.HEAD, 1f);
            ci.cancel();
        }
    }
}
