package net.amurdza.examplemod.event_handlers;

import net.amurdza.examplemod.AOEMod;
import net.amurdza.examplemod.Config;
import net.amurdza.examplemod.Helper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Consumer;

@Mod.EventBusSubscriber(modid = AOEMod.MOD_ID, bus=Mod.EventBusSubscriber.Bus.FORGE)
public class BlockGrowth {
    @SubscribeEvent
    public static void blockGrowthPre(BlockEvent.CropGrowEvent.Pre event) {
        BlockState state=event.getState();
        Block block=state.getBlock();
        BlockPos pos=event.getPos();
        LevelAccessor level=event.getLevel();
        if(Helper.isBiomeNameAtPos(level,pos, Config.SPECIAL_BIOME)){
            int index=Config.CROP_BLOCKS.indexOf(block);
            if(index>=0){
                double chance=Config.CROP_GROWTH_CHANCES.get(index);
                boolean growUntilMaxAge=Config.CROP_GROW_UNTIL_MAX_AGE.get(index);
//                int numTries=Config.CROP_GROWTH_NUM_TRIES.get(index);
                IntegerProperty[] agePropertyList=new IntegerProperty[]{
                        BlockStateProperties.AGE_1,BlockStateProperties.AGE_2, BlockStateProperties.AGE_3,
                        BlockStateProperties.AGE_4,BlockStateProperties.AGE_5, BlockStateProperties.AGE_7,
                        BlockStateProperties.AGE_15,BlockStateProperties.AGE_25};
                int[] maxAges=new int[]{15,1,2,3,4,5,7,25};

                if(Helper.withChance(level,chance)){
                    event.setResult(Event.Result.ALLOW);
                    if(growUntilMaxAge){
                        for(int i=0;i<agePropertyList.length;i++){
                            IntegerProperty property=agePropertyList[i];
                            if(state.hasProperty(property)&&state.getValue(property)<maxAges[i]){
                                state.randomTick((ServerLevel) level,pos,level.getRandom());
                            }
                        }
                    }
                }
                event.setResult(Event.Result.DENY);
            }
        }
    }
}
