package net.petersil98.thresh.model.game.match.timeline.event;

public class ObjectiveBountyFinish extends TimelineEvent {

    private final int teamId;

    public ObjectiveBountyFinish(long timestamp, EventType type, int teamId) {
        super(timestamp, type);
        this.teamId = teamId;
    }

    public int getTeamId() {
        return teamId;
    }
}
