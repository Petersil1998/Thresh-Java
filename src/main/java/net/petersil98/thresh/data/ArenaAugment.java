package net.petersil98.thresh.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArenaAugment {

    private int id;
    private String name;
    private int rarity;
    private String tooltip;
    @JsonProperty("desc")
    private String description;
    private String iconLarge;
    private String iconSmall;
    private Map<String, Double> dataValues;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRarity() {
        return rarity;
    }

    public String getTooltip() {
        return tooltip;
    }

    public String getDescription() {
        return description;
    }

    public String getIconLarge() {
        return iconLarge;
    }

    public String getIconSmall() {
        return iconSmall;
    }

    public Map<String, Double> getDataValues() {
        return dataValues;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArenaAugment that = (ArenaAugment) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
