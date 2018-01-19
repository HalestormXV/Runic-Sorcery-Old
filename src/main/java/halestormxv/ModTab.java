package halestormxv;

import halestormxv.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModTab extends CreativeTabs
{
    public ModTab(String label) { super("RunicSorcery"); }
    public ItemStack getTabIconItem() { return new ItemStack(ItemInit.INGOT_PHENET);}
}
