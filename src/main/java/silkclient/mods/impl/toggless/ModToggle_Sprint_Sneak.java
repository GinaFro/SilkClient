package silkclient.mods.impl.toggless;

import silkclient.gui.hud.ScreenPosition;
import silkclient.mods.ModDraggable;
import silkclient.utils.StringUtils;

public class ModToggle_Sprint_Sneak extends ModDraggable {


    public boolean flyBoost = true;
    public boolean sprintT = true;
    public boolean sneakT = true;
    public float flyBoostFactor = 4;
    public int keyHoldTicks = 7;
    private String textToRender = "";



    public boolean isFlyBoost() {
        return flyBoost;
    }


    public void setFlyBoost(boolean flyBoost) {
        this.flyBoost = flyBoost;
    }

    public boolean isSprintT() {
        return sprintT;
    }

    public void setSprintT(boolean sprintT) {
        this.sprintT = sprintT;
    }

    public boolean isSneakT() {
        return sneakT;
    }

    public void setSneakT(boolean sneakT) {
        this.sneakT = sneakT;
    }

    @Override
    public int getWidth() {
        return font.getStringWidth("Sprint Sneak MOD");
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }



    @Override
    public void render(ScreenPosition pos) {
        textToRender = mc.thePlayer.movementInput.getDisplayText();
        if(this.isChromaOn()) {
            StringUtils.getInstance().drawChromaString(textToRender, pos.getAbsoluteX(), pos.getAbsoluteY(), false);
        }else {
            font.drawString(textToRender, pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
        }
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawString("Sprint Sneak Mod", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }


}
