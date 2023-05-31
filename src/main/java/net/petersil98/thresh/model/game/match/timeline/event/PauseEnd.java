package net.petersil98.thresh.model.game.match.timeline.event;

public class PauseEnd extends TimelineEvent {

    private final long realTimestamp;

    public PauseEnd(long timestamp, EventType type, long realTimestamp) {
        super(timestamp, type);
        this.realTimestamp = realTimestamp;
    }

    public long getRealTimestamp() {
        return this.realTimestamp;
    }
}
