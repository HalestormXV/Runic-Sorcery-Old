package halestormxv.objects.items;

import halestormxv.RunicSorcery;
import halestormxv.init.ItemInit;
import halestormxv.objects.items.staffs.abilities.AbilityCosts;
import halestormxv.objects.items.staffs.abilities.Empower;
import halestormxv.utility.interfaces.IHasModel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.List;

public class ItemBaseStaff extends Item implements IHasModel
{
    private List<ItemStack> getEmpowerCosts = new AbilityCosts().getEmpowerReagents();
    public ItemBaseStaff(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(RunicSorcery.RUNICSORCERY);
        setMaxStackSize(1);
        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        RunicSorcery.proxy.registerItemRenderer(this, 0 , "inventory");
    }

    @Override
    public boolean isDamageable() { return false; }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        if (!worldIn.isRemote) {
            ItemStack theStaff = playerIn.getHeldItem(handIn);
            NBTTagCompound activeSpell = theStaff.getTagCompound();
            if (activeSpell != null) {
                int spellSelected = activeSpell.getInteger("SpellSelected");
                switch (spellSelected)
                {
                    case 1:
                        Empower.applyEmpower(playerIn, handIn);
                        break;

                    default:
                        break;
                }
            } else {
                NBTTagCompound nbt;
                nbt = new NBTTagCompound();
                nbt.setInteger("SpellSelected", 1);
                for (int i = 0; i < getEmpowerCosts.size(); i++) {
                    nbt.setString("RuneName" + (i), getEmpowerCosts.get(i).getDisplayName());
                    nbt.setInteger("RuneCost" + (i), getEmpowerCosts.get(i).getCount());
                }
                theStaff.setTagCompound(nbt);
                return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }
        }
        return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
