package nl.teamdiopside.infinitybuttons.registry;

import dev.architectury.registry.registries.RegistrySupplier;

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
    }

    public enum CopperButtonType {
        NORMAL("normal"),
        WAXED("waxed"),
        STICKY("sticky");

        private final String name;

        CopperButtonType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
