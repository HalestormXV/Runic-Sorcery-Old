package halestormxv.objects.items.staffs;

import halestormxv.init.ItemInit;
import halestormxv.objects.items.staffs.abilities.*;
import halestormxv.objects.items.ItemBaseStaff;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ApprenticeStaff extends ItemBaseStaff
{

    public ApprenticeStaff(String name)
    {
        super(name);
        setMaxDamage(20);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack reagentRequired = new ItemStack(ItemInit.ITEM_RUNE, 1, 4);
        Buff_Resistance.applyResistanceSelf(worldIn, playerIn, handIn, reagentRequired, 2);
        return  new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
