package net.petersil98.thresh.collection;

import net.petersil98.thresh.data.ArenaAugment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArenaAugments {

    private static Map<Integer, ArenaAugment> arenaAugments;

    public static ArenaAugment getArenaAugment(int id) {
        return arenaAugments.get(id);
    }

    public static List<ArenaAugment> getArenaAugments() {
        return new ArrayList<>(arenaAugments.values());
    }
}
