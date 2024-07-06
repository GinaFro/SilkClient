package silkclient.gui.ModMenu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import silkclient.mods.Mod;
import silkclient.utils.ShapeUtils;

import java.awt.*;

public class ModButton extends GuiButton {
    private Mod mod;
    public ModButton(int buttonId, int x, int y, int width, int height, Mod m) {
        super(buttonId, x, y, width , height , m.getClass().getSimpleName().replace("Mod", ""));
        this.mod = m;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if(this.visible) {
            GlStateManager.resetColor();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.scale(1.0F, 1.0F, 1.0F);
            ShapeUtils.drawRoundedRect(xPosition, yPosition, xPosition + width, yPosition + height, 5f, new Color(255, 255, 255, 72).getRGB());
            ShapeUtils.drawRoundedOutline(xPosition, yPosition, xPosition + width, yPosition + height, 5f, 3, mod.isEnabled() ? new Color(160 , 32, 240).getRGB() : Color.black.getRGB());
            String string = mod.getClass().getSimpleName().replace("Mod" , "").replace("_" , "");

            if (mc.fontRendererObj.getStringWidth(mod.getClass().getSimpleName().replace("Mod", "")) > width) {
                String[] str = mod.getClass().getSimpleName().replace("Mod", "").split("_" , 4);
                for (int i = 0; i < str.length; i++) {
                    Gui.drawCenteredString(mc.fontRendererObj, str[i], xPosition + width / 2, yPosition + 10 + 10 * i, -1);
                }
            } else {

                Gui.drawCenteredString(mc.fontRendererObj, string, xPosition + width / 2, yPosition + 10, -1);
            }
        }
    }

    public void toggle() {
        mod.setEnabled(!mod.isEnabled());
    }
}
