package nl.teamdiopside.infinitybuttons.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.PlainTextButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import nl.teamdiopside.infinitybuttons.IBNetworking;
import nl.teamdiopside.infinitybuttons.InfinityButtons;
import nl.teamdiopside.infinitybuttons.block.faced6.LetterButton;
import nl.teamdiopside.infinitybuttons.block.faced6.LetterButtonState;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

public class LetterButtonGUI extends Screen {
    private static final ResourceLocation LETTER_TEXTURE = ResourceLocation.fromNamespaceAndPath(InfinityButtons.MOD_ID, "textures/block/letter_button/characters.png");

    private static final int BUTTON_WIDTH = 24;
    private static final int BUTTON_HEIGHT = 24;
    private static final int BUTTON_MARGIN = 4;
    private static final int BUTTONS_PER_ROW = 7;
    private static final int NUM_BUTTONS = 49;

    private final LetterButton letterButton;
    private final BlockState state;
    private final Level level;
    private final BlockPos pos;

    private static int selectedButton;

    public LetterButtonGUI(LetterButton letterButton, BlockState state, Level level, BlockPos pos) {
        super(Component.translatable("block.infinitybuttons.letter_button"));
        this.letterButton = letterButton;
        this.state = state;
        this.level = level;
        this.pos = pos;
    }

    @Override
    protected void init() {
        selectedButton = this.state.getValue(LetterButton.CHARACTER).ordinal();

        super.init();

        int startX = (this.width - BUTTONS_PER_ROW * (BUTTON_WIDTH + BUTTON_MARGIN)) / 2;
        int rows = (NUM_BUTTONS - 1) / BUTTONS_PER_ROW + 1;
        int startY = (this.height - rows * (BUTTON_HEIGHT + BUTTON_MARGIN)) / 2;

        for (int i = 0; i < NUM_BUTTONS; i++) {
            int row = i / BUTTONS_PER_ROW;
            int col = i % BUTTONS_PER_ROW;

            int x = startX + col * (BUTTON_WIDTH + BUTTON_MARGIN);
            int y = startY + row * (BUTTON_HEIGHT + BUTTON_MARGIN);

            int button = i;
            int textureX = col * 20;
            int textureY = row * 20;

            this.addRenderableWidget(new ImageLetterButton(x, y, BUTTON_WIDTH, BUTTON_HEIGHT, textureX, textureY,
                    0, LETTER_TEXTURE, ignored -> this.onClick(button), button));
        }

        Component doneText = CommonComponents.GUI_DONE;
        int doneButtonWidth = this.font.width(doneText);
        int doneButtonHeight = this.font.lineHeight;

        this.addRenderableWidget(new PlainTextButton( (this.width - doneButtonWidth) / 2, startY + rows * (BUTTON_HEIGHT + BUTTON_MARGIN),
                doneButtonWidth, doneButtonHeight, doneText, ignored -> this.onClose(), this.font));
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_E) {
            this.onClose();
            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void onClose() {
        LetterButtonState[] values = LetterButtonState.values();

        if (selectedButton >= 0 && selectedButton < values.length && this.level.getBlockState(this.pos).getBlock() instanceof LetterButton) {
            LetterButtonState buttonState = values[selectedButton];

            this.level.setBlock(this.pos, this.state.setValue(LetterButton.CHARACTER, buttonState), 3);
            IBNetworking.sendSetLetterButton(this.pos, buttonState);
        }

        if (this.minecraft != null) {
            this.minecraft.setScreen(null);
        }
    }

    public static int getSelectedButton() {
        return selectedButton;
    }

    protected void onClick(int button) {
        selectedButton = button;
        this.onClose();
    }
}
