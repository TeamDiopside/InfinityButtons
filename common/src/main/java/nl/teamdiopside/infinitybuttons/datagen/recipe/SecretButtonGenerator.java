package nl.teamdiopside.infinitybuttons.datagen.recipe;

import net.minecraft.world.level.block.Block;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButton;
import nl.teamdiopside.infinitybuttons.block.faced4.SecretButtonType;
import nl.teamdiopside.infinitybuttons.datagen.conditions.AnyModsLoaded;
import nl.teamdiopside.infinitybuttons.datagen.conditions.ModLoaded;
import nl.teamdiopside.infinitybuttons.registry.IBBlocks;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;

import java.util.Objects;

public class SecretButtonGenerator extends RecipeGenerator {

    private void runConditions(IBRecipeOutput output, SecretButton block) {
        IBRegistryUtils.BlockInfo originalInfo = IBRegistryUtils.BlockInfo.from(block.getCamouflage());
        if (!Objects.equals(originalInfo.namespace(), "minecraft")) {
            addConditions(output, block, new ModLoaded(originalInfo.namespace()));
            if (block.type != SecretButtonType.BOOKSHELF) return;
            if (originalInfo.namespace().equals("quark") || originalInfo.namespace().equals("woodworks")) return;
            addConditions(output, block, new AnyModsLoaded("quark", "woodworks"));
        }
    }

    @Override
    public void generate(IBRecipeOutput output) {
        for (var entry : IBBlocks.SECRET_BUTTONS.entrySet()) {
            SecretButton block = entry.getValue().get();
            Block originalBlock = block.getCamouflage();
            runConditions(output, block);
            convertingRecipe(output, originalBlock, block, false, 1, "", "secret_buttons");
        }
    }
}
