package net.petersil98.thresh.model.game.match;

public class Objective {

    private int kills;
    private boolean first;

    public Objective() {}

    public Objective(int kills, boolean first) {
        this.kills = kills;
        this.first = first;
    }

    public int getKills() {
        return kills;
    }

    public boolean isFirst() {
        return first;
    }
}
