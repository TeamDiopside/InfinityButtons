package nl.teamdiopside.infinitybuttons.datagen.conditions;

import com.google.gson.JsonObject;

public class ModLoaded implements DataCondition {
    private final String modId;

    public ModLoaded(String modId) {
        this.modId = modId;
    }

    @Override
    public JsonObject fabric() {
        return new AnyModsLoaded(modId).fabric();
    }

    @Override
    public JsonObject neoForge() {
        JsonObject condition = new JsonObject();
        condition.addProperty("type", "neoforge:mod_loaded");
        condition.addProperty("modid", modId);
        return condition;
    }
}
