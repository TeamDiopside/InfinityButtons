package nl.teamdiopside.infinitybuttons.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import nl.teamdiopside.diopside.registry.DiopsideBlocks;
import nl.teamdiopside.infinitybuttons.block.CopperButton;
import nl.teamdiopside.infinitybuttons.block.NormalButton;
import nl.teamdiopside.infinitybuttons.registry.RegistryUtils.CopperButtonType;
import nl.teamdiopside.infinitybuttons.registry.RegistryUtils.LargeVariantSupplier;

import java.util.HashMap;
import java.util.function.Function;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.*;

public class IBBlocks {

    /*
     * Registry Suppliers
     */

    public static final HashMap<CopperButtonType, HashMap<WeatheringCopper.WeatherState, LargeVariantSupplier<CopperButton>>> COPPER_BUTTONS = registerCopperButtons();
    public static final HashMap<BlockSetType, RegistrySupplier<Block>> DEFAULT_LARGE_BUTTONS = registerDefaultLargeButtons();

    /*
     * Properties
     */

    private static BlockBehaviour.Properties getDefaultProperties() {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON);
    }

    /*
     * Helper Functions
     */

    private static LargeVariantSupplier<CopperButton> registerCopperButton(CopperButtonType copperButtonType, WeatheringCopper.WeatherState weatherState) {
        String state = weatherState == WeatheringCopper.WeatherState.UNAFFECTED ? "copper" : weatherState.getSerializedName() + "_copper";
        String type = copperButtonType == CopperButtonType.NORMAL ? state : copperButtonType.getName() + "_" + state;
        return LargeVariantSupplier.registerVariants((large) -> registerBlock(
                type + (large ? "_large_button" : "_button"),
                (properties) -> new CopperButton(properties, large, weatherState, copperButtonType),
                getDefaultProperties().sound(SoundType.COPPER).requiresCorrectToolForDrops()
        ));
    }

    private static HashMap<CopperButtonType, HashMap<WeatheringCopper.WeatherState, LargeVariantSupplier<CopperButton>>> registerCopperButtons() {
        HashMap<CopperButtonType, HashMap<WeatheringCopper.WeatherState, LargeVariantSupplier<CopperButton>>> typeMap = new HashMap<>();
        for (CopperButtonType type : CopperButtonType.values()) {
            HashMap<WeatheringCopper.WeatherState, LargeVariantSupplier<CopperButton>> stateMap = new HashMap<>();
            for (WeatheringCopper.WeatherState state : WeatheringCopper.WeatherState.values()) {
                stateMap.put(state, registerCopperButton(type, state));
            }
            typeMap.put(type, stateMap);
        }
        return typeMap;
    }

    private static HashMap<BlockSetType, RegistrySupplier<Block>> registerDefaultLargeButtons() {
        HashMap<BlockSetType, RegistrySupplier<Block>> map = new HashMap<>();
        for (BlockSetType type : BlockSetType.values().toList()) {
            if (type == BlockSetType.COPPER || type == BlockSetType.GOLD || type == BlockSetType.IRON) continue;
            map.put(type, registerBlock(
                            type.name() + "_large_button",
                            (properties) -> new NormalButton(type, type == BlockSetType.STONE ? 20 : 30, properties, true),
                            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)
                    )
            );
        }
        return map;
    }

    /*
     * Registry Functions
     */

    private static <T extends Block> RegistrySupplier<T> registerBlock(String blockId, Function<BlockBehaviour.Properties, T> blockFunction, BlockBehaviour.Properties properties) {
        return DiopsideBlocks.registerBlock(ResourceLocation.fromNamespaceAndPath(MOD_ID, blockId), blockFunction, properties);
    }

    public static void register() {
        LOGGER.info("Registering Blocks for Infinity Buttons");
    }
}
