package net.petersil98.thresh.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.type.TypeFactory;
import net.petersil98.core.constant.Platform;
import net.petersil98.thresh.data.champion.Champion;
import net.petersil98.thresh.http.LoLAPI;

import java.util.List;
import java.util.Objects;

public class ChampionMasteries {

    private final String summonerId;
    private final Platform platform;
    private List<Mastery> masteries;
    private int totalMasteryPoints = -1;

    private ChampionMasteries(String summonerId, Platform platform) {
        this.summonerId = summonerId;
        this.platform = platform;
    }

    public static ChampionMasteries getChampionMasteriesOfSummoner(String summonerId, Platform platform) {
        return new ChampionMasteries(summonerId, platform);
    }

    public int getTotalMasteryPoints() {
        if(this.totalMasteryPoints == -1) {
            this.totalMasteryPoints = LoLAPI.requestLoLChampionMasteryEndpoint("scores/by-summoner/", this.summonerId, this.platform, Integer.class);
        }
        return this.totalMasteryPoints;
    }

    public List<Mastery> getChampionMasteries() {
        if(this.masteries == null) {
            this.masteries = LoLAPI.requestLoLChampionMasteryEndpoint("champion-masteries/by-summoner/", this.summonerId, this.platform, TypeFactory.defaultInstance().constructCollectionType(List.class, Mastery.class));
        }
        return this.masteries;
    }

    public int getTotalMasteryPointsCombined() {
        return this.getChampionMasteries().stream().mapToInt(Mastery::getChampionPoints).sum();
    }

    @JsonDeserialize(using = Deserializers.ChampionMasteryDeserializer.class)
    public static class Mastery {

        private boolean chestGranted;
        private int championLevel;
        private int championPoints;
        private Champion champion;
        private int championPointsUntilNextLevel;
        private int lastPlayTime;
        private int tokensEarned;
        private int championPointsSinceLastLevel;

        public Mastery(boolean chestGranted, int championLevel, int championPoints, Champion champion, int championPointsUntilNextLevel, int lastPlayTime, int tokensEarned, int championPointsSinceLastLevel) {
            this.chestGranted = chestGranted;
            this.championLevel = championLevel;
            this.championPoints = championPoints;
            this.champion = champion;
            this.championPointsUntilNextLevel = championPointsUntilNextLevel;
            this.lastPlayTime = lastPlayTime;
            this.tokensEarned = tokensEarned;
            this.championPointsSinceLastLevel = championPointsSinceLastLevel;
        }

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
            Mastery that = (Mastery) o;
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
}
