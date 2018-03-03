package halestormxv.init;

import halestormxv.objects.items.*;
import halestormxv.objects.items.staffs.ApprenticeStaff;
import halestormxv.objects.items.tomes.Tome_Empower;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;

import java.util.ArrayList;
import java.util.List;

public class ItemInit
{
    public static final List<Item> ITEMS = new ArrayList<Item>();

    //DUSTS\\
    public static final Item DUST_MYSTERIUM = new ItemBase("dust_mysterium");
    public static final Item DUST_SIEGRE = new ItemBase("dust_siegre");

    //BASIC ITEMS\\
    public static final Item INGOT_PHENET = new ItemBase("ingot_phenet");
    public static final Item INGOT_LUPRESIUM = new ItemBase("ingot_lupresium");
    public static final Item INGOT_APRONYX = new ItemBase("ingot_apronyx");
    public static final Item INGOT_XOSHIAN = new ItemBase("ingot_xoshian");

    //SPELL TOMES\\
    public static final Item TOME_BASE = new SpellTomeBase("spell_tome_base");
    public static final Item TOME_EMPOWER = new Tome_Empower("spell_tome_empower");

    //META DATA ITEMS\\
    public static final Item ITEM_RUNE = new ItemRunes("item_runestone");
    public static final Item ITEM_RUNE_BAG = new RuneBag("item_runebag");
    public static final Item RUNE_ESSENCE = new ItemEssence("item_essence");
    public static final Item MAGICIANS_CORE = new ItemMagicianCore("magicians_core");

    //STAVES\\
    public static final Item STAFF_FLEDGLING = new ItemBaseStaff("staff_fledgling");
    public static final Item STAFF_APRENTICE = new ApprenticeStaff("staff_apprentice");
}
