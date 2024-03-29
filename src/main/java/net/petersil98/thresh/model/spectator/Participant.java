package net.petersil98.thresh.model.spectator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.petersil98.thresh.data.SummonerSpell;
import net.petersil98.thresh.data.champion.Champion;
import net.petersil98.thresh.data.rune.BaseRune;
import net.petersil98.thresh.data.rune.RuneStyle;
import net.petersil98.thresh.model.Deserializers;

import java.util.List;
import java.util.Objects;

@JsonDeserialize(using = Deserializers.ParticipantDeserializer.class)
public class Participant {
    private final String summonerId;
    private final boolean isBot;
    private final Champion champion;
    private final int teamId;
    private final SummonerSpell summonerSpell1;
    private final SummonerSpell summonerSpell2;
    private final List<BaseRune> runes;
    private final RuneStyle runeStyle;
    private final RuneStyle runeSubStyle;

    public Participant(String summonerId, boolean isBot, Champion champion, int teamId, SummonerSpell summonerSpell1, SummonerSpell summonerSpell2, List<BaseRune> runes, RuneStyle runeStyle, RuneStyle runeSubStyle) {
        this.summonerId = summonerId;
        this.isBot = isBot;
        this.champion = champion;
        this.teamId = teamId;
        this.summonerSpell1 = summonerSpell1;
        this.summonerSpell2 = summonerSpell2;
        this.runes = runes;
        this.runeStyle = runeStyle;
        this.runeSubStyle = runeSubStyle;
    }

    public String getSummonerId() {
        return this.summonerId;
    }

    public boolean isBot() {
        return this.isBot;
    }

    public Champion getChampion() {
        return this.champion;
    }

    public int getTeamId() {
        return this.teamId;
    }

    public SummonerSpell getSummonerSpell1() {
        return this.summonerSpell1;
    }

    public SummonerSpell getSummonerSpell2() {
        return this.summonerSpell2;
    }

    public List<BaseRune> getRunes() {
        return this.runes;
    }

    public RuneStyle getRuneStyle() {
        return this.runeStyle;
    }

    public RuneStyle getRuneSubStyle() {
        return this.runeSubStyle;
    }

    @Override
    public String toString() {
        return this.summonerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(summonerId, that.summonerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(summonerId);
    }
}
