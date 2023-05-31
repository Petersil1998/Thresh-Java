package net.petersil98.thresh.model.game.match;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.type.TypeFactory;
import net.petersil98.thresh.collection.QueueTypes;
import net.petersil98.thresh.constant.Platform;
import net.petersil98.thresh.data.Map;
import net.petersil98.thresh.data.QueueType;
import net.petersil98.thresh.http.RiotAPIRequest;
import net.petersil98.thresh.model.Deserializers;
import net.petersil98.thresh.model.game.match.participant.MatchParticipant;
import net.petersil98.thresh.model.game.match.timeline.Timeline;
import net.petersil98.thresh.model.summoner.Summoner;

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
    private final Platform platform;
    private final QueueType queueType;
    private final List<Team> teams;
    private final String tournamentCode;
    private Timeline timeline;

    public MatchDetails(long gameCreation, int gameDuration, long gameEndTimestamp, long gameId, String gameMode, String gameName, long gameStartTimestamp, String gameType, String gameVersion, Map map, List<MatchParticipant> participants, Platform platform, QueueType queueType, List<Team> teams, String tournamentCode) {
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

    public static MatchDetails getMatchDetails(String matchId) {
        return RiotAPIRequest.requestLoLMatchEndpoint("matches/" + matchId, MatchDetails.class);
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

    public Platform getPlatform() {
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

    public static List<MatchDetails> getMatchHistory(Summoner summoner, java.util.Map<String, String> filter) {
        return getMatchHistory(summoner.getPuuid(), filter);
    }

    public static List<MatchDetails> getMatchHistory(String puuid, java.util.Map<String, String> filter) {
        validateFilter(filter);
        List<String> matchIds = RiotAPIRequest.requestLoLMatchEndpoint("matches/by-puuid/" + puuid + "/ids", TypeFactory.defaultInstance().constructCollectionType(List.class, String.class), filter);
        return matchIds.stream().map(MatchDetails::getMatchDetails).toList();
    }

    private static void validateFilter(java.util.Map<String, String> filter) {
        filter.forEach((filterName, arg) -> {
            switch (filterName) {
                case "endTime", "start", "startTime" -> {
                    try {
                        long time = Long.parseLong(arg);
                        if (time < 0) throw new InvalidFilterException(arg + " cannot be negative");
                    } catch (NumberFormatException e) {
                        throw new InvalidFilterException("Filter \"" + arg + "\" isn't a number", e);
                    }
                }
                case "queue" -> {
                    try {
                        int queueId = Integer.parseInt(arg);
                        if (QueueTypes.getQueueTypes().stream().noneMatch(queueType -> queueType.getId() == queueId)) {
                            throw new InvalidFilterException("No queue type found with ID \"" + arg + "\"");
                        }
                    } catch (NumberFormatException e) {
                        throw new InvalidFilterException("Filter \"" + arg + "\" isn't a number", e);
                    }
                }
                case "type" -> {}
                case "count" -> {
                    try {
                        int count = Integer.parseInt(arg);
                        if (count < 0 || count > 100)
                            throw new InvalidFilterException("count must be between 0 and 100");
                    } catch (NumberFormatException e) {
                        throw new InvalidFilterException("Filter \"" + arg + "\" isn't a number", e);
                    }
                }
                default -> throw new InvalidFilterException("Unknown filter \"" + filterName + "\" for match history");
            }
        });
    }

    public Timeline getTimeline() {
        if(this.timeline == null) this.timeline = Timeline.getTimelinesForMatch(this.platform.toString().toUpperCase()+ "_" + this.gameId);
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
