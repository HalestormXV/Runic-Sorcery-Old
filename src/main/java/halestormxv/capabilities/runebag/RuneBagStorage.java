package halestormxv.capabilities.runebag;

import halestormxv.utils.interfaces.IRuneBagProvider;
import halestormxv.utils.interfaces.IRuneCraftLevel;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class RuneBagStorage implements Capability.IStorage<IRuneBagProvider>
{

    @Override
    public NBTTagCompound writeNBT(Capability<IRuneBagProvider> capability, IRuneBagProvider instance, EnumFacing side)
    {
        return instance.serializeNBT();
    }

    @Override
    public void readNBT(Capability<IRuneBagProvider> capability, IRuneBagProvider instance, EnumFacing side, NBTBase nbt) {
        if (nbt instanceof NBTTagCompound)
            instance.deserializeNBT(((NBTTagCompound) nbt));
    }
}
