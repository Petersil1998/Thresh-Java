package net.petersil98.thresh.model.game.match.timeline.event;

import net.petersil98.thresh.data.Item;
import net.petersil98.thresh.model.game.match.timeline.TimelineParticipant;

public class ItemDestroyed extends TimelineEvent {

    private final Item item;
    private final TimelineParticipant participant;

    public ItemDestroyed(long timestamp, EventType type, Item item, TimelineParticipant participant) {
        super(timestamp, type);
        this.item = item;
        this.participant = participant;
    }

    public Item getItem() {
        return item;
    }

    public TimelineParticipant getParticipant() {
        return participant;
    }
}
