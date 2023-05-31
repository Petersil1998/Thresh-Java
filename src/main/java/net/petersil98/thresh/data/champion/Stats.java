package net.petersil98.thresh.data.champion;

public class Stats {

    private float health;
    private float healthPerLevel;
    private float resource;
    private float resourcePerLevel;
    private int movementSpeed;
    private float armor;
    private float armorPerLevel;
    private float magicResist;
    private float magicResistPerLevel;
    private int attackRange;
    private float healthRegeneration;
    private float healthRegenerationPerLevel;
    private float resourceRegeneration;
    private float resourceRegenerationPerLevel;
    private float critChance;
    private float critChancePerLevel;
    private float attackDamage;
    private float attackDamagePerLevel;
    private float attackSpeed;
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
