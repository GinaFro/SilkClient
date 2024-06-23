package silkclient.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import org.lwjgl.input.Mouse;

import java.io.IOException;

public class GUiSliderTest extends GuiScreen {

    private ColorSlider slider;

    @Override
    public void initGui() {
        this.buttonList.add(slider = new ColorSlider(10001,this.width/2 , this.height / 2 ,255));
    }



    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        slider.clicked = true;
        slider.mousePressed(mc,mouseX,mouseY);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        slider.mouseReleased(mouseX,mouseY);
    }
}

