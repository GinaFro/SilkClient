package silkclient.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MathHelper;
import silkclient.utils.ShapeUtils;

import java.awt.*;

public class ColorSlider extends GuiButton {

    public float sliderValue = 0.0F;
    public boolean dragging;
    public boolean clicked;

    public ColorSlider(int buttonId, int x, int y, int widthIn) {
        super(buttonId, x, y, widthIn, 5, "");
        this.sliderValue = 0.0F;
        Minecraft minecraft = Minecraft.getMinecraft();
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if(this.visible) {
            FontRenderer fontrenderer = mc.fontRendererObj;
            mc.getTextureManager().bindTexture(buttonTextures);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
            ShapeUtils.drawRoundedRect(xPosition , yPosition, xPosition + width , yPosition+height , 3f, Color.black.getRGB());
            if(this.dragging) {
                ShapeUtils.drawRoundedRect(mouseX - 5, yPosition - 5, mouseX + 5, yPosition + height + 5, 5f,Color.black.getRGB() );
            }else {
                ShapeUtils.drawRoundedRect(xPosition + (int)(width * sliderValue) - 5, yPosition - 5, xPosition + (int)(width * sliderValue) + 5, yPosition + height + 5 , 5f , Color.black.getRGB());
            }

        }
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        if (super.mousePressed(mc, mouseX, mouseY))
        {
            this.sliderValue = (float)(mouseX - (this.xPosition + 4)) / (float)(this.width - 8);
            this.sliderValue = MathHelper.clamp_float(this.sliderValue, 0.0F, 1.0F);
            this.dragging = true;
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
        if(!clicked) return;

        if(mouseX >= xPosition && mouseX <= xPosition+width) {
            if(mouseY >= yPosition && mouseY <= yPosition+height) {
                this.dragging = true;
            }else {
                this.dragging = false;
            }
        }else {
            this.dragging = false;
        }
        if(!this.dragging){
            sliderValue = 0.0F;
            return;
        }

        this.sliderValue = (float)(mouseX - (this.xPosition + 4)) / (float)(this.width - 8);
        this.sliderValue = MathHelper.clamp_float(this.sliderValue, 0.0F, 1.0F);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        this.dragging = false;
    }
}
