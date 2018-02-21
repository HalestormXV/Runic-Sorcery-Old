package halestormxv.network.packets;

import halestormxv.RunicSorcery;
import halestormxv.capabilities.rcLvl_Provider;
import halestormxv.utils.interfaces.IRuneCraftLevel;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSyncRCLevel implements IMessage {
    private int rcLvL;

    public PacketSyncRCLevel() {
    }

    public PacketSyncRCLevel(int rcLvL) {
        this.rcLvL = rcLvL;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.rcLvL = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(rcLvL);
    }

    public static class Handler implements IMessageHandler<PacketSyncRCLevel, IMessage>
    {
        @Override
        public IMessage onMessage(PacketSyncRCLevel message, MessageContext ctx)
        {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
                EntityPlayer player = RunicSorcery.proxy.getMyPlayer();
                if (player != null)
                {
                    IRuneCraftLevel runeCraftLevel = player.getCapability(rcLvl_Provider.RUNECRAFT_LEVEL, null);
                    runeCraftLevel.setRuneLevel(message.rcLvL);
                }
                //System.out.println("The Rune Craft Level has been synced to: " + message.rcLvL);
            });
            return null;
        }
    }
}