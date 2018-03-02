package halestormxv.objects.items.staffs.abilities;

import halestormxv.api.ReagentControl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;

import java.util.List;

public class Empower
{
    public static void applyEmpower(EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        List<ItemStack> getEmpowerReagents = new AbilityCosts().getEmpowerReagents();
        boolean passed = ReagentControl.checkReagentListAndConsume(playerIn, getEmpowerReagents);
        if(passed)
        {
            playerIn.getEntityWorld().spawnParticle(EnumParticleTypes.CRIT_MAGIC, playerIn.posX, playerIn.posY, playerIn.posZ, 1,1,1);
            playerIn.getEntityWorld().playSound(playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.elder_guardian.curse")), SoundCategory.AMBIENT, 1.5F, 8F, false);
            heldItem.damageItem(1, playerIn);
        }
    }
}
