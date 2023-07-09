package net.larsmans.infinitybuttons.block.custom.button;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoneycombItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class CopperButton extends WaxedCopperButton implements WeatheringButton {

    private final OxidationLevel weatherState;

    public CopperButton(FabricBlockSettings settings, boolean large, OxidationLevel weatherState) {
        super(settings, large);
        this.weatherState = weatherState;
    }

    @Override
    public void randomTick(BlockState pState, ServerWorld pLevel, BlockPos pPos, Random pRandom) {
        this.tickDegradation(pState, pLevel, pPos, pRandom);
    }

    @Override
    public ActionResult onUse(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(handIn);
        if (state.get(PRESSED)) {
            return ActionResult.CONSUME;
        } else if (itemStack.getItem() instanceof HoneycombItem) {
            return wax(state, worldIn, pos, player, itemStack);
        } else if (itemStack.getItem() instanceof AxeItem && getDegradationLevel() != OxidationLevel.UNAFFECTED) {
            return scrape(state, worldIn, pos, player, itemStack);
        } else {
            return super.onUse(state, worldIn, pos, player, handIn, hit);
        }
    }

    @Override
    public boolean hasRandomTicks(BlockState pState) {
        return WeatheringButton.getNext(pState.getBlock()).isPresent() && !pState.get(PRESSED);
    }

    @Override
    public OxidationLevel getDegradationLevel() {
        return this.weatherState;
    }
}
