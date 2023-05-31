package net.petersil98.thresh.collection;

import net.petersil98.thresh.data.rune.RuneStyle;

import java.util.List;

public class RuneStyles {

    private static List<RuneStyle> runeStyles;

    public static RuneStyle getRuneStyle(int id){
        return runeStyles.stream().filter(runeStyle -> runeStyle.getId() == id).findFirst().orElse(null);
    }

    public static List<RuneStyle> getRuneStyles() {
        return runeStyles;
    }

    public static void setRuneStyles(List<RuneStyle> runeStyles) {
        RuneStyles.runeStyles = runeStyles;
    }
}
