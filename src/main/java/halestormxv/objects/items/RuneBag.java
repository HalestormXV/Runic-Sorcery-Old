package halestormxv.objects.items;

import halestormxv.RunicSorcery;
import halestormxv.init.ItemInit;
import halestormxv.utils.Reference;
import halestormxv.utils.interfaces.IHasModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public class RuneBag extends Item implements IHasModel
{
    private String name;

    public RuneBag(String name)
    {
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(RunicSorcery.RUNICSORCERY);
        this.setHasSubtypes(true);
        this.setMaxStackSize(1);
        this.setMaxDamage(0);

        this.name = name;

        ItemInit.ITEMS.add(this);
    }

    // MC Lang files have these unlocalized names mapped to raw color names
    private final String[] unlocalizedColors = {
            "item.fireworksCharge.white", "item.fireworksCharge.orange",
            "item.fireworksCharge.magenta", "item.fireworksCharge.lightBlue",
            "item.fireworksCharge.yellow", "item.fireworksCharge.lime",
            "item.fireworksCharge.pink", "item.fireworksCharge.gray",
            "item.fireworksCharge.silver", "item.fireworksCharge.cyan",
            "item.fireworksCharge.purple", "item.fireworksCharge.blue",
            "item.fireworksCharge.brown", "item.fireworksCharge.green",
            "item.fireworksCharge.red", "item.fireworksCharge.black"
    };

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        if (!world.isRemote) {
            player.openGui(RunicSorcery.instance, Reference.GUI_RUNE_BAG, world, hand.ordinal(), -1, -1);
        }

        return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        String name = super.getItemStackDisplayName(stack);
        int i = stack.getItemDamage();
        if (stack.getItemDamage() > 15) {
            return name + " (" + I18n.translateToLocal("hsrs.debug.metainvalid.name") + ")";
        }

        String color = " (" + I18n.translateToLocal(unlocalizedColors[i]) + ")";
        return name + color;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs cTab, NonNullList<ItemStack> list) {
        if (isInCreativeTab(cTab)) {
            for (int i = 0; i < 16; ++i)
                list.add(new ItemStack(this, 1, i));
        }
    }

    public void registerModels()
    {
        for (EnumDyeColor e : EnumDyeColor.values())
        {
            ModelLoader.setCustomModelResourceLocation(ItemInit.ITEM_RUNE_BAG, e.getMetadata(), new ModelResourceLocation("hsrs:bags/runebag_" + e.getName(), "inventory"));
        }
    }

    /*public static ItemStack getFirstBagWithSuctionItem(EntityPlayer player, NonNullList<ItemStack> inventory) {
        for (ItemStack stack : inventory) {
            if (stack.isEmpty()) {
                continue;
            }

            if (stack.getItem() == ObjHandler.alchBag) {
                IItemHandler inv = player.getCapability(ProjectEAPI.ALCH_BAG_CAPABILITY, null)
                        .getBag(EnumDyeColor.byMetadata(stack.getItemDamage()));
                for (int i = 0; i < inv.getSlots(); i++) {
                    ItemStack ring = inv.getStackInSlot(i);

                    if (!ring.isEmpty() && (ring.getItem() instanceof BlackHoleBand || ring.getItem() instanceof VoidRing)) {
                        if (ItemHelper.getOrCreateCompound(ring).getBoolean(TAG_ACTIVE)) {
                            return stack;
                        }
                    }
                }
            }
        }

        return ItemStack.EMPTY;
    }*/
}
