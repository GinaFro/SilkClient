package silkclient.utils;

import java.io.File;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import silkclient.FileManager;


public abstract class Config {

    protected final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    protected final File file;

    public Config(String fileLocation){
        file = new File( FileManager.getModsDirectory() + File.separator + fileLocation);
    }

    public abstract void save();

    public abstract void load();
}