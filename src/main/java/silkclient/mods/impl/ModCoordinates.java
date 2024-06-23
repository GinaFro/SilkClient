package silkclient.mods.impl;

import net.minecraft.client.gui.Gui;
import silkclient.gui.hud.ScreenPosition;
import silkclient.mods.ModDraggable;
import silkclient.utils.StringUtils;

import java.awt.*;

public class ModCoordinates extends ModDraggable {




    private static int offsetX = 14;
    private static int offsetXText = 20;
    private static int offsetY = 7;
    private static int biomeText = 16;

    @Override
    public int getWidth() {
        return this.font.getStringWidth("Biome: Deep Ocean Biome") + offsetX;
    }

    @Override
    public int getHeight() {
        return this.font.FONT_HEIGHT * 5 + offsetY;
    }

    @Override
    public void render(ScreenPosition pos) {
        Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth() + 1, pos.getAbsoluteY() + getHeight(), new Color(0 , 0 , 0 ,102).getRGB());
        if(this.isChromaOn()) {
            StringUtils.getInstance().drawUnCenteredChromaString("X: " + (int) this.mc.thePlayer.posX, pos.getAbsoluteX() , pos.getAbsoluteY() + this.font.FONT_HEIGHT, false);
            StringUtils.getInstance().drawUnCenteredChromaString("Y: " + (int) this.mc.thePlayer.posY, pos.getAbsoluteX() , pos.getAbsoluteY() + this.font.FONT_HEIGHT * 2, false);
            StringUtils.getInstance().drawUnCenteredChromaString("Z: " + (int) this.mc.thePlayer.posZ, pos.getAbsoluteX() , pos.getAbsoluteY() + this.font.FONT_HEIGHT * 3, false);
            StringUtils.getInstance().drawUnCenteredChromaString("Biome: " + this.mc.theWorld.getBiomeGenForCoords(this.mc.thePlayer.getPosition()).biomeName, pos.getAbsoluteX()  + offsetXText + biomeText, pos.getAbsoluteY() + this.font.FONT_HEIGHT * 4, false);
        }else {
            Gui.drawString(font , "X: " + (int) this.mc.thePlayer.posX, pos.getAbsoluteX() , pos.getAbsoluteY() + this.font.FONT_HEIGHT, -1);
            Gui.drawString(font , "Y: " + (int) this.mc.thePlayer.posY , pos.getAbsoluteX()  , pos.getAbsoluteY() + this.font.FONT_HEIGHT * 2, -1);
            Gui.drawString(font , "Z: " + (int) this.mc.thePlayer.posZ, pos.getAbsoluteX()  , pos.getAbsoluteY() + this.font.FONT_HEIGHT * 3, -1);
            Gui.drawString(font , "Biome: " + this.mc.theWorld.getBiomeGenForCoords(this.mc.thePlayer.getPosition()).biomeName, pos.getAbsoluteX()   , pos.getAbsoluteY() + this.font.FONT_HEIGHT * 4, -1);
        }
    }




}
