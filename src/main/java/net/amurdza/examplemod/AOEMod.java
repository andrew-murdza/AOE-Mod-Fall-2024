package net.amurdza.examplemod;

import com.mojang.logging.LogUtils;
import net.amurdza.examplemod.block.ModBlocks;
import net.amurdza.examplemod.item.ModItems;
import net.amurdza.examplemod.mixins.RestoreXpOnRespawn;
import net.amurdza.examplemod.worldgen.feature.ModFeatures;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AOEMod.MOD_ID)
public class AOEMod
{
    public static final String MOD_ID = "aoemod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public AOEMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        //custom items
        ModItems.register(modEventBus);

        //custom blocks
        ModBlocks.register(modEventBus);

        ModFeatures.register(modEventBus);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHelper.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey()== CreativeModeTabs.NATURAL_BLOCKS||event.getTabKey()==CreativeModeTabs.FOOD_AND_DRINKS){
            event.accept(ModBlocks.GRAPE_VINE.get());
            event.accept(ModItems.GLOW_BERRIES.get());
            event.accept(ModItems.BLUE_BERRIES.get());
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    /** Takes away one level per death ({@link RestoreXpOnRespawn}) */
    @SubscribeEvent
    public void loseLevelOnDeath(final LivingDeathEvent event) {
        if(event.getEntity() instanceof final ServerPlayer player) {
            player.giveExperienceLevels(-1);
        }
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
        //Add once in 1.21
//        @SubscribeEvent
//        private static void registerDataMapTypes(RegisterDataMapTypesEvent event) {
//            event.register(EXAMPLE_DATA);
//        }
    }
}
