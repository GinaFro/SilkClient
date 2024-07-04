package silkclient.UI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;

public class IgnDisplayOnMainMenu extends GuiScreen {
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        // Get the player's name
        String playerName = Minecraft.getMinecraft().thePlayer.getName();

        // Get the FontRenderer instance
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;

        // Draw the player's name on the screen
        fontRenderer.drawString(playerName, 10, 10, 0xFFFFFF);
    }
}


