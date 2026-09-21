package nl.teamdiopside.infinitybuttons.datagen.recipe;

import net.minecraft.world.level.block.Block;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButton;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButtonType;
import nl.teamdiopside.infinitybuttons.datagen.conditions.AnyModsLoaded;
import nl.teamdiopside.infinitybuttons.datagen.conditions.ModLoaded;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;

import java.util.Objects;

public class SecretButtonGenerator extends RecipeGenerator {

    public SecretButtonGenerator(IBRecipeOutput output) {
        super(output);
    }

    private void runConditions(SecretButton block) {
        IBRegistryUtils.BlockInfo originalInfo = IBRegistryUtils.BlockInfo.from(block.getCamouflage());
        if (!Objects.equals(originalInfo.namespace(), "minecraft")) {
            addConditions(block, new ModLoaded(originalInfo.namespace()));
            if (block.type != SecretButtonType.BOOKSHELF) return;
            if (originalInfo.namespace().equals("quark") || originalInfo.namespace().equals("woodworks")) return;
            addConditions(block, new AnyModsLoaded("quark", "woodworks"));
        }
    }

    public void generate(SecretButton block) {
        Block originalBlock = block.getCamouflage();
        runConditions(block);
        convertingRecipe(output, originalBlock, block, false, 1, "", "secret_buttons");
    }
}
