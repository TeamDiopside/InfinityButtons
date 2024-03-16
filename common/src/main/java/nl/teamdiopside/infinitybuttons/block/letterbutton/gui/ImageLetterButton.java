package nl.teamdiopside.infinitybuttons.block.letterbutton.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
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
    public void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        int COLOR;
        super.renderButton(context, mouseX, mouseY, delta);
        if (hovered || isSelected()) {
            if (hovered) {
                COLOR = HOVER_COLOR;
            } else {
                COLOR = SELECT_COLOR;
            }
            int x = getX();
            int y = getY();
            context.fill(x - THICKNESS, y - THICKNESS, x + width + THICKNESS, y, COLOR);
            context.fill(x - THICKNESS, y, x, y + height, COLOR);
            context.fill(x - THICKNESS, y + height, x + width + THICKNESS, y + height + THICKNESS, COLOR);
            context.fill(x + width, y, x + width + THICKNESS, y + height, COLOR);
        }
    }

    public boolean isSelected() {
        return LetterButtonGui.getSelectedButton() == buttonId;
    }
}
