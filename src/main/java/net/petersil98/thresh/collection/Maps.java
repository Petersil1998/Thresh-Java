package net.petersil98.thresh.collection;

import net.petersil98.thresh.data.Map;

import java.util.ArrayList;
import java.util.List;

public class Maps {

    private static java.util.Map<Integer, Map> maps;

    public static Map getMap(int id){
        return maps.get(id);
    }

    public static List<Map> getMaps() {
        return new ArrayList<>(maps.values());
    }
}
