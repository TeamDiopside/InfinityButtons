package net.larsmans.infinitybuttons.mixin;

import net.larsmans.infinitybuttons.block.custom.emergencybutton.SafeEmergencyButton;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "getPreferredEquipmentSlot", at = @At("TAIL"), cancellable = true)
    private static void getCustomPreferredEquipmentSlot(ItemStack stack, CallbackInfoReturnable<EquipmentSlot> cir) {
        Item item = stack.getItem();
        if (item instanceof BlockItem && ((BlockItem)item).getBlock() instanceof SafeEmergencyButton) {
            cir.setReturnValue(EquipmentSlot.HEAD);
        }
    }
}
