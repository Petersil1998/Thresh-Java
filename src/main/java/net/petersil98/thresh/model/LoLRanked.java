package net.petersil98.thresh.model;

import com.fasterxml.jackson.databind.type.TypeFactory;
import net.petersil98.core.constant.RankedDivision;
import net.petersil98.core.constant.RankedQueue;
import net.petersil98.core.constant.RankedTier;
import net.petersil98.core.http.RiotAPIRequest;
import net.petersil98.stcommons.model.league.League;
import net.petersil98.stcommons.model.league.RankEntry;

import java.util.List;
import java.util.Map;

public class LoLRanked {
    private final String summonerId;
    private RankEntry rankSoloDuo, rankFlex5v5;

    private LoLRanked(String summonerId) {
        this.summonerId = summonerId;
    }

    public static LoLRanked getLoLRanksOfSummoner(String summonerId) {
        return new LoLRanked(summonerId);
    }

    public static League getLeagueById(String id) {
        return RiotAPIRequest.requestLoLLeagueEndpoint("leagues/" + id, League.class);
    }

    public static League getMasterLeague(RankedQueue queue) {
        return RiotAPIRequest.requestLoLLeagueEndpoint("masterleagues/by-queue/" + queue.getJsonPropertyValue(), League.class);
    }

    public static League getGrandmasterLeague(RankedQueue queue) {
        return RiotAPIRequest.requestLoLLeagueEndpoint("grandmasterleagues/by-queue/" + queue.getJsonPropertyValue(), League.class);
    }

    public static League getChallengerLeague(RankedQueue queue) {
        return RiotAPIRequest.requestLoLLeagueEndpoint("challengerleagues/by-queue/" + queue.getJsonPropertyValue(), League.class);
    }

    public static List<RankEntry> getRankEntries(RankedDivision division, RankedTier tier, RankedQueue queue) {
        return getRankEntries(division, tier, queue, 1);
    }

    public static List<RankEntry> getRankEntries(RankedDivision division, RankedTier tier, RankedQueue queue, int pageNumber) {
        return RiotAPIRequest.requestLoLLeagueEndpoint(String.format("entries/%s/%s/%s", queue.getJsonPropertyValue(), tier.name(), division.name()),
                TypeFactory.defaultInstance().constructCollectionType(List.class, RankEntry.class), Map.of("page", String.valueOf(pageNumber)));
    }
    private void initRanks() {
        if(this.rankSoloDuo == null || this.rankFlex5v5 == null) {
            List<RankEntry> ranks = RiotAPIRequest.requestLoLLeagueEndpoint("entries/by-summoner/" + this.summonerId, TypeFactory.defaultInstance().constructCollectionType(List.class, RankEntry.class));
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
