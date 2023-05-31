package net.petersil98.thresh.model.game.match.timeline.event;

import net.petersil98.thresh.model.game.match.timeline.Point;
import net.petersil98.thresh.model.game.match.timeline.TimelineParticipant;

import java.util.List;

public class BuildingKill extends TimelineEvent {

    private final List<TimelineParticipant> assistingParticipants;
    private final int bounty;
    private final BuildingType buildingType;
    private final TimelineParticipant killer;
    private final LaneType laneType;
    private final Point position;
    private final int teamId;
    private final TowerType towerType;

    public BuildingKill(long timestamp, EventType type, List<TimelineParticipant> assistingParticipants, int bounty, BuildingType buildingType, TimelineParticipant killer, LaneType laneType, Point position, int teamId, TowerType towerType) {
        super(timestamp, type);
        this.assistingParticipants = assistingParticipants;
        this.bounty = bounty;
        this.buildingType = buildingType;
        this.killer = killer;
        this.laneType = laneType;
        this.position = position;
        this.teamId = teamId;
        this.towerType = towerType;
    }

    public List<TimelineParticipant> getAssistingParticipants() {
        return assistingParticipants;
    }

    public int getBounty() {
        return bounty;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public TimelineParticipant getKiller() {
        return killer;
    }

    public LaneType getLaneType() {
        return laneType;
    }

    public Point getPosition() {
        return position;
    }

    public int getTeamId() {
        return teamId;
    }

    public TowerType getTowerType() {
        return towerType;
    }

    public enum BuildingType {
        TOWER_BUILDING,
        INHIBITOR_BUILDING
    }

    public enum TowerType {
        OUTER_TURRET,
        INNER_TURRET,
        BASE_TURRET,
        NEXUS_TURRET
    }
}
