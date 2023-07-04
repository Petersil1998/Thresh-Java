package net.petersil98.thresh.model.match.timeline;

import java.util.Objects;

public class TimelineParticipant {

    private int participantId;
    private String puuid;

    public int getParticipantId() {
        return this.participantId;
    }

    public String getPuuid() {
        return this.puuid;
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
