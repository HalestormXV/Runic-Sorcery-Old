package halestormxv.api;

import halestormxv.capabilities.runebag.RuneBagProvider;
import halestormxv.init.ItemInit;
import halestormxv.objects.items.RuneBag;
import halestormxv.utils.Logging;
import halestormxv.utils.interfaces.IRuneBagProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.stream.Stream;

import static halestormxv.utils.Logging.getLogger;

public class ReagentControl
{
    public static void consumeReagent(ItemStack itemStack, int meta, int reagentCost, World worldIn, EntityPlayer entityLiving) {
        Item reagentItem = itemStack.getItem();
        entityLiving.inventory.clearMatchingItems(reagentItem, meta, reagentCost, null);
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

    //Work on This Some More - almost got it? Maybe?
    public static int checkForReagentQuantityRuneBag(ItemStack itemStack, EntityPlayer thePlayer)
    {
        int total = 0;
        System.out.println("Good news is I am getting called at least.");
        Item reagentItem;
        for (int slot = 0; slot < thePlayer.inventory.getSizeInventory(); slot++)
        {
            ItemStack stack = thePlayer.inventory.getStackInSlot(slot);
            if (!stack.isEmpty() && stack.getItem() instanceof RuneBag)
            {
                //LEFT OFF OVER HERE\\
                getLogger().info("I found the rune bag while going through all the slots!");
                NBTTagCompound bagData = stack.serializeNBT();
                getLogger().info(bagData);
                IRuneBagProvider theRuneBag = stack.getCapability(RuneBagProvider.RUNEBAG_CAP, null);
                theRuneBag.getBag(EnumDyeColor.WHITE);
                reagentItem = itemStack.getItem();
                IItemHandler bagHandler = new ItemStackHandler(theRuneBag.getBag(EnumDyeColor.WHITE).getSlots());
                for (int bagSlots = 0; bagSlots < bagHandler.getSlots(); bagSlots++)
                {
                    getLogger().info("I made it all the way into the bag!");
                    ItemStack stackInBagSlot = bagHandler.getStackInSlot(bagSlots);
                    if (!stackInBagSlot.isEmpty() && stackInBagSlot.getItem().equals(reagentItem))
                    {
                        total = stackInBagSlot.getCount();
                        System.out.println("Player has: " + total + stackInBagSlot.getDisplayName() + " in the Rune Bag.");
                        return total;
                    }
                }
            } else {
                getLogger().info("Sorry Kappa, you almost got it.");
            }
            getLogger().info("Loopy loopy loop. You have a problem in your loop!");
        }
        getLogger().info("Sorry Kappa, no Rune Bag here.");
        return total;
    }
}
