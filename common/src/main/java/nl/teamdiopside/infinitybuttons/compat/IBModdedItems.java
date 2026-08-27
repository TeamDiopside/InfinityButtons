package nl.teamdiopside.infinitybuttons.compat;

import dev.architectury.platform.Platform;
import nl.teamdiopside.infinitybuttons.compat.items.MyNethersDelightItems;
import nl.teamdiopside.infinitybuttons.registry.IBItems;

public abstract class IBModdedItems extends IBItems {

    protected String namespace;

    protected IBModdedItems(String namespace) {
        this.namespace = namespace;
    }

    public abstract void registerMine();

    public static void register() {
        if (Platform.isModLoaded(MyNethersDelightItems.NAMESPACE)) MyNethersDelightItems.INSTANCE.registerMine();
    }

}
