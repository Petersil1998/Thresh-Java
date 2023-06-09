package net.petersil98.thresh.model.league;

import com.fasterxml.jackson.databind.type.TypeFactory;
import net.petersil98.thresh.constant.RankedDivision;
import net.petersil98.thresh.constant.RankedQueue;
import net.petersil98.thresh.constant.RankedTier;
import net.petersil98.thresh.http.RiotAPIRequest;

import java.util.List;
import java.util.Map;

public class League {

    private RankedTier tier;
    private String leagueId;
    private RankedQueue queue;
    private String name;
    private List<LeagueEntry> entries;

    public static League getLeagueById(String id) {
        return RiotAPIRequest.requestLoLLeagueEndpoint("leagues/" + id, League.class);
    }

    public static League getMasterLeague(RankedQueue queue) {
        if(queue == RankedQueue.RANKED_TFT) {
            return null;
        }
        return RiotAPIRequest.requestLoLLeagueEndpoint("masterleagues/by-queue/" + queue.name(), League.class);
    }

    public static League getGrandmasterLeague(RankedQueue queue) {
        if(queue == RankedQueue.RANKED_TFT) {
            return null;
        }
        return RiotAPIRequest.requestLoLLeagueEndpoint("grandmasterleagues/by-queue/" + queue.name(), League.class);
    }

    public static League getChallengerLeague(RankedQueue queue) {
        if(queue == RankedQueue.RANKED_TFT) {
            return null;
        }
        return RiotAPIRequest.requestLoLLeagueEndpoint("challengerleagues/by-queue/" + queue.name(), League.class);
    }

    public static List<RankEntry> getRankEntries(RankedDivision division, RankedTier tier, RankedQueue queue) {
        return getRankEntries(division, tier, queue, 1);
    }

    public static List<RankEntry> getRankEntries(RankedDivision division, RankedTier tier, RankedQueue queue, int pageNumber) {
        if(queue == RankedQueue.RANKED_TFT) {
            return null;
        }
        return RiotAPIRequest.requestLoLLeagueEndpoint(String.format("entries/%s/%s/%s", queue.name(), tier.name(), division.name()),
                TypeFactory.defaultInstance().constructCollectionType(List.class, RankEntry.class), Map.of("page", String.valueOf(pageNumber)));
    }

    public RankedTier getTier() {
        return tier;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public RankedQueue getQueue() {
        return queue;
    }

    public String getName() {
        return name;
    }

    public List<LeagueEntry> getEntries() {
        return entries;
    }
}
