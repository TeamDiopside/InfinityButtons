package nl.teamdiopside.infinitybuttons.mixin;

import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import nl.teamdiopside.infinitybuttons.block.MaybeButtonActivated;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ButtonBlock.class)
public class ButtonBlockMixin implements MaybeButtonActivated {
    @Shadow
    @Final
    private BlockSetType type;

    @Redirect(method = "entityInside",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/properties/BlockSetType;canButtonBeActivatedByArrows()Z"))
    private boolean infinitybuttons$arrowActivatedEntityCheck(BlockSetType blockSetType) {
        return this.infinityButtons$activatedByArrows();
    }

    @Redirect(method = "checkPressed",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/properties/BlockSetType;canButtonBeActivatedByArrows()Z"))
    private boolean infinitybuttons$arrowActivatedPressCheck(BlockSetType blockSetType) {
        return this.infinityButtons$activatedByArrows();
    }

    @Override
    public boolean infinityButtons$activatedByArrows() {
        return this.type.canButtonBeActivatedByArrows();
    }
}