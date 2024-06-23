package silkclient.mods;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import silkclient.Client;
import silkclient.FileManager;
import silkclient.events.EventManager;

import java.io.File;

public class Mod {


    private boolean isEnabled = true;

    protected final Minecraft mc;
    protected final FontRenderer font;
    protected final Client client;

    public Mod() {
        this.mc = Minecraft.getMinecraft();
        this.font = mc.fontRendererObj;
        this.client = Client.getInstance();

        setEnabled(isEnabled);
    }

    public File getFolder() {
        File folder = new File(FileManager.getModsDirectory() , this.getClass().getSimpleName());
        folder.mkdirs();
        return folder;
    }

    public void toggle() {
        this.isEnabled = !this.isEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
        if (isEnabled) {
            EventManager.register(this);
        } else {
            EventManager.unregister(this);
        }

    }


    public boolean isEnabled() {
        return isEnabled;
    }



}
