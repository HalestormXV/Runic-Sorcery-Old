package halestormxv.utility.handlers;

import halestormxv.utility.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTableHandler
{
    public static final ResourceLocation CULTIST = LootTableList.register(new ResourceLocation(Reference.MODID, "cultist"));
}
