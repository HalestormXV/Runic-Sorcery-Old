package halestormxv.capabilities.learnedspells;

import com.google.common.primitives.Ints;
import halestormxv.utility.Logging;
import net.minecraft.nbt.*;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
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
        public List<Integer> knownSpells = new ArrayList<>();

        public void learnedSpell(int spellLearned)
        {
            this.knownSpells.add(spellLearned);
        }

        public int[] getSpellList()
        {
            return Ints.toArray(knownSpells);
        }

        @Override
        public void setSpellList(int[] spellList)
        {
            spellList = this.knownSpells.stream().mapToInt(i->i).toArray();
        }
    }
    /***********************************
     * This class is the Storage Class
     * It gives us the Capability Storage
     * like a good little egg.
     **********************************/
    public static class LearnedSpellsStorage implements Capability.IStorage<ILearnedSpells>
    {
        public NBTBase writeNBT(Capability<ILearnedSpells> capability, ILearnedSpells instance, EnumFacing side)
        {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.getIntArray("LearnedSpells");
            nbt.setIntArray("LearnedSpells", instance.getSpellList());
            return nbt;
        }

        @Override
        public void readNBT(Capability<ILearnedSpells> capability, ILearnedSpells instance, EnumFacing side, NBTBase base)
        {
            NBTTagCompound nbt = (NBTTagCompound) base;
            int[] intArray = nbt.getIntArray("LearnedSpells");
            Logging.getLogger().info("Injecting spells into player.");
            for (int anIntArray : intArray) {
                instance.learnedSpell(anIntArray);
            }
            Logging.getLogger().info("Injection completed.");
        }
    }
    /***********************************
     * This class is the Provider Class
     * It gives us the Capability and
     * looks for it
     **********************************/
    public static class LearnedSpellsProvider implements ICapabilitySerializable<NBTBase>
    {
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
        public NBTBase serializeNBT() {
            return LEARNED_SPELLS_CAPABILITY.getStorage().writeNBT(LEARNED_SPELLS_CAPABILITY, this.instance, null);
        }

        @Override
        public void deserializeNBT(NBTBase nbt) {
            LEARNED_SPELLS_CAPABILITY.getStorage().readNBT(LEARNED_SPELLS_CAPABILITY, this.instance, null, nbt);
        }
    }
}


