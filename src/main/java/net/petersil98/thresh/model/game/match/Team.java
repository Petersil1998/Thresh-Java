package net.petersil98.thresh.model.game.match;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.petersil98.thresh.data.champion.Champion;
import net.petersil98.thresh.model.Deserializers;

import java.util.Map;
import java.util.Objects;

@JsonDeserialize(using = Deserializers.TeamDeserializer.class)
public class Team {
    private final int teamId;
    private final boolean win;
    private final Objective baron;
    private final Objective champion;
    private final Objective dragon;
    private final Objective inhibitor;
    private final Objective riftHerald;
    private final Objective tower;
    private final Map<Integer, Champion> bans;

    public Team(int teamId, boolean win, Objective baron, Objective champion, Objective dragon, Objective inhibitor, Objective riftHerald, Objective tower, Map<Integer, Champion> bans) {
        this.teamId = teamId;
        this.win = win;
        this.baron = baron;
        this.champion = champion;
        this.dragon = dragon;
        this.inhibitor = inhibitor;
        this.riftHerald = riftHerald;
        this.tower = tower;
        this.bans = bans;
    }

    public int getTeamId() {
        return teamId;
    }

    public boolean isWin() {
        return win;
    }

    public Objective getBaron() {
        return baron;
    }

    public Objective getChampion() {
        return champion;
    }

    public Objective getDragon() {
        return dragon;
    }

    public Objective getInhibitor() {
        return inhibitor;
    }

    public Objective getRiftHerald() {
        return riftHerald;
    }

    public Objective getTower() {
        return tower;
    }

    public Map<Integer, Champion> getBans() {
        return bans;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", win=" + win +
                ", baron=" + baron +
                ", champion=" + champion +
                ", dragon=" + dragon +
                ", inhibitor=" + inhibitor +
                ", riftHerald=" + riftHerald +
                ", tower=" + tower +
                ", bans=" + bans +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return teamId == team.teamId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId);
    }
}
