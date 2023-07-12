package net.petersil98.thresh.model.spectator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.petersil98.core.constant.Constants;
import net.petersil98.core.constant.Platform;
import net.petersil98.thresh.data.Map;
import net.petersil98.thresh.data.QueueType;
import net.petersil98.thresh.http.LoLAPI;
import net.petersil98.thresh.model.Deserializers;

import java.util.List;
import java.util.Objects;

@JsonDeserialize(using = Deserializers.ActiveGameDeserializer.class)
public class ActiveGame {

    private final long gameId;
    private final Map map;
    private final String gameMode;
    private final String gameType;
    private final QueueType queueType;
    private final List<Participant> blueSideTeam;
    private final List<Participant> redSideTeam;
    private final int teamsize;
    private final List<Ban> bans;
    private final String spectatorKey;
    private final Platform platform;
    private final int startTime;
    private final int duration;

    public ActiveGame(long gameId, Map map, String gameMode, String gameType, QueueType queueType, List<Participant> blueSideTeam, List<Participant> redSideTeam, int teamsize, List<Ban> bans, String spectatorKey, Platform platform, int startTime, int duration) {
        this.gameId = gameId;
        this.map = map;
        this.gameMode = gameMode;
        this.gameType = gameType;
        this.queueType = queueType;
        this.blueSideTeam = blueSideTeam;
        this.redSideTeam = redSideTeam;
        this.teamsize = teamsize;
        this.bans = bans;
        this.spectatorKey = spectatorKey;
        this.platform = platform;
        this.startTime = startTime;
        this.duration = duration;
    }

    public static ActiveGame ofSummoner(String summonerID, Platform platform) {
        return LoLAPI.requestLoLSpectatorEndpoint("active-games/by-summoner/", summonerID, platform, ActiveGame.class);
    }

    public long getGameId() {
        return this.gameId;
    }

    public Map getMap() {
        return this.map;
    }

    public String getGameMode() {
        return this.gameMode;
    }

    public String getGameType() {
        return this.gameType;
    }

    public QueueType getQueueType() {
        return this.queueType;
    }

    public List<Participant> getBlueSideTeam() {
        return this.blueSideTeam;
    }

    public List<Participant> getRedSideTeam() {
        return this.redSideTeam;
    }

    public int getTeamsize() {
        return this.teamsize;
    }

    public List<Ban> getBans() {
        return this.bans;
    }

    public String getSpectatorKey() {
        return this.spectatorKey;
    }

    public Platform getPlatform() {
        return this.platform;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public int getDuration() {
        return this.duration;
    }

    public String getSpectatorCommandWindows(String pathToRiotFolder) {
        String url = Constants.SPECTATOR_URL.replaceAll("\\{platform}", this.platform.toString());
        return String.format("cd /d \"%sRiot Games\\League of Legends\\Game\" & \"League of Legends.exe\" \"spectator %s %s %d %s\" \"-UseRads\"",
                pathToRiotFolder, url, this.getSpectatorKey(), this.getGameId(), this.getPlatform());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActiveGame that = (ActiveGame) o;
        return gameId == that.gameId && Objects.equals(platform, that.platform);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, platform);
    }

    @Override
    public String toString() {
        return "ActiveGame{" +
                "gameId=" + gameId +
                ", map=" + map +
                ", gameMode='" + gameMode + '\'' +
                ", gameType='" + gameType + '\'' +
                ", queueType=" + queueType +
                ", blueSideTeam=" + blueSideTeam +
                ", redSideTeam=" + redSideTeam +
                ", teamsize=" + teamsize +
                ", bans=" + bans +
                ", spectatorKey='" + spectatorKey + '\'' +
                ", platformId='" + platform + '\'' +
                ", startTime=" + startTime +
                ", duration=" + duration +
                '}';
    }
}
