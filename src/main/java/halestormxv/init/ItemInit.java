package halestormxv.init;

import halestormxv.objects.items.ItemBase;
import halestormxv.objects.items.ItemRunes;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemInit
{
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item INGOT_PHENET = new ItemBase("ingot_phenet");
    public static final Item DUST_SIEGRE = new ItemBase("dust_siegre");
    public static final Item ITEM_RUNE = new ItemRunes("item_runestone");
}
