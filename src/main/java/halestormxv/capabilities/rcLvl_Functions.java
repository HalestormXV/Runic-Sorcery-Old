package halestormxv.capabilities;

import halestormxv.network.PacketHandler;
import halestormxv.network.packets.PacketSyncRCLevel;
import halestormxv.utils.interfaces.IRuneCraftLevel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.MathHelper;

public class rcLvl_Functions implements IRuneCraftLevel
{
    private int runeLevel = 0;
    private int min_runeLevel = 0;
    private int max_runeLevel = 100;
    private EntityPlayer entityPlayer;


    @Override
    public void gainRuneLevel(int value)
    {
        int clampValue = this.runeLevel - value;
        this.runeLevel = MathHelper.clamp(clampValue, this.min_runeLevel, this.max_runeLevel);
    }

    @Override
    public void setRuneLevel(int value)
    {
        this.runeLevel = value;
    }

    @Override
    public int getRuneLevel() { return this.runeLevel; }

    @Override
    public void syncToClient(EntityPlayer thePlayer)
    {
        if (!thePlayer.world.isRemote)
        {
            PacketHandler.sendTo(new PacketSyncRCLevel(this.runeLevel), (EntityPlayerMP) thePlayer);
        }
    }
}
