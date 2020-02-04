package com.ci.gui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class GuiButtonDouble extends GuiButton {

    private final ResourceLocation resourceLocation;
    private int offsetX, offsetY, textureOffset;

    public GuiButtonDouble(int buttonId, int offsetX, int offsetY, int x, int y, int width, int height, int textureOffset, ResourceLocation resource) {
        super(buttonId, offsetX, offsetY, width, height, "");
        this.resourceLocation = resource;
        this.textureOffset = textureOffset;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    @Override
    public void drawButton(@Nonnull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            GlStateManager.color(1.0F, 1.0F, 1.0F);
            mc.getTextureManager().bindTexture(this.resourceLocation);
            int i = this.x;
            if (!this.enabled) {
                i += this.textureOffset;
            }else if (this.hovered){
                i += this.textureOffset;
            }
            this.drawTexturedModalRect(this.offsetX, this.offsetY, i, this.y, this.width, this.height);
        }
    }
}
