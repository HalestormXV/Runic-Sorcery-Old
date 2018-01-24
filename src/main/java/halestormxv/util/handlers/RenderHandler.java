package halestormxv.util.handlers;

import halestormxv.entity.EntityPhantom;
import halestormxv.entity.render.RenderPhantom;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler
{
    public static void registerEntityRenders()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityPhantom.class, new IRenderFactory<EntityPhantom>()
        {
            @Override
            public Render<? super EntityPhantom> createRenderFor(RenderManager manager) {
                return new RenderPhantom(manager);
            }
        });
    }
}
