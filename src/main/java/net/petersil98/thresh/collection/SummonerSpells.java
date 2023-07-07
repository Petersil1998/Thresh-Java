package net.petersil98.thresh.collection;

import net.petersil98.thresh.data.SummonerSpell;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SummonerSpells {

    private static Map<Integer, SummonerSpell> summonerSpells;

    public static SummonerSpell getSummonerSpell(int id){
        return summonerSpells.get(id);
    }

    public static List<SummonerSpell> getSummonerSpells() {
        return new ArrayList<>(summonerSpells.values());
    }
}
