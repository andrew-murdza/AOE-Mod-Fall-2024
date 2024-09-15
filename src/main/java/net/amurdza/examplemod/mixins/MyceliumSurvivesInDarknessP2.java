package net.amurdza.examplemod.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

public class MyceliumSurvivesInDarknessP2 extends MyceliumSurvivesInDarknessP1 {
    @Override
    protected int getLight(ServerLevel instance, BlockPos blockPos){
        return instance.getMaxLightLevel();
    }
}
