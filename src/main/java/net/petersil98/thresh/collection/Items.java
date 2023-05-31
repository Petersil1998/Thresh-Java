package net.petersil98.thresh.collection;

import net.petersil98.thresh.data.Item;

import java.util.List;

public class Items {

    private static List<Item> items;

    public static Item getItem(int id){
        return items.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
    }

    public static List<Item> getItems() {
        return items;
    }

    public static void setItems(List<Item> items) {
        Items.items = items;
    }
}
