package net.petersil98.thresh.model.summoner;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.petersil98.thresh.data.champion.Champion;
import net.petersil98.thresh.model.Deserializers;

import java.util.Objects;

@JsonDeserialize(using = Deserializers.ChampionMasteryDeserializer.class)
public class ChampionMastery {

    private boolean chestGranted;
    private int championLevel;
    private int championPoints;
    private Champion champion;
    private int championPointsUntilNextLevel;
    private int lastPlayTime;
    private int tokensEarned;
    private int championPointsSinceLastLevel;

    public ChampionMastery(boolean chestGranted, int championLevel, int championPoints, Champion champion, int championPointsUntilNextLevel, int lastPlayTime, int tokensEarned, int championPointsSinceLastLevel) {
        this.chestGranted = chestGranted;
        this.championLevel = championLevel;
        this.championPoints = championPoints;
        this.champion = champion;
        this.championPointsUntilNextLevel = championPointsUntilNextLevel;
        this.lastPlayTime = lastPlayTime;
        this.tokensEarned = tokensEarned;
        this.championPointsSinceLastLevel = championPointsSinceLastLevel;
    }

    public ChampionMastery() {}

    public boolean isChestGranted() {
        return this.chestGranted;
    }

    public void setChestGranted(boolean chestGranted) {
        this.chestGranted = chestGranted;
    }

    public int getChampionLevel() {
        return this.championLevel;
    }

    public void setChampionLevel(int championLevel) {
        this.championLevel = championLevel;
    }

    public int getChampionPoints() {
        return this.championPoints;
    }

    public void setChampionPoints(int championPoints) {
        this.championPoints = championPoints;
    }

    public Champion getChampion() {
        return this.champion;
    }

    public void setChampion(Champion champion) {
        this.champion = champion;
    }

    public int getChampionPointsUntilNextLevel() {
        return this.championPointsUntilNextLevel;
    }

    public void setChampionPointsUntilNextLevel(int championPointsUntilNextLevel) {
        this.championPointsUntilNextLevel = championPointsUntilNextLevel;
    }

    public int getLastPlayTime() {
        return this.lastPlayTime;
    }

    public void setLastPlayTime(int lastPlayTime) {
        this.lastPlayTime = lastPlayTime;
    }

    public int getTokensEarned() {
        return this.tokensEarned;
    }

    public void setTokensEarned(int tokensEarned) {
        this.tokensEarned = tokensEarned;
    }

    public int getChampionPointsSinceLastLevel() {
        return this.championPointsSinceLastLevel;
    }

    public void setChampionPointsSinceLastLevel(int championPointsSinceLastLevel) {
        this.championPointsSinceLastLevel = championPointsSinceLastLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChampionMastery that = (ChampionMastery) o;
        return this.chestGranted == that.chestGranted && this.championLevel == that.championLevel && this.championPoints == that.championPoints && this.championPointsUntilNextLevel == that.championPointsUntilNextLevel && this.lastPlayTime == that.lastPlayTime && this.tokensEarned == that.tokensEarned && this.championPointsSinceLastLevel == that.championPointsSinceLastLevel && Objects.equals(this.champion, that.champion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.chestGranted, this.championLevel, this.championPoints, this.champion, this.championPointsUntilNextLevel, this.lastPlayTime, this.tokensEarned, this.championPointsSinceLastLevel);
    }

    @Override
    public String toString() {
        return "ChampionMastery{" +
                "chestGranted=" + chestGranted +
                ", championLevel=" + championLevel +
                ", championPoints=" + championPoints +
                ", champion=" + champion +
                ", championPointsUntilNextLevel=" + championPointsUntilNextLevel +
                ", lastPlayTime=" + lastPlayTime +
                ", tokensEarned=" + tokensEarned +
                ", championPointsSinceLastLevel=" + championPointsSinceLastLevel +
                '}';
    }
}
