package nl.teamdiopside.infinitybuttons.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.Block;
import nl.teamdiopside.diopside.registry.DiopsideItems;

import java.util.function.Supplier;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.LOGGER;
import static nl.teamdiopside.infinitybuttons.InfinityButtons.MOD_ID;

public class IBItems {


    public static final RegistrySupplier<Item> TORCH_BUTTON = registerTorch("torch_button", IBBlocks.TORCH_BUTTON.get(), IBBlocks.WALL_TORCH_BUTTON.get());

    public static final RegistrySupplier<Item> TORCH_LEVER = registerTorch("torch_lever", IBBlocks.TORCH_LEVER.get(), IBBlocks.WALL_TORCH_LEVER.get());

    public static final RegistrySupplier<Item> SOUL_TORCH_BUTTON = registerTorch("soul_torch_button", IBBlocks.SOUL_TORCH_BUTTON.get(), IBBlocks.SOUL_WALL_TORCH_BUTTON.get());

    public static final RegistrySupplier<Item> SOUL_TORCH_LEVER = registerTorch("soul_torch_lever", IBBlocks.SOUL_TORCH_LEVER.get(), IBBlocks.SOUL_WALL_TORCH_LEVER.get());

    public static final RegistrySupplier<Item> REDSTONE_TORCH_BUTTON = registerTorch("redstone_torch_button", IBBlocks.REDSTONE_TORCH_BUTTON.get(), IBBlocks.REDSTONE_WALL_TORCH_BUTTON.get());

    public static final RegistrySupplier<Item> REDSTONE_TORCH_LEVER = registerTorch("redstone_torch_lever", IBBlocks.REDSTONE_TORCH_LEVER.get(), IBBlocks.REDSTONE_WALL_TORCH_LEVER.get());

    public static RegistrySupplier<Item> registerTorch(String name, Block standing, Block wall) {
        return registerItem(name, () -> new StandingAndWallBlockItem(standing, wall, new Item.Properties(), Direction.DOWN));
    }

    /**
     * Methods
     */
    private static RegistrySupplier<Item> registerItem(String name, Supplier<Item> itemSupplier) {
        return DiopsideItems.INSTANCE.registerItem(ResourceLocation.fromNamespaceAndPath(MOD_ID, name), itemSupplier);
    }

    public static void register() {
        LOGGER.info("Registering Items for Infinity Buttons");
    }
}
