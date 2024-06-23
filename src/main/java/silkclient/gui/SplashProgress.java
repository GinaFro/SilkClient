package silkclient.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import silkclient.utils.UnicodeFontRenderer;

import java.awt.*;

public class SplashProgress {

    private static final int MAX = 6;
    private static int PROGRESS = 0;
    private static String CURRENT = "";
    private static ResourceLocation SPLASH;
    private static UnicodeFontRenderer ufr;

    public static void update() {
        if(Minecraft.getMinecraft() == null || Minecraft.getMinecraft().getLanguageManager() == null) {
            return;
        }
        drawSplash(Minecraft.getMinecraft().getTextureManager());
    }

    public static void setProgress(int givenProgress , String givenText) {
        PROGRESS = givenProgress;
        CURRENT = givenText;
        update();
    }

    public static void drawSplash(TextureManager tm ) {

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int scaleFactor = sr.getScaleFactor();

        Framebuffer fb = new Framebuffer(sr.getScaledWidth() * scaleFactor, sr.getScaledHeight() * scaleFactor, true);
        fb.bindFramebuffer(false);

        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.00, sr.getScaledWidth_double(), sr.getScaledHeight_double(), 0.0, 1000.0, 3000.0);
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.disableDepth();
        GlStateManager.enableTexture2D();

        if(SPLASH == null) {
            SPLASH = new ResourceLocation("silkclient/splash.png");
        }

        tm.bindTexture(SPLASH);

        GlStateManager.resetColor();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        Gui.drawScaledCustomSizeModalRect(0, 0, 0, 0, 1920, 1080, sr.getScaledWidth(), sr.getScaledHeight(), 1920, 1080);
        drawProgress();
        fb.unbindFramebuffer();
        fb.framebufferRender(sr.getScaledWidth() * scaleFactor, sr.getScaledHeight() * scaleFactor);

        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);

        Minecraft.getMinecraft().updateDisplay();

    }

    public static void drawProgress() {
        if(Minecraft.getMinecraft().gameSettings == null || Minecraft.getMinecraft().getTextureManager() == null) {
            return;
        }

        if(ufr == null) {
            ufr = UnicodeFontRenderer.getFontOnPC("Arial", 20);
        }

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int totalWidth = sr.getScaledWidth() - sr.getScaledWidth() / 4;

        double nProgress = (double)PROGRESS + 1;
        double calc = (nProgress/MAX ) * totalWidth;

        Gui.drawRect(sr.getScaledWidth() / 8, (sr.getScaledHeight() - sr.getScaledHeight() / 4) - 15, sr.getScaledWidth() - sr.getScaledWidth() / 8, sr.getScaledHeight() - sr.getScaledHeight() / 4, new Color(0 , 0 , 0 , 50).getRGB());
        GlStateManager.resetColor();
        resetTextureState();

        ufr.drawString("Silk Client - " + CURRENT , sr.getScaledWidth() / 8 + 10 , (sr.getScaledHeight() - sr.getScaledHeight() / 8) - 25 , -1);

        String s = PROGRESS + " / " + MAX;

        ufr.drawString(s , (sr.getScaledWidth() - sr.getScaledWidth() / 8) - 10 - ufr.getStringWidth(s), (sr.getScaledHeight() - sr.getScaledHeight() / 8) - 25 , -1);

        GlStateManager.resetColor();
        resetTextureState();

        Gui.drawRect(sr.getScaledWidth() / 8  , (sr.getScaledHeight() - sr.getScaledHeight() / 4) - 15, (int)calc, sr.getScaledHeight() - sr.getScaledHeight() / 4, -1);
        Gui.drawRect(sr.getScaledWidth() / 8  , (sr.getScaledHeight() - sr.getScaledHeight() / 4) - 15, (sr.getScaledWidth() - sr.getScaledWidth() / 8), sr.getScaledHeight() - sr.getScaledHeight() / 4, new Color(0 , 0 , 0 , 10).getRGB());


    }

    private static void resetTextureState() {
        GlStateManager.textureState[GlStateManager.activeTextureUnit].textureName = -1;
    }

}
