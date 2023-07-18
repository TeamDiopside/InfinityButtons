package net.larsmans.infinitybuttons.block.custom.letterbutton.gui;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.larsmans.infinitybuttons.block.custom.letterbutton.LetterButton;
import net.larsmans.infinitybuttons.block.custom.letterbutton.LetterButtonEnum;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.PressableTextWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LetterButtonGui extends Screen {

    private static final Identifier LETTER_TEXTURE = new Identifier("infinitybuttons", "textures/block/letter_button/characters.png");
    public static final Identifier LETTER_BUTTON_PACKET = new Identifier("infinitybuttons", "letter_button");

    private static final int BUTTON_WIDTH = 24;
    private static final int BUTTON_HEIGHT = 24;
    private static final int BUTTON_MARGIN = 4;
    private static final int BUTTONS_PER_ROW = 7;
    private static final int NUM_BUTTONS = 49;

    private final LetterButton letterButton;
    private final BlockState state;
    private final World world;
    private final BlockPos pos;
    private static int selectedButton;

    public LetterButtonGui(LetterButton letterButton, BlockState state, World world, BlockPos pos) {
        super(Text.translatable("block.infinitybuttons.letter_button"));
        this.letterButton = letterButton;
        this.state = state;
        this.world = world;
        this.pos = pos;
    }

    @Override
    protected void init() {
        selectedButton = letterButton.getEnumId(state);
        super.init();
        int startX = (width - (BUTTONS_PER_ROW * (BUTTON_WIDTH + BUTTON_MARGIN))) / 2;
        int startY = (height - (((NUM_BUTTONS - 1) / BUTTONS_PER_ROW + 1) * (BUTTON_HEIGHT + BUTTON_MARGIN))) / 2;

        for (int i = 0; i < NUM_BUTTONS; i++) {
            int row = i / BUTTONS_PER_ROW;
            int col = i % BUTTONS_PER_ROW;
            int x = startX + col * (BUTTON_WIDTH + BUTTON_MARGIN);
            int y = startY + row * (BUTTON_HEIGHT + BUTTON_MARGIN);
            int button = i;
            int TexX = col * 20;
            int TexY = row * 20;

            addDrawableChild(new ImageLetterButton(x, y, BUTTON_WIDTH, BUTTON_HEIGHT, TexX, TexY, 0, LETTER_TEXTURE, pButton -> onClick(button), button));
        }

        int buttonWidth = textRenderer.getWidth(Text.translatable("gui.done"));
        int buttonHeight = textRenderer.fontHeight;
        addDrawableChild(new PressableTextWidget((width - buttonWidth) / 2, startY + (((NUM_BUTTONS - 1) / BUTTONS_PER_ROW) + 1) * (BUTTON_HEIGHT + BUTTON_MARGIN),
                buttonWidth, buttonHeight, Text.translatable("gui.done"), pButton -> close(), textRenderer));
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public void close() {
        for (LetterButtonEnum buttonEnum : LetterButtonEnum.values()) {
            if (selectedButton == buttonEnum.ordinal()) {
                letterButton.setState(state, world, pos, buttonEnum);
                ClientPlayNetworking.send(LETTER_BUTTON_PACKET, PacketByteBufs.create().writeBlockPos(pos).writeEnumConstant(buttonEnum));
            }
        }
        super.close();
    }

    public static int getSelectedButton() {
        return selectedButton;
    }

    protected void onClick(int button) {
        selectedButton = button;
    }
}
