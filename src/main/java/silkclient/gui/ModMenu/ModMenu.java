package silkclient.gui.ModMenu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import silkclient.gui.hud.HUDManager;
import silkclient.mods.Mod;
import silkclient.utils.Config;
import silkclient.utils.RenderUtils;
import silkclient.utils.ShapeUtils;

import java.awt.*;
import java.io.IOException;

public class ModMenu extends GuiScreen {

     ScaledResolution sr;

     private int boxWidth;
     private int bowHeight;
     private int button = 55;
     private Logger logger = LogManager.getLogger();

     private int currentSection = 1;
     private int MOD = 1 , HOME = 0 , SETTINGS = 2;

     @Override
     public void initGui() {
          mc = Minecraft.getMinecraft();
          sr = new ScaledResolution(mc);
          boxWidth = sr.getScaleFactor() * 600;
          bowHeight = sr.getScaleFactor() * 400;
          loadButtons();
     }

     @Override
     public void drawScreen(int mouseX, int mouseY, float partialTicks) {
          GlStateManager.resetColor();
          GlStateManager.color(1.0F,1.0F,1.0F,1.0F);
          GlStateManager.scale(1.0,1.0,1.0f);
          ShapeUtils.drawRoundedRect(sr.getScaledWidth() / 8 -5 , sr.getScaledHeight() / 8 - 5, sr.getScaledWidth() - sr.getScaledWidth() / 8 + 5 , sr.getScaledHeight() - sr.getScaledHeight() / 8 + 5 , 10f , new Color(0,0,0,255).getRGB());

          ShapeUtils.drawRoundedRect(sr.getScaledWidth() / 8 - 5 , sr.getScaledHeight() / 8 - 5 , sr.getScaledWidth() / 4 - 15 , sr.getScaledHeight() - sr.getScaledHeight()/8  - 5 , 10f , new Color(0,0,0,252).getRGB());
          GlStateManager.pushMatrix();
          GlStateManager.resetColor();
          GlStateManager.color(1.0F,1.0F,1.0F,1.0F);
          GlStateManager.scale(1.0,1.0,1.0f);
          mc.getTextureManager().bindTexture(new ResourceLocation("silkclient/logo.png"));
          Gui.drawScaledCustomSizeModalRect(sr.getScaledWidth() / 8 + 29/2 , sr.getScaledHeight() / 8 + 29 , 0.0F,0.0F, 32,32,32,32,32,32);
          GlStateManager.popMatrix();
          super.drawScreen(mouseX, mouseY, partialTicks);
     }

     @Override
     public boolean doesGuiPauseGame() {
          return false;
     }

     @Override
     public void onGuiClosed() {
     }

     @Override
     public void updateScreen() {
          this.buttonList.clear();
          loadButtons();
     }

     public void loadButtons() {
          this.buttonList.add(new TextButton(1001, sr.getScaledWidth() / 8 + 7, sr.getScaledHeight() / 8 + button + button / 2 , "Home"));
          this.buttonList.add(new TextButton(1002, sr.getScaledWidth() / 8 + 7, sr.getScaledHeight() / 8 + button +  button / 2 +15, "Mods"));
          this.buttonList.add(new TextButton(1003, sr.getScaledWidth() / 8 + 7, sr.getScaledHeight() / 8 + button + button / 2 + 30, "Settings"));
          int modIndex = 0;
          int yIndex = 0;
          int xIndex = 0;
          if(this.currentSection == MOD) {
               for (Mod m : HUDManager.getInstance().getMods()) {
                    int posX = sr.getScaledWidth() / 4 + 5 + ((5 + button) * xIndex);
                    int posY = sr.getScaledHeight() / 4 + 10 + ((5 + button) * yIndex) - button / 2;

                    this.buttonList.add(new ModButton(modIndex, posX, posY, button, button, m));

                    modIndex++;
                    yIndex++;
                    if (posY + button >= (sr.getScaledHeight() - sr.getScaledHeight() / 4) - 20) {
                         yIndex = 0;
                         xIndex++;
                    }
//               logger.info("Y: " +posY);
//               int temp = sr.getScaledHeight() - sr.getScaledHeight()/4;
//               logger.info("HEIGHT: " + temp);
               }
          }
     }


     @Override
     protected void actionPerformed(GuiButton button) throws IOException {
          for(GuiButton b : buttonList) {
               if(b instanceof ModButton) {
                    ModButton m = (ModButton) b;
                    if(m.id == button.id) {
                         m.toggle();
                    }
               }else {
                    if(b instanceof TextButton) {
                         if(button.id == 1001) {
                              currentSection = HOME;
                         }
                         if(button.id == 1002) {
                              currentSection = MOD;
                         }
                         if(button.id == 1003) {
                              currentSection = SETTINGS;
                         }

                    }
               }
          }
     }
}
