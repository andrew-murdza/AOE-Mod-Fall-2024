package net.amurdza.examplemod;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.List;

@Mod.EventBusSubscriber(modid = AOEMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{


    public static final double LLAMA_HEALTH = 40;
    public static final double HORSE_HEALTH = 40;
    public static final double HORSE_SPEED = 0.4;
    public static final double HORSE_JUMP_STRENGTH = 1.1;
    public static final double DONKEY_HEALTH = 40;
    public static final double DONKEY_SPEED = 0.31;
    public static final double DONKEY_JUMP_STRENGTH = 1.0;
    public static final int GLOW_BERRY_HARVEST_AMOUNT = 1;
    public static final int SWEET_BERRIES_PARTIALLY_GROWN = 2;
    public static final int SWEET_BERRIES_FULLY_GROWN = 5;
    public static final int WOOL_FROM_SHEAR = 3;
    public static final int CHANCE_OF_TALL_SEAGRASS_BONEMEAL = 4;
    public static final double MANGROVE_PROPAGULE_AGE_GROWTH_CHANCE = 0.5;
    public static final double MUSHROOM_GROWTH_CHANCE = 0.5;
    public static final int MAX_MUSHROOMS_FOR_GROWTH = 5;
    public static final int MYCELIUM_GRASS_SPREAD_NUM_TRIES = 8;
    public static final double AMETHYST_GROWTH_CHANCE = 0.5;
    public static final double DRIPSTONE_INCREASE_LIQUID_CHANCE = 0.1;
    public static final double DRIPSTONE_GROW_CHANCE = 0.01;
    public static final double SAPLING_GROWTH_CHANCE = 0.5;
    public static final double TURTLE_HATCH_CHANCE = 0.2;
    public static final double VINE_GROWTH_CHANCE = 0.5;
    public static final int GLOW_LICHEN_TRIES = 4;
    public static final double PLACE_CHORUS_FLOWER_CHANCE = 0.1;
    public static final double CHORUS_FLOWER_GROW_CHANCE = 0.16;
    public static List<Item> BLACKLISTED_USE_ITEMS=List.of(Items.GOLD_BLOCK);
    public static List<Block> CROP_BLOCKS=List.of(Blocks.WHEAT,Blocks.CACTUS);
    public static String SPECIAL_BIOME="minecraft:plains";
    public static List<? extends Double> CROP_GROWTH_CHANCES=List.of(1D,1D);
    public static List<? extends Integer> CROP_NUMBER_OF_GROWTHS=List.of(1,16);

    public static List<? extends String> TWIN_MOBS=List.of("minecraft:cow");
    public static List<? extends String> BLACKLISTED_MOBS=List.of("minecraft:sheep");
    public static List<? extends String> PARTIALLY_INFERTILE_MOBS=List.of("minecraft:strider");
    public static List<? extends Double> TWIN_CHANCES=List.of(1D);
    public static List<? extends Double> INFERTILE_CHANCES=List.of(1D);
    public static List<? extends String> SLOWER_GROWTH_MOBS=List.of("minecraft:strider");
    public static List<? extends String> FASTER_GROWTH_MOBS=List.of("minecraft:strider");
    public static List<? extends Double> SLOWER_GROWTH_CHANCES=List.of(0.5);
    //applies for recovering from breeding, babies growing, eggs, wool growing, etc.
    public static List<? extends Double> FASTER_GROWTH_CHANCE=List.of(0.5);

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        System.out.println("this ran");
        SPECIAL_BIOME=ConfigHelper.read("specialbiome","minecraft:plains");

        BLACKLISTED_USE_ITEMS = ConfigHelper.readListOfObjects("blacklistedblocks",ForgeRegistries.ITEMS);

        CROP_BLOCKS=ConfigHelper.readListOfObjects("growingplants",ForgeRegistries.BLOCKS);
        CROP_GROWTH_CHANCES=ConfigHelper.readList("plantgrowthchances");
        CROP_NUMBER_OF_GROWTHS=ConfigHelper.readList("plantgrowuntilmaxage");
    }
}
