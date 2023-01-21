package net.larsmans.infinitybuttons.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.compat.IBNethersDelightBlocks;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class LootTableGen extends SimpleFabricLootTableProvider {
    public LootTableGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator, LootContextTypes.BLOCK);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> i) {
        generateButton(i, "deepslate", InfinityButtonsBlocks.DEEPSLATE_BUTTON);
        generateButton(i, "granite", InfinityButtonsBlocks.GRANITE_BUTTON);
        generateButton(i, "diorite", InfinityButtonsBlocks.DIORITE_BUTTON);
        generateButton(i, "andesite", InfinityButtonsBlocks.ANDESITE_BUTTON);
        generateButton(i, "calcite", InfinityButtonsBlocks.CALCITE_BUTTON);
        generateButton(i, "tuff", InfinityButtonsBlocks.TUFF_BUTTON);
        generateButton(i, "dripstone", InfinityButtonsBlocks.DRIPSTONE_BUTTON);
        generateButton(i, "copper", InfinityButtonsBlocks.COPPER_BUTTON);
        generateButton(i, "exposed_copper", InfinityButtonsBlocks.EXPOSED_COPPER_BUTTON);
        generateButton(i, "weathered_copper", InfinityButtonsBlocks.WEATHERED_COPPER_BUTTON);
        generateButton(i, "oxidized_copper", InfinityButtonsBlocks.OXIDIZED_COPPER_BUTTON);
        generateButton(i, "sticky_copper", InfinityButtonsBlocks.STICKY_COPPER_BUTTON);
        generateButton(i, "sticky_exposed_copper", InfinityButtonsBlocks.STICKY_EXPOSED_COPPER_BUTTON);
        generateButton(i, "sticky_weathered_copper", InfinityButtonsBlocks.STICKY_WEATHERED_COPPER_BUTTON);
        generateButton(i, "sticky_oxidized_copper", InfinityButtonsBlocks.STICKY_OXIDIZED_COPPER_BUTTON);
        generateButton(i, "iron", InfinityButtonsBlocks.IRON_BUTTON);
        generateButton(i, "gold", InfinityButtonsBlocks.GOLD_BUTTON);
        generateButton(i, "emerald", InfinityButtonsBlocks.EMERALD_BUTTON);
        generateButton(i, "diamond", InfinityButtonsBlocks.DIAMOND_BUTTON);
        generateButton(i, "prismarine", InfinityButtonsBlocks.PRISMARINE_BUTTON);
        generateButton(i, "prismarine_brick", InfinityButtonsBlocks.PRISMARINE_BRICK_BUTTON);
        generateButton(i, "dark_prismarine", InfinityButtonsBlocks.DARK_PRISMARINE_BUTTON);
        generateButton(i, "sand", InfinityButtonsBlocks.SAND_BUTTON);
        generateButton(i, "red_sand", InfinityButtonsBlocks.RED_SAND_BUTTON);
        generateButton(i, "gravel", InfinityButtonsBlocks.GRAVEL_BUTTON);
        generateButton(i, "white_concrete_powder", InfinityButtonsBlocks.WHITE_CONCRETE_POWDER_BUTTON);
        generateButton(i, "light_gray_concrete_powder", InfinityButtonsBlocks.LIGHT_GRAY_CONCRETE_POWDER_BUTTON);
        generateButton(i, "gray_concrete_powder", InfinityButtonsBlocks.GRAY_CONCRETE_POWDER_BUTTON);
        generateButton(i, "black_concrete_powder", InfinityButtonsBlocks.BLACK_CONCRETE_POWDER_BUTTON);
        generateButton(i, "brown_concrete_powder", InfinityButtonsBlocks.BROWN_CONCRETE_POWDER_BUTTON);
        generateButton(i, "red_concrete_powder", InfinityButtonsBlocks.RED_CONCRETE_POWDER_BUTTON);
        generateButton(i, "orange_concrete_powder", InfinityButtonsBlocks.ORANGE_CONCRETE_POWDER_BUTTON);
        generateButton(i, "yellow_concrete_powder", InfinityButtonsBlocks.YELLOW_CONCRETE_POWDER_BUTTON);
        generateButton(i, "lime_concrete_powder", InfinityButtonsBlocks.LIME_CONCRETE_POWDER_BUTTON);
        generateButton(i, "green_concrete_powder", InfinityButtonsBlocks.GREEN_CONCRETE_POWDER_BUTTON);
        generateButton(i, "cyan_concrete_powder", InfinityButtonsBlocks.CYAN_CONCRETE_POWDER_BUTTON);
        generateButton(i, "light_blue_concrete_powder", InfinityButtonsBlocks.LIGHT_BLUE_CONCRETE_POWDER_BUTTON);
        generateButton(i, "blue_concrete_powder", InfinityButtonsBlocks.BLUE_CONCRETE_POWDER_BUTTON);
        generateButton(i, "purple_concrete_powder", InfinityButtonsBlocks.PURPLE_CONCRETE_POWDER_BUTTON);
        generateButton(i, "magenta_concrete_powder", InfinityButtonsBlocks.MAGENTA_CONCRETE_POWDER_BUTTON);
        generateButton(i, "pink_concrete_powder", InfinityButtonsBlocks.PINK_CONCRETE_POWDER_BUTTON);

        generateLargeButton(i, "oak", InfinityButtonsBlocks.OAK_LARGE_BUTTON);
        generateLargeButton(i, "spruce", InfinityButtonsBlocks.SPRUCE_LARGE_BUTTON);
        generateLargeButton(i, "birch", InfinityButtonsBlocks.BIRCH_LARGE_BUTTON);
        generateLargeButton(i, "jungle", InfinityButtonsBlocks.JUNGLE_LARGE_BUTTON);
        generateLargeButton(i, "acacia", InfinityButtonsBlocks.ACACIA_LARGE_BUTTON);
        generateLargeButton(i, "dark_oak", InfinityButtonsBlocks.DARK_OAK_LARGE_BUTTON);
        generateLargeButton(i, "mangrove", InfinityButtonsBlocks.MANGROVE_LARGE_BUTTON);
        generateLargeButton(i, "crimson", InfinityButtonsBlocks.CRIMSON_LARGE_BUTTON);
        generateLargeButton(i, "warped", InfinityButtonsBlocks.WARPED_LARGE_BUTTON);
        generateLargeButton(i, "stone", InfinityButtonsBlocks.STONE_LARGE_BUTTON);
        generateLargeButton(i, "deepslate", InfinityButtonsBlocks.DEEPSLATE_LARGE_BUTTON);
        generateLargeButton(i, "granite", InfinityButtonsBlocks.GRANITE_LARGE_BUTTON);
        generateLargeButton(i, "diorite", InfinityButtonsBlocks.DIORITE_LARGE_BUTTON);
        generateLargeButton(i, "andesite", InfinityButtonsBlocks.ANDESITE_LARGE_BUTTON);
        generateLargeButton(i, "calcite", InfinityButtonsBlocks.CALCITE_LARGE_BUTTON);
        generateLargeButton(i, "tuff", InfinityButtonsBlocks.TUFF_LARGE_BUTTON);
        generateLargeButton(i, "dripstone", InfinityButtonsBlocks.DRIPSTONE_LARGE_BUTTON);
        generateLargeButton(i, "copper", InfinityButtonsBlocks.COPPER_LARGE_BUTTON);
        generateLargeButton(i, "exposed_copper", InfinityButtonsBlocks.EXPOSED_COPPER_LARGE_BUTTON);
        generateLargeButton(i, "weathered_copper", InfinityButtonsBlocks.WEATHERED_COPPER_LARGE_BUTTON);
        generateLargeButton(i, "oxidized_copper", InfinityButtonsBlocks.OXIDIZED_COPPER_LARGE_BUTTON);
        generateLargeButton(i, "sticky_copper", InfinityButtonsBlocks.STICKY_COPPER_LARGE_BUTTON);
        generateLargeButton(i, "sticky_exposed_copper", InfinityButtonsBlocks.STICKY_EXPOSED_COPPER_LARGE_BUTTON);
        generateLargeButton(i, "sticky_weathered_copper", InfinityButtonsBlocks.STICKY_WEATHERED_COPPER_LARGE_BUTTON);
        generateLargeButton(i, "sticky_oxidized_copper", InfinityButtonsBlocks.STICKY_OXIDIZED_COPPER_LARGE_BUTTON);
        generateLargeButton(i, "iron", InfinityButtonsBlocks.IRON_LARGE_BUTTON);
        generateLargeButton(i, "gold", InfinityButtonsBlocks.GOLD_LARGE_BUTTON);
        generateLargeButton(i, "emerald", InfinityButtonsBlocks.EMERALD_LARGE_BUTTON);
        generateLargeButton(i, "diamond", InfinityButtonsBlocks.DIAMOND_LARGE_BUTTON);
        generateLargeButton(i, "prismarine", InfinityButtonsBlocks.PRISMARINE_LARGE_BUTTON);
        generateLargeButton(i, "prismarine_brick", InfinityButtonsBlocks.PRISMARINE_BRICK_LARGE_BUTTON);
        generateLargeButton(i, "dark_prismarine", InfinityButtonsBlocks.DARK_PRISMARINE_LARGE_BUTTON);
        generateLargeButton(i, "sand", InfinityButtonsBlocks.SAND_LARGE_BUTTON);
        generateLargeButton(i, "red_sand", InfinityButtonsBlocks.RED_SAND_LARGE_BUTTON);
        generateLargeButton(i, "gravel", InfinityButtonsBlocks.GRAVEL_LARGE_BUTTON);
        generateLargeButton(i, "white_concrete_powder", InfinityButtonsBlocks.WHITE_CONCRETE_POWDER_LARGE_BUTTON);
        generateLargeButton(i, "light_gray_concrete_powder", InfinityButtonsBlocks.LIGHT_GRAY_CONCRETE_POWDER_LARGE_BUTTON);
        generateLargeButton(i, "gray_concrete_powder", InfinityButtonsBlocks.GRAY_CONCRETE_POWDER_LARGE_BUTTON);
        generateLargeButton(i, "black_concrete_powder", InfinityButtonsBlocks.BLACK_CONCRETE_POWDER_LARGE_BUTTON);
        generateLargeButton(i, "brown_concrete_powder", InfinityButtonsBlocks.BROWN_CONCRETE_POWDER_LARGE_BUTTON);
        generateLargeButton(i, "red_concrete_powder", InfinityButtonsBlocks.RED_CONCRETE_POWDER_LARGE_BUTTON);
        generateLargeButton(i, "orange_concrete_powder", InfinityButtonsBlocks.ORANGE_CONCRETE_POWDER_LARGE_BUTTON);
        generateLargeButton(i, "yellow_concrete_powder", InfinityButtonsBlocks.YELLOW_CONCRETE_POWDER_LARGE_BUTTON);
        generateLargeButton(i, "lime_concrete_powder", InfinityButtonsBlocks.LIME_CONCRETE_POWDER_LARGE_BUTTON);
        generateLargeButton(i, "green_concrete_powder", InfinityButtonsBlocks.GREEN_CONCRETE_POWDER_LARGE_BUTTON);
        generateLargeButton(i, "cyan_concrete_powder", InfinityButtonsBlocks.CYAN_CONCRETE_POWDER_LARGE_BUTTON);
        generateLargeButton(i, "light_blue_concrete_powder", InfinityButtonsBlocks.LIGHT_BLUE_CONCRETE_POWDER_LARGE_BUTTON);
        generateLargeButton(i, "blue_concrete_powder", InfinityButtonsBlocks.BLUE_CONCRETE_POWDER_LARGE_BUTTON);
        generateLargeButton(i, "purple_concrete_powder", InfinityButtonsBlocks.PURPLE_CONCRETE_POWDER_LARGE_BUTTON);
        generateLargeButton(i, "magenta_concrete_powder", InfinityButtonsBlocks.MAGENTA_CONCRETE_POWDER_LARGE_BUTTON);
        generateLargeButton(i, "pink_concrete_powder", InfinityButtonsBlocks.PINK_CONCRETE_POWDER_LARGE_BUTTON);

        generateEmergencyButton(i, "white", InfinityButtonsBlocks.WHITE_EMERGENCY_BUTTON);
        generateEmergencyButton(i, "light_gray", InfinityButtonsBlocks.LIGHT_GRAY_EMERGENCY_BUTTON);
        generateEmergencyButton(i, "gray", InfinityButtonsBlocks.GRAY_EMERGENCY_BUTTON);
        generateEmergencyButton(i, "black", InfinityButtonsBlocks.BLACK_EMERGENCY_BUTTON);
        generateEmergencyButton(i, "brown", InfinityButtonsBlocks.BROWN_EMERGENCY_BUTTON);
        generateEmergencyButton(i, "red", InfinityButtonsBlocks.RED_EMERGENCY_BUTTON);
        generateEmergencyButton(i, "orange", InfinityButtonsBlocks.ORANGE_EMERGENCY_BUTTON);
        generateEmergencyButton(i, "yellow", InfinityButtonsBlocks.YELLOW_EMERGENCY_BUTTON);
        generateEmergencyButton(i, "lime", InfinityButtonsBlocks.LIME_EMERGENCY_BUTTON);
        generateEmergencyButton(i, "green", InfinityButtonsBlocks.GREEN_EMERGENCY_BUTTON);
        generateEmergencyButton(i, "cyan", InfinityButtonsBlocks.CYAN_EMERGENCY_BUTTON);
        generateEmergencyButton(i, "light_blue", InfinityButtonsBlocks.LIGHT_BLUE_EMERGENCY_BUTTON);
        generateEmergencyButton(i, "blue", InfinityButtonsBlocks.BLUE_EMERGENCY_BUTTON);
        generateEmergencyButton(i, "purple", InfinityButtonsBlocks.PURPLE_EMERGENCY_BUTTON);
        generateEmergencyButton(i, "magenta", InfinityButtonsBlocks.MAGENTA_EMERGENCY_BUTTON);
        generateEmergencyButton(i, "pink", InfinityButtonsBlocks.PINK_EMERGENCY_BUTTON);

        generateSafetyButton(i, "white", InfinityButtonsBlocks.WHITE_SAFE_EMERGENCY_BUTTON);
        generateSafetyButton(i, "light_gray", InfinityButtonsBlocks.LIGHT_GRAY_SAFE_EMERGENCY_BUTTON);
        generateSafetyButton(i, "gray", InfinityButtonsBlocks.GRAY_SAFE_EMERGENCY_BUTTON);
        generateSafetyButton(i, "black", InfinityButtonsBlocks.BLACK_SAFE_EMERGENCY_BUTTON);
        generateSafetyButton(i, "brown", InfinityButtonsBlocks.BROWN_SAFE_EMERGENCY_BUTTON);
        generateSafetyButton(i, "red", InfinityButtonsBlocks.RED_SAFE_EMERGENCY_BUTTON);
        generateSafetyButton(i, "orange", InfinityButtonsBlocks.ORANGE_SAFE_EMERGENCY_BUTTON);
        generateSafetyButton(i, "yellow", InfinityButtonsBlocks.YELLOW_SAFE_EMERGENCY_BUTTON);
        generateSafetyButton(i, "lime", InfinityButtonsBlocks.LIME_SAFE_EMERGENCY_BUTTON);
        generateSafetyButton(i, "green", InfinityButtonsBlocks.GREEN_SAFE_EMERGENCY_BUTTON);
        generateSafetyButton(i, "cyan", InfinityButtonsBlocks.CYAN_SAFE_EMERGENCY_BUTTON);
        generateSafetyButton(i, "light_blue", InfinityButtonsBlocks.LIGHT_BLUE_SAFE_EMERGENCY_BUTTON);
        generateSafetyButton(i, "blue", InfinityButtonsBlocks.BLUE_SAFE_EMERGENCY_BUTTON);
        generateSafetyButton(i, "purple", InfinityButtonsBlocks.PURPLE_SAFE_EMERGENCY_BUTTON);
        generateSafetyButton(i, "magenta", InfinityButtonsBlocks.MAGENTA_SAFE_EMERGENCY_BUTTON);
        generateSafetyButton(i, "pink", InfinityButtonsBlocks.PINK_SAFE_EMERGENCY_BUTTON);

        generateSecretButton(i, "bookshelf", InfinityButtonsBlocks.BOOKSHELF_SECRET_BUTTON);
        generateSecretButton(i, "brick", InfinityButtonsBlocks.BRICK_SECRET_BUTTON);
        generateSecretButton(i, "stone_brick", InfinityButtonsBlocks.STONE_BRICK_SECRET_BUTTON);
        generateSecretButton(i, "mossy_stone_brick", InfinityButtonsBlocks.MOSSY_STONE_BRICK_SECRET_BUTTON);
        generateSecretButton(i, "cracked_stone_brick", InfinityButtonsBlocks.CRACKED_STONE_BRICK_SECRET_BUTTON);
        generateSecretButton(i, "chiseled_stone_brick", InfinityButtonsBlocks.CHISELED_STONE_BRICK_SECRET_BUTTON);
        generateSecretButton(i, "deepslate_brick", InfinityButtonsBlocks.DEEPSLATE_BRICK_SECRET_BUTTON);
        generateSecretButton(i, "cracked_deepslate_brick", InfinityButtonsBlocks.CRACKED_DEEPSLATE_BRICK_SECRET_BUTTON);
        generateSecretButton(i, "deepslate_tile", InfinityButtonsBlocks.DEEPSLATE_TILE_SECRET_BUTTON);
        generateSecretButton(i, "cracked_deepslate_tile", InfinityButtonsBlocks.CRACKED_DEEPSLATE_TILE_SECRET_BUTTON);
        generateSecretButton(i, "oak_plank", InfinityButtonsBlocks.OAK_PLANK_SECRET_BUTTON);
        generateSecretButton(i, "spruce_plank", InfinityButtonsBlocks.SPRUCE_PLANK_SECRET_BUTTON);
        generateSecretButton(i, "birch_plank", InfinityButtonsBlocks.BIRCH_PLANK_SECRET_BUTTON);
        generateSecretButton(i, "jungle_plank", InfinityButtonsBlocks.JUNGLE_PLANK_SECRET_BUTTON);
        generateSecretButton(i, "acacia_plank", InfinityButtonsBlocks.ACACIA_PLANK_SECRET_BUTTON);
        generateSecretButton(i, "dark_oak_plank", InfinityButtonsBlocks.DARK_OAK_PLANK_SECRET_BUTTON);
        generateSecretButton(i, "mangrove_plank", InfinityButtonsBlocks.MANGROVE_PLANK_SECRET_BUTTON);
        generateSecretButton(i, "crimson_plank", InfinityButtonsBlocks.CRIMSON_PLANK_SECRET_BUTTON);
        generateSecretButton(i, "warped_plank", InfinityButtonsBlocks.WARPED_PLANK_SECRET_BUTTON);
        generateSecretButton(i, "_brick", InfinityButtonsBlocks.MUD_BRICK_SECRET_BUTTON);
        generateSecretButton(i, "end_stone_brick", InfinityButtonsBlocks.END_STONE_BRICK_SECRET_BUTTON);
        generateSecretButton(i, "quartz_brick", InfinityButtonsBlocks.QUARTZ_BRICK_SECRET_BUTTON);
        generateSecretButton(i, "dark_prismarine", InfinityButtonsBlocks.DARK_PRISMARINE_SECRET_BUTTON);
        generateSecretButton(i, "polished_blackstone_brick", InfinityButtonsBlocks.POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON);
        generateSecretButton(i, "cracked_polished_blackstone_brick", InfinityButtonsBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON);
        generateSecretButton(i, "chiseled_polished_blackstone", InfinityButtonsBlocks.CHISELED_POLISHED_BLACKSTONE_SECRET_BUTTON);
        generateSecretButton(i, "nether_brick", InfinityButtonsBlocks.NETHER_BRICK_SECRET_BUTTON);
        generateSecretButton(i, "cracked_nether_brick", InfinityButtonsBlocks.CRACKED_NETHER_BRICK_SECRET_BUTTON);
        generateSecretButton(i, "chiseled_nether_brick", InfinityButtonsBlocks.CHISELED_NETHER_BRICK_SECRET_BUTTON);
        generateSecretButton(i, "red_nether_brick", InfinityButtonsBlocks.RED_NETHER_BRICK_SECRET_BUTTON);

        generateLootTable(i, "doorbell", InfinityButtonsBlocks.DOORBELL);
        generateLootTable(i, "doorbell_button", InfinityButtonsBlocks.DOORBELL_BUTTON);
        generateLootTable(i, "lamp_button", InfinityButtonsBlocks.LAMP_BUTTON);
        generateLootTable(i, "lamp_lever", InfinityButtonsBlocks.LAMP_LEVER);
        generateLootTable(i, "hoglin_mount_button", IBNethersDelightBlocks.HOGLIN_MOUNT_BUTTON);

        generateLootTable(i, "torch_button", InfinityButtonsBlocks.TORCH_BUTTON);
        generateLootTable(i, "torch_lever", InfinityButtonsBlocks.TORCH_LEVER);
        generateLootTable(i, "soul_torch_button", InfinityButtonsBlocks.SOUL_TORCH_BUTTON);
        generateLootTable(i, "soul_torch_lever", InfinityButtonsBlocks.SOUL_TORCH_LEVER);
        generateLootTable(i, "redstone_torch_button", InfinityButtonsBlocks.REDSTONE_TORCH_BUTTON);
        generateLootTable(i, "redstone_torch_lever", InfinityButtonsBlocks.REDSTONE_TORCH_LEVER);
        generateLootTable(i, "propelplant_torch_button", IBNethersDelightBlocks.PROPELPLANT_TORCH_BUTTON);
        generateLootTable(i, "propelplant_torch_lever", IBNethersDelightBlocks.PROPELPLANT_TORCH_LEVER);
    }
    
    public void generateButton(BiConsumer<Identifier, LootTable.Builder> i, String name, ItemConvertible item) {
        generateLootTable(i, name + "_button", item);
    }

    public void generateLargeButton(BiConsumer<Identifier, LootTable.Builder> i, String name, ItemConvertible item) {
        generateLootTable(i, name + "_large_button", item);
    }

    public void generateEmergencyButton(BiConsumer<Identifier, LootTable.Builder> i, String name, ItemConvertible item) {
        generateLootTable(i, name + "_emergency_button", item);
    }

    public void generateSafetyButton(BiConsumer<Identifier, LootTable.Builder> i, String name, ItemConvertible item) {
        generateLootTable(i, name + "_safe_emergency_button", item);
    }

    public void generateSecretButton(BiConsumer<Identifier, LootTable.Builder> i, String name, ItemConvertible item) {
        generateLootTable(i, name + "_secret_button", item);
    }

    public void generateLootTable(BiConsumer<Identifier, LootTable.Builder> i, String name, ItemConvertible item) {
        i.accept(new Identifier("infinitybuttons", "blocks/" + name), BlockLootTableGenerator.drops(item));
    }
}
