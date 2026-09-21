package nl.teamdiopside.infinitybuttons.datagen.conditions;

import com.google.gson.JsonObject;

public class True implements DataCondition {

    public True() {}

    @Override
    public JsonObject fabric() {
        JsonObject condition = new JsonObject();
        condition.addProperty("condition", "fabric:true");
        return condition;
    }

    @Override
    public JsonObject neoForge() {
        JsonObject condition = new JsonObject();
        condition.addProperty("type", "neoforge:true");
        return condition;
    }
}
