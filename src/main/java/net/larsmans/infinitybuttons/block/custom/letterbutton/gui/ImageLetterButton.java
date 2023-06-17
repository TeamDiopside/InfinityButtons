package net.larsmans.infinitybuttons.block.custom.letterbutton.gui;

import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.awt.*;

public class ImageLetterButton extends TexturedButtonWidget {

    private final int buttonId;
    private static final int THICKNESS = 4;
    private static final int HOVER_COLOR = Color.GRAY.getRGB();
    private static final int SELECT_COLOR = Color.LIGHT_GRAY.getRGB();

    public ImageLetterButton(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, PressAction pressAction, int buttonId) {
        super(x, y, width, height, u, v, hoveredVOffset, texture, pressAction);
        this.buttonId = buttonId;
    }

    @Override
    public void renderButton(MatrixStack poseStack, int MouseX, int MouseY, float PartialTick) {
        int COLOR;
        super.renderButton(poseStack, MouseX, MouseY, PartialTick);
        if (hovered || isSelected()) {
            if (hovered) {
                COLOR = HOVER_COLOR;
            } else {
                COLOR = SELECT_COLOR;
            }
            fill(poseStack, x - THICKNESS, y - THICKNESS, x + width + THICKNESS, y, COLOR);
            fill(poseStack, x - THICKNESS, y, x, y + height, COLOR);
            fill(poseStack, x - THICKNESS, y + height, x + width + THICKNESS, y + height + THICKNESS, COLOR);
            fill(poseStack, x + width, y, x + width + THICKNESS, y + height, COLOR);
        }
    }

    public boolean isSelected() {
        return LetterButtonGui.getSelectedButton() == buttonId;
    }
}
