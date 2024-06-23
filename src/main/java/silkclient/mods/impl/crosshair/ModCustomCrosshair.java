package silkclient.mods.impl.crosshair;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import silkclient.gui.hud.ScreenPosition;
import silkclient.mods.Mod;

public class ModCustomCrosshair extends Mod {



    private CrosshairType crosshair = CrosshairType.PLUS;
    private static ModCustomCrosshair instance = new ModCustomCrosshair();

    public static ModCustomCrosshair getInstance() {
        return instance;
    }

    public ResourceLocation getCrosshair() {
        return crosshair.getResource();
    }

    public void render() {
        this.mc.getTextureManager().bindTexture(getCrosshair());
        GlStateManager.color(1.0F,1.0F,1.0F,1.0F);
        GlStateManager.scale(1.0F,1.0F,1.0F);
        Gui.drawScaledCustomSizeModalRect(mc.displayWidth / 2 - 16, mc.displayHeight / 2 - 16, 0.0F,0.0F,16,16,16,16,16,16);

    }
}
