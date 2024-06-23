package silkclient.mods.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import silkclient.gui.hud.ScreenPosition;
import silkclient.mods.ModDraggable;
import silkclient.utils.StringUtils;

public class ModPingDisplay extends ModDraggable {

    private int offsetX = 7;
    private int offsetY = 5;

    @Override
    public int getWidth() {
        return font.getStringWidth("Ping: 1000") + (offsetX * 2);
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT + (offsetY * 2);
    }

    @Override
    public void render(ScreenPosition pos) {
        Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth() + 1, pos.getAbsoluteY() + getHeight(), 0x90000000);

        long ping = 0;

        if(Minecraft.getMinecraft().isSingleplayer()) {
            ping = 0;
        }else {
            ping = Minecraft.getMinecraft().getCurrentServerData().pingToServer;
            
        }


        if(this.isChromaOn()){
            StringUtils.getInstance().drawChromaString("Ping: " + ping , pos.getAbsoluteX() + ( getWidth() / 2), pos.getAbsoluteY() + (getHeight() / 4) , false);
        }else {
            Gui.drawCenteredString( font,"Ping: " + ping , pos.getAbsoluteX() + ( getWidth() / 2), pos.getAbsoluteY() + (getHeight() / 4) , -1);
        }
    }
}
