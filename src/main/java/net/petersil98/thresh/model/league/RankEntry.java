package net.petersil98.thresh.model.league;

import net.petersil98.thresh.constant.RankedDivision;
import net.petersil98.thresh.constant.RankedQueue;
import net.petersil98.thresh.constant.RankedTier;
import net.petersil98.thresh.model.summoner.Summoner;

public class RankEntry {

    public static final RankEntry UNRANKED = new RankEntry();

    private String leagueId;
    private RankedQueue queueType;
    private RankedTier tier;
    private RankedDivision rank;
    private int leaguePoints;
    private int wins;
    private int losses;
    private boolean veteran;
    private boolean inactive;
    private boolean freshBlood;
    private boolean hotStreak;
    private MiniSeries miniSeries;
    private String summonerId;
    private String summonerName;
    private Summoner summoner;

    public RankEntry() {
    }

    public String getLeagueId() {
        return this.leagueId;
    }

    public RankedQueue getQueueType() {
        return this.queueType;
    }

    public RankedTier getTier() {
        return this.tier;
    }

    public RankedDivision getRank() {
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

    public MiniSeries getMiniSeries() {
        return this.miniSeries;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public Summoner getSummoner() {
        if(this.summoner == null) this.summoner = Summoner.getSummonerByID(this.getSummonerId());
        return this.summoner;
    }
}
