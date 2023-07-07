package net.petersil98.thresh.collection;

import net.petersil98.thresh.data.rune.RuneStat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RuneStats {

    private static Map<Integer, RuneStat> runeStats;

    public static RuneStat getRuneStat(int id){
        return runeStats.get(id);
    }

    public static List<RuneStat> getRuneStats() {
        return new ArrayList<>(runeStats.values());
    }
}
