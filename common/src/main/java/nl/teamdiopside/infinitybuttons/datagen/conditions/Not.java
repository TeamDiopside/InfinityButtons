package nl.teamdiopside.infinitybuttons.datagen.conditions;

import com.google.gson.JsonObject;

public class Not implements DataCondition {

    private final DataCondition condition;

    public Not(DataCondition conditions) {
        this.condition = conditions;
    }

    @Override
    public JsonObject fabric() {
        JsonObject not = new JsonObject();
        not.addProperty("condition", "fabric:or");
        not.add("value", condition.fabric());
        return not;
    }

    @Override
    public JsonObject neoForge() {
        JsonObject not = new JsonObject();
        not.addProperty("type", "neoforge:or");
        not.add("value", condition.neoForge());
        return not;
    }
}
