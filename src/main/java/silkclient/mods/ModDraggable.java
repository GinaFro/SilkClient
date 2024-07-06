package silkclient.mods;

import java.io.File;
import java.io.IOException;

import com.google.gson.JsonObject;

import silkclient.FileManager;
import silkclient.gui.hud.IRenderer;
import silkclient.gui.hud.ScreenPosition;

public abstract class ModDraggable extends Mod implements IRenderer {


    protected ScreenPosition pos;
    protected boolean isChromaOn;

    public void toggleChroma() {
        this.isChromaOn = !isChromaOn;
    }

    public boolean isChromaOn() {
        return isChromaOn;
    }

    public ModDraggable() {
        try {
            pos = loadPositionFromFile();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveSettings(JsonObject settings) {
        settings.addProperty("name" , this.getClass().getSimpleName());
        settings.addProperty("enabled" , isEnabled);
        settings.addProperty("chroma" , isChromaOn);
    }

    public void loadSettings(JsonObject settings) {
        setEnabled(settings.get("enabled").getAsBoolean());
        setChroma(settings.get("chroma").getAsBoolean());
    }

    public void setChroma(boolean chromaOn) {
        isChromaOn = chromaOn;
    }

    public File getFolder() {
        File folder = new File(FileManager.getModsDirectory() , this.getClass().getSimpleName());
        folder.mkdirs();
        return folder;
    }

    public void savePositionToFile() {
    FileManager.writeJsonToFile(new File(getFolder(), "pos.json"), this.pos);
    }

    private ScreenPosition loadPositionFromFile() throws IOException {

        ScreenPosition loaded = FileManager.readFromJson(new File(getFolder() , "pos.json"), ScreenPosition.class);
        if(loaded == null) {
            loaded = ScreenPosition.fromRelativePosition(0.5 , 0.5);
            this.pos = loaded;
            savePositionToFile();
        }

        return loaded;

    }


    public final int getLineOffset(ScreenPosition pos , int lineNum) {
        return pos.getAbsoluteX() + getLineOffset(lineNum);
    }

    private int getLineOffset(int lineNum) {
        return (font.FONT_HEIGHT + 3) * lineNum;
    }

    @Override
    public void save(ScreenPosition pos) {
        this.pos = pos;
        savePositionToFile();
    }



    @Override
    public ScreenPosition load() {
       return pos;
    }
}
