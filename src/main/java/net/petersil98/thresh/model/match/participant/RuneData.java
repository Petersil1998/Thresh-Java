package net.petersil98.thresh.model.match.participant;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.petersil98.thresh.data.rune.Rune;
import net.petersil98.thresh.model.Deserializers;

@JsonDeserialize(using = Deserializers.RuneDataDeserializer.class)
public class RuneData {

    private final Rune rune;
    private final int extraData1;
    private final int extraData2;
    private final int extraData3;

    public RuneData(Rune rune, int extraData1, int extraData2, int extraData3) {
        this.rune = rune;
        this.extraData1 = extraData1;
        this.extraData2 = extraData2;
        this.extraData3 = extraData3;
    }

    public Rune getRune() {
        return rune;
    }

    public int getExtraData1() {
        return this.extraData1;
    }

    public int getExtraData2() {
        return this.extraData2;
    }

    public int getExtraData3() {
        return this.extraData3;
    }
}
