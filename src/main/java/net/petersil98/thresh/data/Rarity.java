package net.petersil98.thresh.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Rarity {

    @JsonProperty("kUltimate")
    ULTIMATE,
    @JsonProperty("kMythic")
    MYTHIC,
    @JsonProperty("kLegendary")
    LEGENDARY,
    @JsonProperty("kEpic")
    EPIC,
    @JsonProperty("kRare")
    RARE,
    @JsonProperty("kNoRarity")
    NONE
}
