package halestormxv.utility.handlers;

import halestormxv.KeyBindings;
import halestormxv.capabilities.runecrafting.rcLvl_Provider;
import halestormxv.network.PacketHandler;
import halestormxv.network.packets.CycleSpells_PKT;
import halestormxv.network.packets.FetchRunecraftLvl_PKT;
import halestormxv.network.packets.SendKey_PKT;
import halestormxv.capabilities.runecrafting.IRuneCraftLevel;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class InputHandler
{

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
        if (KeyBindings.runeBag.isPressed())
        {
            // Someone pressed our tutorialKey. We send a message
            RayTraceResult mouseOver = Minecraft.getMinecraft().objectMouseOver;
            BlockPos blockPos = mouseOver.getBlockPos();
            PacketHandler.INSTANCE.sendToServer(new SendKey_PKT(blockPos));
        }

        if(KeyBindings.getRuneCraftLevel.isPressed())
        {
            EntityPlayer player = Minecraft.getMinecraft().player;
            IRuneCraftLevel iRuneCraftLevel = player.getCapability(rcLvl_Provider.RUNECRAFT_LEVEL, null);
            PacketHandler.INSTANCE.sendToServer(new FetchRunecraftLvl_PKT());
        }

        if(KeyBindings.cycleMagicSpells.isPressed())
        {
            EntityPlayer player = Minecraft.getMinecraft().player;
            PacketHandler.INSTANCE.sendToServer(new CycleSpells_PKT(player));
        }
    }
}
