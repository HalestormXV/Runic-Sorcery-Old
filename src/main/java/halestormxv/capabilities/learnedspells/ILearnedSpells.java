package halestormxv.capabilities.learnedspells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import java.util.List;

public interface ILearnedSpells extends INBTSerializable<NBTTagCompound>
{
    void learnedSpell(int spellLearned);

    int[] getSpellList();

    void setSpellList(int[] spellList);

}
