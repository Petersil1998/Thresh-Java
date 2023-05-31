package net.petersil98.thresh.data;

import net.petersil98.thresh.collection.Items;
import net.petersil98.thresh.data.champion.Champion;
import net.petersil98.thresh.util.Loader;

import java.util.List;
import java.util.Objects;

public class Item {

    private final int id;
    private final String name;
    private final int price;
    private final int totalPrice;
    private final int sellPrice;
    private final boolean purchasable;
    private final String description;
    private final String plainText;
    private final boolean consumed;
    private final int maxStackSize;
    private final int depth;
    private final boolean consumeOnFull;
    private List<Item> from;
    private List<Item> into;
    private Item specialRecipe;
    private final boolean inStore;
    private final boolean hideFromAll;
    private final Champion requiredChampion;
    private final Champion requiredAlly;
    private final java.util.Map<String, Float> stats;
    private final List<String> tags;
    private final List<Map> maps;
    private final Sprite sprite;
    private final String image;
    private final java.util.Map<String, String> effect;

    public Item(int id, String name, int price, int totalPrice, int sellPrice, boolean purchasable, String description, String plainText, boolean consumed, int maxStackSize, int depth, boolean consumeOnFull, boolean inStore, boolean hideFromAll, Champion requiredChampion, Champion requiredAlly, java.util.Map<String, Float> stats, List<String> tags, List<Map> maps, Sprite sprite, String image, java.util.Map<String, String> effect) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.totalPrice = totalPrice;
        this.sellPrice = sellPrice;
        this.purchasable = purchasable;
        this.description = description;
        this.plainText = plainText;
        this.consumed = consumed;
        this.maxStackSize = maxStackSize;
        this.depth = depth;
        this.consumeOnFull = consumeOnFull;
        this.inStore = inStore;
        this.hideFromAll = hideFromAll;
        this.requiredChampion = requiredChampion;
        this.requiredAlly = requiredAlly;
        this.stats = stats;
        this.tags = tags;
        this.maps = maps;
        this.sprite = sprite;
        this.image = image;
        this.effect = effect;
    }

    public void postInit() {
        if(Loader.ITEMS_FROM.containsKey(this.id)) {
            this.from = Loader.ITEMS_FROM.get(this.id).stream().map(Items::getItem).toList();
        }
        if(Loader.ITEMS_INTO.containsKey(this.id)) {
            this.into = Loader.ITEMS_INTO.get(this.id).stream().map(Items::getItem).toList();
        }
        if(Loader.ITEMS_SPECIAL_RECIPE.containsKey(this.id)) {
            this.specialRecipe = Items.getItem(Loader.ITEMS_SPECIAL_RECIPE.get(this.id));
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public boolean isPurchasable() {
        return purchasable;
    }

    public String getDescription() {
        return description;
    }

    public String getPlainText() {
        return plainText;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }

    public int getDepth() {
        return depth;
    }

    public boolean isConsumeOnFull() {
        return consumeOnFull;
    }

    public List<Item> getFrom() {
        return from;
    }

    public List<Item> getInto() {
        return into;
    }

    public Item getSpecialRecipe() {
        return specialRecipe;
    }

    public boolean isInStore() {
        return inStore;
    }

    public boolean isHideFromAll() {
        return hideFromAll;
    }

    public Champion getRequiredChampion() {
        return requiredChampion;
    }

    public Champion getRequiredAlly() {
        return requiredAlly;
    }

    public java.util.Map<String, Float> getStats() {
        return stats;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<Map> getMaps() {
        return maps;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public String getImage() {
        return image;
    }

    public java.util.Map<String, String> getEffect() {
        return effect;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return this.id == item.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
