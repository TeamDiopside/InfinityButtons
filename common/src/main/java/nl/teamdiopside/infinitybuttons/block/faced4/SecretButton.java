package nl.teamdiopside.infinitybuttons.block.faced4;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import nl.teamdiopside.infinitybuttons.block.ButtonFaced4;
import nl.teamdiopside.infinitybuttons.compat.jade.JadeCamouflaged;
import nl.teamdiopside.infinitybuttons.registry.IBRegistryUtils;
import org.jetbrains.annotations.NotNull;

public class SecretButton extends ButtonFaced4 implements JadeCamouflaged {

    public static final MapCodec<SecretButton> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(propertiesCodec(),
                    SecretButtonType.CODEC.fieldOf("type").forGetter(secretButton -> secretButton.type),
                    ResourceLocation.CODEC.fieldOf("camouflage").forGetter(secretButton -> secretButton.camouflage)
            ).apply(instance, SecretButton::new)
    );

    public static final int PRESS_TICKS = 50;

    public final SecretButtonType type;
    protected final ResourceLocation camouflage;

    public SecretButton(Properties properties, SecretButtonType type, ResourceLocation camouflage) {
        super(properties, type.shapePressed, type.shapeUnpressed, false);
        this.type = type;
        this.camouflage = camouflage;
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        if (blockHitResult.getDirection() != blockState.getValue(FACING)) {
            return InteractionResult.FAIL;
        }

        return super.useWithoutItem(blockState, level, blockPos, player, blockHitResult);
    }

    @Override
    protected SoundEvent getSound(boolean press) {
        return this.type.sound.get();
    }

    @Override
    protected int getPressTicks() {
        return PRESS_TICKS;
    }

    @Override
    protected @NotNull MapCodec<? extends SecretButton> codec() {
        return CODEC;
    }

    @Override
    public Block getCamouflage() {
        return IBRegistryUtils.getBlockByID(this.camouflage);
    }
}
