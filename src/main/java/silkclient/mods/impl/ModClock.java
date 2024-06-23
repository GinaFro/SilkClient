package silkclient.mods.impl;

import net.minecraft.client.gui.Gui;
import silkclient.gui.hud.ScreenPosition;
import silkclient.mods.ModDraggable;
import silkclient.utils.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalTime;

public class ModClock extends ModDraggable {

    private int offsetX = 7;
    private int offsetY = 5;

    @Override
    public int getWidth() {
        return font.getStringWidth("Time: 12:60 PM") + (offsetX * 2);
    }
    @Override
    public int getHeight() {
        return font.FONT_HEIGHT + (offsetY * 2);
    }

    @Override
    public void render(ScreenPosition pos) {
        LocalTime time = LocalTime.now();
        String minute = Integer.toString(time.getMinute());
        int hour = time.getHour();
        String meridiem = "AM";
        String date = time.getHour() + ":" + time.getMinute();
        if(time.getHour() > 12) {
             hour = time.getHour() - 12;
            meridiem = "PM";
        }else {
             hour = time.getHour();
            meridiem = "AM";
        }
        date = hour + ":" + minute + " " + meridiem;

        if(time.getMinute() < 10) {
            minute = "0" + time.getMinute();
        }

        Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth() + 1, pos.getAbsoluteY() + getHeight(), 0x90000000);
        if (this.isChromaOn()) {
            StringUtils.getInstance().drawChromaString(date,pos.getAbsoluteX() + (getWidth() / 2), pos.getAbsoluteY() + (getHeight() / 4),false);
        } else {
            Gui.drawCenteredString(font, date, pos.getAbsoluteX() + (getWidth() / 2), pos.getAbsoluteY() + (getHeight() / 4), -1);
        }
    }
}
