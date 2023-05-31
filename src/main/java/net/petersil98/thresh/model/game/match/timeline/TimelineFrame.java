package net.petersil98.thresh.model.game.match.timeline;

import net.petersil98.thresh.model.game.match.timeline.event.TimelineEvent;

import java.util.List;
import java.util.Map;

public class TimelineFrame {

    private final int timestamp;
    private final List<TimelineEvent> events;
    private final Map<Integer, ParticipantFrameData> participantFrames;

    public TimelineFrame(int timestamp, List<TimelineEvent> events, Map<Integer, ParticipantFrameData> participantFrames) {
        this.timestamp = timestamp;
        this.events = events;
        this.participantFrames = participantFrames;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public List<TimelineEvent> getEvents() {
        return events;
    }

    public Map<Integer, ParticipantFrameData> getParticipantFrames() {
        return participantFrames;
    }
}
