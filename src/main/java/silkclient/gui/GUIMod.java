package silkclient.gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider;
import silkclient.gui.hud.HUDManager;
import silkclient.gui.hud.IRenderer;
import silkclient.mods.Mod;

public class GUIMod extends GuiScreen {

    int startingPosX = this.width / 4;
    int startingPosY = this.height / 4;
    int offsetX = 10;
    int offsetY = 5;
    int modWidth = 50;
    int modHeight = 20;
    int boxWidth = startingPosX + (this.width - this.width/4);


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
         int i = 0;
         int modsFitIn1Page = 10;
        for(IRenderer r : HUDManager.getInstance().getRegisteredRenderers()) {
            if(r instanceof Mod) {
                Mod m = (Mod)r;
                int xPos = this.width / 4;
                if(i >= 10) {
                    xPos = this.width - this.width/4;
                }
                int yPos = this.height/4 + offsetY + (modHeight * i) + (offsetY * i);

                if(i >= 10) {
                    i = 0;
                }

                Gui.drawCenteredString(fontRendererObj , m.getClass().getSimpleName().replace("Mod", ""),xPos , yPos , -1 );
                i++;

            }

        }
    }
}
