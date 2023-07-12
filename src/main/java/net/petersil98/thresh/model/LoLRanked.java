package net.petersil98.thresh.model;

import com.fasterxml.jackson.databind.type.TypeFactory;
import net.petersil98.core.constant.Platform;
import net.petersil98.stcommons.constants.RankedDivision;
import net.petersil98.stcommons.constants.RankedQueue;
import net.petersil98.stcommons.constants.RankedTier;
import net.petersil98.stcommons.model.league.League;
import net.petersil98.stcommons.model.league.RankEntry;
import net.petersil98.thresh.http.LoLAPI;

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
        return LoLAPI.requestLoLLeagueEndpoint("leagues/", id, platform, League.class);
    }

    public static League getMasterLeague(RankedQueue queue, Platform platform) {
        return LoLAPI.requestLoLLeagueEndpoint("masterleagues/by-queue/", queue.getJsonPropertyValue(), platform, League.class);
    }

    public static League getGrandmasterLeague(RankedQueue queue, Platform platform) {
        return LoLAPI.requestLoLLeagueEndpoint("grandmasterleagues/by-queue/", queue.getJsonPropertyValue(), platform, League.class);
    }

    public static League getChallengerLeague(RankedQueue queue, Platform platform) {
        return LoLAPI.requestLoLLeagueEndpoint("challengerleagues/by-queue/", queue.getJsonPropertyValue(), platform, League.class);
    }

    public static List<RankEntry> getRankEntries(RankedDivision division, RankedTier tier, RankedQueue queue, Platform platform) {
        return getRankEntries(division, tier, queue, platform, 1);
    }

    public static List<RankEntry> getRankEntries(RankedDivision division, RankedTier tier, RankedQueue queue, Platform platform, int pageNumber) {
        return LoLAPI.requestLoLLeagueEndpoint("entries/", String.format("%s/%s/%s", queue.getJsonPropertyValue(), tier.name(), division.name()),
                platform, TypeFactory.defaultInstance().constructCollectionType(List.class, RankEntry.class), Map.of("page", String.valueOf(pageNumber)));
    }
    private void initRanks() {
        if(this.rankSoloDuo == null || this.rankFlex5v5 == null) {
            List<RankEntry> ranks = LoLAPI.requestLoLLeagueEndpoint("entries/by-summoner/", this.summonerId, this.platform, TypeFactory.defaultInstance().constructCollectionType(List.class, RankEntry.class));
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
