package halestormxv;

import halestormxv.objects.blocks.devices.inscriber.TileEntityRunicInscriber;
import halestormxv.proxy.CommonProxy;
import halestormxv.utils.Logging;
import halestormxv.utils.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class RunicSorcery
{
    @Mod.Instance
    public static RunicSorcery instance;

    public static Logger logger;

    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.SERVER)
    public static CommonProxy proxy;

    public static final CreativeTabs RUNICSORCERY = new ModTab("Runic Sorcery");

    static { FluidRegistry.enableUniversalBucket(); }

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event)
    {
        Logging.getLogger().info("Pre Initialize");
        logger = event.getModLog();
        proxy.preInit(event);
    }
    @Mod.EventHandler
    public static void init(FMLInitializationEvent event)
    {
        Logging.getLogger().info("Initialize");
        proxy.init(event);
    }
    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event)
    {
        Logging.getLogger().info("Post Initialize");
        proxy.postInit(event);
    }
}
