package halestormxv.capabilities.learnedspells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface ILearnedSpells
{
    void learnedSpell(int spellLearned);

    int[] getSpellList();

    void setSpellList(int[] spellIDs);

    EntityPlayer getMyPlayer(EntityPlayer player);
    void syncToClient(NBTTagCompound nbt, EntityPlayer entityPlayer);
}
