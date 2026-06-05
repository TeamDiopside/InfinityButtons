package nl.teamdiopside.infinitybuttons.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import nl.teamdiopside.infinitybuttons.registry.IBAdvancementTriggers;
import org.jetbrains.annotations.NotNull;

public class SafeEmergencyButtonItem extends BlockItem implements Equipable {

    private static final int HEAD_SLOT = 39;

    public SafeEmergencyButtonItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public @NotNull EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemStack, level, entity, slot, selected);
        if (slot == HEAD_SLOT && entity instanceof ServerPlayer serverPlayer) {
            IBAdvancementTriggers.SAFETY_TRIGGER.get().trigger(serverPlayer);
        }
    }
}
