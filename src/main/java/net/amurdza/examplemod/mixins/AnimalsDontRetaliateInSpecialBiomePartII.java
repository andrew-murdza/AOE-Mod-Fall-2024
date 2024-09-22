package net.amurdza.examplemod.mixins;

import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Animal.class)
public class AnimalsDontRetaliateInSpecialBiomePartII extends AnimalsDontRetaliateInSpecialBiomePartI{
    @Override
    protected boolean hi(DamageSource instance, TagKey<DamageType> pDamageTypeKey){
        return true;
    }
}
