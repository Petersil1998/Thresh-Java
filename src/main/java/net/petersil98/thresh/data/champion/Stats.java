package net.petersil98.thresh.data.champion;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats {

    @JsonProperty("hp")
    private float health;
    @JsonProperty("hpperlevel")
    private float healthPerLevel;
    @JsonProperty("mp")
    private float resource;
    @JsonProperty("mpperlevel")
    private float resourcePerLevel;
    @JsonProperty("movespeed")
    private int movementSpeed;
    private float armor;
    @JsonProperty("armorperlevel")
    private float armorPerLevel;
    @JsonProperty("spellblock")
    private float magicResist;
    @JsonProperty("spellblockperlevel")
    private float magicResistPerLevel;
    @JsonProperty("attackrange")
    private int attackRange;
    @JsonProperty("hpregen")
    private float healthRegeneration;
    @JsonProperty("hpregenperlevel")
    private float healthRegenerationPerLevel;
    @JsonProperty("mpregen")
    private float resourceRegeneration;
    @JsonProperty("mpregenperlevel")
    private float resourceRegenerationPerLevel;
    @JsonProperty("crit")
    private float critChance;
    @JsonProperty("critperlevel")
    private float critChancePerLevel;
    @JsonProperty("attackdamage")
    private float attackDamage;
    @JsonProperty("attackdamageperlevel")
    private float attackDamagePerLevel;
    @JsonProperty("attackspeed")
    private float attackSpeed;
    @JsonProperty("attackspeedperlevel")
    private float attackSpeedPerLevel;

    public Stats(float health, float healthPerLevel, float resource, float resourcePerLevel, int movementSpeed, float armor, float armorPerLevel, float magicResist, float magicResistPerLevel, int attackRange, float healthRegeneration, float healthRegenerationPerLevel, float resourceRegeneration, float resourceRegenerationPerLevel, float critChance, float critChancePerLevel, float attackDamage, float attackDamagePerLevel, float attackSpeed, float attackSpeedPerLevel) {
        this.health = health;
        this.healthPerLevel = healthPerLevel;
        this.resource = resource;
        this.resourcePerLevel = resourcePerLevel;
        this.movementSpeed = movementSpeed;
        this.armor = armor;
        this.armorPerLevel = armorPerLevel;
        this.magicResist = magicResist;
        this.magicResistPerLevel = magicResistPerLevel;
        this.attackRange = attackRange;
        this.healthRegeneration = healthRegeneration;
        this.healthRegenerationPerLevel = healthRegenerationPerLevel;
        this.resourceRegeneration = resourceRegeneration;
        this.resourceRegenerationPerLevel = resourceRegenerationPerLevel;
        this.critChance = critChance;
        this.critChancePerLevel = critChancePerLevel;
        this.attackDamage = attackDamage;
        this.attackDamagePerLevel = attackDamagePerLevel;
        this.attackSpeed = attackSpeed;
        this.attackSpeedPerLevel = attackSpeedPerLevel;
    }

    public Stats() {

    }

    public float getHealth() {
        return health;
    }

    public float getHealthPerLevel() {
        return healthPerLevel;
    }

    public float getResource() {
        return resource;
    }

    public float getResourcePerLevel() {
        return resourcePerLevel;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public float getArmor() {
        return armor;
    }

    public float getArmorPerLevel() {
        return armorPerLevel;
    }

    public float getMagicResist() {
        return magicResist;
    }

    public float getMagicResistPerLevel() {
        return magicResistPerLevel;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public float getHealthRegeneration() {
        return healthRegeneration;
    }

    public float getHealthRegenerationPerLevel() {
        return healthRegenerationPerLevel;
    }

    public float getResourceRegeneration() {
        return resourceRegeneration;
    }

    public float getResourceRegenerationPerLevel() {
        return resourceRegenerationPerLevel;
    }

    public float getCritChance() {
        return critChance;
    }

    public float getCritChancePerLevel() {
        return critChancePerLevel;
    }

    public float getAttackDamage() {
        return attackDamage;
    }

    public float getAttackDamagePerLevel() {
        return attackDamagePerLevel;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public float getAttackSpeedPerLevel() {
        return attackSpeedPerLevel;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "health=" + health +
                ", healthPerLevel=" + healthPerLevel +
                ", resource=" + resource +
                ", resourcePerLevel=" + resourcePerLevel +
                ", movementSpeed=" + movementSpeed +
                ", armor=" + armor +
                ", armorPerLevel=" + armorPerLevel +
                ", magicResist=" + magicResist +
                ", magicResistPerLevel=" + magicResistPerLevel +
                ", attackRange=" + attackRange +
                ", healthRegeneration=" + healthRegeneration +
                ", healthRegenerationPerLevel=" + healthRegenerationPerLevel +
                ", resourceRegeneration=" + resourceRegeneration +
                ", resourceRegenerationPerLevel=" + resourceRegenerationPerLevel +
                ", critChance=" + critChance +
                ", critChancePerLevel=" + critChancePerLevel +
                ", attackDamage=" + attackDamage +
                ", attackDamagePerLevel=" + attackDamagePerLevel +
                ", attackSpeed=" + attackSpeed +
                ", attackSpeedPerLevel=" + attackSpeedPerLevel +
                '}';
    }
}
