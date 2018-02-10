package halestormxv.util.handlers;

import halestormxv.KeyBindings;
import halestormxv.network.PacketHandler;
import halestormxv.network.packets.PacketSendKey;
import net.minecraft.client.Minecraft;
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
            PacketHandler.INSTANCE.sendToServer(new PacketSendKey(blockPos));
        }
    }
}
