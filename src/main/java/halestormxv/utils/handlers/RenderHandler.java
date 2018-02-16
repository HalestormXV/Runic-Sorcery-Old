package halestormxv.utils.handlers;

import halestormxv.entity.EntityCultist;
import halestormxv.entity.EntityHarbinger;
import halestormxv.entity.EntityPhantom;
import halestormxv.entity.render.RenderCultist;
import halestormxv.entity.render.RenderHarbinger;
import halestormxv.entity.render.RenderPhantom;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderHandler
{
    @SideOnly(Side.CLIENT)
    public static void registerEntityRenders()
    {
        //Phantom Rendering
        RenderingRegistry.registerEntityRenderingHandler(EntityPhantom.class, RenderPhantom::new);

        //Cultist Rendering
        RenderingRegistry.registerEntityRenderingHandler(EntityCultist.class, RenderCultist::new);

        //Harbinger Rendering
        RenderingRegistry.registerEntityRenderingHandler(EntityHarbinger.class, RenderHarbinger::new);
    }
}
