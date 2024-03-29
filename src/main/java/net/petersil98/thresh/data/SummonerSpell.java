package net.petersil98.thresh.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.petersil98.stcommons.data.Sprite;
import net.petersil98.thresh.model.Deserializers;

import java.util.List;
import java.util.Objects;

@JsonDeserialize(using = Deserializers.SummonerSpellDeserializer.class)
public class SummonerSpell {

    private final int id;
    private final String name;
    private final String description;
    private final int cooldown;
    private final int summonerLevel;
    private final int range;
    private final List<String> modes;
    private final Sprite sprite;
    private final String image;

    public SummonerSpell(int id, String name, String description, int cooldown, int summonerLevel, int range, List<String> modes, Sprite sprite, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cooldown = cooldown;
        this.summonerLevel = summonerLevel;
        this.range = range;
        this.modes = modes;
        this.sprite = sprite;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

    public int getRange() {
        return range;
    }

    public List<String> getModes() {
        return modes;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SummonerSpell that = (SummonerSpell) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
