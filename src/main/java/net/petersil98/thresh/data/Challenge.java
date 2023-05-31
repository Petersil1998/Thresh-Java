package net.petersil98.thresh.data;

import net.petersil98.thresh.constant.RankedTier;

import java.util.List;
import java.util.Map;

public class Challenge {

    private int id;
    private String name;
    private String description;
    private String shortDescription;
    private boolean hasLeaderboard;
    private Map<RankedTier, String> levelToIconPath;
    private Map<RankedTier, Threshold> thresholds;

    public Challenge(int id, String name, String description, String shortDescription, boolean hasLeaderboard, Map<RankedTier, String> levelToIconPath, Map<RankedTier, Threshold> thresholds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.shortDescription = shortDescription;
        this.hasLeaderboard = hasLeaderboard;
        this.levelToIconPath = levelToIconPath;
        this.thresholds = thresholds;
    }

    public Challenge() {}

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

    static class Threshold {
        private int value;
        private List<Reward> rewards;

        public Threshold(int value, List<Reward> rewards) {
            this.value = value;
            this.rewards = rewards;
        }

        public Threshold() {}

        public int getValue() {
            return value;
        }

        public List<Reward> getRewards() {
            return rewards;
        }
    }

    static class Reward {
        private String category;
        private int quantity;
        private  String title;

        private Reward(String category, int quantity, String title) {
            this.category = category;
            this.quantity = quantity;
            this.title = title;
        }

        private Reward() {}

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
}
