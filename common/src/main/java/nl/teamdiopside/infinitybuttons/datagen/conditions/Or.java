package nl.teamdiopside.infinitybuttons.datagen.conditions;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Set;

public class Or implements DataCondition {

    private final Set<DataCondition> conditions;

    public Or(DataCondition... conditions) {
        this.conditions = Set.of(conditions);
    }

    public Or(Set<DataCondition> conditions) {
        this.conditions = conditions;
    }

    @Override
    public JsonObject fabric() {
        JsonObject or = new JsonObject();
        JsonArray set = new JsonArray();
        for (DataCondition condition : conditions) {
            set.add(condition.fabric());
        }
        or.addProperty("condition", "fabric:or");
        or.add("values", set);
        return or;
    }

    @Override
    public JsonObject neoForge() {
        JsonObject or = new JsonObject();
        JsonArray set = new JsonArray();
        for (DataCondition condition : conditions) {
            set.add(condition.neoForge());
        }
        or.addProperty("type", "neoforge:or");
        or.add("values", set);
        return or;
    }
}
