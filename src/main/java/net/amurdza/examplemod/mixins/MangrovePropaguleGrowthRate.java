package net.amurdza.examplemod.mixins;

import net.amurdza.examplemod.Config;
import net.amurdza.examplemod.Helper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.MangrovePropaguleBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MangrovePropaguleBlock.class)
public class MangrovePropaguleGrowthRate {
    @Redirect(method = "randomTick",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/util/RandomSource;nextInt(I)I"))
    private int nextInt(RandomSource random, int n, BlockState state, ServerLevel world, BlockPos pos){
        if(Helper.isSpecialBiome(world,pos)){
            return Helper.withChance(world, Config.MANGROVE_PROPAGULE_AGE_GROWTH_CHANCE)?0:1;
        }
        return random.nextInt(n);
    }
}
