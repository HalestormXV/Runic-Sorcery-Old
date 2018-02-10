package halestormxv.gui.handlers;

import halestormxv.gui.GuiRunicInscriber;
import halestormxv.objects.blocks.devices.inscriber.ContainerRunicInscriber;
import halestormxv.objects.blocks.devices.inscriber.TileEntityRunicInscriber;
import halestormxv.utils.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class RunicInscriberGuiHandler implements IGuiHandler
{
    public static final int RUNIC_INSCRIBER_GUI = Reference.GUI_RUNIC_FURNACE;

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == RUNIC_INSCRIBER_GUI)
            return new ContainerRunicInscriber(player.inventory, ((TileEntityRunicInscriber)world.getTileEntity(new BlockPos(x, y, z))));
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == RUNIC_INSCRIBER_GUI)
            return new GuiRunicInscriber(player.inventory, ((TileEntityRunicInscriber)world.getTileEntity(new BlockPos(x, y, z))));
        return null;
    }
}

