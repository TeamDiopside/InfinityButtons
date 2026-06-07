package nl.teamdiopside.infinitybuttons.compat;

import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.level.block.Block;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButton;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButtonType;
import nl.teamdiopside.infinitybuttons.compat.blocks.AtmosphericBlocks;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;

public abstract class IBModdedBlocks extends IBBlocks {

    protected String namespace;

    protected IBModdedBlocks(String namespace) {
        this.namespace = namespace;
    }

    public abstract void registerMine();

    public static void register() {
        if (Platform.isModLoaded(AtmosphericBlocks.NAMESPACE)) AtmosphericBlocks.INSTANCE.registerMine();

        // TODO: Autumnity
        // TODO: BuzzierBees
        // TODO: Clayworks
        // TODO: Create
        // TODO: Endergetic
        // TODO: Environmental
        // TODO: Neapolitan
        // TODO: Nether's Delight
        // TODO: Quark
        // TODO: Savage & Ravage
        // TODO: Upgrade Aquatic
        // TODO: Woodworks
    }

    protected RegistrySupplier<SecretButton> registerBookshelf(String wood) {
        return registerSecretButton(wood + "_bookshelf_secret_button",
                SecretButtonType.BOOKSHELF, getByID(wood + "_bookshelf") );
    }

    protected Block getByID(String id) {
        return IBRegistryUtils.getBlockByID(getNamespace(), id);
    }

    protected String getNamespace() {
        return this.namespace;
    }
}
