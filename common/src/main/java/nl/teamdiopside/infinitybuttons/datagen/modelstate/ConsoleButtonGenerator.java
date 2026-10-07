package nl.teamdiopside.infinitybuttons.datagen.modelstate;

import com.google.gson.JsonElement;
import net.minecraft.core.Direction;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import nl.teamdiopside.infinitybuttons.block.faced6.console.ConsoleButton;
import nl.teamdiopside.infinitybuttons.block.faced6.console.ConsoleButtonType;
import nl.teamdiopside.infinitybuttons.datagen.simplifier.OutFolder;
import nl.teamdiopside.infinitybuttons.datagen.simplifier.SimpleReferenceModel;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.MOD_ID;

public class ConsoleButtonGenerator implements ModelStateGenerator {

    private final ConsoleButton consoleButton;

    public ConsoleButtonGenerator(ConsoleButton consoleButton) {
        super();
        this.consoleButton = consoleButton;
    }

    private ResourceLocation getModel(OutFolder folder, String variant) {
        ConsoleButtonType type = consoleButton.getType();
        String typeName = type == ConsoleButtonType.NORMAL ? "" : type.getSerializedName() + "_";
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, folder.name + (folder == OutFolder.BLOCK ? "/console_buttons/" : "/") + typeName + "console_button" + variant);
    }

    private ResourceLocation getItemModel() {
        return getModel(OutFolder.ITEM, "");
    }

    private ResourceLocation getBlockModel(String variant) {
        return getModel(OutFolder.BLOCK, variant);
    }

    @Override
    public void generateState(Consumer<BlockStateGenerator> consumer) {
        ResourceLocation normalModel = getBlockModel("");
        ResourceLocation pressedModel = getBlockModel("_pressed");
        ResourceLocation brokenModel = getBlockModel("_broken");

        var consoleButtonBuilder = PropertyDispatch.properties(ConsoleButton.BROKEN, BlockStateProperties.ATTACH_FACE, BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.POWERED);

        for (boolean broken : Set.of(true, false))
            for (boolean powered : Set.of(true, false))
                for (AttachFace face : AttachFace.values())
                    for (Direction facing : Direction.Plane.HORIZONTAL) {

                        ResourceLocation model = broken ? brokenModel : (powered ? pressedModel : normalModel);

                        Variant variant = Variant.variant().with(VariantProperties.MODEL, model);
                        if (face == AttachFace.CEILING)
                            variant.with(VariantProperties.X_ROT, VariantProperties.Rotation.R180);

                        if (face != AttachFace.WALL) {
                            if (facing == Direction.EAST) variant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90);
                            else if (facing == Direction.SOUTH) variant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180);
                            else if (facing == Direction.WEST) variant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270);
                        } else {
                            variant.with(VariantProperties.X_ROT, VariantProperties.Rotation.R270);

                            if (facing == Direction.NORTH) variant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180);
                            else if (facing == Direction.EAST) variant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270);
                            else if (facing == Direction.WEST) variant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90);
                        }

                        consoleButtonBuilder.select(broken, face, facing, powered, variant);
                    }
        consumer.accept(MultiVariantGenerator.multiVariant(consoleButton).with(consoleButtonBuilder));
    }

    @Override
    public void generateBlockModels(BiConsumer<ResourceLocation, Supplier<JsonElement>> consumer) {
        // Models were made by hand
        throw new IllegalStateException("Console Button Block Models will not be generated!");
    }

    @Override
    public void generateItemModels(BiConsumer<ResourceLocation, Supplier<JsonElement>> consumer) {
        SimpleReferenceModel model = new SimpleReferenceModel(getItemModel(), getBlockModel(""));
        model.build(consumer);
    }
}
