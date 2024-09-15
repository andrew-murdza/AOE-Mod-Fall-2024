package net.amurdza.examplemod.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CaveVinesBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.Tags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.BERRIES;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED;
@Mixin(CaveVinesBlock.class)
public abstract class WaterLoggableCaveVineHead extends GrowingPlantHeadBlock implements SimpleWaterloggedBlock  {
    protected WaterLoggableCaveVineHead(final BlockBehaviour.Properties pProperties, final Direction pGrowthDirection, final VoxelShape pShape, final boolean pScheduleFluidTicks, final double pGrowPerTickProbability) {
        super(pProperties, pGrowthDirection, pShape, pScheduleFluidTicks, pGrowPerTickProbability);
    }

    @Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)V")
    public void ctor(final BlockBehaviour.Properties pProperties, final CallbackInfo callbackInfo) {
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(BERRIES, Boolean.FALSE).setValue(WATERLOGGED, false));
    }

    @Inject(at = @At("HEAD"), method = "canGrowInto(Lnet/minecraft/world/level/block/state/BlockState;)Z", cancellable = true)
    protected void canGrowInto(final BlockState state, final CallbackInfoReturnable<Boolean> callbackInfo) {
        if(state.getFluidState().is(FluidTags.WATER)) {
            callbackInfo.setReturnValue(true);
        }
    }

    @Inject(at = @At("RETURN"), method = "createBlockStateDefinition(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V")
    public void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder, final CallbackInfo callbackInfo) {
        builder.add(WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(final BlockState p_52362_) {
        return p_52362_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_52362_);
    }

    @Override
    public BlockState getStateForPlacement(final BlockPlaceContext p_54200_) {
        final FluidState fluidstate = p_54200_.getLevel().getFluidState(p_54200_.getClickedPos());
        System.out.println(fluidstate);
        return super.getStateForPlacement(p_54200_).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
