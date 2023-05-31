package net.petersil98.thresh.model.game.match.timeline.event;

public class DragonSoulGiven extends TimelineEvent {

    private final DragonType dragonType;
    private final int teamId;

    public DragonSoulGiven(long timestamp, EventType type, DragonType dragonType, int teamId) {
        super(timestamp, type);
        this.dragonType = dragonType;
        this.teamId = teamId;
    }

    public DragonType getDragonType() {
        return dragonType;
    }

    public int getTeamId() {
        return teamId;
    }

    public enum DragonType {
        OCEAN,
        INFERNAL,
        MOUNTAIN,
        CLOUD,
        HEXTECH,
        CHEMTECH
    }
}
