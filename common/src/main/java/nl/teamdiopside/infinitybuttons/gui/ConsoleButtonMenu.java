package nl.teamdiopside.infinitybuttons.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import nl.teamdiopside.infinitybuttons.registry.IBMenus;
import org.jetbrains.annotations.NotNull;

public class ConsoleButtonMenu extends AbstractContainerMenu {
    private static final int SLOT_SIZE = 18;
    private static final int ROWS = 3;
    private static final int COLS = 9;
    private static final int MAIN_START_X = 8;
    private static final int MAIN_START_Y = 124;
    private static final int HOTBAR_Y = 182;
    private static final int MAIN_SLOT_COUNT = ROWS * COLS;

    private final BlockPos pos;

    public ConsoleButtonMenu(int containerId, Inventory playerInventory, FriendlyByteBuf buf) {
        this(containerId, playerInventory, buf.readBlockPos());
    }

    public ConsoleButtonMenu(int containerId, Inventory playerInventory, BlockPos pos) {
        super(IBMenus.CONSOLE_INVENTORY.get(), containerId);

        this.pos = pos;

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int index = 9 + row * COLS + col;
                this.addSlot(new Slot(playerInventory, index, MAIN_START_X + col * SLOT_SIZE, MAIN_START_Y + row * SLOT_SIZE));
            }
        }

        for (int col = 0; col < COLS; col++) {
            this.addSlot(new Slot(playerInventory, col, MAIN_START_X + col * SLOT_SIZE, HOTBAR_Y));
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        Slot slot = this.slots.get(index);
        if (!slot.hasItem()) return ItemStack.EMPTY;

        ItemStack stackInSlot = slot.getItem();
        ItemStack result = stackInSlot.copy();

        boolean fromMain = index < MAIN_SLOT_COUNT;
        boolean moved = fromMain
                ? this.moveItemStackTo(stackInSlot, MAIN_SLOT_COUNT, this.slots.size(), false)
                : this.moveItemStackTo(stackInSlot, 0, MAIN_SLOT_COUNT, false);

        if (!moved) return ItemStack.EMPTY;

        if (stackInSlot.isEmpty()) slot.set(ItemStack.EMPTY);
        else slot.setChanged();

        return result;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return player.getEyePosition().distanceTo(Vec3.atCenterOf(pos)) < 5;
    }

    public BlockPos getPos() {
        return this.pos;
    }
}
