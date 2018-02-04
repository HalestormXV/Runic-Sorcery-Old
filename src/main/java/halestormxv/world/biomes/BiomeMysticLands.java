package halestormxv.world.biomes;

import halestormxv.entity.EntityCultist;
import halestormxv.init.BlockInit;
import halestormxv.objects.blocks.BlockDirts;
import halestormxv.objects.blocks.BlockStones;
import halestormxv.util.handlers.EnumHandlerStone;
import halestormxv.util.handlers.EnumHandlerWood;
import halestormxv.world.gen.generators.WorldGenLupresiumTree;
import halestormxv.world.gen.generators.WorldGenMysticTree;
import net.minecraft.entity.monster.*;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeMysticLands extends Biome
{
    protected static final WorldGenAbstractTree TREE = new WorldGenMysticTree();

    public BiomeMysticLands()
    {
        super(new BiomeProperties("Mystic Lands").setBaseHeight(0.2f).setHeightVariation(0.8f).setTemperature(0.06f).setWaterColor(12013822).setSnowEnabled());
        topBlock = BlockInit.DIRT.getDefaultState().withProperty(BlockDirts.VARIANT, EnumHandlerWood.EnumTypeWood.MYSTIC);
        fillerBlock = BlockInit.BLOCK_STONES.getDefaultState().withProperty(BlockStones.VARIANT, EnumHandlerStone.EnumTypeStone.MYSTIC_SMOOTHSTONE);

        //this.decorator.coalGen = new WorldGenMinable(Blocks.COAL_BLOCK.getDefaultState(), 4);
        this.decorator.generateFalls = true;
        this.decorator.treesPerChunk = 2;
        this.decorator.bigMushroomsPerChunk = 1;

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        this.spawnableMonsterList.add(new SpawnListEntry(EntityCultist.class, 6, 1, 3));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityZombie.class, 10, 2, 6));
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 4, 2, 8));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityMagmaCube.class, 2, 1, 2));

    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return TREE;
    }
}
