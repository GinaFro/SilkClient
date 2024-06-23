package silkclient.gui.cosmetic;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumChatFormatting;
import net.optifine.player.CapeUtils;
import org.lwjgl.opengl.GL11;
import silkclient.cosmetics.CapeController;
import silkclient.gui.Buttons;
import silkclient.utils.RenderUtils;

import java.awt.*;
import java.io.IOException;
import java.util.Collections;

public class CapeSwitcherGUI extends GuiScreen {

    GuiButton prev =  new Buttons(0, this.width / 2 - 80, this.height / 2 - 10, 14, 20, true, "<");
    GuiButton next =  new Buttons(1, this.width / 2 + 65, this.height / 2 - 10, 14, 20, true, ">");

    @Override
    public void initGui() {
        this.buttonList.clear();
        prev.visible = true;
        next.visible = true;
        prev.enabled = true;
        next.enabled = true;
        this.buttonList.add(prev);
        this.buttonList.add(next);
    }


    private Color nextColor = Color.black;
    private Color prevColor = Color.black;
    private boolean Prevhovered;
    private boolean nexthovered;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawBackground(0);
        Gui.drawRect(0, 50, super.width, super.height - 50, Integer.MIN_VALUE);
        this.drawGradientRect(0, 50, super.width, 58, Integer.MIN_VALUE, 0);
        this.drawGradientRect(0, super.height - 58, super.width, super.height - 50, 0, Integer.MIN_VALUE);
        this.drawGradientRect(0,50,this.width,this.height - 50,0,Integer.MIN_VALUE);
        this.drawGradientRect(0, this.height - 58, this.width, this.height - 50, 0, Integer.MIN_VALUE);
        GL11.glPushMatrix();
        GL11.glScalef(1.5F, 1.5F, 1.5F);
        this.drawCenteredString(this.fontRendererObj, "Cape Switcher", (int) ((float) this.width / 1.5F) / 2, 13, -1);
        GL11.glPopMatrix();
        GlStateManager.scale(1.0F,1.0F,1.0F);
        GlStateManager.color(1.0F,1.0F,1.0F,1.0F);
        this.drawButton(this.width / 2 - 50 , this.height / 2 + (this.height / 4), ">" , nextColor, mouseX,mouseY);
        this.drawButton(this.width / 2 - 50 , this.height / 2 + (this.height / 4) + 20, "<" , prevColor, mouseX,mouseY);
        GL11.glPushMatrix();
        GL11.glScalef(1.5F, 1.5F, 1.5F);
        Gui.drawCenteredString(this.fontRendererObj, "Rejoin to notice changes", (int) ((float) this.width / 1.5F) / 2, 30, -1);
        GL11.glPopMatrix();

        int i = this.fontRendererObj.getStringWidth(CapeController.getCurrentCape().name());

        this.drawHoveringText(Collections.singletonList(CapeController.getCurrentCape().name().replace("_" , " ")) , this.width / 2 - i, this.height / 2 + 65);
        GlStateManager.scale(1.0F,1.0F,1.0F);
        RenderUtils.renderPlayerOnScreen(this.width / 2, this.height / 2 + 40, 35, this.mc.thePlayer);
    }

    private void drawButton(int xPosition, int yPosition, String s, Color color, int mouseX,int mouseY) {
        boolean hovered = false;
        int width = 75;
        int height = 15;
        hovered = mouseX >= xPosition && mouseY >= yPosition && mouseX < xPosition + width && mouseY < yPosition + height;

        if(hovered) {
            color = new Color(color.getRed(),color.getGreen(),color.getBlue(), color.getAlpha() - 50);
        }

        Gui.drawRect(xPosition , yPosition , xPosition + width , yPosition + height , color.getRGB());
        Gui.drawCenteredString(fontRendererObj , s ,xPosition + (width / 2), yPosition + (height / 4) , -1);

    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        int XPosition = this.width / 2 - 50;
        int nextYPosition = this.height / 2 + (this.height / 4);
        int prevYPosition = this.height / 2 + (this.height / 4) + 20;
        int width = 75;
        int height = 15;
        boolean nextPressed = mouseX >= XPosition && mouseY >= nextYPosition && mouseX < XPosition + width && mouseY < nextYPosition + height;
        boolean prevPressed = mouseX >= XPosition && mouseY >= prevYPosition && mouseX < XPosition + width && mouseY < prevYPosition + height;

        if(nextPressed) {
            CapeController.next();
            CapeUtils.downloadCape(Minecraft.getMinecraft().thePlayer);
            CapeUtils.reloadCape(Minecraft.getMinecraft().thePlayer);
        }
        if(prevPressed) {
            CapeController.prev();
            CapeUtils.downloadCape(Minecraft.getMinecraft().thePlayer);
            CapeUtils.reloadCape(Minecraft.getMinecraft().thePlayer);
        }

    }
}
