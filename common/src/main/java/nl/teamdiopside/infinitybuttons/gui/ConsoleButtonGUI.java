package nl.teamdiopside.infinitybuttons.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import nl.teamdiopside.infinitybuttons.IBNetworking;
import nl.teamdiopside.infinitybuttons.InfinityButtons;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Same 176x166 panel shape as vanilla's AnvilScreen, background baked with its own labels - see textures/gui/console.png
// Real player-inventory Slots (ConsoleInventoryMenu) so items can be dragged/arranged like any other container GUI
public class ConsoleButtonGUI extends AbstractContainerScreen<PlainInventoryMenu> {
    private static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(InfinityButtons.MOD_ID,
            "textures/gui/console.png");

    private static final Pattern PRESS_DURATION_RANGE = Pattern.compile("random\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)", Pattern.CASE_INSENSITIVE);

    private static final int TERMINAL_TEXT_COLOR = 0x165c24;
    private static final int TERMINAL_INPUT_COLOR = 0x2dcf4f;
    private static final int TERMINAL_ERROR_COLOR = 0xbb4967;

    private static final Component LEVER_LABEL = Component.literal("Act as lever:");
    private static final Component KEYCARD_LABEL = Component.literal("Keycard item:");
    private static final Component DURATION_LABEL = Component.literal("Button duration:");
    private static final Component INFO_LABEL = Component.literal("num or random(min, max)");

    private EditBox leverInput;
    private EditBox pressDurationInput;
    private ItemPickerSlot itemSlot;

    public ConsoleButtonGUI(PlainInventoryMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();

        this.leverInput = new EditBox(this.font, this.leftPos + 24, this.topPos + 27, 110, 10, Component.literal("Act as lever"));
        this.leverInput.setBordered(false);
        this.leverInput.setTextColor(TERMINAL_INPUT_COLOR);
        this.leverInput.setValue("false");
        this.addRenderableWidget(this.leverInput);

        this.pressDurationInput = new EditBox(this.font, this.leftPos + 24, this.topPos + 53, 110, 10, Component.literal("Press duration"));
        this.pressDurationInput.setBordered(false);
        this.pressDurationInput.setTextColor(TERMINAL_INPUT_COLOR);
        this.pressDurationInput.setValue("40");
        this.addRenderableWidget(this.pressDurationInput);

        this.itemSlot = new ItemPickerSlot(this.leftPos + 141, this.topPos + 27, this.menu::getCarried);
        this.itemSlot.setItem(ItemStack.EMPTY);
        this.addRenderableWidget(this.itemSlot);
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        guiGraphics.blit(BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
    }

    @Override
    protected void renderLabels(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // YOU... SHALL... NOT... CAST SHADOWS
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        drawLabelCentered(guiGraphics, this.title, this.width / 2, this.topPos - 12);
        drawLabel(guiGraphics, LEVER_LABEL, this.leftPos + 18, this.topPos + 16);
        drawLabelRight(guiGraphics, KEYCARD_LABEL, this.leftPos + 160, this.topPos + 16);
        drawLabel(guiGraphics, DURATION_LABEL, this.leftPos + 18, this.topPos + 42);
        drawLabel(guiGraphics, INFO_LABEL, this.leftPos + 26, this.topPos + 62);

        this.renderTooltip(guiGraphics, mouseX, mouseY);

        if (this.itemSlot.isMouseOver(mouseX, mouseY) && !this.itemSlot.getItem().isEmpty()) {
            guiGraphics.renderTooltip(this.font, this.itemSlot.getItem(), mouseX, mouseY);
        }
    }

    private void drawLabel(@NotNull GuiGraphics guiGraphics, Component text, int x, int y) {
        guiGraphics.drawString(this.font, text, x, y, ConsoleButtonGUI.TERMINAL_TEXT_COLOR, false);
    }

    private void drawLabelRight(@NotNull GuiGraphics guiGraphics, Component text, int rightX, int y) {
        guiGraphics.drawString(this.font, text, rightX - this.font.width(text), y, ConsoleButtonGUI.TERMINAL_TEXT_COLOR, false);
    }

    private void drawLabelCentered(@NotNull GuiGraphics guiGraphics, Component text, int centerX, int y) {
        guiGraphics.drawString(this.font, text, centerX - this.font.width(text) / 2, y, 0xFFFFFF, false);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        boolean handled = super.mouseClicked(mouseX, mouseY, button);

        updateFieldFocusAndColor(this.leverInput, mouseX, mouseY);
        updateFieldFocusAndColor(this.pressDurationInput, mouseX, mouseY);

        return handled;
    }

    private void updateFieldFocusAndColor(EditBox box, double mouseX, double mouseY) {
        if (box.isMouseOver(mouseX, mouseY)) {
            box.setTextColor(TERMINAL_INPUT_COLOR);
        } else {
            box.setFocused(false);
            revalidateFieldColor(box);
        }
    }

    private void revalidateFieldColor(EditBox box) {
        boolean valid = box == this.leverInput ? parseLeverEnabled() != null : parsePressDurations() != null;
        box.setTextColor(valid ? TERMINAL_INPUT_COLOR : TERMINAL_ERROR_COLOR);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.getFocused() instanceof EditBox editBox && editBox.isFocused()) {
            if (keyCode == GLFW.GLFW_KEY_ENTER || keyCode == GLFW.GLFW_KEY_KP_ENTER) {
                editBox.setFocused(false);
                revalidateFieldColor(editBox);
                return true;
            }

            return editBox.keyPressed(keyCode, scanCode, modifiers);
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    public Boolean parseLeverEnabled() {
        String value = this.leverInput.getValue();
        if (value.equalsIgnoreCase("true")) return true;
        if (value.equalsIgnoreCase("false")) return false;

        return null;
    }

    public Integer[] parsePressDurations() {
        return parsePressDuration(this.pressDurationInput.getValue());
    }

    public ItemStack getKeycardItem() {
        return this.itemSlot.getItem();
    }

    @Override
    public void onClose() {
        Boolean lever = this.parseLeverEnabled();
        Integer[] durations = this.parsePressDurations();

        if (lever == null) lever = false;
        if (durations == null) durations = new Integer[] { 40, 40 };

        IBNetworking.sendSetConsoleButton(this.menu.getPos(), lever, durations[0], durations[1], this.getKeycardItem());

        super.onClose();
    }

    // Accepts an int "40" or "random(int, int)"
    public static Integer[] parsePressDuration(String value) {
        String trimmed = value.trim();

        try {
            int fixed = Integer.parseInt(trimmed);

            if (fixed < 1) return null;

            return new Integer[] { fixed, fixed };
        } catch (NumberFormatException ignored) {
            Matcher matcher = PRESS_DURATION_RANGE.matcher(trimmed);
            if (!matcher.matches()) return null;

            int min = Integer.parseInt(matcher.group(1));
            int max = Integer.parseInt(matcher.group(2));

            if (min < 1 || max < 1) return null;

            return new Integer[] { min, max };
        }
    }
}
