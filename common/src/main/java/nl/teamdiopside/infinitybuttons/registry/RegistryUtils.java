package nl.teamdiopside.infinitybuttons.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import nl.teamdiopside.diopside.registry.DiopsideItems;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

public class RegistryUtils {
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
        return DiopsideItems.INSTANCE.getById(ResourceLocation.fromNamespaceAndPath(namespace, id));
    }
}
