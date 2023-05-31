package net.petersil98.thresh.model.game.match.timeline.event;

import net.petersil98.thresh.model.game.match.timeline.Point;
import net.petersil98.thresh.model.game.match.timeline.TimelineParticipant;

public class ChampionSpecialKill extends TimelineEvent {

    private final KillType killType;
    private final TimelineParticipant killer;
    private final int multiKillLength;
    private final Point position;

    public ChampionSpecialKill(long timestamp, EventType type, KillType killType, TimelineParticipant killer, int multiKillLength, Point position) {
        super(timestamp, type);
        this.killType = killType;
        this.killer = killer;
        this.multiKillLength = multiKillLength;
        this.position = position;
    }

    public KillType getKillType() {
        return killType;
    }

    public TimelineParticipant getKiller() {
        return killer;
    }

    public int getMultiKillLength() {
        return multiKillLength;
    }

    public Point getPosition() {
        return position;
    }

    public enum KillType {
        KILL_ACE,
        KILL_FIRST_BLOOD,
        KILL_MULTI
    }
}
