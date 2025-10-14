package nl.teamdiopside.infinitybuttons;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.RegistrarManager;

import java.util.function.Supplier;
import java.util.logging.Logger;

public final class InfinityButtons {
    public static final String MOD_ID = "infinitybuttons";
    public static final Supplier<RegistrarManager> MANAGER = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));
    public static final Logger LOGGER = Logger.getLogger(MOD_ID);

    public static void init() {}
}
