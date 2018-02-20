package halestormxv.proxy;

import halestormxv.capabilities.rcLvl_Functions;
import halestormxv.capabilities.rcLvl_Storage;
import halestormxv.gui.handlers.GeneralGuiHandler;
import halestormxv.init.BiomeInit;
import halestormxv.init.BlockInit;
import halestormxv.init.EntityInit;
import halestormxv.init.ItemInit;
import halestormxv.network.PacketHandler;
import halestormxv.utils.Reference;
import halestormxv.utils.handlers.CapabilityHandler;
import halestormxv.utils.handlers.EventHandler;
import halestormxv.utils.handlers.SoundsHandler;
import halestormxv.utils.handlers.TileEntityHandler;
import halestormxv.utils.interfaces.IRuneCraftLevel;
import halestormxv.world.dimensions.ModDimensions;
import halestormxv.world.gen.WorldGenCustomOres;
import halestormxv.world.gen.WorldGenCustomStuffs;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
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
        CapabilityManager.INSTANCE.register(IRuneCraftLevel.class, new rcLvl_Storage(), rcLvl_Functions.class);
        PacketHandler.registerMessages( "hsrrunebag");
        ModDimensions.init();
    }

    public void init(FMLInitializationEvent e)
    {
        SoundsHandler.registerSounds();
        NetworkRegistry.INSTANCE.registerGuiHandler(Reference.MODID, new GeneralGuiHandler());
    }

    public void postInit(FMLPostInitializationEvent e)
    {
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
    }

    public void registerItemRenderer(Item item, int meta, String id) {}

    public void registerVariantRenderer(Item item, int meta, String filename, String id) {}

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
        TileEntityHandler.registerTileEntities();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
    }
}
