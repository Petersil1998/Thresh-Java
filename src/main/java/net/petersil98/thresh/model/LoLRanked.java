package net.petersil98.thresh.model;

import com.fasterxml.jackson.databind.type.TypeFactory;
import net.petersil98.stcommons.constants.LeaguePlatform;
import net.petersil98.stcommons.constants.RankedDivision;
import net.petersil98.stcommons.constants.RankedQueue;
import net.petersil98.stcommons.constants.RankedTier;
import net.petersil98.stcommons.model.league.League;
import net.petersil98.stcommons.model.league.RankEntry;
import net.petersil98.thresh.http.LoLAPI;

import java.util.List;
import java.util.Map;

public class LoLRanked {

    public static PlayerRanks getLoLRanksOfSummoner(String summonerId, LeaguePlatform platform) {
        return new PlayerRanks(summonerId, platform);
    }

    public static League getLeagueById(String id, LeaguePlatform platform) {
        return LoLAPI.requestLoLLeagueEndpoint("leagues/", id, platform, League.class);
    }

    public static League getMasterLeague(RankedQueue queue, LeaguePlatform platform) {
        return LoLAPI.requestLoLLeagueEndpoint("masterleagues/by-queue/", queue.getJsonPropertyValue(), platform, League.class);
    }

    public static League getGrandmasterLeague(RankedQueue queue, LeaguePlatform platform) {
        return LoLAPI.requestLoLLeagueEndpoint("grandmasterleagues/by-queue/", queue.getJsonPropertyValue(), platform, League.class);
    }

    public static League getChallengerLeague(RankedQueue queue, LeaguePlatform platform) {
        return LoLAPI.requestLoLLeagueEndpoint("challengerleagues/by-queue/", queue.getJsonPropertyValue(), platform, League.class);
    }

    public static List<RankEntry> getRankEntries(RankedDivision division, RankedTier tier, RankedQueue queue, LeaguePlatform platform) {
        return getRankEntries(division, tier, queue, platform, 1);
    }

    public static List<RankEntry> getRankEntries(RankedDivision division, RankedTier tier, RankedQueue queue, LeaguePlatform platform, int pageNumber) {
        return LoLAPI.requestLoLLeagueEndpoint("entries/", String.format("%s/%s/%s", queue.getJsonPropertyValue(), tier.name(), division.name()),
                platform, TypeFactory.defaultInstance().constructCollectionType(List.class, RankEntry.class), Map.of("page", String.valueOf(pageNumber)));
    }
}
