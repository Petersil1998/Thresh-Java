package net.petersil98.thresh.model.game.match.participant;

public class PingStats {
    private final int allInPings;
    private final int assistMePings;
    private final int baitPings;
    private final int basicPings;
    private final int commandPings;
    private final int dangerPings;
    private final int enemyMissingPings;
    private final int enemyVisionPings;
    private final int getBackPings;
    private final int holdPings;
    private final int needVisionPings;
    private final int onMyWayPings;
    private final int pushPings;
    private final int visionClearedPings;

    public PingStats(int allInPings, int assistMePings, int baitPings, int basicPings, int commandPings, int dangerPings, int enemyMissingPings, int enemyVisionPings, int getBackPings, int holdPings, int needVisionPings, int onMyWayPings, int pushPings, int visionClearedPings) {
        this.allInPings = allInPings;
        this.assistMePings = assistMePings;
        this.baitPings = baitPings;
        this.basicPings = basicPings;
        this.commandPings = commandPings;
        this.dangerPings = dangerPings;
        this.enemyMissingPings = enemyMissingPings;
        this.enemyVisionPings = enemyVisionPings;
        this.getBackPings = getBackPings;
        this.holdPings = holdPings;
        this.needVisionPings = needVisionPings;
        this.onMyWayPings = onMyWayPings;
        this.pushPings = pushPings;
        this.visionClearedPings = visionClearedPings;
    }

    public int getAllInPings() {
        return this.allInPings;
    }

    public int getAssistMePings() {
        return this.assistMePings;
    }

    public int getBaitPings() {
        return this.baitPings;
    }

    public int getBasicPings() {
        return this.basicPings;
    }

    public int getCommandPings() {
        return this.commandPings;
    }

    public int getDangerPings() {
        return this.dangerPings;
    }

    public int getEnemyMissingPings() {
        return this.enemyMissingPings;
    }

    public int getEnemyVisionPings() {
        return this.enemyVisionPings;
    }

    public int getGetBackPings() {
        return this.getBackPings;
    }

    public int getHoldPings() {
        return this.holdPings;
    }

    public int getNeedVisionPings() {
        return this.needVisionPings;
    }

    public int getOnMyWayPings() {
        return this.onMyWayPings;
    }

    public int getPushPings() {
        return this.pushPings;
    }

    public int getVisionClearedPings() {
        return this.visionClearedPings;
    }
}
