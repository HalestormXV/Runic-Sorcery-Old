package halestormxv.api;

import halestormxv.network.packets.PacketChatUtils;
import halestormxv.objects.items.ItemBaseStaff;
import halestormxv.objects.items.staffs.ApprenticeStaff;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;

public class CycleSpells {
    public static void cycleSpells(EntityPlayer playerIn, EnumHand handIn) {
        ItemStack theStaff = playerIn.getHeldItem(handIn);
        //Apprentice Staff
        if (theStaff.getItem() instanceof ItemBaseStaff) {
            NBTTagCompound activeSpell = theStaff.getTagCompound();
            if (activeSpell != null) {
                int currentSpell = activeSpell.getInteger("SpellSelected");
                switch (currentSpell) {
                    case 1:
                        activeSpell.setInteger("SpellSelected", ++currentSpell);
                        theStaff.setTagCompound(activeSpell);
                        PacketChatUtils.sendNoSpam(playerIn, "\u00A7dSet spell to Boost Fire Resistance");
                        break;

                    case 2:
                        activeSpell.setInteger("SpellSelected", --currentSpell);
                        theStaff.setTagCompound(activeSpell);
                        PacketChatUtils.sendNoSpam(playerIn, "\u00A7dSet spell to Boost Resistance");
                        break;

                    default:
                        break;
                }
            }
        }
    }
}
