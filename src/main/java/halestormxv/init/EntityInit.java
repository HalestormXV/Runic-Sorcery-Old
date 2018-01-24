package halestormxv.init;

import halestormxv.RunicSorcery;
import halestormxv.entity.EntityCultist;
import halestormxv.entity.EntityPhantom;
import halestormxv.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit
{
    public static void registerEntities()
    {
        registerEntity("phantom", EntityPhantom.class, Reference.ENTITY_PHANTOM, 32, 13869624, 11797069);
        registerEntity("cultist", EntityCultist.class, Reference.ENTITY_CULTIST, 32, 3133855, 10375936);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2 )
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id, RunicSorcery.instance,
                range, 1, true, color1, color2);
    }
}
