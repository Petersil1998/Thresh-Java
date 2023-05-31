package net.petersil98.thresh.model.summoner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rank {

    public static final Rank UNRANKED = new Rank();

    private String leagueId;

    private String queueType;

    private String tier;
    private String rank;

    private int leaguePoints;
    private int wins;
    private int losses;
    private boolean veteran;
    private boolean inactive;
    private boolean freshBlood;
    private boolean hotStreak;

    public Rank() {
    }

    public String getLeagueId() {
        return this.leagueId;
    }

    public String getQueueType() {
        return this.queueType;
    }

    public String getTier() {
        return this.tier;
    }

    public String getRank() {
        return this.rank;
    }

    public int getLeaguePoints() {
        return this.leaguePoints;
    }

    public int getWins() {
        return this.wins;
    }

    public int getLosses() {
        return this.losses;
    }

    public boolean isVeteran() {
        return this.veteran;
    }

    public boolean isInactive() {
        return this.inactive;
    }

    public boolean isFreshBlood() {
        return this.freshBlood;
    }

    public boolean isHotStreak() {
        return this.hotStreak;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "leagueId='" + leagueId + '\'' +
                ", queueType='" + queueType + '\'' +
                ", tier='" + tier + '\'' +
                ", rank='" + rank + '\'' +
                ", leaguePoints=" + leaguePoints +
                ", wins=" + wins +
                ", losses=" + losses +
                ", veteran=" + veteran +
                ", inactive=" + inactive +
                ", freshBlood=" + freshBlood +
                ", hotStreak=" + hotStreak +
                '}';
    }
}
