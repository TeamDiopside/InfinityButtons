package nl.teamdiopside.infinitybuttons.compat.items;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.item.StandingAndWallBlockItem;
import nl.teamdiopside.infinitybuttons.compat.IBModdedItems;
import nl.teamdiopside.infinitybuttons.compat.blocks.MyNethersDelightBlocks;

import static com.mojang.text2speech.Narrator.LOGGER;

public class MyNethersDelightItems extends IBModdedItems {
    public static final MyNethersDelightItems INSTANCE = new MyNethersDelightItems();
    public static final String NAMESPACE = "mynethersdelight";

    public static final RegistrySupplier<StandingAndWallBlockItem> POWDERY_TORCH_BUTTON = INSTANCE.registerTorch("powdery_torch_button", MyNethersDelightBlocks.POWDERY_TORCH_BUTTON, MyNethersDelightBlocks.POWDERY_WALL_TORCH_BUTTON);
    public static final RegistrySupplier<StandingAndWallBlockItem> POWDERY_TORCH_LEVER = INSTANCE.registerTorch("powdery_torch_lever", MyNethersDelightBlocks.POWDERY_TORCH_LEVER, MyNethersDelightBlocks.POWDERY_WALL_TORCH_LEVER);

    private MyNethersDelightItems() {
        super(NAMESPACE);
    }

    @Override
    public void registerMine() {
        LOGGER.info("Infinity Buttons: Registering My Nether's Delight Items");
    }
}
