package nl.teamdiopside.infinitybuttons.block.faced6;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum LetterButtonState implements StringRepresentable {
    NONE("none"),
    LETTER_A("a"),
    LETTER_B("b"),
    LETTER_C("c"),
    LETTER_D("d"),
    LETTER_E("e"),
    LETTER_F("f"),
    LETTER_G("g"),
    LETTER_H("h"),
    LETTER_I("i"),
    LETTER_J("j"),
    LETTER_K("k"),
    LETTER_L("l"),
    LETTER_M("m"),
    LETTER_N("n"),
    LETTER_O("o"),
    LETTER_P("p"),
    LETTER_Q("q"),
    LETTER_R("r"),
    LETTER_S("s"),
    LETTER_T("t"),
    LETTER_U("u"),
    LETTER_V("v"),
    LETTER_W("w"),
    LETTER_X("x"),
    LETTER_Y("y"),
    LETTER_Z("z"),
    NUMBER_0("0"),
    NUMBER_1("1"),
    NUMBER_2("2"),
    NUMBER_3("3"),
    NUMBER_4("4"),
    NUMBER_5("5"),
    NUMBER_6("6"),
    NUMBER_7("7"),
    NUMBER_8("8"),
    NUMBER_9("9"),
    DOT("dot"),
    COMMA("comma"),
    Q_MARK("q_mark"),
    EX_MARK("ex_mark"),
    MINUS("minus"),
    PLUS("plus"),
    COLON("colon"),
    EQUALS("equals"),
    HASHTAG("hashtag"),
    BRACKET_OPEN("br_open"),
    BRACKET_CLOSE("br_close"),
    SLASH("slash");

    private final String name;

    LetterButtonState(String name) {
        this.name = name;
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.name;
    }
}