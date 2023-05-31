package net.petersil98.thresh.model.game.match.timeline.event;

public abstract class TimelineEvent {

    protected long timestamp;
    protected EventType type;

    protected TimelineEvent(long timestamp, EventType type) {
        this.timestamp = timestamp;
        this.type = type;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public EventType getType() {
        return this.type;
    }
}
