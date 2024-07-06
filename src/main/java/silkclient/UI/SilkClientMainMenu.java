package silkclient.UI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import silkclient.utils.ShapeUtils;

import java.awt.*;
import java.io.IOException;

public class SilkClientMainMenu extends GuiScreen {

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        mc.getTextureManager().bindTexture(new ResourceLocation("silkclient/mainmenu.png"));
        this.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        int centerX = this.width / 2;

        this.buttonList.add(new TransparentButton(1, centerX - 100, this.height / 2 - 50, "Singleplayer"));
        this.buttonList.add(new TransparentButton(2, centerX - 100, this.height / 2 - 25, "Multiplayer"));
        this.buttonList.add(new TransparentButton(3, centerX - 100, this.height / 2, "Options"));
        this.buttonList.add(new TransparentButton(4, centerX + 2, this.height / 2 + 25, 100, 20, "Quit Game"));
        this.buttonList.add(new TransparentButton(5, centerX - 100 - 2, this.height / 2 + 25, 100, 20, "Mod Options"));

        super.initGui();
    }


    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1) {
            mc.displayGuiScreen(new GuiSelectWorld(this));
        }
        if (button.id == 2) {
            mc.displayGuiScreen(new GuiMultiplayer(this));
        }
        if (button.id == 3) {
            mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
        }
        if (button.id == 4) {
            mc.shutdown();
        }
        if (button.id == 5) {
            mc.displayGuiScreen(new GuiSelectWorld(this));
        }


        super.actionPerformed(button);
    }

    public class TransparentButton extends GuiButton {
        public TransparentButton(int buttonId, int x, int y, String buttonText) {
            super(buttonId, x, y, buttonText);
        }

        public TransparentButton(int buttonId, int x, int y, int width, int height, String buttonText) {
            super(buttonId, x, y, width, height, buttonText);
        }



        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY) {
            if (this.visible) {
                FontRenderer font = mc.fontRendererObj;
                boolean hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
                int k = getHoverState(hovered);

                // Choose the background color based on whether the button is hovered
                int backgroundColor = hovered? 0x80FFFF00 : 0x80FFFFFF;

                // Choose the text color (bright blue when hovered, black otherwise)
                int textColor;
                if (this.displayString.equals(".")) {
                    textColor = 0xFF007bff; // bright blue
                } else {
                    textColor = hovered? 0xFF007bff : 0xFF000000; // bright blue when hovered, black otherwise
                }

                // Draw a rounded rectangle
                ShapeUtils.drawRoundedRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 10, backgroundColor);

                // Calculate the x-coordinate of the text to center it within the button
                int textX = this.xPosition + (this.width - font.getStringWidth(this.displayString)) / 2;

                // Draw the logo above the "Singleplayer" button
                if (this.displayString.equals("Singleplayer")) {
                    mc.getTextureManager().bindTexture(new ResourceLocation("silkclient/logo.png"));
                    GL11.glColor4f(1, 1, 1, 1);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    GL11.glColorMask(true, true, true, false); // disable writing to the alpha channel
                    Gui.drawModalRectWithCustomSizedTexture(this.xPosition + (this.width - 64) / 2, this.yPosition - 64, 0, 0, 64, 64, 64, 64);
                    GL11.glColorMask(true, true, true, true); // re-enable writing to the alpha channel
                    GL11.glDisable(GL11.GL_BLEND);
                }

                font.drawString(this.displayString, textX, this.yPosition + 6, textColor);

            }
        }
    }
}