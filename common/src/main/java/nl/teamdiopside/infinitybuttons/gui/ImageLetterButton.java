package nl.teamdiopside.infinitybuttons.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ImageLetterButton extends Button {
    private static final int THICKNESS = 4;
    private static final int HOVER_COLOR = 0xFF808080;
    private static final int SELECT_COLOR = 0xFFC0C0C0;
    private static final int TEXTURE_SIZE = 256;
    private static final int ICON_SIZE = 20;

    private final int u;
    private final int v;
    private final ResourceLocation texture;
    private final int buttonId;

    public ImageLetterButton(int x, int y, int width, int height, int u, int v, int hoveredVOffset, ResourceLocation texture, OnPress pressAction, int buttonId) {
        super(x, y, width, height, CommonComponents.EMPTY, pressAction, DEFAULT_NARRATION);
        this.u = u;
        this.v = v;
        this.texture = texture;
        this.buttonId = buttonId;
    }

    @Override
    protected void renderWidget(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        int iconX = this.getX() + (this.width - ICON_SIZE) / 2;
        int iconY = this.getY() + (this.height - ICON_SIZE) / 2;

        guiGraphics.blit( this.texture, iconX, iconY, this.u, this.v, ICON_SIZE, ICON_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);

        if (this.isHoveredOrFocused() || this.isSelected()) {
            int color = this.isHoveredOrFocused() ? HOVER_COLOR : SELECT_COLOR;

            int halfThickness = THICKNESS / 2;
            int x = this.getX() + halfThickness;
            int y = this.getY() + halfThickness;

            guiGraphics.fill(x - THICKNESS, y - THICKNESS, x + this.width + THICKNESS, y, color);
            guiGraphics.fill(x - THICKNESS, y, x, y + this.height, color);
            guiGraphics.fill(x - THICKNESS, y + this.height, x + this.width + THICKNESS, y + this.height + THICKNESS, color);
            guiGraphics.fill(x + this.width, y, x + this.width + THICKNESS, y + this.height, color);
        }
    }

    public boolean isSelected() {
        return LetterButtonGUI.getSelectedButton() == this.buttonId;
    }
}

