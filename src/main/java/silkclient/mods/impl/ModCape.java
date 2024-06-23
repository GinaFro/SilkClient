package silkclient.mods.impl;

import com.google.gson.JsonObject;
import silkclient.cosmetics.CapeController;
import silkclient.gui.hud.ScreenPosition;
import silkclient.mods.ModDraggable;

public class ModCape extends ModDraggable {
    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public void saveSettings(JsonObject settings) {
        super.saveSettings(settings);
        settings.addProperty("cape", CapeController.getCurrentCape().getId());
    }

    private int cape;

    @Override
    public void loadSettings(JsonObject settings) {
        super.loadSettings(settings);
        this.cape = settings.get("cape").getAsInt();
    }

    public CapeController.Cape getCape() {
        return CapeController.Cape.getCapeFromID(cape);
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void render(ScreenPosition pos) {

    }
}
