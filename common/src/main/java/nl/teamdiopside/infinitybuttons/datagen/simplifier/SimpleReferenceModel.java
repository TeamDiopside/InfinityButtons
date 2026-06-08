package nl.teamdiopside.infinitybuttons.datagen.simplifier;

import com.google.gson.JsonElement;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.MOD_ID;

public class SimpleReferenceModel {
    private final ResourceLocation modelLocation;
    private final ResourceLocation parentLocation;

    private final TextureMapping textureMapping = new TextureMapping();
    private final Set<TextureSlot> slots = new HashSet<>();

    public SimpleReferenceModel(ResourceLocation modelLocation, ResourceLocation parentLocation) {
        this.modelLocation = modelLocation;
        this.parentLocation = parentLocation;
    }

    public SimpleReferenceModel(String modelLocation, String parentLocation) {
        this(ResourceLocation.fromNamespaceAndPath(MOD_ID, modelLocation), ResourceLocation.fromNamespaceAndPath(MOD_ID, parentLocation));
    }

    private ModelTemplate generateTemplate() {
        TextureSlot[] requiredSlots = this.slots.toArray(new TextureSlot[0]);

        return new ModelTemplate(Optional.of(this.parentLocation), Optional.empty(), requiredSlots);
    }

    public SimpleReferenceModel withTexture(TextureSlot slot, ResourceLocation texture) {
        this.textureMapping.put(slot, texture);
        this.slots.add(slot);
        return this;
    }

    public SimpleReferenceModel withTexture(ResourceLocation texture) {
        return this.withTexture(TextureSlot.TEXTURE, texture);
    }

    public ResourceLocation build(BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput) {
        return this.generateTemplate().create(this.modelLocation, this.textureMapping, modelOutput);
    }
}
