package halestormxv.utils.handlers;

import halestormxv.utils.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler
{
    public static SoundEvent ENTITY_PHANTOM_AMBIENT, ENTITY_PHANTOM_HURT, ENTITY_PHANTOM_DEATH;
    public static SoundEvent  ENTITY_CULTIST_AMBIENT, ENTITY_CULTIST_HURT, ENTITY_CULTIST_DEATH, ENTITY_CULTIST_SPECIAL;
    public static SoundEvent EFFECT_RUNIC_INSCRIBER;

    public static void registerSounds()
    {
        ENTITY_PHANTOM_AMBIENT = registerSound("entity.phantom.ambient");
        ENTITY_PHANTOM_HURT = registerSound("entity.phantom.hurt");
        ENTITY_PHANTOM_DEATH = registerSound("entity.phantom.death");

        ENTITY_CULTIST_AMBIENT = registerSound("entity.cultist.ambient");
        ENTITY_CULTIST_HURT = registerSound("entity.cultist.hurt");
        ENTITY_CULTIST_DEATH = registerSound("entity.cultist.death");
        ENTITY_CULTIST_SPECIAL = registerSound("entity.cultist.special");

        EFFECT_RUNIC_INSCRIBER = registerSound("effect.runic_inscriber.active");
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
