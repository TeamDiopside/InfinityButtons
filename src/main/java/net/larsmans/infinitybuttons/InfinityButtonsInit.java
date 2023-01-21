package net.larsmans.infinitybuttons;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.compat.IBNethersDelightBlocks;
import net.larsmans.infinitybuttons.compat.IBNethersDelightItems;
import net.larsmans.infinitybuttons.item.InfinityButtonsItems;
import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InfinityButtonsInit implements ModInitializer {
	public static final String MOD_ID = "infinitybuttons";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final net.larsmans.infinitybuttons.InfinityButtonsConfig CONFIG = net.larsmans.infinitybuttons.InfinityButtonsConfig.createAndLoad();

	@Override
	public void onInitialize() {
		InfinityButtonsItems.registerModItems();
		InfinityButtonsBlocks.registerModBlocks();
		InfinityButtonsSounds.registerSounds();
		if (FabricLoader.getInstance().isModLoaded("nethersdelight")) {
			IBNethersDelightItems.registerCompatItems();
			IBNethersDelightBlocks.registerCompatBlocks();
		}
	}
}