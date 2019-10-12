package main.body.header;

import java.util.Vector;

public class header {

    public enum Theme {
        LIGHT,
        DARK
    }

    public enum Language {
        ARMENIAN,
        ENGLISH,
        RUSSIAN
    }

    Vector<String> history = new Vector<>();

    public Language language = Language.ARMENIAN;
    public Theme theme = Theme.LIGHT;
}
