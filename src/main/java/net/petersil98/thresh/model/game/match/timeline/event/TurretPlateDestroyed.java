package net.petersil98.thresh.model.game.match.timeline.event;

import net.petersil98.thresh.model.game.match.timeline.Point;
import net.petersil98.thresh.model.game.match.timeline.TimelineParticipant;

public class TurretPlateDestroyed extends TimelineEvent {

    private final TimelineParticipant killer;
    private final LaneType laneType;
    private final Point position;
    private final int teamId;

    public TurretPlateDestroyed(long timestamp, EventType type, TimelineParticipant participant, LaneType laneType, Point position, int teamId) {
        super(timestamp, type);
        this.killer = participant;
        this.laneType = laneType;
        this.position = position;
        this.teamId = teamId;
    }

    public TimelineParticipant getKiller() {
        return killer;
    }

    public LaneType getLaneType() {
        return laneType;
    }

    public Point getPosition() {
        return position;
    }

    public int getTeamId() {
        return teamId;
    }
}
