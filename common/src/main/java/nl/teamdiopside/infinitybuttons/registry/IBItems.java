package nl.teamdiopside.infinitybuttons.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import nl.teamdiopside.diopside.registry.ItemEntryBuilder;
import nl.teamdiopside.infinitybuttons.block.faced4.TorchButton;
import nl.teamdiopside.infinitybuttons.item.SafeEmergencyButtonItem;

import java.util.HashMap;
import java.util.function.Supplier;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.LOGGER;
import static nl.teamdiopside.infinitybuttons.InfinityButtons.getResource;

public class IBItems {


    public static final RegistrySupplier<StandingAndWallBlockItem> TORCH_BUTTON = registerTorch("torch_button", IBBlocks.TORCH_BUTTON, IBBlocks.WALL_TORCH_BUTTON);

    public static final RegistrySupplier<StandingAndWallBlockItem> TORCH_LEVER = registerTorch("torch_lever", IBBlocks.TORCH_LEVER, IBBlocks.WALL_TORCH_LEVER);

    public static final RegistrySupplier<StandingAndWallBlockItem> SOUL_TORCH_BUTTON = registerTorch("soul_torch_button", IBBlocks.SOUL_TORCH_BUTTON, IBBlocks.SOUL_WALL_TORCH_BUTTON);

    public static final RegistrySupplier<StandingAndWallBlockItem> SOUL_TORCH_LEVER = registerTorch("soul_torch_lever", IBBlocks.SOUL_TORCH_LEVER, IBBlocks.SOUL_WALL_TORCH_LEVER);

    public static final RegistrySupplier<StandingAndWallBlockItem> REDSTONE_TORCH_BUTTON = registerTorch("redstone_torch_button", IBBlocks.REDSTONE_TORCH_BUTTON, IBBlocks.REDSTONE_WALL_TORCH_BUTTON);

    public static final RegistrySupplier<StandingAndWallBlockItem> REDSTONE_TORCH_LEVER = registerTorch("redstone_torch_lever", IBBlocks.REDSTONE_TORCH_LEVER, IBBlocks.REDSTONE_WALL_TORCH_LEVER);

    public static RegistrySupplier<StandingAndWallBlockItem> registerTorch(String name, Supplier<TorchButton> standing, Supplier<TorchButton> wall) {
        return registerItem(name, ItemEntryBuilder.ofItem(properties -> new StandingAndWallBlockItem(standing.get(), wall.get(), properties, Direction.DOWN)), new Item.Properties());
    }

    public static HashMap<DyeColor, RegistrySupplier<SafeEmergencyButtonItem>> SAFETY_BUTTONS = registerSafetyButtons();

    public static RegistrySupplier<SafeEmergencyButtonItem> registerSafetyButton(DyeColor color) {
        var safeEmergencyButton = IBBlocks.SAFE_EMERGENCY_BUTTONS.get(color);
        return registerItem(safeEmergencyButton.getId().getPath(), ItemEntryBuilder.ofItem(properties -> new SafeEmergencyButtonItem(safeEmergencyButton.get(), properties)), new Item.Properties());
    }

    public static HashMap<DyeColor, RegistrySupplier<SafeEmergencyButtonItem>> registerSafetyButtons() {
        HashMap<DyeColor, RegistrySupplier<SafeEmergencyButtonItem>> map = new HashMap<>();
        for (DyeColor color : DyeColor.values()) {
            map.put(color, registerSafetyButton(color));
        }
        return map;
    }

    /**
     * Methods
     */
    private static <T extends Item> RegistrySupplier<T> registerItem(String name, ItemEntryBuilder<T> builder, Item.Properties properties) {
        return builder.register(getResource(name), properties);
    }

    public static void register() {
        LOGGER.info("Registering Items for Infinity Buttons");
    }
}
