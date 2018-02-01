package halestormxv.world.gen;

import halestormxv.init.BlockInit;
import halestormxv.objects.blocks.BlockDirts;
import halestormxv.util.handlers.EnumHandlerWood;
import halestormxv.world.biomes.BiomeLupresiumForest;
import halestormxv.world.biomes.BiomeMysticLands;
import halestormxv.world.gen.generators.WorldGenLupresiumTree;
import halestormxv.world.gen.generators.WorldGenMysticTree;
import halestormxv.world.gen.generators.WorldGenStructure;
import net.minecraft.block.Block;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import java.util.*;
import java.util.Arrays;

public class WorldGenCustomStuffs implements IWorldGenerator
{
    private static List<Biome> biomeList = ForgeRegistries.BIOMES.getValues();

    /*###STRUCTURES###*/
    private static final WorldGenStructure CULTIST_HUT = new WorldGenStructure("cultist_hut");

    /*###TREES###*/
    private final WorldGenerator LUPRESIUM_TREE = new WorldGenLupresiumTree();
    private final WorldGenerator MYSTIC_TREE = new WorldGenMysticTree();
    private Block lupresiumDirt = BlockInit.DIRT.getDefaultState().withProperty(BlockDirts.VARIANT, EnumHandlerWood.EnumTypeWood.LUPRESIUM).getBlock();
    private Block mysticDirt = BlockInit.DIRT.getDefaultState().withProperty(BlockDirts.VARIANT, EnumHandlerWood.EnumTypeWood.MYSTIC).getBlock();

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        switch(world.provider.getDimension())
        {
            case -1:
                break;

            case 0:
                generateStructures(CULTIST_HUT, world, random, chunkX, chunkZ, 7, Blocks.GRASS, getBiomeList().toArray(new Class[getBiomeList().size()]));
                generateTrees(LUPRESIUM_TREE, world, random, chunkX, chunkZ, 10, lupresiumDirt, BiomeLupresiumForest.class);
                generateTrees(MYSTIC_TREE, world, random, chunkX, chunkZ, 10, mysticDirt, BiomeMysticLands.class);
                break;

            case 1:
                break;

            case 2:
                break;

        }

    }

    //Greater the Chance the Less Likely it is to Spawn. If chance is 100 then there is a 1 in 100 chance of equaling 0 which will trigger the spawn.
    private void generateStructures(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes)
    {
        ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));

        int x = (chunkX * 16) + random.nextInt(15);
        int z = (chunkZ * 16) + random.nextInt(15);
        int y = calculateGenerationHeight(world, x, z, topBlock);
        BlockPos pos = new BlockPos(x,y,z);
        Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();

        if(world.getWorldType() != WorldType.FLAT)
        {
            if(classesList.contains(biome) || classesList.isEmpty())
            {
                if(random.nextInt(chance) == 0)
                {
                    //Greater the Chance the Less Likely to spawn. If chance = 100 there is a 1 in a 100 chance of it being 0
                    generator.generate(world, random, pos);
                }
            }
        }
    }

    private void generateTrees(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes)
    {
        ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));

        int x = (chunkX * 16) + random.nextInt(15);
        int z = (chunkZ * 16) + random.nextInt(15);
        int y = calculateGenerationHeight(world, x, z, topBlock);
        BlockPos pos = new BlockPos(x,y,z);
        Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();

        if(world.getWorldType() != WorldType.FLAT)
        {
            if(classesList.contains(biome))
            {
                if(random.nextInt(chance) == 0)
                {
                    //Greater the Chance the Less Likely to spawn. If chance = 100 there is a 1 in a 100 chance of it being 0
                    generator.generate(world, random, pos);
                }
            }
        }
    }

    private static int calculateGenerationHeight(World world, int x, int z, Block topBlock)
    {
        int y = world.getHeight();
        boolean foundGround = false;
        while (!foundGround && y-- >= 0)
        {
            Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
            foundGround = block == topBlock;
        }
        return y;
    }


    private static List<?> getBiomeList()
    {
        ArrayList<Class> biomeClassList = new ArrayList<Class>();
        for (Biome biome : biomeList)
        {
            if (biome != null)
            {
                biomeClassList.add(biome.getBiomeClass());
            }
        }
        return biomeClassList;
    }
}
