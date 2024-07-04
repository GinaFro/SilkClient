package silkclient.mods.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import silkclient.gui.hud.ScreenPosition;
import silkclient.mods.ModDraggable;
import silkclient.utils.StringUtils;

import java.util.Timer;
import java.util.TimerTask;

public class ModComboCounter extends ModDraggable {
    private int comboCount = 0;
    private int comboThreshold = 3; // adjust this value to change the combo threshold
    private long lastActionTime = 0;
    private Timer timer = new Timer();
    private TimerTask timerTask;
    private FontRenderer font;

    public ModComboCounter() {
        font = Minecraft.getMinecraft().fontRendererObj;
    }

    public void onActionPerformed(boolean isEntityValid) {
        if (isEntityValid) {
            long currentTime = System.nanoTime();
            if (currentTime - lastActionTime < 5000000000L) { // 5 second threshold, adjust as needed
                comboCount++;
                System.out.println("Combo count: " + comboCount);
            } else {
                comboCount = 1;
                System.out.println("Combo reset!");
            }
            lastActionTime = currentTime;

            if (comboCount >= comboThreshold) {
                System.out.println("Combo achieved!");
            }

            // Reset combo count after 2 seconds of inactivity
            if (timerTask!= null) {
                timerTask.cancel();
            }
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    comboCount = 0;
                    System.out.println("Combo reset due to inactivity!");
                }
            };
            timer.schedule(timerTask, 2000);
        }
    }

    @Override
    public int getWidth() {
        return 50;
    }

    @Override
    public int getHeight() {
        return 20;
    }

    public void render(ScreenPosition pos) {
        Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth() + 1, pos.getAbsoluteY() + getHeight(), 0x90000000);

        if(this.isChromaOn()){
            StringUtils.getInstance().drawChromaString("Combo: " + comboCount , pos.getAbsoluteX() + ( getWidth() / 2), pos.getAbsoluteY() + (getHeight() / 4) , false);
        }else {
            Gui.drawCenteredString( font,"Combo Counter: " + comboCount , pos.getAbsoluteX() + ( getWidth() / 2), pos.getAbsoluteY() + (getHeight() / 4) , -1);
        }
    }

    public void handleKeyInput() {
        if (Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.isEntityAlive()) {
            onActionPerformed(true);
        }
    }
}