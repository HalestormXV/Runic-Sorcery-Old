package halestormxv.network.packets;

import halestormxv.RunicSorcery;
import halestormxv.api.CycleSpells;
import halestormxv.capabilities.rcLvl_Provider;
import halestormxv.utils.interfaces.IRuneCraftLevel;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.nio.charset.Charset;
import java.util.UUID;

public class PacketCycleSpells implements IMessage
{
    private EntityPlayer thePlayer;

    public PacketCycleSpells() { }

    public PacketCycleSpells(EntityPlayer thePlayer)
    {
        this.thePlayer = thePlayer;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        UUID id = UUID.fromString(buf.readCharSequence(buf.readableBytes(), Charset.defaultCharset()).toString());
        try {
            thePlayer = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(id);
        } catch (Throwable e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeCharSequence(thePlayer.getUniqueID().toString(), Charset.defaultCharset());
    }

    public static class Handler implements IMessageHandler<PacketCycleSpells, IMessage>
    {
        @Override
        public IMessage onMessage(PacketCycleSpells message, MessageContext ctx)
        {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketCycleSpells message, MessageContext ctx)
        {
            CycleSpells.cycleSpells(message.thePlayer, message.thePlayer.getActiveHand());
        }
    }
}
