package nl.teamdiopside.infinitybuttons.datagen.simplifier;

import com.google.gson.JsonElement;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.MOD_ID;

public class SimpleReferenceModel {
    private final ResourceLocation modelLocation;
    private final ResourceLocation parentLocation;

    private final TextureMapping textureMapping = new TextureMapping();

    public SimpleReferenceModel(ResourceLocation modelLocation, ResourceLocation parentLocation) {
        this.modelLocation = modelLocation;
        this.parentLocation = parentLocation;
    }

    public SimpleReferenceModel(String modelLocation, String parentLocation) {
        this(ResourceLocation.fromNamespaceAndPath(MOD_ID, modelLocation), ResourceLocation.fromNamespaceAndPath(MOD_ID, parentLocation));
    }

    public SimpleReferenceModel withTexture(TextureSlot slot, ResourceLocation texture) {
        if (texture == null) throw new NullPointerException("Input texture is undefined!");

        this.textureMapping.putForced(slot, texture);
        return this;
    }

    public SimpleReferenceModel withTexture(ResourceLocation texture) {
        return this.withTexture(TextureSlot.TEXTURE, texture);
    }

    private ModelTemplate generateTemplate() {
        return new ModelTemplate(Optional.of(this.parentLocation), Optional.empty());
    }

    public ResourceLocation build(BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput) {
        return this.generateTemplate().create(this.modelLocation, textureMapping, modelOutput);
    }

    // Special types
    public static SimpleReferenceModel makeButton(OutFolder outDir, String buttonName, ResourceLocation textureLocation, boolean isLarge, ButtonVariant variant) {
        String modelName = buttonName + (variant == ButtonVariant.INVENTORY ? "" : variant.suffix);
        String parentName = (isLarge ? "large_button" : "button") + variant.suffix;

        ResourceLocation parent = isLarge
                ? ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/" + parentName)
                : ResourceLocation.withDefaultNamespace("block/" + parentName);

        return new SimpleReferenceModel(ResourceLocation.fromNamespaceAndPath(MOD_ID, outDir.name + "/" + modelName), parent)
                .withTexture(textureLocation);
    }
}
