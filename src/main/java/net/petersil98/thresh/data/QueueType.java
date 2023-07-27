package net.petersil98.thresh.data;

import java.util.Objects;

public class QueueType {

    private int id;
    private String name;
    private String shortName;
    private String description;
    private String detailedDescription;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDescription() {
        return description;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueueType queueType = (QueueType) o;
        return id == queueType.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
