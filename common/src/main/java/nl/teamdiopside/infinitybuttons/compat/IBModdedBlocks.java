package nl.teamdiopside.infinitybuttons.compat;

import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import nl.teamdiopside.diopside.registry.BlockEntryBuilder;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButton;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButtonType;
import nl.teamdiopside.infinitybuttons.compat.blocks.*;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Function;

public abstract class IBModdedBlocks extends IBBlocks {

    protected String namespace;

    protected IBModdedBlocks(String namespace) {
        this.namespace = namespace;
    }

    public abstract void registerMine();

    public static void register() {
        if (Platform.isModLoaded(AtmosphericBlocks.NAMESPACE))      AtmosphericBlocks.INSTANCE.registerMine();
        if (Platform.isModLoaded(AutumnityBlocks.NAMESPACE))        AutumnityBlocks.INSTANCE.registerMine();
        if (Platform.isModLoaded(BuzzierBeesBlocks.NAMESPACE))      BuzzierBeesBlocks.INSTANCE.registerMine();
        if (Platform.isModLoaded(ClayworksBlocks.NAMESPACE))        ClayworksBlocks.INSTANCE.registerMine();
        if (Platform.isModLoaded(CreateBlocks.NAMESPACE))           CreateBlocks.INSTANCE.registerMine();
        if (Platform.isModLoaded(EnvironmentalBlocks.NAMESPACE))    EnvironmentalBlocks.INSTANCE.registerMine();
        if (Platform.isModLoaded(MyNethersDelightBlocks.NAMESPACE)) MyNethersDelightBlocks.INSTANCE.registerMine();
        if (Platform.isModLoaded(NeapolitanBlocks.NAMESPACE))       NeapolitanBlocks.INSTANCE.registerMine();
        if (Platform.isModLoaded(QuarkBlocks.NAMESPACE))            QuarkBlocks.INSTANCE.registerMine();
        if (Platform.isModLoaded(UpgradeAquaticBlocks.NAMESPACE))   UpgradeAquaticBlocks.INSTANCE.registerMine();
        if (Platform.isModLoaded(WoodworksBlocks.NAMESPACE))        WoodworksBlocks.INSTANCE.registerMine();
    }

    public static final HashSet<RegistrySupplier<SecretButton>> MOD_BOOKSHELVES = new HashSet<>();

    protected RegistrySupplier<SecretButton> registerBookshelf(String wood) {
        return registerBookshelf(wood, wood);
    }

    protected RegistrySupplier<SecretButton> registerBookshelf(String name, String wood) {
        String topTextureNamespace = IBRegistryUtils.isWoodType(wood) ? "minecraft" : this.namespace;

        var registry = registerModBookshelfSecretButton(name + "_bookshelf_secret_button",
                ResourceLocation.fromNamespaceAndPath(this.namespace, wood + "_bookshelf"),
                ResourceLocation.fromNamespaceAndPath(topTextureNamespace, "block/" + wood + "_planks"));

        MOD_BOOKSHELVES.add(registry);
        return registry;
    }

    public static final HashMap<RegistrySupplier<? extends Block>, String> MODDED_BUTTONS = new HashMap<>();
    public static final HashMap<String, ResourceLocation> BOOKSHELF_TOP_TEXTURES = new HashMap<>();

    protected RegistrySupplier<SecretButton> registerModBookshelfSecretButton(String blockId, ResourceLocation originalBlock, ResourceLocation topTexture) {
        var registry = super.registerSecretButton(blockId, SecretButtonType.BOOKSHELF, BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF), originalBlock);
        BOOKSHELF_TOP_TEXTURES.put(blockId, topTexture);
        return registry;
    }

    // For camouflages whose texture doesn't live where it's supposed to. Stupid blocks.
    public static final HashMap<String, ResourceLocation> SECRET_BUTTON_TEXTURE_OVERRIDES = new HashMap<>();

    protected RegistrySupplier<SecretButton> registerSecretButton(String blockId, SecretButtonType type, BlockBehaviour.Properties properties, ResourceLocation material, ResourceLocation textureOverride) {
        var registry = registerSecretButton(blockId, type, properties, material);

        SECRET_BUTTON_TEXTURE_OVERRIDES.put(blockId, textureOverride);
        return registry;
    }

    @Override
    protected <T extends Block> RegistrySupplier<T> registerBlock(String blockId, BlockEntryBuilder<T> builder, BlockBehaviour.Properties properties, String tooltipKey, Function<ItemStack, Boolean> tooltipCondition) {
        RegistrySupplier<T> supplier = super.registerBlock(blockId, builder, properties, tooltipKey, tooltipCondition);
        MODDED_BUTTONS.put(supplier, namespace);
        return supplier;
    }
}
