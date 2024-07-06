package silkclient.mods.impl;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import silkclient.gui.hud.ScreenPosition;
import silkclient.mods.ModDraggable;
import silkclient.utils.StringUtils;

import java.awt.*;

public class ModArmor_Status extends ModDraggable {


    @Override
    public int getWidth() {
        return 64;
    }

    @Override
    public int getHeight() {
        return 64;
    }



    @Override
    public void render(ScreenPosition pos) {
        Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth() + 1, pos.getAbsoluteY() + getHeight(),  new Color(0 ,0 , 0 , 102).getRGB());
        for(int i = 0; i < this.mc.thePlayer.inventory.armorInventory.length; i++) {
            ItemStack item = mc.thePlayer.inventory.armorItemInSlot(i);
            renderItemStack(pos , i , item);
        }
    }


    public void renderItemStack(ScreenPosition pos , int i ,  ItemStack is) {

        if(is == null) {
            return;
        }

        GL11.glPushMatrix();
        int yAdd = (-16 * i) + 48;

        if(is.getItem().isDamageable()) {
            double damage = ((is.getMaxDamage() - is.getItemDamage()) / (double) is.getMaxDamage()) * 100;
            if(this.isChromaOn()) {
                StringUtils.getInstance().drawChromaString(String.format("%.2f%%", damage), pos.getAbsoluteX() + 20, pos.getAbsoluteY() + yAdd + 5, false);
            }else {
                Gui.drawCenteredString(font, String.format("%.2f%%", damage), pos.getAbsoluteX() + 40, pos.getAbsoluteY() + yAdd + 5, -1);
            }
        }

        RenderHelper.enableGUIStandardItemLighting();

        mc.getRenderItem().renderItemAndEffectIntoGUI(is, pos.getAbsoluteX() , pos.getAbsoluteY() + yAdd);
        GL11.glPopMatrix();
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        renderItemStack(pos, 3, new ItemStack(Items.diamond_helmet));
        renderItemStack(pos, 2, new ItemStack(Items.diamond_chestplate));
        renderItemStack(pos, 1, new ItemStack(Items.diamond_leggings));
        renderItemStack(pos, 0, new ItemStack(Items.diamond_boots));
    }




}
