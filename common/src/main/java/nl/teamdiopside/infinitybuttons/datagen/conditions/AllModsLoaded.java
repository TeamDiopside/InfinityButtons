package nl.teamdiopside.infinitybuttons.datagen.conditions;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashSet;
import java.util.Set;

public class AllModsLoaded implements DataCondition {
    private final Set<String> modIds;

    public AllModsLoaded(String... modId) {
        this.modIds = Set.of(modId);
    }

    @Override
    public JsonObject fabric() {
        JsonObject condition = new JsonObject();
        JsonArray set = new JsonArray();
        for (String modId : modIds) {
            set.add(modId);
        }
        condition.addProperty("condition", "fabric:all_mods_loaded");
        condition.add("values", set);
        return condition;
    }

    @Override
    public JsonObject neoForge() {
        Set<DataCondition> conditions = new HashSet<>();
        for (String modId : modIds) {
            conditions.add(new ModLoaded(modId));
        }
        return new And(conditions).neoForge();
    }
}
