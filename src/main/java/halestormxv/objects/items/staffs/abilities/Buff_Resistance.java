package halestormxv.objects.items.staffs.abilities;

import halestormxv.api.ReagentControl;
import halestormxv.network.packets.PacketChatUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;

import java.util.List;

public class Buff_Resistance
{
    //IRuneCraftLevel runeCraftLevel = playerIn.getCapability(rcLvl_Provider.RUNECRAFT_LEVEL, null);
    //Make a Caster Level Perhaps?
    public static void applyResistanceSelf(EntityPlayer playerIn, EnumHand handIn, ItemStack reagentRequired)
    {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        List<ItemStack> getResistanceCost = new AbilityCosts().getResistanceReagents();
        boolean passed = ReagentControl.checkReagentListAndConsume(playerIn, getResistanceCost);
        if(passed)
        {
            playerIn.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("resistance"), 1800, 1));
            heldItem.damageItem(1, playerIn);
        }
    }

    public static void applyFireResistance(EntityPlayer playerIn, EnumHand handIn, ItemStack reagentRequired)
    {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        List<ItemStack> getFireResistanceCost = new AbilityCosts().getFireResistanceReagents();
        boolean passed = ReagentControl.checkReagentListAndConsume(playerIn, getFireResistanceCost);
        if(passed)
        {
            playerIn.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("fire_resistance"), 1800, 1));
            heldItem.damageItem(1, playerIn);
        }
    }
}
