package halestormxv.network;

import halestormxv.network.packets.PacketChatUtils;
import halestormxv.network.packets.PacketFetchRunecraftLevel;
import halestormxv.network.packets.PacketSendKey;
import halestormxv.network.packets.PacketSyncRCLevel;
import halestormxv.utils.Reference;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler
{
    private static int packetId = 0;
    public static final ThreadedNetworkWrapper INSTANCE = new ThreadedNetworkWrapper(Reference.NETWORK_CHANNEL);

    public PacketHandler() {
    }

    public static int nextID() {
        return packetId++;
    }

    public static void setupMessages()
    {
        registerMessages();
    }

    public static void registerMessages()
    {
        // Register messages which are sent from the client to the server here:
        INSTANCE.registerMessage(PacketSendKey.Handler.class, PacketSendKey.class, nextID(), Side.SERVER);
        INSTANCE.registerMessage(PacketFetchRunecraftLevel.Handler.class, PacketFetchRunecraftLevel.class, nextID(), Side.SERVER);
        INSTANCE.registerMessage(PacketChatUtils.PacketNoSpamChat.Handler.class, PacketChatUtils.PacketNoSpamChat.class, nextID(), Side.CLIENT);
        INSTANCE.registerMessage(PacketSyncRCLevel.Handler.class, PacketSyncRCLevel.class, nextID(), Side.CLIENT);
    }

    public static void sendToAllAround(IMessage message, TileEntity te, int range)
    {
        BlockPos p = te.getPos();
        INSTANCE.sendToAllAround(message, new NetworkRegistry.TargetPoint(te.getWorld().provider.getDimension(), p.getX(), p.getY(), p.getZ(), range));
    }

    public static void sendToAll(IMessage message)
    {
        INSTANCE.sendToAll(message);
    }

    public static void sendTo(IMessage message, EntityPlayerMP player) { INSTANCE.sendTo(message, player); }

    //Only use if you plan on sending the pack to the server. Has nothing to do with the player. There is only one server.
    public static void sendToServer(IMessage message)
    {
        INSTANCE.sendToServer(message);
    }
}
