package silkclient.mods.skyblock;

import com.google.gson.JsonObject;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import silkclient.events.EventTarget;
import silkclient.events.impl.ClientChatEvent;
import silkclient.gui.hud.ScreenPosition;
import silkclient.mods.ModDraggable;
import silkclient.utils.MiscUtils;
import silkclient.utils.SkyblockUtils;

import java.awt.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModCakeTimer extends ModDraggable {
    @Override
    public int getWidth() {
        return 60;
    }

    public double cakeTime = 1.0;

    private ResourceLocation cakeIcon = new ResourceLocation("silkclient/cake.png");

    @EventTarget
    public void onChat(ClientChatEvent e) {
        if (e.getText().contains("Yum!")) {
            this.cakeTime = System.currentTimeMillis() / 1000 + 172800;
        } else if (e.getText().contains("You may eat some of it again in ")) { // Keeping this. Maybe Hypixel will revert changes ?
            Matcher hoursMatcher = Pattern.compile("(\\d+)h").matcher(e.getText());
            Matcher daysMatcher = Pattern.compile("(\\d+)d").matcher(e.getText());
            Matcher minutesMatcher = Pattern.compile("(\\d+)m").matcher(e.getText());
            Matcher secondsMatcher = Pattern.compile("(\\d+)s").matcher(e.getText());
            int days = daysMatcher.find() ? Integer.parseInt(daysMatcher.group(1)) : 0;
            int hours = hoursMatcher.find() ? Integer.parseInt(hoursMatcher.group(1)) : 0;
            int minutes = minutesMatcher.find() ? Integer.parseInt(minutesMatcher.group(1)) : 0;
            int seconds = secondsMatcher.find() ? Integer.parseInt(secondsMatcher.group(1)) : 0;
            cakeTime = System.currentTimeMillis() / 1000 + days * 86400 + hours * 3600 + minutes * 60 + seconds;

        }
    }

    @Override
    public void saveSettings(JsonObject settings) {
        super.saveSettings(settings);
        settings.addProperty("cakeTime" ,  cakeTime);
    }

    @Override
    public void loadSettings(JsonObject settings) {
        super.loadSettings(settings);
        double savedData = settings.get("cakeTime").getAsDouble();
        double timeNow = System.currentTimeMillis() / 1000;
        this.cakeTime = getTimeBetweenInt(timeNow, savedData);
    }

    @Override
    public int getHeight() {
        return 40;
    }

    @Override
    public void render(ScreenPosition pos) {
        if(cakeTime != 1.0) {
        }
        GlStateManager.scale(1,1,1);
        if(SkyblockUtils.inSkyblock()) {
            Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth() + 1, pos.getAbsoluteY() + getHeight(), 0x90000000);
            mc.getTextureManager().bindTexture(cakeIcon);
            GlStateManager.color(1.0F,1.0F,1.0F);
            Gui.drawScaledCustomSizeModalRect(pos.getAbsoluteX() + (getWidth() / 2 - 8), pos.getAbsoluteY() + + 4 , 0.0F, 0.0F,16,16,16,16,16,16);
            Gui.drawCenteredString(font ,getText() , pos.getAbsoluteX() + (getWidth()/2) , pos.getAbsoluteY() + (getHeight() - getHeight()/4), -1);
        }
    }

    String getText() {
        double timeNow = System.currentTimeMillis() / 1000;
        if (this.cakeTime - timeNow < 0) {
            return EnumChatFormatting.RED + "NONE";
        } else {
            return EnumChatFormatting.RED + getTimeBetween(timeNow,cakeTime);
        }
    }

    public static double getTimeBetweenInt(double timeOne,double timeTwo) {
        double secondsBetween = Math.floor(timeTwo - timeOne);
        return secondsBetween;

    }

    public static String getTimeBetween(double timeOne, double timeTwo) {
        double secondsBetween = Math.floor(timeTwo - timeOne);

        String timeFormatted;
        int days;
        int hours;
        int minutes;
        int seconds;

        if (secondsBetween > 86400) {
            // More than 1d, display #d#h
            days = (int) (secondsBetween / 86400);
            hours = (int) (secondsBetween % 86400 / 3600);
            minutes = (int) ( secondsBetween % 3600 / 60);
            timeFormatted = days + "d" + hours + "h" + minutes + "m";
        } else if (secondsBetween > 3600) {
            // More than 1h, display #h#m
            hours = (int) (secondsBetween / 3600);
            minutes = (int) (secondsBetween % 3600 / 60);
            seconds = (int) (secondsBetween % 60);
            timeFormatted = hours + "h" + minutes + "m" + seconds + "s";
        } else {
            // Display #m#s
            minutes = (int) (secondsBetween / 60);
            seconds = (int) (secondsBetween % 60);
            timeFormatted = minutes + "m" + seconds + "s";
        }



        return timeFormatted;
    }


}
