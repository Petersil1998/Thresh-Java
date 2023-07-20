package net.petersil98.thresh.model;

import com.fasterxml.jackson.databind.type.TypeFactory;
import net.petersil98.stcommons.constants.LeaguePlatform;
import net.petersil98.stcommons.constants.RankedQueue;
import net.petersil98.stcommons.model.league.RankEntry;
import net.petersil98.thresh.http.LoLAPI;

import java.util.List;

public class PlayerRanks {

    private final String summonerId;
    private final LeaguePlatform platform;
    private RankEntry rankSoloDuo, rankFlex5v5;

    public PlayerRanks(String summonerId, LeaguePlatform platform) {
        this.summonerId = summonerId;
        this.platform = platform;
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
