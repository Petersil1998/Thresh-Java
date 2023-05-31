package net.petersil98.thresh.model.game.match.timeline.event;

public class GameEnd extends TimelineEvent {

    private final long gameId;
    private final long realTimestamp;
    private final int winningTeam;

    public GameEnd(long timestamp, EventType type, long gameId, long realTimestamp, int winningTeam) {
        super(timestamp, type);
        this.gameId = gameId;
        this.realTimestamp = realTimestamp;
        this.winningTeam = winningTeam;
    }

    public long getGameId() {
        return gameId;
    }

    public long getRealTimestamp() {
        return realTimestamp;
    }

    public int getWinningTeam() {
        return winningTeam;
    }
}
