package net.petersil98.thresh.model.match.timeline;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.petersil98.core.constant.Region;
import net.petersil98.core.http.RiotAPI;
import net.petersil98.thresh.model.Deserializers;

import java.util.List;

@JsonDeserialize(using = Deserializers.TimelineDeserializer.class)
public class Timeline {

    private final int frameInterval;

    private final List<TimelineFrame> frames;

    private final List<TimelineParticipant> participants;

    public Timeline(int frameInterval, List<TimelineFrame> frames, List<TimelineParticipant> participants) {
        this.frameInterval = frameInterval;
        this.frames = frames;
        this.participants = participants;
    }

    public static Timeline getTimelinesForMatch(String matchId, Region region) {
        return RiotAPI.requestLoLMatchEndpoint("matches/", matchId + "/timeline", region, Timeline.class);
    }

    public int getFrameInterval() {
        return frameInterval;
    }

    public List<TimelineFrame> getFrames() {
        return frames;
    }

    public List<TimelineParticipant> getParticipants() {
        return participants;
    }
}
