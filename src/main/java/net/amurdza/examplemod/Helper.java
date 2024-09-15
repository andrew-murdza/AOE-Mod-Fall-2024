package net.amurdza.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import javax.swing.text.html.parser.Entity;
import java.util.Arrays;
import java.util.List;

public class Helper {
    public static List<Item> smallFlowerItems;
    static {
        smallFlowerItems=Arrays.asList(Items.POPPY,Items.DANDELION,Items.AZURE_BLUET,Items.CORNFLOWER,
                Items.OXEYE_DAISY,Items.ALLIUM,Items.BLUE_ORCHID,Items.LILY_OF_THE_VALLEY,Items.RED_TULIP,
                Items.WHITE_TULIP,Items.PINK_TULIP,Items.ORANGE_TULIP);
    }
    public static boolean isBlackListed(Block block){
        return Config.BLACKLISTED_USE_ITEMS.contains(block.asItem());
    }
    public static boolean isBlackListed(BlockState state){
        return isBlackListed(state.getBlock());
    }
    public static boolean isBlackListed(Mob mob){
        return mob.getEncodeId()!=null&&Config.BLACKLISTED_MOBS.contains(mob.getEncodeId());
    }
    public static boolean isOkMob(Level level, BlockPos pos, Mob mob){
        return !Helper.isSpecialBiome(level, pos) || !isBlackListed(mob);
    }
    public static boolean isOkToPlace(Level level, BlockPos pos, BlockState state){
        return !Helper.isSpecialBiome(level,pos)||!Helper.isBlackListed(state);
    }
    public static boolean isOkToPlace(Level level, BlockPos pos, Block block){
        return !Helper.isSpecialBiome(level,pos)||!Helper.isBlackListed(block);
    }

    public static boolean isBlock(Block block, Block... blocks){
        return Arrays.asList(blocks).contains(block);
    }
    public static boolean withChance(LevelAccessor level, float f){
        return level.getRandom().nextFloat()<f;
    }
    public static boolean withChance(LevelAccessor level, double f){
        return level.getRandom().nextFloat()<f;
    }
    public static String getBiomeName(Holder<Biome> biome) {
        String biomeKey = biome.unwrapKey().toString();
        biomeKey = biomeKey.split("/ ")[1];
        biomeKey = biomeKey.split("]")[0];
        return biomeKey;
    }

    public static <T> T select(LevelAccessor level, List<T> objects){
        return objects.get(level.getRandom().nextInt(objects.size()));
    }

    public static <T> T select(LevelAccessor level, T... objects){
        return select(level,List.of(objects));
    }

    public static List<Direction> HORIZONTAL_DIRECTIONS = List.of(Direction.EAST,Direction.SOUTH,Direction.NORTH,Direction.WEST);

    public static void sendMessage(String string){
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.sendSystemMessage(Component.literal(string));
    }

    public static String getBiomeNameAtPos(LevelAccessor level, BlockPos pos) {
        return getBiomeName(level.getBiome(pos));
    }
    public static boolean isBiomeNameAtPos(LevelAccessor level, BlockPos pos, String name) {
        return getBiomeNameAtPos(level,pos).equals(name);
    }
    public static boolean isSpecialBiome(LevelAccessor level, BlockPos pos){
        return isBiomeNameAtPos(level,pos,Config.SPECIAL_BIOME);
    }
}
