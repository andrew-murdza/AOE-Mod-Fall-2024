package net.amurdza.examplemod.mixins;

import net.amurdza.examplemod.Config;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.checkerframework.checker.units.qual.C;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LeavesBlock.class)
public class FurtherLeavesPart2 {
    @ModifyConstant(method = "<init>",constant = @Constant(intValue = 7))
    private int hi(int i){
        return Config.MAX_LEAVES_DISTANCE;
    }
    @ModifyConstant(method = "isRandomlyTicking",constant = @Constant(intValue = 7))
    private int hi1(int i){
        return Config.MAX_LEAVES_DISTANCE;
    }
    @ModifyConstant(method = "decaying",constant = @Constant(intValue = 7))
    private int hi2(int i){
        return Config.MAX_LEAVES_DISTANCE;
    }
}