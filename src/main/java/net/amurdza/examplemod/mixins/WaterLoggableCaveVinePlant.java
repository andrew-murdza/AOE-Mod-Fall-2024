package net.amurdza.examplemod.mixins;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CaveVinesPlantBlock;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.BERRIES;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED;

@Mixin(CaveVinesPlantBlock.class)
public abstract class WaterLoggableCaveVinePlant extends GrowingPlantBodyBlock implements SimpleWaterloggedBlock {
    protected WaterLoggableCaveVinePlant(final Properties p_53863_, final Direction p_53864_, final VoxelShape p_53865_, final boolean p_53866_) {
        super(p_53863_, p_53864_, p_53865_, p_53866_);
    }

    @Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)V")
    public void ctor(final BlockBehaviour.Properties pProperties, final CallbackInfo callbackInfo) {
        this.registerDefaultState(this.stateDefinition.any().setValue(BERRIES, Boolean.FALSE).setValue(WATERLOGGED, false));
    }

    @Inject(at = @At("RETURN"), method = "createBlockStateDefinition(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V")
    public void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder, final CallbackInfo callbackInfo) {
        builder.add(WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(final BlockState p_52362_) {
        return p_52362_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_52362_);
    }
}
