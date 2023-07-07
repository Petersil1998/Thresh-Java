package net.petersil98.thresh.collection;

import net.petersil98.thresh.data.QueueType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueueTypes {

    private static Map<Integer, QueueType> queueTypes;

    public static QueueType getQueueType(int id){
        return queueTypes.get(id);
    }

    public static List<QueueType> getQueueTypes() {
        return new ArrayList<>(queueTypes.values());
    }
}
