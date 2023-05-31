package net.petersil98.thresh.model.game.match.timeline;

import net.petersil98.thresh.model.summoner.Summoner;

import java.util.Objects;

public class TimelineParticipant {

    private int participantId;
    private String puuid;
    private Summoner summoner;

    public int getParticipantId() {
        return this.participantId;
    }

    public String getPuuid() {
        return this.puuid;
    }

    public Summoner getSummoner() {
        if(this.summoner == null) this.summoner = Summoner.getSummonerByPUUID(this.puuid);
        return this.summoner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimelineParticipant that = (TimelineParticipant) o;
        return Objects.equals(puuid, that.puuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(puuid);
    }
}
