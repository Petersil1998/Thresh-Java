package net.petersil98.thresh.data.champion;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.petersil98.thresh.data.Sprite;

import java.util.List;

public class Champion {

    private int id;
    private String name;
    private String title;
    private String fullImage;
    private Sprite sprite;
    private List<Skin> skins;
    private String lore;
    private List<String> allyTips;
    private List<String> enemyTips;
    private List<String> tags;
    private String resourceType;
    @JsonProperty("stats")
    private Stats baseStats;

    public Champion(int id, String name, String title, String fullImage, Sprite sprite, List<Skin> skins, String lore, List<String> allyTips, List<String> enemyTips, List<String> tags, String resourceType, Stats baseStats) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.fullImage = fullImage;
        this.sprite = sprite;
        this.skins = skins;
        this.lore = lore;
        this.allyTips = allyTips;
        this.enemyTips = enemyTips;
        this.tags = tags;
        this.resourceType = resourceType;
        this.baseStats = baseStats;
    }

    public Champion() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getFullImage() {
        return fullImage;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public List<Skin> getSkins() {
        return skins;
    }

    public String getLore() {
        return lore;
    }

    public List<String> getAllyTips() {
        return allyTips;
    }

    public List<String> getEnemyTips() {
        return enemyTips;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getResourceType() {
        return resourceType;
    }

    public Stats getBaseStats() {
        return baseStats;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
