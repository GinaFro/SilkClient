package silkclient.mods;

import silkclient.gui.hud.HUDManager;
import silkclient.mods.impl.*;
import silkclient.mods.impl.toggless.ModToggle_Sprint_Sneak;
import silkclient.mods.skyblock.ModCakeTimer;

public class ModInstances {

    private static ModCPS_Display modCPSDisplay;
    private static ModFPS_Display modFpsDisplay;
    private static ModArmor_Status modArmorStatus;
    private static ModKeystrokes modKeystrokes;
    private static ModToggle_Sprint_Sneak modToggleSprintSneak;
    private static ModPotion_Status modPotionStatus;
    private static ModCustomChatTest modCustomChatTest;
    private static ModCoordinates modCoordinates;
    private static Motionblur motionblur;
    private static ModItem_Physics itemPhysics;
    private static ModPing_Display modPingDisplay;
    private static ModServer_Info modServerInfo;
    private static ModScoreboard modScoreboard;
    private static ModCakeTimer ModCakeTimer;
    private static Momentum momentum;
    private static ModFullbright modFullbright;
    private static ModCape modCape;
    private static ModClock modClock;
    private static ModCombo_Counter modComboCounter;
    private static ModPlayer_Hud modTargetHud;

    public static void register(HUDManager api) {
        modCPSDisplay = new ModCPS_Display();
        api.register(modCPSDisplay);
        modFpsDisplay = new ModFPS_Display();
        api.register(modFpsDisplay);
        modArmorStatus = new ModArmor_Status();
        api.register(modArmorStatus);
        modKeystrokes = new ModKeystrokes();
        api.register(modKeystrokes);
        modToggleSprintSneak = new ModToggle_Sprint_Sneak();
        api.register(modToggleSprintSneak);
        modPotionStatus = new ModPotion_Status();
        api.register(modPotionStatus);
        modCustomChatTest = new ModCustomChatTest();
        modCoordinates = new ModCoordinates();
        api.register(modCoordinates);
        itemPhysics = new ModItem_Physics();
        api.register(itemPhysics);
        modPingDisplay = new ModPing_Display();
        api.register(modPingDisplay);
        modServerInfo = new ModServer_Info();
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
        modComboCounter = new ModCombo_Counter();
        api.register(modComboCounter);
        modTargetHud= new ModPlayer_Hud();
        api.register(modTargetHud);
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

    public static ModItem_Physics getModItemPhysics() {
        return itemPhysics;
    }

    public static Motionblur getModMotionblur() {
        return motionblur;
    }

    public static ModCustomChatTest getModCustomChatTest() {
        return modCustomChatTest;
    }

    public static ModToggle_Sprint_Sneak getModToggleSprintSneak() {
        return modToggleSprintSneak;
    }
}
