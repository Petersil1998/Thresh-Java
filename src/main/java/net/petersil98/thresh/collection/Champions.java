package net.petersil98.thresh.collection;

import net.petersil98.thresh.data.champion.Champion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Champions {

    private static Map<Integer, Champion> champions;

    public static Champion getChampion(int id){
        return champions.get(id);
    }

    public static Champion getChampionByName(String name){
        String finalName = name.replaceAll("[^A-Za-z]","");
        return getChampions().stream().filter(champion -> champion.getName().replaceAll("[^A-Za-z]","").equals(finalName)).findFirst().orElse(null);
    }

    public static List<Champion> getChampions() {
        return new ArrayList<>(champions.values());
    }
}
