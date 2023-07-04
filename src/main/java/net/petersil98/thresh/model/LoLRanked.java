package net.petersil98.thresh.model;

import com.fasterxml.jackson.databind.type.TypeFactory;
import net.petersil98.core.constant.Platform;
import net.petersil98.core.constant.RankedDivision;
import net.petersil98.core.constant.RankedQueue;
import net.petersil98.core.constant.RankedTier;
import net.petersil98.core.http.RiotAPI;
import net.petersil98.stcommons.model.league.League;
import net.petersil98.stcommons.model.league.RankEntry;

import java.util.List;
import java.util.Map;

public class LoLRanked {
    private final String summonerId;
    private final Platform platform;
    private RankEntry rankSoloDuo, rankFlex5v5;

    private LoLRanked(String summonerId, Platform platform) {
        this.summonerId = summonerId;
        this.platform = platform;
    }

    public static LoLRanked getLoLRanksOfSummoner(String summonerId, Platform platform) {
        return new LoLRanked(summonerId, platform);
    }

    public static League getLeagueById(String id, Platform platform) {
        return RiotAPI.requestLoLLeagueEndpoint("leagues/", id, platform, League.class);
    }

    public static League getMasterLeague(RankedQueue queue, Platform platform) {
        return RiotAPI.requestLoLLeagueEndpoint("masterleagues/by-queue/", queue.getJsonPropertyValue(), platform, League.class);
    }

    public static League getGrandmasterLeague(RankedQueue queue, Platform platform) {
        return RiotAPI.requestLoLLeagueEndpoint("grandmasterleagues/by-queue/", queue.getJsonPropertyValue(), platform, League.class);
    }

    public static League getChallengerLeague(RankedQueue queue, Platform platform) {
        return RiotAPI.requestLoLLeagueEndpoint("challengerleagues/by-queue/", queue.getJsonPropertyValue(), platform, League.class);
    }

    public static List<RankEntry> getRankEntries(RankedDivision division, RankedTier tier, RankedQueue queue, Platform platform) {
        return getRankEntries(division, tier, queue, platform, 1);
    }

    public static List<RankEntry> getRankEntries(RankedDivision division, RankedTier tier, RankedQueue queue, Platform platform, int pageNumber) {
        return RiotAPI.requestLoLLeagueEndpoint("entries/", String.format("%s/%s/%s", queue.getJsonPropertyValue(), tier.name(), division.name()),
                platform, TypeFactory.defaultInstance().constructCollectionType(List.class, RankEntry.class), Map.of("page", String.valueOf(pageNumber)));
    }
    private void initRanks() {
        if(this.rankSoloDuo == null || this.rankFlex5v5 == null) {
            List<RankEntry> ranks = RiotAPI.requestLoLLeagueEndpoint("entries/by-summoner/", this.summonerId, this.platform, TypeFactory.defaultInstance().constructCollectionType(List.class, RankEntry.class));
            this.rankSoloDuo = ranks.stream().filter(rank -> rank.getQueueType().equals(RankedQueue.SOLO_DUO)).findFirst().orElse(RankEntry.UNRANKED);
            this.rankFlex5v5 = ranks.stream().filter(rank -> rank.getQueueType().equals(RankedQueue.FLEX)).findFirst().orElse(RankEntry.UNRANKED);
        }
    }

    public RankEntry getRankSoloDuo() {
        this.initRanks();
        return this.rankSoloDuo;
    }

    public RankEntry getRankFlex5v5() {
        this.initRanks();
        return this.rankFlex5v5;
    }
}
