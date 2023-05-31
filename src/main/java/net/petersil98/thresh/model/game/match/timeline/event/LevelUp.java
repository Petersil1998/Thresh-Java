package net.petersil98.thresh.model.game.match.timeline.event;

import net.petersil98.thresh.model.game.match.timeline.TimelineParticipant;

public class LevelUp extends TimelineEvent {

    private final int level;
    private final TimelineParticipant participant;

    public LevelUp(long timestamp, EventType type, int level, TimelineParticipant participant) {
        super(timestamp, type);
        this.level = level;
        this.participant = participant;
    }

    public int getLevel() {
        return level;
    }

    public TimelineParticipant getParticipant() {
        return participant;
    }
}
