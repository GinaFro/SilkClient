package silkclient.mods.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import silkclient.gui.hud.ScreenPosition;
import silkclient.mods.ModDraggable;
import silkclient.utils.StringUtils;

public class Momentum extends ModDraggable {



    private int offsetX = 7;
    private int offsetY = 5;

    @Override
    public int getWidth() {
        return font.getStringWidth("SPEED: 100 BPS") + (offsetX * 2);
    }
    @Override
    public int getHeight() {
        return font.FONT_HEIGHT + (offsetY * 2);
    }

    @Override
    public void render(ScreenPosition pos) {
         Minecraft mc = Minecraft.getMinecraft();
        float rat = Minecraft.getMinecraft().timer.ticksPerSecond * Minecraft.getMinecraft().timer.timerSpeed;
        double bps = mc.thePlayer.getDistance(mc.thePlayer.lastTickPosX, mc.thePlayer.posY, mc.thePlayer.lastTickPosZ) * rat;
        Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth() + 1, pos.getAbsoluteY() + getHeight(), 0x90000000);
        if(this.isChromaOn()) {
            StringUtils.getInstance().drawChromaString("SPEED: " + (int)(bps) + " BPS", pos.getAbsoluteX() + ( getWidth() / 2), pos.getAbsoluteY() + (getHeight() / 4), false);
        }else {
            Gui.drawCenteredString(font, "SPEED: " + (int)(bps) + " BPS", pos.getAbsoluteX() + ( getWidth() / 2), pos.getAbsoluteY() + (getHeight() / 4), -1);
        }
    }
}
