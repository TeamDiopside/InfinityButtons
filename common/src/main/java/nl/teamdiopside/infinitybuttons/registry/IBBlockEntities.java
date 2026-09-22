package nl.teamdiopside.infinitybuttons.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import nl.teamdiopside.infinitybuttons.block.faced6.console.ConsoleButtonBlockEntity;

import static nl.teamdiopside.infinitybuttons.InfinityButtons.LOGGER;
import static nl.teamdiopside.infinitybuttons.InfinityButtons.MOD_ID;

public class IBBlockEntities {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<ConsoleButtonBlockEntity>> CONSOLE_BUTTON = BLOCK_ENTITIES.register("console_button",
            () -> BlockEntityType.Builder.of(ConsoleButtonBlockEntity::new,
                    IBBlocks.CONSOLE_BUTTON.get(),
                    IBBlocks.SMALL_CONSOLE_BUTTON.get(),
                    IBBlocks.LARGE_CONSOLE_BUTTON.get(),
                    IBBlocks.BIG_CONSOLE_BUTTON.get()
            ).build(null));

    public static void register() {
        LOGGER.info("Registering Block Entities for Infinity Buttons");
        BLOCK_ENTITIES.register();
    }
}
