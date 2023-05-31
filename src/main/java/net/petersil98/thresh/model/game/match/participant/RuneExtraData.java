package net.petersil98.thresh.model.game.match.participant;

public class RuneExtraData {
    private final int extraData1;
    private final int extraData2;
    private final int extraData3;

    public RuneExtraData(int extraData1, int extraData2, int extraData3) {
        this.extraData1 = extraData1;
        this.extraData2 = extraData2;
        this.extraData3 = extraData3;
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
