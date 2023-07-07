package net.petersil98.thresh.collection;

import net.petersil98.thresh.data.rune.RuneStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RuneStyles {

    private static Map<Integer, RuneStyle> runeStyles;

    public static RuneStyle getRuneStyle(int id){
        return runeStyles.get(id);
    }

    public static List<RuneStyle> getRuneStyles() {
        return new ArrayList<>(runeStyles.values());
    }
}
