package halestormxv.objects.items;

import halestormxv.RunicSorcery;
import halestormxv.capabilities.runecrafting.IRuneCraftLevel;
import halestormxv.capabilities.runecrafting.rcLvl_Provider;
import halestormxv.init.ItemInit;
import halestormxv.utility.interfaces.IHasModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class RuneCraftTalisman extends Item implements IHasModel
{
    public RuneCraftTalisman(String name)
    {
        super();
        setUnlocalizedName(name);
        setCreativeTab(RunicSorcery.RUNICSORCERY);
        setRegistryName(name);
        setMaxStackSize(1);
        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        RunicSorcery.proxy.registerItemRenderer(this, 0, "inventory");
    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        if (!world.isRemote)
        {
            ItemStack talisman = player.getHeldItem(hand);
            NBTTagCompound nbt = talisman.getTagCompound();
            if (player.isSneaking())
            {
                if (nbt == null)
                {
                    nbt = new NBTTagCompound();
                    player.sendMessage(new TextComponentString("The Talisman has attuned to you."));
                    nbt.setFloat("Xp", 0);
                    talisman.setTagCompound(nbt);
                }else{
                    IRuneCraftLevel runeCraftLevel = player.getCapability(rcLvl_Provider.RUNECRAFT_LEVEL, null);
                    int currentRuneLevel = runeCraftLevel.getRuneLevel();
                    float currentXP = nbt.getFloat("Xp");
                    float xpRequired = runeCraftLevelCalc(currentRuneLevel);
                    if(currentXP >= xpRequired)
                    {
                        runeCraftLevel.gainRuneLevel(1);
                    }
                    nbt.setInteger("RCLvL", runeCraftLevel.getRuneLevel());
                }
            }
        }
        return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add("");
        tooltip.add("\u00A76" + "This item will store all XP gained");
        tooltip.add("\u00A76" + "from Rune Crafting. Once a level is");
        tooltip.add("\u00A76" + "ready to be earned one must Shift+Right Click.");
        tooltip.add("\u00A74" + "Be Warned, a seal can be stolen.");
        tooltip.add("");
        if (stack.getTagCompound() != null)
        {
            NBTTagCompound nbt = stack.getTagCompound();
            float Xp = nbt.getFloat("Xp");
            tooltip.add("\u00A73" + "Xp Stored: " +  Math.round(Xp*100)/100);
            float xpRequired = runeCraftLevelCalc(nbt.getInteger("RCLvL"));
            if(Xp >= xpRequired)
            {
                tooltip.add("\u00A72" + "Ready for Rune Craft Ascension!");
            }else{
                tooltip.add("\u00A72" + Xp +" / "+ xpRequired);
            }
            tooltip.add("");
        }
    }

    private float runeCraftLevelCalc(int lvl)
    {
        double points = 0;
        float output = 0;
        int minlevel = 1; // first level to display
        int maxlevel = 100; // last level to display
        int currentLevel = lvl;
        //for (lvl = currentLevel; lvl <= maxlevel; lvl++)
        //{
            //points += Math.floor(lvl + 175 * Math.pow(2, lvl / 7.));
            //if (lvl >= minlevel)
            //    output = (float) Math.floor(points / 4);
            //System.out.println("Level " + (lvl) + " - " + output + " xp will be required to ascend.");
        //}
        points += Math.floor(lvl + 175 * Math.pow(2, lvl / 7.));
        if (lvl >= minlevel)
            output = (float) Math.floor(points / 4);
        return output;
    }
}