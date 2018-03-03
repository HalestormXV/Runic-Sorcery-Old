package halestormxv.objects.items.tomes;

import halestormxv.abilities.AbilityCosts;
import halestormxv.abilities.Empower;
import halestormxv.capabilities.runecrafting.rcLvl_Provider;
import halestormxv.capabilities.spellcastlevel.SpellCastLvLProvider;
import halestormxv.objects.items.SpellTomeBase;
import halestormxv.utility.Reference;
import halestormxv.utility.interfaces.IHasModel;
import halestormxv.utility.interfaces.IRuneCraftLevel;
import halestormxv.utility.interfaces.ISpellCastLevel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Tome_Empower extends SpellTomeBase implements IHasModel
{
    public Tome_Empower(String name)
    {
        super(name);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        Empower.applyEmpower(playerIn, handIn);
        return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("");
        tooltip.add("\u00A76" + "A Spell Tome that");
        tooltip.add("\u00A76" + "will teach the player");
        tooltip.add("\u00A74Empower" + "\u00A76. Right Click to learn.");
        tooltip.add("");
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return EnumRarity.EPIC;
    }
}
