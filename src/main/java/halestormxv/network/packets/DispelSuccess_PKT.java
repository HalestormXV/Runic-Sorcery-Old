package halestormxv.network.packets;

import halestormxv.RunicSorcery;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class DispelSuccess_PKT implements IMessage
{

    public DispelSuccess_PKT() {
    }


    @Override
    public void fromBytes(ByteBuf buf) { }

    @Override
    public void toBytes(ByteBuf buf) { }

    public static class Handler implements IMessageHandler<DispelSuccess_PKT, IMessage> {

        @Override
        public IMessage onMessage(DispelSuccess_PKT message, MessageContext ctx) {
            RunicSorcery.proxy.playDispelSound();
            return null;
        }

    }
}
