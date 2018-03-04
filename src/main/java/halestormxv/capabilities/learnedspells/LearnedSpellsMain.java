package halestormxv.capabilities.learnedspells;

import halestormxv.utility.Reference;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.*;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class LearnedSpellsMain
{
    /***********************************
     * This class is the Functions Class
     * The actions take place in this
     * code segment.
     **********************************/
    public static class LearnedSpellsFunctions implements ILearnedSpells
    {
        private List<Integer> knownSpells = new ArrayList<>();

        public void learnedSpell(int spellLearned)
        {
            this.knownSpells.add(spellLearned);
        }

        private int[] convert2Primative(List<Integer> integers)
        {
            int[] ret = new int[integers.size()];
            Iterator<Integer> iterator = integers.iterator();
            for (int i = 0; i < ret.length; i++) {
                ret[i] = iterator.next();
            }
            return ret;
        }

        private NBTTagCompound writeNBT(List<Integer> knownSpells)
        {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setIntArray("LearnedSpells", convert2Primative(knownSpells));
            return nbt;
        }

        @Override
        public NBTTagCompound serializeNBT()
        {
            return writeNBT(this.knownSpells);
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt)
        {
            if (nbt.hasKey("LearnedSpells"))
            {
                nbt.getIntArray("LearnedSpells");
            }
        }

        @Override
        public void sync(EntityPlayerMP entityPlayer) {

        }
    }
    /***********************************
     * This class is the Storage Class
     * It gives us the Capability Storage
     * like a good little egg.
     **********************************/
    public static class LearnedSpellsStorage implements Capability.IStorage<ILearnedSpells> {

        @Override
        public NBTTagCompound writeNBT(Capability<ILearnedSpells> capability, ILearnedSpells instance, EnumFacing side) {
            return instance.serializeNBT();
        }

        @Override
        public void readNBT(Capability<ILearnedSpells> capability, ILearnedSpells instance, EnumFacing side, NBTBase nbt) {
            if (nbt instanceof NBTTagCompound)
                instance.deserializeNBT(((NBTTagCompound) nbt));
        }
    }
    /***********************************
     * This class is the Provider Class
     * It gives us the Capability and
     * looks for it
     **********************************/
    public static class LearnedSpellsProvider implements ICapabilitySerializable<NBTTagCompound> {
        @CapabilityInject(ILearnedSpells.class)
        public static final Capability<ILearnedSpells> LEARNED_SPELLS_CAPABILITY = null;

        private ILearnedSpells instance = LEARNED_SPELLS_CAPABILITY.getDefaultInstance();

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return capability == LEARNED_SPELLS_CAPABILITY;
        }

        @Nullable
        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            return capability == LEARNED_SPELLS_CAPABILITY ? LEARNED_SPELLS_CAPABILITY.<T>cast(this.instance) : null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            return null;
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {

        }
    }
    /***********************************
     * This class is the Handler Class
     * It gives us out interactions
     * and getters and setters like a
     * good little egg.
     **********************************/
    public static class LearnedSpellsHandler implements ICapabilitySerializable<NBTBase> {
        @CapabilityInject(ILearnedSpells.class)
        public final Capability<ILearnedSpells> LEARNED_SPELLS_CAP = null;

        private ILearnedSpells instance = LEARNED_SPELLS_CAP.getDefaultInstance();

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return capability == LEARNED_SPELLS_CAP;
        }

        @Nullable
        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            return capability == LEARNED_SPELLS_CAP ? LEARNED_SPELLS_CAP.<T>cast(this.instance) : null;
        }

        @Override
        public NBTBase serializeNBT() {
            return LEARNED_SPELLS_CAP.getStorage().writeNBT(LEARNED_SPELLS_CAP, this.instance, null);
        }

        @Override
        public void deserializeNBT(NBTBase nbt) {
            LEARNED_SPELLS_CAP.getStorage().readNBT(LEARNED_SPELLS_CAP, this.instance, null, nbt);
        }
    }
}


