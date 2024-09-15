package net.amurdza.examplemod.event_handlers;

import net.amurdza.examplemod.Config;
import net.amurdza.examplemod.Helper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.amurdza.examplemod.AOEMod.MOD_ID;
@Mod.EventBusSubscriber(modid = MOD_ID, bus=Mod.EventBusSubscriber.Bus.FORGE)
public class MobTicking {
    @SubscribeEvent
    public static void mobTickChanges(LivingEvent.LivingTickEvent event){
        LivingEntity mob=event.getEntity();
        Level level = mob.level();
        BlockPos pos=mob.getOnPos();
        if(!level.isClientSide&&mob.isAlive()&& Helper.isSpecialBiome(level,pos)){
            String mobName=mob.getEncodeId();
            if(mobName!=null){
                int index= Config.SLOWER_GROWTH_MOBS.indexOf(mobName);
                int index1= Config.FASTER_GROWTH_MOBS.indexOf(mobName);
                if(index>=0&&Helper.withChance(level,Config.SLOWER_GROWTH_CHANCES.get(index))){
                    event.setCanceled(true);
                }
                else if(index1>=0&&Helper.withChance(level,Config.FASTER_GROWTH_CHANCE.get(index1))){
                    mob.tick();
                }
            }
        }
    }
}
