package halestormxv.capabilities.learnedspells;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface ILearnedSpells //extends INBTSerializable<NBTTagCompound>
{
    void learnedSpell(int spellLearned);

    int[] getSpellList();

    void setSpellList(int[] spellIDs);

}
