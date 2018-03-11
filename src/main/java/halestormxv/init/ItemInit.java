package halestormxv.init;

import halestormxv.objects.items.*;
import halestormxv.objects.items.records.Record_Kishuu;
import halestormxv.objects.items.records.Record_Motomiya;
import halestormxv.objects.items.spellblades.SpellBlade_Fire;
import halestormxv.objects.items.spellblades.SpellBlade_Weakness;
import halestormxv.objects.items.staffs.ApprenticeStaff;
import halestormxv.objects.items.tomes.Tome_Empower;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class ItemInit
{
    //Materials
    private static Item.ToolMaterial SpecialItems = EnumHelper.addToolMaterial("SpecialItems", 4, 1340, 11.0F, 9.2F, 25);

    public static final List<Item> ITEMS = new ArrayList<Item>();

    //DUSTS\\
    public static final Item DUST_MYSTERIUM = new ItemBase("dust_mysterium");
    public static final Item DUST_SIEGRE = new ItemBase("dust_siegre");

    //BASIC ITEMS\\
    public static final Item INGOT_PHENET = new ItemBase("ingot_phenet");
    public static final Item INGOT_LUPRESIUM = new ItemBase("ingot_lupresium");
    public static final Item INGOT_APRONYX = new ItemBase("ingot_apronyx");
    public static final Item INGOT_XOSHIAN = new ItemBase("ingot_xoshian");
    public static final Item ITEM_SCRYING_ORB = new ItemScryingOrb("scrying_orb");

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

    //SPELL BLADES\\
    public static final Item SPELL_BLADE_FIRE = new SpellBlade_Fire("spellblade_flame", SpecialItems);
    public static final Item SPELL_BLADE_WEAKNESS = new SpellBlade_Weakness("spellblade_weakness", SpecialItems);

    //RECORDS\\
    public static final Item RECORD_KISHUU = new Record_Kishuu();
    public static final Item RECORD_MOTOMIYA = new Record_Motomiya();
}
