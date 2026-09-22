package nl.teamdiopside.infinitybuttons.block.faced6.console;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import nl.teamdiopside.infinitybuttons.registry.IBBlockEntities;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ConsoleButtonBlockEntity extends BlockEntity {
    private static final String IS_LEVER_KEY = "IsLever";
    private static final String MIN_PRESS_TICKS_KEY = "MinPressTicks";
    private static final String MAX_PRESS_TICKS_KEY = "MaxPressTicks";
    private static final String KEY_ITEM_KEY = "KeyItem";

    public boolean isLever = false;
    private int minPressTicks = 20;
    private int maxPressTicks = 20;

    private ItemStack keyItem;


    public ConsoleButtonBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(IBBlockEntities.CONSOLE_BUTTON.get(), blockPos, blockState);
    }

    public int getPressTicks() {
        return (int) Math.floor(Math.random() * (maxPressTicks - minPressTicks) + minPressTicks);
    }

    public boolean validatePlayerItem(Player player) {
        if (keyItem == null) return true;

        for (ItemStack playerItem : List.of(player.getMainHandItem(), player.getOffhandItem())) {
            if (ItemStack.isSameItemSameComponents(this.keyItem, playerItem)) return true;
        }

        return false;
    }

    public void configure(boolean isLever, int minPressTicks, int maxPressTicks, @Nullable ItemStack keyItem) {
        this.isLever = isLever;
        this.minPressTicks = minPressTicks;
        this.maxPressTicks = maxPressTicks;
        this.keyItem = keyItem;

        setChanged();
        // press() runs client-side too, so clients need the real keyItem to validate correctly
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putBoolean(IS_LEVER_KEY, isLever);
        tag.putInt(MIN_PRESS_TICKS_KEY, minPressTicks);
        tag.putInt(MAX_PRESS_TICKS_KEY, maxPressTicks);
        tag.put(KEY_ITEM_KEY, keyItem != null ? keyItem.saveOptional(registries) : new CompoundTag());
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        isLever = tag.getBoolean(IS_LEVER_KEY);
        if (tag.contains(MIN_PRESS_TICKS_KEY)) minPressTicks = tag.getInt(MIN_PRESS_TICKS_KEY);
        if (tag.contains(MAX_PRESS_TICKS_KEY)) maxPressTicks = tag.getInt(MAX_PRESS_TICKS_KEY);

        ItemStack loadedKeyItem = ItemStack.parseOptional(registries, tag.getCompound(KEY_ITEM_KEY));
        keyItem = loadedKeyItem.isEmpty() ? null : loadedKeyItem;
    }

    // Used both for chunk-load sync and for the live update packet triggered by sendBlockUpdated in configure()
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = super.getUpdateTag(registries);
        saveAdditional(tag, registries);
        return tag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
