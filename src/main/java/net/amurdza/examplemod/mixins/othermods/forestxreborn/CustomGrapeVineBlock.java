package net.amurdza.examplemod.mixins.othermods.forestxreborn;

import net.amurdza.examplemod.block.GrapeVineNew;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import ru.power_umc.forestxreborn.init.ForestModBlocks;

import java.util.function.Supplier;

@Mixin(ForestModBlocks.class)
public class CustomGrapeVineBlock {
    @Redirect(method = "<clinit>",at = @At(value = "INVOKE", target = "Lnet/minecraftforge/registries/DeferredRegister;register(Ljava/lang/String;Ljava/util/function/Supplier;)Lnet/minecraftforge/registries/RegistryObject;",ordinal = 191))
    private static RegistryObject hi(DeferredRegister instance, String s, Supplier<? extends Block> name){
        return instance.register("grape_vine_0", GrapeVineNew::new);
    }

}
