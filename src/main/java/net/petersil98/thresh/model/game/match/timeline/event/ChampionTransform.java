package net.petersil98.thresh.model.game.match.timeline.event;

import net.petersil98.thresh.model.game.match.timeline.TimelineParticipant;

public class ChampionTransform extends TimelineEvent {

    private final TimelineParticipant participant;
    private final TransformType transformType;

    public ChampionTransform(long timestamp, EventType type, TimelineParticipant participant, TransformType transformType) {
        super(timestamp, type);
        this.participant = participant;
        this.transformType = transformType;
    }

    public TimelineParticipant getParticipant() {
        return participant;
    }

    public TransformType getTransformType() {
        return transformType;
    }

    public enum TransformType {
        ASSASSIN,
        SLAYER
    }
}
