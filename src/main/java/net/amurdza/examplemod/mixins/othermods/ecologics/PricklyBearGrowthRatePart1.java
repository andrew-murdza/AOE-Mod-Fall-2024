package net.amurdza.examplemod.mixins.othermods.ecologics;

import net.amurdza.examplemod.Config;
import net.amurdza.examplemod.Helper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import samebutdifferent.ecologics.block.PricklyPearBlock;

@Mixin(PricklyPearBlock.class)
public class PricklyBearGrowthRatePart1 {
    @Redirect(method = "randomTick",at= @At(value = "INVOKE", target = "Lnet/minecraft/util/RandomSource;nextInt(I)I"))
    private int hi(RandomSource instance, int i, BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom){
        return Helper.isSpecialBiome(pLevel,pPos)?Helper.withChanceToInt(pLevel,Config.PRICKLY_PEAR_GROWH_CHANCE):instance.nextInt(i);
    }
}
