package silkclient.mods.impl;

import java.util.Collection;

import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import silkclient.gui.hud.ScreenPosition;
import silkclient.mods.ModCategory;
import silkclient.mods.ModDraggable;

public class ModPotionStatus extends ModDraggable {

    private ScreenPosition pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
    protected FontRenderer fontRendererObj;
    protected float zLevelFloat;

    @Override
    public int getWidth() {
        return 101;
    }

    @Override
    public int getHeight() {
        return 154;
    }

    public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181704_d);
        worldrenderer.func_181662_b((double)(x + 0), (double)(y + height), (double)this.zLevelFloat).func_181673_a((double)((float)(textureX + 0) * f), (double)((float)(textureY + height) * f1)).func_181675_d();
        worldrenderer.func_181662_b((double)(x + width), (double)(y + height), (double)this.zLevelFloat).func_181673_a((double)((float)(textureX + width) * f), (double)((float)(textureY + height) * f1)).func_181675_d();
        worldrenderer.func_181662_b((double)(x + width), (double)(y + 0), (double)this.zLevelFloat).func_181673_a((double)((float)(textureX + width) * f), (double)((float)(textureY + 0) * f1)).func_181675_d();
        worldrenderer.func_181662_b((double)(x + 0), (double)(y + 0), (double)this.zLevelFloat).func_181673_a((double)((float)(textureX + 0) * f), (double)((float)(textureY + 0) * f1)).func_181675_d();
        tessellator.draw();
    }

    @Override
    public void render(ScreenPosition pos) {


        int offsetX = 21;
        int offsetY = 14;
        int i = 80;
        int i2 = 16;
        Collection<PotionEffect> collection = this.mc.thePlayer.getActivePotionEffects();

        if (!collection.isEmpty())
        {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableLighting();
            int l = 33;


            if (collection.size() > 5)
            {
                l = 132 / (collection.size() - 1);
            }

            for (PotionEffect potioneffect : this.mc.thePlayer.getActivePotionEffects())
            {
                Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

                if (potion.hasStatusIcon())
                {
                    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
                    int i1 = potion.getStatusIconIndex();
                    drawTexturedModalRect((pos.getAbsoluteX() + offsetX) - 20, (pos.getAbsoluteY() + i2) - offsetY, 0 + i1 % 8 * 18, 198 + i1 / 8 * 18, 18, 18);
                }

                String s1 = I18n.format(potion.getName(), new Object[0]);
                if (potioneffect.getAmplifier() == 1)
                {
                    s1 = s1 + " " + I18n.format("enchantment.level.2", new Object[0]);
                }
                else if (potioneffect.getAmplifier() == 2)
                {
                    s1 = s1 + " " + I18n.format("enchantment.level.3", new Object[0]);
                }
                else if (potioneffect.getAmplifier() == 3)
                {
                    s1 = s1 + " " + I18n.format("enchantment.level.4", new Object[0]);
                }

                font.drawString(s1, pos.getAbsoluteX() + offsetX, (pos.getAbsoluteY() + i2) - offsetY, 16777215, true);
                String s = Potion.getDurationString(potioneffect);
                font.drawString(s, pos.getAbsoluteX() + offsetX, (pos.getAbsoluteY() + i2 + 10) - offsetY, 8355711, true);
                i2 += l;
            }
        }
    }


}
