package net.amurdza.examplemod.mixins.othermods.undergarden;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import quek.undergarden.block.UGSaplingBlock;

@Mixin(UGSaplingBlock.class)
public class GrongleGrowthRate {
    @Redirect(method = "randomTick",at= @At(value = "INVOKE", target = "Lquek/undergarden/block/UGSaplingBlock;advanceTree(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/util/RandomSource;)V"))
    private void hi(UGSaplingBlock instance, ServerLevel serverLevel, BlockPos blockPos, BlockState blockState, RandomSource randomSource){

    }
}
