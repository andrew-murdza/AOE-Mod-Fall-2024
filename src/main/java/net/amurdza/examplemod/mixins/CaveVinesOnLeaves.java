package net.amurdza.examplemod.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.GrowingPlantBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
@Mixin(GrowingPlantBlock.class)
public class CaveVinesOnLeaves {
    @Redirect(method = "canSurvive",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;isFaceSturdy(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)Z"))
    boolean canSurvive(final BlockState instance, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return instance.isFaceSturdy(blockGetter,blockPos,direction)||instance.getBlock() instanceof LeavesBlock;
    }
}
