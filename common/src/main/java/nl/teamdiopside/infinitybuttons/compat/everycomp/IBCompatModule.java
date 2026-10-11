package nl.teamdiopside.infinitybuttons.compat.everycomp;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButton;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButtonType;
import nl.teamdiopside.infinitybuttons.block.faced6.normal.NormalButton;

public class IBCompatModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> largeButtons;
    public final SimpleEntrySet<WoodType, Block> plankSecretButtons;

    public IBCompatModule(String modId) {
        super(modId, "ib");
        ResourceLocation tab = modRes(modId);

        largeButtons = SimpleEntrySet.builder(WoodType.class, "large_button",
                        getModBlock("oak_large_button"), () -> VanillaWoodTypes.OAK,
                        w -> new NormalButton(
                                BlockSetType.OAK,
                                30, Utils.copyPropertySafe(Blocks.OAK_BUTTON),
                                true,
                                false,
                                w.getSound()
                        )
                )
                //TEXTURES: planks
                .addTag(modRes("wooden_large_buttons"), Registries.BLOCK, Registries.ITEM)
                .setTab(getTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(largeButtons);

        plankSecretButtons = SimpleEntrySet.builder(WoodType.class, "plank_secret_button",
                        getModBlock("oak_plank_secret_button"), () -> VanillaWoodTypes.OAK,
                        w -> new SecretButton(
                                Utils.copyPropertySafe(w.planks),
                                SecretButtonType.PLANK,
                                Utils.getID(w.planks)
                        )
                )
                //TEXTURES: planks
                .addTag(modRes("wooden_secret_buttons"), Registries.BLOCK, Registries.ITEM)
                .setTab(getTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(plankSecretButtons);
    }
}