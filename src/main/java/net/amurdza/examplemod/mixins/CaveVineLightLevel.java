package net.amurdza.examplemod.mixins;

import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
@Mixin(Blocks.class)
public class CaveVineLightLevel {
    @ModifyArg(method = "<clinit>",at=@At(value = "INVOKE",target = "Lnet/minecraft/world/level/block/CaveVines;emission(I)Ljava/util/function/ToIntFunction;"))
    private static int moreLightCaveVines(int i){
        return 15;
    }
    @ModifyArg(method = "<clinit>",at=@At(value = "INVOKE",target = "Lnet/minecraft/world/level/block/GlowLichenBlock;emission(I)Ljava/util/function/ToIntFunction;"))
    private static int moreLightGlowLichen(int i){
        return 15;
    }
}
