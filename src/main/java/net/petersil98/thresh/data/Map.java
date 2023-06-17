package net.petersil98.thresh.data;

import net.petersil98.core.data.Sprite;

public record Map(int id, String name, String fullImage, Sprite sprite) {

    @Override
    public String toString() {
        return this.name;
    }
}
