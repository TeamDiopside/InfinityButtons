package nl.teamdiopside.infinitybuttons.datagen.conditions;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Set;

public class And implements DataCondition {

    private final Set<DataCondition> conditions;

    public And(DataCondition... conditions) {
        this.conditions = Set.of(conditions);
    }

    public And(Set<DataCondition> conditions) {
        this.conditions = conditions;
    }

    @Override
    public JsonObject fabric() {
        JsonObject and = new JsonObject();
        JsonArray set = new JsonArray();
        for (DataCondition condition : conditions) {
            set.add(condition.fabric());
        }
        and.addProperty("condition", "fabric:and");
        and.add("values", set);
        return and;
    }

    @Override
    public JsonObject neoForge() {
        JsonObject and = new JsonObject();
        JsonArray set = new JsonArray();
        for (DataCondition condition : conditions) {
            set.add(condition.neoForge());
        }
        and.addProperty("type", "neoforge:and");
        and.add("values", set);
        return and;
    }
}
