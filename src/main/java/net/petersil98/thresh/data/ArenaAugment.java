package net.petersil98.thresh.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.petersil98.thresh.model.Deserializers;

import java.util.Map;
import java.util.Objects;

@JsonDeserialize(using = Deserializers.ArenaAugmentDeserializer.class)
public class ArenaAugment {

    private final int id;
    private final String name;
    private final int rarity;
    private final String tooltip;
    private final String description;
    private final String iconLarge;
    private final String iconSmall;
    private final Map<String, Double> dataValues;

    public ArenaAugment(int id, String name, int rarity, String tooltip, String description, String iconLarge, String iconSmall, Map<String, Double> dataValues) {
        this.id = id;
        this.name = name;
        this.rarity = rarity;
        this.tooltip = tooltip;
        this.description = description;
        this.iconLarge = iconLarge;
        this.iconSmall = iconSmall;
        this.dataValues = dataValues;
    }

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
