package net.petersil98.thresh.collection;

import net.petersil98.thresh.data.rune.RuneStat;

import java.util.List;

public class RuneStats {

    private static List<RuneStat> runeStats;

    public static RuneStat getRuneStat(int id){
        return runeStats.stream().filter(runeStat -> runeStat.getId() == id).findFirst().orElse(null);
    }

    public static List<RuneStat> getRuneStats() {
        return runeStats;
    }

    public static void setRuneStats(List<RuneStat> runeStats) {
        RuneStats.runeStats = runeStats;
    }
}
