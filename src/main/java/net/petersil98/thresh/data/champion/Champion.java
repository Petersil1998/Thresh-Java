package net.petersil98.thresh.data.champion;

import net.petersil98.core.data.Sprite;

import java.util.List;

public class Champion {

    private final int id;
    private final String name;
    private final String title;
    private final String fullImage;
    private final Sprite sprite;
    private final List<Skin> skins;
    private final String lore;
    private final List<String> allyTips;
    private final List<String> enemyTips;
    private final List<String> tags;
    private final String resourceType;
    private final Info info;
    private final Stats baseStats;

    public Champion(int id, String name, String title, String fullImage, Sprite sprite, List<Skin> skins, String lore, List<String> allyTips, List<String> enemyTips, List<String> tags, String resourceType, Info info, Stats baseStats) {
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
        this.info = info;
        this.baseStats = baseStats;
    }

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

    public Info getInfo() {
        return info;
    }

    public Stats getBaseStats() {
        return baseStats;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
