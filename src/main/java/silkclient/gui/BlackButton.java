package silkclient.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;

public class BlackButton extends GuiButton {
    public BlackButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
         int button = Color.black.getRGB();
         int text = Color.white.getRGB();
        if(this.visible) {
            FontRenderer font = mc.fontRendererObj;
            mc.getTextureManager().bindTexture(buttonTextures);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int i = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
            this.mouseDragged(mc, mouseX, mouseY);
            if(this.hovered) {
                button = new Color(10 , 10,10).getRGB();
                text = 16777120;
            }else {
                button = Color.black.getRGB();
                text = Color.white.getRGB();
            }
            GuiButton.drawRect(this.xPosition , this.yPosition , this.xPosition + width, yPosition + height , button);
            GuiButton.drawCenteredString(font , this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2 , text );
        }
    }
}
