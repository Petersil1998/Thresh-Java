package net.petersil98.thresh.model.game.match.timeline.event;

import net.petersil98.thresh.model.game.match.timeline.TimelineParticipant;

public class SkillLevelUp extends TimelineEvent {

    private final String levelUpType;
    private final TimelineParticipant participant;
    private final int skillSlot;

    public SkillLevelUp(long timestamp, EventType type, String levelUpType, TimelineParticipant participant, int skillSlot) {
        super(timestamp, type);
        this.levelUpType = levelUpType;
        this.participant = participant;
        this.skillSlot = skillSlot;
    }

    public String getLevelUpType() {
        return levelUpType;
    }

    public TimelineParticipant getParticipant() {
        return participant;
    }

    public int getSkillSlot() {
        return skillSlot;
    }
}
