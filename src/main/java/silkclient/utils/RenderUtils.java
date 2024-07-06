package silkclient.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class RenderUtils {


    private static final Animator animator = new Animator(3000.0F);
    private static boolean flag = true;

    private static final Map<String, ResourceLocation> playerSkins = new HashMap<>();
    final static Minecraft mc = Minecraft.getMinecraft();

    public static ResourceLocation getHeadLocation(String displayName) {
        ResourceLocation playerSkin = (ResourceLocation)playerSkins.getOrDefault(displayName, new ResourceLocation("urban/ui/heads/" + displayName + ".png"));
        if (!playerSkins.containsKey(displayName)) {
            ThreadDownloadImageData skinData = new ThreadDownloadImageData(null, "https://minotar.net/helm/" + displayName + "/32.png", new ResourceLocation("urban/ui/heads/steve.png"), null);
            (Minecraft.getMinecraft()).getTextureManager().loadTexture(playerSkin, skinData);
            playerSkins.put(displayName, playerSkin);
        }
        return playerSkin;
    }


    public static void renderPlayerOnScreen(int x, int y, int scale, EntityPlayer player) {
        float f = RenderUtils.animator.getValue(138.0F, 222.0F, RenderUtils.flag);

        if (f == 222.0F && RenderUtils.flag) {
            RenderUtils.flag = false;
            RenderUtils.animator.reset();
        } else if (f == 138.0F && !RenderUtils.flag) {
            RenderUtils.flag = true;
            RenderUtils.animator.reset();
        }

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x, (float) y, 50.0F);
        GlStateManager.scale((float) (-scale), (float) scale, (float) scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float renderYawOffset = player.renderYawOffset;
        float rotationYaw = player.rotationYaw;
        float rotationPitch = player.rotationPitch;
        float rotationYawHead = player.rotationYawHead;

        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        player.renderYawOffset = f;
        player.rotationYaw = f;
        player.rotationPitch = 0.0F;
        player.rotationYawHead = f;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();

        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntityWithPosYaw(player, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        rendermanager.setRenderShadow(true);
        player.renderYawOffset = renderYawOffset;
        player.rotationYaw = rotationYaw;
        player.rotationPitch = rotationPitch;
        player.rotationYawHead = rotationYawHead;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

}
