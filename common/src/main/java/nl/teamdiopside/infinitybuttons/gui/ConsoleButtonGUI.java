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

public class ConsoleButtonGUI extends AbstractContainerScreen<ConsoleButtonMenu> {
    private static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(InfinityButtons.MOD_ID,
            "textures/gui/console.png");

    private static final Pattern PRESS_DURATION_RANGE = Pattern.compile("random\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)", Pattern.CASE_INSENSITIVE);

    private static final int TERMINAL_TEXT_COLOR = 0x165c24;
    private static final int TERMINAL_INPUT_COLOR = 0x2dcf4f;
    private static final int TERMINAL_ERROR_COLOR = 0xbb4967;

    private static final Component LEVER_LABEL = Component.translatable("gui.infinitybuttons.console_button.lever_label");
    private static final Component KEYCARD_LABEL = Component.translatable("gui.infinitybuttons.console_button.keycard_label");
    private static final Component DURATION_LABEL = Component.translatable("gui.infinitybuttons.console_button.duration_label");
    private static final Component DURATION_INFO_LABEL = Component.translatable("gui.infinitybuttons.console_button.duration_info");
    private static final Component DURABILITY_LABEL = Component.translatable("gui.infinitybuttons.console_button.durability_label");
    private static final Component DURABILITY_INFO_LABEL = Component.translatable("gui.infinitybuttons.console_button.durability_info");

    private static final Component TICKS_UNIT = Component.translatable("gui.infinitybuttons.console_button.ticks_unit");
    private static final Component TIMES_UNIT = Component.translatable("gui.infinitybuttons.console_button.times_unit");

    private static final Component DURATION_NARRATION = Component.translatable("gui.infinitybuttons.console_button.duration_narration");
    private static final Component DURABILITY_NARRATION = Component.translatable("gui.infinitybuttons.console_button.durability_narration");

    private EditBox leverInput;
    private EditBox pressDurationInput;
    private EditBox durabilityInput;
    private ItemPickerSlot itemSlot;

    public ConsoleButtonGUI(ConsoleButtonMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 206;
    }

    @Override
    protected void init() {
        super.init();

        this.leverInput = new EditBox(this.font, this.leftPos + 24, this.topPos + 27, 110, 10, LEVER_LABEL);
        this.leverInput.setBordered(false);
        this.leverInput.setTextColor(TERMINAL_INPUT_COLOR);
        this.leverInput.setValue("false");
        this.addRenderableWidget(this.leverInput);

        this.pressDurationInput = new EditBox(this.font, this.leftPos + 24, this.topPos + 53, 110, 10, DURATION_NARRATION);
        this.pressDurationInput.setBordered(false);
        this.pressDurationInput.setTextColor(TERMINAL_INPUT_COLOR);
        this.pressDurationInput.setValue("40");
        this.addRenderableWidget(this.pressDurationInput);

        this.durabilityInput = new EditBox(this.font, this.leftPos + 24, this.topPos + 88, 110, 10, DURABILITY_NARRATION);
        this.durabilityInput.setBordered(false);
        this.durabilityInput.setTextColor(TERMINAL_INPUT_COLOR);
        this.durabilityInput.setValue("infinite");
        this.addRenderableWidget(this.durabilityInput);

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
        drawLabel(guiGraphics, DURATION_INFO_LABEL, this.leftPos + 26, this.topPos + 62);
        drawLabel(guiGraphics, DURABILITY_LABEL, this.leftPos + 18, this.topPos + 77);
        drawLabel(guiGraphics, DURABILITY_INFO_LABEL, this.leftPos + 26, this.topPos + 97);

        int ticksInputWidth = Math.min(
                this.font.width(this.pressDurationInput.getValue()),
                this.pressDurationInput.getWidth()
        );
        drawLabel(guiGraphics, TICKS_UNIT, this.leftPos + 24 + ticksInputWidth, this.topPos + 53);

        int timesInputWidth = Math.min(
                this.font.width(this.durabilityInput.getValue()),
                this.durabilityInput.getWidth()
        );
        drawLabel(guiGraphics, TIMES_UNIT, this.leftPos + 24 + timesInputWidth, this.topPos + 88);

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
        updateFieldFocusAndColor(this.durabilityInput, mouseX, mouseY);

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
        boolean valid;
        if (box == this.leverInput) {
            valid = parseLeverEnabled() != null;
        } else if (box == this.pressDurationInput) {
            valid = parsePressDurations() != null;
        } else if (box == this.durabilityInput) {
            valid = parseDurability() != null;
        } else {
            throw new IllegalArgumentException("Unexpected value!");
        }
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

    public Integer parseDurability() {
        return parseDurability(this.durabilityInput.getValue());
    }

    public ItemStack getKeycardItem() {
        return this.itemSlot.getItem();
    }

    @Override
    public void onClose() {
        Boolean lever = this.parseLeverEnabled();
        Integer[] durations = this.parsePressDurations();
        Integer durability = this.parseDurability();

        if (lever == null) lever = false;
        if (durations == null) durations = new Integer[] { 40, 40 };
        if (durability == null) durability = -1;

        IBNetworking.sendSetConsoleButton(this.menu.getPos(), lever, durations[0], durations[1], durability, this.getKeycardItem());

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

    // Accepts an int "40" or "random(int, int)" or "infinite"
    public static Integer parseDurability(String value) {
        String trimmed = value.trim();
        // -1 is infinite
        if (trimmed.equalsIgnoreCase("infinite") || trimmed.equalsIgnoreCase("infinity")) return -1;

        Integer[] rangeArray = parsePressDuration(value);
        if (rangeArray == null || rangeArray.length != 2) return null;

        int range = rangeArray[1] - rangeArray[0];
        // Support random durability by calculating it when closing the UI.
        // This will not be communicated to the player, but can still be used.
        return (int)Math.floor(Math.random() * range + rangeArray[0]);
    }
}
