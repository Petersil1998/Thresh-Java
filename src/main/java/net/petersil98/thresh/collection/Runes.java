package net.petersil98.thresh.collection;

import net.petersil98.thresh.data.rune.Rune;

import java.util.List;

public class Runes {

    private static List<Rune> runes;

    public static Rune getRune(int id){
        return runes.stream().filter(rune -> rune.getId() == id).findFirst().orElse(null);
    }

    public static List<Rune> getRunes() {
        return runes;
    }

    public static void setRunes(List<Rune> runes) {
        Runes.runes = runes;
    }
}
