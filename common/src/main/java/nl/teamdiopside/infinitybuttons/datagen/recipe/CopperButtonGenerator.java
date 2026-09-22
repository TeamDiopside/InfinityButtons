package nl.teamdiopside.infinitybuttons.datagen.recipe;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.WeatheringCopper;
import nl.teamdiopside.infinitybuttons.block.faced6.normal.CopperButton;
import nl.teamdiopside.infinitybuttons.block.faced6.normal.CopperButtonType;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;

import java.util.function.Function;

public class CopperButtonGenerator extends SmallLargeGenerator {

    @Override
    public void generate(IBRecipeOutput output) {
        for (var copperType : CopperButtonType.values()) {
            for (var weatherState : WeatheringCopper.WeatherState.values()) {
                String state = weatherState == WeatheringCopper.WeatherState.UNAFFECTED ? "copper" : weatherState.getSerializedName() + "_copper";
                String type = copperType == CopperButtonType.NORMAL ? state : copperType.getName() + "_" + state;

                IBRegistryUtils.LargeVariantSupplier<CopperButton> buttons = IBBlocks.COPPER_BUTTONS.get(copperType, weatherState);

                Function<String, String> group = (infix) -> (copperType == CopperButtonType.NORMAL ? copperType.getName() + "_" : "")
                        + "copper" + infix + "_buttons";

                if (copperType != CopperButtonType.STICKY) {
                    Item materialItem = IBRegistryUtils.getItemByID("minecraft", type +
                            (weatherState == WeatheringCopper.WeatherState.UNAFFECTED ? "_block" : "") // WTF Mojang
                    );

                    convertingRecipe(output, materialItem, buttons.getSmall(), false, 2, "", group.apply(""));

                    largeButton(output, buttons, materialItem, "", group.apply("_large"));

                    if (copperType == CopperButtonType.WAXED) {
                        simpleShapelessRecipe(output, materialItem, buttons.getSmall(), 1, "_honeycomb", group.apply(""),
                                IBBlocks.COPPER_BUTTONS.get(CopperButtonType.NORMAL, weatherState).getSmall(), Items.HONEYCOMB);
                        simpleShapelessRecipe(output, materialItem, buttons.getLarge(), 1, "_honeycomb", group.apply("_large"),
                                IBBlocks.COPPER_BUTTONS.get(CopperButtonType.NORMAL, weatherState).getLarge(), Items.HONEYCOMB);
                    }

                } else { // copperType == CopperButtonType.STICKY
                    largeButton(output, buttons, Items.COPPER_BLOCK, "", group.apply("_large"));

                    simpleShapelessRecipe(output, Items.COPPER_BLOCK, buttons.getSmall(), 1, "_honey", group.apply(""),
                            IBBlocks.COPPER_BUTTONS.get(CopperButtonType.NORMAL, weatherState).getSmall(), Items.HONEY_BOTTLE);
                    simpleShapelessRecipe(output, Items.COPPER_BLOCK, buttons.getLarge(), 1, "_honey", group.apply("_large"),
                            IBBlocks.COPPER_BUTTONS.get(CopperButtonType.NORMAL, weatherState).getLarge(), Items.HONEY_BOTTLE);
                }
            }
        }
    }
}
