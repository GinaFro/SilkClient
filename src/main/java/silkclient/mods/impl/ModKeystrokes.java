package silkclient.mods.impl;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import silkclient.gui.hud.ScreenPosition;
import silkclient.mods.ModCategory;
import silkclient.mods.ModDraggable;
import silkclient.utils.StringUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModKeystrokes extends ModDraggable {





    private List<Long> clicksLMB = new ArrayList<Long>();

    private List<Long> clicksRMB = new ArrayList<Long>();

    private boolean cpsOn = false;
    public JsonObject settings;

    @Override
    public void saveSettings(JsonObject settings) {
        super.saveSettings(settings);
        this.settings = settings;
    }

    public boolean isCpsOn() {
        return cpsOn;
    }

    public void setCpsOn(boolean cpsOn) {
        this.cpsOn = cpsOn;
    }






    private boolean wasPressedLMB;
    private long lastPressedLMB;

    private boolean wasPressedRMB;
    private long lastPressedRMB;


    public static enum KeystrokeMode {

        WASD(Key.W, Key.A, Key.S, Key.D), WASD_MOUSE(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB),
        WASD_JUMP(Key.W, Key.A, Key.S, Key.D,
                new Key("§m-------", Minecraft.getMinecraft().gameSettings.keyBindJump, 1, 41, 58, 18)),
        WASD_JUMP_MOUSE(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB,
                new Key("§m-------", Minecraft.getMinecraft().gameSettings.keyBindJump, 1, 61, 58, 18));

        private final Key[] keys;
        private int width = 0;
        private int height = 0;

        private KeystrokeMode(Key... keysIn) {
            this.keys = keysIn;

            for (Key key : keys) {
                this.width = Math.max(this.width, key.getX() + key.getWidth());
                this.height = Math.max(this.height, key.getY() + key.getHeight());
            }
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }

        public Key[] getKeys() {
            return keys;
        }

    }

    private static class Key {

        private static final Key W = new Key("W", Minecraft.getMinecraft().gameSettings.keyBindForward, 21, 1, 18, 18);
        private static final Key A = new Key("A", Minecraft.getMinecraft().gameSettings.keyBindLeft, 1, 21, 18, 18);
        private static final Key S = new Key("S", Minecraft.getMinecraft().gameSettings.keyBindBack, 21, 21, 18, 18);
        private static final Key D = new Key("D", Minecraft.getMinecraft().gameSettings.keyBindRight, 41, 21, 18, 18);

        private static final Key LMB = new Key("LMB", Minecraft.getMinecraft().gameSettings.keyBindAttack, 1, 41, 28,
                18);
        private static final Key RMB = new Key("RMB", Minecraft.getMinecraft().gameSettings.keyBindUseItem, 31, 41, 28,
                18);

        private final String name;
        private final KeyBinding keyBind;
        private final int x;
        private final int y;
        private final int width;
        private final int height;

        public Key(String name, KeyBinding keyBind, int x, int y, int width, int height) {
            this.name = name;
            this.keyBind = keyBind;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public boolean isDown() {
            return keyBind.isKeyDown();
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }

        public String getName() {
            return name;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

    }


    private KeystrokeMode mode = KeystrokeMode.WASD_JUMP_MOUSE;

    public void setMode(KeystrokeMode mode) {
        this.mode = mode;
    }

    @Override
    public int getWidth() {
        return mode.getWidth();
    }

    @Override
    public int getHeight() {
        return mode.getHeight();
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
        if (mc.currentScreen instanceof GuiInventory || mc.currentScreen instanceof GuiChat)
            return;

        GL11.glPushMatrix();

        boolean blend = GL11.glIsEnabled(GL11.GL_BLEND);

        for (Key key : mode.getKeys()) {

            int textWidth = mc.fontRendererObj.getStringWidth(key.getName());

            Gui.drawRect(pos.getAbsoluteX() + key.getX() , pos.getAbsoluteY() + key.getY() ,
                    pos.getAbsoluteX() + key.getX() + key.getWidth(), pos.getAbsoluteY() + key.getY() + key.getHeight(),
                    key.isDown() ? new Color(255, 255, 255, 102).getRGB() : new Color(0, 0, 0, 102).getRGB());
            if(this.isChromaOn()) {
                StringUtils.getInstance().drawChromaString(key.getName(), pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 ,
                        pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 - 4, false);
            } else {
                font.drawString(key.getName(), pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2,
                        pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 - 4, -1);
            }

            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2F , pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 + 4F, 1F);
            if(key.getName().matches(key.LMB.getName()) && isCpsOn()) {
                if(this.isChromaOn()) {
                    StringUtils.getInstance().drawChromaString("CPS: " + getLMB(), pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2 , pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 + 4, false);
                }else {
                    font.drawString("CPS: " + getLMB(), pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 + textWidth / 2 , pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 + 4, -1);
                }
            }

            if(key.getName().matches(key.RMB.getName()) && isCpsOn()) {
                if(this.isChromaOn()) {
                    StringUtils.getInstance().drawChromaString("CPS: " + getRMB(), pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2 , pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 + 4, false);
                }else {
                    Gui.drawCenteredString(font , "CPS: " + getRMB(), pos.getAbsoluteX() + key.getX() + key.getWidth() / 2  , pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 + 4, -1);
                }
            }
            GlStateManager.popMatrix();
        }





        if (blend) {
            GL11.glEnable(GL11.GL_BLEND);
        }

        GL11.glPopMatrix();

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

    public KeystrokeMode getMode() {
        if(mode == null) {
            mode = KeystrokeMode.WASD;
        }
        return mode;
    }
    public int modeToInt() {
        switch (getMode()) {
            case WASD:
                return 0;
            case WASD_JUMP:
                return 1;
            case WASD_MOUSE:
                return 2;
            case WASD_JUMP_MOUSE:
                return 3;
            default:
                return 0;
        }
    }




    public KeystrokeMode intToMode() {
        if(settings.get("mode") == null) {
            settings.addProperty("mode", modeToInt());
        }
        switch (settings.get("mode").getAsInt()) {
            case 0:
                return KeystrokeMode.WASD;
            case 1:
                return KeystrokeMode.WASD_JUMP;
            case 2:
                return KeystrokeMode.WASD_MOUSE;
            case 3:
                return KeystrokeMode.WASD_JUMP_MOUSE;
            default:
                return KeystrokeMode.WASD;
        }
    }



}
