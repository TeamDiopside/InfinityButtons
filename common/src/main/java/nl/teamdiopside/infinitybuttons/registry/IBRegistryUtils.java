package nl.teamdiopside.infinitybuttons.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import nl.teamdiopside.diopside.registry.DiopsideRegistries;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class IBRegistryUtils {
    public record LargeVariantSupplier<T>(RegistrySupplier<T> small, RegistrySupplier<T> large) {
        public static <T> LargeVariantSupplier<T> registerVariants(Function<Boolean, RegistrySupplier<T>> supplier) {
            return new LargeVariantSupplier<>(supplier.apply(false), supplier.apply(true));
        }

        public RegistrySupplier<T> getSupplier(boolean isLarge) {
            return isLarge ? large : small;
        }

        public T get(boolean isLarge) {
            return isLarge ? large.get() : small.get();
        }

        public T getSmall() {
            return small.get();
        }

        public T getLarge() {
            return large.get();
        }
    }

    public static Item getItemByID(String id) {
        Optional<Item> item = BuiltInRegistries.ITEM.getOptional(ResourceLocation.parse(id));

        if (item.isEmpty()) {
            throw new NoSuchElementException("Could not find item \"" + id + "\"!");
        }

        return item.get();
    }

    public static Item getItemByID(String namespace, String id) {
        Item item = DiopsideRegistries.ITEM.getById(ResourceLocation.fromNamespaceAndPath(namespace, id));

        if (item == null) {
            throw new NoSuchElementException("Could not find item \"" + id + "\"!");
        }

        return item;
    }

    public static Block getBlockByID(String id) {
        Optional<Block> block = BuiltInRegistries.BLOCK.getOptional(ResourceLocation.parse(id));

        if (block.isEmpty()) {
            throw new NoSuchElementException("Could not find black \"" + id + "\"!");
        }

        return block.get();
    }

    public static Block getBlockByID(String namespace, String id) {
        System.out.println("get " + id);
        Block block = DiopsideRegistries.BLOCK.getById(ResourceLocation.fromNamespaceAndPath(namespace, id));

        if (block == null) {
            throw new NoSuchElementException("Could not find black \"" + id + "\"!");
        }

        return block;
    }

    public static boolean isWoodType(String name) {
        return Set.of(
                "oak",
                "spruce",
                "birch",
                "jungle",
                "acacia",
                "dark_oak",
                "mangrove",
                "cherry",
                "bamboo",
                "crimson",
                "warped"
        ).contains(name);
    }
}
