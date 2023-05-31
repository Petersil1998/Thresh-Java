package net.petersil98.thresh.model.game.match.timeline.event;

import net.petersil98.thresh.data.champion.Champion;
import net.petersil98.thresh.model.game.match.timeline.Point;
import net.petersil98.thresh.model.game.match.timeline.TimelineParticipant;

import java.util.List;

public class ChampionKill extends TimelineEvent {

    private final List<TimelineParticipant> assistingParticipants;
    private final int bounty;
    private final int killStreakLength;
    private final TimelineParticipant killer;
    private final Point position;
    private final int shutdownBounty;
    private final List<DamageData> victimDamageDealt;
    private final List<DamageData> victimDamageReceived;
    private final TimelineParticipant victim;

    public ChampionKill(long timestamp, EventType type, List<TimelineParticipant> assistingParticipants, int bounty, int killStreakLength, TimelineParticipant killer, Point position, int shutdownBounty, List<DamageData> victimDamageDealt, List<DamageData> victimDamageReceived, TimelineParticipant victim) {
        super(timestamp, type);
        this.assistingParticipants = assistingParticipants;
        this.bounty = bounty;
        this.killStreakLength = killStreakLength;
        this.killer = killer;
        this.position = position;
        this.shutdownBounty = shutdownBounty;
        this.victimDamageDealt = victimDamageDealt;
        this.victimDamageReceived = victimDamageReceived;
        this.victim = victim;
    }

    public List<TimelineParticipant> getAssistingParticipants() {
        return assistingParticipants;
    }

    public int getBounty() {
        return bounty;
    }

    public int getKillStreakLength() {
        return killStreakLength;
    }

    public TimelineParticipant getKiller() {
        return killer;
    }

    public Point getPosition() {
        return position;
    }

    public int getShutdownBounty() {
        return shutdownBounty;
    }

    public List<DamageData> getVictimDamageDealt() {
        return victimDamageDealt;
    }

    public List<DamageData> getVictimDamageReceived() {
        return victimDamageReceived;
    }

    public TimelineParticipant getVictim() {
        return victim;
    }

    public static class DamageData {

        private final boolean basic;
        private final int magicDamage;
        private final Champion champion;
        private final TimelineParticipant participant;
        private final int physicalDamage;
        private final String spellName;
        private final int spellSlot;
        private final int trueDamage;
        private final String type;

        public DamageData(boolean basic, int magicDamage, Champion champion, TimelineParticipant participant, int physicalDamage, String spellName, int spellSlot, int trueDamage, String type) {
            this.basic = basic;
            this.magicDamage = magicDamage;
            this.champion = champion;
            this.participant = participant;
            this.physicalDamage = physicalDamage;
            this.spellName = spellName;
            this.spellSlot = spellSlot;
            this.trueDamage = trueDamage;
            this.type = type;
        }

        public boolean isBasic() {
            return basic;
        }

        public int getMagicDamage() {
            return magicDamage;
        }

        public Champion getChampion() {
            return champion;
        }

        public TimelineParticipant getParticipant() {
            return participant;
        }

        public int getPhysicalDamage() {
            return physicalDamage;
        }

        public String getSpellName() {
            return spellName;
        }

        public int getSpellSlot() {
            return spellSlot;
        }

        public int getTrueDamage() {
            return trueDamage;
        }

        public String getType() {
            return type;
        }
    }
}
