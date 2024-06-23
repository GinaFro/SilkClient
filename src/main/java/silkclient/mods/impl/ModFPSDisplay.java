package silkclient.mods.impl;

import net.minecraft.client.gui.Gui;
import silkclient.gui.hud.ScreenPosition;
import silkclient.mods.ModCategory;
import silkclient.mods.ModDraggable;
import silkclient.utils.StringUtils;

public class ModFPSDisplay extends ModDraggable {



    private int offsetX = 7;
    private int offsetY = 5;

    @Override
    public int getWidth() {
        return font.getStringWidth("FPS:100000") + (offsetX * 2);
    }
    @Override
    public int getHeight() {
        return font.FONT_HEIGHT + (offsetY * 2);
    }

    @Override
    public void render(ScreenPosition pos) {
        int fps = mc.getDebugFPS();
        Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth() + 1, pos.getAbsoluteY() + getHeight(), 0x90000000);
        if(this.isChromaOn()) {
            StringUtils.getInstance().drawChromaString("FPS: " + fps, pos.getAbsoluteX() + ( getWidth() / 2), pos.getAbsoluteY() + (getHeight() / 4), false);
        }else {
            Gui.drawCenteredString(font, "FPS: " + fps, pos.getAbsoluteX() + ( getWidth() / 2), pos.getAbsoluteY() + (getHeight() / 4), -1);
        }
    }


}
