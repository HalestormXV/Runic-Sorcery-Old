package halestormxv.network.packets;

import halestormxv.RunicSorcery;
import halestormxv.capabilities.runecrafting.rcLvl_Provider;
import halestormxv.capabilities.spellcastlevel.SpellCastLvLProvider;
import halestormxv.utils.interfaces.IRuneCraftLevel;
import halestormxv.utils.interfaces.ISpellCastLevel;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SyncSpellCastLvl_PKT implements IMessage
{
    private int scLvL;

    public SyncSpellCastLvl_PKT() { }

    public SyncSpellCastLvl_PKT(int scLvL) {
        this.scLvL = scLvL;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.scLvL = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(scLvL);
    }

    public static class Handler implements IMessageHandler<SyncSpellCastLvl_PKT, IMessage>
    {
        @Override
        public IMessage onMessage(SyncSpellCastLvl_PKT message, MessageContext ctx)
        {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
                EntityPlayer player = RunicSorcery.proxy.getMyPlayer(ctx);
                if (player != null)
                {
                    ISpellCastLevel spellCastLevel = player.getCapability(SpellCastLvLProvider.SPELL_CAST_LEVEL_CAP, null);
                    spellCastLevel.setSpellCastLevel(message.scLvL);
                }
            });
            return null;
        }
    }
}
