package halestormxv.capabilities.runebag;

import halestormxv.init.ItemInit;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class RuneBagSlotHandler extends SlotItemHandler
{
    public RuneBagSlotHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition)
    {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        return (stack.getItem() == ItemInit.ITEM_RUNE);
    }
}
