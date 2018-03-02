package halestormxv.utility.handlers;

import halestormxv.capabilities.runebag.RuneBagProvider;
import halestormxv.capabilities.runecrafting.rcLvl_Provider;
import halestormxv.capabilities.spellcastlevel.SpellCastLvLProvider;
import halestormxv.utility.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler
{
    public static final ResourceLocation RUNECRAFT_LEVEL = new ResourceLocation(Reference.MODID, "runeCraftLevel");
    public static final ResourceLocation RUNEBAG_CAP = new ResourceLocation(Reference.MODID, "runeBagCapability");
    public static final ResourceLocation SPELL_CAST_LEVEL_CAP = new ResourceLocation(Reference.MODID, "spellCastLevel");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof EntityPlayer)) return;
        event.addCapability(RUNECRAFT_LEVEL, new rcLvl_Provider());
        event.addCapability(RUNEBAG_CAP, new RuneBagProvider());
        event.addCapability(SPELL_CAST_LEVEL_CAP, new SpellCastLvLProvider());
    }
}