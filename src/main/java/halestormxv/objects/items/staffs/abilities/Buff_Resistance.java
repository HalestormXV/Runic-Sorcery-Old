package halestormxv.objects.items.staffs.abilities;

import halestormxv.api.ReagentControl;
import halestormxv.init.ItemInit;
import halestormxv.network.packets.PacketChatUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class Buff_Resistance
{
    public static void applyResistanceSelf(World worldIn, EntityPlayer playerIn, EnumHand handIn, ItemStack reagentRequired, int reagentCost)
    {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        //IRuneCraftLevel runeCraftLevel = playerIn.getCapability(rcLvl_Provider.RUNECRAFT_LEVEL, null);
        //Make a Caster Level Perhaps?
        if (playerIn.inventory.hasItemStack(new ItemStack(ItemInit.ITEM_RUNE, reagentCost, 4)))
        {
            if (ReagentControl.checkForReagentQuantity(reagentRequired, playerIn) >= reagentCost)
            {
                playerIn.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("resistance"), 1800, 3));
                ReagentControl.consumeReagent(reagentRequired, reagentCost, worldIn, playerIn);
                heldItem.damageItem(1, playerIn);
            }else{
                PacketChatUtils.sendNoSpam(playerIn, "\u00A74This ability requires "+reagentCost+" "+reagentRequired.getDisplayName()+ "(s) to cast.");
            }
        }else{
            PacketChatUtils.sendNoSpam(playerIn, "\u00A74This ability requires "+reagentCost+" "+reagentRequired.getDisplayName()+ "(s) to cast.");
        }
    }
}
