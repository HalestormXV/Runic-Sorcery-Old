package halestormxv.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

public class ReagentControl {
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
    public static int checkForReagentQuantityRuneBag(ItemStack itemStack, IItemHandler itemHandler)
    {
        int count = 0;
        Item reagentItem = itemStack.getItem();
        for (int index = 0; index < itemHandler.getSlots(); index++)
        {
            ItemStack stack = itemHandler.getStackInSlot(index);
            if (!stack.isEmpty() && stack.getItem().equals(reagentItem))
            {
                int total = count += stack.getCount();
                System.out.println("There are "+total+" "+stack.getDisplayName()+" in the inventory.");
                return total;
            }
            else { return 0; }
        }
        return 0;
    }
}
