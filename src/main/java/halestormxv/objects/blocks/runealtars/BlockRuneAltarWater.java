package halestormxv.objects.blocks.runealtars;

import halestormxv.RunicSorcery;
import halestormxv.init.BlockInit;
import halestormxv.init.ItemInit;
import halestormxv.objects.blocks.BlockRuneAltar;
import halestormxv.utility.handlers.SoundsHandler;
import halestormxv.utility.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockRuneAltarWater extends BlockRuneAltar implements IHasModel
{

    public BlockRuneAltarWater(String name, Material material)
    {
        super(name, material);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        double d0 = (double)pos.getX() + 0.2D;
        double d1 = (double)pos.getY() + rand.nextDouble() * 6.0D / 16.0D + 1.3D;
        double d2 = (double)pos.getZ() + 0.2D;
        double d4 = rand.nextDouble() * 0.6D - 0.3D;
        if (rand.nextDouble() < 0.05D) {
            worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, SoundEvent.REGISTRY.getObject
                            (new ResourceLocation("entity.illusion_illager.cast_spell")), SoundCategory.BLOCKS, 0.5F, 1.0F, true);
        }
        worldIn.spawnParticle(EnumParticleTypes.WATER_SPLASH, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
        worldIn.spawnParticle(EnumParticleTypes.WATER_SPLASH, d0 - d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
    }
}
