package halestormxv.objects.items.staffs.abilities;

import halestormxv.api.ReagentControl;
import halestormxv.network.packets.PacketChatUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class Buff_Resistance
{
    //IRuneCraftLevel runeCraftLevel = playerIn.getCapability(rcLvl_Provider.RUNECRAFT_LEVEL, null);
    //Make a Caster Level Perhaps?

    public static void applyResistanceSelf(World worldIn, EntityPlayer playerIn, EnumHand handIn, ItemStack reagentRequired, int reagentCost)
    {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        if (playerIn.inventory.hasItemStack(new ItemStack(reagentRequired.getItem(), reagentCost, reagentRequired.getMetadata())))
        {
            if (ReagentControl.checkForReagentQuantity(reagentRequired, playerIn) >= reagentCost)
            {
                playerIn.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("resistance"), 1800, 1));
                ReagentControl.consumeReagent(reagentRequired, reagentRequired.getMetadata(), reagentCost, worldIn, playerIn);
                heldItem.damageItem(1, playerIn);
            }else{
                PacketChatUtils.sendNoSpam(playerIn, "\u00A74This ability requires "+reagentCost+" "+reagentRequired.getDisplayName()+ "(s) to cast.");
            }
        }else{
            PacketChatUtils.sendNoSpam(playerIn, "\u00A74This ability requires "+reagentCost+" "+reagentRequired.getDisplayName()+ "(s) to cast.");
        }
    }

    public static void applyFireResistance(World worldIn, EntityPlayer playerIn, EnumHand handIn, ItemStack reagentRequired, int reagentCost)
    {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        if (playerIn.inventory.hasItemStack(new ItemStack(reagentRequired.getItem(), reagentCost, reagentRequired.getMetadata())))
        {
            if (ReagentControl.checkForReagentQuantity(reagentRequired, playerIn) >= reagentCost)
            {
                playerIn.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("fire_resistance"), 1800, 1));
                ReagentControl.consumeReagent(reagentRequired, reagentRequired.getMetadata(), reagentCost, worldIn, playerIn);
                heldItem.damageItem(1, playerIn);
            }else{
                PacketChatUtils.sendNoSpam(playerIn, "\u00A74This ability requires "+reagentCost+" "+reagentRequired.getDisplayName()+ "(s) to cast.");
            }
        }else{
            PacketChatUtils.sendNoSpam(playerIn, "\u00A74This ability requires "+reagentCost+" "+reagentRequired.getDisplayName()+ "(s) to cast.");
        }
    }
}
