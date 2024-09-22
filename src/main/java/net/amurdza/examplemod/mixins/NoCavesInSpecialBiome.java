package net.amurdza.examplemod.mixins;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(NoiseBasedChunkGenerator.class)
public abstract class NoCavesInSpecialBiome {

    @Redirect(method = "applyCarvers",at= @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/carver/ConfiguredWorldCarver;isStartChunk(Lnet/minecraft/util/RandomSource;)Z"))
    private boolean hi(ConfiguredWorldCarver instance, RandomSource pRandom){
        return false;//!pBiomeGetter.apply(pPos).is(Config.SPECIAL_BIOME) && canReplaceBlock(pConfig, pState)
    }

}
