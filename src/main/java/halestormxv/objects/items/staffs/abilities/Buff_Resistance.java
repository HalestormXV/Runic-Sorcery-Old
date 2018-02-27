package halestormxv.objects.items.staffs.abilities;

import halestormxv.api.ReagentControl;
import halestormxv.capabilities.runebag.RuneBagProvider;
import halestormxv.network.packets.PacketChatUtils;
import halestormxv.utils.Logging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class Buff_Resistance {
    //IRuneCraftLevel runeCraftLevel = playerIn.getCapability(rcLvl_Provider.RUNECRAFT_LEVEL, null);
    //Make a Caster Level Perhaps?

    public static void applyResistanceSelf(EntityPlayer playerIn, EnumHand handIn, ItemStack reagentRequired)
    {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        boolean passed = ReagentControl.checkAndConsumeReagent(playerIn, reagentRequired, reagentRequired.getCount());
        if(passed)
        {
            playerIn.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("resistance"), 1800, 1));
            heldItem.damageItem(1, playerIn);
        }else{
            PacketChatUtils.sendNoSpam(playerIn, "\u00A74This ability requires " + reagentRequired.getCount() + " " + reagentRequired.getDisplayName() + "(s) to cast.");
        }
    }

    public static void applyFireResistance(EntityPlayer playerIn, EnumHand handIn, ItemStack reagentRequired)
    {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        boolean passed = ReagentControl.checkAndConsumeReagent(playerIn, reagentRequired, reagentRequired.getCount());
        if(passed)
        {
            playerIn.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("fire_resistance"), 1800, 1));
            heldItem.damageItem(1, playerIn);
        }else{
            PacketChatUtils.sendNoSpam(playerIn, "\u00A74This ability requires " + reagentRequired.getCount() + " " + reagentRequired.getDisplayName() + "(s) to cast.");
        }
    }
}
