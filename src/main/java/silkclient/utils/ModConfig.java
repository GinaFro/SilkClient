package silkclient.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import silkclient.cosmetics.CapeController;
import silkclient.gui.hud.HUDManager;
import silkclient.mods.Mod;
import silkclient.mods.ModCategory;
import silkclient.mods.ModDraggable;

import java.io.*;

public class ModConfig extends Config{

    private static ModConfig instance = new ModConfig();

    public static ModConfig getInstance() {
        return instance;
    }

    public ModConfig() {
        super("settings.json");
    }



    @Override
    public void save() {
        JsonArray modules = new JsonArray();
        for(ModDraggable m : HUDManager.getInstance().getMods()){
            JsonObject jsonModule = new JsonObject();
            modules.add(jsonModule);
            m.saveSettings(jsonModule);
        }
        JsonObject jsonModule = new JsonObject();
        modules.add(jsonModule);
        JsonObject jsonObject = new JsonObject();


        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        String s = gson.toJson(modules);
        try{
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getPath());
            fw.write(s);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        try{
            BufferedReader br = new BufferedReader(new FileReader(file.getPath()));
            JsonArray modules = gson.fromJson(br, JsonArray.class);

            if(!modules.isJsonNull()){
                for(JsonElement je : modules){
                    JsonObject jsonModule = je.getAsJsonObject();
                    for(ModDraggable m : HUDManager.getInstance().getMods()){
                        if(jsonModule.get("name") == null) return;
                        if(jsonModule != null && jsonModule.get("name").getAsString().equals(m.getClass().getSimpleName())) {
                            m.loadSettings(jsonModule);
                        }
                    }
                }
            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
