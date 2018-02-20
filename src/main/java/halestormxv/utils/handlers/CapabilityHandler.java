package halestormxv.utils.handlers;

import halestormxv.capabilities.rcLvl_Provider;
import halestormxv.utils.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler
{
    public static final ResourceLocation RUNECRAFT_LEVEL = new ResourceLocation(Reference.MODID, "runeCraftLevel");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof EntityPlayer)) return;
        event.addCapability(RUNECRAFT_LEVEL, new rcLvl_Provider());
    }
}