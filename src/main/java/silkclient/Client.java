package silkclient;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.StringUtils;
import silkclient.cosmetics.CosmeticManager;
import silkclient.cosmetics.impl.Bandana;
import silkclient.cosmetics.threeD.ObjLoader;
import silkclient.events.EventManager;
import silkclient.events.EventTarget;
import silkclient.events.impl.ClientTickEvent;
import silkclient.gui.GUIMod;
import silkclient.gui.GUiSliderTest;
import silkclient.gui.ModMenu.ModMenu;
import silkclient.gui.SplashProgress;
import silkclient.gui.cosmetic.CapeSwitcherGUI;
import silkclient.gui.hud.HUDManager;
import silkclient.gui.modtoggle.GuiModToggle;
import silkclient.mods.ModInstances;
import silkclient.mods.skyblock.ModCakeTimer;
import silkclient.mods.skyblock.DungeonFloor;
import silkclient.mods.skyblock.Test;
import silkclient.utils.ConfigAPI;
import silkclient.utils.ScoreboardUtils;
import silkclient.utils.SkyblockUtils;

import java.util.List;

public class Client {

    private int tickAmount = 0;
    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;
    private int days = 0;

    private static final Client Instance = new Client();

    public static Client getInstance() {
        return Instance;
    }
    private HUDManager api;
    private DiscordRP discordRp = new DiscordRP();
    private static DungeonFloor currentFloor = DungeonFloor.NONE;

    private ObjLoader objLoader;


    public ObjLoader getObjLoader() {
        return objLoader;
    }

    public void init() {
        discordRp.start();
        FileManager.init();
        SplashProgress.setProgress(1 , "Starting");
        EventManager.register(this);
        EventManager.register(new ModCakeTimer());
        EventManager.register(new Test());
        objLoader = new ObjLoader();
        ConfigAPI.getInstance().load();

    }

    public void start() {
        api = HUDManager.getInstance();
        ModInstances.register(api);
        CosmeticManager.registerCosmetics(new Bandana());
    }


    public  void shutdown() {
    discordRp.shutdown();
        ConfigAPI.getInstance().save();
    }

    public DiscordRP getDiscordRP() {
        return discordRp;
    }

    @EventTarget
    public void onTick(ClientTickEvent e) {

        if(Minecraft.getMinecraft().gameSettings.CLIENT_CAPE_SWITCHER.isPressed()) {
            Minecraft.getMinecraft().displayGuiScreen(new ModMenu());
        }

        tickAmount++;
        if(tickAmount >= 20) {
            tickAmount = 0;
            seconds += 1;
        }
        if(seconds >= 60) {
            seconds = 0;
            minutes += 1;
        }
        if(minutes >= 60) {
            minutes = 0;
            hours += 1;
        }
        if(hours >= 24) {
            hours = 0;
            days += 1;
        }

    Minecraft mc = Minecraft.getMinecraft();
    if(Minecraft.getMinecraft().gameSettings.CLIENT_MOD_POSITION.isPressed()) {
        api.openConfigScreen();
    }

    if(Minecraft.getMinecraft().gameSettings.CLIENT_MENU.isPressed()) {
        Minecraft.getMinecraft().displayGuiScreen(new GUIMod());
        }
    checkForDungeonFloor();
    }


    public static String getLocationString() {
        if (!Minecraft.getMinecraft().isSingleplayer() && Minecraft.getMinecraft().getNetHandler() != null) {
            for (NetworkPlayerInfo playerInfo : Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap()) {
                if (playerInfo.getDisplayName() != null) {
                    String name = StringUtils.stripControlCodes(playerInfo.getDisplayName().getUnformattedText());
                    if (name.contains("Area")) {
                        return name.replace("Area: ", "");
                    } else if (name.contains("Dungeon")) {
                        return name.replace("Dungeon: ", "");
                    }
                }
            }
        }
        return null;
    }

    public static void checkForDungeonFloor() {
        if(SkyblockUtils.inDungeons()) {
            List<String> scoreboard = ScoreboardUtils.getSidebarLines();

            for(String s : scoreboard) {
                String sCleaned = ScoreboardUtils.cleanSB(s);

                if(sCleaned.contains("The Catacombs (")) {
                    String floor = sCleaned.substring(sCleaned.indexOf("(") + 1, sCleaned.indexOf(")"));

                    try {
                        currentFloor = DungeonFloor.valueOf(floor);
                    }catch(IllegalArgumentException ex) {
                        currentFloor = DungeonFloor.NONE;
                        ex.printStackTrace();
                    }

                }

            }

        }
    }

}



