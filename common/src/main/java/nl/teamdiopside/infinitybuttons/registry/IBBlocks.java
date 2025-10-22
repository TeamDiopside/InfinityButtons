package nl.teamdiopside.infinitybuttons.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import nl.teamdiopside.diopside.registry.DiopsideBlocks;
import java.util.function.Function;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.LOGGER;
import static nl.teamdiopside.infinitybuttons.InfinityButtons.MOD_ID;

public class IBBlocks {
    
    /*
     * Registry Suppliers
     */

    /*
     * Properties
     */

    private static BlockBehaviour.Properties getDefaultProperties() {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON);
    }

    /*
     * Helper Functions
     */

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
