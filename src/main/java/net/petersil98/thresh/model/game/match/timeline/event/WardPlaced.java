package net.petersil98.thresh.model.game.match.timeline.event;

import net.petersil98.thresh.model.game.match.timeline.TimelineParticipant;

public class WardPlaced extends TimelineEvent {

    private final TimelineParticipant creator;
    private final WardKill.WardType wardType;

    public WardPlaced(long timestamp, EventType type, TimelineParticipant creator, WardKill.WardType wardType) {
        super(timestamp, type);
        this.creator = creator;
        this.wardType = wardType;
    }

    public TimelineParticipant getCreator() {
        return creator;
    }

    public WardKill.WardType getWardType() {
        return wardType;
    }
}
