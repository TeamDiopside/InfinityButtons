package nl.teamdiopside.infinitybuttons.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.EnchantingTableBlock;
import nl.teamdiopside.infinitybuttons.registry.IBTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnchantingTableBlock.class)
public abstract class EnchantingTableBlockMixin extends BaseEntityBlock {

    protected EnchantingTableBlockMixin(Properties properties) {
        super(properties);
    }

    @ModifyExpressionValue(
            method = "isValidBookShelf",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/tags/TagKey;)Z", ordinal = 0))
    private static boolean addModdedBookshelves(boolean original, Level level, BlockPos tablePos, BlockPos bookshelfOffset) {
        return original || level.getBlockState(tablePos.offset(bookshelfOffset)).is(IBTags.Blocks.BOOKSHELF_SECRET_BUTTONS);
    }
}
