package net.amurdza.examplemod.event_handlers;

import net.amurdza.examplemod.Helper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.amurdza.examplemod.AOEMod.MOD_ID;
import static net.amurdza.examplemod.Config.BLACKLISTED_USE_ITEMS;
import static net.amurdza.examplemod.Config.SPECIAL_BIOME;

@Mod.EventBusSubscriber(modid = MOD_ID, bus=Mod.EventBusSubscriber.Bus.FORGE)
public class ItemBlackList {
    @SubscribeEvent
    public static void cancelPlace(PlayerInteractEvent.RightClickBlock event){
        final LevelAccessor level = event.getLevel();
        final BlockPos pos = event.getPos();
        if(Helper.isBiomeNameAtPos(level,pos,SPECIAL_BIOME)){
            final Item item=event.getItemStack().getItem();
            if(!item.equals(Items.AIR)&&BLACKLISTED_USE_ITEMS.contains(item)){
                Helper.sendMessage("You cannot place "+item+" in "+SPECIAL_BIOME);
                event.setCanceled(true);
            }
        }
    }
}
