package net.amurdza.examplemod.event_handlers;

import net.amurdza.examplemod.Config;
import net.amurdza.examplemod.Helper;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Donkey;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.checkerframework.checker.units.qual.C;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static net.amurdza.examplemod.AOEMod.MOD_ID;
import static net.amurdza.examplemod.Config.BLACKLISTED_USE_ITEMS;
import static net.amurdza.examplemod.Config.SPECIAL_BIOME;

@Mod.EventBusSubscriber(modid = MOD_ID, bus=Mod.EventBusSubscriber.Bus.FORGE)
public class UseEvent {

    private static final Map<DyeColor, Item> woolMap = new HashMap<>();
    static {
        woolMap.put(DyeColor.WHITE, Items.WHITE_WOOL);
        woolMap.put(DyeColor.ORANGE, Items.ORANGE_WOOL);
        woolMap.put(DyeColor.MAGENTA, Items.MAGENTA_WOOL);
        woolMap.put(DyeColor.LIGHT_BLUE, Items.LIGHT_BLUE_WOOL);
        woolMap.put(DyeColor.YELLOW, Items.YELLOW_WOOL);
        woolMap.put(DyeColor.LIME, Items.LIME_WOOL);
        woolMap.put(DyeColor.PINK, Items.PINK_WOOL);
        woolMap.put(DyeColor.GRAY, Items.GRAY_WOOL);
        woolMap.put(DyeColor.LIGHT_GRAY, Items.LIGHT_GRAY_WOOL);
        woolMap.put(DyeColor.CYAN, Items.CYAN_WOOL);
        woolMap.put(DyeColor.PURPLE, Items.PURPLE_WOOL);
        woolMap.put(DyeColor.BLUE, Items.BLUE_WOOL);
        woolMap.put(DyeColor.BROWN, Items.BROWN_WOOL);
        woolMap.put(DyeColor.GREEN, Items.GREEN_WOOL);
        woolMap.put(DyeColor.RED, Items.RED_WOOL);
        woolMap.put(DyeColor.BLACK, Items.BLACK_WOOL);
    }

    @SubscribeEvent
    public static void extraOrCancelBreeding(PlayerInteractEvent.EntityInteract event){
        if(event.getTarget() instanceof Mob mob){
            BlockPos pos=mob.getOnPos();
            if(!Helper.isOkMob(event.getLevel(),pos,mob)){
                event.setCanceled(true);
            }
            else {
                Player player=event.getEntity();
                ItemStack stack=event.getItemStack();


                if(mob instanceof MushroomCow){
                    useFood(mob,player,stack,new Item[]{Items.BROWN_MUSHROOM,Items.RED_MUSHROOM});
                    if(event.getItemStack().is(Items.SHEARS)){
                        event.setCanceled(true);
                    }
                }
                else if(mob instanceof Sheep sheep&&event.getItemStack().is(Items.SHEARS)){
                    Item wool=woolMap.get(sheep.getColor());
                    mob.spawnAtLocation(new ItemStack(wool,Config.WOOL_FROM_SHEAR));
                    event.setCanceled(true);
                }
                else if(mob instanceof Rabbit){
                    useFood(mob,player,stack,p-> Helper.smallFlowerItems.contains(p)&&p!=Items.WITHER_ROSE);
                }
                else if(mob instanceof Horse || mob instanceof Donkey){
                    useFood(mob,player,stack,Items.HAY_BLOCK);
                }
                else if(mob instanceof Strider){
                    useFood(mob,player,stack,new Item[]{Items.CRIMSON_ROOTS,Items.WARPED_ROOTS});
                }
                else if(mob instanceof Frog){
                    useFood(mob,player,stack,Items.SEAGRASS);
                }
                else if(mob instanceof Ocelot ||mob instanceof Cat){
                    useFood(mob,player,stack,new Item[]{Items.COD,Items.TROPICAL_FISH,Items.SALMON});
                }
                else if(mob instanceof Fox){
                    useFood(mob,player,stack,new Item[]{Items.CHICKEN,Items.RABBIT,Items.COD,Items.TROPICAL_FISH,Items.SALMON});
                }
                else if(mob instanceof Parrot){
                    useFood(mob,player,stack,Items.APPLE);
                }
                else if(mob instanceof PolarBear){
                    useFood(mob,player,stack,new Item[]{Items.BEEF,Items.MUTTON,Items.COD,Items.SALMON});
                }
            }
        }
    }


    //Black Listed Blocks
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


    @SubscribeEvent
    public static void hoeOnMossPodzol(PlayerInteractEvent.RightClickBlock event){
        Level level=event.getLevel();
        Player player=event.getEntity();
        BlockPos pos=event.getPos();
        BlockState blockState=level.getBlockState(pos);
        if(event.getItemStack().getItem() instanceof HoeItem){
            if(blockState.is(Blocks.MOSS_BLOCK)|| blockState.is(Blocks.PODZOL)){
                level.playSound(player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!level.isClientSide) {
                    level.setBlock(pos,Blocks.FARMLAND.defaultBlockState(),11);
                    if (player != null) {
                        event.getItemStack().hurtAndBreak(1, player, (p_150845_) -> p_150845_.broadcastBreakEvent(event.getHand()));
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void berriesFromUse(PlayerInteractEvent.RightClickBlock event){
        Level level=event.getLevel();
        BlockPos pos=event.getPos();
        BlockState state=level.getBlockState(pos);
        Block block=state.getBlock();
        if(Helper.isSpecialBiome(level,pos)){
            if((block==Blocks.CAVE_VINES||block==Blocks.CAVE_VINES_PLANT)&&state.getValue(BlockStateProperties.BERRIES)){
                Block.popResource(level, pos, new ItemStack(Items.GLOW_BERRIES, Config.GLOW_BERRY_HARVEST_AMOUNT));
            }
            if(state.is(Blocks.SWEET_BERRY_BUSH)){
                int age=state.getValue(BlockStateProperties.AGE_3);
                int amount=0;
                if(age==2){
                    amount= Config.SWEET_BERRIES_PARTIALLY_GROWN;
                }
                if(age==3){
                    amount = Config.SWEET_BERRIES_FULLY_GROWN;
                }
                if(amount>0){
                    Block.popResource(level, pos, new ItemStack(Items.SWEET_BERRIES,amount));
                }
            }
        }
    }

    private static void useItem(Player player, ItemStack stack){
        if(!player.getAbilities().instabuild){
            stack.shrink(1);
        }
    }

    private static void useFood(Entity entity, Player player, ItemStack stack, Item[] items){
        useFood(entity,player,stack,p-> Arrays.asList(items).contains(p));
    }
    private static void useFood(Entity entity, Player player, ItemStack stack, Item item){
        useFood(entity,player,stack,p-> p==item);
    }
    private static boolean useFood(Entity entity, Player player, ItemStack stack, Function<Item,Boolean> func){
        if(entity instanceof Animal animal &&func.apply(stack.getItem())){
            if(!animal.level().isClientSide&&animal.getAge()==0&&animal.canFallInLove()){
                if(!(animal instanceof TamableAnimal)||((TamableAnimal)animal).isTame()){
                    if(!(animal instanceof AbstractHorse)||((AbstractHorse)animal).isTamed()){
                        useItem(player,stack);
                        animal.setInLove(player);
                        animal.gameEvent(GameEvent.ENTITY_INTERACT,animal);
                        return true;
                    }
                }
            }
            if(animal.isBaby()){
                useItem(player,stack);
                animal.ageUp((int)((float)(-animal.getAge() / 20) * 0.1F), true);
                animal.gameEvent(GameEvent.ENTITY_INTERACT,animal);
                return true;
            }
        }
        return false;
    }
}
