package halestormxv.init;

import halestormxv.util.Logging;
import halestormxv.world.biomes.BiomeLupresiumForest;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BiomeInit
{
    public static final Biome LUPRESIUM_FOREST = new BiomeLupresiumForest();

    public static void registerBiomes()
    {
        initBiome(LUPRESIUM_FOREST, 12,"Lupresium Touched", BiomeType.WARM, Type.HILLS, Type.MAGICAL, Type.DRY);
    }

    private static Biome initBiome(Biome biome, int weight, String name, BiomeType biomeType, Type... types)
    {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        Logging.getLogger().info("Oh boy, the biomes have been registered!");
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addBiome(biomeType, new BiomeEntry(biome, weight));
        BiomeManager.addSpawnBiome(biome);
        Logging.getLogger().info("Awww snap! Now they were added. Locked and loaded.");
        return biome;
    }
}
