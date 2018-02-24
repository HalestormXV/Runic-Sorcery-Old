package halestormxv;

import halestormxv.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

public class ModTab extends CreativeTabs
{
    public ModTab(String label) { super("RunicSorcery"); }
    public ItemStack getTabIconItem() { return new ItemStack(ItemInit.INGOT_PHENET); }

    public class ModTab2 extends ModTab { private ModTab2(String label) { super("RunicSorceryPotions"); }
    public ItemStack getTabIconItem() { return new ItemStack(Items.POTIONITEM); }}
}
