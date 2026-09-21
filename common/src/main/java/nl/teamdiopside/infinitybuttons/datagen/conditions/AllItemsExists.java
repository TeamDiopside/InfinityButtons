package nl.teamdiopside.infinitybuttons.datagen.conditions;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;

import java.util.HashSet;
import java.util.Set;

public class AllItemsExists implements DataCondition {
    private final Set<ResourceLocation> items;

    public AllItemsExists(ResourceLocation... items) {
        this.items = Set.of(items);
    }

    public AllItemsExists(Set<ResourceLocation> items) {
        this.items = items;
    }

    @Override
    public JsonObject fabric() {
        JsonObject condition = new JsonObject();
        condition.addProperty("condition", "fabric:registry_contains");
        condition.addProperty("registry", "minecraft:item");
        JsonArray array = new JsonArray();
        for (ResourceLocation item : items) {
            array.add(item.toString());
        }
        condition.add("values", array);
        return condition;
    }

    @Override
    public JsonObject neoForge() {
        Set<DataCondition> conditions = new HashSet<>();
        for (ResourceLocation item : items) {
            conditions.add(new ItemExists(item));
        }
        return new And(conditions).neoForge();
    }
}
