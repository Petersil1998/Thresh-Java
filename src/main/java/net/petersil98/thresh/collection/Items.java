package net.petersil98.thresh.collection;

import net.petersil98.thresh.data.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Items {

    private static Map<Integer, Item> items;

    public static Item getItem(int id){
        return items.get(id);
    }

    public static List<Item> getItems() {
        return new ArrayList<>(items.values());
    }
}
