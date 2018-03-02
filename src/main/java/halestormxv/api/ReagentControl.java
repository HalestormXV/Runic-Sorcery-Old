package halestormxv.api;

import halestormxv.capabilities.runebag.RuneBagProvider;
import halestormxv.objects.items.RuneBag;
import halestormxv.utility.interfaces.IRuneBagProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.List;

import static halestormxv.utility.Logging.getLogger;

public class ReagentControl
{
    public static boolean checkReagentListAndConsume(EntityPlayer thePlayer, List<ItemStack> reagentsRequired)
    {
        IItemHandler playerInventory = thePlayer.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        IRuneBagProvider theRuneBag = thePlayer.getCapability(RuneBagProvider.RUNEBAG_CAP, null);
        int counter = reagentsRequired.size();
        for (int index = reagentsRequired.size() - 1; index >= 0; index--)
        {
            boolean hasReagent = thePlayer.inventory.hasItemStack(reagentsRequired.get(index));
            if (hasReagent)
            {
                for (int slot = 0; slot < playerInventory.getSlots(); slot++)
                {
                    int total;
                    ItemStack stack = playerInventory.getStackInSlot(slot);
                    if (!stack.isEmpty() && stack.getItem().equals(reagentsRequired.get(index).getItem()) && stack.getMetadata() == reagentsRequired.get(index).getMetadata())
                    {
                        total = stack.getCount();
                        if (total >= reagentsRequired.get(index).getCount())
                        {
                            stack.shrink(reagentsRequired.get(index).getCount());
                            getLogger().info("Player has " + (total - reagentsRequired.get(index).getCount()) + " " + stack.getDisplayName() +
                                    " in the slot cause I ate " + reagentsRequired.get(index).getCount());
                            --counter;
                        }
                    }
                }
            } else {
                for (int slot = 0; slot < playerInventory.getSlots(); slot++)
                {
                    ItemStack stack = playerInventory.getStackInSlot(slot);
                    if (!stack.isEmpty() && stack.getItem() instanceof RuneBag)
                    {
                        IItemHandler bagInventory = theRuneBag.getBag(EnumDyeColor.byMetadata(stack.getMetadata()));
                        for (int bagSlots = 0; bagSlots < bagInventory.getSlots(); bagSlots++)
                        {
                            int total;
                            ItemStack stackInBagSlot = bagInventory.getStackInSlot(bagSlots);
                            if (!stackInBagSlot.isEmpty() && stackInBagSlot.getItem().equals(reagentsRequired.get(index).getItem()) && stackInBagSlot.getMetadata() == reagentsRequired.get(index).getMetadata()) {
                                total = stackInBagSlot.getCount();
                                if (total >= reagentsRequired.get(index).getCount())
                                {
                                    bagInventory.getStackInSlot(bagSlots).shrink(reagentsRequired.get(index).getCount());
                                    getLogger().info("Player has " + (total - reagentsRequired.get(index).getCount()) + " " + stackInBagSlot.getDisplayName() +
                                            " in the Rune Bag cause I ate " + reagentsRequired.get(index).getCount());
                                    --counter;
                                }
                            }
                        }
                    }
                }
            }
            reagentsRequired.remove(index);
        }
        return counter == 0;
    }

    public static boolean checkAndConsumeReagent(EntityPlayer thePlayer, ItemStack itemStack, int reagentCost)
    {
        int total;
        Item reagentItem;
        IItemHandler playerInventory = thePlayer.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        IRuneBagProvider theRuneBag = thePlayer.getCapability(RuneBagProvider.RUNEBAG_CAP, null);
        reagentItem = itemStack.getItem();
        boolean hasReagent = thePlayer.inventory.hasItemStack(itemStack);
        if(hasReagent)
        {
            for (int slot = 0; slot < playerInventory.getSlots(); slot++)
            {
                ItemStack stack = playerInventory.getStackInSlot(slot);
                if (!stack.isEmpty() && stack.getItem().equals(reagentItem) && stack.getMetadata() == itemStack.getMetadata())
                {
                    total = stack.getCount();
                    if (total >= reagentCost)
                    {
                        stack.shrink(reagentCost);
                        getLogger().info("Player has " + (total-reagentCost) +" "+ stack.getDisplayName() +
                                " in the slot cause I ate "+reagentCost);
                        return true;
                    }
                }
            }
        }else{
            for (int slot = 0; slot < playerInventory.getSlots(); slot++)
            {
                ItemStack stack = playerInventory.getStackInSlot(slot);
                if (!stack.isEmpty() && stack.getItem() instanceof RuneBag)
                {
                    IItemHandler bagInventory = theRuneBag.getBag(EnumDyeColor.byMetadata(stack.getMetadata()));
                    reagentItem = itemStack.getItem();
                    for (int bagSlots = 0; bagSlots < bagInventory.getSlots(); bagSlots++)
                    {
                        ItemStack stackInBagSlot = bagInventory.getStackInSlot(bagSlots);
                        if (!stackInBagSlot.isEmpty() && stackInBagSlot.getItem().equals(reagentItem) && stackInBagSlot.getMetadata() == itemStack.getMetadata())
                        {
                            total = stackInBagSlot.getCount();
                            if (total >= reagentCost)
                            {
                                bagInventory.getStackInSlot(bagSlots).shrink(reagentCost);
                                getLogger().info("Player has " + (total-reagentCost) +" "+ stackInBagSlot.getDisplayName() +
                                        " in the Rune Bag cause I ate "+reagentCost);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public static int checkForReagentQuantityRuneBag(ItemStack itemStack, EntityPlayer thePlayer, int reagentCost)
    {
        int total = 0;
        Item reagentItem;
        IItemHandler itemHandler = thePlayer.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        for (int slot = 0; slot < itemHandler.getSlots(); slot++)
        {
            ItemStack stack = itemHandler.getStackInSlot(slot);
            if (!stack.isEmpty() && stack.getItem() instanceof RuneBag)
            {
                IRuneBagProvider theRuneBag = thePlayer.getCapability(RuneBagProvider.RUNEBAG_CAP, null);
                reagentItem = itemStack.getItem();
                IItemHandler bagHandler = theRuneBag.getBag(EnumDyeColor.WHITE);
                for (int bagSlots = 0; bagSlots < bagHandler.getSlots(); bagSlots++)
                {
                    ItemStack stackInBagSlot = bagHandler.getStackInSlot(bagSlots);
                    if (!stackInBagSlot.isEmpty() && stackInBagSlot.getItem().equals(reagentItem))
                    {
                        total = stackInBagSlot.getCount();
                        bagHandler.getStackInSlot(bagSlots).shrink(reagentCost);
                        return total;
                    }
                }
            }
        }
        return total;
    }

    public static int checkForReagentQuantity(ItemStack itemStack, EntityPlayer player) {
        int count = 0;
        Item reagentItem = itemStack.getItem();
        boolean hasReagent = player.inventory.hasItemStack(itemStack);
        if (hasReagent) {
            for (int slot = 0; slot < player.inventory.getSizeInventory(); slot++) {
                ItemStack stack = player.inventory.getStackInSlot(slot);
                if (stack != null && stack.getItem().equals(reagentItem)) {
                    int total = count += stack.getCount();
                    //System.out.println("Player has: " + total);
                    return total;
                }
            }
        } else {
            return 0;
        }
        return 0;
    }

    public static void consumeReagent(ItemStack itemStack, int meta, int reagentCost, World worldIn, EntityPlayer entityLiving) {
        Item reagentItem = itemStack.getItem();
        entityLiving.inventory.clearMatchingItems(reagentItem, meta, reagentCost, null);
    }
}

