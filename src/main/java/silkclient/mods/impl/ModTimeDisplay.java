package silkclient.mods.impl;

import net.minecraft.client.gui.Gui;
import silkclient.gui.hud.ScreenPosition;
import silkclient.mods.ModDraggable;
import silkclient.utils.StringUtils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ModTimeDisplay extends ModDraggable {

    private int offsetX = 7;
    private int offsetY = 5;

    @Override
    public int getWidth() {
        return font.getStringWidth("TIME: 100:100") + (offsetX * 2);
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT + (offsetY * 2);
    }

    @Override
    public void render(ScreenPosition pos) {
        Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth() + 1, pos.getAbsoluteY() + getHeight(), 0x90000000);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime time = LocalTime.now();
        String t = dtf.format(time);
        if(this.isChromaOn()) {
            StringUtils.getInstance().drawChromaString(t , pos.getAbsoluteX(), pos.getAbsoluteY(), false);
        }else {
            Gui.drawCenteredString(font , t , pos.getAbsoluteX() , pos.getAbsoluteY() , -1);
        }
    }
}
