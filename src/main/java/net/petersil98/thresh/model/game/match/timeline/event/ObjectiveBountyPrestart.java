package net.petersil98.thresh.model.game.match.timeline.event;

public class ObjectiveBountyPrestart extends TimelineEvent {

    private final long actualStartTime;
    private final int teamId;

    public ObjectiveBountyPrestart(long timestamp, EventType type, long actualStartTime, int teamId) {
        super(timestamp, type);
        this.actualStartTime = actualStartTime;
        this.teamId = teamId;
    }

    public long getActualStartTime() {
        return actualStartTime;
    }

    public int getTeamId() {
        return teamId;
    }
}
