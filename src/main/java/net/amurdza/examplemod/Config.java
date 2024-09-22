package net.amurdza.examplemod;

import com.belgieyt.trailsandtalesplus.Objects.TTBlockRegistry;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.amurdza.examplemod.block.ModBlocks;
import net.amurdza.examplemod.item.ModItems;
import net.brnbrd.delightful.common.block.DelightfulBlocks;
import net.mehvahdjukaar.hauntedharvest.reg.ModRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collections;
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
    public static final int GLOW_BERRY_HARVEST_AMOUNT = 2;
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
    public static final double PRICKLY_PEAR_GROWH_CHANCE = 1;
    public static final int MULBERRY_HARVEST_AMOUNT_IN_SPECIAL_BIOME = 4;
    public static final int NUM_GRAPES = 3;
    public static final double PUPA_CHANCE = 0.2;
    public static final int ANT_FOOD_COUNT = 4;
    public static List<Item> BLACKLISTED_USE_ITEMS=List.of();
    public static List<Block> CROP_BLOCKS;
    public static ResourceKey<Biome> SPECIAL_BIOME= Biomes.JUNGLE;
    public static List<? extends Double> CROP_GROWTH_CHANCES=List.of(1D,1D,0.45D,1D,1D,1D,1D,1D,1D,1D,1D,1D,1D,1D,1D,1D,1D,1D,1D,0.25D,1D,1D,1D,1D,1D,1D,1D,1D,0.12D,1D,0.25D);
    public static List<? extends Integer> CROP_NUMBER_OF_GROWTHS=List.of(1,1,1,4,1,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1);

    public static List<? extends String> TWIN_MOBS=List.of("minecraft:axolotl","minecraft:bee","minecraft:camel","minecraft:cat","minecraft:chicken","minecraft:cow","minecraft:donkey","minecraft:frog","minecraft:horse","minecraft:mooshroom","minecraft:mule","minecraft:ocelot","minecraft:parrot","minecraft:pig","minecraft:rabbit","minecraft:sheep","minecraft:sniffer","minecraft:tadpole","minecraft:turtle","minecraft:fox","minecraft:goat","minecraft:llama","minecraft:panda","minecraft:polar_bear","minecraft:wolf","minecraft:strider","minecraft:hoglin","minecraft:hoglin","alexsmobs:banana_slug","alexsmobs:blue_jay","alexsmobs:cockroach","alexsmobs:crow","alexsmobs:endergrade","alexsmobs:flutter","alexsmobs:fly","alexsmobs:gazelle","alexsmobs:hummingbird","alexsmobs:jerboa","alexsmobs:laviathan","alexsmobs:maned_wolf","alexsmobs:mimic_octopus","alexsmobs:mudskipper","alexsmobs:mungus","alexsmobs:Potoo","alexsmobs:rain_frog","alexsmobs:road_runner","alexsmobs:seagull","alexsmobs:seal","alexsmobs:sugar_glider","alexsmobs:terrapin","alexsmobs:toucan","alexsmobs:triops","alexsmobs:anteater","alexsmobs:bald_eagle","alexsmobs:bison","alexsmobs:caiman","alexsmobs:capuchin_monkey","alexsmobs:cosmaw","alexsmobs:elephant","alexsmobs:emu","alexsmobs:gelada_monkey","alexsmobs:gorilla","alexsmobs:kangaroo","alexsmobs:mantis_shrimp","alexsmobs:racoon","alexsmobs:snow_leopard","alexsmobs:tarantula_hawk","alexsmobs:tasmanian_devil","alexsmobs:warped_toad","alexsmobs:alligator_snapping_turtle","alexsmobs:grizzly_bear","alexsmobs:platypus","alexsmobs:rattlesnake","alexsmobs:rhinoceros","alexsmobs:skunk","alexsmobs:anaconda","alexsmobs:crocodile","alexsmobs:froststalker","alexsmobs:komodo_dragon","alexsmobs:tiger","alexsmobs:tusklin","iceandfire:hippocampus","iceandfire:hippogryphs","iceandfire:amphithere","samurai_dynasty:twotailed","quark:crab","quark:foxhound","quark:toretoise");
    public static List<? extends String> BLACKLISTED_MOBS=List.of();
    public static List<? extends String> PARTIALLY_INFERTILE_MOBS=List.of();
    public static List<? extends Double> TWIN_CHANCES= NonNullList.withSize(TWIN_MOBS.size(),1D);
    public static List<? extends Double> INFERTILE_CHANCES=List.of(1D);
    public static List<? extends String> SLOWER_GROWTH_MOBS=List.of();
    public static List<? extends String> FASTER_GROWTH_MOBS=TWIN_MOBS;
    public static List<? extends Double> SLOWER_GROWTH_CHANCES=List.of(0.5);
    //applies for recovering from breeding, babies growing, eggs, wool growing, etc.
    public static List<? extends Double> FASTER_GROWTH_CHANCE= NonNullList.withSize(FASTER_GROWTH_MOBS.size(),0.75D);
    public static int MAX_LEAVES_DISTANCE=8;

    @SubscribeEvent
    static void onLoad(final FMLCommonSetupEvent event)
    {
        System.out.println("this ran");
        SPECIAL_BIOME=Biomes.JUNGLE;//ConfigHelper.read("specialbiome","minecraft:jungle");
        CROP_BLOCKS=List.of(Blocks.BAMBOO_SAPLING,Blocks.BAMBOO,Blocks.BEETROOTS,Blocks.CACTUS,Blocks.CARROTS,Blocks.CAVE_VINES, Blocks.CAVE_VINES_PLANT, Blocks.COCOA, Blocks.MELON_STEM, Blocks.PITCHER_CROP, Blocks.POTATOES, Blocks.PUMPKIN_STEM, Blocks.SUGAR_CANE, Blocks.SWEET_BERRY_BUSH, Blocks.VINE, Blocks.WHEAT, UABlocks.MULBERRY_VINE.get(),ModBlocks.BLUE_BERRY_BUSH.get(),ModBlocks.GRAPE_VINE.get(), DelightfulBlocks.CANTALOUPE_PLANT.get(), net.ribs.vintagedelight.block.ModBlocks.OAT_CROP.get(), net.ribs.vintagedelight.block.ModBlocks.PEANUT_CROP.get(), vectorwing.farmersdelight.common.registry.ModBlocks.ONION_CROP.get(),vectorwing.farmersdelight.common.registry.ModBlocks.TOMATO_CROP.get(),vectorwing.farmersdelight.common.registry.ModBlocks.CABBAGE_CROP.get(),vectorwing.farmersdelight.common.registry.ModBlocks.RICE_CROP.get(), TTBlockRegistry.CHERRY_VINE.get(), samebutdifferent.ecologics.registry.ModBlocks.PRICKLY_PEAR.get(),UABlocks.PICKERELWEED.get(), ModRegistry.CORN_BASE.get(),ModRegistry.CORN_MIDDLE.get(),ModRegistry.CORN_TOP.get(),Blocks.NETHER_WART);
//
//        BLACKLISTED_USE_ITEMS = ConfigHelper.readListOfObjects("blacklistedblocks",ForgeRegistries.ITEMS);
//
//        CROP_BLOCKS=ConfigHelper.readListOfObjects("growingplants",ForgeRegistries.BLOCKS);
//        CROP_GROWTH_CHANCES=ConfigHelper.readList("plantgrowthchances");
//        CROP_NUMBER_OF_GROWTHS=ConfigHelper.readList("plantgrowuntilmaxage");
    }
}
