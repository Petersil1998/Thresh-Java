package net.petersil98.thresh.collection;

import net.petersil98.thresh.data.champion.Champion;

import java.util.List;

public class Champions {

    private static List<Champion> champions;

    public static Champion getChampion(int id){
        return champions.stream().filter(champion -> champion.getId() == id).findFirst().orElse(null);
    }

    public static Champion getChampionByName(String name){
        String finalName = name.replaceAll("[^A-Za-z]","");
        return champions.stream().filter(champion -> champion.getName().replaceAll("[^A-Za-z]","").equals(finalName)).findFirst().orElse(null);
    }

    public static List<Champion> getChampions() {
        return champions;
    }

    public static void setChampions(List<Champion> champions) {
        Champions.champions = champions;
    }
}
