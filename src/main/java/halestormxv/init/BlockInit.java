package halestormxv.init;

import halestormxv.objects.blocks.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit
{
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block BLOCK_PHENET = new BlockBase("block_phenet", Material.IRON).setHardness(3.0f);
    public static final Block ORE_PHENET = new BlockBase("ore_phenet", Material.ROCK).setHardness(4.0f);
}
