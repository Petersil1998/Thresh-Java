package net.petersil98.thresh.model.summoner;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.type.TypeFactory;
import net.petersil98.thresh.constant.RankedQueue;
import net.petersil98.thresh.http.RiotAPIRequest;
import net.petersil98.thresh.model.game.match.MatchDetails;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Summoner {

    private String id;

    private String accountId;

    private String puuid;

    private String name;

    @JsonProperty(value = "profileIconId")
    private int profileIcon;

    private long revisionDate;

    private int summonerLevel;

    private int totalMasteryPoints = -1;

    private List<ChampionMastery> championMasteries;

    private Rank rankSoloDuo, rankFlex5v5;

    private Account account;

    public Summoner() {}

    public static Summoner getSummonerByName(String summonerName){
        return RiotAPIRequest.requestLoLSummonerEndpoint("summoners/by-name/" + URLEncoder.encode(summonerName, StandardCharsets.UTF_8), Summoner.class);
    }

    public static Summoner getSummonerByAccountID(String accountID){
        return RiotAPIRequest.requestLoLSummonerEndpoint("summoners/by-account/" + accountID, Summoner.class);
    }

    public static Summoner getSummonerByPUUID(String puuid){
        return RiotAPIRequest.requestLoLSummonerEndpoint("summoners/by-puuid/" + puuid, Summoner.class);
    }

    public static Summoner getSummonerByID(String id){
        return RiotAPIRequest.requestLoLSummonerEndpoint("summoners/" + id, Summoner.class);
    }

    public String getId() {
        return this.id;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public String getPuuid() {
        return this.puuid;
    }

    public String getName() {
        return this.name;
    }

    public int getProfileIcon() {
        return this.profileIcon;
    }

    public long getRevisionDate() {
        return this.revisionDate;
    }

    public int getSummonerLevel() {
        return this.summonerLevel;
    }

    public int getTotalMasteryPoints() {
        if(this.totalMasteryPoints == -1) {
            this.totalMasteryPoints = RiotAPIRequest.requestLoLChampionMasteryEndpoint("scores/by-summoner/" + this.id, Integer.class);
        }
        return this.totalMasteryPoints;
    }

    public List<ChampionMastery> getChampionMasteries() {
        if(this.championMasteries == null) {
            this.championMasteries = RiotAPIRequest.requestLoLChampionMasteryEndpoint("champion-masteries/by-summoner/" + this.id, TypeFactory.defaultInstance().constructCollectionType(List.class, ChampionMastery.class));
        }
        return this.championMasteries;
    }

    public int getTotalMasteryPointsCombined() {
        return this.getChampionMasteries().stream().mapToInt(ChampionMastery::getChampionPoints).sum();
    }

    public Rank getRankSoloDuo() {
        this.initRanks();
        return this.rankSoloDuo;
    }

    public Rank getRankFlex5v5() {
        this.initRanks();
        return this.rankFlex5v5;
    }

    public Account getAccount() {
        if(this.account == null){
            this.account = RiotAPIRequest.requestRiotAccountEndpoint("accounts/by-puuid/" + this.puuid, Account.class);
        }
        return this.account;
    }

    public List<MatchDetails> getMatchHistory(Map<String, String> filter) {
        return MatchDetails.getMatchHistory(this, filter);
    }

    private void initRanks() {
        if(this.rankSoloDuo == null || this.rankFlex5v5 == null) {
            List<Rank> ranks = RiotAPIRequest.requestLoLLeagueEndpoint("entries/by-summoner/" + this.id, TypeFactory.defaultInstance().constructCollectionType(List.class, Rank.class));
            this.rankSoloDuo = ranks.stream().filter(rank -> rank.getQueueType().equals(RankedQueue.SOLO_DUO.toString())).findFirst().orElse(Rank.UNRANKED);
            this.rankFlex5v5 = ranks.stream().filter(rank -> rank.getQueueType().equals(RankedQueue.FLEX.toString())).findFirst().orElse(Rank.UNRANKED);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Summoner summoner = (Summoner) o;
        return Objects.equals(puuid, summoner.puuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(puuid);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
