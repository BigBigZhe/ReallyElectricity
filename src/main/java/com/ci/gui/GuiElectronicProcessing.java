package com.ci.gui;

import com.ci.ModMain;
import com.ci.container.ContainerElectronicProcessing;
import com.ci.gui.button.GuiButtonDouble;
import com.ci.items.BlankCircuitBoard;
import com.ci.loader.ItemsLoader;
import com.ci.loader.NetWorkLoader;
import com.ci.network.MessageLink;
import com.ci.tileentity.TileEntityElectronicProcessing;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.util.Arrays;

@SideOnly(Side.CLIENT)
public class GuiElectronicProcessing extends GuiContainer{

	private static final String TEXTURE_PATH = ModMain.MODID + ":" + "textures/gui/guielectronicprocessing.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);
    private GuiButton buttonLeft, buttonRight, buttonUp, buttonDown;
    private TileEntityElectronicProcessing tile;
    private boolean[] link;

	public GuiElectronicProcessing(InventoryPlayer invPlayer, TileEntityElectronicProcessing tile) {
		super(new ContainerElectronicProcessing(invPlayer, tile));
		this.link = new boolean[]{false, false, false, false};
		this.tile = tile;
		this.xSize = 176;
        this.ySize = 148;
	}

	@Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
		super.actionPerformed(button);
		ItemStack itemStack = tile.getStackInSlot(0);
		Item item = itemStack.getItem();
		if (button.id == 4 && item == ItemsLoader.BLANK_CIRCUIT_BOARD){
            /*BlankCircuitBoard board = (BlankCircuitBoard)item;
            for (int i = 0; i < 4; i++) {
                System.out.println("link["+ i +"] = "+ board.getLink(i));
            }
            System.out.println("linkCount = " + board.getLinkCount());*/
            System.out.println(itemStack.getMetadata());
        }
        if (button == buttonUp){System.out.println("Up");}
        if (button == buttonDown){System.out.println("Down");}
        if (button == buttonLeft){System.out.println("Left");}
        if (button == buttonRight){System.out.println("Right");}
		if (item == ItemsLoader.BLANK_CIRCUIT_BOARD){
            BlankCircuitBoard board = (BlankCircuitBoard)item;
            int meta = itemStack.getMetadata();
            if (meta < 7) {
                boolean b = false;
                if (button == buttonUp && (meta == 0|| meta == 2|| meta == 3|| meta == 5)) {
                    transBoolean(0); b = true;
                }else if (button == buttonDown && (meta == 0|| meta == 1|| meta == 4|| meta == 5)) {
                    transBoolean(2); b = true;
                }else if (button == buttonLeft && (meta == 0|| meta == 3|| meta == 4|| meta == 6)) {
                    transBoolean(1); b = true;
                }else if (button == buttonRight && (meta == 0|| meta == 1|| meta == 2|| meta == 6)) {
                    transBoolean(3); b = true;
                }
                if (b){ checkWhetherLink();}
            }
        }
    }

    @Override
    public void onGuiClosed() {
        if (this.mc.player != null)
        {
            Arrays.fill(link, false);
            this.inventorySlots.onContainerClosed(this.mc.player);
        }
    }

	@Override
    public void initGui() {
        super.initGui();
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        this.buttonList.add(buttonUp = new GuiButtonDouble(0, offsetX + 21 + 115, offsetY + 7, 2, 161, 12, 6, 15, TEXTURE));
        this.buttonList.add(buttonDown = new GuiButtonDouble(1, offsetX + 21 + 115, offsetY + 7 + 48, 2, 161, 12, 6, 15, TEXTURE));
        this.buttonList.add(buttonLeft = new GuiButtonDouble(2, offsetX + 115, offsetY + 7 + 21, 2, 149, 6, 12, 9, TEXTURE));
        this.buttonList.add(buttonRight = new GuiButtonDouble(3, offsetX + 48 + 115, offsetY + 7 + 21, 2, 149, 6, 12, 9, TEXTURE));
        this.buttonList.add(new GuiButton(4, offsetX + 80, offsetY + 10, 15, 15, ""));
    }
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(TEXTURE);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);
        if (tile.isBlank()){
	    	this.drawTexturedModalRect(offsetX + 115, offsetY + 7, 176, 0, 54, 54);
			this.drawTexturedModalRect(offsetX + 21 + 115, offsetY + 7, 176 + 6, 54, 12, 6);
            this.drawTexturedModalRect(offsetX + 21 + 115, offsetY + 7 + 48, 176 + 6, 54, 12, 6);
            this.drawTexturedModalRect(offsetX + 115, offsetY + 7 + 21, 176, 54, 6, 12);
            this.drawTexturedModalRect(offsetX + 48 + 115, offsetY + 7 + 21, 176, 54, 6, 12);
            int meta = tile.getStackInSlot(0).getMetadata();
            if (meta > 0 && meta < 7){ drawTexture(meta); }else
            if (meta == 7){ drawTexture(1); drawTexture(3);}else
            if (meta == 8){ drawTexture(2); drawTexture(4);}else
            if (meta == 9){ drawTexture(5); drawTexture(6);}
        }
	}

	private void drawTexture(int num){
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        if (num == 1){this.drawTexturedModalRect(offsetX + 116, offsetY + 8, 176, 97, 31, 31);}
        if (num == 2){this.drawTexturedModalRect(offsetX + 116, offsetY + 8 + 21, 207, 66, 31, 31);}
        if (num == 3){this.drawTexturedModalRect(offsetX + 116 + 21, offsetY + 8 + 21, 176, 66, 31, 31);}
        if (num == 4){this.drawTexturedModalRect(offsetX + 116 + 21, offsetY + 8, 207, 97, 31, 31);}
        if (num == 5){this.drawTexturedModalRect(offsetX + 115 + 6, offsetY + 7 + 21, 194, 54, 42, 12);}
        if (num == 6){this.drawTexturedModalRect(offsetX + 115 + 21, offsetY + 7 + 6, 230, 12, 12, 42);}
    }

    private void transBoolean(int num){
	    if (link[num]){link[num] = false;}
	    link[num] = true;
    }

    private void checkWhetherLink(){
	    for (int i = 0; i < 3; i++){
	        if (link[i]){
	            for (int j = i + 1; j < 4; j++){
	                if (link[j]){ linkTwo(i, j); }
	            }
            }
        }
    }

    private void linkTwo(int i, int j){
	    ItemStack itemStack = tile.getStackInSlot(0);
	    int meta = itemStack.getMetadata();
	    int mirror = meta;
	    if (i == 0){
	        if (j == 1){
	            if (meta == 0){meta = 1;} else {meta = 7;}
            }else if (j == 2){
                if (meta == 0){meta = 6;} else {meta = 9;}
            }else if (j == 3){
                if (meta == 0){meta = 4;} else {meta = 8;}
            }
        }else if (i ==1){
	        if (j == 2){
                if (meta == 0){meta = 2;} else {meta = 8;}
            }else if (j == 3){
                if (meta == 0){meta = 5;} else {meta = 9;}
            }
        }else if (i == 2){
	        if (j == 3){
                if (meta == 0){meta = 3;} else {meta = 7;}
            }
        }
	    if (mirror != meta) {
	        Arrays.fill(link, false);
            itemStack.setItemDamage(meta);
            MessageLink message = new MessageLink(tile.getPos(), meta);
            NetWorkLoader.instance.sendToServer(message);
        }
    }

}
