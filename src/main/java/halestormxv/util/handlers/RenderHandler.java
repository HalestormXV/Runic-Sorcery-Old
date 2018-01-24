package halestormxv.util.handlers;

import halestormxv.entity.EntityCultist;
import halestormxv.entity.EntityPhantom;
import halestormxv.entity.render.RenderCultist;
import halestormxv.entity.render.RenderPhantom;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler
{
    public static void registerEntityRenders()
    {
        //Phantom Rendering
        RenderingRegistry.registerEntityRenderingHandler(EntityPhantom.class, new IRenderFactory<EntityPhantom>()
        {
            @Override
            public Render<? super EntityPhantom> createRenderFor(RenderManager manager) {
                return new RenderPhantom(manager);
            }
        });

        //Cultist Rendering
        RenderingRegistry.registerEntityRenderingHandler(EntityCultist.class, new IRenderFactory<EntityCultist>()
        {
            @Override
            public Render<? super EntityCultist> createRenderFor(RenderManager manager) {
                return new RenderCultist(manager);
            }
        });
    }
}
