package net.petersil98.thresh.collection;

import net.petersil98.thresh.data.rune.Rune;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Runes {

    private static Map<Integer, Rune> runes;

    public static Rune getRune(int id){
        return runes.get(id);
    }

    public static List<Rune> getRunes() {
        return new ArrayList<>(runes.values());
    }
}
