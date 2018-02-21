package halestormxv.utils.interfaces;

import net.minecraft.entity.player.EntityPlayer;

import java.util.Optional;

public interface IRuneCraftLevel
{
    public void gainRuneLevel(int runeLevel);

    public void setRuneLevel(int runeLevel);

    public int getRuneLevel();

    public void syncToClient(EntityPlayer entityPlayer);
}