package net.amurdza.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.LiteralContents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Helper {
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
    public static int withChanceToInt(LevelAccessor level, double f){
        return withChance(level,f)?0:1;
    }
    public static boolean withChance(LevelAccessor level, double f){
        return level.getRandom().nextFloat()<f;
    }
    public static ResourceKey<Biome> getBiomeName(Holder<Biome> biome) {
        return biome.unwrapKey().get();
    }

    public static <T> T select(LevelAccessor level, List<T> objects){
        return objects.get(level.getRandom().nextInt(objects.size()));
    }

    public static <T> T select(LevelAccessor level, T... objects){
        return select(level,List.of(objects));
    }

    public static List<Direction> HORIZONTAL_DIRECTIONS = List.of(Direction.EAST,Direction.SOUTH,Direction.NORTH,Direction.WEST);

    public static void sendMessage(Player player,String message){
        player.displayClientMessage(MutableComponent.create(new LiteralContents(message)),true);
    }

    public static ResourceKey<Biome> getBiomeNameAtPos(LevelAccessor level, BlockPos pos) {
        return getBiomeName(level.getBiome(pos));
    }
    public static boolean isBiomeNameAtPos(LevelAccessor level, BlockPos pos, ResourceKey<Biome> name) {
        return getBiomeNameAtPos(level,pos).equals(name);
    }
    public static boolean isSpecialBiome(LevelAccessor level, BlockPos pos){
        return isBiomeNameAtPos(level,pos,Config.SPECIAL_BIOME);
    }
    public static boolean isSpecialBiome(LivingEntity entity){
        return entity!=null&&isSpecialBiome(entity.level(),entity.getOnPos());
    }
}
