package silkclient.mods.impl;

import net.minecraft.block.Block;
import silkclient.Client;
import silkclient.events.EventTarget;
import silkclient.events.impl.ClientChatEvent;
import silkclient.gui.hud.ScreenPosition;
import silkclient.mods.ModDraggable;
import silkclient.mods.skyblock.Location;

import java.util.logging.Logger;

public class ModCustomChatTest extends ModDraggable {

    public enum BlockChatType {



    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void render(ScreenPosition pos) {

    }

    @EventTarget
    public void onChat(ClientChatEvent e) {
        System.out.println(e.getText());
        if(e.getText().toUpperCase().contains("BOSS") && Location.locationExists(Client.getLocationString())) {
                e.setCancelled(true);
        }
    }


}
