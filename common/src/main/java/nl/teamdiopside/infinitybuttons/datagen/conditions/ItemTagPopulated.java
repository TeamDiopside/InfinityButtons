package nl.teamdiopside.infinitybuttons.datagen.conditions;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

public class ItemTagPopulated implements DataCondition {
    private final ResourceLocation tag;

    public ItemTagPopulated(TagKey<?> tag) {
        this.tag = tag.location();
    }

    public ItemTagPopulated(ResourceLocation tag) {
        this.tag = tag;
    }

    @Override
    public JsonObject fabric() {
        JsonObject populated = new JsonObject();
        populated.addProperty("condition", "fabric:tags_populated");
        populated.addProperty("registry", "minecraft:item");
        JsonArray set = new JsonArray();
        set.add(tag.toString());
        populated.add("values", set);
        return populated;
    }

    @Override
    public JsonObject neoForge() {
        return new Not(new ItemTagEmpty(tag)).neoForge();
    }
}
