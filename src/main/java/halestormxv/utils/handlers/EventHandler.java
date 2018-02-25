package halestormxv.utils.handlers;

import halestormxv.capabilities.runebag.RuneBagProvider;
import halestormxv.capabilities.runecrafting.rcLvl_Provider;
import halestormxv.capabilities.spellcastlevel.SpellCastLvLProvider;
import halestormxv.init.ItemInit;
import halestormxv.network.packets.PacketChatUtils;
import halestormxv.utils.interfaces.IRuneCraftLevel;
import halestormxv.utils.interfaces.ISpellCastLevel;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class EventHandler
{
    @SubscribeEvent
    public void livingDrops(LivingDropsEvent event)
    {
        if (event.getEntity() instanceof EntityMob)
        {
            Random dChance = new Random();
            int rareDrop = dChance.nextInt(100) + 1;
            if (rareDrop < 20 && (event.getSource().getTrueSource() instanceof EntityPlayer))
            {
                Random random = new Random();
                ItemStack itemStackToDrop = new ItemStack(ItemInit.DUST_MYSTERIUM, random.nextInt(3));
                event.getDrops().add(new EntityItem(event.getEntity().world, event.getEntity().posX,
                        event.getEntity().posY, event.getEntity().posZ, itemStackToDrop));
            }
        }
    }

    @SubscribeEvent
    public void entityJoinWorld(EntityJoinWorldEvent event)
    {
        if (event.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            IRuneCraftLevel runeCraftLevel = player.getCapability(rcLvl_Provider.RUNECRAFT_LEVEL, null);
            ISpellCastLevel spellCastLevel = player.getCapability(SpellCastLvLProvider.SPELL_CAST_LEVEL_CAP, null);
            if (runeCraftLevel != null && spellCastLevel != null)
            {
                PacketChatUtils.sendNoSpam(player,"\u00A73Rune Craft Level: " + runeCraftLevel.getRuneLevel(),
                        "\u00A72Spell Casting Level: " + spellCastLevel.getSpellCastLevel() );
            }
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event)
    {
        EntityPlayer player = event.getEntityPlayer();

        //Make Sure the Rune Crafting Level Data is Saved
        IRuneCraftLevel runeCraftLevel = player.getCapability(rcLvl_Provider.RUNECRAFT_LEVEL, null);
        IRuneCraftLevel oldLevel = event.getOriginal().getCapability(rcLvl_Provider.RUNECRAFT_LEVEL, null);
        runeCraftLevel.setRuneLevel(oldLevel.getRuneLevel());

        //Make Sure the Spell Casting Level Data is Saved
        ISpellCastLevel spellCastLevel = player.getCapability(SpellCastLvLProvider.SPELL_CAST_LEVEL_CAP, null);
        ISpellCastLevel oldSpellCastLevel = event.getOriginal().getCapability(SpellCastLvLProvider.SPELL_CAST_LEVEL_CAP, null);
        spellCastLevel.setSpellCastLevel(oldSpellCastLevel.getSpellCastLevel());

        //Rune Satchel Bag Do What it Gotta Do
        NBTTagCompound bags = event.getOriginal().getCapability(RuneBagProvider.RUNEBAG_CAP, null).serializeNBT();
        event.getEntityPlayer().getCapability(RuneBagProvider.RUNEBAG_CAP, null).deserializeNBT(bags);
    }

    @SubscribeEvent
    public static void respawnEvent(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent event)
    {
        event.player.getCapability(RuneBagProvider.RUNEBAG_CAP, null).sync(null, (EntityPlayerMP) event.player);
    }

    @SubscribeEvent
    public static void playerConnect(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event)
    {
        EntityPlayerMP player = (EntityPlayerMP) event.player;
        player.getCapability(RuneBagProvider.RUNEBAG_CAP, null).sync(null, player);
    }
}
