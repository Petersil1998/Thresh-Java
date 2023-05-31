package net.petersil98.thresh.model.game.spectator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.petersil98.thresh.data.champion.Champion;
import net.petersil98.thresh.model.Deserializers;

@JsonDeserialize(using = Deserializers.BanDeserializer.class)
public class Ban {
    private final Champion champion;
    private final int teamId;
    private final int pickTurn;

    public Ban(Champion champion, int teamId, int pickTurn) {
        this.champion = champion;
        this.teamId = teamId;
        this.pickTurn = pickTurn;
    }

    public Champion getChampion() {
        return this.champion;
    }

    public int getTeamId() {
        return this.teamId;
    }

    public int getPickTurn() {
        return this.pickTurn;
    }

    @Override
    public String toString() {
        return this.champion.toString();
    }
}
