package net.amurdza.examplemod.mixins;

import net.amurdza.examplemod.Helper;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Animal.class)
public class AnimalsDontRetaliateInSpecialBiomePartII extends AnimalsDontRetaliateInSpecialBiomePartI{
    @Shadow private int inLove;

    @Override
    protected boolean hi(DamageSource instance, TagKey<DamageType> pDamageTypeKey){
        return super.hi(instance,pDamageTypeKey)&&
                !(instance.getEntity() instanceof Player player&& Helper.isSpecialBiome(player));
    }
}
