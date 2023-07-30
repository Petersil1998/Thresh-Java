package net.petersil98.thresh.data.champion;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.petersil98.thresh.data.Rarity;
import net.petersil98.thresh.data.SkinLine;
import net.petersil98.thresh.model.Deserializers;

import java.util.List;
import java.util.Objects;

@JsonDeserialize(using = Deserializers.SkinDeserializer.class)
public class Skin {

    private final int id;
    private final boolean isBase;
    private final String name;
    private final String splashArt;
    private final String squareImage;
    private final String loadingScreenImage;
    private final Rarity rarity;
    private final boolean isLegacy;
    private final List<Chroma> chromas;
    private final List<SkinLine> skinLines;
    private final String description;

    public Skin(int id, boolean isBase, String name, String splashArt, String squareImage, String loadingScreenImage, Rarity rarity, boolean isLegacy, List<Chroma> chromas, List<SkinLine> skinLines, String description) {
        this.id = id;
        this.isBase = isBase;
        this.name = name;
        this.splashArt = splashArt;
        this.squareImage = squareImage;
        this.loadingScreenImage = loadingScreenImage;
        this.rarity = rarity;
        this.isLegacy = isLegacy;
        this.chromas = chromas;
        this.skinLines = skinLines;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public boolean isBase() {
        return isBase;
    }

    public String getName() {
        return name;
    }

    public String getSplashArt() {
        return splashArt;
    }

    public String getSquareImage() {
        return squareImage;
    }

    public String getLoadingScreenImage() {
        return loadingScreenImage;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public boolean isLegacy() {
        return isLegacy;
    }

    public List<Chroma> getChromas() {
        return chromas;
    }

    public List<SkinLine> getSkinLines() {
        return skinLines;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skin skin = (Skin) o;
        return id == skin.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
