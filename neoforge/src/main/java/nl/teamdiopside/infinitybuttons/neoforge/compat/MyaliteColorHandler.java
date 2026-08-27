// See org.violetmoon.quark.content.world.module.NewStoneTypesModule.Client.MyaliteColorHandler
// That class was private ._.
package nl.teamdiopside.infinitybuttons.neoforge.compat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import org.violetmoon.quark.content.world.block.MyaliteColorLogic;

public class MyaliteColorHandler implements BlockColor, ItemColor {
    public static final MyaliteColorHandler INSTANCE = new MyaliteColorHandler();

    public int getColor(@NotNull BlockState state, BlockAndTintGetter level, BlockPos pos, int tintIndex) {
        return MyaliteColorLogic.getColor(pos);
    }

    public int getColor(@NotNull ItemStack stack, int tintIndex) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) {
            return MyaliteColorLogic.getColor(BlockPos.ZERO);
        } else {
            BlockPos pos = mc.player.blockPosition();
            HitResult res = mc.hitResult;
            if (res != null && res.getType() == HitResult.Type.BLOCK) {
                pos = ((BlockHitResult)res).getBlockPos();
            }

            return MyaliteColorLogic.getColor(pos);
        }
    }
}
