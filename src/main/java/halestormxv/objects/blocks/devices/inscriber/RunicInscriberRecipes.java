package halestormxv.objects.blocks.devices.inscriber;

import java.util.Map;
import java.util.Map.Entry;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import halestormxv.init.BlockInit;
import halestormxv.init.ItemInit;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class RunicInscriberRecipes
{
    private static final RunicInscriberRecipes INSTANCE = new RunicInscriberRecipes();
    private final Table<ItemStack, ItemStack, ItemStack> smeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
    private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

    public static RunicInscriberRecipes getInstance()
    {
        return INSTANCE;
    }

    private RunicInscriberRecipes()
    {
        addInscriberRecipe(new ItemStack(ItemInit.DUST_SIEGRE), new ItemStack(BlockInit.ORE_OVERWORLD), new ItemStack(ItemInit.ITEM_RUNE), 5.0F);
        addInscriberRecipe(new ItemStack(Blocks.ACACIA_FENCE), new ItemStack(Blocks.ACACIA_FENCE_GATE), new ItemStack(BlockInit.RUNIC_INSCRIBER), 5.0F);
    }


    public void addInscriberRecipe(ItemStack input2, ItemStack input1, ItemStack result, float experience)
    {
        if(getInscriberResult(input2, input1) != ItemStack.EMPTY) return;
        this.smeltingList.put(input2, input1, result);
        this.experienceList.put(result, Float.valueOf(experience));
    }

    public ItemStack getInscriberResult(ItemStack input1, ItemStack input2)
    {
        for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.smeltingList.columnMap().entrySet())
        {
            if(this.compareItemStacks(input1, (ItemStack)entry.getKey()))
            {
                for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())
                {
                    if(this.compareItemStacks(input2, (ItemStack)ent.getKey()))
                    {
                        return (ItemStack)ent.getValue();
                    }
                }
            }
        }
        return ItemStack.EMPTY;
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList()
    {
        return this.smeltingList;
    }

    public float getInscriberExperience(ItemStack stack)
    {
        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet())
        {
            if(this.compareItemStacks(stack, (ItemStack)entry.getKey()))
            {
                return ((Float)entry.getValue()).floatValue();
            }
        }
        return 0.0F;
    }
}