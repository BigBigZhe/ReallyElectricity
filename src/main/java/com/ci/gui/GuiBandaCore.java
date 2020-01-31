package com.ci.gui;

import java.io.IOException;

import com.ci.ModMain;
import com.ci.container.ContainerBandaCore;

import com.ci.loader.NetWorkLoader;
import com.ci.network.MessageMain;
import com.ci.tileentity.TileEntityBandaCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

@SideOnly(Side.CLIENT)
public class GuiBandaCore extends GuiContainer{

	private static final String TEXTURE_PATH = ModMain.MODID + ":" + "textures/gui/guibanda.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);
	private TileEntityBandaCore tileEntity;
	
	public GuiBandaCore(InventoryPlayer invPlayer, TileEntityBandaCore tileInventory) {
		super(new ContainerBandaCore(invPlayer, tileInventory));
		this.xSize = 176;
        this.ySize = 133;
        this.tileEntity = tileInventory;
	}

	@Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
		super.actionPerformed(button);
        BlockPos pos = this.tileEntity.getPos();
        MessageMain message = new MessageMain(pos, 10);
        NetWorkLoader.instance.sendToServer(message);
        tileEntity.addCount(10);
        tileEntity.removeStackFromSlot(3);
        System.out.print(tileEntity.getCount());
		/*if (tileEntity.isEmpty(3)){
		    return;
        }else if (tileEntity.getStackInSlot(3).getDisplayName().startsWith("money")){*/
            /*int sum =tileEntity. getStackInSlot(3).getCount() * ((Money)tileEntity.getStackInSlot(3).getItem()).getValue();
            tileEntity.addCount(sum);
            tileEntity.removeStackFromSlot(3);*/

       /*}*/
        /*tileEntity.addCount(1);
        tileEntity.removeStackFromSlot(3);
        tileEntity.syncToTrackingClients();
        System.out.print(this.tileEntity.getCount());*/
    }
	
	@Override
    public void initGui()
    {
        super.initGui();
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        this.buttonList.add(new GuiButton(0, offsetX + 153, offsetY + 17, 15, 10, "")
        {
            public void drawButton(Minecraft mc, int mouseX, int mouseY)
            {
                if (this.visible)
                {
                    GlStateManager.color(1.0F, 1.0F, 1.0F);

                    mc.getTextureManager().bindTexture(TEXTURE);
                    int x = mouseX - this.x, y = mouseY - this.y;

                    if (x >= 0 && y >= 0 && x < this.width && y < this.height)
                    {
                        this.drawTexturedModalRect(this.x, this.y, 1, 146, this.width, this.height);
                    }
                    else
                    {
                        this.drawTexturedModalRect(this.x, this.y, 1, 134, this.width, this.height);
                    }
                }
            }
        });
        this.buttonList.add(new GuiButton(1, offsetX + 153, offsetY + 29, 15, 10, "")
        {
            public void drawButton(Minecraft mc, int mouseX, int mouseY)
            {
                if (this.visible)
                {
                    GlStateManager.color(1.0F, 1.0F, 1.0F);

                    mc.getTextureManager().bindTexture(TEXTURE);
                    int x = mouseX - this.x, y = mouseY - this.y;

                    if (x >= 0 && y >= 0 && x < this.width && y < this.height)
                    {
                        this.drawTexturedModalRect(this.x, this.y, 20, 146, this.width, this.height);
                    }
                    else
                    {
                        this.drawTexturedModalRect(this.x, this.y, 20, 134, this.width, this.height);
                    }
                }
            }
        });
    }
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(TEXTURE);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;

        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);
	}

}
