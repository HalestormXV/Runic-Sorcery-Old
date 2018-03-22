package halestormxv.objects.items;

import halestormxv.RunicSorcery;
import halestormxv.init.ItemInit;
import halestormxv.utility.Reference;
import halestormxv.utility.interfaces.IHasModel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.Map;


public class ItemRuneBlade extends ItemSword implements IHasModel
{
    private static ItemStackHandler handler;

    public ItemRuneBlade(String name, ToolMaterial material)
    {
        super(material);
        handler = new ItemStackHandler(4);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(RunicSorcery.RUNICSORCERY_SPECIAL);
        setMaxStackSize(1);
        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        RunicSorcery.proxy.registerItemRenderer(this, 0 , "inventory");
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        ItemStack stackToSaveTo = player.getHeldItem(hand);
        if(handler == null)
        {
            handler = (ItemStackHandler) initCapabilities(stackToSaveTo, null).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        }
        NBTTagCompound nbt = new NBTTagCompound();
        handler.deserializeNBT(nbt);
        if (!world.isRemote)
        {
            if (player.isSneaking())
            player.openGui(RunicSorcery.instance, Reference.GUI_RUNE_BLADE, world, hand.ordinal(), -1, -1);
        }

        return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
        handler.serializeNBT();
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack item, NBTTagCompound nbt)
    {
        if(item.getItem() instanceof ItemRuneBlade)
        {
            return new RuneSlotHandler();
        }
        return null;
    }

    public static class RuneSlotHandler implements ICapabilityProvider, INBTSerializable<NBTTagCompound>
    {
        @Override
        //Retrieve data here
        public NBTTagCompound serializeNBT()
        {
            NBTTagCompound ret = new NBTTagCompound();
            NBTBase inv = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().writeNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, handler, null);
            ret.setTag("RunesStored", inv);
            return ret;
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt)
        {
            //Do your saving here
            if (nbt.hasKey("RunesStored"))
            {
                NonNullList<ItemStack> inventory = NonNullList.withSize(4, ItemStack.EMPTY);
                CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().readNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, handler, null, nbt.getTag("RunesStored"));
                ItemStackHelper.saveAllItems(nbt, inventory);
            }
        }

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing)
        {
            return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing)
        {
            if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            {
                return (T) new ItemStackHandler(4/**the amount of slots you want*/);
                //This is the default implementation by forge, but you'll likely want to make your own by overriding.
            }
            return null;
        }
    }
}
