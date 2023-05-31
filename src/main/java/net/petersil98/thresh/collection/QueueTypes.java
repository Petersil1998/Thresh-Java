package net.petersil98.thresh.collection;

import net.petersil98.thresh.data.QueueType;

import java.util.List;

public class QueueTypes {

    private static List<QueueType> queueTypes;

    public static QueueType getQueueType(int id){
        return queueTypes.stream().filter(queueType -> queueType.getId() == id).findFirst().orElse(null);
    }

    public static List<QueueType> getQueueTypes() {
        return queueTypes;
    }

    public static void setQueueTypes(List<QueueType> queueTypes) {
        QueueTypes.queueTypes = queueTypes;
    }
}
