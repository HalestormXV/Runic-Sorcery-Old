package halestormxv.util.handlers;

import halestormxv.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler
{
    public static SoundEvent ENTITY_PHANTOM_AMBIENT, ENTITY_PHANTOM_HURT, ENTITY_PHANTOM_DEATH;

    public static void registerSounds()
    {
        ENTITY_PHANTOM_AMBIENT = registerSound("entity.phantom.ambient");
        ENTITY_PHANTOM_HURT = registerSound("entity.phantom.hurt");
        ENTITY_PHANTOM_DEATH = registerSound("entity.phantom.death");
    }

    private static SoundEvent registerSound(String name)
    {
        ResourceLocation location = new ResourceLocation(Reference.MODID, name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }

}
