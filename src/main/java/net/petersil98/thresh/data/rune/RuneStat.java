package net.petersil98.thresh.data.rune;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.petersil98.thresh.model.Deserializers;

@JsonDeserialize(using = Deserializers.RuneStatDeserializer.class)
public class RuneStat extends BaseRune {

    public RuneStat(int id, String name, String iconPath, String shortDesc, String longDesc) {
        super(id, name, iconPath, shortDesc, longDesc);
    }
}
