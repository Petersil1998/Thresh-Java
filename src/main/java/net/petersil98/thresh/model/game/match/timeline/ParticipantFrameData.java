package net.petersil98.thresh.model.game.match.timeline;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"participantId"})
public class ParticipantFrameData {
    private ChampionStats championStats;
    private int currentGold;
    private DamageStats damageStats;
    private float goldPerSecond;
    private int jungleMinionsKilled;
    private int level;
    private int minionsKilled;
    private Point position;
    private double timeEnemySpentControlled;
    private int totalGold;
    private int xp;

    public ChampionStats getChampionStats() {
        return championStats;
    }

    public int getCurrentGold() {
        return currentGold;
    }

    public DamageStats getDamageStats() {
        return damageStats;
    }

    public float getGoldPerSecond() {
        return goldPerSecond;
    }

    public int getJungleMinionsKilled() {
        return jungleMinionsKilled;
    }

    public int getLevel() {
        return level;
    }

    public int getMinionsKilled() {
        return minionsKilled;
    }

    public Point getPosition() {
        return position;
    }

    public double getTimeEnemySpentControlled() {
        return timeEnemySpentControlled;
    }

    public int getTotalGold() {
        return totalGold;
    }

    public int getXp() {
        return xp;
    }

    public static class ChampionStats {
        private int abilityHaste;
        private int abilityPower;
        private int armor;
        private int armorPen;
        private int armorPenPercent;
        private int attackDamage;
        private int attackSpeed;
        private int bonusArmorPenPercent;
        private int bonusMagicPenPercent;

        @JsonProperty(value = "ccReduction")
        private int tenacity;
        private int cooldownReduction;
        private int health;
        private int healthMax;
        private int healthRegen;
        private int lifesteal;
        private int magicPen;
        private int magicPenPercent;
        private int magicResist;
        private int movementSpeed;
        private int omnivamp;
        private int physicalVamp;
        @JsonProperty(value = "power")
        private int resource;
        @JsonProperty(value = "powerMax")
        private int resourceMax;
        @JsonProperty(value = "powerRegen")
        private int resourceRegen;
        private int spellVamp;

        public int getAbilityHaste() {
            return abilityHaste;
        }

        public int getAbilityPower() {
            return abilityPower;
        }

        public int getArmor() {
            return armor;
        }

        public int getArmorPen() {
            return armorPen;
        }

        public int getArmorPenPercent() {
            return armorPenPercent;
        }

        public int getAttackDamage() {
            return attackDamage;
        }

        public int getAttackSpeed() {
            return attackSpeed;
        }

        public int getBonusArmorPenPercent() {
            return bonusArmorPenPercent;
        }

        public int getBonusMagicPenPercent() {
            return bonusMagicPenPercent;
        }

        public int getTenacity() {
            return tenacity;
        }

        public int getCooldownReduction() {
            return cooldownReduction;
        }

        public int getHealth() {
            return health;
        }

        public int getHealthMax() {
            return healthMax;
        }

        public int getHealthRegen() {
            return healthRegen;
        }

        public int getLifesteal() {
            return lifesteal;
        }

        public int getMagicPen() {
            return magicPen;
        }

        public int getMagicPenPercent() {
            return magicPenPercent;
        }

        public int getMagicResist() {
            return magicResist;
        }

        public int getMovementSpeed() {
            return movementSpeed;
        }

        public int getOmnivamp() {
            return omnivamp;
        }

        public int getPhysicalVamp() {
            return physicalVamp;
        }

        public int getResource() {
            return resource;
        }

        public int getResourceMax() {
            return resourceMax;
        }

        public int getResourceRegen() {
            return resourceRegen;
        }

        public int getSpellVamp() {
            return spellVamp;
        }
    }

    public static class DamageStats {
        private int magicDamageDone;
        private int magicDamageDoneToChampions;
        private int magicDamageTaken;
        private int physicalDamageDone;
        private int physicalDamageDoneToChampions;
        private int physicalDamageTaken;
        private int totalDamageDone;
        private int totalDamageDoneToChampions;
        private int totalDamageTaken;
        private int trueDamageDone;
        private int trueDamageDoneToChampions;
        private int trueDamageTaken;

        public int getMagicDamageDone() {
            return magicDamageDone;
        }

        public int getMagicDamageDoneToChampions() {
            return magicDamageDoneToChampions;
        }

        public int getMagicDamageTaken() {
            return magicDamageTaken;
        }

        public int getPhysicalDamageDone() {
            return physicalDamageDone;
        }

        public int getPhysicalDamageDoneToChampions() {
            return physicalDamageDoneToChampions;
        }

        public int getPhysicalDamageTaken() {
            return physicalDamageTaken;
        }

        public int getTotalDamageDone() {
            return totalDamageDone;
        }

        public int getTotalDamageDoneToChampions() {
            return totalDamageDoneToChampions;
        }

        public int getTotalDamageTaken() {
            return totalDamageTaken;
        }

        public int getTrueDamageDone() {
            return trueDamageDone;
        }

        public int getTrueDamageDoneToChampions() {
            return trueDamageDoneToChampions;
        }

        public int getTrueDamageTaken() {
            return trueDamageTaken;
        }
    }

}
