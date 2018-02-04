package halestormxv.proxy;

import halestormxv.init.BiomeInit;
import halestormxv.init.BlockInit;
import halestormxv.init.EntityInit;
import halestormxv.init.ItemInit;
import halestormxv.network.PacketHandler;
import halestormxv.util.handlers.SoundsHandler;
import halestormxv.world.gen.WorldGenCustomOres;
import halestormxv.world.gen.WorldGenCustomStuffs;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent e)
    {
        GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
        GameRegistry.registerWorldGenerator(new WorldGenCustomStuffs(), 0);
        BiomeInit.registerBiomes();
        EntityInit.registerEntities();
        PacketHandler.registerMessages( "hsrrunebag");
    }

    public void init(FMLInitializationEvent e)
    {
        SoundsHandler.registerSounds();
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    public void registerItemRenderer(Item item, int meta, String id) {}

    public void registerVariantRenderer(Item item, int meta, String filename, String id) {}

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
    }
}
