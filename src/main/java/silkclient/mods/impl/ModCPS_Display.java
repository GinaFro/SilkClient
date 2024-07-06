package silkclient.mods.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import net.minecraft.client.gui.Gui;
import silkclient.gui.hud.ScreenPosition;
import silkclient.mods.ModDraggable;
import silkclient.utils.StringUtils;

public class ModCPS_Display extends ModDraggable {



    private List<Long> clicksLMB = new ArrayList<Long>();

    private List<Long> clicksRMB = new ArrayList<Long>();

    private boolean wasPressedLMB;
    private long lastPressedLMB;

    private boolean wasPressedRMB;
    private long lastPressedRMB;





    public enum CPSMODE {
        LMB(true, false),
        RMB(false , true),
        LRMB(true , true);

        private boolean right = false;
        private boolean left = false;
        CPSMODE(boolean b, boolean c) {
            this.left = b;
            this.right = c;
        }
    }

    private int offsetX = 7;
    private int offsetY = 5;

    private CPSMODE mode = CPSMODE.LRMB;

    public void setMode(CPSMODE mode) {
        this.mode = mode;
    }

    public CPSMODE getMode() {
        return mode;
    }

    @Override
    public int getWidth() {
        return this.font.getStringWidth("CPS: [00 | 00]") + offsetX;
    }

    @Override
    public int getHeight() {
        return this.font.FONT_HEIGHT + offsetY*2;
    }


    @Override
    public void render(ScreenPosition pos) {
        final boolean pressedLMB = Mouse.isButtonDown(0);

        if (pressedLMB != this.wasPressedLMB) {
            this.lastPressedLMB = System.currentTimeMillis();
            this.wasPressedLMB = pressedLMB;
            if (pressedLMB) {
                this.clicksLMB.add(this.lastPressedLMB);
            }
        }

        final boolean pressedRMB = Mouse.isButtonDown(1);

        if (pressedRMB != this.wasPressedRMB) {
            this.lastPressedRMB = System.currentTimeMillis();
            this.wasPressedRMB = pressedRMB;
            if (pressedRMB) {
                this.clicksRMB.add(this.lastPressedRMB);
            }
        }
        Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth() + 1, pos.getAbsoluteY() + getHeight(), new Color(0 , 0 , 0 ,102).getRGB());
        if(this.mode == CPSMODE.LRMB) {
            if(this.isChromaOn()) {
                StringUtils.getInstance().drawChromaString("CPS: [" + getLMB() + " | " + getRMB() + "]", pos.getAbsoluteX() + getWidth() / 2, pos.getAbsoluteY() + getHeight() / 3, false);
            }else {
                Gui.drawCenteredString(font , "CPS: [" + getLMB() + " | " + getRMB() + "]", pos.getAbsoluteX() + getWidth() / 2, pos.getAbsoluteY() + getHeight() / 3, -1);
            }
        }else if(this.mode == CPSMODE.LMB) {
            if(this.isChromaOn()) {
                StringUtils.getInstance().drawChromaString("CPS: [" + getLMB() + " ]", pos.getAbsoluteX() + getWidth() / 2, pos.getAbsoluteY() + getHeight() / 3, false);
            }else {
                Gui.drawCenteredString(font , "CPS: [" + getLMB() + " ]", pos.getAbsoluteX() + getWidth() / 2, pos.getAbsoluteY() + getHeight() / 3, -1);
            }
        }else if(this.mode == CPSMODE.RMB) {
            if(this.isChromaOn()) {
                StringUtils.getInstance().drawChromaString("CPS: [" + getRMB() + " ]", pos.getAbsoluteX() + getWidth() / 2, pos.getAbsoluteY() + getHeight() / 3, false);
            }else {
                Gui.drawCenteredString(font , "CPS: [" + getRMB() + " ]", pos.getAbsoluteX() + getWidth() / 2, pos.getAbsoluteY() + getHeight() / 3, -1);
            }
        }
    }



    public int getLMB() {

        final long time = System.currentTimeMillis();
        this.clicksLMB.removeIf(aLong -> aLong + 1000 < time);
        return this.clicksLMB.size();

    }

    public int getRMB() {

        final long time = System.currentTimeMillis();
        this.clicksRMB.removeIf(aLong -> aLong + 1000 < time);
        return this.clicksRMB.size();

    }

}
