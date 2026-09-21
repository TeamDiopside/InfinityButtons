package nl.teamdiopside.infinitybuttons.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import nl.teamdiopside.infinitybuttons.registry.IBMenus;
import org.jetbrains.annotations.NotNull;

// Matching the anvil UI
public class PlainInventoryMenu extends AbstractContainerMenu {
    private static final int SLOT_SIZE = 18;
    private static final int ROWS = 3;
    private static final int COLS = 9;
    private static final int MAIN_START_X = 8;
    private static final int MAIN_START_Y = 84;
    private static final int HOTBAR_Y = 142;
    private static final int MAIN_SLOT_COUNT = ROWS * COLS;

    private final BlockPos pos;
    private final boolean initialLever;
    private final int initialMinTicks;
    private final int initialMaxTicks;
    private final ItemStack initialKeyItem;

    public PlainInventoryMenu(int containerId, Inventory playerInventory, FriendlyByteBuf buf) {
        this(containerId, playerInventory, buf.readBlockPos(), buf.readBoolean(), buf.readVarInt(), buf.readVarInt(),
                ItemStack.parseOptional(playerInventory.player.level().registryAccess(), buf.readNbt()));
    }

    public PlainInventoryMenu(int containerId, Inventory playerInventory, BlockPos pos,
                              boolean initialLever, int initialMinTicks, int initialMaxTicks, ItemStack initialKeyItem) {
        super(IBMenus.CONSOLE_INVENTORY.get(), containerId);

        this.pos = pos;
        this.initialLever = initialLever;
        this.initialMinTicks = initialMinTicks;
        this.initialMaxTicks = initialMaxTicks;
        this.initialKeyItem = initialKeyItem.is(Items.AIR) ? ItemStack.EMPTY : initialKeyItem;

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
        return true;
    }

    public BlockPos getPos() {
        return this.pos;
    }

    public boolean isInitialLever() {
        return this.initialLever;
    }

    public int getInitialMinTicks() {
        return this.initialMinTicks;
    }

    public int getInitialMaxTicks() {
        return this.initialMaxTicks;
    }

    public ItemStack getInitialKeyItem() {
        return this.initialKeyItem;
    }
}
