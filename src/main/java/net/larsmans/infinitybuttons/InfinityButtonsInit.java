package net.larsmans.infinitybuttons;

import net.fabricmc.api.ModInitializer;
import net.larsmans.infinitybuttons.block.ModBlocks;
import net.larsmans.infinitybuttons.item.ModItems;
import net.larsmans.infinitybuttons.sounds.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InfinityButtonsInit implements ModInitializer {
	public static final String MOD_ID = "infinitybuttons";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final net.larsmans.infinitybuttons.InfinityButtonsConfig CONFIG = net.larsmans.infinitybuttons.InfinityButtonsConfig.createAndLoad();

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModSounds.registerSounds();
	}
}