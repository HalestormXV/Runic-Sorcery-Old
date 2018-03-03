package halestormxv.capabilities.learnedspells;

import halestormxv.utility.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.*;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;


public class LearnedSpellsMain
{
    /***********************************
     * This class is the Functions Class
     * The actions take place in this
     * code segment.
     **********************************/
    public class LearnedSpellsFunctions implements ILearnedSpells
    {
        private List<Integer> spellIDs = Arrays.asList();
        private int  SPELL_ID = 0;

        public void learnedSpell(EntityPlayer thePlayer, int spellLearned)
        {
            this.SPELL_ID = spellLearned;
            //writeNBT(this.SPELL_ID);
        }

        private NBTTagCompound writeNBT()
        {

            return null;
        }

        @Override
        public NBTTagCompound serializeNBT()
        {
            return writeNBT();
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt)
        {

        }

        @Override
        public void sync(EntityPlayerMP entityPlayer) {

        }
    }

    /***********************************
     * This class is the Provider Class
     * It gives us the Capability and
     * looks for it
     **********************************/
    public class LearnedSpellsProvider implements ICapabilitySerializable<NBTTagCompound>
    {
        @CapabilityInject(ILearnedSpells.class)
        public final Capability<ILearnedSpells> LEARNED_SPELLS_CAP = null;
        public ILearnedSpells instance = LEARNED_SPELLS_CAP.getDefaultInstance();
        public final ResourceLocation NAME = new ResourceLocation(Reference.MODID, "learned_spells");
        private final ILearnedSpells cap = new LearnedSpellsFunctions();

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return capability == LEARNED_SPELLS_CAP;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            if (capability == LEARNED_SPELLS_CAP) {
                return LEARNED_SPELLS_CAP.cast(cap);
            }

            return null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            return cap.serializeNBT();
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            cap.deserializeNBT(nbt);
        }
    }

    /***********************************
     * This class is the Storage Class
     * It gives us the Capability Storage
     * like a good little egg.
     **********************************/
    public class LearnedSpellsStorage implements Capability.IStorage<ILearnedSpells>
    {

        @Override
        public NBTTagCompound writeNBT(Capability<ILearnedSpells> capability, ILearnedSpells instance, EnumFacing side)
        {
            return instance.serializeNBT();
        }

        @Override
        public void readNBT(Capability<ILearnedSpells> capability, ILearnedSpells instance, EnumFacing side, NBTBase nbt)
        {
            if (nbt instanceof NBTTagIntArray)
                instance.deserializeNBT(((NBTTagCompound) nbt));
        }
    }

    /***********************************
     * This class is the Handler Class
     * It gives us out interactions
     * and getters and setters like a
     * good little egg.
     **********************************/
    public class LearnedSpellsHandler implements ICapabilitySerializable<NBTBase>
    {
        @CapabilityInject(ILearnedSpells.class)
        public  final Capability<ILearnedSpells> LEARNED_SPELLS_CAP = null;

        private ILearnedSpells instance = LEARNED_SPELLS_CAP.getDefaultInstance();

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
        {
            return capability == LEARNED_SPELLS_CAP;
        }

        @Nullable
        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            return capability == LEARNED_SPELLS_CAP ? LEARNED_SPELLS_CAP.<T> cast(this.instance) : null;
        }

        @Override
        public NBTBase serializeNBT()
        {
            return LEARNED_SPELLS_CAP.getStorage().writeNBT(LEARNED_SPELLS_CAP, this.instance, null);
        }

        @Override
        public void deserializeNBT(NBTBase nbt)
        {
            LEARNED_SPELLS_CAP.getStorage().readNBT(LEARNED_SPELLS_CAP, this.instance, null, nbt);
        }
    }
}


