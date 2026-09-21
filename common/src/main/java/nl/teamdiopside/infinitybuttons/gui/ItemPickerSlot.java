package nl.teamdiopside.infinitybuttons.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

// Displays a picked item, purely client-side, never stored in any container.
// Click: if the mouse is currently carrying an item (menu.getCarried()), copies it here (the carried
// stack is left untouched, still being dragged); otherwise clears whatever was picked before.
public class ItemPickerSlot extends AbstractWidget {
    private final Supplier<ItemStack> carriedItemSupplier;
    private ItemStack item = ItemStack.EMPTY;

    public ItemPickerSlot(int x, int y, Supplier<ItemStack> carriedItemSupplier) {
        super(x, y, 18, 18, CommonComponents.EMPTY);
        this.carriedItemSupplier = carriedItemSupplier;
    }

    @Override
    protected void renderWidget(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // No background/border drawn here - the screen's own texture already has a slot box at this position
        if (!this.item.isEmpty()) {
            guiGraphics.renderItem(this.item, this.getX() + 1, this.getY() + 1);
            guiGraphics.renderItemDecorations(Minecraft.getInstance().font, this.item, this.getX() + 1, this.getY() + 1);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!this.active || !this.visible || !this.isMouseOver(mouseX, mouseY)) return false;

        ItemStack carried = this.carriedItemSupplier.get();
        this.item = carried.isEmpty() ? ItemStack.EMPTY : carried.copy();

        return true;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    @Override
    protected void updateWidgetNarration(@NotNull NarrationElementOutput narrationElementOutput) {
        narrationElementOutput.add(NarratedElementType.TITLE, this.item.isEmpty() ? CommonComponents.EMPTY : this.item.getHoverName());
    }
}
