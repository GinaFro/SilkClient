package silkclient.mods.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import silkclient.gui.hud.ScreenPosition;
import silkclient.mods.ModDraggable;
import silkclient.utils.StringUtils;

public class ModServer_Info extends ModDraggable {

    private int offsetX = 7;
    private int offsetY = 5;

    @Override
    public int getWidth() {
        String ip = "";
        if(Minecraft.getMinecraft().isSingleplayer()) {
            ip = Minecraft.getMinecraft().theWorld.getWorldInfo().getWorldName();
        }else {
            ip = Minecraft.getMinecraft().getCurrentServerData().serverIP;
        }
        return font.getStringWidth(ip) + (offsetX * 2);
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT + (offsetY * 2);
    }

    @Override
    public void render(ScreenPosition pos) {
        Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth() + 1, pos.getAbsoluteY() + getHeight(), 0x90000000);
        if(this.isChromaOn()) {
            StringUtils.getInstance().drawChromaString(getIP() , pos.getAbsoluteX() + ( getWidth() / 2), pos.getAbsoluteY() + (getHeight() / 4),false);
        }else {
            Gui.drawCenteredString(font , getIP() ,pos.getAbsoluteX() + ( getWidth() / 2), pos.getAbsoluteY() + (getHeight() / 4) , -1);
        }
    }

    private String getIP() {
        String IP;
        if(mc.isSingleplayer()) {
            IP = mc.getIntegratedServer().getWorldName();
        }else {
            IP = mc.getCurrentServerData().serverIP;
        }
        return IP;
    }

    public void renderDummy(ScreenPosition pos) {
        Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth() + 1, pos.getAbsoluteY() + getHeight(), 0x90000000);
        font.drawString("Server IP", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 1, -1);
    }

}
