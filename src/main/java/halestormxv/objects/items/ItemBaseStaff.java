package halestormxv.objects.items;

import halestormxv.RunicSorcery;
import halestormxv.init.ItemInit;
import halestormxv.utils.interfaces.IHasModel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

public class ItemBaseStaff extends Item implements IHasModel
{

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
}
