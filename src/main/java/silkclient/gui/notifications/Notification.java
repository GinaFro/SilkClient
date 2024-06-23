package silkclient.gui.notifications;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.UnicodeFont;
import silkclient.utils.ShapeUtils;
import silkclient.utils.UnicodeFontRenderer;

import static org.lwjgl.opengl.Display.getWidth;

public class Notification {
    private String messsage;
    private long start;

    private long fadedIn;
    private long fadeOut;
    private long end;

    private int length;
    private final UnicodeFontRenderer font = UnicodeFontRenderer.getFontOnPC("Arial" , 52 , Font.BOLD);
    private final ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

    public Notification(String messsage, int length) {
        this.messsage = messsage;
        this.length = length;

        fadedIn = 200 * length;
        fadeOut = fadedIn + 500 * length;
        end = fadeOut + fadedIn;
    }

    public void show() {
        start = System.currentTimeMillis();
    }

    public boolean isShown() {
        return getTime() <= end;
    }

    private long getTime() {
        return System.currentTimeMillis() - start;
    }

    public void render() {
        double offset;
        int width = font.getStringWidth(messsage) + 20;
        int height = font.FONT_HEIGHT + 10;
        long time = getTime();

        if (time < fadedIn) {
            offset = Math.tanh(time / (double) (fadedIn) * 3.0) * 10;
        } else if (time > fadeOut) {
            offset = (Math.tanh(3.0 - (time - fadeOut) / (double) (end - fadeOut) * 3.0) * 10);
        } else {
            offset = 10;
        }

        int x = (sr.getScaledWidth() / 2) - (width / 2);
        int y = (int) offset;
        ShapeUtils.drawRoundedRect(x, y, x + width, y + height, 7, new Color(0, 0, 0, 120).getRGB());
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        font.drawString(messsage, x + 10, (int) y + 6, Color.WHITE.getRGB());
    }

    public static void drawRect(double left, double top, double right, double bottom, int color) {
        if (left < right) {
            double i = left;
            left = right;
            right = i;
        }

        if (top < bottom) {
            double j = top;
            top = bottom;
            bottom = j;
        }

        float f3 = (float) (color >> 24 & 255) / 255.0F;
        float f = (float) (color >> 16 & 255) / 255.0F;
        float f1 = (float) (color >> 8 & 255) / 255.0F;
        float f2 = (float) (color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        worldrenderer.func_181662_b(left, bottom, 0.0D).func_181675_d();
        worldrenderer.func_181662_b(right, bottom, 0.0D).func_181675_d();
        worldrenderer.func_181662_b(right, top, 0.0D).func_181675_d();
        worldrenderer.func_181662_b(left, top, 0.0D).func_181675_d();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawRect(int mode, double left, double top, double right, double bottom, int color) {
        if (left < right) {
            double i = left;
            left = right;
            right = i;
        }

        if (top < bottom) {
            double j = top;
            top = bottom;
            bottom = j;
        }

        float f3 = (float) (color >> 24 & 255) / 255.0F;
        float f = (float) (color >> 16 & 255) / 255.0F;
        float f1 = (float) (color >> 8 & 255) / 255.0F;
        float f2 = (float) (color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.func_181668_a(mode, DefaultVertexFormats.field_181705_e);
        worldrenderer.func_181662_b(left, bottom, 0.0D).func_181675_d();
        worldrenderer.func_181662_b(right, bottom, 0.0D).func_181675_d();
        worldrenderer.func_181662_b(right, top, 0.0D).func_181675_d();
        worldrenderer.func_181662_b(left, top, 0.0D).func_181675_d ();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
}
