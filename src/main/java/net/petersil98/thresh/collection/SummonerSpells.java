package net.petersil98.thresh.collection;

import net.petersil98.thresh.data.SummonerSpell;

import java.util.List;

public class SummonerSpells {

    private static List<SummonerSpell> summonerSpells;

    public static SummonerSpell getSummonerSpell(int id){
        return summonerSpells.stream().filter(summonerSpell -> summonerSpell.getId() == id).findFirst().orElse(null);
    }

    public static List<SummonerSpell> getSummonerSpells() {
        return summonerSpells;
    }

    public static void setSummonerSpells(List<SummonerSpell> summonerSpells) {
        SummonerSpells.summonerSpells = summonerSpells;
    }
}
