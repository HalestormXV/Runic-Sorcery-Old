package halestormxv.gui;

import halestormxv.capabilities.runebag.RuneBagContainer;
import halestormxv.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;

public class GuiRuneBag extends GuiContainer
{
    private static final ResourceLocation texture = new ResourceLocation(Reference.MODID.toLowerCase(), "textures/gui/runebag.png");

    public GuiRuneBag(InventoryPlayer invPlayer, EnumHand hand, IItemHandlerModifiable invBag)
    {
        super(new RuneBagContainer(invPlayer, hand, invBag));
        this.xSize = 255;
        this.ySize = 230;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GlStateManager.color(1, 1, 1, 1);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        this.drawTexturedModalRect((width - xSize) / 2, (height - ySize) / 2, 0, 0, xSize, ySize);
    }
}

