package net.petersil98.thresh.model.game.match.timeline.event;

import net.petersil98.thresh.model.game.match.timeline.Point;
import net.petersil98.thresh.model.game.match.timeline.TimelineParticipant;

public class EliteMonsterKill extends TimelineEvent {

    private final int bounty;
    private final TimelineParticipant killer;
    private final int teamId;
    private final MonsterType monsterType;
    private final MonsterSubType monsterSubType;
    private final Point position;

    public EliteMonsterKill(long timestamp, EventType type, int bounty, TimelineParticipant killer, int teamId, MonsterType monsterType, MonsterSubType monsterSubType, Point position) {
        super(timestamp, type);
        this.bounty = bounty;
        this.killer = killer;
        this.teamId = teamId;
        this.monsterType = monsterType;
        this.monsterSubType = monsterSubType;
        this.position = position;
    }

    public int getBounty() {
        return bounty;
    }

    public TimelineParticipant getKiller() {
        return killer;
    }

    public int getTeamId() {
        return teamId;
    }

    public MonsterType getMonsterType() {
        return monsterType;
    }

    public MonsterSubType getMonsterSubType() {
        return monsterSubType;
    }

    public Point getPosition() {
        return position;
    }

    public enum MonsterType {
        DRAGON,
        RIFTHERALD,
        BARON_NASHOR,
        ELDER_DRAGON
    }

    public enum MonsterSubType {
        AIR_DRAGON,
        WATER_DRAGON,
        FIRE_DRAGON,
        EARTH_DRAGON,
        CHEMTECH_DRAGON,
        HEXTECH_DRAGON
    }
}
