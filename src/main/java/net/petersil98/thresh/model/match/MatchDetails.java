package net.petersil98.thresh.model.match;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.type.TypeFactory;
import net.petersil98.stcommons.constants.LeaguePlatform;
import net.petersil98.stcommons.constants.LeagueRegion;
import net.petersil98.thresh.data.Map;
import net.petersil98.thresh.data.QueueType;
import net.petersil98.thresh.http.LoLAPI;
import net.petersil98.thresh.model.Deserializers;
import net.petersil98.thresh.model.match.participant.MatchParticipant;
import net.petersil98.thresh.model.match.timeline.Timeline;
import net.petersil98.thresh.util.Util;

import java.util.List;
import java.util.Objects;

@JsonDeserialize(using = Deserializers.MatchDetailsDeserializer.class)
public class MatchDetails {

    private final long gameCreation;
    private final int gameDuration;
    private final long gameEndTimestamp;
    private final long gameId;
    private final String gameMode;
    private final String gameName;
    private final long gameStartTimestamp;
    private final String gameType;
    private final String gameVersion;
    private final Map map;
    private final List<MatchParticipant> participants;
    private final LeaguePlatform platform;
    private final QueueType queueType;
    private final List<Team> teams;
    private final String tournamentCode;
    private Timeline timeline;

    public MatchDetails(long gameCreation, int gameDuration, long gameEndTimestamp, long gameId, String gameMode, String gameName, long gameStartTimestamp, String gameType, String gameVersion, Map map, List<MatchParticipant> participants, LeaguePlatform platform, QueueType queueType, List<Team> teams, String tournamentCode) {
        this.gameCreation = gameCreation;
        this.gameDuration = gameDuration;
        this.gameEndTimestamp = gameEndTimestamp;
        this.gameId = gameId;
        this.gameMode = gameMode;
        this.gameName = gameName;
        this.gameStartTimestamp = gameStartTimestamp;
        this.gameType = gameType;
        this.gameVersion = gameVersion;
        this.map = map;
        this.participants = participants;
        this.platform = platform;
        this.queueType = queueType;
        this.teams = teams;
        this.tournamentCode = tournamentCode;
    }

    public static MatchDetails getMatchDetails(String matchId, LeagueRegion region) {
        return LoLAPI.requestLoLMatchEndpoint("matches/", matchId, region, MatchDetails.class);
    }

    public static List<MatchDetails> getMatchHistory(String puuid, LeagueRegion region, java.util.Map<String, String> filter) {
        Util.validateFilter(filter);
        List<String> matchIds = LoLAPI.requestLoLMatchEndpoint("matches/by-puuid/", puuid + "/ids", region, TypeFactory.defaultInstance().constructCollectionType(List.class, String.class), filter);
        return matchIds.stream().map(matchId -> MatchDetails.getMatchDetails(matchId, region)).toList();
    }

    public long getGameCreation() {
        return gameCreation;
    }

    public int getGameDuration() {
        return gameDuration;
    }

    public long getGameEndTimestamp() {
        return gameEndTimestamp;
    }

    public long getGameId() {
        return gameId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public String getGameName() {
        return gameName;
    }

    public long getGameStartTimestamp() {
        return gameStartTimestamp;
    }

    public String getGameType() {
        return gameType;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public Map getMap() {
        return map;
    }

    public List<MatchParticipant> getParticipants() {
        return participants;
    }

    public LeaguePlatform getPlatform() {
        return platform;
    }

    public QueueType getQueueType() {
        return queueType;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public String getTournamentCode() {
        return tournamentCode;
    }

    public Timeline getTimeline() {
        if(this.timeline == null) this.timeline = Timeline.getTimelinesForMatch(this.platform.toString().toUpperCase()+ "_" + this.gameId, LeagueRegion.byPlatform(this.platform));
        return this.timeline;
    }

    @Override
    public String toString() {
        return "MatchDetails{" +
                "gameCreation=" + gameCreation +
                ", gameDuration=" + gameDuration +
                ", gameEndTimestamp=" + gameEndTimestamp +
                ", gameId=" + gameId +
                ", gameMode='" + gameMode + '\'' +
                ", gameName='" + gameName + '\'' +
                ", gameStartTimestamp=" + gameStartTimestamp +
                ", gameType='" + gameType + '\'' +
                ", gameVersion='" + gameVersion + '\'' +
                ", map=" + map +
                ", participants=" + participants +
                ", platform=" + platform +
                ", queueType=" + queueType +
                ", teams=" + teams +
                ", tournamentCode='" + tournamentCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchDetails that = (MatchDetails) o;
        return gameId == that.gameId && platform == that.platform;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, platform);
    }
}
