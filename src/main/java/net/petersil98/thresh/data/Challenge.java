package net.petersil98.thresh.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.petersil98.stcommons.constants.RankedTier;
import net.petersil98.thresh.model.Deserializers;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonDeserialize(using = Deserializers.ChallengeDeserializer.class)
public class Challenge {

    private final int id;
    private final String name;
    private final String description;
    private final String shortDescription;
    private final boolean hasLeaderboard;
    private final Map<RankedTier, String> levelToIconPath;
    private final Map<RankedTier, Threshold> thresholds;

    public Challenge(int id, String name, String description, String shortDescription, boolean hasLeaderboard, Map<RankedTier, String> levelToIconPath, Map<RankedTier, Threshold> thresholds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.shortDescription = shortDescription;
        this.hasLeaderboard = hasLeaderboard;
        this.levelToIconPath = levelToIconPath;
        this.thresholds = thresholds;
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

    public String getShortDescription() {
        return shortDescription;
    }

    public boolean isHasLeaderboard() {
        return hasLeaderboard;
    }

    public Map<RankedTier, String> getLevelToIconPath() {
        return levelToIconPath;
    }

    public Map<RankedTier, Threshold> getThresholds() {
        return thresholds;
    }

    public static class Threshold {
        private int value;
        private List<Reward> rewards;

        public int getValue() {
            return value;
        }

        public List<Reward> getRewards() {
            return rewards;
        }
    }

    public static class Reward {
        private String category;
        private int quantity;
        private String title;

        public String getCategory() {
            return category;
        }

        public int getQuantity() {
            return quantity;
        }

        public String getTitle() {
            return title;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Challenge challenge = (Challenge) o;
        return id == challenge.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
