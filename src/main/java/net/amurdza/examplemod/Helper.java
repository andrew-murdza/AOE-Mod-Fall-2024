package net.amurdza.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.function.Predicate;

import static net.amurdza.examplemod.Config.SPECIAL_BIOME;

public class Helper {

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
}
