package net.amurdza.examplemod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CaveVinesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class CaveVinesHeadNew extends CaveVinesBlock {

    private static final float CHANCE_OF_BERRIES_ON_GROWTH = 0.11F;
    public CaveVinesHeadNew(Properties p_152959_) {
        super(p_152959_);
    }
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(BlockStateProperties.ENABLED);
    }


    protected Block getBodyBlock() {
        return ModBlocks.CAVE_VINES_PLANT.get();
    }
    public boolean isRandomlyTicking(BlockState pState) {
        return pState.getValue(AGE) < 25 || !pState.getValue(BERRIES)&&pState.getValue(BlockStateProperties.ENABLED);
    }
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(pLevel, pPos.relative(this.growthDirection), pLevel.getBlockState(pPos.relative(this.growthDirection)),pRandom.nextDouble() < CHANCE_OF_BERRIES_ON_GROWTH)) {
            if(!pState.getValue(BERRIES)&&pState.getValue(BlockStateProperties.ENABLED)){
                pLevel.setBlock(pPos, pState.setValue(BERRIES,true), 2);
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
            }
            else{
                super.randomTick(pState,pLevel,pPos,pRandom);
            }
        }
    }
}
