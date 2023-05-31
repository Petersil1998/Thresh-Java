package net.petersil98.thresh.data;

public class Sprite {

    private String sprite;
    private String group;
    private int x;
    private int y;
    private int width;
    private int height;

    public Sprite(String sprite, String group, int x, int y, int width, int height) {
        this.sprite = sprite;
        this.group = group;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Sprite() {}

    public String getSprite() {
        return sprite;
    }

    public String getGroup() {
        return group;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
