package net.petersil98.thresh.collection;

import net.petersil98.thresh.data.Map;

import java.util.List;

public class Maps {

    private static List<Map> maps;

    public static Map getMap(int id){
        return maps.stream().filter(map -> map.id() == id).findFirst().orElse(null);
    }

    public static List<Map> getMaps() {
        return maps;
    }

    public static void setMaps(List<Map> maps) {
        Maps.maps = maps;
    }
}
