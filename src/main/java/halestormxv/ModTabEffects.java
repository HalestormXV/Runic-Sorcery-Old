package halestormxv;

import halestormxv.potion.ModPotionHelper;
import halestormxv.potion.PotionTypeBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.util.NonNullList;

import java.util.ArrayList;

public class ModTabEffects extends CreativeTabs
{
    private static ItemStack ICON_STACK;

    public ModTabEffects(String label)
    {
        super("RunicSorceryPotions");
        ICON_STACK = new ItemStack(Items.POTIONITEM);
    }

    @Override
    public ItemStack getIconItemStack()
    {
        return ICON_STACK;
    }

    @Override
    public void displayAllRelevantItems(NonNullList<ItemStack> list) {
        ArrayList<ItemStack> added_list = new ArrayList<ItemStack>();
        try {
            for (PotionType t:PotionType.REGISTRY) {
                if (t instanceof PotionTypeBase) {
                    ItemStack is = ModPotionHelper.getItemStackOfPotion(Items.POTIONITEM, t);
                    added_list.add(is);
                    list.add(is);
                }
            }
            for (ItemStack s:added_list) {
                list.add(ModPotionHelper.transformToSplash(s));
            }
            for (ItemStack s:added_list) {
                list.add(ModPotionHelper.transformToLingering(s));
            }
            for (ItemStack s:added_list) {
                list.add(ModPotionHelper.transformToArrow(s));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.displayAllRelevantItems(list);
    }


    @Override
    public ItemStack getTabIconItem() {
        return ICON_STACK;
    }
}
