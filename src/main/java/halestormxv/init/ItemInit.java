package halestormxv.init;

import halestormxv.objects.items.ItemBase;
import halestormxv.objects.items.ItemBaseStaff;
import halestormxv.objects.items.ItemRunes;
import halestormxv.objects.items.staffs.ApprenticeStaff;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemInit
{
    public static final List<Item> ITEMS = new ArrayList<Item>();

    //DUSTS\\
    public static final Item DUST_MYSTERIUM = new ItemBase("dust_mysterium");
    public static final Item DUST_SIEGRE = new ItemBase("dust_siegre");

    //INGOTS AND METALS\\
    public static final Item INGOT_PHENET = new ItemBase("ingot_phenet");

    //META DATA ITEMS\\
    public static final Item ITEM_RUNE = new ItemRunes("item_runestone");

    //STAVES\\
    public static final Item STAFF_FLEDGLING = new ItemBaseStaff("staff_fledgling");
    public static final Item STAFF_APRENTICE = new ApprenticeStaff("staff_apprentice");

}
