package net.petersil98.thresh.collection;

import net.petersil98.thresh.data.SkinLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SkinLines {

    private static Map<Integer, SkinLine> skinLines;

    public static SkinLine getSkinLine(int id){
        return skinLines.get(id);
    }

    public static List<SkinLine> getSkinLines() {
        return new ArrayList<>(skinLines.values());
    }
}
