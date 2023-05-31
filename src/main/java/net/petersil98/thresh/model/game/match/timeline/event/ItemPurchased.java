package net.petersil98.thresh.model.game.match.timeline.event;

import net.petersil98.thresh.data.Item;
import net.petersil98.thresh.model.game.match.timeline.TimelineParticipant;

public class ItemPurchased extends TimelineEvent {

    private final Item purchased;
    private final TimelineParticipant participant;

    public ItemPurchased(long timestamp, EventType type, Item purchased, TimelineParticipant participant) {
        super(timestamp, type);
        this.purchased = purchased;
        this.participant = participant;
    }

    public Item getPurchased() {
        return purchased;
    }

    public TimelineParticipant getParticipant() {
        return participant;
    }
}
