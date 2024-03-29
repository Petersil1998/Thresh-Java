package net.petersil98.thresh.model.match.timeline.event;

import net.petersil98.thresh.model.match.timeline.TimelineParticipant;

public class WardKill extends TimelineEvent {

    private final TimelineParticipant killer;
    private final WardType wardType;

    public WardKill(long timestamp, EventType type, TimelineParticipant killer, WardType wardType) {
        super(timestamp, type);
        this.killer = killer;
        this.wardType = wardType;
    }

    public TimelineParticipant getKiller() {
        return killer;
    }

    public WardType getWardType() {
        return wardType;
    }
}
