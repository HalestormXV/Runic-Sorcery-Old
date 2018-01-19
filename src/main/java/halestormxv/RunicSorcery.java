package halestormxv;

import halestormxv.proxy.CommonProxy;
import halestormxv.util.Logging;
import halestormxv.util.Reference;
import halestormxv.util.handlers.RegistryHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class RunicSorcery
{
    @Mod.Instance
    public static RunicSorcery instance;

    public static Logger logger;

    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        Logging.getLogger().info("Pre Initialize");
        logger = event.getModLog();
        RegistryHandler.preInitRegistries();
    }
    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        Logging.getLogger().info("Initialize");
    }
    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        Logging.getLogger().info("Post Initialize");
    }
}
