package nl.teamdiopside.infinitybuttons.compat.blocks;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import nl.teamdiopside.diopside.registry.BlockEntryBuilder;
import nl.teamdiopside.infinitybuttons.block.faced4.HoglinTrophyButton;
import nl.teamdiopside.infinitybuttons.block.faced4.TorchButton;
import nl.teamdiopside.infinitybuttons.compat.IBModdedBlocks;

import java.util.function.UnaryOperator;

import static com.mojang.text2speech.Narrator.LOGGER;

public class MyNethersDelightBlocks extends IBModdedBlocks {
    public static final MyNethersDelightBlocks INSTANCE = new MyNethersDelightBlocks();
    public static final String NAMESPACE = "mynethersdelight";

    public static UnaryOperator<BlockBehaviour.Properties> POWDERY_PROPERTIES = properties -> properties.mapColor(MapColor.PLANT).noCollission().instabreak().lightLevel((state) -> 8).sound(SoundType.BAMBOO).pushReaction(PushReaction.DESTROY);

    public static final RegistrySupplier<HoglinTrophyButton> HOGLIN_TROPHY_BUTTON = INSTANCE.registerBlock("hoglin_trophy_button", BlockEntryBuilder.ofBlock(properties ->
            new HoglinTrophyButton(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS).mapColor(MapColor.TERRACOTTA_PINK))), null);

    public static final RegistrySupplier<TorchButton> POWDERY_TORCH_BUTTON = INSTANCE.registerBlock("powdery_torch_button", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(POWDERY_PROPERTIES.apply(properties), ParticleTypes.FLAME, false, false, ResourceLocation.fromNamespaceAndPath(NAMESPACE, "powdery_torch"))).withoutItem(), null);

    public static final RegistrySupplier<TorchButton> POWDERY_WALL_TORCH_BUTTON = INSTANCE.registerBlock("powdery_wall_torch_button", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(POWDERY_PROPERTIES.apply(properties).dropsLike(POWDERY_TORCH_BUTTON.get()), ParticleTypes.FLAME, false, true, ResourceLocation.fromNamespaceAndPath(NAMESPACE, "powdery_torch"))).withoutItem(), null);

    public static final RegistrySupplier<TorchButton> POWDERY_TORCH_LEVER = INSTANCE.registerBlock("powdery_torch_lever", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(POWDERY_PROPERTIES.apply(properties), ParticleTypes.FLAME, true, false, ResourceLocation.fromNamespaceAndPath(NAMESPACE, "powdery_torch"))).withoutItem(), null);

    public static final RegistrySupplier<TorchButton> POWDERY_WALL_TORCH_LEVER = INSTANCE.registerBlock("powdery_wall_torch_lever", BlockEntryBuilder.ofBlock(properties ->
            new TorchButton(POWDERY_PROPERTIES.apply(properties).dropsLike(POWDERY_TORCH_LEVER.get()), ParticleTypes.FLAME, true, true, ResourceLocation.fromNamespaceAndPath(NAMESPACE, "powdery_torch"))).withoutItem(), null);

    private MyNethersDelightBlocks() {
        super(NAMESPACE);
    }

    @Override
    public void registerMine() {
        LOGGER.info("Infinity Buttons: Registering My Nether's Delight buttons");
    }
}
