package net.petersil98.thresh.model.game.match.timeline.event;

import net.petersil98.thresh.data.Item;
import net.petersil98.thresh.model.game.match.timeline.TimelineParticipant;

public class ItemUndo extends TimelineEvent {

    private final Item after;
    private final Item before;
    private final int goldGain;
    private final TimelineParticipant participant;

    public ItemUndo(long timestamp, EventType type, Item after, Item before, int goldGain, TimelineParticipant participant) {
        super(timestamp, type);
        this.after = after;
        this.before = before;
        this.goldGain = goldGain;
        this.participant = participant;
    }

    public Item getAfter() {
        return after;
    }

    public Item getBefore() {
        return before;
    }

    public int getGoldGain() {
        return goldGain;
    }

    public TimelineParticipant getParticipant() {
        return participant;
    }
}
