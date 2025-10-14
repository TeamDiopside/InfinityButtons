package nl.teamdiopside.infinitybuttons.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import nl.teamdiopside.diopside.registry.DiopsideBlocks;
import nl.teamdiopside.infinitybuttons.block.NormalButton;

import java.util.HashMap;
import java.util.function.Function;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.LOGGER;
import static nl.teamdiopside.infinitybuttons.InfinityButtons.MOD_ID;

public class IBBlocks {
    
    /*
     * Registry Suppliers
     */

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
