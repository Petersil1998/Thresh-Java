package net.petersil98.thresh.data.champion;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.petersil98.thresh.model.Deserializers;

import java.util.List;
import java.util.Objects;

@JsonDeserialize(using = Deserializers.ChampionDeserializer.class)
public class Champion {

    private final int id;
    private final String apiName;
    private final String name;
    private final String title;
    private final List<Skin> skins;
    private final String lore;
    private final List<String> allyTips;
    private final List<String> enemyTips;
    private final List<String> tags;
    private final String resourceType;
    private final TacticalInfo tacticalInfo;
    private final PlayStyleInfo playStyleInfo;
    private final Stats baseStats;

    public Champion(int id, String apiName, String name, String title, List<Skin> skins, String lore, List<String> allyTips, List<String> enemyTips, List<String> tags, String resourceType, TacticalInfo tacticalInfo, PlayStyleInfo playStyleInfo, Stats baseStats) {
        this.id = id;
        this.apiName = apiName;
        this.name = name;
        this.title = title;
        this.skins = skins;
        this.lore = lore;
        this.allyTips = allyTips;
        this.enemyTips = enemyTips;
        this.tags = tags;
        this.resourceType = resourceType;
        this.tacticalInfo = tacticalInfo;
        this.playStyleInfo = playStyleInfo;
        this.baseStats = baseStats;
    }

    public int getId() {
        return id;
    }

    public String getApiName() {
        return apiName;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
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

    public TacticalInfo getTacticalInfo() {
        return tacticalInfo;
    }

    public PlayStyleInfo getPlayStyleInfo() {
        return playStyleInfo;
    }

    public Stats getBaseStats() {
        return baseStats;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Champion champion = (Champion) o;
        return id == champion.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
