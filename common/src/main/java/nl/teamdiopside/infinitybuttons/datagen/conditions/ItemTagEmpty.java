package nl.teamdiopside.infinitybuttons.datagen.conditions;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

public class ItemTagEmpty implements DataCondition {
    private final ResourceLocation tag;

    public ItemTagEmpty(TagKey<?> tag) {
        this.tag = tag.location();
    }

    public ItemTagEmpty(ResourceLocation tag) {
        this.tag = tag;
    }

    @Override
    public JsonObject fabric() {
        return new Not(new ItemTagPopulated(tag)).fabric();
    }

    @Override
    public JsonObject neoForge() {
        JsonObject condition = new JsonObject();
        condition.addProperty("type", "neoforge:tag_empty");
        condition.addProperty("tag", tag.toString());
        return condition;
    }
}
