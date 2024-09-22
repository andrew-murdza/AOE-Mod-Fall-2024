package net.amurdza.examplemod.mixins;

import net.amurdza.examplemod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CaveVinesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.minecraft.world.level.block.CaveVines.BERRIES;

@Mixin(CaveVinesBlock.class)
public class CaveVinesDropNewBerriesPart1 {
    @Redirect(method = "use",at= @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/CaveVines;use(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/InteractionResult;"))
    private InteractionResult hi(Entity pEntity, BlockState pState, Level pLevel, BlockPos pPos){
        if (pState.getValue(BERRIES)) {
            Block.popResource(pLevel, pPos, new ItemStack(ModItems.GLOW_BERRIES.get(), 1));
            float f = Mth.randomBetween(pLevel.random, 0.8F, 1.2F);
            pLevel.playSound((Player)null, pPos, SoundEvents.CAVE_VINES_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, f);
            BlockState blockstate = pState.setValue(BERRIES, Boolean.valueOf(false));
            pLevel.setBlock(pPos, blockstate, 2);
            pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(pEntity, blockstate));
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }
}
