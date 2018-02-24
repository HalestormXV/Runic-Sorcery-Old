package halestormxv.capabilities.runebag;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class RuneBagContainer extends Container
{
    public final EnumHand hand;
    private final int blocked;

    public RuneBagContainer(InventoryPlayer invPlayer, EnumHand hand, IItemHandlerModifiable invBag)
    {
        this.hand = hand;

        //Bag Inventory
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 13; j++)
                this.addSlotToContainer(new SlotItemHandler(invBag, j + i * 13, 12 + j * 18, 5 + i * 18));

        //Player Inventory
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 9; j++)
                this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 48 + j * 18, 152 + i * 18));

        //Player Hotbar
        for (int i = 0; i < 9; i++)
            this.addSlotToContainer(new Slot(invPlayer, i, 48 + i * 18, 210));

        blocked = hand == EnumHand.MAIN_HAND ? (inventorySlots.size() - 1) - (8 - invPlayer.currentItem) : -1;
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer player)
    {
        return true;
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        Slot slot = this.getSlot(slotIndex);

        if (slot == null || !slot.getHasStack())
        {
            return ItemStack.EMPTY;
        }

        ItemStack stack = slot.getStack();
        ItemStack newStack = stack.copy();

        if (slotIndex < 104)
        {
            if (!this.mergeItemStack(stack, 104, this.inventorySlots.size(), true))
                return ItemStack.EMPTY;
            slot.onSlotChanged();
        }
        else if (!this.mergeItemStack(stack, 0, 104, false))
        {
            return ItemStack.EMPTY;
        }
        if (stack.isEmpty())
        {
            slot.putStack(ItemStack.EMPTY);
        }
        else
        {
            slot.onSlotChanged();
        }

        return slot.onTake(player, newStack);
    }

    @Nonnull
    @Override
    public ItemStack slotClick(int slot, int button, ClickType flag, EntityPlayer player)
    {
        if (slot == blocked)
        {
            return ItemStack.EMPTY;
        }

        return super.slotClick(slot, button, flag, player);
    }
}
