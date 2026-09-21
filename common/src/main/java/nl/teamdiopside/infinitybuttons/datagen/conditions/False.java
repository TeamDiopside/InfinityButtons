package nl.teamdiopside.infinitybuttons.datagen.conditions;

import com.google.gson.JsonObject;

public class False implements DataCondition {

    public False() {}

    @Override
    public JsonObject fabric() {
        JsonObject condition = new JsonObject();
        condition.addProperty("condition", "fabric:false");
        return condition;
    }

    @Override
    public JsonObject neoForge() {
        JsonObject condition = new JsonObject();
        condition.addProperty("type", "neoforge:false");
        return condition;
    }
}
