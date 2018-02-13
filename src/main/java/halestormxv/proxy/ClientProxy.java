package halestormxv.proxy;

import halestormxv.KeyBindings;
import halestormxv.gui.handlers.RunicInscriberGuiHandler;
import halestormxv.init.BlockInit;
import halestormxv.init.ItemInit;
import halestormxv.objects.fluids.ModFluids;
import halestormxv.utils.Reference;
import halestormxv.utils.handlers.InputHandler;
import halestormxv.utils.handlers.RenderHandler;
import halestormxv.utils.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    @Override
    public void registerItemRenderer(Item item, int meta, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }

    @Override
    public void registerVariantRenderer(Item item, int meta, String filename, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Reference.MODID, filename), id));
    }

    @Override
    public void preInit(FMLPreInitializationEvent e)
    {
        super.preInit(e);
        RenderHandler.registerEntityRenders();
    }

    @Override
    public void init(FMLInitializationEvent e)
    {
        super.init(e);
        MinecraftForge.EVENT_BUS.register(new InputHandler());
        KeyBindings.init();
    }


    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event)
    {
        for(Item item : ItemInit.ITEMS)
        {
            if(item instanceof IHasModel)
            {
                ((IHasModel)item).registerModels();
            }
        }

        for(Block block : BlockInit.BLOCKS)
        {
            if(block instanceof IHasModel)
            {
                ((IHasModel)block).registerModels();
            }
        }

    }
}
