package halestormxv.objects.fluids;

import halestormxv.RunicSorcery;
import halestormxv.utils.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

@SuppressWarnings("WeakerAccess")
public class ModFluids
{
    /**
     * The fluids registered by this mod. Includes fluids that were already registered by another mod.
     */
    public static final Set<Fluid> FLUIDS = new HashSet<>();

    /**
     * The fluid blocks from this mod only. Doesn't include blocks for fluids that were already registered by another mod.
     */
    public static final Set<IFluidBlock> MOD_FLUID_BLOCKS = new HashSet<>();

    public static final Fluid STATIC = createFluid("static", false,
            fluid -> fluid.setLuminosity(10).setDensity(800).setViscosity(1500),
            fluid -> new NonFlowFluid(fluid, new MaterialLiquid(MapColor.BROWN)));

    public static final Fluid STATIC_GAS = createFluid("static_gas", false,
            fluid -> fluid.setLuminosity(10).setDensity(-800).setViscosity(1500).setGaseous(true),
            fluid -> new NonFlowFluid(fluid, new MaterialLiquid(MapColor.BROWN)));

    public static final Fluid NORMAL = createFluid("normal", true,
            fluid -> fluid.setLuminosity(10).setDensity(1600).setViscosity(100),
            fluid -> new BlockFluidClassic(fluid, new MaterialLiquid(MapColor.ADOBE)));

    public static final Fluid NORMAL_GAS = createFluid("normal_gas", true,
            fluid -> fluid.setLuminosity(10).setDensity(-1600).setViscosity(100).setGaseous(true),
            fluid -> new BlockFluidClassic(fluid, new MaterialLiquid(MapColor.ADOBE)));

    public static final Fluid FINITE = createFluid("finite", false,
            fluid -> fluid.setLuminosity(10).setDensity(800).setViscosity(1500),
            fluid -> new BlockFluidFinite(fluid, new MaterialLiquid(MapColor.BLACK)));

    public static final Fluid PORTAL_DISPLACEMENT = createFluid("portal_displacement", true,
            fluid -> fluid.setLuminosity(10).setDensity(1600).setViscosity(100),
            fluid -> new PortalDisplacement(fluid, new MaterialLiquid(MapColor.DIAMOND)));

    /**
     * Create a {@link Fluid} and its {@link IFluidBlock}, or use the existing ones if a fluid has already been registered with the same name.
     *
     * @param name                 The name of the fluid
     * @param hasFlowIcon          Does the fluid have a flow icon?
     * @param fluidPropertyApplier A function that sets the properties of the {@link Fluid}
     * @param blockFactory         A function that creates the {@link IFluidBlock}
     * @return The fluid and block
     */
    private static <T extends Block & IFluidBlock> Fluid createFluid(String name, boolean hasFlowIcon, Consumer<Fluid> fluidPropertyApplier, Function<Fluid, T> blockFactory) {
        final String texturePrefix = Reference.RESOURCE_PREFIX + "blocks/fluid_";

        final ResourceLocation still = new ResourceLocation(texturePrefix + name + "_still");
        final ResourceLocation flowing = hasFlowIcon ? new ResourceLocation(texturePrefix + name + "_flow") : still;

        Fluid fluid = new Fluid(name, still, flowing);
        final boolean useOwnFluid = FluidRegistry.registerFluid(fluid);

        if (useOwnFluid) {
            fluidPropertyApplier.accept(fluid);
            MOD_FLUID_BLOCKS.add(blockFactory.apply(fluid));
        } else {
            fluid = FluidRegistry.getFluid(name);
        }

        FLUIDS.add(fluid);

        return fluid;
    }

    @Mod.EventBusSubscriber
    public static class RegistrationHandler
    {

        /**
         * Register this mod's fluid {@link Block}s.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event)
        {
            final IForgeRegistry<Block> registry = event.getRegistry();

            for (final IFluidBlock fluidBlock : MOD_FLUID_BLOCKS)
            {
                final Block block = (Block) fluidBlock;
                block.setRegistryName(Reference.MODID, "fluid." + fluidBlock.getFluid().getName());
                block.setUnlocalizedName(Reference.RESOURCE_PREFIX + fluidBlock.getFluid().getUnlocalizedName());
                block.setCreativeTab(RunicSorcery.RUNICSORCERY);
                registry.register(block);
            }
        }

        /**
         * Register this mod's fluid {@link ItemBlock}s.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event)
        {
            final IForgeRegistry<Item> registry = event.getRegistry();

            for (final IFluidBlock fluidBlock : MOD_FLUID_BLOCKS) {
                final Block block = (Block) fluidBlock;
                final ItemBlock itemBlock = new ItemBlock(block);
                itemBlock.setRegistryName(block.getRegistryName());
                registry.register(itemBlock);
            }
        }
    }

    private static void registerBucket(Fluid fluid) {
        FluidRegistry.addBucketForFluid(fluid);
    }

}
