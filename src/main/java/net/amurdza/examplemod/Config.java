package net.amurdza.examplemod;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.List;

@Mod.EventBusSubscriber(modid = AOEMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{


    public static List<Item> BLACKLISTED_USE_ITEMS;
    public static List<Block> CROP_BLOCKS;
    public static String SPECIAL_BIOME;
    public static List<? extends Double> CROP_GROWTH_CHANCES;
    public static List<? extends Boolean> CROP_GROW_UNTIL_MAX_AGE;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        System.out.println("this ran");
        SPECIAL_BIOME=ConfigHelper.read("specialbiome","minecraft:plains");

        BLACKLISTED_USE_ITEMS = ConfigHelper.readListOfObjects("blacklistedblocks",ForgeRegistries.ITEMS);

        CROP_BLOCKS=ConfigHelper.readListOfObjects("growingplants",ForgeRegistries.BLOCKS);
        CROP_GROWTH_CHANCES=ConfigHelper.readList("plantgrowthchances");
        CROP_GROW_UNTIL_MAX_AGE=ConfigHelper.readList("plantgrowuntilmaxage");
    }
}
