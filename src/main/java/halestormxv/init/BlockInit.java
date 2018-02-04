package halestormxv.init;

import halestormxv.objects.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit
{
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    //META BLOCKS
    public static final Block ORE_END = new BlockOres("ore_end", "end");
    public static final Block ORE_OVERWORLD = new BlockOres("ore_overworld", "overworld");
    public static final Block ORE_NETHER = new BlockOres("ore_nether", "nether");

    public static final Block LOGS = new BlockLogs("log");
    public static final Block LEAVES = new BlockLeaf("leaves");
    public static final Block SAPLINGS = new BlockSaplings("sapling");

    public static final Block BLOCK_STONES = new BlockStones("stone");
    public static final Block DIRT = new BlockDirts("dirt");
    public static final Block PLANKS = new BlockPlank("planks");


    //STANDARD BLOCKS
    public static final Block BLOCK_LUPRESIUM_COBBLE = new BlockBase("block_lupresium_cobblestone", Material.ROCK).setHardness(2.6f);
    public static final Block BLOCK_MYSTIC_COBBLE = new BlockBase("block_mystic_cobblestone", Material.ROCK).setHardness(2.6f);
    public static final Block ORE_SIEGRE = new BlockBase("ore_siegre", Material.ROCK).setHardness(5.0f).setLightLevel(5.0f);
    public static final Block BLOCK_SIEGRE = new BlockBase("block_siegre", Material.GLASS).setHardness(2.0f).setLightLevel(1.0f);
    public static final Block BLOCK_PHENET = new BlockBase("block_phenet", Material.IRON).setHardness(3.0f);
    public static final Block ORE_PHENET = new BlockBase("ore_phenet", Material.ROCK).setHardness(4.0f);
}
