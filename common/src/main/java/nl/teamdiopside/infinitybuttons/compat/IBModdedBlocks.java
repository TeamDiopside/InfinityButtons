package nl.teamdiopside.infinitybuttons.compat;

import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButton;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButtonShape;
import nl.teamdiopside.infinitybuttons.compat.blocks.*;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;

import java.util.HashMap;
import java.util.HashSet;

public abstract class IBModdedBlocks extends IBBlocks {

    protected String namespace;

    protected IBModdedBlocks(String namespace) {
        this.namespace = namespace;
    }

    public abstract void registerMine();

    public static void register() {
        if (Platform.isModLoaded(AtmosphericBlocks.NAMESPACE))    AtmosphericBlocks.INSTANCE.registerMine();
        if (Platform.isModLoaded(AutumnityBlocks.NAMESPACE))      AutumnityBlocks.INSTANCE.registerMine();
        if (Platform.isModLoaded(BuzzierBeesBlocks.NAMESPACE))    BuzzierBeesBlocks.INSTANCE.registerMine();
        if (Platform.isModLoaded(ClayworksBlocks.NAMESPACE))      ClayworksBlocks.INSTANCE.registerMine();

        // TODO: Create

        if (Platform.isModLoaded(EnvironmentalBlocks.NAMESPACE))  EnvironmentalBlocks.INSTANCE.registerMine();
        if (Platform.isModLoaded(NeapolitanBlocks.NAMESPACE))     NeapolitanBlocks.INSTANCE.registerMine();

        // TODO: My Nether's Delight

        if (Platform.isModLoaded(QuarkBlocks.NAMESPACE))          QuarkBlocks.INSTANCE.registerMine();
        if (Platform.isModLoaded(UpgradeAquaticBlocks.NAMESPACE)) UpgradeAquaticBlocks.INSTANCE.registerMine();
        if (Platform.isModLoaded(WoodworksBlocks.NAMESPACE))      WoodworksBlocks.INSTANCE.registerMine();
    }

    public static final HashSet<RegistrySupplier<SecretButton>> MOD_BOOKSHELVES = new HashSet<>();

    protected RegistrySupplier<SecretButton> registerBookshelf(String wood) {
        return registerBookshelf(wood, wood);
    }

    protected RegistrySupplier<SecretButton> registerBookshelf(String name, String wood) {
        var registry = registerModBookshelfSecretButton(name + "_bookshelf_secret_button",
                ResourceLocation.fromNamespaceAndPath(this.namespace, wood + "_bookshelf"),
                ResourceLocation.fromNamespaceAndPath(this.namespace, "block/" + wood + "_planks"));

        MOD_BOOKSHELVES.add(registry);
        return registry;
    }

    public static final HashMap<String, RegistrySupplier<SecretButton>> MOD_SECRET_BUTTONS = new HashMap<>(); // TODO needed??
    public static final HashMap<String, ResourceLocation> BOOKSHELF_TOP_TEXTURES = new HashMap<>();

    protected RegistrySupplier<SecretButton> registerModBookshelfSecretButton(String blockId, ResourceLocation originalBlock, ResourceLocation topTexture) {
        var registry = super.registerSecretButton(blockId, SecretButtonShape.BOOKSHELF, BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF), originalBlock);

        MOD_SECRET_BUTTONS.put(blockId, registry);
        BOOKSHELF_TOP_TEXTURES.put(blockId, topTexture);
        return registry;
    }
}
