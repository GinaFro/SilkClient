package silkclient.mods;

import silkclient.gui.hud.HUDManager;
import silkclient.mods.impl.*;
import silkclient.mods.impl.toggless.ModToggleSprintSneak;
import silkclient.mods.skyblock.ModCakeTimer;

public class ModInstances {

    private static ModCPSDisplay modCPSDisplay;
    private static ModFPSDisplay modFpsDisplay;
    private static ModArmorStatus modArmorStatus;
    private static ModKeystrokes modKeystrokes;
    private static ModToggleSprintSneak modToggleSprintSneak;
    private static ModPotionStatus modPotionStatus;
    private static ModCustomChatTest modCustomChatTest;
    private static ModCoordinates modCoordinates;
    private static Motionblur motionblur;
    private static ModItemPhysics itemPhysics;
    private static ModPingDisplay modPingDisplay;
    private static ModServerInfo modServerInfo;
    private static ModScoreboard modScoreboard;
    private static ModCakeTimer ModCakeTimer;
    private static Momentum momentum;
    private static ModFullbright modFullbright;
    private static ModCape modCape;
    private static ModClock modClock;

    public static void register(HUDManager api) {
        modCPSDisplay = new ModCPSDisplay();
        api.register(modCPSDisplay);
        modFpsDisplay = new ModFPSDisplay();
        api.register(modFpsDisplay);
        modArmorStatus = new ModArmorStatus();
        api.register(modArmorStatus);
        modKeystrokes = new ModKeystrokes();
        api.register(modKeystrokes);
        modToggleSprintSneak = new ModToggleSprintSneak();
        api.register(modToggleSprintSneak);
        modPotionStatus = new ModPotionStatus();
        api.register(modPotionStatus);
        modCustomChatTest = new ModCustomChatTest();
        modCoordinates = new ModCoordinates();
        api.register(modCoordinates);
        itemPhysics = new ModItemPhysics();
        api.register(itemPhysics);
        modPingDisplay = new ModPingDisplay();
        api.register(modPingDisplay);
        modServerInfo = new ModServerInfo();
        api.register(modServerInfo);
        modScoreboard = new ModScoreboard();
        api.register(modScoreboard);
        momentum = new Momentum();
        api.register(momentum);
        modFullbright = new ModFullbright();
        api.register(modFullbright);
        modCape = new ModCape();
        api.register(modCape);
        modClock = new ModClock();
        api.register(modClock);
    }

    public static ModCape getModCape() {
        return modCape;
    }

    public static ModKeystrokes getModKeystrokes() {
        return modKeystrokes;
    }

    public static ModFullbright getModFullbright() {
        return modFullbright;
    }

    public static ModItemPhysics getModItemPhysics() {
        return itemPhysics;
    }

    public static Motionblur getModMotionblur() {
        return motionblur;
    }

    public static ModCustomChatTest getModCustomChatTest() {
        return modCustomChatTest;
    }

    public static ModToggleSprintSneak getModToggleSprintSneak() {
        return modToggleSprintSneak;
    }
}
