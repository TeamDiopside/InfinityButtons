package net.larsmans.infinitybuttons.mixin;

import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.larsmans.infinitybuttons.InfinityButtonsUtil.SAFETY_BUTTONS;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity {
    protected MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow public abstract void equipStack(EquipmentSlot slot, ItemStack stack);

    @Shadow public abstract void setEquipmentDropChance(EquipmentSlot slot, float chance);

    @Inject(method = "initEquipment", at = @At("HEAD"), cancellable = true)
    private void initEquipment(Random random, LocalDifficulty localDifficulty, CallbackInfo ci) {
        InfinityButtonsUtil.buildSafety();
        if (random.nextDouble() < 0.001) {
            this.equipStack(EquipmentSlot.HEAD, new ItemStack(SAFETY_BUTTONS.get(random.nextInt(SAFETY_BUTTONS.size()))));
            this.setEquipmentDropChance(EquipmentSlot.HEAD, 1f);
            ci.cancel();
        }
    }
}
