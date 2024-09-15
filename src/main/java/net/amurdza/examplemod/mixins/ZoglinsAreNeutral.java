package net.amurdza.examplemod.mixins;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.entity.schedule.Activity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Zoglin.class)
public class ZoglinsAreNeutral {
    @Redirect(method = "initIdleActivity",at=@At(value = "INVOKE",target = "Lnet/minecraft/world/entity/ai/Brain;addActivity(Lnet/minecraft/world/entity/schedule/Activity;ILcom/google/common/collect/ImmutableList;)V"))
    private static <E>void hi(Brain instance, Activity pActivity, int pPriorityStart, ImmutableList<? extends BehaviorControl<? super E>> pTasks){

    }
}
