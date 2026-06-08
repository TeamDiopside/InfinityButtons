package nl.teamdiopside.infinitybuttons.compat;

import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.level.block.Block;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButton;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButtonType;
import nl.teamdiopside.infinitybuttons.compat.blocks.AtmosphericBlocks;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;

import java.util.HashMap;

public abstract class IBModdedBlocks extends IBBlocks {

    protected String namespace;

    protected IBModdedBlocks(String namespace) {
        this.namespace = namespace;
    }

    public abstract void registerMine();

    private static boolean shouldRegister(String modId) {
        if (Platform.isModLoaded(modId)) return true;

        return false;
    }

    public static void register() {
        if (shouldRegister(AtmosphericBlocks.NAMESPACE)) AtmosphericBlocks.INSTANCE.registerMine();

        // TODO: Atmospheric      - alleen NeoForge 1.21.1
        // TODO: Autumnity        - alleen NeoForge 1.21.1
        // TODO: BuzzierBees      - alleen NeoForge 1.21.1
        // TODO: Clayworks        - alleen NeoForge 1.21.1
        // TODO: Create           - alleen NeoForge 1.21.1
        // TODO: Endergetic       - ---
        // TODO: Environmental    - alleen NeoForge 1.21.1
        // TODO: Neapolitan       - alleen NeoForge 1.21.1
        // TODO: Nether's Delight - alleen NeoForge 1.21.1 -> Nieuwe mod
        // TODO: Quark            - alleen NeoForge 1.21.1
        // TODO: Savage & Ravage  - --- (discontinued)
        // TODO: Upgrade Aquatic  - alleen NeoForge 1.21.1
        // TODO: Woodworks        - alleen NeoForge 1.21.1
    }

    protected RegistrySupplier<SecretButton> registerBookshelf(String wood) {
        var registry = registerSecretButton(wood + "_bookshelf_secret_button",
                SecretButtonType.BOOKSHELF, blockFromMod(wood + "_bookshelf") );

        return registry;
    }

    protected Block blockFromMod(String id) {
        return IBRegistryUtils.getBlockByID(this.namespace, id);
    }


    public static final HashMap<String, RegistrySupplier<SecretButton>> MOD_SECRET_BUTTONS = new HashMap<>();
    @Override
    protected RegistrySupplier<SecretButton> registerSecretButton(String blockId, SecretButtonType type, Block originalBlock) {
        var registry = super.registerSecretButton(blockId, type, originalBlock);

        MOD_SECRET_BUTTONS.put(blockId, registry);
        return registry;
    }
}
