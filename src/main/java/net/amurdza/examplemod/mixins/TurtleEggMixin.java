package net.amurdza.examplemod.mixins;

import net.amurdza.examplemod.Config;
import net.amurdza.examplemod.Helper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
@Mixin(TurtleEggBlock.class)
public class TurtleEggMixin {


    @Redirect(method = "randomTick",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/TurtleEggBlock;shouldUpdateHatchLevel(Lnet/minecraft/world/level/Level;)Z"))
    private boolean shouldHatchProgress(TurtleEggBlock instance, Level pLevel, BlockState pState, ServerLevel level, BlockPos pPos, RandomSource pRandom){
        if(Helper.isSpecialBiome(pLevel,pPos)){
            return Helper.withChance(pLevel, Config.TURTLE_HATCH_CHANCE);
        }
        return shouldUpdateHatchLevel(pLevel);
    }

    @Shadow
    private boolean shouldUpdateHatchLevel(Level p_57766_) {
        float f = p_57766_.getTimeOfDay(1.0F);
        if ((double)f < 0.69D && (double)f > 0.65D) {
            return true;
        } else {
            return p_57766_.random.nextInt(500) == 0;
        }
    }

    @Redirect(method = "destroyEgg",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/TurtleEggBlock;canDestroyEgg(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Entity;)Z"))
    private boolean protectEgg(TurtleEggBlock instance, Level pLevel, Entity pEntity){
        if(Helper.isSpecialBiome(pLevel,pEntity.getOnPos())){
            return false;
        }
        return canDestroyEgg(pLevel,pEntity);
    }
    @Shadow
    private boolean canDestroyEgg(Level p_57768_, Entity p_57769_) {
        if (!(p_57769_ instanceof Turtle) && !(p_57769_ instanceof Bat)) {
            if (!(p_57769_ instanceof LivingEntity)) {
                return false;
            } else {
                return p_57769_ instanceof Player || net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(p_57768_, p_57769_);
            }
        } else {
            return false;
        }
    }
}
