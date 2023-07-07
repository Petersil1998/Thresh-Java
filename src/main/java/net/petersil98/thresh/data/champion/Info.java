package net.petersil98.thresh.data.champion;

public class Info {

    private final int attack;
    private final int defense;
    private final int magic;
    private final int difficulty;

    public Info(int attack, int defense, int magic, int difficulty) {
        this.attack = attack;
        this.defense = defense;
        this.magic = magic;
        this.difficulty = difficulty;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getMagic() {
        return magic;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
