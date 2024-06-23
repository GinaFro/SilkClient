package silkclient.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import silkclient.FileManager;
import silkclient.mods.ModDraggable;

import java.io.File;

public abstract class Config {


    protected final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    protected final File file;

    public Config(File file){
        this.file = file;
    }

    public Config(String fileLocation) {
        file = new File( FileManager.getModsDirectory() + File.separator + fileLocation);
    }


    public abstract void save();

    public abstract void load();

}
