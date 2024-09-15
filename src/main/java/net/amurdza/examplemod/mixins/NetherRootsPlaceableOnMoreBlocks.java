package net.amurdza.examplemod.mixins;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RootsBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(RootsBlock.class)
public class NetherRootsPlaceableOnMoreBlocks {
    @Redirect(method = "mayPlaceOn",at=@At(value = "INVOKE",target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
    private boolean hi(BlockState instance, Block block){
        return List.of(block,Blocks.BLACKSTONE,Blocks.BASALT,Blocks.POLISHED_BASALT).contains(instance.getBlock());
    }
}
