package nl.teamdiopside.infinitybuttons.datagen.conditions;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;

public class ItemExists implements DataCondition {
    private final ResourceLocation item;

    public ItemExists(ResourceLocation item) {
        this.item = item;
    }

    @Override
    public JsonObject fabric() {
        return new AllItemsExists(item).fabric();
    }

    @Override
    public JsonObject neoForge() {
        JsonObject condition = new JsonObject();
        condition.addProperty("type", "neoforge:item_exists");
        condition.addProperty("item", item.toString());
        return condition;
    }
}
