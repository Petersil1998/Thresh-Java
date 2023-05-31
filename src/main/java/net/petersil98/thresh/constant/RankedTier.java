package net.petersil98.thresh.constant;

public enum RankedTier {
    IRON("IRON", false),
    BRONZE("BRONZE", false),
    SILVER("SILVER", false),
    GOLD("GOLD", false),
    PLATINUM("PLATINUM", false),
    DIAMOND("DIAMOND", false),
    MASTER("MASTER", true),
    GRANDMASTER("GRANDMASTER", true),
    CHALLENGER("CHALLENGER", true);

    private final String name;
    private final boolean isApex;

    RankedTier(String name, boolean isApex) {
        this.name = name;
        this.isApex = isApex;
    }

    public boolean isApex() {
        return this.isApex;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
