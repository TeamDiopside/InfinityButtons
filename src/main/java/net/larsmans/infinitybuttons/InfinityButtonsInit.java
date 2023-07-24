package net.larsmans.infinitybuttons;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.larsmans.infinitybuttons.advancement.InfinityButtonsTriggers;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.block.custom.letterbutton.LetterButton;
import net.larsmans.infinitybuttons.block.custom.letterbutton.LetterButtonEnum;
import net.larsmans.infinitybuttons.compat.IBCreateBlocks;
import net.larsmans.infinitybuttons.compat.IBNethersDelightBlocks;
import net.larsmans.infinitybuttons.compat.IBNethersDelightItems;
import net.larsmans.infinitybuttons.item.InfinityButtonsItems;
import net.larsmans.infinitybuttons.item.SafeEmergencyButtonItem;
import net.larsmans.infinitybuttons.particle.InfinityButtonsParticleTypes;
import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InfinityButtonsInit implements ModInitializer {
	public static final String MOD_ID = "infinitybuttons";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final net.larsmans.infinitybuttons.config.InfinityButtonsConfig CONFIG = net.larsmans.infinitybuttons.config.InfinityButtonsConfig.createAndLoad();

	public static final Identifier LETTER_BUTTON_BLOCK_PACKET = new Identifier(MOD_ID, "letter_button_block");

	@Override
	public void onInitialize() {
		registerPackets();
		InfinityButtonsItems.registerModItems();
		InfinityButtonsBlocks.registerModBlocks();
		InfinityButtonsSounds.registerSounds();
		InfinityButtonsTriggers.register();
		InfinityButtonsParticleTypes.register();
		registerDispenserBehavior();

		if (FabricLoader.getInstance().isModLoaded("nethersdelight")) {
			IBNethersDelightItems.registerCompatItems();
			IBNethersDelightBlocks.registerCompatBlocks();
		}

		if (FabricLoader.getInstance().isModLoaded("create")) {
			IBCreateBlocks.registerCompatBlocks();
		}
	}

	public static void registerPackets() {
		ServerPlayNetworking.registerGlobalReceiver(LETTER_BUTTON_BLOCK_PACKET, (server, player, handler, buf, sender) -> {
			BlockPos pos = buf.readBlockPos();
			LetterButtonEnum letterButtonEnum = buf.readEnumConstant(LetterButtonEnum.class);
			World world = player.getWorld();
			if (world.getBlockState(pos).getBlock() instanceof LetterButton) {
				world.setBlockState(pos, world.getBlockState(pos).with(LetterButton.CHARACTER, letterButtonEnum));
			}
		});
	}

	public static void registerDispenserBehavior() {
		for (Item item : Registry.ITEM.stream().toList()) {
			if (item instanceof SafeEmergencyButtonItem) {
				DispenserBlock.registerBehavior(item, new FallibleItemDispenserBehavior() {
					@Override
					protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
						this.setSuccess(ArmorItem.dispenseArmor(pointer, stack));
						return stack;
					}
				});
			}
		}
	}
}